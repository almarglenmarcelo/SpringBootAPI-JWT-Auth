package com.zuitt.discussion.model;


import javax.persistence.*;

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


    @ManyToOne
    @JoinColumn(name="enrollee_id")
    private User enrollee;

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


    public User getEnrollee() {
        return enrollee;
    }

    public void setEnrollee(User enrollee) {
        this.enrollee = enrollee;
    }


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", enrollee=" + enrollee +
                '}';
    }
}
