package com.software.fire.firebaseuidatabasetutorial.models;

/**
 * Created by Brad on 12/25/2016.
 */

public class Post {
    private String UID;
    private String text;

    public Post() {
    }

    public Post(String UID, String text) {

        this.UID = UID;
        this.text = text;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
