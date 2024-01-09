package com.flurry.android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.DisplayMetrics;
import java.io.Closeable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

final class ac {
  static int a(Context paramContext) {
    return (paramContext.getResources().getDisplayMetrics()).widthPixels;
  }
  
  static int a(Context paramContext, int paramInt) {
    DisplayMetrics displayMetrics = paramContext.getResources().getDisplayMetrics();
    float f = paramInt;
    return Math.round(displayMetrics.density * f);
  }
  
  static bl a(bo parambo, String paramString) {
    long l = parambo.b();
    parambo.d();
    bl bl = new bl(l, paramString);
    parambo.a(bl);
    return bl;
  }
  
  static String a(String paramString) {
    if (paramString == null)
      return ""; 
    String str = paramString;
    if (paramString.length() > 255)
      str = paramString.substring(0, 255); 
    return str;
  }
  
  static HttpResponse a(be parambe, String paramString, int paramInt1, int paramInt2, boolean paramBoolean) {
    UnknownHostException unknownHostException = null;
    try {
      HttpGet httpGet = new HttpGet();
      this(paramString);
      BasicHttpParams basicHttpParams = new BasicHttpParams();
      this();
      HttpConnectionParams.setConnectionTimeout((HttpParams)basicHttpParams, 10000);
      HttpConnectionParams.setSoTimeout((HttpParams)basicHttpParams, 15000);
      basicHttpParams.setParameter("http.protocol.handle-redirects", Boolean.valueOf(paramBoolean));
      HttpResponse httpResponse = parambe.a((HttpParams)basicHttpParams).execute((HttpUriRequest)httpGet);
    } catch (UnknownHostException null) {
      bm.a("FlurryAgent", "Unknown host: " + exception.getMessage());
      exception = unknownHostException;
    } catch (Exception exception) {
      bm.a("FlurryAgent", "Failed to hit URL: " + paramString, exception);
      exception = unknownHostException;
    } 
    return (HttpResponse)exception;
  }
  
  static void a(Closeable paramCloseable) {
    if (paramCloseable != null)
      try {
        paramCloseable.close();
      } catch (Throwable throwable) {} 
  }
  
  static boolean a(long paramLong) {
    boolean bool = false;
    if (System.currentTimeMillis() <= paramLong)
      bool = true; 
    return bool;
  }
  
  static boolean a(Context paramContext, Intent paramIntent) {
    return (paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).size() > 0);
  }
  
  static boolean a(Context paramContext, String paramString) {
    Intent intent = (new Intent("android.intent.action.VIEW")).setData(Uri.parse(paramString));
    if (a(paramContext, intent)) {
      bm.a("FlurryAgent", "Launching intent for " + paramString);
      paramContext.startActivity(intent);
      return true;
    } 
    return false;
  }
  
  static int b(Context paramContext) {
    return (paramContext.getResources().getDisplayMetrics()).heightPixels;
  }
  
  private static int b(Context paramContext, int paramInt) {
    DisplayMetrics displayMetrics = paramContext.getResources().getDisplayMetrics();
    return Math.round(paramInt / displayMetrics.density);
  }
  
  static String b(String paramString) {
    try {
      String str = URLEncoder.encode(paramString, "UTF-8");
      paramString = str;
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      bm.d("FlurryAgent", "Cannot encode '" + paramString + "'");
      paramString = "";
    } 
    return paramString;
  }
  
  static int c(Context paramContext) {
    return b(paramContext, a(paramContext));
  }
  
  static byte[] c(String paramString) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
      messageDigest.update(paramString.getBytes(), 0, paramString.length());
      byte[] arrayOfByte = messageDigest.digest();
    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
      bm.b("FlurryAgent", "Unsupported SHA1: " + noSuchAlgorithmException.getMessage());
      noSuchAlgorithmException = null;
    } 
    return (byte[])noSuchAlgorithmException;
  }
  
  static int d(Context paramContext) {
    return b(paramContext, b(paramContext));
  }
  
  static String d(String paramString) {
    return paramString.replace("'", "\\'");
  }
  
  static Map e(String paramString) {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    if (paramString != null && !paramString.isEmpty()) {
      String[] arrayOfString = paramString.split("&");
      int i = arrayOfString.length;
      for (byte b = 0; b < i; b++) {
        String[] arrayOfString1 = arrayOfString[b].split("=");
        if (!arrayOfString1[0].equals("event"))
          hashMap.put(f(arrayOfString1[0]), f(arrayOfString1[1])); 
      } 
    } 
    return hashMap;
  }
  
  private static String f(String paramString) {
    try {
      String str = URLDecoder.decode(paramString, "UTF-8");
      paramString = str;
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      bm.d("FlurryAgent", "Cannot decode '" + paramString + "'");
      paramString = "";
    } 
    return paramString;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\ac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */