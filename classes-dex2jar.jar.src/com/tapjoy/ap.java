package com.tapjoy;

import android.graphics.BitmapFactory;
import java.net.URL;
import java.net.URLConnection;

class ap implements Runnable {
  final ao a;
  
  ap(ao paramao) {}
  
  public void run() {
    boolean bool2 = false;
    String str = h.c();
    str = str + "&publisher_user_id=" + h.e();
    str = (new am()).b("https://ws.tapjoyads.com/videos?", str);
    boolean bool1 = bool2;
    if (str != null) {
      bool1 = bool2;
      if (str.length() > 0)
        bool1 = ao.a(this.a, str); 
    } 
    if (bool1) {
      ao.a(this.a);
      if ("https://s3.amazonaws.com/tapjoy/videos/assets/watermark.png" != null && "https://s3.amazonaws.com/tapjoy/videos/assets/watermark.png".length() > 0)
        try {
          URL uRL = new URL();
          this("https://s3.amazonaws.com/tapjoy/videos/assets/watermark.png");
          URLConnection uRLConnection = uRL.openConnection();
          uRLConnection.setConnectTimeout(15000);
          uRLConnection.setReadTimeout(25000);
          uRLConnection.connect();
          ao.a(BitmapFactory.decodeStream(uRL.openConnection().getInputStream()));
        } catch (Exception exception) {
          aj.b("TapjoyVideo", "e: " + exception.toString());
        }  
      ao.b(this.a);
      ao.a(this.a, true);
      if (ao.c(this.a)) {
        aj.a("TapjoyVideo", "trying to cache because of cache_auto flag...");
        this.a.d();
      } 
      aj.a("TapjoyVideo", "------------------------------");
      aj.a("TapjoyVideo", "------------------------------");
      aj.a("TapjoyVideo", "INIT DONE!");
      aj.a("TapjoyVideo", "------------------------------");
      return;
    } 
    ao.b(2);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\tapjoy\ap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */