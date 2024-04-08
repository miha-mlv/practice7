package com.example.practice7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button start_btn, stop_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(MainActivity.this, Service1.class);
                startService(startIntent);
            }
        });
        stop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stopIntent = new Intent(MainActivity.this, Service1.class);
                stopService(stopIntent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Остановка сервиса и музыки при уничтожении активности
        Intent stopIntent = new Intent(this, Service1.class);
        stopService(stopIntent);
    }
    private void init()
    {
        start_btn = findViewById(R.id.start_btn);
        stop_btn = findViewById(R.id.stop_btn);
    }
}