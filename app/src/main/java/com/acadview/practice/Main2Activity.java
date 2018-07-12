package com.acadview.practice;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.networkutil.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    RecyclerView detailList;
    String id ;
    String API_KEY = "e54a4c6cfccd449bb4652893fc8622aa";
    String URL = "https://newsapi.org/v2/top-headlines?sources=";
    String data;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        detailList = findViewById(R.id.detailList);

        detailList.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        adapter = new Adapter(this);
        detailList.setAdapter(adapter);

        new FetchNews().execute();
    }

    class FetchNews extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            String finalUrl = URL+id+"&apiKey=e54a4c6cfccd449bb4652893fc8622aa";

             data = NetworkUtil.makeServiceCall(finalUrl);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ArrayList<News> newsArrayList = new ArrayList<>();

            if(data == null){
                Toast.makeText(Main2Activity.this,"no data returned",Toast.LENGTH_SHORT).show();
            }else{

                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray articleArray = jsonObject.getJSONArray("articles");

                    for(int i=0;i<articleArray.length();i++){
                        JSONObject news = articleArray.getJSONObject(i);
                        String title = news.getString("title");
                        String description = news.getString("description");
                        String imageUrl = news.getString("urlToImage");

                        newsArrayList.add(new News(title,description,imageUrl));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            adapter.swap(newsArrayList);
        }
    }
}
