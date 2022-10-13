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
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtToken jwtUtil;

    @Override
    public Iterable<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public ResponseEntity getCourseById(int courseId) {


        return new ResponseEntity<>(courseRepository.findById(courseId) , HttpStatus.OK);
    }



    @Override
    public ResponseEntity createCourse(Course course) {

        courseRepository.save(course);
        return new ResponseEntity<>(String.format("The course %s has been added! Id of %s.", course.getName(), course.getId()), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity deleteCourse(int postId, User user, String token) {

        Optional<Course> courseToBeDeleted = courseRepository.findById(postId);

        User theAdmin = userRepository.findByUsername(jwtUtil.getUsernameFromToken(token));
        User loggedInUser = userRepository.findByUsername(user.getUsername());

        if (theAdmin != null && loggedInUser.getUsername().equals(theAdmin.getUsername())) {

            if(courseToBeDeleted.isEmpty())
                return new ResponseEntity<>(String.format("Course does not exists!! "), HttpStatus.BAD_REQUEST);


            courseRepository.deleteById(postId);
            return new ResponseEntity<>(String.format("The Course %s has been Deleted! ", courseToBeDeleted.get().getName() ), HttpStatus.OK);

        }else {
            return new ResponseEntity<>(String.format("You are authorized to delete this! "), HttpStatus.UNAUTHORIZED);
        }


    }

    @Override
    public ResponseEntity updateCourse(int courseId, String token, Course course) {

        User theAdmin = userRepository.findByUsername(jwtUtil.getUsernameFromToken(token));

        if(theAdmin != null) {

            Optional<Course> theCourse = courseRepository.findById(courseId);
            Course courseToBeUpdated;

            if(theCourse.isPresent()){
                courseToBeUpdated = theCourse.get();
                courseToBeUpdated.setName(course.getName());
                courseToBeUpdated.setDescription(course.getDescription());
                courseToBeUpdated.setPrice(course.getPrice());
                courseToBeUpdated.setEnrollees(course.getEnrollees());

                courseRepository.save(courseToBeUpdated);


            }else {
                return new ResponseEntity<>("Course Does not exists!", HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>("Course Updated Successfully!", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("You are authorized to Update this course!", HttpStatus.UNAUTHORIZED);
        }


    }

    @Override
    public ResponseEntity addEnrollees(User user, String token, int courseId) {

        User userToBeAdded = userRepository.findByUsername(user.getUsername());
        Optional<Course> tempCourse = courseRepository.findById(courseId);
        String authenticatedUsername = jwtUtil.getUsernameFromToken(token);
        String username;
        Course theCourse;

        if(userToBeAdded != null) {
            username = userToBeAdded.getUsername();
        }
        else {
            return new ResponseEntity<>("User does not exist!", HttpStatus.BAD_REQUEST);
        }

        if(tempCourse.isEmpty()) {
            return new ResponseEntity<>("Course does not exist!", HttpStatus.BAD_REQUEST);
        }
        else {
            theCourse = tempCourse.get();
        }


        if(username.equals(authenticatedUsername)) {
            theCourse.addEnrollee(userToBeAdded);
            return new ResponseEntity<>(String.format("The user %s has been added as Enrollee of course %s", userToBeAdded.getUsername(), theCourse.getName()), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("The user is not authorized to be add", HttpStatus.UNAUTHORIZED);
        }


    }

}
