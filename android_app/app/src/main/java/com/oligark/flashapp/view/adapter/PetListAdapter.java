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

    private final PetListCallback cb;
    private List<Pet> petList;

    public PetListAdapter(List<Pet> petList, PetListCallback cb) {
        if (petList == null) {
            this.petList = new ArrayList<>();
        } else {
            this.petList = petList;
        }
        this.cb = cb;
    }

    public interface PetListCallback {
        void petClick(Pet pet);
    }

    @Override
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PetViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.pet_listapets, parent, false),
                cb
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
        private final TextView petTipoView;
        private final TextView petSexoView;
        private Pet pet;

        public PetViewHolder(View itemView, PetListCallback cb) {
            super(itemView);
            final PetListCallback callback = cb;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.petClick(pet);
                }
            });
            petImageView = itemView.findViewById(R.id.pet_img);
            petNameView = itemView.findViewById(R.id.pet_name);
            petBreedView = itemView.findViewById(R.id.pet_raza);
            petTipoView = itemView.findViewById(R.id.pet_tipo);
            petSexoView = itemView.findViewById(R.id.pet_sexo);

        }

        public void bind(Pet pet) {
            this.pet = pet;
            petNameView.setText(pet.getNombre());
            petBreedView.setText(pet.getRaza());
            petTipoView.setText(pet.getAnimal().getDescription());
            petSexoView.setText(pet.getSexo());
            Picasso.get()
                    .load(pet.getImageUrl())
                    .into(petImageView);
        }
    }
}
