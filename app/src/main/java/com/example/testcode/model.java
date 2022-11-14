package com.example.testcode;


public class model {
    private String imageuri;

    public model(){
    }

    public model(String imageuri){
        this.imageuri=imageuri;
    }

    public String getImageUri(){
        return imageuri;
    }

    public void setImageUri(String imageuri){
        this.imageuri = imageuri;
    }
}
