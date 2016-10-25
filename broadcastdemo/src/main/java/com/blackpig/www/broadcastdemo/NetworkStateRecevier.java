package com.blackpig.www.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by black on 2016/9/24/0024.
 */

public class NetworkStateRecevier extends BroadcastReceiver {
    private static final String TAG = "NetworkStateRecevier";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive called");
        if (isNetworkAvailable(context)) {
            Toast.makeText(context, "network is connect", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] infos = manager.getAllNetworkInfo();
        if (infos != null) {
            for (int i = 0; i < infos.length; i++) {
                if (infos[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }
}
