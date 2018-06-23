package com.oligark.flashapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.oligark.flashapp.R;
import com.oligark.flashapp.model.Pet;
import com.oligark.flashapp.view.adapter.PetListAdapter;
import com.oligark.flashapp.viewmodel.PetListViewModel;

import java.util.List;

public class PetListFragment extends Fragment implements PetListAdapter.PetListCallback {
    public static final String TAG = PetListFragment.class.getSimpleName();

    RecyclerView listaDatos;
    PetListAdapter petListAdapter;
    RecyclerView.LayoutManager petListViewManager;
    private FloatingActionButton addPetFab;
    private PetListViewModel viewModel;
    private PetListCallback mCallback;

    public PetListFragment() {
        // Required empty public constructor
    }

    @Override
    public void petClick(Pet pet) {
        pet_detail detail = new pet_detail();
        Bundle data = new Bundle();
        data.putString("pet",new Gson().toJson(pet));
        detail.setArguments(data);
        ((CustomerMainActivity) getActivity()).replaceFragment(detail, true);
    }

    public interface PetListCallback {
        void addPetButtonClick();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(PetListViewModel.class);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (PetListCallback) context;
        } catch (ClassCastException ex) {
            //
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pet_list, container, false);
        listaDatos = rootView.findViewById(R.id.petListView);
        addPetFab = rootView.findViewById(R.id.addPetFab);

        listaDatos.setHasFixedSize(true);
        petListViewManager = new LinearLayoutManager(getActivity());
        viewModel.getPetList().observe(this, new Observer<List<Pet>>() {
            @Override
            public void onChanged(@Nullable List<Pet> pets) {
                petListAdapter = new PetListAdapter(pets, PetListFragment.this);
                listaDatos.setAdapter(petListAdapter);
            }
        });
        listaDatos.setLayoutManager(petListViewManager);

        addPetFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallback != null) {
                    mCallback.addPetButtonClick();
                }
            }
        });
        return rootView;
    }
}
