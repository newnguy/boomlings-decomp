package com.tapjoy;

import android.os.AsyncTask;

/* loaded from: classes.dex */
class r extends AsyncTask {
    final /* synthetic */ TapjoyDailyRewardAdWebView a;

    private r(TapjoyDailyRewardAdWebView tapjoyDailyRewardAdWebView) {
        this.a = tapjoyDailyRewardAdWebView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a */
    public void onPostExecute(Boolean bool) {
        if (TapjoyDailyRewardAdWebView.a(this.a) != null) {
            TapjoyDailyRewardAdWebView.a(this.a).loadUrl("javascript:window.onorientationchange();");
        }
    }
}
