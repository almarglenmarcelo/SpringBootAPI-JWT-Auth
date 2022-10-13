package com.zuitt.discussion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;


//  Set class - is a collection that contains no duplicate elements
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    Prevent infinite recursion with bidirectional relationships
    @JsonIgnore
    private Set<Post> posts;



    @ManyToMany( fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    @JoinTable( name="course_user",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="course_id"))
    private List<Course> enrollments;


    public User() {

    }

    public User( String username, String password) {
        this.username = username;
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public List<Course> getCourses() {
        return enrollments;
    }

    public void setCourses(List<Course> courses) {
        this.enrollments = courses;
    }


}
