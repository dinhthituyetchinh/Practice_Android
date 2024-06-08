package com.example.bai2_de1;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class CountryDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_country_detail);

        Country country = (Country) getIntent().getSerializableExtra("country");

        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView officialNameTextView = findViewById(R.id.officialNameTextView);
        TextView capitalTextView = findViewById(R.id.capitalTextView);
        TextView populationTextView = findViewById(R.id.populationTextView);
        TextView languagesTextView = findViewById(R.id.languagesTextView);
        ImageView flagImageView = findViewById(R.id.flagImageView);

        nameTextView.setText(country.getName().getCommon());
        officialNameTextView.setText(country.getName().getOfficial());
        capitalTextView.setText(country.getCapital() != null ? String.join(", ", country.getCapital()) : "N/A");
        populationTextView.setText(String.valueOf(country.getPopulation()));
        languagesTextView.setText(country.getLanguages() != null ? String.join(", ", country.getLanguages().values()) : "N/A");
        Glide.with(this).load(country.getFlags().getPng()).into(flagImageView);
    }
}