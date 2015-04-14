package com.zujia.android.zujia.ui.custom;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zujia.android.zujia.AppContext;
import com.zujia.android.zujia.R;
import com.zujia.android.zujia.ui.LoginActivity;
import com.zujia.android.zujia.ui.SettingActivity;

/**
 */
public class CustomMeFragment extends Fragment implements View.OnClickListener {


    public CustomMeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View temp = inflater.inflate(R.layout.fragment_custom_me, container, false);
        temp.findViewById(R.id.growth).setOnClickListener(this);
        temp.findViewById(R.id.postWant).setOnClickListener(this);
        temp.findViewById(R.id.logout).setOnClickListener(this);
        temp.findViewById(R.id.setting).setOnClickListener(this);
        temp.findViewById(R.id.feedback).setOnClickListener(this);
        temp.findViewById(R.id.about).setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.growth:
                break;
            case R.id.postWant:
                startActivity(new Intent().setClass(getActivity(), CustomPostWantActivity.class));
                break;
            case R.id.logout:
                ((AppContext)(getActivity().getApplication())).logout();
                startActivity(new Intent().setClass(getActivity(), LoginActivity.class));
                break;
            case R.id.setting:
                startActivity(new Intent().setClass(getActivity(), SettingActivity.class));
                break;
            case R.id.feedback:
                startActivity(new Intent().setClass(getActivity(), CustomFeedbackActivity.class));
                break;
            case R.id.about:
                startActivity(new Intent().setClass(getActivity(), AboutActivity.class));
                break;
        }
    }

}
