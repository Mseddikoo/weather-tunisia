package tn.isi.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.activeandroid.query.Select;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tn.isi.weatherapp.adapters.StateAdapter;
import tn.isi.weatherapp.entities.WeatherState;

public class HistoryActivity extends AppCompatActivity {

    @Bind(R.id.list_state)
    ListView listState;

    List<WeatherState> states;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        states = new Select().from(WeatherState.class)
                .execute();

        StateAdapter adapter =
                new StateAdapter(this,states);
        listState.setAdapter(adapter);
        listState.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                WeatherState state= states.get(position);
                state.delete();
                states = new Select().from(WeatherState.class)
                        .execute();

                StateAdapter adapter =
                        new StateAdapter(HistoryActivity.this,states);
                listState.setAdapter(adapter);
                return false;
            }
        });




        //https://goo.gl/rFIq4T




        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }
}
