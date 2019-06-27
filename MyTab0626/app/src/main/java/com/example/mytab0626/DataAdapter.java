package com.example.mytab0626;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataViewHolder> {

    ArrayList<DataList> list;
    Context context;

    public DataAdapter(ArrayList<DataList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public DataAdapter() {
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataViewHolder(LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        DataList currentList = list.get(position);
        holder.name.setText(currentList.getName());
        holder.number.setText(currentList.getNumber());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}