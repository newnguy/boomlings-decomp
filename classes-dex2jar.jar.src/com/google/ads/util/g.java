package com.google.ads.util;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import com.google.ads.l;
import com.google.ads.m;
import java.io.File;

public class g {
  public static void a(View paramView) {
    paramView.setLayerType(1, null);
  }
  
  public static void a(Window paramWindow) {
    paramWindow.setFlags(16777216, 16777216);
  }
  
  public static void a(WebSettings paramWebSettings, m paramm) {
    Context context = (Context)paramm.f.a();
    l.a a = (l.a)((l)paramm.a.a()).a.a();
    paramWebSettings.setAppCacheEnabled(true);
    paramWebSettings.setAppCacheMaxSize(((Long)a.f.a()).longValue());
    paramWebSettings.setAppCachePath((new File(context.getCacheDir(), "admob")).getAbsolutePath());
    paramWebSettings.setDatabaseEnabled(true);
    paramWebSettings.setDatabasePath(context.getDatabasePath("admob").getAbsolutePath());
    paramWebSettings.setDomStorageEnabled(true);
    paramWebSettings.setSupportZoom(true);
    paramWebSettings.setBuiltInZoomControls(true);
    paramWebSettings.setDisplayZoomControls(false);
  }
  
  public static void b(View paramView) {
    paramView.setLayerType(0, null);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ad\\util\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */