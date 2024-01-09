package com.b.a;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class p extends WebViewClient {
    final /* synthetic */ n a;

    private p(n nVar) {
        this.a = nVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ p(n nVar, p pVar) {
        this(nVar);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        ProgressDialog progressDialog;
        FrameLayout frameLayout;
        WebView webView2;
        ImageView imageView;
        super.onPageFinished(webView, str);
        progressDialog = this.a.f;
        progressDialog.dismiss();
        frameLayout = this.a.i;
        frameLayout.setBackgroundColor(0);
        webView2 = this.a.h;
        webView2.setVisibility(0);
        imageView = this.a.g;
        imageView.setVisibility(0);
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        ProgressDialog progressDialog;
        s.a("Facebook-WebView", "Webview loading URL: " + str);
        super.onPageStarted(webView, str, bitmap);
        progressDialog = this.a.f;
        progressDialog.show();
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        i iVar;
        super.onReceivedError(webView, i, str, str2);
        iVar = this.a.e;
        iVar.onError(new e(str, i, str2));
        this.a.dismiss();
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        i iVar;
        i iVar2;
        i iVar3;
        i iVar4;
        s.a("Facebook-WebView", "Redirect URL: " + str);
        if (!str.startsWith("fbconnect://success")) {
            if (str.startsWith("fbconnect://cancel")) {
                iVar = this.a.e;
                iVar.onCancel();
                this.a.dismiss();
                return true;
            } else if (str.contains("touch")) {
                return false;
            } else {
                this.a.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            }
        }
        Bundle b = s.b(str);
        String string = b.getString("error");
        if (string == null) {
            string = b.getString("error_type");
        }
        if (string == null) {
            iVar4 = this.a.e;
            iVar4.onComplete(b);
        } else if (string.equals("access_denied") || string.equals("OAuthAccessDeniedException")) {
            iVar2 = this.a.e;
            iVar2.onCancel();
        } else {
            iVar3 = this.a.e;
            iVar3.onFacebookError(new m(string));
        }
        this.a.dismiss();
        return true;
    }
}
