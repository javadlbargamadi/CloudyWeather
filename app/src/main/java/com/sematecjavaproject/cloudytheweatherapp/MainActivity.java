package com.sematecjavaproject.cloudytheweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;


import com.sematecjavaproject.cloudytheweatherapp.OpenWeatherClass.OpenWeatherClass;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;


import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    String url;
    String city;
    String weatherType;
    String weatherDescription;
    String weatherIcon;
    Integer minTemp;
    Integer maxTemp;
    Double mainTemp;
    Double windSpeed;
    String iconUrl;

    TextView txtCity;
    TextView txtWeatherType;
    TextView txtWeatherDescription;
    ImageView imgWeatherIcon;
    TextView txtMinTemp;
    TextView txtMaxTemp;
    TextView txtMainTemp;
    TextView txtWindSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this, "open drawer to change city", Toast.LENGTH_SHORT).show();

        txtCity = findViewById(R.id.txtCity);
        txtWeatherType = findViewById(R.id.txtWeatherType);
        txtWeatherDescription = findViewById(R.id.txtWeatherDescription);
        imgWeatherIcon = findViewById(R.id.imgWeatherIcon);
        txtMinTemp = findViewById(R.id.txtMinTemp);
        txtMaxTemp = findViewById(R.id.txtMaxTemp);
        txtMainTemp = findViewById(R.id.txtMainTemp);
        txtWindSpeed = findViewById(R.id.txtWindSpeed);

        url = "https://api.openweathermap.org/data/2.5/weather?q=Tehran&units=metric&APPID=05c1d09d0ef22e9f6ddfbf67d8dcc151";

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                Gson gson = new Gson();

                OpenWeatherClass openWeatherClass = gson.fromJson(response.toString(), OpenWeatherClass.class);

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    String weatherData = jsonObject.getString("weather");
                    JSONArray jsonArray = new JSONArray(weatherData);

                    weatherType = "";
                    weatherDescription = "";
                    weatherIcon = null;
                    iconUrl = null;

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject weatherPart = jsonArray.getJSONObject(i);
                        weatherType = weatherPart.getString("main");
                        weatherDescription = weatherPart.getString("description");
                        weatherIcon = weatherPart.getString("icon");
                        String wIcon = weatherIcon;
                    }

                    txtWeatherType.setText(weatherType);
                    txtWeatherDescription.setText(weatherDescription);
                    iconUrl = "http://openweathermap.org/img/wn/" + weatherIcon + ".png";
                    Picasso.get().load(Uri.parse(iconUrl)).into(imgWeatherIcon);
//                    Glide.with(MainActivity.this).load("url").into(imgWeatherIcon);
                } catch (Exception e) {
                    e.printStackTrace();
                }



                city = openWeatherClass.getName();
                txtCity.setText(city);

                minTemp = openWeatherClass.getMain().getTempMin();
                txtMinTemp.setText(minTemp.toString() + "°C");

                maxTemp = openWeatherClass.getMain().getTempMax();
                txtMaxTemp.setText(maxTemp.toString() + "°C");

                mainTemp = openWeatherClass.getMain().getTemp();
                txtMainTemp.setText(mainTemp.toString() + "°C");

                windSpeed = openWeatherClass.getWind().getSpeed();
                txtWindSpeed.setText(windSpeed.toString() + "m/s");


//                AsyncHttpClient asyncHttpClient1 = new AsyncHttpClient();
//                asyncHttpClient1.get(url,new JsonHttpResponseHandler(){
//
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                        super.onSuccess(statusCode, headers, response);
//                        String weatherType;
//
//                        JSONObject jsonResponse;
//                        try {
//                            ArrayList<String> temp = new ArrayList<String>();
//                            jsonResponse = new JSONObject(response.toString());
//                            JSONArray weatherArray = jsonResponse.getJSONArray("weather");
//                            for(int i=0;i<weatherArray.length();i++){
//                                JSONObject weatherType1 = weatherArray.getJSONObject(i);
//                                JSONArray main = weatherType1.getJSONArray("main");
//                                for(int j=0;j<main.length();j++){
//                                    temp.add(main.getString(j));
//                                }
//                            }
//                            txtWeatherType.setText(temp.toString());
//                        } catch (JSONException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                        super.onFailure(statusCode, headers, throwable, errorResponse);
//                    }
//                });


//                weatherType = weatherClass.getWeather();
//                txtWeatherType.setText((CharSequence) weatherType);
//                Weather weather = gson.fromJson(response.toString(), Weather.class);
//                weatherType = weatherClass.getWeather("main");
//                txtWeatherType.setText(weatherType);


//                weatherList = weatherClass.getName();

//                String city = weatherList.

//                WeatherClass weatherClass = new WeatherClass();
//                city = weatherClass.n
//                city = weatherClass.getName();
//                txtCity.setText(city);
//
//                Weather weather = new Weather();
//                weatherMain = weather.getMain();
//                txtWeatherType.setText("" + weatherMain);
//                weatherDescription = weather.getDescription();
//                weatherIcon = weather.getIcon();
//
//                Main main = new Main();
//                minTemp = main.getTempMin();
//                maxTemp = main.getTempMax();
//                mainTemp = main.getTemp();
//
//                Sys sys = new Sys();
//                surise = sys.getSunrise();
//                sunset = sys.getSunset();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });


        TextView txtChangeCity = findViewById(R.id.txtChangeCity);
        TextView textView = findViewById(R.id.txtTest);
        RecyclerView recyclerView = findViewById(R.id.OtherDaysRecyclerView);

        txtChangeCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent changeCityIntent = new Intent(MainActivity.this, SearchCityActivity.class);
                startActivity(changeCityIntent);
            }
        });

        ItemAdapterActivity itemAdapterActivity = new ItemAdapterActivity();
        recyclerView.setAdapter(itemAdapterActivity);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false));


    }
}