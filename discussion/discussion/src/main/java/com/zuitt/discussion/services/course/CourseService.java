package com.zuitt.discussion.services.course;

import com.zuitt.discussion.model.Course;
import org.springframework.http.ResponseEntity;

public interface CourseService {


    Iterable<Course> getCourses();

    ResponseEntity createCourse(Course course);

    ResponseEntity deleteCourse(int courseId);

    ResponseEntity updateCourse(int courseId, Course course);

    ResponseEntity getCourseById(int courseId);

    Iterable<Course> getMyCourses(String stringToken);


}
