package com.test.interphonetest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {
            Intent intent_n = new Intent(context, MyReceiver.class);
            context.startService(intent_n);
        }
    }

}
