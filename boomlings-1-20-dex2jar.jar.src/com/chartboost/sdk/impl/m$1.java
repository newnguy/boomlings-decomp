package com.chartboost.sdk.impl;

import android.content.Context;
import android.os.AsyncTask;
import java.net.HttpURLConnection;
import java.net.URL;

class m$1 extends AsyncTask {
  final m a;
  
  private final String b;
  
  private final Context c;
  
  m$1(m paramm, String paramString, Context paramContext) {}
  
  public String a(Void... paramVarArgs) {
    String str1;
    String str2 = null;
    try {
      null = new URL();
      this(this.b);
      HttpURLConnection httpURLConnection = (HttpURLConnection)null.openConnection();
      try {
        httpURLConnection.setInstanceFollowRedirects(false);
        str2 = httpURLConnection.getHeaderField("Location");
        String str = str2;
        if (str2 == null)
          str = this.b; 
        return str2;
      } catch (Exception exception) {
      
      } finally {
        if (httpURLConnection != null)
          httpURLConnection.disconnect(); 
      } 
      return null;
    } catch (Exception exception) {
      HttpURLConnection httpURLConnection = null;
      return null;
    } finally {
      paramVarArgs = null;
    } 
    if (str1 != null)
      str1.disconnect(); 
    throw paramVarArgs;
  }
  
  public void a(String paramString) {
    m.a(this.a, paramString, this.c);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\m$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */