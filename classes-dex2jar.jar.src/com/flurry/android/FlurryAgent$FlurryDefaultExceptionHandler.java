package com.flurry.android;

public class FlurryAgent$FlurryDefaultExceptionHandler implements Thread.UncaughtExceptionHandler {
  private Thread.UncaughtExceptionHandler a = Thread.getDefaultUncaughtExceptionHandler();
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
    try {
      FlurryAgent.d().a(paramThrowable);
    } catch (Throwable throwable) {
      bm.b("FlurryAgent", "", throwable);
    } 
    if (this.a != null)
      this.a.uncaughtException(paramThread, paramThrowable); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\FlurryAgent$FlurryDefaultExceptionHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */