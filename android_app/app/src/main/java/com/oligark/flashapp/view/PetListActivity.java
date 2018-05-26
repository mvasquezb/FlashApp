package com.oligark.flashapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.oligark.flashapp.generated.callback.OnClickListener;
import com.oligark.flashapp.view.adapter.PetListAdapter;
import com.oligark.flashapp.R;
import com.oligark.flashapp.model.Pet;
import com.oligark.flashapp.viewmodel.PetListViewModel;

import java.util.ArrayList;
import java.util.List;

public class PetListActivity extends AppCompatActivity {

    RecyclerView listaDatos;
    PetListAdapter petListAdapter;
    RecyclerView.LayoutManager petListViewManager;
    private FloatingActionButton addPetFab;
    private PetListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas);
        listaDatos = findViewById(R.id.petListView);
        addPetFab = findViewById(R.id.addPetFab);
        viewModel = ViewModelProviders.of(this).get(PetListViewModel.class);

        listaDatos.setHasFixedSize(true);
        petListViewManager = new LinearLayoutManager(this);

        viewModel.getPetList().observe(this, new Observer<List<Pet>>() {
            @Override
            public void onChanged(@Nullable List<Pet> pets) {
                petListAdapter = new PetListAdapter(pets);
                listaDatos.setAdapter(petListAdapter);
            }
        });
        listaDatos.setLayoutManager(petListViewManager);

        addPetFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetListActivity.this, NewPetActivity.class);
                startActivity(intent);
            }
        });
    }
}
