package com.example.testcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();

    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://127.0.0.1:5000/").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                TextView textView=findViewById(R.id.textview);
                textView.setText(response.body().string());
            }
        });

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

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
            PyObject imagedown = pyobj.callAttr("image",url);

            addScrap(text.toString(),title.toString(),description.toString(),Url.toString());


        }
    }
    //값을 파이어베이스 Realtime database로 넘기는 함수
    public void addScrap(String text, String title, String description, String Url) {

        //여기에서 직접 변수를 만들어서 값을 직접 넣는것도 가능합니다.
        // ex) 갓 태어난 동물만 입력해서 int age=1; 등을 넣는 경우

        //animal.java에서 선언했던 함수.
        Scrap Scrap = new Scrap(text,title,description,Url);

        databaseReference.child("news").push().setValue(Scrap);

    }
}