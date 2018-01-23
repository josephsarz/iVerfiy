package com.codegene.femicodes.cscproject.model;

/**
 * Created by EKENE on 11/4/2017.
 */

public class Post {

    private String title, desc, imageUrl;

    public Post(String title, String desc, String imageUrl) {
        this.title = title;
        this.desc = desc;
        this.imageUrl = imageUrl;
    }

    public Post() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
