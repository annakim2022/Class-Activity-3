package com.example.classactivity3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    // define variables
    private Button button_go;
    private SearchView searchView_city;
    private static AsyncHttpClient client = new AsyncHttpClient();
    private ArrayList<String> descriptions;
    private ArrayList<String> dts;
    private ArrayList<String> feelsLikes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // look up button by id
        button_go = findViewById(R.id.button_go);
        searchView_city = findViewById(R.id.searchView_city);
        // add an event listener to listen for the click
        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // handle what happens after I click
                launchNextActivity(v);

            }
        });
    }
    public void launchNextActivity(View view) {
        String city = searchView_city.getQuery().toString();
        String api_url = "http://api.openweathermap.org/data/2.5/forecast/?q=" + city + "&cnt=21&appid=997475d54886c4fb4db2803703fb4007&units=imperial";
        client.get(api_url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("api response", new String(responseBody));

                try {

                    JSONObject json = new JSONObject(new String(responseBody));
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                    /*
                    JSONObject sys = json.getJSONObject("sys");
                    String country = sys.getString("country");
                    String location = city + ", " + country;
                    intent.putExtra("location", location);
                     */

                    intent.putExtra("location", city);

                    JSONArray list = json.getJSONArray("list");
                    descriptions = new ArrayList<>();
                    feelsLikes = new ArrayList<>();
                    dts = new ArrayList<>();

                    for(int i = 0; i < list.length(); i++) {
                        JSONObject object = list.getJSONObject(i);

                        JSONArray weather = object.getJSONArray("weather");
                        JSONObject description = weather.getJSONObject(0);
                        descriptions.add(description.getString("description"));

                        JSONObject feels_like = object.getJSONObject("main");
                        feelsLikes.add(feels_like.getString("feels_like"));

                        dts.add(object.getString("dt_txt"));
                    }

                    intent.putExtra("descriptions", descriptions);
                    intent.putExtra("feelsLikes", feelsLikes);
                    intent.putExtra("dts", dts);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("api error", new String(responseBody));
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });


    }


}