package com.seoul_weather.seoulweather;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.tsengvn.typekit.Typekit;
import com.tsengvn.typekit.TypekitContextWrapper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ImageView gifView;
    TextView weather_condition, location, temperature, weather_standard, celsius_symbol;
    ViewPager viewPager;
    Button indi_one, indi_two, indi_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        gifView = (ImageView)findViewById(R.id.weather_gif);
        weather_condition = (TextView)findViewById(R.id.weather_condition);
        location = (TextView)findViewById(R.id.location);
        temperature = (TextView)findViewById(R.id.temperature);
        weather_standard = (TextView)findViewById(R.id.weather_standard);
        celsius_symbol = (TextView)findViewById(R.id.celsious_symbol);
        indi_one = (Button)findViewById(R.id.indi_one);
        indi_two = (Button)findViewById(R.id.indi_two);
        indi_three = (Button)findViewById(R.id.indi_three);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(gifView);

        new ReceiveShortWeather().execute();

        viewPager.setAdapter(new pageAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(0);
    }

    View.OnClickListener movePageListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            int tag = (int)view.getTag();
            viewPager.setCurrentItem(tag);
        }
    };

    private class pageAdapter extends FragmentStatePagerAdapter
    {
        public pageAdapter(android.support.v4.app.FragmentManager fragmentManager)
        {
            super(fragmentManager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch(position)
            {
                case 0:
                    return new FragmentFirst();
                case 1:
                    return new FragmentSecond();
                case 2:
                    return new FragmentThird();
                default:
                    return null;
            }
        }

        @Override
        public int getCount()
        {
            return 3;
        }
    }

    public String getDateString()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA);
        String str_date = df.format(new Date());

        return str_date;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    public class ReceiveShortWeather extends AsyncTask<URL, Integer, Long> {

        ArrayList<ShortWeather> shortWeathers = new ArrayList<ShortWeather>();

        protected Long doInBackground(URL... urls) {

            String url = "http://www.weather.go.kr/wid/queryDFSRSS.jsp?zone=1168052100";

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = null;

            try {
                response = client.newCall(request).execute();
                parseXML(response.body().string());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(Long result) {
            String data = "";

            for(int i=0; i<shortWeathers.size(); i++) {
                data += shortWeathers.get(i).getHour() + "(시간) " +
                        shortWeathers.get(i).getDay() + "(날짜) " +
                        shortWeathers.get(i).getTemp() + "(온도) " +
                        shortWeathers.get(i).getWfKor() + "(날씨) " +
                        shortWeathers.get(i).getWs() + "(풍속) " +
                        shortWeathers.get(i).getPty()+"(강수코드)"+
                        shortWeathers.get(i).getPop() + "(강수량)"+"\n";
            }

            weather_condition.setText(shortWeathers.get(0).getWfKor());
            temperature.setText(shortWeathers.get(0).getTemp());
            weather_standard.setText(getDateString()+" "+(Integer.parseInt(shortWeathers.get(0).getHour())-3)+"시 기준");

            if(shortWeathers.get(0).getWfKor().equals("구름 많음")) {
                Glide.with(getApplicationContext()).load(R.drawable.heavy_cloudy).into(gifView);
            } else if(shortWeathers.get(0).getWfKor().equals("구름 조금")) {
                Glide.with(getApplicationContext()).load(R.drawable.light_cloudy).into(gifView);
            } else if(shortWeathers.get(0).getWfKor().equals("맑음")) {
                Glide.with(getApplicationContext()).load(R.drawable.sunny).into(gifView);
                location.setTextColor(Color.parseColor("#629e72"));
                weather_condition.setTextColor(Color.parseColor("#629e72"));
                temperature.setTextColor(Color.parseColor("#629e72"));
                weather_standard.setTextColor(Color.parseColor("#629e72"));
                celsius_symbol.setTextColor(Color.parseColor("#629e72"));
            } else if(shortWeathers.get(0).getWfKor().equals("흐림")) {
                Glide.with(getApplicationContext()).load(R.drawable.overclouy).into(gifView);
            } else if((shortWeathers.get(0).getPty().equals("1"))||(shortWeathers.get(0).getPty().equals("2"))) {
                Glide.with(getApplicationContext()).load(R.drawable.rainy).into(gifView);
            } else if((shortWeathers.get(0).getPty().equals("3"))) {
                Glide.with(getApplicationContext()).load(R.drawable.light_snowy).into(gifView);
                location.setTextColor(Color.parseColor("#729faf"));
                weather_condition.setTextColor(Color.parseColor("#729faf"));
                temperature.setTextColor(Color.parseColor("#729faf"));
                weather_standard.setTextColor(Color.parseColor("#729faf"));
                celsius_symbol.setTextColor(Color.parseColor("#729faf"));
            } else if((shortWeathers.get(0).getPty().equals("4"))) {
                Glide.with(getApplicationContext()).load(R.drawable.heavy_snowny).into(gifView);
            }

        }

        void parseXML(String xml) {
            try {
                String tagName = "";
                boolean onCategory = false;
                boolean onHour = false;
                boolean onDay = false;
                boolean onTem = false;
                boolean onWfKor = false;
                boolean onPty = false;
                boolean onPop = false;
                boolean onEnd = false;
                boolean isItemTag1 = false;
                int i = 0;

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();

                parser.setInput(new StringReader(xml));

                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        tagName = parser.getName();
                        if (tagName.equals("data")) {
                            shortWeathers.add(new ShortWeather());
                            onEnd = false;
                            isItemTag1 = true;
                        }
                    } else if (eventType == XmlPullParser.TEXT && isItemTag1) {
                        if (tagName.equals("category") && !onCategory) {
                            shortWeathers.get(i).setCategory(parser.getText());
                            onCategory = true;
                        }
                        if (tagName.equals("hour") && !onHour) {
                            shortWeathers.get(i).setHour(parser.getText());
                            onHour = true;
                        }
                        if (tagName.equals("day") && !onDay) {
                            shortWeathers.get(i).setDay(parser.getText());
                            onDay = true;
                        }
                        if (tagName.equals("temp") && !onTem) {
                            shortWeathers.get(i).setTemp(parser.getText());
                            onTem = true;
                        }
                        if (tagName.equals("wfKor") && !onWfKor) {
                            shortWeathers.get(i).setWfKor(parser.getText());
                            onWfKor = true;
                        }
                        if (tagName.equals("pty") && !onPty) {
                            shortWeathers.get(i).setPty(parser.getText());
                            onPty = true;
                        }
                        if (tagName.equals("pop") && !onPop) {
                            shortWeathers.get(i).setPop(parser.getText());
                            onPop = true;
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        if (tagName.equals("s06") && onEnd == false) {
                            i++;
                            onHour = false;
                            onDay = false;
                            onTem = false;
                            onWfKor = false;
                            onPop = false;
                            isItemTag1 = false;
                            onEnd = true;
                        }
                    }

                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
