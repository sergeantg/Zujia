package com.zujia.android.zujia.service;

import com.zujia.android.zujia.model.DoubanTest;
import com.zujia.android.zujia.model.HouseInfo;
import com.zujia.android.zujia.model.LoginResponse;
import com.zujia.android.zujia.model.PersonalInfo;
import com.zujia.android.zujia.model.SearchCondition;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by sergeantg_local on 2015/4/5.
 */
public class RestApi {

    private RestAdapter adapter;

    public RestApi(){
        adapter = new RestAdapter.Builder().setEndpoint("http://121.40.17.54/zjw/index.php/Phone").build();
    }

    //private interface
    private interface _login{
        @GET("/login")
        LoginResponse login(@Query("phone") String phone, @Query("password") String password);
    }

    private interface _search{
        @GET("/search")
        List<HouseInfo> search(@Query("key") String key,
                               @Query("size") int size, @Query("iscertification") int cer,
                               @Query("elevator") int e, @Query("decorated") int de,
                               @Query("min") int min, @Query("max") int max,
                               @Query("order") int o, @Query("no") int no, @Query("i") int i);
    }

    private interface _douban{
        @GET("/v2/music/1415369/tags")
        DoubanTest douban();
    }
    //public function
    public LoginResponse login(String phone, String psw){
        _login service = adapter.create(_login.class);
        return service.login(phone, psw);
    }

    public List<HouseInfo> search(SearchCondition c){
        _search service = adapter.create(_search.class);
        return service.search(c.key, c.rooms,
                c.certification, c.elevator, c.decoration,
                c.min, c.max, c.sort, c.no, c.i);
    }

    public PersonalInfo getPersonalInfo(String loginUid){
        return new PersonalInfo();
    }

    public DoubanTest douban(){
        _douban service = adapter.create(_douban.class);
        return  service.douban();
    }
}
