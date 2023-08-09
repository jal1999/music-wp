package com.music.app.models;

import com.music.app.models.AnnouncementReceiver;

import java.util.Date;
import java.util.List;

public class Announcement {
    private String content;
    private Date dateCreated;
    private String creator;
    private List<AnnouncementReceiver> receivers;

    public Announcement(String content, Date dateCreated, String creator, List<AnnouncementReceiver> receivers) {
        this.content = content;
        this.dateCreated = dateCreated;
        this.creator = creator;
        this.receivers = receivers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<AnnouncementReceiver> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<AnnouncementReceiver> receivers) {
        this.receivers = receivers;
    }
}
