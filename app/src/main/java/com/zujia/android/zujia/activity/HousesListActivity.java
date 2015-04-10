package com.zujia.android.zujia.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.zujia.android.zujia.AppContext;
import com.zujia.android.zujia.R;
import com.zujia.android.zujia.adapter.HouseListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.trinea.android.common.view.DropDownListView;


public class HousesListActivity extends Activity {

    private HouseListAdapter adapter;
    private AppContext ac = (AppContext)getApplication();
    private DropDownListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_houses_list);

        adapter = new HouseListAdapter(this, ac.getHouseList(false, false));

        lv = (DropDownListView)findViewById(R.id.houseList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle b = new Bundle();
                b.putInt("position", position);
                startActivity(new Intent().setClass(getApplicationContext(), HouseDetailsActivity.class).putExtras(b));
            }
        });

        //滑到底部刷新
        lv.setOnBottomListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetDataTask(true, false).execute();
                adapter.list.clear();
                adapter.list.addAll(ac.getHouseList(false, false));
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_houses_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_filter:
                startActivityForResult(new Intent().setClass(this, FilterActivity.class), 0);
                break;
            case R.id.action_location_select:
                break;
            case R.id.action_sort:
                startActivityForResult(new Intent().setClass(this, SortActivity.class), 2);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            Bundle b = data.getExtras(); //data为B中回传的Intent

            switch (requestCode) {
                case 0://筛选,重新搜索
                    if (b.getBoolean("certification"))
                        ac.condition.certification = 1;
                    if (b.getBoolean("elevator"))
                        ac.condition.elevator = 1;
                    if (b.getBoolean("decoration"))
                        ac.condition.decoration = 1;
                    ac.condition.rooms = b.getInt("rooms");
                    ac.condition.min = b.getInt("min");
                    ac.condition.max = b.getInt("max");

                    new GetDataTask(false, true).execute();//重新异步搜索
                    adapter.list.clear();
                    adapter.list.addAll(ac.getHouseList(false, false));
                    adapter.notifyDataSetChanged();
                    break;
                case 1://商圈
                    break;
                case 2://排序
                    ac.condition.sort = b.getInt("sort");

                    new GetDataTask(false, true).execute();//重新异步搜索

                    adapter.list.clear();
                    adapter.list.addAll(ac.getHouseList(false, false));
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("textView17", "how");
        map.put("imageView6", R.drawable.avater);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("textView17", "are");
        map.put("imageView6", R.drawable.avater);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("textView17", "you");
        map.put("imageView6", R.drawable.avater);
        list.add(map);

        return list;
    }



    //异步搜索任务
    private class GetDataTask extends AsyncTask<Void, Void , Integer>{
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
}
