package com.test.interphonetest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static int phonestate = 0;//0:空闲   1:我在讲话    2:正在接收
    private Button talkbtn;
    private TextView stateView;
    private ConnectService connecter;

    private SpeakerService speaker;
    private RecipientService recipient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        talkbtn = (Button) findViewById(R.id.talkbtn);
        stateView = (TextView) findViewById(R.id.stateView);

        //对讲机初始状态
        phonestate = 0;
        showState();

        //获取讲话服务
        speaker = SpeakerService.getInstance();

        //获取收听服务
        recipient = RecipientService.getInstance();

        //获取网络链接服务
        connecter = ConnectService.getInstance();

        talkbtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN://0
                        phonestate = speaker.speak(phonestate);
                        showState();
                        break;
                    case MotionEvent.ACTION_UP://1
                        phonestate = speaker.end(phonestate);
                        showState();
                        break;
                    case MotionEvent.ACTION_MOVE://2
                        break;
                }
                return false;
            }
        });
    }

    private void showState()
    {
        switch (phonestate)
        {
            case 0:
                stateView.setText("空闲");
                stateView.setTextColor(Color.GRAY);
                break;
            case 1:
                stateView.setText("请讲话");
                stateView.setTextColor(Color.GREEN);
                break;
            case 2:
                stateView.setText("繁忙");
                stateView.setTextColor(Color.RED);
                break;
        }
    }
}
