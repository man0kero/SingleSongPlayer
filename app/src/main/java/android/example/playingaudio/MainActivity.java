package android.example.playingaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageView playPauseIcon;
    SeekBar seekBar;
    ImageView note;
    boolean isAnimate = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        note = findViewById(R.id.note);
        note.animate().rotation(3600).setDuration(2000);

        playPauseIcon = findViewById(R.id.play);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.stuff);

        seekBar = findViewById(R.id.volumeSeekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 1000);
    }

    public void previous(View view) {
        seekBar.setProgress(0);
        mediaPlayer.seekTo(0);
        mediaPlayer.pause();
        playPauseIcon.setImageResource(R.drawable.play_arrow);
    }

    public void next(View view) {
        seekBar.setProgress(mediaPlayer.getDuration());
        mediaPlayer.seekTo(mediaPlayer.getDuration());
        mediaPlayer.pause();
        playPauseIcon.setImageResource(R.drawable.play_arrow);
    }

    public void play(View view) {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            playPauseIcon.setImageResource(R.drawable.play_arrow);
        } else {
            mediaPlayer.start();
            playPauseIcon.setImageResource(R.drawable.pause);
        }
    }
}