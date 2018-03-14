package com.test.interphonetest;

import android.app.Service;
import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.IBinder;

public class SpeakerService extends Service {
    private static final SpeakerService instance = new SpeakerService();
    private static ConnectService connectService;
    public SpeakerService() {
    }

    public static SpeakerService getInstance()
    {
        connectService = ConnectService.getInstance();
        return instance;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int speak(int nowstate)
    {
        if(nowstate == 0)
        {
            nowstate = 1;
            connectService.setState(0);
        }
        return nowstate;
    }

    public int end(int nowstate)
    {
        if(nowstate == 1)
        {
            nowstate = 0;
            connectService.setState(1);
        }
        return nowstate;
    }
}
