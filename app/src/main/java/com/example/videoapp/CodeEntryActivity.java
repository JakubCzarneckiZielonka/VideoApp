package com.example.videoapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.videoapp.EKRANY.VideoActivity;

public class CodeEntryActivity extends AppCompatActivity {

    private EditText editTextCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_entry);

        editTextCode = findViewById(R.id.editText);

        // Nasłuchiwanie zmian w EditText
        editTextCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Nie używane w tym przypadku
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String enteredPin = s.toString();

                // Sprawdzamy tylko po wpisaniu 4 znaków
                if (enteredPin.length() == 4) {
                    handlePinEntry(enteredPin);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Nie używane w tym przypadku
            }
        });
    }

    private void handlePinEntry(String enteredPin) {
        Intent intent = new Intent(CodeEntryActivity.this, VideoActivity.class);

        if (enteredPin.equals("1957")) {
            intent.putExtra("video", R.raw.ps_banan);
        } else if (enteredPin.equals("6264")) {
            intent.putExtra("video", R.raw.ps_peruka);
        } else if (enteredPin.equals("1430")) {
            intent.putExtra("video", R.raw.ps_niedopalek);
        } else if (enteredPin.equals("5200")) {
            intent.putExtra("video", R.raw.ps_okulary);
        } else if (enteredPin.equals("2506")) {
            intent.putExtra("video", R.raw.ps_korektor);
        } else if (enteredPin.equals("0109")) {
            intent = new Intent(CodeEntryActivity.this, VideoSelectionActivity.class);
            startActivity(intent);
        } else if (enteredPin.equals("8379")) {
            intent.putExtra("video", R.raw.ps_poduszka);
        } else if (enteredPin.equals("7124")) {
            intent.putExtra("video", R.raw.ps_peruka);
        } else {
            // Jeśli kod jest niepoprawny
            Toast.makeText(CodeEntryActivity.this, "Niepoprawny kod", Toast.LENGTH_SHORT).show();
            return;
        }

        // Jeśli kod jest poprawny, przejście do VideoActivity
        startActivity(intent);
    }
}
