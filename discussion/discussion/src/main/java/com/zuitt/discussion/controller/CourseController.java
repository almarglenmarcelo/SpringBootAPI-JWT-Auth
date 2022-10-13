package com.zuitt.discussion.controller;

import com.zuitt.discussion.model.Course;
import com.zuitt.discussion.model.Post;
import com.zuitt.discussion.model.User;
import com.zuitt.discussion.services.course.CourseService;
import com.zuitt.discussion.services.posts.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;


    @GetMapping("/courses")
    public Iterable<Course> getCourses() {
        return courseService.getCourses();
    }


    @PostMapping("/courses")
    public ResponseEntity createCourse(@RequestBody Course course){

        return courseService.createCourse(course);
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity deleteCourse(@PathVariable int courseId){
        System.out.println(courseId);
        return courseService.deleteCourse(courseId);
    }

    @PutMapping("/courses/{courseId}")
    public ResponseEntity updateCourse(@PathVariable int courseId, @RequestBody Course course){
        return  courseService.updateCourse(courseId, course);
    }

}
