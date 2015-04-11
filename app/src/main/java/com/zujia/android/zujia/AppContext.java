package com.zujia.android.zujia;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
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
import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient.UserInfo;

/**
 * 全局应用程序类：用于保存和调用全局应用配置及访问网络数据
 * Created by lenovo on 15-4-9.
 */
public class AppContext extends Application {

    private RestApi restApi;
    private PersonalInfo pi;
    private boolean login = false;	//登录状态
    private int uid = 0;	//登录用户的id

    public LocationClient mLocationClient;
    public SearchCondition condition;
    public List<HouseInfo> houseList;

    @Override
    public void onCreate() {
        super.onCreate();
        restApi = new RestApi();
        rongInit();
        baiduInit();
    }

    /**
     * 融云功能初始化
     * @return
     */
    private boolean rongInit(){
        boolean re = true;
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
            re = false;
        }

        RongIM.setGetFriendsProvider(new RongIM.GetFriendsProvider() {
            @Override
            public List<RongIMClient.UserInfo> getFriends() {
                return getFriendList();
            }
        });

        RongIM.setGetUserInfoProvider(new RongIM.GetUserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String userId) {
                return new UserInfo(userId, getPersonalInfo(false).getUsername(), getPersonalInfo(false).getAvater());
            }
        }, false);

        return re;
    }

    /**
     * 百度地图初始化
     */
    private void baiduInit(){
        mLocationClient = new LocationClient(this.getApplicationContext());

        //设置定位选项
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//设置定位模式
        option.setCoorType("bd09ll");//返回的定位结果是百度经纬度,默认值gcj02
        option.setScanSpan(5000);//设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(true);//返回的定位结果包含地址信息
        option.setNeedDeviceDirect(true);//返回的定位结果包含手机机头的方向

        mLocationClient.setLocOption(option);

        SDKInitializer.initialize(getApplicationContext());
    }

    /**
     * 获取联系人列表
     * @return list
     */
    private List<UserInfo> getFriendList(){
        List<UserInfo> list = new ArrayList<RongIMClient.UserInfo>();
        if(login) {
            RongIMClient.UserInfo user1 = new RongIMClient.UserInfo("15620936889", "我", "http://http://www.qqzhi.com/touxiang/viewimg_229150.html?http://www.qqzhi.com/uploadpic/2014-09-12/055445721.jpg");
            list.add(user1);

        }
        return list;
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
    public int getUid() {
        return this.uid;
    }

    /**
     * 登陆方法
     * @param phone
     * @param psw
     * @return
     */
    public int login(int phone, String psw){
        if(isNetworkConnected()){
            int re = restApi.login(phone, psw);

            if(re == 0){
                uid = phone;
                login = true;
            }
            return re;
        }
        return 1;
    }

    /**
     * 用户注销
     */
    public void Logout() {
        this.login = false;
        this.uid = 0;
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
        if(login){

            String key = "personal_info_" + uid;
            if(isNetworkConnected() && (!isReadDataCache(key) || isRefresh)) {
                try{
                    pi = restApi.getPersonalInfo(uid);
                    if(pi != null){
                        saveObject(pi, key);
                    }
                }catch(Exception e){
                    pi = (PersonalInfo)readObject(key);
                    if(pi == null)
                        throw e;
                }
            } else {
                pi = (PersonalInfo)readObject(key);
                if(pi == null)
                    pi = new PersonalInfo();
            }
            return pi;
        }

        ///DEBUG
        pi = new PersonalInfo();
        pi.setAvater("http://rongcloud-web.qiniudn.com/docs_demo_rongcloud_logo.png");
        pi.setUsername("奥巴马");
        pi.setPhone(36889);
        return pi;
        ///END_DEBUG

        //return null;
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
