package com.example.testcode;

public class model {
    private String imageUri;

    model(){

    }
    public model(String imageUri) {
        this.imageUri = imageUri;
    }
    public String getImageUri(){
        return imageUri;
    }
    public void setImageUri(String imageUri){
        this.imageUri = imageUri;
    }
}
