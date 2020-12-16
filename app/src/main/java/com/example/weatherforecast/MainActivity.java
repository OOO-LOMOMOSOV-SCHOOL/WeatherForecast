package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

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
        tv=findViewById(R.id.tv);
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
                Double pop1 = daily1.getPop();
                List<Weather_> wl1= daily1.getWeather();
                Weather_ w1= wl1.get(0);
                Integer id1= w1.getId();
                SetIcon(day1,id1);

                Daily daily2=mydatalist.getDaily(1);
                Double pop2 = daily2.getPop();
                List<Weather_> wl2= daily1.getWeather();
                Weather_ w2= wl2.get(0);
                Integer id2= w2.getId();
                SetIcon(day2,id2);

                Daily daily3=mydatalist.getDaily(2);
                Double pop3 = daily3.getPop();
                List<Weather_> wl3= daily3.getWeather();
                Weather_ w3= wl3.get(0);
                Integer id3= w3.getId();
                SetIcon(day3,id3);

                Daily daily4=mydatalist.getDaily(3);
                Double pop4 = daily4.getPop();
                List<Weather_> wl4= daily4.getWeather();
                Weather_ w4= wl4.get(0);
                Integer id4= w4.getId();
                SetIcon(day4,id4);

                Daily daily5=mydatalist.getDaily(4);
                Double pop5 = daily5.getPop();
                List<Weather_> wl5= daily5.getWeather();
                Weather_ w5= wl5.get(0);
                Integer id5= w5.getId();
                SetIcon(day5,id5);

                Daily daily6=mydatalist.getDaily(5);
                Double pop6 = daily6.getPop();
                List<Weather_> wl6= daily6.getWeather();
                Weather_ w6= wl6.get(0);
                Integer id6= w6.getId();
                SetIcon(day6,id6);

                Daily daily7=mydatalist.getDaily(6);
                Double pop7 = daily7.getPop();
                List<Weather_> wl7= daily7.getWeather();
                Weather_ w7= wl7.get(0);
                Integer id7= w7.getId();
                SetIcon(day7,id7);
                Wash(pop1,pop2,pop3,pop4);



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