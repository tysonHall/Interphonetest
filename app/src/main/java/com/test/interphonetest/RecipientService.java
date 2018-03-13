package com.test.interphonetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class RecipientService extends Service {
    private static final RecipientService instance = new RecipientService();
    public RecipientService() {
    }

    public static RecipientService getInstance()
    {
        return instance;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
