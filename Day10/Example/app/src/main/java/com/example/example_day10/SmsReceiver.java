package com.example.example_day10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;

import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("SmsReceiver", "Có tin nhắn");
        for(SmsMessage sms : Telephony.Sms.Intents.getMessagesFromIntent(intent))
        {
            Log.e("SmsReceiver",
                    String.format("From: %s - Content: %s", sms.getOriginatingAddress(), sms.getMessageBody()));

        }
    }
}
