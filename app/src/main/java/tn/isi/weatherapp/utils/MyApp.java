package tn.isi.weatherapp.utils;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by MoeZ seddik .
 */
public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
