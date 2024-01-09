package com.google.ads;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import com.flurry.android.Constants;
import java.util.HashMap;

/* loaded from: classes.dex */
public class ai implements n {

    /* loaded from: classes.dex */
    public enum b {
        AD("ad"),
        APP("app");
        
        public String c;

        b(String str) {
            this.c = str;
        }
    }

    @Override // com.google.ads.n
    public void a(com.google.ads.internal.d dVar, HashMap hashMap, WebView webView) {
        Context context = (Context) dVar.h().f.a();
        String str = (String) hashMap.get("a");
        if (str != null) {
            if (str.equals("resize")) {
                ag.a(webView, (String) hashMap.get("u"));
                return;
            } else if (str.equals("state")) {
                ag.a((Activity) dVar.h().e.a(), webView, (String) hashMap.get("u"));
                return;
            }
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.google.android.apps.plus", "com.google.android.apps.circles.platform.PlusOneActivity"));
        if (ah.a(intent, context)) {
            AdActivity.a(dVar, new com.google.ads.internal.e("plusone", hashMap));
        } else if (ah.a(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.google.android.apps.plus")), context)) {
            if (TextUtils.isEmpty((CharSequence) hashMap.get("d")) || TextUtils.isEmpty((CharSequence) hashMap.get("o")) || TextUtils.isEmpty((CharSequence) hashMap.get(Constants.ALIGN_CENTER))) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("u", "market://details?id=com.google.android.apps.plus");
                AdActivity.a(dVar, new com.google.ads.internal.e("intent", hashMap2));
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage((CharSequence) hashMap.get("d")).setPositiveButton((CharSequence) hashMap.get("o"), new ak(dVar)).setNegativeButton((CharSequence) hashMap.get(Constants.ALIGN_CENTER), new aj());
            builder.create().show();
        }
    }
}
