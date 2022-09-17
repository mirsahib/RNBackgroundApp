package com.rnbackgroundapp;

import androidx.work.Worker;
import androidx.work.WorkerParameters;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;


public class NetworkMonitorWorker extends Worker {
    private Context mContext;
    private BroadcastReceiver br;


    public NetworkMonitorWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
        mContext = context;
    }

    @Override
    public Result doWork() {

        // Do the work here--in this case, upload the images.
        Log.d("TAG", "Network monitor on...");

        br = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.mContext.registerReceiver(br, filter);
        
        Log.d("TAG", "network monitor code completed");

        // Indicate whether the work finished successfully with the Result
        return Result.success();
    }

}
