package com.zujia.android.zujia.model;

/**
 * Created by lenovo on 15-4-9.
 */
public class Tag{
    private int count;
    private String alt;
    private String title;

    public int getCount(){
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }

    public String getAlt(){
        return alt;
    }

    public void setAlt(String alt){
        this.alt = alt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}