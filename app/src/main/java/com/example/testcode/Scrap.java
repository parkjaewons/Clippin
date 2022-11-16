package com.example.testcode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Scrap{
    String Url; //url주소
    String text;//주소 텍스트
    String title;//제목
    String description;//요약 내용
    String image_url; //이미지 url
    String keyword;

    public Scrap(){} // 생성자 메서드

    //getter, setter 설정
    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url(){ return image_url;}

    public void setImage_url(String image_url) { this.image_url = image_url; }

    public String getKeyword(){ return keyword;}

    public void setKeyword(String keyword) { this.keyword = keyword; }




    public Scrap(String text, String title, String description, String Url, String image_url, String keyword){
        this.text = text;
        this.title = title;
        this.description = description;
        this.Url = Url;
        this.image_url = image_url;
        this.keyword = keyword;
    }
}