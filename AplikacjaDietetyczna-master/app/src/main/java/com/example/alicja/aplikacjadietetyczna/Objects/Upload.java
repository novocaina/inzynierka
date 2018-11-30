package com.example.alicja.aplikacjadietetyczna.Objects;

public class Upload {
    private String name;
    private String imageUrl;

    public Upload() {
    }

    public Upload(String imgName, String image) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        name = imgName;
        imageUrl = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String imgName) {
        name = imgName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String image) {
        imageUrl = image;
    }
}