package com.google.ads.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.ads.AdActivity;
import com.google.ads.internal.AdWebView;
import com.google.ads.l;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/* loaded from: classes.dex */
public class g {

    /* loaded from: classes.dex */
    public class a extends WebChromeClient {
        private final com.google.ads.m a;

        public a(com.google.ads.m mVar) {
            this.a = mVar;
        }

        private static void a(AlertDialog.Builder builder, Context context, String str, String str2, JsPromptResult jsPromptResult) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(1);
            TextView textView = new TextView(context);
            textView.setText(str);
            EditText editText = new EditText(context);
            editText.setText(str2);
            linearLayout.addView(textView);
            linearLayout.addView(editText);
            builder.setView(linearLayout).setPositiveButton(17039370, new p(jsPromptResult, editText)).setNegativeButton(17039360, new o(jsPromptResult)).setOnCancelListener(new n(jsPromptResult)).create().show();
        }

        private static void a(AlertDialog.Builder builder, String str, JsResult jsResult) {
            builder.setMessage(str).setPositiveButton(17039370, new m(jsResult)).setNegativeButton(17039360, new l(jsResult)).setOnCancelListener(new k(jsResult)).create().show();
        }

        private static boolean a(WebView webView, String str, String str2, String str3, JsResult jsResult, JsPromptResult jsPromptResult, boolean z) {
            AdActivity d;
            if (!(webView instanceof AdWebView) || (d = ((AdWebView) webView).d()) == null) {
                return false;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(d);
            builder.setTitle(str);
            if (z) {
                a(builder, d, str2, str3, jsPromptResult);
            } else {
                a(builder, str2, jsResult);
            }
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public void onCloseWindow(WebView webView) {
            if (webView instanceof AdWebView) {
                ((AdWebView) webView).a();
            }
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            String str = "JS: " + consoleMessage.message() + " (" + consoleMessage.sourceId() + ":" + consoleMessage.lineNumber() + ")";
            switch (j.a[consoleMessage.messageLevel().ordinal()]) {
                case 1:
                    com.google.ads.util.b.b(str);
                    break;
                case 2:
                    com.google.ads.util.b.e(str);
                    break;
                case 3:
                case 4:
                    com.google.ads.util.b.c(str);
                    break;
                case 5:
                    com.google.ads.util.b.a(str);
                    break;
            }
            return super.onConsoleMessage(consoleMessage);
        }

        @Override // android.webkit.WebChromeClient
        public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
            l.a aVar = (l.a) ((com.google.ads.l) this.a.a.a()).a.a();
            long longValue = ((Long) aVar.i.a()).longValue() - j3;
            if (longValue <= 0) {
                quotaUpdater.updateQuota(j);
                return;
            }
            if (j == 0) {
                if (j2 > longValue || j2 > ((Long) aVar.j.a()).longValue()) {
                    j2 = 0;
                }
            } else if (j2 == 0) {
                j2 = Math.min(Math.min(((Long) aVar.k.a()).longValue(), longValue) + j, ((Long) aVar.j.a()).longValue());
            } else {
                if (j2 <= Math.min(((Long) aVar.j.a()).longValue() - j, longValue)) {
                    j += j2;
                }
                j2 = j;
            }
            quotaUpdater.updateQuota(j2);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            return a(webView, str, str2, null, jsResult, null, false);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
            return a(webView, str, str2, null, jsResult, null, false);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
            return a(webView, str, str2, null, jsResult, null, false);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            return a(webView, str, str2, str3, null, jsPromptResult, true);
        }

        @Override // android.webkit.WebChromeClient
        public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
            l.a aVar = (l.a) ((com.google.ads.l) this.a.a.a()).a.a();
            long longValue = ((Long) aVar.g.a()).longValue() + j;
            if (((Long) aVar.h.a()).longValue() - j2 < longValue) {
                quotaUpdater.updateQuota(0L);
            } else {
                quotaUpdater.updateQuota(longValue);
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            customViewCallback.onCustomViewHidden();
        }
    }

    /* loaded from: classes.dex */
    public class b extends com.google.ads.internal.i {
        public b(com.google.ads.internal.d dVar, Map map, boolean z, boolean z2) {
            super(dVar, map, z, z2);
        }

        private static WebResourceResponse a(String str, Context context) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                AdUtil.a(httpURLConnection, context.getApplicationContext());
                httpURLConnection.connect();
                return new WebResourceResponse("application/javascript", "UTF-8", new ByteArrayInputStream(AdUtil.a(new InputStreamReader(httpURLConnection.getInputStream())).getBytes("UTF-8")));
            } finally {
                httpURLConnection.disconnect();
            }
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            try {
                if ("mraid.js".equalsIgnoreCase(new File(str).getName())) {
                    com.google.ads.internal.c j = this.a.j();
                    if (j != null) {
                        j.b(true);
                    } else {
                        this.a.a(true);
                    }
                    l.a aVar = (l.a) ((com.google.ads.l) this.a.h().a.a()).a.a();
                    if (this.a.h().b()) {
                        String str2 = (String) aVar.e.a();
                        com.google.ads.util.b.a("shouldInterceptRequest(" + str2 + ")");
                        return a(str2, webView.getContext());
                    } else if (this.b) {
                        String str3 = (String) aVar.d.a();
                        com.google.ads.util.b.a("shouldInterceptRequest(" + str3 + ")");
                        return a(str3, webView.getContext());
                    } else {
                        String str4 = (String) aVar.c.a();
                        com.google.ads.util.b.a("shouldInterceptRequest(" + str4 + ")");
                        return a(str4, webView.getContext());
                    }
                }
            } catch (IOException e) {
                com.google.ads.util.b.d("IOException fetching MRAID JS.", e);
            } catch (Throwable th) {
                com.google.ads.util.b.b("An unknown error occurred fetching MRAID JS.", th);
            }
            return super.shouldInterceptRequest(webView, str);
        }
    }

    public static void a(View view) {
        view.setLayerType(1, null);
    }

    public static void a(Window window) {
        window.setFlags(16777216, 16777216);
    }

    public static void a(WebSettings webSettings, com.google.ads.m mVar) {
        Context context = (Context) mVar.f.a();
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCacheMaxSize(((Long) ((l.a) ((com.google.ads.l) mVar.a.a()).a.a()).f.a()).longValue());
        webSettings.setAppCachePath(new File(context.getCacheDir(), "admob").getAbsolutePath());
        webSettings.setDatabaseEnabled(true);
        webSettings.setDatabasePath(context.getDatabasePath("admob").getAbsolutePath());
        webSettings.setDomStorageEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
    }

    public static void b(View view) {
        view.setLayerType(0, null);
    }
}
