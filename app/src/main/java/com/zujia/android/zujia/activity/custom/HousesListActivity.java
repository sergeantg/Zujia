package com.zujia.android.zujia.activity.custom;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleAdapter;

import com.zujia.android.zujia.R;
import com.zujia.android.zujia.customs.SortActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HousesListActivity extends ListActivity {

    private SimpleAdapter adapter;

    private String key;
    private boolean certification;
    private boolean elevator;
    private boolean decoration;
    private int rooms;
    private int min;
    private int max;
    private int sort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_houses_list);

        //搜索参数初始化
        Bundle bundle = getIntent().getExtras();
        key = bundle.getString("search_key");
        rooms = bundle.getInt("rooms");

        //搜索
        search();

        //adapter = new SimpleAdapter(this, getData(), R.layout.activity_houses_list, new String[] { "textView17",  "imageView6" }, new int[] { R.id.textView17, R.id.imageView6 });
       // setListAdapter(adapter);
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
        switch (item.getItemId()){
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

        if(resultCode == RESULT_OK){
            Bundle b = data.getExtras(); //data为B中回传的Intent

            switch(requestCode){
                case 0://筛选
                    certification = b.getBoolean("certification");
                    elevator = b.getBoolean("elevator");
                    decoration = b.getBoolean("decoration");
                    rooms = b.getInt("rooms");
                    min = b.getInt("min");
                    max = b.getInt("max");

                    //搜索房屋
                    search();
                    break;
                case 1://商圈
                    break;
                case 2://排序
                    sort = b.getInt("sort");
                    search();
                    break;

            }

        }
    }

    private List<Map<String, Object>> getData(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("textView17", "how");
        map.put("imageView6", R.drawable.   avater);
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

    private void search(){

    }
}
