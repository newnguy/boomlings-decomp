package com.tapjoy;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.Hashtable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class e extends WebViewClient {
    final /* synthetic */ TJCOffersWebView a;

    private e(TJCOffersWebView tJCOffersWebView) {
        this.a = tJCOffersWebView;
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        TJCOffersWebView.a(this.a).setVisibility(8);
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        TJCOffersWebView.a(this.a).setVisibility(0);
        TJCOffersWebView.a(this.a).bringToFront();
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        aj.a("Offers", "URL = [" + str + "]");
        if (str.startsWith("tjvideo://")) {
            Hashtable hashtable = new Hashtable();
            String str2 = "";
            String str3 = "";
            boolean z = false;
            for (int indexOf = str.indexOf("://") + "://".length(); indexOf < str.length() && indexOf != -1; indexOf++) {
                char charAt = str.charAt(indexOf);
                if (z) {
                    if (z) {
                        if (charAt == '&') {
                            String decode = Uri.decode(str2);
                            str2 = "";
                            aj.a("Offers", "k:v: " + str3 + ", " + decode);
                            hashtable.put(str3, decode);
                            z = false;
                        } else {
                            str2 = str2 + charAt;
                        }
                    }
                } else if (charAt == '=') {
                    str3 = Uri.decode(str2);
                    str2 = "";
                    z = true;
                } else {
                    str2 = str2 + charAt;
                }
            }
            if (z && str2.length() > 0) {
                String decode2 = Uri.decode(str2);
                aj.a("Offers", "k:v: " + str3 + ", " + decode2);
                hashtable.put(str3, decode2);
            }
            String str4 = (String) hashtable.get("video_id");
            String str5 = (String) hashtable.get("amount");
            String str6 = (String) hashtable.get("currency_name");
            String str7 = (String) hashtable.get("click_url");
            String str8 = (String) hashtable.get("video_complete_url");
            String str9 = (String) hashtable.get("video_url");
            aj.a("Offers", "videoID: " + str4);
            aj.a("Offers", "currencyAmount: " + str5);
            aj.a("Offers", "currencyName: " + str6);
            aj.a("Offers", "clickURL: " + str7);
            aj.a("Offers", "webviewURL: " + str8);
            aj.a("Offers", "videoURL: " + str9);
            if (ao.a().a(str4, str6, str5, str7, str8, str9)) {
                aj.a("Offers", "VIDEO");
            } else {
                aj.b("Offers", "Unable to play video: " + str4);
                TJCOffersWebView.a(this.a, new AlertDialog.Builder(this.a).setTitle("").setMessage("Unable to play video.").setPositiveButton("OK", new f(this)).create());
                try {
                    TJCOffersWebView.b(this.a).show();
                } catch (Exception e) {
                    aj.b("Offers", "e: " + e.toString());
                }
            }
        } else if (str.contains("ws.tapjoyads.com")) {
            aj.a("Offers", "Open redirecting URL = [" + str + "]");
            webView.loadUrl(str);
        } else {
            aj.a("Offers", "Opening URL in new browser = [" + str + "]");
            this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            aj.a("Offers", "skipOfferWall: " + TJCOffersWebView.c(this.a));
            if (TJCOffersWebView.c(this.a)) {
                this.a.finish();
            }
        }
        return true;
    }
}
