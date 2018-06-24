package com.oligark.flashapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.oligark.flashapp.R;
import com.oligark.flashapp.model.Service;
import com.oligark.flashapp.view.adapter.HistoryUserAdapter;
import com.oligark.flashapp.viewmodel.ServiceViewModel;

import java.util.List;


public class UserHistoryFragment extends Fragment {

    private RecyclerView serviceListView;
    private ServiceViewModel serviceViewModel;
    private HistoryUserAdapter adapter;

    public UserHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serviceViewModel = ViewModelProviders.of(getActivity()).get(ServiceViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText(getActivity(), "History", Toast.LENGTH_SHORT).show();
        View root = inflater.inflate(R.layout.fragment_user_history, container, false);
        serviceListView = (RecyclerView) root.findViewById(R.id.historyListView);
        serviceListView.setHasFixedSize(true);
        serviceViewModel.getServiceList().observe(this, new Observer<List<Service>>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable List<Service> services) {
                adapter = new HistoryUserAdapter(services);
                serviceListView.setAdapter(adapter);
            }
        });
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        serviceListView.setLayoutManager(llm);

        return root;
    }
}
