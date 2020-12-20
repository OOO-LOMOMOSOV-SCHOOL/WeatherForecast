package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.Widget.Button;
import android.view.View;

public class INFO extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_n_f_o);
    }
Buton info= (button) findViewById(R.id.btn_info);
    info.setOnClickListener(new View.OnClickListener()){
        {}
        {}


            @Override
            public void onClick (View view);
            {
                startActivity(INF0Activity.this, InformationActivity.this);}

        }
                
}