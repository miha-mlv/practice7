package com.example.practice7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Запуск сервиса для воспроизведения музыки
        Intent startIntent = new Intent(this, Service1.class);
        startService(startIntent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Остановка сервиса и музыки при уничтожении активности
        Intent stopIntent = new Intent(this, Service1.class);
        stopService(stopIntent);
    }
}