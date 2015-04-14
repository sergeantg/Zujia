package com.zujia.android.zujia.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zujia.android.zujia.R;

/**
 */
public class ZujiabangFragment extends Fragment implements View.OnClickListener {

    private View [] vv = new View[10];//visability view
    private View [] vc = new View[5];//color view


    public ZujiabangFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View temp = inflater.inflate(R.layout.fragment_zujiabang, container, false);;
        vv[0] = container.findViewById(R.id.rent_hint);
        vv[1] = container.findViewById(R.id.rent_arrow);
        vv[2] = container.findViewById(R.id.life_hint);
        vv[3] = container.findViewById(R.id.life_arrow);
        vv[4] = container.findViewById(R.id.live_hint);
        vv[5] = container.findViewById(R.id.live_arrow);
        vv[6] = container.findViewById(R.id.appliance_hint);
        vv[7] = container.findViewById(R.id.appliance_arrow);
        vv[8] = container.findViewById(R.id.furniture_hint);
        vv[9] = container.findViewById(R.id.furniture_arrow);
        vc[0] = container.findViewById(R.id.layout_rent);
        vc[1] = container.findViewById(R.id.layout_live);
        vc[2] = container.findViewById(R.id.layout_appliance);
        vc[3] = container.findViewById(R.id.layout_furniture);
        vc[4] = container.findViewById(R.id.layout_life);
        // Inflate the layout for this fragment
        return temp;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    //discovery tab click methods
    public void menuClick(View v){

        int id = v.getId();
        int type = 0;
        int color = 0;
        boolean open = false;

        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        if(lastPressedItem == id){//隐藏fragment,显示vv,重置lastPressedItem
            color = getResources().getColor(R.color.white);
            lastPressedItem = -1;
            open = false;
        }else{
            switch (id){
                case R.id.layout_rent:
                    type = 0;
                    color = getResources().getColor(R.color.color1);
                    break;
                case R.id.layout_live:
                    type = 1;
                    color = getResources().getColor(R.color.color2);
                    break;
                case R.id.layout_appliance:
                    type = 2;
                    color = getResources().getColor(R.color.color3);
                    break;
                case R.id.layout_furniture:
                    type = 3;
                    color = getResources().getColor(R.color.color4);
                    break;
                case R.id.layout_life:
                    type = 4;
                    color = getResources().getColor(R.color.color5);
                    break;
            }
            open = true;
            lastPressedItem = id;
            ServiceSecondMenuItemFragment f =
                    ServiceSecondMenuItemFragment.newInstance(type, color);
            transaction.replace(R.id.menu_container, f);
            transaction.commit();
        }

        toggleVisiable(open, type, color);
    }

    @Override
    public void onClick(View v) {

    }

    private void toggleVisiable(boolean open, int p, int color){
        if(open){
            for(View i:vv) {
                i.setVisibility(View.GONE);
            }
            titleLeftBtn.setVisibility(View.VISIBLE);
            fl.setVisibility(View.VISIBLE);
            //设置颜色
            for(View i:vc){
                i.setBackgroundColor(0xffffffff);
            }
            vc[p].setBackgroundColor(color);
        }else{
            for(View i:vv) {
                i.setVisibility(View.VISIBLE);
            }
            titleLeftBtn.setVisibility(View.GONE);
            fl.setVisibility(View.GONE);
            for(View i:vc){
                i.setBackgroundColor(0xffffffff);
            }
        }
    }
}
