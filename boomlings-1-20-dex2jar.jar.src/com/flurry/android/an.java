package com.flurry.android;

abstract class an implements Runnable {
  public abstract void a();
  
  public final void run() {
    try {
      a();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      bm.b("FlurryAgent", "", throwable);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\an.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */