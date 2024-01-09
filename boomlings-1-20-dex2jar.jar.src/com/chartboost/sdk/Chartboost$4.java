package com.chartboost.sdk;

import android.util.Log;
import com.chartboost.sdk.Libraries.d;
import com.chartboost.sdk.impl.k;
import org.json.JSONObject;

class Chartboost$4 implements k.a {
  final Chartboost a;
  
  Chartboost$4(Chartboost paramChartboost) {}
  
  public void a(JSONObject paramJSONObject) {
    if (d.a(this.a.getContext())) {
      String str = paramJSONObject.optString("latest-sdk-version");
      if (str != null && !str.equals("") && !str.equals("3.1.5"))
        Log.w("Chartboost", String.format("WARNING: you have an outdated version of the SDK (Current: %s, Latest: %s). Get the latest version at chartboost.com/support/sdk#android", new Object[] { str, "3.1.5" })); 
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\Chartboost$4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */