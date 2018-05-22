package com.example.a16022774.c302_photostoreclient_ps;

public class Photo {

    private String title;
    private String description;
    private String creator;
    private String image;

    public Photo(String title, String description, String creator, String image) {
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
