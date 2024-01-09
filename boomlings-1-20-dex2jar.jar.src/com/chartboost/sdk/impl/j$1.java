package com.chartboost.sdk.impl;

import android.app.Application;
import android.content.Context;
import com.chartboost.sdk.Chartboost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

class j$1 extends DefaultHttpClient {
  private final Application a;
  
  j$1(Application paramApplication) {}
  
  protected SocketFactory a() {
    SSLSocketFactory sSLSocketFactory;
    try {
      Class<?> clazz = Class.forName("android.net.SSLSessionCache");
      Object object = clazz.getConstructor(new Class[] { Context.class }).newInstance(new Object[] { this.a });
      SocketFactory socketFactory = (SocketFactory)Class.forName("android.net.SSLCertificateSocketFactory").getMethod("getHttpSocketFactory", new Class[] { int.class, clazz }).invoke(null, new Object[] { Integer.valueOf(Chartboost.sharedChartboost().getTimeout()), object });
    } catch (Exception exception) {
      sSLSocketFactory = SSLSocketFactory.getSocketFactory();
    } 
    return (SocketFactory)sSLSocketFactory;
  }
  
  protected ClientConnectionManager createClientConnectionManager() {
    SchemeRegistry schemeRegistry = new SchemeRegistry();
    schemeRegistry.register(new Scheme("http", (SocketFactory)PlainSocketFactory.getSocketFactory(), 80));
    schemeRegistry.register(new Scheme("https", a(), 443));
    HttpParams httpParams = getParams();
    HttpConnectionParams.setConnectionTimeout(httpParams, Chartboost.sharedChartboost().getTimeout());
    HttpConnectionParams.setSoTimeout(httpParams, Chartboost.sharedChartboost().getTimeout());
    HttpProtocolParams.setUserAgent(httpParams, j.a(this.a, HttpProtocolParams.getUserAgent(httpParams)));
    return (ClientConnectionManager)new ThreadSafeClientConnManager(httpParams, schemeRegistry);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\j$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */