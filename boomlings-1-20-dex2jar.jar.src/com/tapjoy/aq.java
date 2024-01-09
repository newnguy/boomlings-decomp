package com.tapjoy;

import android.os.Environment;

class aq implements Runnable {
  final ao a;
  
  aq(ao paramao) {}
  
  public void run() {
    aj.a("TapjoyVideo", "--- cacheAllVideos called ---");
    int i = 0;
    while (!ao.d(this.a)) {
      int j = i;
      try {
        Thread.sleep(500L);
        j = (int)(i + 500L);
        i = j;
        if (j > 10000L) {
          aj.b("TapjoyVideo", "Error during cacheVideos.  Timeout while waiting for initVideos to finish.");
          return;
        } 
      } catch (Exception exception) {
        aj.b("TapjoyVideo", "Exception in cacheAllVideos: " + exception.toString());
        i = j;
      } 
    } 
    aj.a("TapjoyVideo", "cacheVideos connection_type: " + h.g());
    aj.a("TapjoyVideo", "cache3g: " + ao.e(this.a));
    aj.a("TapjoyVideo", "cacheWifi: " + ao.f(this.a));
    if ((ao.e(this.a) && h.g().equals("mobile")) || (ao.f(this.a) && h.g().equals("wifi"))) {
      if (!"mounted".equals(Environment.getExternalStorageState())) {
        aj.a("TapjoyVideo", "Media storage unavailable.  Aborting caching videos.");
        ao.b(1);
        return;
      } 
      while (ao.g(this.a).size() < ao.h(this.a) && ao.i(this.a).size() > 0) {
        String str = ((as)ao.j(this.a).get(ao.i(this.a).elementAt(0))).c;
        ao.b(this.a, str);
      } 
    } else {
      aj.a("TapjoyVideo", " * Skipping caching videos because of video flags and connection_type...");
    } 
    ao.k(this.a);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\tapjoy\aq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */