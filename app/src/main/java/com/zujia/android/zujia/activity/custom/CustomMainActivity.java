package com.zujia.android.zujia.activity.custom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;

import com.zujia.android.zujia.R;
import com.zujia.android.zujia.activity.SettingActivity;


public class CustomMainActivity extends Activity {

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

                //tabHost.getTabWidget().getChildAt(0).setAlpha(255);

               // tabHost.getCurrentTabView();
                //findViewById(R.id.imgV_tab_message).setAlpha(255);
                //findViewById(R.id.imgV_tab_contact).setAlpha(255);
                //findViewById(R.id.imgV_tab_zujiabang).setAlpha(255);
                findViewById(R.id.imgV_tab_me).setAlpha(255);
                findViewById(R.id.txtV_tab_me).setAlpha(255);
                Log.i("test", tabId);
                switch(tabId){

                    case "0":
                       // findViewById(R.id.imgV_tab_message).setAlpha(0);

                        break;
                    case "1":
                        //findViewById(R.id.imgV_tab_contact).setAlpha(0);
                        break;
                    case "2":
                        //findViewById(R.id.imgV_tab_zujiabang).setAlpha(0);
                        break;
                    case "3":
                        findViewById(R.id.imgV_tab_me).setAlpha(100);
                        findViewById(R.id.txtV_tab_me).setAlpha(100);
                        break;

                }
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
    public void moveClick(View v){

    }
    public void housekeeperClick(View v){

    }
    public void otherClick(View v){

    }

}

