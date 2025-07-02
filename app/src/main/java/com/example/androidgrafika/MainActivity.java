package com.example.androidgrafika;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RysunekView rysunekView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rysunekView = findViewById(R.id.rysunekView);

        findViewById(R.id.redBtn).setOnClickListener(v -> rysunekView.setKolor(0xFFFF0000));
        findViewById(R.id.yellowBtn).setOnClickListener(v -> rysunekView.setKolor(0xFFFFFF00));
        findViewById(R.id.blueBtn).setOnClickListener(v -> rysunekView.setKolor(0xFF0000FF));
        findViewById(R.id.greenBtn).setOnClickListener(v -> rysunekView.setKolor(0xFF00FF00));
        findViewById(R.id.clearBtn).setOnClickListener(v -> rysunekView.wyczysc());
    }
}
