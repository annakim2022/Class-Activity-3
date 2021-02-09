package com.example.classactivity3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private TextView textView_noCity;
    private TextView textView_location;
    private RecyclerView recyclerView;
    private ArrayList<Weather> weathers;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recyclerView = findViewById(R.id.recyclerView_weather);
        weathers = new ArrayList<>();
        Intent intent = getIntent();
        //Log.d("location", intent.getStringExtra("location"));

        if (intent.getStringExtra("location")==null) {
            textView_noCity = findViewById(R.id.textView_noCity);
            textView_noCity.setText("No city found.");
            findViewById(R.id.textView_loc).setVisibility(View.INVISIBLE);
            findViewById(R.id.recyclerView_weather).setVisibility(View.INVISIBLE);

        }

        else {

            textView_location = findViewById(R.id.textView_loc);
            textView_location.setText(intent.getStringExtra("location"));

            ArrayList<String> descriptions = intent.getStringArrayListExtra("descriptions");
            ArrayList<String> feelsLikes = intent.getStringArrayListExtra("feelsLikes");
            ArrayList<String> dts = intent.getStringArrayListExtra("dts");

            for (int i = 0; i < descriptions.size(); i++) {

                Weather weather = new Weather(dts.get(i),
                        descriptions.get(i),
                        feelsLikes.get(i));

                weathers.add(weather);
            }


        }

        WeatherAdapter adapter = new WeatherAdapter(weathers);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


}
