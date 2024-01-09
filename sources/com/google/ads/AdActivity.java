package com.google.ads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.flurry.android.Constants;
import com.google.ads.ai;
import com.google.ads.internal.AdVideoView;
import com.google.ads.internal.AdWebView;
import com.google.ads.l;
import com.google.ads.util.AdUtil;
import java.util.HashMap;

/* loaded from: classes.dex */
public class AdActivity extends Activity implements View.OnClickListener {
    private static final com.google.ads.internal.a a = (com.google.ads.internal.a) com.google.ads.internal.a.a.b();
    private static final Object b = new Object();
    private static AdActivity c = null;
    private static com.google.ads.internal.d d = null;
    private static AdActivity e = null;
    private static AdActivity f = null;
    private static final StaticMethodWrapper g = new StaticMethodWrapper();
    private AdWebView h;
    private FrameLayout i;
    private int j;
    private boolean l;
    private long m;
    private RelativeLayout n;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private AdVideoView t;
    private ViewGroup k = null;
    private AdActivity o = null;

    /* loaded from: classes.dex */
    public class StaticMethodWrapper {
        public void a(com.google.ads.internal.d dVar, com.google.ads.internal.e eVar) {
            synchronized (AdActivity.b) {
                if (AdActivity.d == null) {
                    com.google.ads.internal.d unused = AdActivity.d = dVar;
                } else if (AdActivity.d != dVar) {
                    com.google.ads.util.b.b("Tried to launch a new AdActivity with a different AdManager.");
                    return;
                }
                Activity activity = (Activity) dVar.h().e.a();
                if (activity == null) {
                    com.google.ads.util.b.e("activity was null while launching an AdActivity.");
                    return;
                }
                Intent intent = new Intent(activity.getApplicationContext(), AdActivity.class);
                intent.putExtra("com.google.ads.AdOpener", eVar.a());
                try {
                    com.google.ads.util.b.a("Launching AdActivity.");
                    activity.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    com.google.ads.util.b.b("Activity not found.", e);
                }
            }
        }

        public boolean a() {
            boolean z;
            synchronized (AdActivity.b) {
                z = AdActivity.e != null;
            }
            return z;
        }
    }

    public static void a(com.google.ads.internal.d dVar, com.google.ads.internal.e eVar) {
        g.a(dVar, eVar);
    }

    private void a(String str) {
        com.google.ads.util.b.b(str);
        finish();
    }

    private void a(String str, Throwable th) {
        com.google.ads.util.b.b(str, th);
        finish();
    }

