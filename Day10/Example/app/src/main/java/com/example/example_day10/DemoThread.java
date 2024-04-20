package com.example.example_day10;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class DemoThread extends Thread{
    Handler handler;

    public DemoThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        int i = 0;
        while (i <= 10)
        {
            Message m = handler.obtainMessage();

            Intent intent = new Intent();
            intent.putExtra("data", i);
            m.setData(intent.getExtras());
            Log.e("MainActivity", "Gửi dữ liệu");
            handler.sendMessage(m);
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
}
