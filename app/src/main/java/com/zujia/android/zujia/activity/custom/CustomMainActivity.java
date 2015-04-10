package com.zujia.android.zujia.activity.custom;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TabHost;

import com.zujia.android.zujia.R;
import com.zujia.android.zujia.activity.SearchActivity;
import com.zujia.android.zujia.activity.ServiceSecondMenuItemFragment;
import com.zujia.android.zujia.activity.SettingActivity;

import io.rong.imkit.RongIM;


public class CustomMainActivity extends Activity {

    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_main);

        // 获取TabHost对象
        final TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        // 如果没有继承TabActivity时，通过该种方法加载启动tabHost
        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec("0")
                .setIndicator("消息", getResources().getDrawable(R.drawable.message)).setContent(
                R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("1")
                .setIndicator("联系记录", getResources().getDrawable(R.drawable.contact)).setContent(
                R.id.tab2));
        tabHost.addTab(tabHost.newTabSpec("2")
                .setIndicator("租家帮", getResources().getDrawable(R.drawable.zujiabang)).setContent(
                R.id.tab3));
       // tabHost.addTab(tabHost.newTabSpec("tab1")
          //      .setIndicator("我", getResources().getDrawable(R.drawable.me_meitu_1)).setContent(
            //    R.id.tab4));

        android.view.View view = getLayoutInflater().inflate(R.layout.custom_tab_me, null);

        tabHost.addTab(tabHost.newTabSpec("3").setIndicator(view).setContent(
                      R.id.tab4));

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
            public void onTabChanged(String tabId) {
                ActionBar b = getActionBar();


                //tabHost.getTabWidget().getChildAt(0).setAlpha(255);

               // tabHost.getCurrentTabView();
                //findViewById(R.id.imgV_tab_message).setAlpha(255);
                //findViewById(R.id.imgV_tab_contact).setAlpha(255);
                //findViewById(R.id.imgV_tab_zujiabang).setAlpha(255);
                //findViewById(R.id.imgV_tab_me).setAlpha(255);
                //findViewById(R.id.txtV_tab_me).setAlpha(255);
                Log.i("test", tabId);
                switch(tabId){

                    case "0":
                       // findViewById(R.id.imgV_tab_message).setAlpha(0);
                        // 点击按钮发起聊天会话。

                        b.setTitle(R.string.message_main);
                        break;
                    case "1":
                        //findViewById(R.id.imgV_tab_contact).setAlpha(0);
                        b.setTitle(R.string.contact_main);
                        break;
                    case "2":
                        //findViewById(R.id.imgV_tab_zujiabang).setAlpha(0);
                        b.setTitle(R.string.zujiabang_main);
                        break;
                    case "3":
                        b.setTitle(R.string.me_main);
                        //findViewById(R.id.imgV_tab_me).setAlpha(100);
                        //findViewById(R.id.txtV_tab_me).setAlpha(100);
                        break;

                }
            }
        });


        mHandler = new Handler();

        /////////////////////////
        findViewById(R.id.buttondd).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mHandler.post(new Runnable() {
                              @Override
                              public void run() {
                                  String userId = "1";
                                  String title = "自问自答";

                                  RongIM.getInstance().startPrivateChat(CustomMainActivity.this, userId, title);
                              }
                          }
            );
        }
    });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()){
            case R.id.searchItem:
                startActivity(new Intent().setClass(this, SearchActivity.class));
            break;
        }

        ;

        return super.onOptionsItemSelected(item);
    }

    //my tab click methods
    public void growthClick(View v){

    }
    public void postWantClick(View v){
        startActivity(new Intent().setClass(this, CustomPostWantActivity.class));
    }
    public void settingClick(View v){
        startActivity(new Intent().setClass(this, SettingActivity.class));
    }
    public void feedbackClick(View v){
        startActivity(new Intent().setClass(this, CustomFeedbackActivity.class));
    }
    public void  aboutClick(View v){
        startActivity(new Intent().setClass(this, AboutActivity.class));
    }
    public void logoutClick(View v){
    }

    //discovery tab click methods
    public void menuClick(View v){

        toggleVisiable(View.INVISIBLE);
        ServiceSecondMenuItemFragment f = new ServiceSecondMenuItemFragment();
        FrameLayout fl = (FrameLayout)findViewById(R.id.menu_container);
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();

        Bundle b = new Bundle();

        int id = v.getId();

        switch (id){
            case R.id.layout_rent:
                b.putInt("type", 0);
                toggleColor(0);
                break;
            case R.id.layout_live:
                b.putInt("type", 1);
                toggleColor(1);
                break;
            case R.id.layout_appliance:
                b.putInt("type", 2);
                toggleColor(2);
                break;
            case R.id.layout_furniture:
                b.putInt("type", 3);
                toggleColor(3);
                break;
            case R.id.layout_life:
                b.putInt("type", 4);
                toggleColor(4);
                break;
        }

        f.setArguments(b);
        transaction.replace(R.id.menu_container, f);
        // 把当前Fragment添加至回退栈，通过返回键返回时可以导航到上一个Fragment状态
        transaction.addToBackStack(null);
        // 提交
        transaction.commit();
    }
    //
    private void toggleVisiable(int v){
        findViewById(R.id.rent_hint).setVisibility(v);
        findViewById(R.id.rent_arrow).setVisibility(v);
        findViewById(R.id.life_hint).setVisibility(v);
        findViewById(R.id.life_arrow).setVisibility(v);
        findViewById(R.id.live_hint).setVisibility(v);
        findViewById(R.id.live_arrow).setVisibility(v);
        findViewById(R.id.appliance_hint).setVisibility(v);
        findViewById(R.id.appliance_arrow).setVisibility(v);
        findViewById(R.id.furniture_hint).setVisibility(v);
        findViewById(R.id.furniture_arrow).setVisibility(v);
    }

    private void toggleColor(int p){
        if(p==0)
            findViewById(R.id.layout_rent).setBackgroundColor(0xfff9b294);
        else
            findViewById(R.id.layout_rent).setBackgroundColor(0xffffffff);
        if(p==1)
            findViewById(R.id.layout_live).setBackgroundColor(0xfff67280);
        else
            findViewById(R.id.layout_live).setBackgroundColor(0xffffffff);
        if(p==2)
            findViewById(R.id.layout_appliance).setBackgroundColor(0xffc16b84);
        else
            findViewById(R.id.layout_appliance).setBackgroundColor(0xffffffff);
        if(p==3)
            findViewById(R.id.layout_furniture).setBackgroundColor(0xff6e5d7f);
        else
            findViewById(R.id.layout_furniture).setBackgroundColor(0xffffffff);
        if(p==4)
            findViewById(R.id.layout_life).setBackgroundColor(0xff355c7d);
        else
            findViewById(R.id.layout_life).setBackgroundColor(0xffffffff);

    }

}

