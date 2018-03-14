package com.test.interphonetest;

import android.app.Service;
import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ConnectService extends Service {
    private static int connectstate = 0; //0:空闲   1:占用
    private static final ConnectService instance = new ConnectService();

    private static ConnectThread connectThread;
    public ConnectService() {
    }

    public static ConnectService getInstance()
    {
        try {
            connectThread = new ConnectThread();
            connectThread.start();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return instance;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void setState(int cstate)
    {
        connectstate = cstate;
    }

    static class ConnectThread extends Thread
    {
        private boolean flag = true;
        private DatagramSocket mSocket;
        private InetAddress group = null;
        private int NETPORT = 12112;
        private AudioRecord audioRec;

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

            try {
                //初始化链接
                group = InetAddress.getByName("www.nididake.com");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            //初始化音频接收器
            int inputMinSize = AudioRecord.getMinBufferSize(8000,AudioFormat.CHANNEL_IN_MONO,AudioFormat.ENCODING_PCM_16BIT);
            audioRec = new AudioRecord(MediaRecorder.AudioSource.MIC, 8000,AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, inputMinSize);
            while(flag)
            {
                if(connectstate == 1)
                {
                    int length = audioRec.read(inputBytes, 0, inputBufSize);
                    DatagramPacket writerPacker;
                    if(group != null && length > 0)
                    {
                        System.out.println("AudioRect read length:"+length);
                        writerPacker = new DatagramPacket(encodeBytes, length, group, NETPORT);
                        writerPacker.setLength(length);
                        try {
                            mSocket.send(writerPacker);
                        } catch (IOException e) {

                        }
                    }
                }
            }
        }

        public void close()
        {
            flag = false;
            if(mSocket != null)
            {
                mSocket.close();
            }
        }
    }
}
