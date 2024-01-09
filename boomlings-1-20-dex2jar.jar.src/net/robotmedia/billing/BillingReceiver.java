package net.robotmedia.billing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BillingReceiver extends BroadcastReceiver {
  private void a(Context paramContext, Intent paramIntent) {
    a.a(paramContext, paramIntent.getStringExtra("inapp_signed_data"), paramIntent.getStringExtra("inapp_signature"));
  }
  
  private void b(Context paramContext, Intent paramIntent) {
    a.a(paramContext, paramIntent.getStringExtra("notification_id"));
  }
  
  private void c(Context paramContext, Intent paramIntent) {
    a.a(paramContext, paramIntent.getLongExtra("request_id", -1L), paramIntent.getIntExtra("response_code", 0));
  }
  
  public void onReceive(Context paramContext, Intent paramIntent) {
    String str = paramIntent.getAction();
    a.a("Received " + str);
    if ("com.android.vending.billing.PURCHASE_STATE_CHANGED".equals(str)) {
      a(paramContext, paramIntent);
      return;
    } 
    if ("com.android.vending.billing.IN_APP_NOTIFY".equals(str)) {
      b(paramContext, paramIntent);
      return;
    } 
    if ("com.android.vending.billing.RESPONSE_CODE".equals(str)) {
      c(paramContext, paramIntent);
      return;
    } 
    Log.w(getClass().getSimpleName(), "Unexpected action: " + str);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\net\robotmedia\billing\BillingReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */