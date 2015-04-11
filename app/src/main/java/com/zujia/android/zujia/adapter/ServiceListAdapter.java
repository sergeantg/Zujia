package com.zujia.android.zujia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.zujia.android.zujia.R;
import com.zujia.android.zujia.model.ServiceInfo;

import java.util.List;

/**
 * Created by sergeantg_local on 2015/4/11.
 */
public class ServiceListAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    public List<ServiceInfo> list;

    public ServiceListAdapter(Context context, List<ServiceInfo> l){
        this.list = l;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh = null;
        if(convertView == null || convertView.getTag() == null){
            convertView = mInflater.inflate(R.layout.item_service_list, parent, false);
            vh = new ViewHolder();
            vh.title = (TextView)convertView.findViewById(R.id.serviceTitle);
            vh.des = (TextView)convertView.findViewById(R.id.serviceDescription);
            vh.logo = (ImageView)convertView.findViewById(R.id.serviceLogo);
            vh.rate = (RatingBar)convertView.findViewById(R.id.serviceRate);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder)convertView.getTag();
        }

        vh.title.setText(list.get(position).getName());
        vh.des.setText(list.get(position).getDescription());
        //vh.logo.set
        vh.rate.setRating(list.get(position).getRate());

        return convertView;
    }

    private static class ViewHolder{
        public TextView title;
        public TextView des;
        public ImageView logo;
        public RatingBar rate;
    }
}
