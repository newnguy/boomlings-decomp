package com.tapjoy;

import android.os.AsyncTask;

class r extends AsyncTask {
  final TapjoyDailyRewardAdWebView a;
  
  private r(TapjoyDailyRewardAdWebView paramTapjoyDailyRewardAdWebView) {}
  
  protected Boolean a(Void... paramVarArgs) {
    try {
      Thread.sleep(200L);
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    } 
    return Boolean.valueOf(true);
  }
  
  protected void a(Boolean paramBoolean) {
    if (TapjoyDailyRewardAdWebView.a(this.a) != null)
      TapjoyDailyRewardAdWebView.a(this.a).loadUrl("javascript:window.onorientationchange();"); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\tapjoy\r.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */