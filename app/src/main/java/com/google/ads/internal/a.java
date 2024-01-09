package com.google.ads.internal;

import android.net.Uri;
import android.webkit.WebView;
import com.google.ads.util.AdUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a {
    private static final a d = new a();
    public static final com.google.ads.util.f a = new n();
    public static final Map b = Collections.unmodifiableMap(new o());
    public static final Map c = Collections.unmodifiableMap(new m());

    public String a(Uri uri, HashMap hashMap) {
        if (!c(uri)) {
            if (b(uri)) {
                return uri.getPath();
            }
            com.google.ads.util.b.e("Message was neither a GMSG nor an AMSG.");
            return null;
        }
        String host = uri.getHost();
        if (host == null) {
            com.google.ads.util.b.e("An error occurred while parsing the AMSG parameters.");
            return null;
        } else if (host.equals("launch")) {
            hashMap.put("a", "intent");
            hashMap.put("u", hashMap.get("url"));
            hashMap.remove("url");
            return "/open";
        } else if (host.equals("closecanvas")) {
            return "/close";
        } else {
            if (host.equals("log")) {
                return "/log";
            }
            com.google.ads.util.b.e("An error occurred while parsing the AMSG: " + uri.toString());
            return null;
        }
    }

    public void a(WebView webView) {
        a(webView, "onshow", "{'version': 'afma-sdk-a-v6.2.1'}");
    }

    public void a(WebView webView, String str) {
        com.google.ads.util.b.a("Sending JS to a WebView: " + str);
        webView.loadUrl("javascript:" + str);
    }

    public void a(WebView webView, String str, String str2) {
        if (str2 != null) {
            a(webView, "AFMA_ReceiveMessage('" + str + "', " + str2 + ");");
        } else {
            a(webView, "AFMA_ReceiveMessage('" + str + "');");
        }
    }

    public void a(WebView webView, Map map) {
        a(webView, "openableURLs", new JSONObject(map).toString());
    }

    public void a(d dVar, Map map, Uri uri, WebView webView) {
        HashMap b2 = AdUtil.b(uri);
        if (b2 == null) {
            com.google.ads.util.b.e("An error occurred while parsing the message parameters.");
            return;
        }
        String a2 = a(uri, b2);
        if (a2 == null) {
            com.google.ads.util.b.e("An error occurred while parsing the message.");
            return;
        }
        com.google.ads.n nVar = (com.google.ads.n) map.get(a2);
        if (nVar == null) {
            com.google.ads.util.b.e("No AdResponse found, <message: " + a2 + ">");
        } else {
            nVar.a(dVar, b2, webView);
        }
    }

    public boolean a(Uri uri) {
        if (uri == null || !uri.isHierarchical()) {
            return false;
        }
        return b(uri) || c(uri);
    }

    public void b(WebView webView) {
        a(webView, "onhide", null);
    }

    public boolean b(Uri uri) {
        String authority;
        String scheme = uri.getScheme();
        return scheme != null && scheme.equals("gmsg") && (authority = uri.getAuthority()) != null && authority.equals("mobileads.google.com");
    }

    public boolean c(Uri uri) {
        String scheme = uri.getScheme();
        return scheme != null && scheme.equals("admob");
    }
}
