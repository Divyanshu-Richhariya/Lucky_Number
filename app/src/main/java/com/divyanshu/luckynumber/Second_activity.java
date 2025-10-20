package com.divyanshu.luckynumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class Second_activity extends AppCompatActivity {

    // 1. Declare the UI components
    TextView welcomeTxt, luckyNumberTxt;
    Button share_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        // Initialize the views
        welcomeTxt = findViewById(R.id.textview2);
        luckyNumberTxt = findViewById(R.id.lucky_number_txt);
        share_btn = findViewById(R.id.share_btn);

        // Receiving the data from Main Activity
        Intent i = getIntent();
        String userName = i.getStringExtra("name");

        // Set the welcome text with the user's name
        welcomeTxt.setText("Hello " + userName);

        // Generating Random Numbers
        int random_num = generateRandomNumber();
        luckyNumberTxt.setText("" + random_num);

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(userName, random_num);
            }
        });

    }

    public int generateRandomNumber() {
        Random random = new Random();
        int upper_limit = 1000;

        int randomNumberGenerated = random.nextInt(upper_limit);
        return randomNumberGenerated;
    }

    public void shareData(String username, int randomNum) {
        // Implicit Intent
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        // Additional Info
        i.putExtra(Intent.EXTRA_SUBJECT, username + " got lucky today!");
        i.putExtra(Intent.EXTRA_TEXT, "His lucky number is: " + randomNum);

        startActivity(Intent.createChooser(i, "Choose a Platform"));
    }
}