    private RelativeLayout.LayoutParams c(int i, int i2, int i3, int i4) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, i4);
        layoutParams.setMargins(i, i2, 0, 0);
        layoutParams.addRule(10);
        layoutParams.addRule(9);
        return layoutParams;
    }

    public static boolean c() {
        return g.a();
    }

    private void g() {
        if (this.l) {
            return;
        }
        if (this.h != null) {
            a.b(this.h);
            this.h.setAdActivity(null);
            this.h.setIsExpandedMraid(false);
            if (!this.q && this.n != null && this.k != null) {
                if (this.r && !this.s) {
                    com.google.ads.util.b.a("Disabling hardware acceleration on collapsing MRAID WebView.");
                    this.h.b();
                } else if (!this.r && this.s) {
                    com.google.ads.util.b.a("Re-enabling hardware acceleration on collapsing MRAID WebView.");
                    this.h.c();
                }
                this.n.removeView(this.h);
                this.k.addView(this.h);
            }
        }
        if (this.t != null) {
            this.t.e();
            this.t = null;
        }
        if (this == c) {
            c = null;
        }
        f = this.o;
        synchronized (b) {
            if (d != null && this.q && this.h != null) {
                if (this.h == d.k()) {
                    d.a();
                }
                this.h.stopLoading();
            }
            if (this == e) {
                e = null;
                if (d != null) {
                    d.s();
                    d = null;
                } else {
                    com.google.ads.util.b.e("currentAdManager is null while trying to destroy AdActivity.");
                }
            }
        }
        this.l = true;
        com.google.ads.util.b.a("AdActivity is closing.");
    }

    protected View a(int i, boolean z) {
        this.j = (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
        this.i = new FrameLayout(getApplicationContext());
        this.i.setMinimumWidth(this.j);
        this.i.setMinimumHeight(this.j);
        this.i.setOnClickListener(this);
        a(z);
        return this.i;
    }

    public AdVideoView a() {
        return this.t;
    }

    protected AdVideoView a(Activity activity) {
        return new AdVideoView(activity, this.h);
    }

    public void a(int i, int i2, int i3, int i4) {
        if (this.t != null) {
            this.t.setLayoutParams(c(i, i2, i3, i4));
            this.t.requestLayout();
        }
    }

    protected void a(AdWebView adWebView, boolean z, int i, boolean z2, boolean z3) {
        requestWindowFeature(1);
        Window window = getWindow();
        window.setFlags(1024, 1024);
        if (AdUtil.a >= 11) {
            if (this.r) {
                com.google.ads.util.b.a("Enabling hardware acceleration on the AdActivity window.");
                com.google.ads.util.g.a(window);
            } else {
                com.google.ads.util.b.a("Disabling hardware acceleration on the AdActivity WebView.");
                adWebView.b();
            }
        }
        ViewParent parent = adWebView.getParent();
        if (parent != null) {
            if (!z2) {
                a("Interstitial created with an AdWebView that has a parent.");
                return;
            } else if (!(parent instanceof ViewGroup)) {
                a("MRAID banner was not a child of a ViewGroup.");
                return;
            } else {
                this.k = (ViewGroup) parent;
                this.k.removeView(adWebView);
            }
        }
        if (adWebView.d() != null) {
            a("Interstitial created with an AdWebView that is already in use by another AdActivity.");
            return;
        }
        setRequestedOrientation(i);
        adWebView.setAdActivity(this);
        View a2 = a(z2 ? 50 : 32, z3);
        this.n.addView(adWebView, -1, -1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (z2) {
            layoutParams.addRule(10);
            layoutParams.addRule(11);
        } else {
            layoutParams.addRule(10);
            layoutParams.addRule(9);
        }
        this.n.addView(a2, layoutParams);
        this.n.setKeepScreenOn(true);
        setContentView(this.n);
        this.n.getRootView().setBackgroundColor(-16777216);
        if (z) {
            a.a(adWebView);
        }
    }

    protected void a(com.google.ads.internal.d dVar) {
        this.h = null;
        this.m = SystemClock.elapsedRealtime();
        this.p = true;
        synchronized (b) {
            if (c == null) {
                c = this;
                dVar.u();
            }
        }
    }

    protected void a(HashMap hashMap, com.google.ads.internal.d dVar) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.google.android.apps.plus", "com.google.android.apps.circles.platform.PlusOneActivity"));
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.putExtras(getIntent().getExtras());
        intent.putExtra("com.google.circles.platform.intent.extra.ENTITY", (String) hashMap.get("u"));
        intent.putExtra("com.google.circles.platform.intent.extra.ENTITY_TYPE", ai.b.AD.c);
        intent.putExtra("com.google.circles.platform.intent.extra.ACTION", (String) hashMap.get("a"));
        a(dVar);
        try {
            com.google.ads.util.b.a("Launching Google+ intent from AdActivity.");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException e2) {
            a(e2.getMessage(), e2);
        }
    }

    public void a(boolean z) {
        if (this.i != null) {
            this.i.removeAllViews();
            if (z) {
                return;
            }
            ImageButton imageButton = new ImageButton(this);
            imageButton.setImageResource(17301527);
            imageButton.setBackgroundColor(0);
            imageButton.setOnClickListener(this);
            imageButton.setPadding(0, 0, 0, 0);
            this.i.addView(imageButton, new FrameLayout.LayoutParams(this.j, this.j, 17));
        }
    }

    public AdWebView b() {
        AdWebView adWebView = null;
        if (this.o != null) {
            return this.o.h;
        }
        synchronized (b) {
            if (d == null) {
                com.google.ads.util.b.e("currentAdManager was null while trying to get the opening AdWebView.");
            } else {
                AdWebView k = d.k();
                if (k != this.h) {
                    adWebView = k;
                }
            }
        }
        return adWebView;
    }

    public void b(int i, int i2, int i3, int i4) {
        if (this.t == null) {
            this.t = a(this);
            this.n.addView(this.t, 0, c(i, i2, i3, i4));
            synchronized (b) {
                if (d == null) {
                    com.google.ads.util.b.e("currentAdManager was null while trying to get the opening AdWebView.");
                } else {
                    d.l().b(false);
                }
            }
        }
    }

    protected void b(HashMap hashMap, com.google.ads.internal.d dVar) {
        if (hashMap == null) {
            a("Could not get the paramMap in launchIntent()");
            return;
        }
        String str = (String) hashMap.get("u");
        if (str == null) {
            a("Could not get the URL parameter in launchIntent().");
            return;
        }
        String str2 = (String) hashMap.get("i");
        String str3 = (String) hashMap.get(Constants.ALIGN_MIDDLE);
        Uri parse = Uri.parse(str);
        Intent intent = str2 == null ? new Intent("android.intent.action.VIEW", parse) : new Intent(str2, parse);
        if (str3 != null) {
            intent.setDataAndType(parse, str3);
        }
        a(dVar);
        try {
            com.google.ads.util.b.a("Launching an intent from AdActivity: " + intent.getAction() + " - " + parse);
            startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            a(e2.getMessage(), e2);
        }
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (b() != null && intent != null && intent.getExtras() != null && intent.getExtras().getString("com.google.circles.platform.result.extra.CONFIRMATION") != null && intent.getExtras().getString("com.google.circles.platform.result.extra.ACTION") != null) {
            String string = intent.getExtras().getString("com.google.circles.platform.result.extra.CONFIRMATION");
            String string2 = intent.getExtras().getString("com.google.circles.platform.result.extra.ACTION");
            if (string.equals("yes")) {
                if (string2.equals("insert")) {
                    ag.a((WebView) b(), true);
                } else if (string2.equals("delete")) {
                    ag.a((WebView) b(), false);
                }
            }
        }
        finish();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        finish();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        boolean e2;
        boolean z = false;
        super.onCreate(bundle);
        this.l = false;
        synchronized (b) {
            if (d == null) {
                a("Could not get currentAdManager.");
                return;
            }
            com.google.ads.internal.d dVar = d;
            if (e == null) {
                e = this;
                dVar.t();
            }
            if (this.o == null && f != null) {
                this.o = f;
            }
            f = this;
            if ((dVar.h().a() && e == this) || (dVar.h().b() && this.o == e)) {
                dVar.v();
            }
            boolean q = dVar.q();
            l.a aVar = (l.a) ((l) dVar.h().a.a()).a.a();
            this.s = AdUtil.a >= ((Integer) aVar.a.a()).intValue();
            this.r = AdUtil.a >= ((Integer) aVar.b.a()).intValue();
            this.n = null;
            this.p = false;
            this.q = true;
            this.t = null;
            Bundle bundleExtra = getIntent().getBundleExtra("com.google.ads.AdOpener");
            if (bundleExtra == null) {
                a("Could not get the Bundle used to create AdActivity.");
                return;
            }
            com.google.ads.internal.e eVar = new com.google.ads.internal.e(bundleExtra);
            String b2 = eVar.b();
            HashMap c2 = eVar.c();
            if (b2.equals("plusone")) {
                a(c2, dVar);
            } else if (b2.equals("intent")) {
                b(c2, dVar);
            } else {
                this.n = new RelativeLayout(getApplicationContext());
                if (b2.equals("webapp")) {
                    this.h = new AdWebView(dVar.h(), null);
                    com.google.ads.internal.i a2 = com.google.ads.internal.i.a(dVar, com.google.ads.internal.a.c, true, !q);
                    a2.d(true);
                    if (q) {
                        a2.a(true);
                    }
                    this.h.setWebViewClient(a2);
                    String str = (String) c2.get("u");
                    String str2 = (String) c2.get("baseurl");
                    String str3 = (String) c2.get("html");
                    if (str != null) {
                        this.h.loadUrl(str);
                    } else if (str3 == null) {
                        a("Could not get the URL or HTML parameter to show a web app.");
                        return;
                    } else {
                        this.h.loadDataWithBaseURL(str2, str3, "text/html", "utf-8", null);
                    }
                    String str4 = (String) c2.get("o");
                    a(this.h, false, "p".equals(str4) ? AdUtil.b() : Constants.ALIGN_LEFT.equals(str4) ? AdUtil.a() : this == e ? dVar.n() : -1, q, c2 != null && "1".equals(c2.get("custom_close")));
                } else if (!b2.equals("interstitial") && !b2.equals("expand")) {
                    a("Unknown AdOpener, <action: " + b2 + ">");
                } else {
                    this.h = dVar.k();
                    int n = dVar.n();
                    if (b2.equals("expand")) {
                        this.h.setIsExpandedMraid(true);
                        this.q = false;
                        if (c2 != null && "1".equals(c2.get("custom_close"))) {
                            z = true;
                        }
                        if (!this.r || this.s) {
                            e2 = z;
                        } else {
                            com.google.ads.util.b.a("Re-enabling hardware acceleration on expanding MRAID WebView.");
                            this.h.c();
                            e2 = z;
                        }
                    } else {
                        e2 = this.h.e();
                    }
                    a(this.h, true, n, q, e2);
                }
            }
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        if (this.n != null) {
            this.n.removeAllViews();
        }
        if (isFinishing()) {
            g();
            if (this.q && this.h != null) {
                this.h.stopLoading();
                this.h.destroy();
                this.h = null;
            }
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onPause() {
        if (isFinishing()) {
            g();
        }
        super.onPause();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        if (this.p && z && SystemClock.elapsedRealtime() - this.m > 250) {
            com.google.ads.util.b.d("Launcher AdActivity got focus and is closing.");
            finish();
        }
        super.onWindowFocusChanged(z);
    }
}
