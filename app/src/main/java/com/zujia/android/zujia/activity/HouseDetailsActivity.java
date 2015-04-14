package com.zujia.android.zujia.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zujia.android.zujia.AppContext;
import com.zujia.android.zujia.R;
import com.zujia.android.zujia.model.HouseInfo;

public class HouseDetailsActivity extends Activity {

    private int position;
    private HouseInfo info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);

        position = getIntent().getExtras().getInt("position");
        info = ((AppContext)getApplication()).getHouseList(false, false).get(position);
        initView(info);
    }


    private void initView(HouseInfo i){


        Picasso.with(this).load(i.getAppliance()).into((ImageView)findViewById(R.id.zhaopian));
        ((TextView) findViewById(R.id.txtVLocation)).setText(i.getDizhi());

        ((TextView)findViewById(R.id.txtVTitle)).setText(i.getTitile());
        ((TextView)findViewById(R.id.txtVState)).setText(i.getState());
        ((TextView)findViewById(R.id.txtVSize)).setText(i.getFangxing());
        ((TextView)findViewById(R.id.txtVRent)).setText(i.getChuzu());
        ((TextView)findViewById(R.id.txtVFloor)).setText(i.getLouceng());
        ((TextView)findViewById(R.id.txtVDate)).setText(i.getZuizaoruzhu());
        ((TextView)findViewById(R.id.txtVDecoration)).setText(i.getZhuangxiuqingkuang());
        ((TextView)findViewById(R.id.txtVAppliance)).setText(i.getJiadian());
        ((TextView)findViewById(R.id.txtVFurniture)).setText(i.getJiaju());
        ((TextView)findViewById(R.id.txtVOther)).setText(i.getQita());
        ((TextView)findViewById(R.id.txtVDescribtion)).setText(i.getDescription());


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_house_details, menu);
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
}
