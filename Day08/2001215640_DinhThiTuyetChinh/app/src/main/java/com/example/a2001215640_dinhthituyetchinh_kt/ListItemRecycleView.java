package com.example.a2001215640_dinhthituyetchinh_kt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListItemRecycleView extends RecyclerView.Adapter<ListItemRecycleView.ViewHolder>
{

    private final Context context;
    private final List<Member> items;

    public ListItemRecycleView(Context context, List<Member> items)
    {

        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder;
        View layout = LayoutInflater.from(context)
                .inflate((R.layout.list_item_member), parent, false);// luôn luôn là false
        viewHolder = new ViewHolder(layout);
        return viewHolder;
    }

    @Override
    //Bind data vào view
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Member item = items.get(position);
        holder.id.setText(item.getId());
        holder.name.setText(item.getName());
        holder.email.setText(item.getEmail());

    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView id, name, email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.txtID);
            name = itemView.findViewById(R.id.txtName);
            email = itemView.findViewById(R.id.txtEmail);
        }
    }
}
