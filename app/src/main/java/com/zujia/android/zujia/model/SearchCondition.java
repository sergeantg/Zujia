package com.zujia.android.zujia.model;

/**
 * Created by sergeantg_local on 2015/4/8.
 */
public class SearchCondition{
    public String key = "";
    public double longitude = 0;
    public double latitude = 0;

    public int certification = 0;
    public int elevator = 0;
    public int decoration = 0;
    public int rooms = 0;
    public int min = 0;
    public int max = 0;
    public int sort = 0;
    public int no = 20;
    public int i = 0;

    public SearchCondition(){
        longitude = 0;
        latitude = 0;
        certification = 0;
        elevator = 0;
        decoration = 0;
        rooms = 0;
        min = 0;
        max = 0;
        sort = 0;
        no = 20;
        i = 0;
    }

    public void reset(){
        longitude = 0;
        latitude = 0;
        certification = 0;
        elevator = 0;
        decoration = 0;
        rooms = 0;
        min = 0;
        max = 0;
        sort = 0;
        no = 20;
        i = 0;
    }
}
