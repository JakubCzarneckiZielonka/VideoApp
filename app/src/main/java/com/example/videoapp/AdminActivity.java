package com.example.videoapp;



import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

public class AdminActivity extends AppCompatActivity {

    private TextView textViewClickCount;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        textViewClickCount = findViewById(R.id.textViewClickCount);

        // Inicjalizacja bazy danych
        db = AppDatabase.getDatabase(this);

        // Obserwowanie liczby poprawnych kodów
        db.codeEntryDao().getTotalCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                textViewClickCount.setText("Liczba poprawnych kodów: " + count);
            }
        });
    }
}
