package com.zujia.android.zujia.activity.custom;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zujia.android.zujia.R;
import com.zujia.android.zujia.customs.SortActivity;
import com.zujia.android.zujia.model.HouseInfo;
import com.zujia.android.zujia.model.SearchCondition;
import com.zujia.android.zujia.service.RestApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HousesListActivity extends ListActivity {

    private MyAdapter adapter;

    private SearchCondition condition = new SearchCondition();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_houses_list);

        //搜索参数初始化
        Bundle bundle = getIntent().getExtras();
        condition.key = bundle.getString("search_key");
        condition.rooms = bundle.getInt("rooms");

        //搜索
        search();

       // adapter = new SimpleAdapter(this, getData(), R.layout.activity_houses_list,
        //        new String[]{"txtVTitle", "txtVDistance", "imgVDetail", "txtVPattern", "txtVElevator", "txtVArea", "txtVDecoration", "imgVLandlordAvater"}, new int[]{R.id.textView17, R.id.imageView6});
        setListAdapter(adapter);
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
                case 0://筛选

                    if (b.getBoolean("certification"))
                        condition.certification = 1;
                    if (b.getBoolean("elevator"))
                        condition.elevator = 1;
                    if (b.getBoolean("decoration"))
                        condition.decoration = 1;
                    condition.rooms = b.getInt("rooms");
                    condition.min = b.getInt("min");
                    condition.max = b.getInt("max");

                    //搜索房屋
                    search();
                    break;
                case 1://商圈
                    break;
                case 2://排序
                    condition.sort = b.getInt("sort");
                    search();
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


    //viewHolder
    public final class ViewHodler {
        public TextView title;
        public TextView distance;
        public ImageView detail;
        public TextView elevator;
        public TextView decoration;
        public TextView pattern;
        public TextView area;
        public ImageView avater;

    }

    private class MyAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        public List<HouseInfo> list;

        public MyAdapter(Context context) {

            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return list.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHodler holder = null;
            if (convertView == null) {

                holder = new ViewHodler();

                convertView = mInflater.inflate(R.layout.activity_houses_list, null);

                holder.title = (TextView) convertView.findViewById(R.id.txtVTitle);
                holder.distance = (TextView) convertView.findViewById(R.id.txtVDistance);
                holder.detail = (ImageView) convertView.findViewById(R.id.imgVDetail);
                holder.elevator = (TextView) convertView.findViewById(R.id.txtVElevetor);
                holder.pattern = (TextView) convertView.findViewById(R.id.txtVPattern);
                holder.area = (TextView) convertView.findViewById(R.id.txtVArea);
                holder.decoration = (TextView) convertView.findViewById(R.id.txtVDecoration);
                holder.avater = (ImageView) convertView.findViewById(R.id.imgVLandlordAvater);
                convertView.setTag(holder);

            } else {

                holder = (ViewHodler) convertView.getTag();
            }

            //holder.title.setText(list.get().);

            return convertView;
        }
    }

    //异步搜索任务
    private class SearchTask extends AsyncTask<Void, Void , List<HouseInfo>>{

        private final SearchCondition mc;
        private RestApi service;

        SearchTask(SearchCondition c){
            mc = c;
            service = new RestApi();
        }

        @Override
        protected List<HouseInfo> doInBackground(Void... params){
            return service.search(mc);
        }

        @Override
        protected void onPostExecute(List<HouseInfo> l){

        }
    }
    private void search(){

    }
}
