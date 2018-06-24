
package com.oligark.flashapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oligark.flashapp.R;
import com.oligark.flashapp.model.Service;
import com.oligark.flashapp.view.adapter.HistoryUserAdapter;
import com.oligark.flashapp.viewmodel.ServiceViewModel;

import java.util.List;


public class ServiceDetailFragment extends Fragment {
    private RecyclerView rv;
    private ServiceViewModel serviceViewModel;
    private HistoryUserAdapter adapter;
    public ServiceDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serviceViewModel = ViewModelProviders.of(this).get(ServiceViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_service, container, false);

        rv = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);
        serviceViewModel.getServiceList().observe(this, new Observer<List<Service>>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable List<Service> services) {
                adapter = new HistoryUserAdapter(services);
                rv.setAdapter(adapter);
            }

        });

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;
    }
}
