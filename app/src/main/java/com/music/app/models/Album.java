package com.music.app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "albums")
public class Album {
    @Id
    private String id;
    private String albumName;
    private String image;
    private Map<String, String> links;

    public Album(String albumName, String image, Map<String, String> links) {
        this.albumName = albumName;
        this.image = image;
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> newLinks) {
        this.links = newLinks;
    }
}
