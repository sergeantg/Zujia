package com.zujia.android.zujia.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zujia.android.zujia.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ZujiabangFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ZujiabangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ZujiabangFragment extends Fragment {

    private View [] vv = new View[10];//visability view
    private View [] vc = new View[5];//color view
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static ZujiabangFragment newInstance(String param1, String param2) {
        ZujiabangFragment fragment = new ZujiabangFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ZujiabangFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
