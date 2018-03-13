package com.test.interphonetest;

import android.app.Service;
import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.IBinder;

public class SpeakerService extends Service {
    private static final SpeakerService instance = new SpeakerService();
    public static int inputMinSize = AudioRecord.getMinBufferSize(8000,AudioFormat.CHANNEL_CONFIGURATION_MONO,AudioFormat.ENCODING_PCM_16BIT);
    public static AudioRecord audiorec = new AudioRecord(MediaRecorder.AudioSource.MIC, 8000,AudioFormat.CHANNEL_CONFIGURATION_MONO,AudioFormat.ENCODING_PCM_16BIT, inputMinSize);
    public SpeakerService() {
    }

    public static SpeakerService getInstance()
    {
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
        }
        return nowstate;
    }

    public int end(int nowstate)
    {
        if(nowstate == 1)
        {
            nowstate = 0;
        }
        return nowstate;
    }
}
