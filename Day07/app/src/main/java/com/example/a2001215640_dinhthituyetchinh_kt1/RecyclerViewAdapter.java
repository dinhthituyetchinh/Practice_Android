package com.example.a2001215640_dinhthituyetchinh_kt1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DemoViewHolder>
{
    private final Context context;
    private final ArrayList<HashMap<String, String>> items;
    public RecyclerViewAdapter(Context context, ArrayList<HashMap<String, String>> items)
    {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    //Bước 4
    // Tạo ra đối tượng ViewHolder để rec view sử dụng
    public DemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DemoViewHolder demoViewHolder;
        //Inflate layout => view đưa vào view holder
        View layout = LayoutInflater.from(context)
                .inflate((R.layout.activity_second), parent, false);// luôn luôn là false
        demoViewHolder = new DemoViewHolder(layout);
        return demoViewHolder;
    }
    //      Bước 5
    @Override
    //Bind data vào view
    public void onBindViewHolder(@NonNull DemoViewHolder holder, int position) {
        HashMap<String, String> item = items.get(position);
        holder.txtLeft.setText(item.get("id"));
        holder.txtRight.setText(item.get("name"));
        holder.txtRight.setText(item.get("email"));
    }
    //Bước 6
    @Override
    public int getItemCount() {
        return items.size();
    }

    public class  DemoViewHolder extends RecyclerView.ViewHolder{

        TextView txtLeft, txtRight;
        public DemoViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
