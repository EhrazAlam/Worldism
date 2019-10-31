package com.example.worldism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FinalActivity extends AppCompatActivity {
    int position;
    Data finalData;
    ArrayList<String> currencyList;
    ArrayList<String> languageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        Intent intent = getIntent();
        if (intent != null) {

            finalData = (Data) intent.getSerializableExtra("data");
            position = intent.getIntExtra("position", 0);

           /* ImageView myImage = findViewById(R.id.imageView);
            Glide.with(this).load(finalData.getCimage()).into(myImage);*/

            TextView myCountry = findViewById(R.id.text);
            myCountry.setText(finalData.getCname());

            TextView myArea = findViewById(R.id.text01);
            myArea.setText(finalData.getCarea());

            TextView myCapital = findViewById(R.id.text02);
            myCapital.setText(finalData.getCcapital());

            TextView myCurrency = findViewById(R.id.text03);

            currencyList = finalData.getCcurrency();
            myCurrency.setText(currencyList.get(0) + "");

            languageList = finalData.getClanguage();
            TextView myLanguage = findViewById(R.id.text04);
            myLanguage.setText(languageList.get(0) + "");

            TextView myContinent = findViewById(R.id.text05);
            myContinent.setText(finalData.getCcontinent());

            TextView myPopulation = findViewById(R.id.text06);
            myPopulation.setText(finalData.getCpopulation());

            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}