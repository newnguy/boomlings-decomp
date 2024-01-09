package com.google.ads.internal;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;
import com.google.ads.AdActivity;
import com.google.ads.util.AdUtil;
import com.google.ads.util.b;

class l implements DownloadListener {
  final AdWebView a;
  
  l(AdWebView paramAdWebView) {}
  
  public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong) {
    try {
      Intent intent = new Intent();
      this("android.intent.action.VIEW");
      intent.setDataAndType(Uri.parse(paramString1), paramString4);
      AdActivity adActivity = this.a.d();
      if (adActivity != null && AdUtil.a(intent, (Context)adActivity))
        adActivity.startActivity(intent); 
    } catch (ActivityNotFoundException activityNotFoundException) {
      b.a("Couldn't find an Activity to view url/mimetype: " + paramString1 + " / " + paramString4);
    } catch (Throwable throwable) {
      b.b("Unknown error trying to start activity to view URL: " + paramString1, throwable);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\internal\l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */