package com.example.minorius.weather_ghub;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Entity;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Image;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minorius.weather_ghub.adapter.UpData;
import com.example.minorius.weather_ghub.adapter.WorkingAdapter;
import com.example.minorius.weather_ghub.descriptoin_fragments.Df1;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private WorkingAdapter adapter;
    public ArrayList<UpData> fetch = new ArrayList<UpData>();
    private ListView lv;

    private GetData gd;

    Df1 df1;

    FragmentManager fm;

    public TextView d1txt1;
    public TextView d1txt2;
    public TextView d1txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetData gd = new GetData();
        gd.execute();

        df1 = new Df1();

        d1txt1 = (TextView) findViewById(R.id.d1txt1);
        d1txt2 = (TextView) findViewById(R.id.d1txt2);
        d1txt3 = (TextView) findViewById(R.id.d1txt3);

        fm = getFragmentManager();

        final ListView mainList = (ListView) findViewById(R.id.mainList);
        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction fm = getFragmentManager().beginTransaction();
                switch (position) {
                    case 0:
                        Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_SHORT).show();
                        fm.replace(R.id.buffer, df1).commit();
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_SHORT).show();
//                        fm.replace(R.id.buffer, p1).commit();
                        break;
                    case 2:
//                        fm.replace(R.id.buffer, p2).commit();
                        break;
                    case 3:
//                        fm.replace(R.id.buffer, p3).commit();
                        break;
                    case 4:
//                        fm.replace(R.id.buffer, p4).commit();
                        break;
                    case 5:
//                        fm.replace(R.id.buffer, p5).commit();
                        break;
                }
            }
        });

        int screenOrientation = getResources().getConfiguration().orientation;

        if (screenOrientation == Configuration.ORIENTATION_PORTRAIT) {
            hideSidePanel();
            mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    FragmentTransaction transaction = fm.beginTransaction();
                    switch (position) {
                        case 0:
                            Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_SHORT).show();
                            mainList.setVisibility(View.GONE);
                            transaction.replace(R.id.buffer2, df1);
                            break;
                        case 1:
                            Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_SHORT).show();
//                            mainList.setVisibility(View.GONE);
//                            transaction.replace(R.id.buffer2, p1);
                            break;
                        case 2:
//                            mainList.setVisibility(View.GONE);
//                            transaction.replace(R.id.buffer2, p2);
                            break;
                        case 3:
//                            mainList.setVisibility(View.GONE);
//                            transaction.replace(R.id.buffer2, p3);
                            break;
                        case 4:
//                            mainList.setVisibility(View.GONE);
//                            transaction.replace(R.id.buffer2, p4);
                            break;
                        case 5:
//                            mainList.setVisibility(View.GONE);
//                            transaction.replace(R.id.buffer2, p5);
                            break;
                    }
                    transaction.addToBackStack(null).commit();;
                }
            });
        }
    }

    private void hideSidePanel() {
        View dataconteiner = findViewById(R.id.dataconteiner);
        if (dataconteiner.getVisibility() == View.VISIBLE) {
            dataconteiner.setVisibility(View.GONE);
        }
    }


   class GetData extends AsyncTask<Void, Void, String> {

       HttpURLConnection urlConnection = null;
       BufferedReader reader = null;
       String result = "";

       @Override
       protected String doInBackground(Void... params) {

           JSONObject dataJsonObj = null;
           String url_for_img = "http://openweathermap.org/img/w/";

           try {

               URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?id=710791&appid=2de143494c0b295cca9337e1e96b00e0");
               urlConnection = (HttpURLConnection) url.openConnection();
               urlConnection.setRequestMethod("GET");
               urlConnection.connect();

               InputStream inputStream = urlConnection.getInputStream();
               StringBuffer buffer = new StringBuffer();

               reader = new BufferedReader(new InputStreamReader(inputStream));

               String line;
               while ((line = reader.readLine()) != null) {
                   buffer.append(line);
               }

               result = buffer.toString();

           } catch (Exception e) {
               e.printStackTrace();
           }


           return result;
       }

       @Override
       protected void onPostExecute(String result) {
           super.onPostExecute(result);

           JSONObject dataJsonObj = null;
           String url_for_img = "http://openweathermap.org/img/w/";

           lv = (ListView) findViewById(R.id.mainList);


           adapter = new WorkingAdapter(MainActivity.this, R.id.mainList, fetch);
           lv.setAdapter(adapter);

           try {
               dataJsonObj = new JSONObject((String) result);
               JSONArray list = dataJsonObj.getJSONArray("list");
               for (int i = 0; i < 5; i++) {
                   JSONObject element = list.getJSONObject(i);
                   JSONObject main = element.getJSONObject("main");
                   JSONObject wind = element.getJSONObject("wind");

                   JSONArray weather = element.getJSONArray("weather");
                   String p = weather.getJSONObject(0).getString("main");
                   String icon = weather.getJSONObject(0).getString("icon");

                   String date = element.getString("dt_txt");

                   int humidity = main.getInt("humidity");
                   double temp_min = main.getDouble("temp_min");
                   double temp_min_in_c = temp_min - 273.15;
                   double temp_max = main.getDouble("temp_max");
                   double temp_max_in_c = temp_max - 273.15;
                   int temp = main.getInt("temp");
                   int temp_in_c = temp - 273;


                   double speed = wind.getDouble("speed");

                  // String url_for_img1 = url_for_img + icon + ".png";
                  // Picasso.with(getApplicationContext()).load(url_for_img1);

                   UpData a = new UpData(""+date,""+temp_in_c, p);
                   fetch.add(a);
               }


           } catch (Exception e) {
               Toast.makeText(getApplicationContext(), "error " + e, Toast.LENGTH_LONG).show();
           }

       }
   }
}

