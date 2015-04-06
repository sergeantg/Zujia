package com.zujia.android.zujia.activity.custom;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import com.zujia.android.zujia.R;
import com.zujia.android.zujia.service.RestApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HousesListActivity extends ListActivity {

    private String key;
    private int rooms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_houses_list);

        Bundle bundle = getIntent().getExtras();
        key = bundle.getString("search_key");
        rooms = bundle.getInt("rooms");

        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.activity_houses_list, new String[] { "textView17",  "imageView6" }, new int[] { R.id.textView17, R.id.imageView6 });
        setListAdapter(adapter);
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
}
