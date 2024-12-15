package com.example.videoapp;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageButton buttonWhoAreYou;
    private ImageButton buttonDirectorInfo;
    private ImageButton buttonEnterCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonWhoAreYou = findViewById(R.id.buttonWhoAreYou);
        buttonDirectorInfo = findViewById(R.id.buttonDirectorInfo);
        buttonEnterCode = findViewById(R.id.buttonSubmit);

        buttonWhoAreYou.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WhoAreYouActivity.class);
            startActivity(intent);
        });

        buttonDirectorInfo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, com.example.videoapp.DirectorInfoActivity.class);
            startActivity(intent);
        });

        buttonEnterCode.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CodeEntryActivity.class);
            startActivity(intent);
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startKioskMode();
        }
    }

    private void startKioskMode() {
        if (!getSystemService(ActivityManager.class).isInLockTaskMode()) {
            startLockTask();
            Toast.makeText(this, "Kiosk mode started", Toast.LENGTH_SHORT).show();
        }
    }
}
