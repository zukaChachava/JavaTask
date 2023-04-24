package com.exam.assigment.models;

import java.util.Date;

public class Post {
    private int Id;
    private String Header;
    private String Body;
    private String Author;
    private Date CreatedAt;

    public Post(){}

    public Post(int id, String header, String body, String author, Date createdAt) {
        Id = id;
        Header = header;
        Body = body;
        Author = author;
        CreatedAt = createdAt;
    }

    public String getHeader() {
        return Header;
    }

    public void setHeader(String header) {
        Header = header;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        CreatedAt = createdAt;
    }
}
