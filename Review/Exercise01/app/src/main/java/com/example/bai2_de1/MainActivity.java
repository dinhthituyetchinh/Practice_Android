package com.example.bai2_de1;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerContinents;
    private ListView listViewCountries;
    private CountryListAdapter countryListAdapter;
    private List<Country> countries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        if (checkInternetConnection()) {
            spinnerContinents = findViewById(R.id.spinnerContinents);
            listViewCountries = findViewById(R.id.listViewCountries);

            countryListAdapter = new CountryListAdapter(this, new ArrayList<>());
            listViewCountries.setAdapter(countryListAdapter);

            fetchCountries();
        }
    }
    private boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
    private void fetchCountries() {
        CountryService service = RetrofitInstance.getCountryService();
        Call<List<Country>> call = service.getCountries();

        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    countries = response.body();
                    setupContinentsSpinner();
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                System.out.println("Err: " +t.getMessage());
            }
        });
    }

    private void setupContinentsSpinner() {
        List<String> continents = new ArrayList<>();
        for (Country country : countries) {
            continents.addAll(country.getContinents());
        }
        continents = continents.stream().distinct().collect(Collectors.toList());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, continents);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerContinents.setAdapter(adapter);

        List<String> finalContinents = continents;
        spinnerContinents.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedContinent = finalContinents.get(position);
                List<Country> filteredCountries = new ArrayList<>();
                for (Country country : countries) {
                    if (country.getContinents().contains(selectedContinent)) {
                        filteredCountries.add(country);
                    }
                }
                countryListAdapter.updateCountries(filteredCountries);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Không làm gì cả
            }
        });
    }
}