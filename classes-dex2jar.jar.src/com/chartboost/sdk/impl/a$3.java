package com.chartboost.sdk.impl;

import android.content.Intent;
import android.net.Uri;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.b;
import org.json.JSONObject;

class a$3 implements b.c {
  final a a;
  
  private final a b;
  
  a$3(a parama1, a parama2) {}
  
  public void a(String paramString, JSONObject paramJSONObject) {
    if (paramString == null)
      paramString = this.b.a.optString("link"); 
    String str2 = this.b.a.optString("deep-link");
    String str1 = paramString;
    if (str2 != null) {
      str1 = paramString;
      if (!str2.equals(""))
        try {
          Intent intent = new Intent();
          this("android.intent.action.VIEW", Uri.parse(str2));
          int i = Chartboost.sharedChartboost().getContext().getPackageManager().queryIntentActivities(intent, 65536).size();
          String str = paramString;
          if (i > 0)
            str = str2; 
        } catch (Exception exception) {
          str1 = paramString;
        }  
    } 
    this.b.g.a(this.b, str1, paramJSONObject);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\a$3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */