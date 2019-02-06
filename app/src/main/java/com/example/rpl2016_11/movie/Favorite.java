package com.example.rpl2016_11.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class Favorite extends AppCompatActivity {

    nowAdapter adapter;
    Toolbar toolbar;
    List<nowItem> data;
    dbHelper db;
    RecyclerView recView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        toolbar = findViewById(R.id.toolbar);
        data = new ArrayList<>();
        recView =  findViewById(R.id.recView);
        db = new dbHelper(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data.addAll(db.readAll());
       // adapter = new nowAdapter(getApplicationContext(), data);

        recView.setLayoutManager(new GridLayoutManager(this, 2));
        recView.setAdapter(adapter);
    }
}
