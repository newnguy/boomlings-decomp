package com.tapjoy;

import android.os.AsyncTask;

/* loaded from: classes.dex */
class ae extends AsyncTask {
    final /* synthetic */ TapjoyFullScreenAdWebView a;

    private ae(TapjoyFullScreenAdWebView tapjoyFullScreenAdWebView) {
        this.a = tapjoyFullScreenAdWebView;
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
        if (TapjoyFullScreenAdWebView.a(this.a) != null) {
            TapjoyFullScreenAdWebView.a(this.a).loadUrl("javascript:window.onorientationchange();");
        }
    }
}
