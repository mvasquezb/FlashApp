package com.oligark.flashapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oligark.flashapp.R;
import com.oligark.flashapp.model.ServiceCategory;
import com.oligark.flashapp.view.adapter.CategoryAdapter;
import com.oligark.flashapp.viewmodel.ServiceCategoryViewModel;

import java.util.ArrayList;
import java.util.List;


public class ServiceCategoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private  LinearLayoutManager layoutManager;
    private ServiceCategoryViewModel viewModel;
    private  CategoryAdapter categoryAdapter;
    private RecyclerView rv;

//    private OnFragmentInteractionListener mListener;

    public ServiceCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ServiceCategoryViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());

        viewModel.getCategoryList().observe(this, new Observer<List<ServiceCategory>>() {
            @Override
            public void onChanged(@Nullable List<ServiceCategory> pets) {
                categoryAdapter = new CategoryAdapter(pets);
                rv.setAdapter(categoryAdapter);
            }
        });
        rv.setLayoutManager(layoutManager);

        return rootView;
    }

}
