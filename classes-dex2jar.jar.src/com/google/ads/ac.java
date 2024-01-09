package com.google.ads;

import android.content.Context;
import com.google.ads.util.AdUtil;
import com.google.ads.util.b;
import java.net.HttpURLConnection;
import java.net.URL;

public class ac implements Runnable {
  private final Context a;
  
  private final String b;
  
  public ac(String paramString, Context paramContext) {
    this.b = paramString;
    this.a = paramContext;
  }
  
  protected HttpURLConnection a(URL paramURL) {
    return (HttpURLConnection)paramURL.openConnection();
  }
  
  public void run() {
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      b.a(stringBuilder.append("Pinging URL: ").append(this.b).toString());
      URL uRL = new URL();
      this(this.b);
      HttpURLConnection httpURLConnection = a(uRL);
      try {
        AdUtil.a(httpURLConnection, this.a);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.connect();
        int i = httpURLConnection.getResponseCode();
        if (i < 200 || i >= 300) {
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          b.e(stringBuilder1.append("Did not receive 2XX (got ").append(i).append(") from pinging URL: ").append(this.b).toString());
        } 
        return;
      } finally {
        httpURLConnection.disconnect();
      } 
    } catch (Throwable throwable) {
      b.d("Unable to ping the URL: " + this.b, throwable);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\ac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */