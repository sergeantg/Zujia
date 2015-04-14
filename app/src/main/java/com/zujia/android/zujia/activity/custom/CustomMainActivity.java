package com.zujia.android.zujia.activity.custom;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zujia.android.zujia.AppContext;
import com.zujia.android.zujia.R;
import com.zujia.android.zujia.activity.LoginActivity;
import com.zujia.android.zujia.activity.SearchActivity;
import com.zujia.android.zujia.activity.ServiceSecondMenuItemFragment;
import com.zujia.android.zujia.activity.SettingActivity;
import com.zujia.android.zujia.model.PersonalInfo;


public class CustomMainActivity extends FragmentActivity {

    private View [] vv = new View[10];//visability view
    private View [] vc = new View[5];//color view
    private ImageView [] iv = new ImageView[4];
    private TextView title;
    private View titleLeftBtn;
    private View acbar;
    private FrameLayout fl;
    private int lastPressedItem = 0;
    private PersonalInfo pi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getIntent().setData(Uri.parse("rong://com.zujia.android.zujia").buildUpon()
                .appendPath("conversationlist").build());
        setContentView(R.layout.activity_custom_main);
        initView();
        initData();
    }

    private void initView(){

        vv[0] = findViewById(R.id.rent_hint);
        vv[1] = findViewById(R.id.rent_arrow);
        vv[2] = findViewById(R.id.life_hint);
        vv[3] = findViewById(R.id.life_arrow);
        vv[4] = findViewById(R.id.live_hint);
        vv[5] = findViewById(R.id.live_arrow);
        vv[6] = findViewById(R.id.appliance_hint);
        vv[7] = findViewById(R.id.appliance_arrow);
        vv[8] = findViewById(R.id.furniture_hint);
        vv[9] = findViewById(R.id.furniture_arrow);
        vc[0] = findViewById(R.id.layout_rent);
        vc[1] = findViewById(R.id.layout_live);
        vc[2] = findViewById(R.id.layout_appliance);
        vc[3] = findViewById(R.id.layout_furniture);
        vc[4] = findViewById(R.id.layout_life);

        title = (TextView)findViewById(R.id.ivTitleName);
        titleLeftBtn = findViewById(R.id.ivTitleBtnLeft);
        fl = (FrameLayout)findViewById(R.id.menu_container);
        acbar = findViewById(R.id.layout_top);
        final TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        titleLeftBtn.setVisibility(View.GONE);

        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec("0")
                .setIndicator(getLayoutInflater().inflate(R.layout.custom_tab_message, null))
                .setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("1")
                .setIndicator(getLayoutInflater().inflate(R.layout.custom_tab_contact, null))
                .setContent(R.id.tab2));
        tabHost.addTab(tabHost.newTabSpec("2")
                .setIndicator(getLayoutInflater().inflate(R.layout.custom_tab_zujiabang, null))
                .setContent(R.id.tab3));
        tabHost.addTab(tabHost.newTabSpec("3")
                .setIndicator(getLayoutInflater().inflate(R.layout.custom_tab_me, null))
                .setContent(R.id.tab4));

        iv[0] = (ImageView)findViewById(R.id.imgV_tab_message);
        iv[1] = (ImageView)findViewById(R.id.imgV_tab_contact);
        iv[2] = (ImageView)findViewById(R.id.imgV_tab_zujiabang);
        iv[3] = (ImageView)findViewById(R.id.imgV_tab_me);



        for(ImageView i:iv){
            i.setImageAlpha(40);
        }

        tabHost.setCurrentTab(0);
        iv[0].setImageAlpha(255);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                ActionBar b = getActionBar();

                for(ImageView i:iv)
                    i.setImageAlpha(40);
                titleLeftBtn.setVisibility(View.GONE);
                toggleVisiable(false, 0, 0);
                lastPressedItem = -1;

                //当前选项卡点亮
                iv[new Integer(tabId)].setImageAlpha(255);

                switch (tabId){
                    case "0":
                        //b.setTitle(R.string.message_main);
                        title.setText(R.string.message_main);
                        acbar.setVisibility(View.VISIBLE);
                        break;
                    case "1":
                        //b.setTitle(R.string.contact_main);
                        title.setText(R.string.contact_main);
                        acbar.setVisibility(View.VISIBLE);
                        break;
                    case "2":
                        //b.setTitle(R.string.zujiabang_main);
                        title.setText(R.string.zujiabang_main);
                        acbar.setVisibility(View.VISIBLE);
                        break;
                    case "3":
                        //b.setTitle(R.string.me_main);
                        title.setText(R.string.me_main);
                        acbar.setVisibility(View.GONE);
                        break;
                }
            }
        });


    }

    private void initData(){
        new GetPersonalInfoTask((TextView)findViewById(R.id.txtVName), (ImageView)findViewById(R.id.imgVAvater), this).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()){
            case R.id.searchItem:
                startActivity(new Intent().setClass(this, SearchActivity.class));
            break;
        }

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
        ((AppContext)getApplication()).logout();
        startActivity(new Intent().setClass(this, LoginActivity.class));
    }

    //discovery tab click methods
    public void menuClick(View v){

        int id = v.getId();
        int type = 0;
        int color = 0;
        boolean open = false;

        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        if(lastPressedItem == id){//隐藏fragment,显示vv,重置lastPressedItem
            color = getResources().getColor(R.color.white);
            lastPressedItem = -1;
            open = false;
        }else{
            switch (id){
                case R.id.layout_rent:
                    type = 0;
                    color = getResources().getColor(R.color.color1);
                    break;
                case R.id.layout_live:
                    type = 1;
                    color = getResources().getColor(R.color.color2);
                    break;
                case R.id.layout_appliance:
                    type = 2;
                    color = getResources().getColor(R.color.color3);
                    break;
                case R.id.layout_furniture:
                    type = 3;
                    color = getResources().getColor(R.color.color4);
                    break;
                case R.id.layout_life:
                    type = 4;
                    color = getResources().getColor(R.color.color5);
                    break;
            }
            open = true;
            lastPressedItem = id;
            ServiceSecondMenuItemFragment f =
                    ServiceSecondMenuItemFragment.newInstance(type, color);
            transaction.replace(R.id.menu_container, f);
            transaction.commit();
        }

        toggleVisiable(open, type, color);
    }

    public void cameraClick(View view){

    }

    public void writeClick(View view){

    }

    public void leftBtnClick(View v){
        toggleVisiable(false, 0, 0);
    }
    //
    private void toggleVisiable(boolean open, int p, int color){
        if(open){
            for(View i:vv) {
                i.setVisibility(View.GONE);
            }
            titleLeftBtn.setVisibility(View.VISIBLE);
            fl.setVisibility(View.VISIBLE);
            //设置颜色
            for(View i:vc){
                i.setBackgroundColor(0xffffffff);
            }
            vc[p].setBackgroundColor(color);
        }else{
            for(View i:vv) {
                i.setVisibility(View.VISIBLE);
            }
            titleLeftBtn.setVisibility(View.GONE);
            fl.setVisibility(View.GONE);
            for(View i:vc){
                i.setBackgroundColor(0xffffffff);
            }
        }
    }

    private class GetPersonalInfoTask extends AsyncTask<Void, Void, Void>{

        private TextView name;
        private ImageView avater;
        private Context context;

        public GetPersonalInfoTask(TextView n, ImageView a, Context c){
            this.name = n;
            this.avater = a;
            this.context = c;
        }
        @Override
        protected Void doInBackground(Void... params) {
            pi = ((AppContext)getApplication()).getPersonalInfo(true);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            name.setText(pi.getUsername());
            Picasso.with(context).load(pi.getAvater()).into(avater);
        }
    }
}

