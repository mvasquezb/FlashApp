package com.oligark.flashapp.view.adapter;

import android.util.Log;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oligark.flashapp.R;
import com.oligark.flashapp.model.Service;
import com.oligark.flashapp.model.ServiceCategory;

import java.util.ArrayList;
import java.util.List;

public class HistoryUserAdapter extends RecyclerView.Adapter<HistoryUserAdapter.MyHolder> {
    private List<Service> services;


    public HistoryUserAdapter(List<Service> services) {
        this.services = services;
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public TextView txtSeller;
        public TextView txtDescription;
        public TextView txtType;
        public TextView txtDate;
        public CardView mCardView;

        public MyHolder(View itemView) {
            super(itemView);

            mCardView = (CardView) itemView.findViewById(R.id.card_view_history_user);
            txtSeller = (TextView) itemView.findViewById(R.id.txtSeller);
            txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
            txtType = (TextView) itemView.findViewById(R.id.txtDate);
            txtDate = (TextView) itemView.findViewById(R.id.txtType);
        }
    }


    @Override
    public HistoryUserAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item_history_user, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyHolder vh = new MyHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.txtType.setText(services.get(position).getTipoServicio());
        holder.txtDescription.setText(services.get(position).getDescripcion());
        if (services.get(position).getVendedor() != null) {
            holder.txtSeller.setText(services.get(position).getVendedor().toString());
        }
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentValue = services.get(position).getDescripcion();
                Log.d("CardView", "CardView Clicked: " + currentValue);
            }
        });
    }

    @Override
    public int getItemCount() {
        return services.size();
    }


}

