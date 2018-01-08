package com.music_player.musicplayer;


import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageButton btn_start;
    private SeekBar music_progress;
    private MediaPlayer music;
    private TextView time_current;
    private boolean btn_start_boolean;
    int sec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = (ImageButton)findViewById(R.id.btn_start);
        time_current = (TextView)findViewById(R.id.time_current);
        btn_start_boolean = false;

        music = MediaPlayer.create(this, R.raw.decalcomanie);
        music.setLooping(true);

        music_progress = (SeekBar)findViewById(R.id.music_progress);

        music_progress.setThumb(null);
        music_progress.setMax(music.getDuration());

        music_progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser)
                    music.seekTo(progress);

                sec = music.getCurrentPosition()/1000;
                time_current.setText((sec/60)+":"+(sec%60)+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btn_start_boolean =! btn_start_boolean;
                if(btn_start_boolean == true) {
                    btn_start.setImageResource(R.drawable.btn_pause);

                    music.start();

                    Thread();

                } else {
                    btn_start.setImageResource(R.drawable.btn_start);

                    music.stop();
                    try{
                        music.prepare();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    music.seekTo(0);
                }
            }
        });

    }

    public void Thread(){

        Runnable task = new Runnable(){

            public void run(){

                /**

                 * while문을 돌려서 음악이 실행중일때 게속 돌아가게 합니다

                 */

                while(music.isPlaying()){

                    try {

                        Thread.sleep(1000);

                    } catch (InterruptedException e) {

                        // TODO Auto-generated catch block

                        e.printStackTrace();

                    }

                    /**

                     * music.getCurrentPosition()은 현재 음악 재생 위치를 가져오는 구문 입니다

                     */

                    music_progress.setProgress(music.getCurrentPosition());

                }

            }

        };

        Thread thread = new Thread(task);

        thread.start();

    }

}
