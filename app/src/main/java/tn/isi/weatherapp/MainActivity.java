package tn.isi.weatherapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tn.isi.weatherapp.adapters.VilleAdapter;
import tn.isi.weatherapp.entities.Ville;

public class MainActivity extends ActionBarActivity {

    @Bind(R.id.list_ville)
    ListView listVille;

    @Bind(R.id.btn_history)
    Button btnHistory;

    List<Ville> villes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        villes = new ArrayList<>();
        //tunis
        villes.add(new Ville("Tunis",
                34.00, 9.00,R.drawable.img_tunis
        ));
        //sfax
        villes.add(new Ville("Sfax",
                34.7333, 10.76,R.drawable.img_sfax
        ));
        //sousse
        villes.add(new Ville("Sousse",
                35.8333, 10.6333, R.drawable.img_sousse
        ));

        VilleAdapter adapter = new VilleAdapter(this,villes);
        listVille.setAdapter(adapter);
        listVille.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ville ville = villes.get(position);
                Intent intent
                        = new Intent(MainActivity.this
                        , DetailsActivity.class);
                intent.putExtra("obj", ville);
                startActivity(intent);
            }
        });

        btnHistory.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this
                ,HistoryActivity.class);
                startActivity(intent);
            }
        });

    }
}
