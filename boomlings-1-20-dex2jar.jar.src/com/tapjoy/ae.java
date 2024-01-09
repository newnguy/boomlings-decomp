package com.tapjoy;

import android.os.AsyncTask;

class ae extends AsyncTask {
  final TapjoyFullScreenAdWebView a;
  
  private ae(TapjoyFullScreenAdWebView paramTapjoyFullScreenAdWebView) {}
  
  protected Boolean a(Void... paramVarArgs) {
    try {
      Thread.sleep(200L);
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    } 
    return Boolean.valueOf(true);
  }
  
  protected void a(Boolean paramBoolean) {
    if (TapjoyFullScreenAdWebView.a(this.a) != null)
      TapjoyFullScreenAdWebView.a(this.a).loadUrl("javascript:window.onorientationchange();"); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\tapjoy\ae.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */