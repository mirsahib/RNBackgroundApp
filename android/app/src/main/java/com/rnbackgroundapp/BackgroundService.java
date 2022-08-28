package com.rnbackgroundapp;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.os.Build;
import com.facebook.react.HeadlessJsTaskService;
import android.os.Bundle;
import android.util.Log;
import android.app.Service;
import android.os.Handler;
import com.facebook.react.HeadlessJsTaskService;
import java.lang.Runnable;

public class BackgroundService extends Service {

    private Handler handler = new Handler();
    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            Log.d("TAG", "run");
            // Context context = getApplicationContext();
            // Intent myIntent = new Intent(context, HeadlessAction.class);
            // context.startService(myIntent);
            // HeadlessJsTaskService.acquireWakeLockNow(context);
            handler.postDelayed(this, 1000); // 5 Min
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TAG", "OnStartCommand: ");
       //this.handler.post(this.runnableCode);
        

        // Intent service = new Intent(getApplicationContext(), HeadlessAction.class);
        // Bundle bundle = new Bundle();

        // bundle.putString("foo", "bar");
        // service.putExtras(bundle);
        // getApplicationContext().startService(service);

        return START_STICKY_COMPATIBILITY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

}