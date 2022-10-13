package com.zuitt.discussion.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;


    @Column(name="price")
    private Double price;



    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    @JoinTable( name="course_user",
                joinColumns = @JoinColumn(name="course_id"),
                inverseJoinColumns = @JoinColumn(name="user_id"))
    private List<User> enrollees;


    public Course() {}

    public Course(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<User> getEnrollees() {
        return enrollees;
    }

    public void setEnrollees(List<User> enrollees) {
        this.enrollees = enrollees;
    }

//    Add convenience Method
    public void addEnrollee(User theUser) {
        if(enrollees == null)
            enrollees = new ArrayList<>();


        enrollees.add(theUser);


    }

}
