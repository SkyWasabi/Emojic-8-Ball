package com.example.dakeh.emojic8ball;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ArrayList<QuestionResponseModel> questionResponseModels = (ArrayList<QuestionResponseModel>)bundle.getSerializable("value");

            HistoryArrayAdapter itemsAdapter = new HistoryArrayAdapter(this, questionResponseModels);

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(itemsAdapter);


    }
}
