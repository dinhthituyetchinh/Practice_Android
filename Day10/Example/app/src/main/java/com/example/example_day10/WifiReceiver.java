package com.example.example_day10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;

public class WifiReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("WifiReceiver", "Wifi thay đổi");
        if(intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, false))
        {
            //Connect
            Log.e("WifiReceiver", "Đã kết nối");
        }
        else {
            Log.e("WifiReceiver", "Chưa kết nối");
        }
    }

}
