package com.rnbackgroundapp;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import java.util.List;
import android.util.Log;
import 	android.net.wifi.WifiManager;
import com.facebook.react.HeadlessJsTaskService;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class NetworkChangeReceiver extends BroadcastReceiver {

    private OneTimeWorkRequest request;

    @Override
    public void onReceive(final Context context, final Intent intent) {
        /**
         * This part will be called every time network connection is changed
         * e.g. Connected -> Not Connected
         **/
        Log.d("TAG", "onReceive");
        boolean hasInternet = isNetworkAvailable(context);
        Log.d("TAG", "From br Wifi status "+hasInternet);
        //if (!isAppOnForeground((context))) {
            /**
             * We will start our service and send extra info about
             * network connections
             **/
            //Log.d("TAG", "not foreground " + hasInternet);
            
            // final String action = intent.getAction();
            // //Log.d("TAG", " wifi action" + action);
            // if (action.equals(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION)) {
            //     if (intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, false)) {
            //         // do stuff
            //         Log.d("TAG", "wifi connected");
            //     } else {
            //         // wifi connection was lost
            //         Log.d("TAG", "connection lost");
            //         request = new OneTimeWorkRequest.Builder(BackgroundWorker.class).build();
            //         WorkManager.getInstance().enqueue(request);
            //         // Intent serviceIntent = new Intent(context, HeadlessAction.class);
            //         // // serviceIntent.putExtra("hasInternet", hasInternet);
            //         // context.startService(serviceIntent);
            //         // HeadlessJsTaskService.acquireWakeLockNow(context);

            //     }
            // }


            
        // } else {
        //     Log.d("TAG", "foreground");
        // }
    }

    private boolean isAppOnForeground(Context context) {
        /**
         * We need to check if app is in foreground otherwise the app will crash.
         * http://stackoverflow.com/questions/8489993/check-android-application-is-in-foreground-or-not
         **/
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND &&
                    appProcess.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network networkCapabilities = cm.getActiveNetwork();

            if(networkCapabilities == null) {
                return false;
            }

            NetworkCapabilities actNw = cm.getNetworkCapabilities(networkCapabilities);

            if(actNw == null) {
                return false;
            }

            if(actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true;
            }

            return false;
        }

        // deprecated in API level 29
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }
}