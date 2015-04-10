package com.zujia.android.zujia.activity;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.zujia.android.zujia.AppContext;
import com.zujia.android.zujia.R;
import com.zujia.android.zujia.service.RestApi;

public class SearchActivity extends Activity {

    private int rooms;
    private String key;
    private AppContext ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ac = (AppContext)getApplication();
        rooms = 0;

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
        ac.condition.key = key;
        ac.condition.rooms = rooms;

        new GetDataTask(false, true).execute();//异步获取houselist

        startActivity(new Intent().setClass(this, HousesListActivity.class));
    }

    /**搜索附近房屋
     *
     * @param view
     */
    public void aroundClick(View view){

        AppContext ac = (AppContext)getApplication();
        Location l;

        if(ac.getGPSSettings()){
            l = ac.getLocation();
            if(l != null){
                ac.condition.latitude = l.getLatitude();
                ac.condition.longitude = l.getLongitude();

                new GetDataTask(false, true).execute();//异步获取houselist

                startActivity(new Intent().setClass(this, HousesListActivity.class));
            }
            Toast.makeText(this, "无法定位", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "请开启GPS！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
            startActivityForResult(intent,0); //此为设置完成后返回到获取界面
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
