package com.google.ads;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.webkit.WebView;
import com.google.ads.internal.a;
import java.util.Date;
import java.util.Locale;

public final class ag {
  private static final a a = (a)a.a.b();
  
  public static void a(Activity paramActivity) {
    (new Thread(new i(paramActivity))).start();
  }
  
  public static void a(Activity paramActivity, WebView paramWebView, String paramString) {
    (new Thread(new j(paramActivity, paramWebView, paramString))).start();
  }
  
  public static void a(WebView paramWebView, String paramString) {
    a.a(paramWebView, String.format(Locale.US, "(G_resizeIframe(%s))", new Object[] { paramString }));
  }
  
  public static void a(WebView paramWebView, boolean paramBoolean) {
    a.a(paramWebView, String.format(Locale.US, "(G_updatePlusOne(%b))", new Object[] { Boolean.valueOf(paramBoolean) }));
  }
  
  public static boolean a(Context paramContext, long paramLong) {
    return a(paramContext, paramLong, PreferenceManager.getDefaultSharedPreferences(paramContext.getApplicationContext()));
  }
  
  static boolean a(Context paramContext, long paramLong, SharedPreferences paramSharedPreferences) {
    return (ah.a(paramContext) && (!paramSharedPreferences.contains("drt") || !paramSharedPreferences.contains("drt_ts") || paramSharedPreferences.getLong("drt_ts", 0L) < (new Date()).getTime() - paramLong));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\ag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */