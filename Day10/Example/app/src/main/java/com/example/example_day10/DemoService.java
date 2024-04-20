package com.example.example_day10;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class DemoService extends IntentService {
    public DemoService() {
        super("DemoService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int i = 0;
        while (i <= 10)
        {
            Log.e("DemoService", String.valueOf(i));
            i++;
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        onStart(intent, startId);
      return  START_NOT_STICKY;
        ////return  START_STICKY;
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e("DemoService", "START");
    }
}
