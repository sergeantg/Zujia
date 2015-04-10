package com.zujia.android.zujia.model;

/**
 * Created by sergeantg_local on 2015/4/10.
 */
public class ServiceInfo extends Base{
    private String name;
    private String description;
    private float rate;

    public ServiceInfo(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
