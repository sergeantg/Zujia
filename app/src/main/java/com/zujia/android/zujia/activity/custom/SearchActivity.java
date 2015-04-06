package com.zujia.android.zujia.activity.custom;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.zujia.android.zujia.R;
import com.zujia.android.zujia.service.RestApi;

public class SearchActivity extends ActionBarActivity {

    private int rooms;
    private String key;
    private RestApi restService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rooms = 0;
        restService = new RestApi();

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

    //关键词搜索
    public void searchClick(View view){
        key = ((EditText)findViewById(R.id.editText_search_key)).getText().toString();

        //传递搜索关键字，搜索选项
        Bundle bundle = new Bundle();
        bundle.putString("search_key", key);
        bundle.putInt("rooms", rooms);

        startActivity(new Intent().setClass(this, HousesListActivity.class).putExtras(bundle));
    }

    //搜索附近房屋
    public void aroundClick(View view){
        startActivity(new Intent().setClass(this, HousesListActivity.class));
    }
}
