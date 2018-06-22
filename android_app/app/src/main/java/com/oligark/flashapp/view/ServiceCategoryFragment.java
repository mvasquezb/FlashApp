package com.oligark.flashapp.view;

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
import com.oligark.flashapp.model.CategoryService;
import com.oligark.flashapp.view.adapter.CategoryAdapter;

import java.util.ArrayList;


public class ServiceCategoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private  LinearLayoutManager layoutManager;
    private  ArrayList<CategoryService> categories;
    private  CategoryAdapter categoryAdapter;
    private RecyclerView recyclerView;

//    private OnFragmentInteractionListener mListener;

    public ServiceCategoryFragment() {
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);

        categories = new ArrayList<>();
        categories.add(new CategoryService("Bano", "Bano",1 )  );
        categories.add(new CategoryService("Paseo", "Paseo", 2)  );
        categories.add(new CategoryService("Peluqueria", "Peluqueria", 3)  );
        categories.add(new CategoryService("Otros", "Otros" , 4)  );

        CategoryAdapter adapter = new CategoryAdapter(categories);
        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;
    }

}
