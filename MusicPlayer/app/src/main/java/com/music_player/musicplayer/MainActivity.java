package com.music_player.musicplayer;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageButton btn_start;
    private ImageView volume_down;
    private SeekBar music_progress, volume_controller;
    private MediaPlayer mediaPlayer;
    private TextView time_current;
    boolean btn_start_boolean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = (ImageButton)findViewById(R.id.btn_start);
        volume_down = (ImageView)findViewById(R.id.volume_down);
        time_current = (TextView)findViewById(R.id.time_current);
        music_progress = (SeekBar)findViewById(R.id.music_progress);
        volume_controller = (SeekBar)findViewById(R.id.volume_controller);

        btn_start_boolean = false;

        /* 음악 파일 재생 및 시크바 구현 */
        mediaPlayer = MediaPlayer.create(this, R.raw.decalcomanie);           // raw폴더에 있는 노래 제목을 받아옴
        mediaPlayer.setLooping(true);

        music_progress.setThumb(null);
        music_progress.setMax(mediaPlayer.getDuration());         // getDuration은 음악의 전체 길이를 받아옴
        music_progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser)
                    mediaPlayer.seekTo(progress);               // user가 터치한 부분으로 프로그래스바와 음악을 진행

                int time, min, sec;
                time = mediaPlayer.getCurrentPosition()/1000;
                min = time/60;
                sec = time%60;
                if(sec<10) {
                    time_current.setText(min+":"+"0"+sec+"");
                } else {
                    time_current.setText(min+":"+sec+"");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        /* 재생/일시정지 버튼 구현 */
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_start_boolean =! btn_start_boolean;
                if(btn_start_boolean == true) {
                    btn_start.setImageResource(R.drawable.btn_pause);

                    mediaPlayer.start();
                    Thread();
                } else {
                    btn_start.setImageResource(R.drawable.btn_start);

                    mediaPlayer.pause();
                    try{
                        mediaPlayer.prepare();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    mediaPlayer.seekTo(0);                            // mediaPlayer.stop()을 했을 때 시크바가 0위치로
                }
            }
        });

        /* 볼륨조절 구현 */
        volume_controller.setThumb(null);

        final AudioManager audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        int vMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volume_controller.setMax(vMax);
        volume_controller.setProgress(currentVolume);
        volume_controller.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        if(volume_controller.getProgress() == 0) {
            volume_down.setImageResource(R.drawable.btn_volume_off);
        }

    }

    public void Thread(){
        Runnable task = new Runnable(){
            public void run(){

                /* while문을 돌려서 음악이 실행중일 때 계속 돌아가게 */
                while(mediaPlayer.isPlaying()){

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    /* music.getCurrentPosition()은 현재 음악 재생 위치를 가져오는 */
                    music_progress.setProgress(mediaPlayer.getCurrentPosition());
                }
            }
        };

        Thread thread = new Thread(task);
        thread.start();
    }
}
