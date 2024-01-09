package com.google.ads.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AdUtil$UserActivityReceiver extends BroadcastReceiver {
  public void onReceive(Context paramContext, Intent paramIntent) {
    if (paramIntent.getAction().equals("android.intent.action.USER_PRESENT")) {
      AdUtil.a(true);
      return;
    } 
    if (paramIntent.getAction().equals("android.intent.action.SCREEN_OFF"))
      AdUtil.a(false); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ad\\util\AdUtil$UserActivityReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */