package com.tapjoy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import java.util.Hashtable;

/* loaded from: classes.dex */
public class af extends WebViewClient {
    final /* synthetic */ TapjoyFullScreenAdWebView a;

    /* JADX INFO: Access modifiers changed from: private */
    public af(TapjoyFullScreenAdWebView tapjoyFullScreenAdWebView) {
        this.a = tapjoyFullScreenAdWebView;
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        ProgressBar progressBar;
        progressBar = this.a.c;
        progressBar.setVisibility(8);
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        ProgressBar progressBar;
        ProgressBar progressBar2;
        progressBar = this.a.c;
        progressBar.setVisibility(0);
        progressBar2 = this.a.c;
        progressBar2.bringToFront();
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Dialog dialog;
        aj.a("Full Screen Ad", "URL = [" + str + "]");
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
                            aj.a("Full Screen Ad", "k:v: " + str3 + ", " + decode);
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
                aj.a("Full Screen Ad", "k:v: " + str3 + ", " + decode2);
                hashtable.put(str3, decode2);
            }
            String str4 = (String) hashtable.get("video_id");
            String str5 = (String) hashtable.get("amount");
            String str6 = (String) hashtable.get("currency_name");
            String str7 = (String) hashtable.get("click_url");
            String str8 = (String) hashtable.get("video_complete_url");
            String str9 = (String) hashtable.get("video_url");
            aj.a("Full Screen Ad", "videoID: " + str4);
            aj.a("Full Screen Ad", "currencyAmount: " + str5);
            aj.a("Full Screen Ad", "currencyName: " + str6);
            aj.a("Full Screen Ad", "clickURL: " + str7);
            aj.a("Full Screen Ad", "webviewURL: " + str8);
            aj.a("Full Screen Ad", "videoURL: " + str9);
            if (ao.a().a(str4, str6, str5, str7, str8, str9)) {
                aj.a("Full Screen Ad", "VIDEO");
            } else {
                aj.b("Full Screen Ad", "Unable to play video: " + str4);
                this.a.d = new AlertDialog.Builder(this.a).setTitle("").setMessage("Unable to play video.").setPositiveButton("OK", new ag(this)).create();
                try {
                    dialog = this.a.d;
                    dialog.show();
                } catch (Exception e) {
                    aj.b("Full Screen Ad", "e: " + e.toString());
                }
            }
        } else if (str.contains("showOffers")) {
            aj.a("Full Screen Ad", "show offers");
            this.a.a();
        } else if (str.contains("dismiss")) {
            aj.a("Full Screen Ad", "dismiss");
            this.a.b();
        } else if (str.contains("ws.tapjoyads.com")) {
            aj.a("Full Screen Ad", "Open redirecting URL = [" + str + "]");
            webView.loadUrl(str);
        } else {
            aj.a("Full Screen Ad", "Opening URL in new browser = [" + str + "]");
            this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        }
        return true;
    }
}
