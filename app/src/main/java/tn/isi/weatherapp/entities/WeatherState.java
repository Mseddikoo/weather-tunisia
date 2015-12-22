package tn.isi.weatherapp.entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Anouar BEN ZAHRA on 27/11/2015.
 */

@Table(name = "WeatherState")
public class WeatherState  extends Model{

    @Column()
    private String villeName;
    @Column()
    private double lat;
    @Column()
    private double lng;
    @Column()
    private double temp;
    @Column()
    private String desc;
    @Column()
    private String icon;

    public WeatherState(){}


    public WeatherState(String villeName, double lat, double lng, double temp, String desc, String icon) {
        this.villeName = villeName;
        this.lat = lat;
        this.lng = lng;
        this.temp = temp;
        this.desc = desc;
        this.icon = icon;
    }


    public String getVilleName() {
        return villeName;
    }

    public void setVilleName(String villeName) {
        this.villeName = villeName;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
