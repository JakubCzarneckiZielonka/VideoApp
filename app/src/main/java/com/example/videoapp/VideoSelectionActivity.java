package com.example.videoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.videoapp.EKRANY.VideoActivity;
import com.example.videoapp.R;


public class VideoSelectionActivity extends AppCompatActivity {

    private Button btnVideo1, btnVideo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_selection);

        btnVideo1 = findViewById(R.id.btnVideo1);
        btnVideo2 = findViewById(R.id.btnVideo2);


        // Przypisanie filmów do przycisków
        btnVideo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo(R.raw.xpp);
            }
        });

        btnVideo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo(R.raw.ps_peruka);
            }
        });


    }

    // Funkcja do odtwarzania wideo
    private void playVideo(int videoResId) {
        Intent intent = new Intent(VideoSelectionActivity.this, VideoActivity.class);
        intent.putExtra("video", videoResId);
        startActivity(intent);
    }
}
