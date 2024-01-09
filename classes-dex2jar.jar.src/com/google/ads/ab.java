package com.google.ads;

import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.webkit.WebView;
import com.google.ads.internal.AdVideoView;
import com.google.ads.internal.AdWebView;
import com.google.ads.internal.a;
import com.google.ads.internal.d;
import com.google.ads.util.AdUtil;
import com.google.ads.util.b;
import java.util.HashMap;

public class ab implements n {
  private static final a a = (a)a.a.b();
  
  protected int a(HashMap paramHashMap, String paramString, int paramInt, DisplayMetrics paramDisplayMetrics) {
    String str = (String)paramHashMap.get(paramString);
    int i = paramInt;
    if (str != null)
      try {
        float f = TypedValue.applyDimension(1, Integer.parseInt(str), paramDisplayMetrics);
        i = (int)f;
      } catch (NumberFormatException numberFormatException) {
        b.a("Could not parse \"" + paramString + "\" in a video gmsg: " + str);
        i = paramInt;
      }  
    return i;
  }
  
  public void a(d paramd, HashMap paramHashMap, WebView paramWebView) {
    String str1;
    AdActivity adActivity;
    AdWebView adWebView;
    String str2 = (String)paramHashMap.get("action");
    if (str2 == null) {
      b.a("No \"action\" parameter in a video gmsg.");
      return;
    } 
    if (paramWebView instanceof AdWebView) {
      adWebView = (AdWebView)paramWebView;
      adActivity = adWebView.d();
      if (adActivity == null) {
        b.a("Could not get adActivity for a video gmsg.");
        return;
      } 
    } else {
      b.a("Could not get adWebView for a video gmsg.");
      return;
    } 
    boolean bool1 = str2.equals("new");
    boolean bool2 = str2.equals("position");
    if (bool1 || bool2) {
      DisplayMetrics displayMetrics = AdUtil.a(adActivity);
      int i = a(paramHashMap, "x", 0, displayMetrics);
      int m = a(paramHashMap, "y", 0, displayMetrics);
      int j = a(paramHashMap, "w", -1, displayMetrics);
      int k = a(paramHashMap, "h", -1, displayMetrics);
      if (bool1 && adActivity.a() == null) {
        adActivity.b(i, m, j, k);
        return;
      } 
      adActivity.a(i, m, j, k);
      return;
    } 
    AdVideoView adVideoView = adActivity.a();
    if (adVideoView == null) {
      a.a((WebView)adWebView, "onVideoEvent", "{'event': 'error', 'what': 'no_video_view'}");
      return;
    } 
    if (str2.equals("click")) {
      DisplayMetrics displayMetrics = AdUtil.a(adActivity);
      int i = a(paramHashMap, "x", 0, displayMetrics);
      int j = a(paramHashMap, "y", 0, displayMetrics);
      long l = SystemClock.uptimeMillis();
      adVideoView.a(MotionEvent.obtain(l, l, 0, i, j, 0));
      return;
    } 
    if (str2.equals("controls")) {
      str1 = (String)paramHashMap.get("enabled");
      if (str1 == null) {
        b.a("No \"enabled\" parameter in a controls video gmsg.");
        return;
      } 
      if (str1.equals("true")) {
        adVideoView.setMediaControllerEnabled(true);
        return;
      } 
      adVideoView.setMediaControllerEnabled(false);
      return;
    } 
    if (str2.equals("currentTime")) {
      str1 = (String)str1.get("time");
      if (str1 == null) {
        b.a("No \"time\" parameter in a currentTime video gmsg.");
        return;
      } 
      try {
        adVideoView.a((int)(Float.parseFloat(str1) * 1000.0F));
      } catch (NumberFormatException numberFormatException) {
        b.a("Could not parse \"time\" parameter: " + str1);
      } 
      return;
    } 
    if (str2.equals("hide")) {
      numberFormatException.setVisibility(4);
      return;
    } 
    if (str2.equals("load")) {
      numberFormatException.b();
      return;
    } 
    if (str2.equals("pause")) {
      numberFormatException.c();
      return;
    } 
    if (str2.equals("play")) {
      numberFormatException.d();
      return;
    } 
    if (str2.equals("show")) {
      numberFormatException.setVisibility(0);
      return;
    } 
    if (str2.equals("src")) {
      numberFormatException.setSrc((String)str1.get("src"));
      return;
    } 
    b.a("Unknown video action: " + str2);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\ab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */