package com.example.testcode;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Scrap_info extends AppCompatActivity {
    ImageView scrapimage;
    TextView scraptitle, scraptext, scrapkeyword,scrapurl;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_info);

        scrapimage = findViewById(R.id.scrap_image);
        scraptitle = findViewById(R.id.scrap_title);
        scraptext = findViewById(R.id.scrap_text);
        scrapkeyword = findViewById(R.id.scrap_keyword);
        scrapurl = findViewById(R.id.scrap_url);
        scraptext.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();
        String title = intent.getExtras().getString("title");
        String text = intent.getExtras().getString("maintext");
        String keyword = intent.getExtras().getString("keyword");
        String url = intent.getExtras().getString("url");
        String image = intent.getExtras().getString("image");

        Glide.with(Scrap_info.this).load(image).into(scrapimage);

        scraptitle.setText(title);
        scraptext.setText(text);
        scrapkeyword.setText(keyword);
        scrapurl.setText(url);
    }
}