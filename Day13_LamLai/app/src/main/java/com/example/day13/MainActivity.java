package com.example.day13;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    Intent mInHome = new Intent(MainActivity.this, PokemonListActivity.class);
                    MainActivity.this.startActivity(mInHome);
                    MainActivity.this.finish();
                }
                else
                {
                    showNoInternetDialog();
                }
            }
        }, 3000);

    }
    private boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    private void showNoInternetDialog()
    {
        new AlertDialog.Builder(this)
                .setIcon(getResources().getDrawable(R.drawable.warning))
                .setTitle("No Internet")
                .setMessage("Please check your internet connection.")
                .setPositiveButton("Confirm", (dialog, which) -> finish())
                .show();
    }

}