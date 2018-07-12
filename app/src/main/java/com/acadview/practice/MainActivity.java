package com.acadview.practice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView channelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        channelList = findViewById(R.id.channelList);

        String [] channelName = {"ABC NEWS","TIMES OF INDIA","NATIONAL GEOGRAPHIC"};
        final String [] channelId = {"abc-news","the-times-of-india","national-geographic"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,channelName);
        channelList.setAdapter(adapter);

        channelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chid = channelId[position];

                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("id",chid);
                startActivity(intent);
            }
        });
    }
}
