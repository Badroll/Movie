package com.example.rpl2016_11.movie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class detail extends AppCompatActivity {

    ImageView picture;
    TextView title, rate, language, description, date, popularity;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailmovie);

        ImageButton share = new ImageButton(this);
        share.setLayoutParams(new ViewGroup.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT));
        share.setImageResource(R.drawable.ic_share);

        picture = findViewById(R.id.pict);
        title = findViewById(R.id.title);
        rate = findViewById(R.id.rate);
        language =  findViewById(R.id.language);
        description = findViewById(R.id.desc);
        date = findViewById(R.id.date);
        popularity = findViewById(R.id.popularity);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Movie Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nowItem data = getIntent().getParcelableExtra("parcel");
        if (data  != null){
            Picasso.with(this).load(data.getImageurl()).into(picture);
            title.setText(data.getTittle());
            rate.setText(data.getRate());
            language.setText(data.getLanguage());
            description.setText(data.getDescription());
            date.setText(data.getDate());
            popularity.setText(data.getPopularity());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                Intent sharing = new Intent();
                sharing.setAction(Intent.ACTION_SEND);
                sharing.putExtra(Intent.EXTRA_TEXT, "https://www.badrolNjsG.com/MovieViewer");
                sharing.setType("text/plain");
                startActivity(sharing);
        }
        return super.onOptionsItemSelected(item);
    }
}
