package com.example.exercise_01.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exercise_01.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

//bước 2 extends ecyclerView.Adapter<DemoRecyclerViewAdapter.DemoViewHolder>
public class DemoRecyclerViewAdapter extends RecyclerView.Adapter<DemoRecyclerViewAdapter.DemoViewHolder>
{

    private final Context context;
    private final ArrayList<HashMap<String, String>> items;

    // bước 3 tạo cons
    public  DemoRecyclerViewAdapter(Context context, ArrayList<HashMap<String, String>> items)
    {
        this.context = context;
        this.items = items;
    }
    @NonNull
    @Override
    // Tạo ra đối tượng ViewHolder để rec view sử dụng
    public DemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DemoViewHolder demoViewHolder;
        //Inflate layout => view đưa vào view holder
        View layout = LayoutInflater.from(context)
                .inflate((R.layout.demo_list_item_layout), parent, false);// luôn luôn là false
        demoViewHolder = new DemoViewHolder(layout);
        return demoViewHolder;
    }

    @Override
    //Bind data vào view
    public void onBindViewHolder(@NonNull DemoViewHolder holder, int position) {
    HashMap<String, String> item = items.get(position);
        holder.txtLeft.setText(item.get("fullname"));
        holder.txtRight.setText(item.get("age"));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Sau khi tạo class DemoRecyclerViewAdapter thì bước 1: phải tạo class DemoViewHolder
    //Find view trong layout và lưu trữ lại trong view holder
    public class  DemoViewHolder extends RecyclerView.ViewHolder{

            TextView txtLeft, txtRight;
        public DemoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLeft = itemView.findViewById(R.id.txt_left);
            txtRight = itemView.findViewById(R.id.txt_right);

        }
    }
}
