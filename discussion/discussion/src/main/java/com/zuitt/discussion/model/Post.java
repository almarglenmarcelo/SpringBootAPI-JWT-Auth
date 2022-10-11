package com.zuitt.discussion.model;


import javax.persistence.*;


@Entity
@Table(name="posts")
public class Post {


    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;
    

    // Constructors
    public Post(){}

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }



    // Getters and Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
                                                          
    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
