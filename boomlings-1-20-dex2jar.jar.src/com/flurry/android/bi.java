package com.flurry.android;

import java.net.Socket;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

final class bi extends SSLSocketFactory {
  private SSLContext a = SSLContext.getInstance("TLS");
  
  public bi(be parambe, KeyStore paramKeyStore) {
    super(paramKeyStore);
    o o = new o();
    this.a.init(null, new TrustManager[] { o }, null);
  }
  
  public final Socket createSocket() {
    return this.a.getSocketFactory().createSocket();
  }
  
  public final Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean) {
    return this.a.getSocketFactory().createSocket(paramSocket, paramString, paramInt, paramBoolean);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\bi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */