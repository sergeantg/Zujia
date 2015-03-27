package com.zujia.android.zujia.activity.custom;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;

import com.zujia.android.zujia.activity.LoginActivity;
import com.zujia.android.zujia.activity.SettingActivity;
import com.zujia.android.zujia.R;


public class CustomMainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_main);

        // 获取TabHost对象
        final TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        // 如果没有继承TabActivity时，通过该种方法加载启动tabHost
        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("消息", getResources().getDrawable(R.drawable.message)).setContent(
                R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("联系记录", getResources().getDrawable(R.drawable.contact)).setContent(
                R.id.tab2));
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("租家帮", getResources().getDrawable(R.drawable.zujiabang)).setContent(
                R.id.tab3));
        //tabHost.addTab(tabHost.newTabSpec("tab1")
        //        .setIndicator("我", getResources().getDrawable(R.drawable.me)).setContent(
        //        R.id.tab4));

        android.view.View view = getLayoutInflater().inflate(R.layout.custom_tab_me, null);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(view).setContent(
                        R.id.tab4));

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
            public void onTabChanged(String tabId) {

                tabHost.getTabWidget().getChildAt(0).setAlpha(255);
                tabHost.getTabWidget().getChildAt(1).setAlpha(255);
                tabHost.getTabWidget().getChildAt(2).setAlpha(255);
                tabHost.getTabWidget().getChildAt(3).setAlpha(255);
                tabHost.getCurrentTabView().setAlpha(0);
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
                startActivity(new Intent().setClass(this, LoginActivity.class));
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
    public void moveClick(View v){

    }
    public void housekeeperClick(View v){

    }
    public void otherClick(View v){

    }

}

