package com.oligark.flashapp.view.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oligark.flashapp.R;
import com.oligark.flashapp.model.ServiceCategory;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyHolder> {
    private List<ServiceCategory> categories;
    private Context context;


    public CategoryAdapter(List<ServiceCategory> categories, Context context){
        this.categories = categories;
        this.context = context;
    }

    public  static class MyHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public CardView mCardView;
        public MyHolder(View itemView) {
            super(itemView);

            mCardView = (CardView) itemView.findViewById(R.id.card_view);
            mTextView = (TextView) itemView.findViewById(R.id.textCar);
        }
    }



    @Override
    public CategoryAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.card_items, parent, false);
    // set the view's size, margins, paddings and layout parameters
    MyHolder vh = new MyHolder(v);
        return vh;
}

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.mTextView.setText(categories.get(position).getName());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentValue = categories.get(position).getName();
                Log.d("CardView", "CardView Clicked: " + currentValue);
//
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                Fragment myFragment = new MyFragment();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();


            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


}

