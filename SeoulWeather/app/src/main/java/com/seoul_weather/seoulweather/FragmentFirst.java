package com.seoul_weather.seoulweather;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by Jeong Minji on 2018-01-14.
 */

public class FragmentFirst extends Fragment {

    TextView s_weather_condition, s_temperature, s_pop, s_reh, s_ws;


    public FragmentFirst()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_first, container, false);

        s_weather_condition = (TextView)layout.findViewById(R.id.s_weather_condition);
        s_temperature = (TextView)layout.findViewById(R.id.s_temperature);
        s_pop = (TextView)layout.findViewById(R.id.s_pop);
        s_reh = (TextView)layout.findViewById(R.id.s_reh);
        s_ws = (TextView)layout.findViewById(R.id.s_ws);

        return layout;
    }
}
