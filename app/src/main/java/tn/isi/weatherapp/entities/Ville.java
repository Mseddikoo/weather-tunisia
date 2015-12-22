package tn.isi.weatherapp.entities;

import java.io.Serializable;

/**
 * Created by Radhwen on 24/11/2015.
 */
public class Ville implements Serializable{

    private String name;
    private double lat;
    private double lng;
    private int imgRes;



    public Ville(String name, double lat, double lng, int imgRes) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.imgRes = imgRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }
}
