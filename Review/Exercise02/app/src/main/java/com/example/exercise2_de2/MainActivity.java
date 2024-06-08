package com.example.exercise2_de2;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private DrinkAdapter drinkAdapter;
    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Để hiển thị sản phẩm cầm chuột kéo từ cạnh trái qua phải để hiển thị NavigationView rùi chọn
        toNextActivity();

    }
    public void toNextActivity()
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            public void run() {
                if (checkInternetConnection())
                {
                    drawerLayout = findViewById(R.id.drawer_layout);
                    NavigationView navigationView = findViewById(R.id.nav_view);
                    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            int id = item.getItemId();

                            if (id == R.id.nav_ordinary_drink) {
                                fetchDrinksByCategory("Ordinary_Drink");
                            } else if (id == R.id.nav_alcoholic) {
                                fetchDrinksByAlcoholic("Alcoholic");
                            } else if (id == R.id.nav_non_alcoholic) {
                                fetchDrinksByAlcoholic("Non_Alcoholic");
                            } else if (id == R.id.nav_gin) {
                                fetchDrinksByIngredient("Gin");
                            } else if (id == R.id.nav_vodka) {
                                fetchDrinksByIngredient("Vodka");
                            }

                            drawerLayout.closeDrawers();
                            return true;
                        }
                    });


                    recyclerView = findViewById(R.id.recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                    apiService = RetrofitClient.getClient().create(ApiService.class);
                }

            }
        }, 1);

    }
    private boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    private void fetchDrinksByCategory(String category) {
        apiService.getDrinksByCategory(category).enqueue(new Callback<DrinkResponse>() {
            @Override
            public void onResponse(Call<DrinkResponse> call, Response<DrinkResponse> response) {
                if (response.isSuccessful()) {
                    List<Drink> drinks = response.body().getDrinks();
                    drinkAdapter = new DrinkAdapter(MainActivity.this, drinks);
                    recyclerView.setAdapter(drinkAdapter);
                }
            }

            @Override
            public void onFailure(Call<DrinkResponse> call, Throwable t) {
                // Handle error
            }
        });
    }

    private void fetchDrinksByAlcoholic(String alcoholic) {
        apiService.getDrinksByAlcoholic(alcoholic).enqueue(new Callback<DrinkResponse>() {
            @Override
            public void onResponse(Call<DrinkResponse> call, Response<DrinkResponse> response) {
                if (response.isSuccessful()) {
                    List<Drink> drinks = response.body().getDrinks();
                    drinkAdapter = new DrinkAdapter(MainActivity.this, drinks);
                    recyclerView.setAdapter(drinkAdapter);
                }
            }

            @Override
            public void onFailure(Call<DrinkResponse> call, Throwable t) {
                // Handle error
            }
        });
    }

    private void fetchDrinksByIngredient(String ingredient) {
        apiService.getDrinksByIngredient(ingredient).enqueue(new Callback<DrinkResponse>() {
            @Override
            public void onResponse(Call<DrinkResponse> call, Response<DrinkResponse> response) {
                if (response.isSuccessful()) {
                    List<Drink> drinks = response.body().getDrinks();
                    drinkAdapter = new DrinkAdapter(MainActivity.this, drinks);
                    recyclerView.setAdapter(drinkAdapter);
                }
            }

            @Override
            public void onFailure(Call<DrinkResponse> call, Throwable t) {
                // Handle error
            }
        });
    }
}