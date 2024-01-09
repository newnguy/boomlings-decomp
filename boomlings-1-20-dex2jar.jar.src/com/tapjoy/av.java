package com.tapjoy;

class av implements Runnable {
  final TapjoyVideoView a;
  
  av(TapjoyVideoView paramTapjoyVideoView) {}
  
  public void run() {
    TapjoyVideoView.c(this.a).setText("" + TapjoyVideoView.b(this.a) + " seconds");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\tapjoy\av.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */