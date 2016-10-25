package com.blackpig.www.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by black on 2016/9/24/0024.
 */

public class BootCompletementRecevier extends BroadcastReceiver {
    private static final String TAG = "BootCompleteRecevier";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, MsgPushService.class);
        context.startService(i);
        Log.i(TAG, "Boot Complete.Stating MsgPushService");
    }
}
