package com.google.ads.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;
import com.google.ads.AdActivity;
import com.google.ads.util.AdUtil;

/* loaded from: classes.dex */
public class l implements DownloadListener {
    final /* synthetic */ AdWebView a;

    public l(AdWebView adWebView) {
        this.a = adWebView;
    }

    @Override // android.webkit.DownloadListener
    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            AdActivity d = this.a.d();
            if (d == null || !AdUtil.a(intent, d)) {
                return;
            }
            d.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            com.google.ads.util.b.a("Couldn't find an Activity to view url/mimetype: " + str + " / " + str4);
        } catch (Throwable th) {
            com.google.ads.util.b.b("Unknown error trying to start activity to view URL: " + str, th);
        }
    }
}
