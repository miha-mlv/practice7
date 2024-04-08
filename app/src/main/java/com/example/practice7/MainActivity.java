package com.example.practice7;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    private EditText editName;
    private Button okBtn, timeAndDate, nextActivity;
    private TextView yourName, textTimeAndDate;
    private Boolean flag = false;
    private int hour, minute, year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == false)
                {
                    yourName.setText(editName.getText().toString());
                    flag = true;
                }
                else {
                    // Создание строителя диалоговых окон
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    // Установка заголовка и сообщения диалогового окна
                    builder.setTitle("Подтверждение");
                    builder.setMessage("Вы уверены, что вас зовут " + editName.getText().toString() + "?");
                    builder.setIcon(android.R.drawable.ic_dialog_alert);

                    // Установка кнопки "Да" и ее обработчика
                    builder.setPositiveButton("Да", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    yourName.setText(editName.getText().toString());
                                }
                            });

                    // Установка кнопки "Отмена" и ее обработчика
                    builder.setNegativeButton("Нет", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    editName.setText(null);
                                    dialog.dismiss();
                                }
                            });

                    // Создание и отображение AlertDialog
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

        timeAndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int
                                    hourOfDay, int this_minute) {
                                hour = hourOfDay;
                                minute = this_minute;
                                textTimeAndDate.setText(
                                        "Год: " + year+"\n" +
                                                "Месяц: "+month + "\n" +
                                                "День: "+day + "\n"+
                                                "Время: "+ hour+":"+minute);
                            }
                        }, hour, minute, true); // Использование 24-часового формата
                timePickerDialog.show();

                // Создание обработчика выбора даты
                DatePickerDialog.OnDateSetListener dateSetListener = new
                        DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int this_year, int this_month,
                                                  int this_day) {
                                year = this_year;
                                month = this_month;
                                day = this_day;
                            }
                        };
// Создание и отображение DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        dateSetListener, year, month, day);
                datePickerDialog.show();
            }
        });
        nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_window);

                Button yesBtn = dialog.findViewById(R.id.yesBtn);
                Button noBtn = dialog.findViewById(R.id.noBtn);
                yesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });

                noBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    private void init()
    {
        editName = findViewById(R.id.editName);
        okBtn = findViewById(R.id.okBtn);
        yourName = findViewById(R.id.yourName);
        timeAndDate = findViewById(R.id.timeAndDate);
        textTimeAndDate = findViewById(R.id.textTimeAndDate);
        nextActivity = findViewById(R.id.nextActivity);
    }
}