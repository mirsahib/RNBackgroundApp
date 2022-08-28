package com.rnbackgroundapp; // replace com.your-app-name with your app’s name

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.Map;
import java.util.HashMap;
import android.util.Log;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

public class BackgroundModule extends ReactContextBaseJavaModule {
    private Context mContext;

    BackgroundModule(ReactApplicationContext context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public String getName() {
        return "BackgroundModule";
    }

    @ReactMethod
    public void startBackgroundService() {
        Log.d("TAG", "Start background service");
        // Intent service = new Intent(this.mContext, BackgroundService.class);

        // this.mContext.startService(service);
        BroadcastReceiver br = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        this.mContext.registerReceiver(br, filter);
    }
}