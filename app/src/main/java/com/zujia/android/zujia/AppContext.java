package com.zujia.android.zujia;

import android.app.Application;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.zujia.android.zujia.model.HouseInfo;
import com.zujia.android.zujia.model.PersonalInfo;
import com.zujia.android.zujia.model.SearchCondition;
import com.zujia.android.zujia.service.RestApi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * 全局应用程序类：用于保存和调用全局应用配置及访问网络数据
 * Created by lenovo on 15-4-9.
 */
public class AppContext extends Application {

    private RestApi restApi;
    public SearchCondition condition;
    public List<HouseInfo> houseList;
    private boolean login = false;	//登录状态
    private int loginUid = 0;	//登录用户的id

    @Override
    public void onCreate() {
        super.onCreate();
        restApi = new RestApi();

        String token = "Mvx131kv07Ox9esct1N5D5C3BleW4x8tlHuWbCsQJjU6eriHn/IR4kf0BA4Stult9mduJXfIXmymWR3HZqodFeTNGd6qG13V";
        // 初始化融云
        RongIM.init(this);
        // 连接融云服务器。
        try {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                @Override
                public void onSuccess(String s) {
                    // 此处处理连接成功。
                    Log.d("Connect:", "Login successfully.");
                }

                @Override
                public void onError(ErrorCode errorCode) {
                    // 此处处理连接错误。
                    Log.d("Connect:", "Login failed.");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检测网络是否可用
     * @return
     */
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }


    /**
     * 用户是否登录
     * @return
     */
    public boolean isLogin() {
        return login;
    }

    /**
     * 获取登录用户id
     * @return
     */
    public int getLoginUid() {
        return this.loginUid;
    }

    /**
     * 用户注销
     */
    public void Logout() {
        this.login = false;
        this.loginUid = 0;
    }


    public boolean getGPSSettings() {
        LocationManager alm = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);
        if (!alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER))
            return false;
        return true;
    }

    /**GPS定位
     *
     * @return
     */
    public Location getLocation()
    {
        // 获取位置管理服务
        LocationManager locationManager;
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) this.getSystemService(serviceName);
        // 查找到服务信息
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // 高精度
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW); // 低功耗

        String provider = locationManager.getBestProvider(criteria, true); // 获取GPS信息
        Location location = locationManager.getLastKnownLocation(provider); // 通过GPS获取位置
        if (location == null)
            return null;
        return location;
    }

    /**
     * 由关键词定位
     *
     */
    public Location getLocation(String key){
        Location location = null;
        return location;
    }
   /**
    *获取房屋列表
    * @param isRefresh 是否主动刷新
    * @return
    */
    public List<HouseInfo> getHouseList(boolean add, boolean isRefresh){

        //重新获取list
        if(isRefresh){
            condition.i = 0;
            this.houseList = restApi.search(condition);
        }
        //加载更多
        if(add){
            condition.i++;
            houseList.addAll(restApi.search(condition));
        }
        return houseList;
    }


    /**
     * 我的个人资料
     * @param isRefresh 是否主动刷新
     * @return
     */
    public PersonalInfo getPersonalInfo(boolean isRefresh){
        PersonalInfo myinfo;
        String key = "personal_info_" + loginUid;
        if(isNetworkConnected() && (!isReadDataCache(key) || isRefresh)) {
            try{
                myinfo = restApi.getPersonalInfo(loginUid);
                if(myinfo != null){
                    saveObject(myinfo, key);
                }
            }catch(Exception e){
                myinfo = (PersonalInfo)readObject(key);
                if(myinfo == null)
                    throw e;
            }
        } else {
            myinfo = (PersonalInfo)readObject(key);
            if(myinfo == null)
                myinfo = new PersonalInfo();
        }
        return myinfo;
    }

    /**
     * 判断缓存数据是否可读
     * @param cachefile
     * @return
     */
    private boolean isReadDataCache(String cachefile)
    {
        return readObject(cachefile) != null;
    }

    /**
     * 判断缓存是否存在
     * @param cachefile
     * @return
     */
    private boolean isExistDataCache(String cachefile)
    {
        boolean exist = false;
        File data = getFileStreamPath(cachefile);
        if(data.exists())
            exist = true;
        return exist;
    }

    /**
     * 保存对象
     * @param ser
     * @param file
     */
    public boolean saveObject(Serializable ser, String file) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = openFileOutput(file, MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ser);
            oos.flush();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            try {
                oos.close();
            } catch (Exception e) {}
            try {
                fos.close();
            } catch (Exception e) {}
        }
    }

    /**
     * 读取对象
     * @param file
     * @return
     */
    public Serializable readObject(String file){
        if(!isExistDataCache(file))
            return null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = openFileInput(file);
            ois = new ObjectInputStream(fis);
            return (Serializable)ois.readObject();
        }catch(FileNotFoundException e){
        }catch(Exception e){
            e.printStackTrace();
            //反序列化失败 - 删除缓存文件
            if(e instanceof InvalidClassException){
                File data = getFileStreamPath(file);
                data.delete();
            }
        }finally{
            try {
                ois.close();
            } catch (Exception e) {}
            try {
                fis.close();
            } catch (Exception e) {}
        }
        return null;
    }

}
