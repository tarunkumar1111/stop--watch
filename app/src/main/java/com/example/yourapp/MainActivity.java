package com.example.yourapp;



import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvTime;
    private Button btnStart, btnStop, btnReset;

    private boolean isRunning = false;
    private int seconds = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTime = findViewById(R.id.tvTime);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnReset = findViewById(R.id.btnReset);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }

    private void startTimer() {
        if (!isRunning) {
            isRunning = true;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    seconds++;
                    int hours = seconds / 3600;
                    int minutes = (seconds % 3600) / 60;
                    int secs = seconds % 60;
                    String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
                    tvTime.setText(time);
                    if (isRunning) {
                        startTimer();
                    }
                }
            }, 1000);
        }
    }

    private void stopTimer() {
        isRunning = false;
    }

    private void resetTimer() {
        isRunning = false;
        seconds = 0;
        tvTime.setText("00:00:00");
    }
}
