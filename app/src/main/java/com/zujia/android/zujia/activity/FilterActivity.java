package com.zujia.android.zujia.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Switch;

import com.zujia.android.zujia.R;

public class FilterActivity extends Activity {

    private boolean certification;
    private boolean elevator;
    private boolean decoration;
    private int rooms;
    private int min;
    private int max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        certification = false;
        elevator = false;
        decoration = false;
        rooms = 0;
        min = 0;
        max = 0;

        setContentView(R.layout.activity_filter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
  //          return true;
  //      }

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

    public void certificationClick(View view){
        certification   = ((Switch)view).isChecked();
    }

    public void elevatorClick(View view){
        elevator  = ((Switch)view).isChecked();
    }

    public void decorationClick(View view){
        decoration  = ((Switch)view).isChecked();
    }

    public void submitClick(View view){
        Bundle bundle = new Bundle();
        bundle.putBoolean("certification", certification);
        bundle.putBoolean("elevator", elevator);
        bundle.putBoolean("decoration", decoration);
        bundle.putInt("rooms", rooms);
        bundle.putInt("min", min);
        bundle.putInt("max", max);

        setResult(0, new Intent().putExtras(bundle));
        finish();
        //startActivity(new Intent().setClass(this, HousesListActivity.class).putExtras(bundle));
    }

    public void resetClick(View view){
        certification = false;
        elevator = false;
        decoration = false;
        rooms = 0;
        min = 0;
        max = 0;
    }
}
