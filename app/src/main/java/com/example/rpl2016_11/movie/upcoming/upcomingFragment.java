package com.example.rpl2016_11.movie.upcoming;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rpl2016_11.movie.R;
import com.example.rpl2016_11.movie.nowAdapter;
import com.example.rpl2016_11.movie.nowItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class upcomingFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private nowAdapter mNowAdapter;
    private ArrayList<nowItem> mNowList;
    private RequestQueue mRequestQueue;

    public upcomingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_upcoming, container, false);

        mRecyclerView = view.findViewById(R.id.recviewnow);
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mNowList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(getContext());
        parseJSON();


        return view;
    }

    private void parseJSON() {
        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=05d24d1094bc376832434c74ca08824f&language=en-US";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray = response.getJSONArray("results");

                    for (int i = 0; i <jsonArray.length(); i++){
                        JSONObject result = jsonArray.getJSONObject(i);

                        String imageUrl = "http://image.tmdb.org/t/p/w185" +  result.getString("poster_path");
                        String tittle = result.getString("title");
                        String rate = result.getString("vote_average");
                        String language = result.getString("original_language");
                        String description= result.getString("overview");
                        String popularity = result.getString("popularity");
                        String date = result.getString("release_date");

                        mNowList.add(new nowItem(imageUrl, tittle, rate , language, description, popularity, date));

                        Log.d("gendon", "onResponse: "+imageUrl);
                    }

                    mNowAdapter = new nowAdapter(getContext(), mNowList);
                    mRecyclerView.setAdapter(mNowAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);

    }
}
