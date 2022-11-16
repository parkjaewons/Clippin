package com.example.testcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ScrapList extends AppCompatActivity {

    RecyclerView recyclerView;
    ScrapAdapter scrapAdapter;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_list);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Scrap> options =
                new FirebaseRecyclerOptions.Builder<Scrap>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("news"),Scrap.class).build();

        scrapAdapter = new ScrapAdapter(options);
        recyclerView.setAdapter(scrapAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        scrapAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        scrapAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str){
        FirebaseRecyclerOptions<Scrap> options =
                new FirebaseRecyclerOptions.Builder<Scrap>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("news").orderByChild("title").startAt(str).endAt(str+"~"),Scrap.class)
                        .build();

        scrapAdapter = new ScrapAdapter(options);
        scrapAdapter.startListening();
        recyclerView.setAdapter(scrapAdapter);
    }
}