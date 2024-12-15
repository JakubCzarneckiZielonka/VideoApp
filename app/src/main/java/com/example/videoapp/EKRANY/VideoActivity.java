package com.example.videoapp.EKRANY;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.videoapp.MainActivity;
import com.example.videoapp.R;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_video);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        VideoView videoView = findViewById(R.id.videoView);

        // Odbierz kod przesłany z CodeEntryActivity
       int videoResId = getIntent().getIntExtra("video", R.raw.xpp);

        // Ustaw odpowiednie wideo
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + videoResId));

        MediaController mediaController = new MediaController(VideoActivity.this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        // Listener na zakończenie odtwarzania
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intent = new Intent(VideoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Rozpocznij odtwarzanie po przygotowaniu
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setVolume(1.0f, 1.0f);
                mediaPlayer.start();
            }
        });

        // Listener przycisku powrotu
        Button btnBackToMain = findViewById(R.id.btnBackToMain);
        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
