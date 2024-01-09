package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.b;
import com.chartboost.sdk.Libraries.d;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class k {
  public String a;
  
  public String b;
  
  public String c;
  
  public Map d;
  
  public JSONObject e;
  
  public Map f;
  
  public List g;
  
  public k$a h;
  
  public boolean i;
  
  public JSONObject j = null;
  
  public k(String paramString1, String paramString2) {
    this.a = paramString1;
    this.b = paramString2;
    this.c = "GET";
  }
  
  public static k a(JSONObject paramJSONObject) {
    try {
      k k2 = new k();
      this(paramJSONObject.getString("controller"), paramJSONObject.getString("action"));
      k2.g = d.a(paramJSONObject.optJSONArray("params"));
      k2.f = d.a(paramJSONObject.optJSONObject("query"));
      k2.e = paramJSONObject.optJSONObject("body");
      k2.i = paramJSONObject.getBoolean("ensureDelivery");
      k2.d = d.a(paramJSONObject.optJSONObject("headers"));
      k k1 = k2;
    } catch (Exception exception) {
      Log.w("Chartboost", "Unable to deserialize failed request");
      exception = null;
    } 
    return (k)exception;
  }
  
  private void b(Context paramContext) {
    int i;
    int j = 0;
    try {
      if (paramContext instanceof Activity) {
        Activity activity = (Activity)paramContext;
        Rect rect = new Rect();
        this();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        if (Build.VERSION.SDK_INT < 9)
          rect.top = 0; 
        i = rect.width();
        try {
          int m = rect.height();
          j = m;
        } catch (Exception exception) {}
      } else {
        i = 0;
      } 
    } catch (Exception exception) {
      i = 0;
    } 
    Display display = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    if (!i)
      i = display.getWidth(); 
    if (j <= 0)
      j = display.getHeight(); 
    a("w", i);
    a("h", j);
  }
  
  public String a() {
    return "/" + this.a + "/" + this.b + d.a(this.f);
  }
  
  public void a(Context paramContext) {
    a("app", Chartboost.sharedChartboost().getAppID());
    if (Build.PRODUCT.equals("sdk")) {
      a("model", "Android Simulator");
      a("identity", d.b());
    } else {
      a("model", Build.MODEL);
      a("identity", d.b());
    } 
    a("device_type", String.valueOf(Build.MANUFACTURER) + " " + Build.MODEL);
    a("os", "Android " + Build.VERSION.RELEASE);
    a("country", Locale.getDefault().getCountry());
    a("language", Locale.getDefault().getLanguage());
    a("sdk", "3.1.5");
    a("timestamp", (new StringBuilder(String.valueOf((new Date()).getTime()))).toString());
    b(paramContext);
    a("scale", (paramContext.getResources().getDisplayMetrics()).density);
    try {
      a("bundle", (paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 128)).versionName);
    } catch (Exception exception) {}
  }
  
  public void a(String paramString1, String paramString2) {
    if (this.e == null) {
      this.e = new JSONObject();
      this.c = "POST";
    } 
    try {
      this.e.put(paramString1, paramString2);
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
    } 
  }
  
  public String b() {
    return this.e.toString();
  }
  
  public void b(String paramString1, String paramString2) {
    if (this.d == null)
      this.d = new HashMap<Object, Object>(); 
    this.d.put(paramString1, paramString2);
  }
  
  public Map c() {
    return this.d;
  }
  
  public void c(String paramString1, String paramString2) {
    paramString2 = b.b(b.a((String.valueOf(this.c) + " " + a() + "\n" + paramString2 + "\n" + b()).getBytes()));
    b("X-Chartboost-App", paramString1);
    b("X-Chartboost-Signature", paramString2);
  }
  
  public JSONObject d() {
    try {
      JSONObject jSONObject = new JSONObject();
      this();
      jSONObject.put("controller", this.a);
      jSONObject.put("action", this.b);
      jSONObject.put("params", d.a(this.g));
      jSONObject.put("query", d.b(this.f));
      jSONObject.put("body", this.e);
      jSONObject.put("ensureDelivery", this.i);
      jSONObject.put("headers", d.b(this.d));
    } catch (Exception exception) {
      Log.w("Chartboost", "Unable to serialize failed request");
      exception = null;
    } 
    return (JSONObject)exception;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */