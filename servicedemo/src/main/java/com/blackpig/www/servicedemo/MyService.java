package com.blackpig.www.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.IOException;

import static android.content.ContentValues.TAG;

/**
 * Created by black on 2016/9/20/0020.
 */

public class MyService extends Service {
    private MediaRecorder recorder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getService();//获取电话状态
    }


    /**
     * 获取电话状态
     */
    private void getService() {
        //监听电话状态
        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        tm.listen(new myListener(), PhoneStateListener.LISTEN_CALL_STATE);
    }

    class myListener extends PhoneStateListener {
        //电话状态改变的回调

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
           switch (state) {
               case TelephonyManager.CALL_STATE_IDLE:
                   //电话空闲
                   Log.i(TAG, "onCallStateChanged: 电话空闲");
                   if (recorder != null) {
                       //停止录音
                       recorder.stop();
                       //释放资源
                       recorder.release();
                       recorder = null;
                   }
                   break;
               case TelephonyManager.CALL_STATE_RINGING:
                   //电话响铃  判断硬件是否支持录音
                   Log.i(TAG, "onCallStateChanged: 电话响铃");
                    if (recorder == null) {
                        getRecorder();
                    }
                   break;
               case TelephonyManager.CALL_STATE_OFFHOOK:
                   //正在通话
                   Log.i(TAG, "onCallStateChanged: 正在通话");
                   if (recorder != null) {
                       recorder.start();
                   }
                   break;

            }


        }
    }

    private void getRecorder() {
        recorder = new MediaRecorder();
        //麦克风
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //设置格式3GP
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        //设置目录权限
        recorder.setOutputFile("sdcard/audio.3gp");
        //设置音频编码
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        //准备录音
        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
