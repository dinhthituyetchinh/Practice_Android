package com.example.day12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day12.databinding.DemoListItemBinding;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{

    private final Context context;
    private final List<Employee> employees;

    public RecyclerViewAdapter(Context context, List<Employee> employees )
    {

        this.context = context;
        this.employees = employees;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DemoListItemBinding demoListItemBinding = DemoListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(demoListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee item = employees.get(position);
        holder.demoListItemBinding.txtID.setText(item.getId());
        holder.demoListItemBinding.txtName.setText(item.getName());
        holder.demoListItemBinding.txtEmail.setText(item.getEmail());

    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder
    {
        DemoListItemBinding demoListItemBinding;

        public ViewHolder(@NonNull DemoListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            demoListItemBinding = itemBinding;

        }
    }
}
