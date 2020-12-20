package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class INFO extends AppCompatActivity implements View.OnClickListener{
    Button info;
    Button info2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_n_f_o);
        info= (Button) findViewById(R.id.btn_info);
        info2= (Button) findViewById(R.id.auto_button);
    }

    public void onClick (View v){
        Intent i;
        i = new Intent(this, MainActivity.class);
        startActivity(i);


    }
    public void onClick2 (View v){
        Intent j;
        j = new Intent(this, Information.class);
        startActivity(j);

    }
}