package com.google.ads.internal;

import android.content.Context;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.ads.AdActivity;
import com.google.ads.AdSize;
import com.google.ads.util.AdUtil;
import com.google.ads.util.g;
import com.google.ads.util.h;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class AdWebView extends WebView {
    private WeakReference a;
    private AdSize b;
    private boolean c;
    private boolean d;
    private boolean e;

    public AdWebView(com.google.ads.m mVar, AdSize adSize) {
        super((Context) mVar.f.a());
        this.b = adSize;
        this.a = null;
        this.c = false;
        this.d = false;
        this.e = false;
        setBackgroundColor(0);
        AdUtil.a(this);
        WebSettings settings = getSettings();
        settings.setSupportMultipleWindows(false);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        setDownloadListener(new l(this));
        if (AdUtil.a >= 11) {
            com.google.ads.util.g.a(settings, mVar);
        }
        setScrollBarStyle(33554432);
        if (AdUtil.a >= 14) {
            setWebChromeClient(new h.a(mVar));
        } else if (AdUtil.a >= 11) {
            setWebChromeClient(new g.a(mVar));
        }
    }

    public void a() {
        AdActivity d = d();
        if (d != null) {
            d.finish();
        }
    }

    public void b() {
        if (AdUtil.a >= 11) {
            com.google.ads.util.g.a(this);
        }
        this.d = true;
    }

    public void c() {
        if (this.d && AdUtil.a >= 11) {
            com.google.ads.util.g.b(this);
        }
        this.d = false;
    }

    public AdActivity d() {
        if (this.a != null) {
            return (AdActivity) this.a.get();
        }
        return null;
    }

    @Override // android.webkit.WebView
    public void destroy() {
        try {
            super.destroy();
            setWebViewClient(new WebViewClient());
        } catch (Throwable th) {
            com.google.ads.util.b.b("An error occurred while destroying an AdWebView:", th);
        }
    }

    public boolean e() {
        return this.e;
    }

    public boolean f() {
        return this.d;
    }

    @Override // android.webkit.WebView
    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        try {
            super.loadDataWithBaseURL(str, str2, str3, str4, str5);
        } catch (Throwable th) {
            com.google.ads.util.b.b("An error occurred while loading data in AdWebView:", th);
        }
    }

    @Override // android.webkit.WebView
    public void loadUrl(String str) {
        try {
            super.loadUrl(str);
        } catch (Throwable th) {
            com.google.ads.util.b.b("An error occurred while loading a URL in AdWebView:", th);
        }
    }

    @Override // android.webkit.WebView, android.widget.AbsoluteLayout, android.view.View
    protected synchronized void onMeasure(int i, int i2) {
        int i3 = Integer.MAX_VALUE;
        synchronized (this) {
            if (isInEditMode()) {
                super.onMeasure(i, i2);
            } else if (this.b == null || this.c) {
                super.onMeasure(i, i2);
            } else {
                int mode = View.MeasureSpec.getMode(i);
                int size = View.MeasureSpec.getSize(i);
                int mode2 = View.MeasureSpec.getMode(i2);
                int size2 = View.MeasureSpec.getSize(i2);
                float f = getContext().getResources().getDisplayMetrics().density;
                int a = (int) (this.b.a() * f);
                int b = (int) (this.b.b() * f);
                int i4 = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size : Integer.MAX_VALUE;
                if (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) {
                    i3 = size2;
                }
                if (a - (f * 6.0f) > i4 || b > i3) {
                    com.google.ads.util.b.e("Not enough space to show ad! Wants: <" + a + ", " + b + ">, Has: <" + size + ", " + size2 + ">");
                    setVisibility(8);
                    setMeasuredDimension(size, size2);
                } else {
                    setMeasuredDimension(a, b);
                }
            }
        }
    }

    public void setAdActivity(AdActivity adActivity) {
        this.a = new WeakReference(adActivity);
    }

    public synchronized void setAdSize(AdSize adSize) {
        this.b = adSize;
        requestLayout();
    }

    public void setCustomClose(boolean z) {
        AdActivity adActivity;
        this.e = z;
        if (this.a == null || (adActivity = (AdActivity) this.a.get()) == null) {
            return;
        }
        adActivity.a(z);
    }

    public void setIsExpandedMraid(boolean z) {
        this.c = z;
    }

    @Override // android.webkit.WebView
    public void stopLoading() {
        try {
            super.stopLoading();
        } catch (Throwable th) {
            com.google.ads.util.b.d("An error occurred while stopping loading in AdWebView:", th);
        }
    }
}
