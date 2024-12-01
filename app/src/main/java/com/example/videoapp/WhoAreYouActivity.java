package com.example.videoapp;

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

public class WhoAreYouActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_video);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));

        MediaController mediaController = new MediaController(WhoAreYouActivity.this);
        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);

        // Ustaw listener na zakończenie odtwarzania
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intent = new Intent(WhoAreYouActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setVolume(1.0f, 1.0f); // Ustaw głośność na maksimum
                mediaPlayer.start();
            }
        });

        // Znajdź przycisk i ustaw listener
        Button btnBackToMain = findViewById(R.id.btnBackToMain);
        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WhoAreYouActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
