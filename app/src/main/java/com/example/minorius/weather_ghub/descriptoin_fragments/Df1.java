package com.example.minorius.weather_ghub.descriptoin_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.minorius.weather_ghub.R;
import com.example.minorius.weather_ghub.adapter.UpData;
import com.example.minorius.weather_ghub.adapter.WorkingAdapter;

public class Df1 extends Fragment {

    WorkingAdapter wa;

    TextView d1txt1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.d1, container, false);
    }
}
