package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Information extends AppCompatActivity {
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        back = (Button) findViewById(R.id.autoBack);
    }
        public void autoBack (View v){
            Intent i;
            i = new Intent(this, INFO.class);
            startActivity(i);
        }


}