package tn.isi.weatherapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class SplashActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //getSupportActionBar().hide();  //pour supprimer raction bar

        //pour fair le chargement
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(
                        new Intent(
                                SplashActivity.this,
                                MainActivity.class
                        )
                );
            }
        },3000);


    }
}
