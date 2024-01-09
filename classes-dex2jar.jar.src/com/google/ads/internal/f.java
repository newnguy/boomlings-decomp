package com.google.ads.internal;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.util.AdUtil;
import com.google.ads.util.b;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public final class f implements Runnable {
  private final c a;
  
  private final d b;
  
  private final f$a c;
  
  private volatile boolean d;
  
  private boolean e;
  
  private String f;
  
  private Thread g = null;
  
  f(c paramc, d paramd) {
    this(paramc, paramd, new v());
  }
  
  f(c paramc, d paramd, f$a paramf$a) {
    this.a = paramc;
    this.b = paramd;
    this.c = paramf$a;
  }
  
  private void a(Context paramContext, HttpURLConnection paramHttpURLConnection) {
    String str = PreferenceManager.getDefaultSharedPreferences(paramContext).getString("drt", "");
    if (this.e && !TextUtils.isEmpty(str)) {
      if (AdUtil.a == 8) {
        paramHttpURLConnection.addRequestProperty("X-Afma-drt-Cookie", str);
        return;
      } 
    } else {
      return;
    } 
    paramHttpURLConnection.addRequestProperty("Cookie", str);
  }
  
  private void a(HttpURLConnection paramHttpURLConnection) {
    b(paramHttpURLConnection);
    f(paramHttpURLConnection);
    g(paramHttpURLConnection);
    h(paramHttpURLConnection);
    e(paramHttpURLConnection);
    i(paramHttpURLConnection);
    j(paramHttpURLConnection);
    k(paramHttpURLConnection);
    d(paramHttpURLConnection);
    c(paramHttpURLConnection);
    l(paramHttpURLConnection);
  }
  
  private void a(HttpURLConnection paramHttpURLConnection, int paramInt) {
    if (300 <= paramInt && paramInt < 400) {
      String str = paramHttpURLConnection.getHeaderField("Location");
      if (str == null) {
        b.c("Could not get redirect location from a " + paramInt + " redirect.");
        this.a.a(AdRequest.ErrorCode.d);
        a();
        return;
      } 
      a(paramHttpURLConnection);
      this.f = str;
      return;
    } 
    if (paramInt == 200) {
      a(paramHttpURLConnection);
      String str = AdUtil.a(new InputStreamReader(paramHttpURLConnection.getInputStream())).trim();
      b.a("Response content is: " + str);
      if (TextUtils.isEmpty(str)) {
        b.a("Response message is null or zero length: " + str);
        this.a.a(AdRequest.ErrorCode.b);
        a();
        return;
      } 
      this.a.a(str, this.f);
      a();
      return;
    } 
    if (paramInt == 400) {
      b.c("Bad request");
      this.a.a(AdRequest.ErrorCode.a);
      a();
      return;
    } 
    b.c("Invalid response code: " + paramInt);
    this.a.a(AdRequest.ErrorCode.d);
    a();
  }
  
  private void b() {
    while (!this.d) {
      null = new URL(this.f);
      HttpURLConnection httpURLConnection = this.c.a(null);
      try {
        a((Context)(this.b.h()).f.a(), httpURLConnection);
        AdUtil.a(httpURLConnection, (Context)(this.b.h()).f.a());
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.connect();
        a(httpURLConnection, httpURLConnection.getResponseCode());
      } finally {
        httpURLConnection.disconnect();
      } 
    } 
  }
  
  private void b(HttpURLConnection paramHttpURLConnection) {
    String str = paramHttpURLConnection.getHeaderField("X-Afma-Debug-Dialog");
    if (!TextUtils.isEmpty(str))
      this.a.e(str); 
  }
  
  private void c(HttpURLConnection paramHttpURLConnection) {
    String str = paramHttpURLConnection.getHeaderField("Content-Type");
    if (!TextUtils.isEmpty(str))
      this.a.b(str); 
  }
  
  private void d(HttpURLConnection paramHttpURLConnection) {
    String str = paramHttpURLConnection.getHeaderField("X-Afma-Mediation");
    if (!TextUtils.isEmpty(str))
      this.a.a(Boolean.valueOf(str).booleanValue()); 
  }
  
  private void e(HttpURLConnection paramHttpURLConnection) {
    String str = paramHttpURLConnection.getHeaderField("X-Afma-Interstitial-Timeout");
    if (!TextUtils.isEmpty(str))
      try {
        float f1 = Float.parseFloat(str);
        this.b.a((long)(f1 * 1000.0F));
      } catch (NumberFormatException numberFormatException) {
        b.d("Could not get timeout value: " + str, numberFormatException);
      }  
  }
  
  private void f(HttpURLConnection paramHttpURLConnection) {
    String str = paramHttpURLConnection.getHeaderField("X-Afma-Tracking-Urls");
    if (!TextUtils.isEmpty(str))
      for (String str1 : str.trim().split("\\s+"))
        this.b.b(str1);  
  }
  
  private void g(HttpURLConnection paramHttpURLConnection) {
    str = paramHttpURLConnection.getHeaderField("X-Afma-Click-Tracking-Urls");
    if (!TextUtils.isEmpty(str))
      for (String str : str.trim().split("\\s+"))
        this.a.a(str);  
  }
  
  private void h(HttpURLConnection paramHttpURLConnection) {
    String str = paramHttpURLConnection.getHeaderField("X-Afma-Refresh-Rate");
    if (!TextUtils.isEmpty(str)) {
      try {
        float f1 = Float.parseFloat(str);
        if (f1 > 0.0F) {
          this.b.a(f1);
          if (!this.b.r())
            this.b.f(); 
          return;
        } 
      } catch (NumberFormatException numberFormatException) {
        b.d("Could not get refresh value: " + str, numberFormatException);
        return;
      } 
    } else {
      return;
    } 
    if (this.b.r())
      this.b.e(); 
  }
  
  private void i(HttpURLConnection paramHttpURLConnection) {
    String str = paramHttpURLConnection.getHeaderField("X-Afma-Orientation");
    if (!TextUtils.isEmpty(str)) {
      if (str.equals("portrait")) {
        this.a.a(AdUtil.b());
        return;
      } 
    } else {
      return;
    } 
    if (str.equals("landscape"))
      this.a.a(AdUtil.a()); 
  }
  
  private void j(HttpURLConnection paramHttpURLConnection) {
    if (!TextUtils.isEmpty(paramHttpURLConnection.getHeaderField("X-Afma-Doritos-Cache-Life")))
      try {
        long l = Long.parseLong(paramHttpURLConnection.getHeaderField("X-Afma-Doritos-Cache-Life"));
        this.b.b(l);
      } catch (NumberFormatException numberFormatException) {
        b.e("Got bad value of Doritos cookie cache life from header: " + paramHttpURLConnection.getHeaderField("X-Afma-Doritos-Cache-Life") + ". Using default value instead.");
      }  
  }
  
  private void k(HttpURLConnection paramHttpURLConnection) {
    String str = paramHttpURLConnection.getHeaderField("Cache-Control");
    if (!TextUtils.isEmpty(str))
      this.a.c(str); 
  }
  
  private void l(HttpURLConnection paramHttpURLConnection) {
    String str = paramHttpURLConnection.getHeaderField("X-Afma-Ad-Size");
    if (!TextUtils.isEmpty(str))
      try {
        StringBuilder stringBuilder;
        String[] arrayOfString = str.split("x", 2);
        if (arrayOfString.length != 2) {
          stringBuilder = new StringBuilder();
          this();
          b.e(stringBuilder.append("Could not parse size header: ").append(str).toString());
          return;
        } 
        int i = Integer.parseInt((String)stringBuilder[0]);
        int j = Integer.parseInt((String)stringBuilder[1]);
        this.a.a(new AdSize(i, j));
      } catch (NumberFormatException numberFormatException) {
        b.e("Could not parse size header: " + str);
      }  
  }
  
  void a() {
    this.d = true;
  }
  
  void a(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield g : Ljava/lang/Thread;
    //   6: ifnonnull -> 40
    //   9: aload_0
    //   10: aload_1
    //   11: putfield f : Ljava/lang/String;
    //   14: aload_0
    //   15: iconst_0
    //   16: putfield d : Z
    //   19: new java/lang/Thread
    //   22: astore_1
    //   23: aload_1
    //   24: aload_0
    //   25: invokespecial <init> : (Ljava/lang/Runnable;)V
    //   28: aload_0
    //   29: aload_1
    //   30: putfield g : Ljava/lang/Thread;
    //   33: aload_0
    //   34: getfield g : Ljava/lang/Thread;
    //   37: invokevirtual start : ()V
    //   40: aload_0
    //   41: monitorexit
    //   42: return
    //   43: astore_1
    //   44: aload_0
    //   45: monitorexit
    //   46: aload_1
    //   47: athrow
    // Exception table:
    //   from	to	target	type
    //   2	40	43	finally
  }
  
  public void a(boolean paramBoolean) {
    this.e = paramBoolean;
  }
  
  public void run() {
    try {
      b();
    } catch (MalformedURLException malformedURLException) {
      b.b("Received malformed ad url from javascript.", malformedURLException);
      this.a.a(AdRequest.ErrorCode.d);
    } catch (IOException iOException) {
      b.d("IOException connecting to ad url.", iOException);
      this.a.a(AdRequest.ErrorCode.c);
    } catch (Throwable throwable) {
      b.b("An unknown error occurred in AdResponseLoader.", throwable);
      this.a.a(AdRequest.ErrorCode.d);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\internal\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */