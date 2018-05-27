package com.oligark.flashapp.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oligark.flashapp.R;

import jp.wasabeef.blurry.Blurry;

public class ServiceDetailFragment extends Fragment {
    public ServiceDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_service, container, false);
        final View contentView = rootView.findViewById(R.id.content);

        rootView.post(new Runnable() {
            @Override
            public void run() {
                Blurry.with(getActivity()).radius(25).sampling(2).onto((ViewGroup) contentView);
            }
        });
        return rootView;
    }
}
