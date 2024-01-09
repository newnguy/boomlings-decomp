package com.google.ads;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.webkit.WebView;
import com.google.ads.util.b;
import java.lang.ref.WeakReference;

class j implements Runnable {
  private final WeakReference a;
  
  private final WebView b;
  
  private final String c;
  
  public j(Activity paramActivity, WebView paramWebView, String paramString) {
    this.a = new WeakReference<Activity>(paramActivity);
    this.c = paramString;
    this.b = paramWebView;
  }
  
  public void run() {
    try {
      boolean bool;
      Uri uri = Uri.withAppendedPath(af.a, this.c);
      Activity activity = this.a.get();
      if (activity == null) {
        b.a("Activity was null while getting the +1 button state.");
        return;
      } 
      Cursor cursor = activity.getContentResolver().query(uri, af.c, null, null, null);
      if (cursor != null && cursor.moveToFirst()) {
        if (cursor.getInt(cursor.getColumnIndex("has_plus1")) == 1) {
          bool = true;
        } else {
          bool = false;
        } 
      } else {
        b.a("Google+ app not installed, showing ad as not +1'd");
        bool = false;
      } 
      WebView webView = this.b;
      k k = new k();
      this(this.b, bool);
      webView.post(k);
    } catch (Throwable throwable) {
      b.b("An unknown error occurred while updating the +1 state.", throwable);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */