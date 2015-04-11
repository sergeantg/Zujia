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
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.zujia.android.zujia.AppContext;
import com.zujia.android.zujia.R;
import com.zujia.android.zujia.service.RestApi;

public class SearchActivity extends Activity {

    private int rooms;
    private String key;
    private AppContext ac;
    private MyLocationListener mLoListener;
    private GeoCoder mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ac = (AppContext)getApplication();
        mLoListener = new MyLocationListener();
        mSearch = GeoCoder.newInstance();
        rooms = 0;

        //设置地理位置编码回调
        mSearch.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                ac.condition.reset();
                ac.condition.latitude = geoCodeResult.getLocation().latitude;
                ac.condition.longitude = geoCodeResult.getLocation().longitude;
                //new GetDataTask(false, true).execute();
                Toast.makeText(getApplicationContext(), geoCodeResult.getAddress() + geoCodeResult.getLocation().latitude,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
        key = ((EditText)findViewById(R.id.editText_search_key)).getText().toString();

        key = "南开大学";
        ac.condition.reset();
        ac.condition.rooms = rooms;
        mSearch.geocode(new GeoCodeOption()
                .city("天津").address(key));
    }

    /**搜索附近房屋
     *
     * @param view
     */
    public void aroundClick(View view){

        ac.mLocationClient.registerLocationListener(mLoListener);

        ac.mLocationClient.requestLocation();
    }

    /**
     * 实现定位回调监听
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            ac.mLocationClient.unRegisterLocationListener(mLoListener);
            ac.condition.reset();
            ac.condition.latitude = location.getLatitude();
            ac.condition.longitude = location.getLongitude();
            new GetDataTask(false, true).execute();
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

    private void Douban(){
        RestApi api = new RestApi();
        com.zujia.android.zujia.model.DoubanTest t = new com.zujia.android.zujia.model.DoubanTest();
        t = api.douban();
        Toast.makeText(this, t.getTags().get(0).getTitle(), Toast.LENGTH_SHORT).show();
    }


}
