package com.seoul_weather.seoulweather;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
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
    TextView weather_condition, temperature, weather_standard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        gifView = (ImageView)findViewById(R.id.weather_gif);
        weather_condition = (TextView)findViewById(R.id.weather_condition);
        temperature = (TextView)findViewById(R.id.temperature);
        weather_standard = (TextView)findViewById(R.id.weather_standard);

        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(gifView);


        new ReceiveShortWeather().execute();
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

            String url = "http://www.weather.go.kr/wid/queryDFSRSS.jsp?zone=1111053000";

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
            } else if(shortWeathers.get(0).getWfKor().equals("흐림")) {
                Glide.with(getApplicationContext()).load(R.drawable.overclouy).into(gifView);
            } else if((shortWeathers.get(0).getPty() == 1)||(shortWeathers.get(0).getPty() == 2)) {
                Glide.with(getApplicationContext()).load(R.drawable.rainy).into(gifView);
            } else if((shortWeathers.get(0).getPty() == 3)) {
                Glide.with(getApplicationContext()).load(R.drawable.light_snowy).into(gifView);
            } else if((shortWeathers.get(0).getPty() == 4)) {
                Glide.with(getApplicationContext()).load(R.drawable.heavy_snowny).into(gifView);
            }

        }

        void parseXML(String xml) {
            try {
                String tagName = "";
                boolean onHour = false;
                boolean onDay = false;
                boolean onTem = false;
                boolean onWfKor = false;
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
