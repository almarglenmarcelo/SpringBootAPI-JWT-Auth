package com.zuitt.discussion.services.course;

import com.zuitt.discussion.config.JwtToken;
import com.zuitt.discussion.model.Course;
import com.zuitt.discussion.model.Post;
import com.zuitt.discussion.model.User;
import com.zuitt.discussion.repositories.CourseRepository;
import com.zuitt.discussion.repositories.PostRepository;
import com.zuitt.discussion.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtToken jwtToken;


    @Override
    public Iterable<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public ResponseEntity createCourse(Course course) {

        courseRepository.save(course);
        return new ResponseEntity<>("Course Added!", HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity deleteCourse(int postId) {

        Optional<Course> courseToBeDeleted = courseRepository.findById(postId);

        if(courseToBeDeleted.isPresent()){
            courseRepository.deleteById(postId);
            return new ResponseEntity<>("Course has been Deleted!", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Course Doesn't Exist!", HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity updateCourse(int courseId, Course course) {
        Optional<Course> theCourse = courseRepository.findById(courseId);

        Course courseToBeUpdated;
        if(theCourse.isPresent()){
            courseToBeUpdated = theCourse.get();
            courseToBeUpdated.setName(course.getName());
            courseToBeUpdated.setDescription(course.getDescription());
            courseToBeUpdated.setPrice(course.getPrice());
            courseToBeUpdated.setEnrollee(course.getEnrollee());

            courseRepository.save(courseToBeUpdated);
        }else {
            return new ResponseEntity<>("Course Does not exists!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Course Updated Successfully!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity getCourseById(int postId) {
        return null;
    }

    @Override
    public Iterable<Course> getMyCourses(String stringToken) {
        return null;
    }
}
