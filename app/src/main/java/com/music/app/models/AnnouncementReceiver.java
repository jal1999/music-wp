package com.music.app.models;

import java.util.Date;

public class AnnouncementReceiver {
    private String firstName;
    private String lastName;
    private String email;
    private Date dateJoined;
    private Date dateLeft;

    public AnnouncementReceiver(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateJoined = new Date();
    }
}
