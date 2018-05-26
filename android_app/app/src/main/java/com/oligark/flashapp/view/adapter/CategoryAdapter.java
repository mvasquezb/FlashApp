package com.oligark.flashapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oligark.flashapp.R;
import com.oligark.flashapp.model.CategoryService;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyHolder> {
    private Context context;
    private ArrayList<CategoryService> categories;


    public CategoryAdapter(Context context, ArrayList<CategoryService> categories){
        this.context = context;
        this.categories = categories;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_items, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.name.setText(categories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public  static class MyHolder extends RecyclerView.ViewHolder {
        TextView name;

        public MyHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textCar);
        }
    }

}

