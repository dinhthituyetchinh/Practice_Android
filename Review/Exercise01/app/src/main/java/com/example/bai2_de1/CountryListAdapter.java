package com.example.bai2_de1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CountryListAdapter extends BaseAdapter {
    private Context context;
    private List<Country> countries;

    public CountryListAdapter(Context context, List<Country> countries) {
        this.context = context;
        this.countries = countries;
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Country getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_country, parent, false);
        }

        Country country = getItem(position);

        ImageView flagImageView = view.findViewById(R.id.flagImageView);
        TextView nameTextView = view.findViewById(R.id.nameTextView);

        nameTextView.setText(country.getName().getCommon());
        Glide.with(context).load(country.getFlags().getPng()).into(flagImageView);

        view.setOnClickListener(v -> {
            Intent intent = new Intent(context, CountryDetailActivity.class);
            intent.putExtra("country", (CharSequence) country);
            context.startActivity(intent);
        });

        return view;
    }

    public void updateCountries(List<Country> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }
}
