package com.example.testcode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Scrap{
    String Url; //url주소
    String text;//주소 텍스트
    String title;//제목
    String description;//요약 내용

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




    //값을 추가할때 쓰는 함수, MainActivity에서 addanimal함수에서 사용할 것임.
    public Scrap(String text, String title, String description, String Url){
        this.text = text;
        this.title = title;
        this.description = description;
        this.Url = Url;
    }
}