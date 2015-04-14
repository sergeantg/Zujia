package com.zujia.android.zujia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zujia.android.zujia.R;
import com.zujia.android.zujia.model.HouseInfo;

import java.util.List;

/**
 * Created by sergeantg_local on 2015/4/10.
 */
public class HouseListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;

    public List<HouseInfo> list;
    private Context c;

    public HouseListAdapter(Context context, List<HouseInfo> l) {
        this.list = l;

        this.mInflater = LayoutInflater.from(context);
        c = context;
    }

    //viewHolder
    static class ViewHodler {
        public TextView title;
        public TextView distance;
        public ImageView detail;
        public TextView des;
        public ImageView avater;
        public TextView money;
        public TextView date;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        //return list.size();
        return 10;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        HouseInfo t = list.get(position);
        ViewHodler holder = null;
        if (convertView == null) {

            holder = new ViewHodler();

            convertView = mInflater.inflate(R.layout.item_house_list, parent, false);

            holder.title = (TextView) convertView.findViewById(R.id.txtVTitle);
            holder.distance = (TextView) convertView.findViewById(R.id.txtVDistance);
            holder.detail = (ImageView) convertView.findViewById(R.id.imgVDetail);
            holder.avater = (ImageView) convertView.findViewById(R.id.imgVAvater);
            holder.des = (TextView) convertView.findViewById(R.id.txtVDetailDes);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            holder.money = (TextView) convertView.findViewById(R.id.money);
            convertView.setTag(holder);

        } else {

            holder = (ViewHodler) convertView.getTag();
        }

        holder.title.setText(t.getTitile());
        holder.distance.setText(t.getJuli());
        holder.detail.setImageDrawable(c.getDrawable(R.drawable.house_detail));
        holder.des.setText(t.getMiaoshu());
        //holder.avater.setImageDrawable(c.getDrawable(R.drawable.));

        return convertView;
    }
}


