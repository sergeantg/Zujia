package com.zujia.android.zujia.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

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

        position = getIntent().getExtras().getInt("postition");
        info = ((AppContext)getApplication()).getHouseList(false, false).get(position);
        initView(info);
    }


    private void initView(HouseInfo i){
        ((TextView)findViewById(R.id.txtVTitle)).setText(i.getTitile());
        ((TextView)findViewById(R.id.txtVState)).setText(i.getState());
        ((TextView)findViewById(R.id.txtVRent)).setText(new Integer(i.getType()).toString() + new Integer(i.getTarea()).toString());
        ((TextView)findViewById(R.id.txtVLocation)).setText(i.getAddress());
        ((TextView)findViewById(R.id.txtVFloor)).setText(new Integer(i.getFloor()).toString() + "楼" + "(" + ")共" + new Integer(i.getTfloor()).toString() + "层" + i.getElevator());
        ((TextView)findViewById(R.id.txtVDate)).setText(i.getDate().toString());
        ((TextView)findViewById(R.id.txtVDecoration)).setText(i.getDecoration());
        ((TextView)findViewById(R.id.txtVAppliance)).setText(i.getAppliance());
        ((TextView)findViewById(R.id.txtVFurniture)).setText(i.getFurniture());
        ((TextView)findViewById(R.id.txtVOther)).setText(i.getOther());
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
