package com.seng401.mango;

import model.PostCategory;

import java.util.UUID;

//Form that receives information to add a new post to the database
public class PostForm {
    private String title;
    private String message;
    private UUID userID;
    private PostCategory category;

    public PostForm() {
        super();
    }

    public UUID getUserID() {
        return userID;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PostCategory getCategory() {
        return category;
    }

    public void setCategory(PostCategory category) {
        this.category = category;
    }
}
