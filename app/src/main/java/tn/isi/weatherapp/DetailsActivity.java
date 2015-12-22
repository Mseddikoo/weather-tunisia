package tn.isi.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import tn.isi.weatherapp.entities.Ville;
import tn.isi.weatherapp.entities.WeatherState;

public class DetailsActivity extends ActionBarActivity {

    @Bind(R.id.img_weather)
    ImageView imgWeather;

    @Bind(R.id.tv_desc)
    TextView tvDesc;

    @Bind(R.id.tv_temp)
    TextView tvTemp;

    @Bind(R.id.btn_save)
    Button btnSave;

    @Bind(R.id.btn_map)
    Button btnMap;

    Ville ville;

    String temp,desc,icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        ville = (Ville) getIntent().getExtras()
                .getSerializable("obj");

        DisplayImageOptions defaultOptions =
                new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnFail(R.drawable.logo_weather)
                .showImageOnLoading(R.drawable.logo_weather)
                .build();
        ImageLoaderConfiguration config =
                new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(defaultOptions).build();
        ImageLoader.getInstance().init(config);

        getWeather();

        btnSave.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherState state
                        =new WeatherState(
                        ville.getName(),
                        ville.getLat(),
                        ville.getLng(),
                        Double.parseDouble(temp),
                        desc,
                        icon
                );

                state.save();
            }
        });
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent
                        = new Intent(DetailsActivity.this
                ,MapsActivity.class);

                intent.putExtra("lat",ville.getLat());
                intent.putExtra("lng",ville.getLng());
                startActivity(intent);
            }
        });
    }

    public void getWeather() {
        String url =
                "http://api.openweathermap.org/data" +
                        "/2.5/weather?lat=" + ville.getLat() +
                        "&lon=" + ville.getLng() +
                        "&APPID=b92bff27c4f161d8b90e9933ba891f38";

        Response.Listener response
                =new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                System.out.println(response.toString());
                JSONArray json = response.optJSONArray("weather");
                JSONObject jsonMain = response.optJSONObject("main");
                desc = json.optJSONObject(0).optString("main");
                icon = json.optJSONObject(0).optString("icon");
                temp = jsonMain.optString("temp");

                String urlImg
                        ="http://openweathermap.org/img/w/"+icon+".png";

                ImageLoader.getInstance()
                .displayImage(urlImg, imgWeather);

                tvDesc.setText(desc);
                tvTemp.setText(temp + "°F");
            }
        };


        Response.ErrorListener error = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };

        JsonObjectRequest request
                = new JsonObjectRequest
                (Request.Method.GET, url, response,error );


        Volley.newRequestQueue(this).add(request);
    }

}
