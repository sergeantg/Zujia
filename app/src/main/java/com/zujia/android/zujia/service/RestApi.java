package com.zujia.android.zujia.service;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by sergeantg_local on 2015/4/5.
 */
public class RestApi {

    private RestAdapter adapter;

    public RestApi(){
        adapter = new RestAdapter.Builder().setEndpoint("http://zujia.com").build();
    }

    //private interface
    private interface _Login{
        @GET("/login?phone={phone}&password={password}")
        int login(@Path("phone") int phone, @Path("password") String password);
    }

    //public function
    public int login(int phone, String psw){
        _Login service = adapter.create(_Login.class);
        return service.login(phone, psw);
    }
}
