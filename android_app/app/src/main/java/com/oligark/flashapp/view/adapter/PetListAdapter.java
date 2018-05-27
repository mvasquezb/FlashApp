package com.oligark.flashapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oligark.flashapp.R;
import com.oligark.flashapp.model.Pet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PetListAdapter extends RecyclerView.Adapter<PetListAdapter.PetViewHolder> {

    private List<Pet> petList;

    public PetListAdapter(List<Pet> petList) {
        if (petList == null) {
            this.petList = new ArrayList<>();
        } else {
            this.petList = petList;
        }
    }

    @Override
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PetViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.layoutlistview, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(PetViewHolder holder, int position) {
        holder.bind(petList.get(position));
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    public class PetViewHolder extends RecyclerView.ViewHolder {
        private final ImageView petImageView;
        private final TextView petNameView;
        private final TextView petBreedView;

        public PetViewHolder(View itemView) {
            super(itemView);
            petImageView = itemView.findViewById(R.id.petImage);
            petNameView = itemView.findViewById(R.id.petName);
            petBreedView = itemView.findViewById(R.id.petBreed);
        }

        public void bind(Pet pet) {
            petNameView.setText(pet.getNombre());
            petBreedView.setText(pet.getRaza());
            Picasso.get()
                    .load(pet.getImageUrl())
                    .into(petImageView);
        }
    }
}
