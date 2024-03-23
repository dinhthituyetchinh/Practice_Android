package com.example.exercise_01.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.exercise_01.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DemoCustomAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<HashMap<String, String>> items;

    public DemoCustomAdapter(Context context, ArrayList<HashMap<String, String>> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void addItem(HashMap<String, String> item)
    {
        items.add(item);
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.demo_list_item_layout, null);
        HashMap<String, String> item = (HashMap<String, String>) getItem(position);
        ((TextView) convertView.findViewById(R.id.txt_left)).setText(item.get("fullname"));
        ((TextView) convertView.findViewById(R.id.txt_right)).setText(item.get("age"));

        return convertView;
    }
}
