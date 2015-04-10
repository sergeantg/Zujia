package com.zujia.android.zujia.activity;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zujia.android.zujia.R;

import java.util.ArrayList;
import java.util.List;

public class ServiceSecondMenuItemFragment extends ListFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TYPE = "type";

    private int type;

    // TODO: Rename and change types of parameters
    public static ServiceSecondMenuItemFragment newInstance(int type) {
        ServiceSecondMenuItemFragment fragment = new ServiceSecondMenuItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ServiceSecondMenuItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            type = getArguments().getInt(ARG_TYPE);
        }

        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.rent_service)));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    public void setType(int t){
        this.type = t;
    }

    private List<String> getData(int t){
        List<String> l = new ArrayList<String>();

        switch (t){
            case 0:
                l.add("1");
                l.add("2");
                break;
            case 1:
                l.add("a");
                l.add("b");
                break;
        }
        return l;
    }
}
