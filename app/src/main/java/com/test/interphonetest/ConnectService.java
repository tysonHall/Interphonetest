package com.test.interphonetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ConnectService extends Service {
    private int connectstate = 0; //0:空闲   1:占用
    private static final ConnectService instance = new ConnectService();

    private static SpeakerService speaker;
    private static RecipientService recipient;
    public ConnectService() {
    }

    public static ConnectService getInstance()
    {
        //获取讲话服务
        speaker = SpeakerService.getInstance();

        //获取收听服务
        recipient = RecipientService.getInstance();
        return instance;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    class ConnectThread extends Thread
    {
        private boolean flag = true;
        private DatagramSocket mSocket;

        private int inputBufSize = 160;
        short[] inputBytes = new short[1024];
        byte[] encodeBytes = new byte[1024];

        ConnectThread() throws SocketException {
            // TODO Auto-generated constructor stub
            mSocket = new DatagramSocket();
        }
        @Override
        public void run()
        {
            if(mSocket == null)
                return;

            while(flag)
            {
                if(connectstate == 0)
                {
                }
            }
        }
    }
}
