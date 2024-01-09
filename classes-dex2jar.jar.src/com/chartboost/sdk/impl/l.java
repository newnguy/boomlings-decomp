package com.chartboost.sdk.impl;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.chartboost.sdk.Chartboost;

public class l {
  public static boolean a() {
    boolean bool;
    try {
      ConnectivityManager connectivityManager = (ConnectivityManager)Chartboost.sharedChartboost().getContext().getSystemService("connectivity");
      if (connectivityManager != null) {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
          boolean bool1 = networkInfo.isConnectedOrConnecting();
          if (bool1)
            return true; 
        } 
      } 
      bool = false;
    } catch (Exception exception) {
      bool = true;
    } 
    return bool;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */