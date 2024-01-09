package com.tapjoy;

import android.os.AsyncTask;
import android.webkit.WebView;

/* loaded from: classes.dex */
class r extends AsyncTask {
    final /* synthetic */ TapjoyDailyRewardAdWebView a;

    /* JADX INFO: Access modifiers changed from: private */
    public r(TapjoyDailyRewardAdWebView tapjoyDailyRewardAdWebView) {
        this.a = tapjoyDailyRewardAdWebView;
    }

    @Override // android.os.AsyncTask
    /* renamed from: a */
    public Boolean doInBackground(Void... voidArr) {
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // android.os.AsyncTask
    /* renamed from: a */
    public void onPostExecute(Boolean bool) {
        WebView webView;
        WebView webView2;
        webView = this.a.b;
        if (webView != null) {
            webView2 = this.a.b;
            webView2.loadUrl("javascript:window.onorientationchange();");
        }
    }
}
