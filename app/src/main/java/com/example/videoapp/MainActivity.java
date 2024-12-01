package com.example.videoapp;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


import com.example.videoapp.EKRANY.VideoActivity;

import com.example.videoapp.WhoAreYouActivity;

public class MainActivity extends AppCompatActivity {

    private static final String KIOSK_PIN = "1234"; // PIN do przejścia do VideoActivity
    private static final String ADMIN_PIN = "9999"; // PIN do wyjścia z trybu kiosku i wejścia do AdminActivity
    private EditText editTextCode;
    private Button buttonSubmit;
    private Button buttonWhoAreYou;
    private Button buttonDirectorInfo;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicjalizacja pól
        editTextCode = findViewById(R.id.editTextCode);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonWhoAreYou = findViewById(R.id.buttonWhoAreYou);
        buttonDirectorInfo = findViewById(R.id.buttonDirectorInfo);

        // Inicjalizacja bazy danych
        db = AppDatabase.getDatabase(this);

        // Ustawienie akcji dla przycisku "Submit"
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextCode.getText().toString();
                handlePinEntry(code);  // Wywołuje funkcję obsługującą PIN
            }
        });

        // Akcja dla przycisku "Kim jesteś"
        buttonWhoAreYou.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WhoAreYouActivity.class);
            startActivity(intent);
        });

        // Akcja dla przycisku "Co wiesz o reżyserze"
        buttonDirectorInfo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, com.example.videoapp.DirectorInfoActivity.class);
            startActivity(intent);
        });

        // Uruchomienie trybu kiosku
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startKioskMode();
        }
    }

    private void startKioskMode() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null && !activityManager.isInLockTaskMode()) {
            startLockTask();
            Toast.makeText(this, "Kiosk mode started", Toast.LENGTH_SHORT).show();
        }
    }

    private void handlePinEntry(String enteredPin) {
        if (enteredPin.equals(KIOSK_PIN)) {
            // Zwiększ liczbę poprawnych wpisów do bazy
            new Thread(() -> {
                CodeEntry codeEntry = new CodeEntry(1);
                db.codeEntryDao().insert(codeEntry);
            }).start();

            Intent intent = new Intent(MainActivity.this, VideoActivity.class);
            startActivity(intent);
        } else if (enteredPin.equals(ADMIN_PIN)) {
            // Wyjdź z trybu kiosku i otwórz panel administracyjny
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                stopLockTask();
            }
            Intent intent = new Intent(MainActivity.this, AdminActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Niepoprawny kod", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null) {
            activityManager.moveTaskToFront(getTaskId(), 0);
        }
    }
}