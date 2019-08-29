package com.sematecjavaproject.cloudytheweatherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapterActivity extends RecyclerView.Adapter<ItemAdapterActivity.ItemAdapterViewHolder> {

    @NonNull
    @Override
    public ItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item, parent, false);
        ItemAdapterViewHolder holder = new ItemAdapterViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class ItemAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView txtTest;

        public ItemAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTest = itemView.findViewById(R.id.txtTest);
        }
    }
}
