package com.rnbackgroundapp;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactRootView;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import com.rnbackgroundapp.NetworkStateManager;
import androidx.lifecycle.Observer;
import 	android.net.wifi.WifiManager;
import androidx.work.WorkManager;
import androidx.work.OneTimeWorkRequest;



public class MainActivity extends ReactActivity {

  private BroadcastReceiver br;
  private OneTimeWorkRequest request;

  
  /**
   * Returns the name of the main component registered from JavaScript. This is
   * used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "RNBackgroundApp";
  }

  

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(null);
    // Log.d("TAG", "MainActivity on create called");
    // this.br = new NetworkChangeReceiver();
  }

  private final Observer<Boolean> activeNetworkStateObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean isConnected) {
            Log.d("TAG", "Wifi Status "+isConnected);
        }
    };

  @Override
  protected void onStart(){
    super.onStart();
    Log.d("TAG", "MainActivity on start called");
    // IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    // filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
    // this.registerReceiver(this.br, filter);
    //NetworkStateManager.getInstance().getNetworkConnectivityStatus()
    //            .observe(this, activeNetworkStateObserver);
    // br = new NetworkChangeReceiver();
    // IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    // this.registerReceiver(br, filter);
    request = new OneTimeWorkRequest.Builder(NetworkMonitorWorker.class).build();
    WorkManager.getInstance().enqueue(request);
  }
  

  @Override
  public void onStop() {
    super.onStop();
    // Log.d("TAG", "onstop() called");
    // Intent service = new Intent(getApplicationContext(), HeadlessAction.class);
    // Bundle bundle = new Bundle();
    // bundle.putString("foo", "bar");
    // service.putExtras(bundle);
    // getApplicationContext().startService(service);
    // Log.d("TAG", "onstop() end");
  }

  /**
   * Returns the instance of the {@link ReactActivityDelegate}. There the RootView
   * is created and
   * you can specify the renderer you wish to use - the new renderer (Fabric) or
   * the old renderer
   * (Paper).
   */
  @Override
  protected ReactActivityDelegate createReactActivityDelegate() {
    return new MainActivityDelegate(this, getMainComponentName());
  }

  public static class MainActivityDelegate extends ReactActivityDelegate {
    public MainActivityDelegate(ReactActivity activity, String mainComponentName) {
      super(activity, mainComponentName);
    }

    @Override
    protected ReactRootView createRootView() {
      ReactRootView reactRootView = new ReactRootView(getContext());
      // If you opted-in for the New Architecture, we enable the Fabric Renderer.
      reactRootView.setIsFabric(BuildConfig.IS_NEW_ARCHITECTURE_ENABLED);
      return reactRootView;
    }

    @Override
    protected boolean isConcurrentRootEnabled() {
      // If you opted-in for the New Architecture, we enable Concurrent Root (i.e.
      // React 18).
      // More on this on https://reactjs.org/blog/2022/03/29/react-v18.html
      return BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
    }
  }
}
