package com.google.ads;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.webkit.WebView;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes.dex */
public final class ag {
    private static final com.google.ads.internal.a a = (com.google.ads.internal.a) com.google.ads.internal.a.a.b();

    public static void a(Activity activity) {
        new Thread(new i(activity)).start();
    }

    public static void a(Activity activity, WebView webView, String str) {
        new Thread(new j(activity, webView, str)).start();
    }

    public static void a(WebView webView, String str) {
        a.a(webView, String.format(Locale.US, "(G_resizeIframe(%s))", str));
    }

    public static void a(WebView webView, boolean z) {
        a.a(webView, String.format(Locale.US, "(G_updatePlusOne(%b))", Boolean.valueOf(z)));
    }

    public static boolean a(Context context, long j) {
        return a(context, j, PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()));
    }

    static boolean a(Context context, long j, SharedPreferences sharedPreferences) {
        return ah.a(context) && !(sharedPreferences.contains("drt") && sharedPreferences.contains("drt_ts") && sharedPreferences.getLong("drt_ts", 0L) >= new Date().getTime() - j);
    }
}
