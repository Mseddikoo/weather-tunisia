package tn.isi.weatherapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import tn.isi.weatherapp.R;
import tn.isi.weatherapp.entities.Ville;
import tn.isi.weatherapp.entities.WeatherState;

public class StateAdapter extends BaseAdapter {

    List<WeatherState> states;
    Activity context;
    private LayoutInflater inflater = null;
    /**
     * Constructor
     * c: context
     * depense: list of depenses
     **/
    public StateAdapter(Activity c, List<WeatherState> states){
        context = c;
        this.states = states;

        /*
        Il faut récupérer l'inflater du contexte (Activity mère)
        pour pouvoir injecter
        des elements dans l'interface graphique
        ==> Un adapter n'a pas d'inflater !
        */
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DisplayImageOptions defaultOptions =
                new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .showImageOnFail(R.drawable.logo_weather)
                        .showImageOnLoading(R.drawable.logo_weather)
                        .build();
        ImageLoaderConfiguration config =
                new ImageLoaderConfiguration.Builder(context)
                        .defaultDisplayImageOptions(defaultOptions).build();
        ImageLoader.getInstance().init(config);

    }

    @Override
    public int getCount() {
        return states.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        @Bind(R.id.image_state)
        ImageView img;
        @Bind(R.id.tv_desc)
        TextView  tvDesc;
        @Bind(R.id.tv_temp)
        TextView tvTemp;

        public Holder(View view){
            ButterKnife.bind(this,view);
        }
    }

    /*
    position est "final" car il ne doit pas etre modifié lors du
    traitement d'une row.
    */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.item_state, null);
        final Holder holder = new Holder(rowView);

        WeatherState state = states.get(position);

        String urlImg
                ="http://openweathermap.org/img/w/"+state.getIcon()+".png";

        ImageLoader.getInstance()
                .displayImage(urlImg,holder.img);

        holder.tvDesc.setText(state.getVilleName()+" - "+state.getDesc());
        holder.tvTemp.setText(state.getTemp()+"°F");

        return rowView;
    }


}

