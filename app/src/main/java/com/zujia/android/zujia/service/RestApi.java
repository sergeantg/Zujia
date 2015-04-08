package com.zujia.android.zujia.service;

import com.zujia.android.zujia.model.HouseInfo;
import com.zujia.android.zujia.model.SearchCondition;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by sergeantg_local on 2015/4/5.
 */
public class RestApi {

    private RestAdapter adapter;

    public RestApi(){
        adapter = new RestAdapter.Builder().setEndpoint("http://zujia.com").build();
    }

    //private interface
    private interface _login{
        @GET("/login?phone={phone}&password={password}")
        int login(@Path("phone") int phone, @Path("password") String password);
    }

    private interface _search{
        @GET("/search")
        List<HouseInfo> search(@Query("longitude") float longi, @Query("latitude") float lati,
                               @Query("size") int size, @Query("iscertification") int cer,
                               @Query("elevator") int e, @Query("decorated") int de,
                               @Query("min") int min, @Query("max") int max,
                               @Query("order") int o, @Query("no") int no, @Query("i") int i);
    }

    //public function
    public int login(int phone, String psw){
        _login service = adapter.create(_login.class);
        return service.login(phone, psw);
    }

    public List<HouseInfo> search(SearchCondition c){
        _search service = adapter.create(_search.class);
        return service.search(c.longitude, c.latitude, c.rooms,
                c.certification, c.elevator, c.decoration,
                c.min, c.max, c.sort, c.no, c.i);
    }
}
