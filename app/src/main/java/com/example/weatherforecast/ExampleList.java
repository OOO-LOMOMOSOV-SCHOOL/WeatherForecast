package com.example.weatherforecast;
import java.util.ArrayList;

public class ExampleList {
ArrayList<Daily> daily;

    public Daily getDaily(int i) {
        return daily.get(i);
    }

    public void setDaily(ArrayList<Daily> daily) {
        this.daily = daily;
    }
}
