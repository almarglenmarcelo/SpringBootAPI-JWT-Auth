package com.zuitt.discussion.services.course;

import com.zuitt.discussion.model.Course;
import com.zuitt.discussion.model.User;
import org.springframework.http.ResponseEntity;

public interface CourseService {


    Iterable<Course> getCourses();

    ResponseEntity getCourseById(int courseId);

    ResponseEntity createCourse(Course course);

    ResponseEntity deleteCourse(int courseId, User user, String token);

    ResponseEntity updateCourse(int courseId, Course course);

    ResponseEntity addEnrollees(User user, String token, int courseId);

}
