package com.example.clockview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.clockview.view.ClockView;

public class MainActivity extends AppCompatActivity {

    private ClockView clockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clockView = new ClockView(this);

        final ClockView clockView = findViewById(R.id.clockView);
        clockView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "现在是：" + clockView.getTime(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
