package com.example.said_world;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button1 = findViewById(R.id.button1);
        TextView text1 = findViewById(R.id.text1);
        text1.setText("");
        button1.setOnClickListener(v -> {
            text1.setText("Hello World!");
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> {
            text1.setTextColor(ContextCompat.getColor(this, R.color.purple_200));
        });
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(v -> {
            text1.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_200));
        });
        }
    }
