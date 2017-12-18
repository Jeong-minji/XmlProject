package oom.music_selector.music_selector;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import java.util.ArrayList;

import oom.music_selector.musicselector.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private HorizontalScrollView hScrollView1, hScrollView2, hScrollView3, hScrollView4, hScrollView5;
    private String btn_click_text_color = "#FFFFFF";
    private ArrayList<String> clikedBtnList;
    private Button[] btnArray1, btnArray2, btnArray3, btnArray4, btnArray5;
    public static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clikedBtnList = new ArrayList<>();

        hScrollView1 = (HorizontalScrollView)findViewById(R.id.hscrollview1);
        hScrollView1.post(new Runnable() {                        // Thread
            @Override
            public void run() {
                // TODO Auto-generated method stub
                hScrollView1.scrollTo(100, 0);
            }
        });

        hScrollView2 = (HorizontalScrollView)findViewById(R.id.hscrollview2);
        hScrollView2.post(new Runnable() {                        // Thread
            @Override
            public  void run() {
                // TODO Auto-generated method stub
                hScrollView2.scrollTo(120, 0);
            }
        });

        hScrollView3 = (HorizontalScrollView)findViewById(R.id.hscrollview3);
        hScrollView3.post(new Runnable() {                        // Thread
            @Override
            public void run() {
                // TODO Auto-generated method stub
                hScrollView3.scrollTo(230, 0);
            }
        });

        hScrollView4 = (HorizontalScrollView)findViewById(R.id.hscrollview4);
        hScrollView4.post(new Runnable() {                        // Thread
            @Override
            public void run() {
                // TODO Auto-generated method stub
                hScrollView4.scrollTo(280, 0);
            }
        });

        hScrollView5 = (HorizontalScrollView)findViewById(R.id.hscrollview5);
        hScrollView5.post(new Runnable() {                        // Thread
            @Override
            public void run() {
                // TODO Auto-generated method stub
                hScrollView5.scrollTo(170, 0);
            }
        });

        btnArray1 = new Button[4];
        int startbtnArray = R.id.btn_select1;
        for(int i = 0; i < 4; i++) {
            btnArray1[i] = (Button) findViewById(startbtnArray);
            btnArray1[i].setOnClickListener(this);
            startbtnArray++;
        }

        btnArray2 = new Button[4];
        startbtnArray = R.id.btn_select5;
        for(int i = 0; i < 4; i++) {
            btnArray2[i] = (Button) findViewById(startbtnArray);
            btnArray2[i].setOnClickListener(this);
            startbtnArray++;
        }

        btnArray3 = new Button[4];
        startbtnArray = R.id.btn_select9;
        for(int i = 0; i < 4; i++) {
            btnArray3[i] = (Button) findViewById(startbtnArray);
            btnArray3[i].setOnClickListener(this);
            startbtnArray++;
        }

        btnArray4 = new Button[4];
        startbtnArray = R.id.btn_select13;
        for(int i = 0; i < 4; i++) {
            btnArray4[i] = (Button) findViewById(startbtnArray);
            btnArray4[i].setOnClickListener(this);
            startbtnArray++;
        }

        btnArray5 = new Button[4];
        startbtnArray = R.id.btn_select17;
        for(int i = 0; i < 4; i++) {
            btnArray5[i] = (Button) findViewById(startbtnArray);
            btnArray5[i].setOnClickListener(this);
            startbtnArray++;
        }

    }

    @Override
    public void onClick(View view) {
        count++;

        int intID = view.getId();
        Button button = (Button) findViewById(intID);
        String message = button.getText().toString();

        clikedBtnList.add(message);

        if(count == 1) {
            String value = clikedBtnList.get(0);
            for(int i = 0; i < 4; i++) {
                if(value == btnArray1[i].getText()) {
                    btnArray1[i].setBackgroundResource(R.drawable.btn_select_background_gra1);
                    btnArray1[i].setTextColor(Color.parseColor(btn_click_text_color));
                } else if(value == btnArray2[i].getText()) {
                    btnArray2[i].setBackgroundResource(R.drawable.btn_select_background_gra1);
                    btnArray2[i].setTextColor(Color.parseColor(btn_click_text_color));
                } else if(value == btnArray3[i].getText()) {
                    btnArray3[i].setBackgroundResource(R.drawable.btn_select_background_gra1);
                    btnArray3[i].setTextColor(Color.parseColor(btn_click_text_color));
                } else if(value == btnArray4[i].getText()) {
                    btnArray4[i].setBackgroundResource(R.drawable.btn_select_background_gra1);
                    btnArray4[i].setTextColor(Color.parseColor(btn_click_text_color));
                } else if(value == btnArray5[i].getText()) {
                    btnArray5[i].setBackgroundResource(R.drawable.btn_select_background_gra1);
                    btnArray5[i].setTextColor(Color.parseColor(btn_click_text_color));
                }
            }
        } else if(count == 2) {
            String value = clikedBtnList.get(1);
            for(int i = 0; i < 4; i++) {
                if(value == btnArray1[i].getText()) {
                    btnArray1[i].setBackgroundResource(R.drawable.btn_select_background_gra2);
                    btnArray1[i].setTextColor(Color.parseColor(btn_click_text_color));
                } else if(value == btnArray2[i].getText()) {
                    btnArray2[i].setBackgroundResource(R.drawable.btn_select_background_gra2);
                    btnArray2[i].setTextColor(Color.parseColor(btn_click_text_color));
                } else if(value == btnArray3[i].getText()) {
                    btnArray3[i].setBackgroundResource(R.drawable.btn_select_background_gra2);
                    btnArray3[i].setTextColor(Color.parseColor(btn_click_text_color));
                } else if(value == btnArray4[i].getText()) {
                    btnArray4[i].setBackgroundResource(R.drawable.btn_select_background_gra2);
                    btnArray4[i].setTextColor(Color.parseColor(btn_click_text_color));
                } else if(value == btnArray5[i].getText()) {
                    btnArray5[i].setBackgroundResource(R.drawable.btn_select_background_gra2);
                    btnArray5[i].setTextColor(Color.parseColor(btn_click_text_color));
                }
            }
        } else if(count == 3) {
            String value = clikedBtnList.get(2);
            for(int i = 0; i < 4; i++) {
                if(value == btnArray1[i].getText()) {
                    btnArray1[i].setBackgroundResource(R.drawable.btn_select_background_gra3);
                    btnArray1[i].setTextColor(Color.parseColor(btn_click_text_color));
                } else if(value == btnArray2[i].getText()) {
                    btnArray2[i].setBackgroundResource(R.drawable.btn_select_background_gra3);
                    btnArray2[i].setTextColor(Color.parseColor(btn_click_text_color));
                } else if(value == btnArray3[i].getText()) {
                    btnArray3[i].setBackgroundResource(R.drawable.btn_select_background_gra3);
                    btnArray3[i].setTextColor(Color.parseColor(btn_click_text_color));
                } else if(value == btnArray4[i].getText()) {
                    btnArray4[i].setBackgroundResource(R.drawable.btn_select_background_gra3);
                    btnArray4[i].setTextColor(Color.parseColor(btn_click_text_color));
                } else if(value == btnArray5[i].getText()) {
                    btnArray5[i].setBackgroundResource(R.drawable.btn_select_background_gra3);
                    btnArray5[i].setTextColor(Color.parseColor(btn_click_text_color));
                }
            }
        } else {
            String value = clikedBtnList.get(3);
            for(int i = 0; i < 4; i++) {
                if(value == btnArray1[i].getText()) {
                    btnArray1[i].setBackgroundResource(R.drawable.btn_select_background_gra4);
                    btnArray1[i].setTextColor(Color.parseColor(btn_click_text_color));
                } else if(value == btnArray2[i].getText()) {
                    btnArray2[i].setBackgroundResource(R.drawable.btn_select_background_gra4);
                    btnArray2[i].setTextColor(Color.parseColor(btn_click_text_color));
                } else if(value == btnArray3[i].getText()) {
                    btnArray3[i].setBackgroundResource(R.drawable.btn_select_background_gra4);
                    btnArray3[i].setTextColor(Color.parseColor(btn_click_text_color));
                } else if(value == btnArray4[i].getText()) {
                    btnArray4[i].setBackgroundResource(R.drawable.btn_select_background_gra4);
                    btnArray4[i].setTextColor(Color.parseColor(btn_click_text_color));
                } else if(value == btnArray5[i].getText()) {
                    btnArray5[i].setBackgroundResource(R.drawable.btn_select_background_gra4);
                    btnArray5[i].setTextColor(Color.parseColor(btn_click_text_color));
                }
            }
        }

    }
}
