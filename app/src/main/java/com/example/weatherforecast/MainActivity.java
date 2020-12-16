package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView day1;
    TextView day2;
    TextView day3;
    TextView day4;
    TextView day5;
    TextView day6;
    TextView day7;
    TextView pop1;
    TextView pop2;
    TextView pop3;
    TextView pop4;
    TextView pop5;
    TextView pop6;
    TextView pop7;
    TextView date1;
    TextView date2;
    TextView date3;
    TextView date4;
    TextView date5;
    TextView date6;
    TextView date7;
    String url="http://api.openweathermap.org/data/2.5/onecall?lat={lat}&lon={lon}&exclude{...}&units{...}&appid{...}";
    String apikey="3a136475c83d56418cedc74414c4f2ba";
    String units = "metric";
    String lat="59.95";
    String lon="30.44";
    String exclude="hourly,minutely,alerts";
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        day1=findViewById(R.id.day1);
        day2=findViewById(R.id.day2);
        day3=findViewById(R.id.day3);
        day4=findViewById(R.id.day4);
        day5=findViewById(R.id.day5);
        day6=findViewById(R.id.day6);
        day7=findViewById(R.id.day7);
        date1=findViewById(R.id.date1);
        date2=findViewById(R.id.date2);
        date3=findViewById(R.id.date3);
        date4=findViewById(R.id.date4);
        date5=findViewById(R.id.date5);
        date6=findViewById(R.id.date6);
        date7=findViewById(R.id.date7);
        pop1=findViewById(R.id.pop1);
        pop2=findViewById(R.id.pop2);
        pop3=findViewById(R.id.pop3);
        pop4=findViewById(R.id.pop4);
        pop5=findViewById(R.id.pop5);
        pop6=findViewById(R.id.pop6);
        pop7=findViewById(R.id.pop7);
        tv=findViewById(R.id.tv);

        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd", Locale.getDefault());
        String dateStr = dateFormat.format(currentDate);
        Integer dateInt =Integer.parseInt (dateStr);
        date1.setText(String.valueOf(dateInt));
        date2.setText(String.valueOf(dateInt+1));
        date3.setText(String.valueOf(dateInt+2));
        date4.setText(String.valueOf(dateInt+3));
        date5.setText(String.valueOf(dateInt+4));
        date6.setText(String.valueOf(dateInt+5));
        date7.setText(String.valueOf(dateInt+6));



    }
    public static void SetIcon(TextView day,Integer id){
        if (id >= 200 && id <= 232){
            day.setText("\uF01E");      //гроза с дождем
        } else if (id >= 300 && id <= 321){
            day.setText("\uF017");      //морось
        }else if (id >= 500 && id <= 504){
            day.setText("\uF008");      //дождь
        }else if (id == 511){
            day.setText("\uF018");      //Ледяной дождь
        }else if (id >= 511 && id <= 531){
            day.setText("\uF019");      //Дождь и темно
        }else if (id >= 600 && id <= 622){
            day.setText("\uF076");      //снег
        }else if (id >= 701 && id <= 781){
            day.setText("\uF063");       //туман
        }else if (id == 800) {
            day.setText("\uF00D");       //Солнышко
        }else if (id == 801) {
            day.setText("\uF00C");       //мало облаков
        }else if (id == 802) {
            day.setText("\uF002");       //больше облаков
        }else if (id == 803) {
            day.setText("\uF041");       //еще больше облаков
        }else if (id == 804) {
            day.setText("\uF013");       //Все небо- облако
        }

    }

    public void Wash(Double p1,Double p2,Double p3,Double p4){
        if (p1>0.5||p2>0.5||p3>0.5||p4>0.5){
            tv.setText("В ближайшие дни ожидаются осадки. Лучше машину не мыть");
        }else {
            tv.setText("Можете смело мыть машину");

        }
    }




    public void getweather(View v){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherapi myapi=retrofit.create(weatherapi.class);
        Call<ExampleList> examplecall=myapi.getweather(lat,lon,exclude,units,apikey);
        examplecall.enqueue(new Callback<ExampleList>() {
            @Override
            public void onResponse(Call<ExampleList> call, Response<ExampleList> response) {
               if(response.code()==404){
                    Toast.makeText(MainActivity.this,"Please Entere the valid city",Toast.LENGTH_LONG).show();
                }
                else if(!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_LONG).show();
                }else {
                     Toast.makeText(MainActivity.this, "sucsess", Toast.LENGTH_LONG).show();

               }
                ExampleList mydatalist= (ExampleList) response.body();

                Daily daily1=mydatalist.getDaily(0);
                Double p1 = daily1.getPop();
                List<Weather_> wl1= daily1.getWeather();
                Weather_ w1= wl1.get(0);
                Integer id1= w1.getId();
                SetIcon(day1,id1);
                pop1.setText(String.valueOf(Math.round (p1))+"%");

                Daily daily2=mydatalist.getDaily(1);
                Double p2 = daily2.getPop();
                List<Weather_> wl2= daily1.getWeather();
                Weather_ w2= wl2.get(0);
                Integer id2= w2.getId();
                SetIcon(day2,id2);
                pop2.setText(String.valueOf(Math.round (p2*100))+"%");

                Daily daily3=mydatalist.getDaily(2);
                Double p3 = daily3.getPop();
                List<Weather_> wl3= daily3.getWeather();
                Weather_ w3= wl3.get(0);
                Integer id3= w3.getId();
                SetIcon(day3,id3);
                pop3.setText(String.valueOf(Math.round(p3*100))+"%");

                Daily daily4=mydatalist.getDaily(3);
                Double p4 = daily4.getPop();
                List<Weather_> wl4= daily4.getWeather();
                Weather_ w4= wl4.get(0);
                Integer id4= w4.getId();
                SetIcon(day4,id4);
                pop4.setText(String.valueOf(Math.round (p4*100))+"%");

                Daily daily5=mydatalist.getDaily(4);
                Double p5 = daily5.getPop();
                List<Weather_> wl5= daily5.getWeather();
                Weather_ w5= wl5.get(0);
                Integer id5= w5.getId();
                SetIcon(day5,id5);
                pop5.setText(String.valueOf(Math.round (p5*100))+"%");

                Daily daily6=mydatalist.getDaily(5);
                Double p6 = daily6.getPop();
                List<Weather_> wl6= daily6.getWeather();
                Weather_ w6= wl6.get(0);
                Integer id6= w6.getId();
                SetIcon(day6,id6);
                pop6.setText(String.valueOf(Math.round (p6*100))+"%");

                Daily daily7=mydatalist.getDaily(6);
                Double p7 = daily7.getPop();
                List<Weather_> wl7= daily7.getWeather();
                Weather_ w7= wl7.get(0);
                Integer id7= w7.getId();
                SetIcon(day7,id7);
                pop7.setText(String.valueOf(Math.round (p7*100))+"%");


                Wash(p1,p2,p3,p4);



               // Daily daily=mydatalist.get(1).getDaily();
               // Integer pop=daily.getPop();
                //day1.setText(String.valueOf(pop));


                //                Example mydata=response.body();
                //                Main main=mydata.getMain();
                //                Double temp = main.getTemp();
                //                tv.setText(String.valueOf(temp)+"C");
            }


           @Override
           public void onFailure(Call<ExampleList> call, Throwable t) {
               Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
}