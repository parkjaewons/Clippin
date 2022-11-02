package com.example.testcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();
    TextView textView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        textView = (TextView) findViewById(R.id.textview);
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
            url = intent.getStringExtra(Intent.EXTRA_TEXT);
            }
        }

        Python py = Python.getInstance();
        PyObject pyobj = py.getModule("test");

        //페이지에서 공유를 했을때만 url에 데이터 들어가면서 아래 코드 실행
        if (url != null) {
            PyObject text = pyobj.callAttr("text", url);
            PyObject title = pyobj.callAttr("title", url);
            PyObject description = pyobj.callAttr("description", url);
            PyObject Url = pyobj.callAttr("Url", url);

            databaseReference.child("news").child("text").push().setValue(text.toString());
            databaseReference.child("news").child("title").push().setValue(title.toString());
            databaseReference.child("news").child("description").push().setValue(description.toString());
            databaseReference.child("news").child("Url").push().setValue(Url.toString());
        }
    }
}