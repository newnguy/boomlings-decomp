package com.chartboost.sdk.impl;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.d;
import java.util.Locale;
import org.apache.http.client.HttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class j {
  private static int g = 0;
  
  private static HttpClient h = null;
  
  public String a;
  
  public j$c b;
  
  public String c;
  
  public SparseArray d;
  
  public int e = 1;
  
  public String f = "Loading...";
  
  public j(String paramString1, j$c paramj$c, String paramString2) {
    String str = paramString1;
    if (paramString1 == null)
      str = "https://www.chartboost.com"; 
    this.a = str;
    this.b = paramj$c;
    this.c = paramString2;
    this.d = new SparseArray();
  }
  
  private static String b(Application paramApplication, String paramString) {
    try {
      String str = (paramApplication.getPackageManager().getPackageInfo(paramApplication.getPackageName(), 0)).versionName;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramApplication.getPackageName());
      stringBuilder.append("/");
      stringBuilder.append(str);
      stringBuilder.append(" (");
      stringBuilder.append("Linux; U; Android ");
      stringBuilder.append(Build.VERSION.RELEASE);
      stringBuilder.append("; ");
      stringBuilder.append(Locale.getDefault());
      stringBuilder.append("; ");
      stringBuilder.append(Build.PRODUCT);
      stringBuilder.append(")");
      if (paramString != null) {
        stringBuilder.append(" ");
        stringBuilder.append(paramString);
      } 
      return stringBuilder.toString();
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      throw new RuntimeException(nameNotFoundException);
    } 
  }
  
  public static HttpClient b() {
    if (h != null)
      return h; 
    try {
      Application application = (Application)Chartboost.sharedChartboost().getContext().getApplicationContext();
      j$1 j$1 = new j$1();
      this(application);
      h = (HttpClient)j$1;
      HttpClient httpClient = h;
    } catch (Exception exception) {}
    return (HttpClient)exception;
  }
  
  private void b(k paramk) {
    if (paramk.i && this.c != null) {
      SharedPreferences sharedPreferences = d.a();
      String str = "CBQueuedRequests-" + this.c;
      try {
        JSONArray jSONArray;
        JSONObject jSONObject = paramk.d();
        if (jSONObject != null) {
          String str1 = sharedPreferences.getString(str, null);
          if (str1 != null) {
            try {
              JSONTokener jSONTokener = new JSONTokener();
              this(str1);
              jSONArray = new JSONArray();
              this(jSONTokener);
            } catch (Exception null) {}
          } else {
            jSONArray = new JSONArray();
          } 
        } else {
          return;
        } 
        jSONArray.put(jSONObject);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(str, jSONArray.toString());
        editor.commit();
      } catch (Exception exception) {
        Log.w("Chartboost", "Unable to save failed request");
      } 
      return;
    } 
    if (this.b != null)
      this.b.a((k)exception); 
  }
  
  public void a() {
    if (l.a()) {
      SharedPreferences sharedPreferences = d.a();
      String str2 = "CBQueuedRequests-" + this.c;
      String str1 = sharedPreferences.getString(str2, null);
      if (str1 != null) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(str2, null);
        editor.commit();
        try {
          JSONTokener jSONTokener = new JSONTokener();
          this(str1);
          JSONArray jSONArray = new JSONArray();
          this(jSONTokener);
          for (byte b = 0;; b++) {
            int i = jSONArray.length();
            if (b < i) {
              try {
                k k = k.a(jSONArray.getJSONObject(b));
                if (k != null)
                  a(k); 
              } catch (Exception exception) {}
            } else {
              return;
            } 
          } 
        } catch (Exception exception) {
          Log.w("Chartboost", "Retrying request list failed");
        } 
      } 
    } 
  }
  
  public void a(k paramk) {
    if (!l.a()) {
      b(paramk);
      return;
    } 
    int i = this.e;
    this.e = i + 1;
    j$b j$b = new j$b(this, paramk, null);
    j$b.c = Integer.valueOf(i);
    this.d.put(i, j$b);
    (new j$a(this)).execute((Object[])new j$b[] { j$b });
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */