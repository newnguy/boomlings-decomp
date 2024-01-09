package com.google.ads;

import android.app.Activity;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.webkit.WebView;
import com.google.ads.internal.AdVideoView;
import com.google.ads.internal.AdWebView;
import com.google.ads.util.AdUtil;
import java.util.HashMap;

/* loaded from: classes.dex */
public class ab implements n {
    private static final com.google.ads.internal.a a = (com.google.ads.internal.a) com.google.ads.internal.a.a.b();

    protected int a(HashMap hashMap, String str, int i, DisplayMetrics displayMetrics) {
        String str2 = (String) hashMap.get(str);
        if (str2 != null) {
            try {
                return (int) TypedValue.applyDimension(1, Integer.parseInt(str2), displayMetrics);
            } catch (NumberFormatException e) {
                com.google.ads.util.b.a("Could not parse \"" + str + "\" in a video gmsg: " + str2);
                return i;
            }
        }
        return i;
    }

    @Override // com.google.ads.n
    public void a(com.google.ads.internal.d dVar, HashMap hashMap, WebView webView) {
        String str = (String) hashMap.get("action");
        if (str == null) {
            com.google.ads.util.b.a("No \"action\" parameter in a video gmsg.");
        } else if (!(webView instanceof AdWebView)) {
            com.google.ads.util.b.a("Could not get adWebView for a video gmsg.");
        } else {
            AdWebView adWebView = (AdWebView) webView;
            AdActivity d = adWebView.d();
            if (d == null) {
                com.google.ads.util.b.a("Could not get adActivity for a video gmsg.");
                return;
            }
            boolean equals = str.equals("new");
            boolean equals2 = str.equals("position");
            if (equals || equals2) {
                DisplayMetrics a2 = AdUtil.a((Activity) d);
                int a3 = a(hashMap, "x", 0, a2);
                int a4 = a(hashMap, "y", 0, a2);
                int a5 = a(hashMap, "w", -1, a2);
                int a6 = a(hashMap, "h", -1, a2);
                if (equals && d.a() == null) {
                    d.b(a3, a4, a5, a6);
                    return;
                } else {
                    d.a(a3, a4, a5, a6);
                    return;
                }
            }
            AdVideoView a7 = d.a();
            if (a7 == null) {
                a.a(adWebView, "onVideoEvent", "{'event': 'error', 'what': 'no_video_view'}");
            } else if (str.equals("click")) {
                DisplayMetrics a8 = AdUtil.a((Activity) d);
                int a9 = a(hashMap, "x", 0, a8);
                int a10 = a(hashMap, "y", 0, a8);
                long uptimeMillis = SystemClock.uptimeMillis();
                a7.a(MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, a9, a10, 0));
            } else if (str.equals("controls")) {
                String str2 = (String) hashMap.get("enabled");
                if (str2 == null) {
                    com.google.ads.util.b.a("No \"enabled\" parameter in a controls video gmsg.");
                } else if (str2.equals("true")) {
                    a7.setMediaControllerEnabled(true);
                } else {
                    a7.setMediaControllerEnabled(false);
                }
            } else if (str.equals("currentTime")) {
                String str3 = (String) hashMap.get("time");
                if (str3 == null) {
                    com.google.ads.util.b.a("No \"time\" parameter in a currentTime video gmsg.");
                    return;
                }
                try {
                    a7.a((int) (Float.parseFloat(str3) * 1000.0f));
                } catch (NumberFormatException e) {
                    com.google.ads.util.b.a("Could not parse \"time\" parameter: " + str3);
                }
            } else if (str.equals("hide")) {
                a7.setVisibility(4);
            } else if (str.equals("load")) {
                a7.b();
            } else if (str.equals("pause")) {
                a7.c();
            } else if (str.equals("play")) {
                a7.d();
            } else if (str.equals("show")) {
                a7.setVisibility(0);
            } else if (str.equals("src")) {
                a7.setSrc((String) hashMap.get("src"));
            } else {
                com.google.ads.util.b.a("Unknown video action: " + str);
            }
        }
    }
}
