package com.example.minorius.weather_ghub.adapter;

import android.media.Image;
import android.view.View;
import android.widget.TextView;

import com.example.minorius.weather_ghub.MainActivity;

/**
 * Created by Minorius on 22.11.2015.
 */
public class UpData extends MainActivity {

    String date;
    String temp;
    String p;

//    Image i;
    //String speed;
   // String humidity;

    public UpData(String date, String temp, String p) {
        this.date = date;
        this.temp = temp;
        this.p = p;

//        this.i = i;
       // this.speed = speed;
       // this.humidity = humidity;
    }


//    public Image getI() {
//        return i;
//    }
//
//    public void setI(Image i) {
//        this.i = i;
//    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

//    public String getSpeed() {
//        return speed;
//    }
//
//    public void setSpeed(String speed) {
//        this.speed = speed;
//    }
//
//    public String getHumidity() {
//        return humidity;
//    }
//
//    public void setHumidity(String humidity) {
//        this.humidity = humidity;
//    }
}