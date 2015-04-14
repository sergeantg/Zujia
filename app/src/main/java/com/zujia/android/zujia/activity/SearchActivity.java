package com.zujia.android.zujia.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.zujia.android.zujia.AppContext;
import com.zujia.android.zujia.R;
import com.zujia.android.zujia.service.RestApi;

public class SearchActivity extends Activity {

    private int rooms;
    private String key;
    private AppContext ac;
    private MyLocationListener mLoListener;
    private LocationClient mLocationClient;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ac = (AppContext)getApplication();
        mLoListener = new MyLocationListener();
        rooms = 1;
        editText = (EditText)findViewById(R.id.editText_search_key);

        mLocationClient = ac.mLocationClient;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void radioButtonClick(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_one_room:
                if (checked)
                    rooms = 1;
                    break;
            case R.id.radio_two_room:
                if (checked)
                    rooms = 2;
                    break;
            case R.id.radio_three_room:
                if (checked)
                    rooms = 3;
                    break;
            case R.id.radio_more_room:
                if (checked)
                    rooms = 4;
                    break;
        }
    }

    /**关键词搜索
     *
     * @param view
     */
    public void searchClick(View view){
        key = editText.getText().toString();
        if(key == null || "".equals(key)){
            editText.setError("请输入正确地名");
        }else{
            ac.condition.reset();
            ac.condition.key = key;
            ac.condition.rooms = rooms;
            new GetDataTask(false, true).execute();
        }
    }

    /**搜索附近房屋
     *
     * @param view
     */
    public void aroundClick(View view){

        mLocationClient.registerLocationListener(mLoListener);
        mLocationClient.start();

        mLocationClient.requestLocation();
        Toast.makeText(getApplicationContext(), "aroundclick",
                Toast.LENGTH_SHORT).show();
    }

    /**
     * 实现定位回调监听
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            ac.condition.reset();
            if(location.getAddrStr() != null)
                ac.condition.key = location.getAddrStr();

            new GetDataTask(false, true).execute();

            Toast.makeText(getApplicationContext(), "you location:" + location.getAddrStr(),
                    Toast.LENGTH_SHORT).show();
            ac.mLocationClient.unRegisterLocationListener(mLoListener);
            ac.mLocationClient.stop();
        }
    }
    /**
     * 异步搜索任务
     *
     */
    private class GetDataTask extends AsyncTask<Void, Void , Integer> {
        private boolean add;
        private boolean isRefresh;
        GetDataTask(boolean add, boolean isRefresh){
            this.add = add;
            this.isRefresh = isRefresh;
        }
        @Override
        protected Integer doInBackground(Void... params){
            ((AppContext)getApplication()).getHouseList(add, isRefresh);
            startActivity(new Intent().setClass(SearchActivity.this, HousesListActivity.class));
            return 1;
        }
    }

    /**
     * DEBUG
     */
    private void Douban(){
        RestApi api = new RestApi();
        com.zujia.android.zujia.model.DoubanTest t = new com.zujia.android.zujia.model.DoubanTest();
        t = api.douban();
        Toast.makeText(this, t.getTags().get(0).getTitle(), Toast.LENGTH_SHORT).show();
    }


}
