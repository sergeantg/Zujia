package com.zujia.android.zujia.customs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zujia.android.zujia.R;

public class SortActivity extends ActionBarActivity {

    //排序方式
    private int sort = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sort, menu);
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


    //响应函数
    public void defaultClick(View view){
        sort = 0;

        Bundle b = new Bundle();
        b.putInt("sort", sort);
        setResult(2, new Intent().putExtras(b));
        finish();
    }

    public void moneyClick(View view){
        sort = 1;
        Bundle b = new Bundle();
        b.putInt("sort", sort);
        setResult(2, new Intent().putExtras(b));
        finish();
    }

    public void distanceClick(View view){
        sort =2;
        Bundle b = new Bundle();
        b.putInt("sort", sort);
        setResult(2, new Intent().putExtras(b));
        finish();
    }

}
