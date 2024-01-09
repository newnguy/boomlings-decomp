package com.google.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.webkit.WebView;
import com.flurry.android.AdCreative;
import com.flurry.android.Constants;
import com.flurry.org.apache.avro.file.DataFileConstants;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.l;
import com.google.ads.searchads.SearchAdRequest;
import com.google.ads.util.AdUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;

/* loaded from: classes.dex */
public class c implements Runnable {
    boolean a;
    private String b;
    private String c;
    private String d;
    private String e;
    private boolean f;
    private f g;
    private com.google.ads.internal.d h;
    private AdRequest i;
    private WebView j;
    private String k;
    private LinkedList l;
    private String m;
    private AdSize n;
    private volatile boolean o;
    private boolean p;
    private AdRequest.ErrorCode q;
    private boolean r;
    private int s;
    private Thread t;
    private boolean u;
    private d v;

    /* loaded from: classes.dex */
    public enum d {
        ONLINE_USING_BUFFERED_ADS("online_buffered"),
        ONLINE_SERVER_REQUEST("online_request"),
        OFFLINE_USING_BUFFERED_ADS("offline_buffered"),
        OFFLINE_EMPTY("offline_empty");
        
        public String e;

        d(String str) {
            this.e = str;
        }
    }

    protected c() {
        this.v = d.ONLINE_SERVER_REQUEST;
    }

    public c(com.google.ads.internal.d dVar) {
        this.v = d.ONLINE_SERVER_REQUEST;
        this.h = dVar;
        this.k = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.l = new LinkedList();
        this.q = null;
        this.r = false;
        this.s = -1;
        this.f = false;
        this.p = false;
        this.m = null;
        this.n = null;
        if (((Activity) dVar.h().e.a()) == null) {
            this.j = null;
            this.g = null;
            com.google.ads.util.b.e("activity was null while trying to create an AdLoader.");
            return;
        }
        this.j = new AdWebView(dVar.h(), null);
        this.j.setWebViewClient(i.a(dVar, a.b, false, false));
        this.j.setVisibility(8);
        this.j.setWillNotDraw(true);
        this.g = new f(this, dVar);
    }

    static void a(String str, com.google.ads.c cVar, com.google.ads.d dVar) {
        if (str == null || str.contains("no-store") || str.contains("no-cache")) {
            return;
        }
        Matcher matcher = Pattern.compile("max-age\\s*=\\s*(\\d+)").matcher(str);
        if (!matcher.find()) {
            com.google.ads.util.b.c("Unrecognized cacheControlDirective: '" + str + "'. Not caching configuration.");
            return;
        }
        try {
            int parseInt = Integer.parseInt(matcher.group(1));
            dVar.a(cVar, parseInt);
            com.google.ads.util.b.c(String.format(Locale.US, "Caching gWhirl configuration for: %d seconds", Integer.valueOf(parseInt)));
        } catch (NumberFormatException e) {
            com.google.ads.util.b.b("Caught exception trying to parse cache control directive. Overflow?", e);
        }
    }

    private void b(String str, String str2) {
        this.h.a(new t(this, this.j, str2, str));
    }

    private String d() {
        return this.i instanceof SearchAdRequest ? "AFMA_buildAdURL" : "AFMA_buildAdURL";
    }

    private String e() {
        return this.i instanceof SearchAdRequest ? "AFMA_getSdkConstants();" : "AFMA_getSdkConstants();";
    }

    private String f() {
        return this.i instanceof SearchAdRequest ? "http://www.gstatic.com/safa/" : "http://media.admob.com/";
    }

    private String g() {
        return this.i instanceof SearchAdRequest ? "<html><head><script src=\"http://www.gstatic.com/safa/sdk-core-v40.js\"></script><script>" : "<html><head><script src=\"http://media.admob.com/sdk-core-v40.js\"></script><script>";
    }

    private String h() {
        return this.i instanceof SearchAdRequest ? "</script></head><body></body></html>" : "</script></head><body></body></html>";
    }

    private void i() {
        AdWebView k = this.h.k();
        this.h.l().c(true);
        this.h.m().h();
        this.h.a(new t(this, k, this.b, this.c));
    }

    private void j() {
        this.h.a(new u(this.h, this.j, this.l, this.s, this.p, this.m, this.n));
    }

    public String a(Map map, Activity activity) {
        Context applicationContext = activity.getApplicationContext();
        g m = this.h.m();
        long m2 = m.m();
        if (m2 > 0) {
            map.put("prl", Long.valueOf(m2));
        }
        long n = m.n();
        if (n > 0) {
            map.put("prnl", Long.valueOf(n));
        }
        String l = m.l();
        if (l != null) {
            map.put("ppcl", l);
        }
        String k = m.k();
        if (k != null) {
            map.put("pcl", k);
        }
        long j = m.j();
        if (j > 0) {
            map.put("pcc", Long.valueOf(j));
        }
        map.put("preqs", Long.valueOf(m.o()));
        map.put("oar", Long.valueOf(m.p()));
        map.put("bas_on", Long.valueOf(m.s()));
        map.put("bas_off", Long.valueOf(m.v()));
        if (m.y()) {
            map.put("aoi_timeout", "true");
        }
        if (m.A()) {
            map.put("aoi_nofill", "true");
        }
        String D = m.D();
        if (D != null) {
            map.put("pit", D);
        }
        map.put("ptime", Long.valueOf(g.E()));
        m.a();
        m.i();
        if (this.h.h().b()) {
            map.put("format", "interstitial_mb");
        } else {
            AdSize b = ((h) this.h.h().k.a()).b();
            if (b.c()) {
                map.put("smart_w", "full");
            }
            if (b.d()) {
                map.put("smart_h", "auto");
            }
            if (b.e()) {
                HashMap hashMap = new HashMap();
                hashMap.put("w", Integer.valueOf(b.a()));
                hashMap.put("h", Integer.valueOf(b.b()));
                map.put("ad_frame", hashMap);
            } else {
                map.put("format", b.toString());
            }
        }
        map.put("slotname", this.h.h().d.a());
        map.put("js", "afma-sdk-a-v6.2.1");
        try {
            int i = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0).versionCode;
            String f = AdUtil.f(applicationContext);
            if (!TextUtils.isEmpty(f)) {
                map.put("mv", f);
            }
            map.put("msid", applicationContext.getPackageName());
            map.put("app_name", i + ".android." + applicationContext.getPackageName());
            map.put("isu", AdUtil.a(applicationContext));
            String d2 = AdUtil.d(applicationContext);
            if (d2 == null) {
                d2 = DataFileConstants.NULL_CODEC;
            }
            map.put("net", d2);
            String e = AdUtil.e(applicationContext);
            if (e != null && e.length() != 0) {
                map.put("cap", e);
            }
            map.put("u_audio", Integer.valueOf(AdUtil.g(applicationContext).ordinal()));
            DisplayMetrics a = AdUtil.a(activity);
            map.put("u_sd", Float.valueOf(a.density));
            map.put("u_h", Integer.valueOf(AdUtil.a(applicationContext, a)));
            map.put("u_w", Integer.valueOf(AdUtil.b(applicationContext, a)));
            map.put("hl", Locale.getDefault().getLanguage());
            if (this.h.h().i != null && this.h.h().i.a() != null) {
                AdView adView = (AdView) this.h.h().i.a();
                if (adView.getParent() != null) {
                    int[] iArr = new int[2];
                    adView.getLocationOnScreen(iArr);
                    int i2 = iArr[0];
                    int i3 = iArr[1];
                    DisplayMetrics displayMetrics = ((Context) this.h.h().f.a()).getResources().getDisplayMetrics();
                    int i4 = (!adView.isShown() || adView.getWidth() + i2 <= 0 || adView.getHeight() + i3 <= 0 || i2 > displayMetrics.widthPixels || i3 > displayMetrics.heightPixels) ? 0 : 1;
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("x", Integer.valueOf(i2));
                    hashMap2.put("y", Integer.valueOf(i3));
                    hashMap2.put(AdCreative.kFixWidth, Integer.valueOf(adView.getWidth()));
                    hashMap2.put(AdCreative.kFixHeight, Integer.valueOf(adView.getHeight()));
                    hashMap2.put("visible", Integer.valueOf(i4));
                    map.put("ad_pos", hashMap2);
                }
            }
            StringBuilder sb = new StringBuilder();
            AdSize[] adSizeArr = (AdSize[]) this.h.h().l.a();
            if (adSizeArr != null) {
                for (AdSize adSize : adSizeArr) {
                    if (sb.length() != 0) {
                        sb.append("|");
                    }
                    sb.append(adSize.a() + "x" + adSize.b());
                }
                map.put("sz", sb.toString());
            }
            TelephonyManager telephonyManager = (TelephonyManager) applicationContext.getSystemService("phone");
            map.put("carrier", telephonyManager.getNetworkOperator());
            map.put("gnt", Integer.valueOf(telephonyManager.getNetworkType()));
            if (AdUtil.c()) {
                map.put("simulator", 1);
            }
            map.put("session_id", com.google.ads.b.a().b().toString());
            map.put("seq_num", com.google.ads.b.a().c().toString());
            String a2 = AdUtil.a(map);
            String str = ((Boolean) ((l.a) ((com.google.ads.l) this.h.h().a.a()).a.a()).l.a()).booleanValue() ? g() + d() + "(" + a2 + ");" + h() : g() + e() + d() + "(" + a2 + ");" + h();
            com.google.ads.util.b.c("adRequestUrlHtml: " + str);
            return str;
        } catch (PackageManager.NameNotFoundException e2) {
            throw new s(this, "NameNotFoundException");
        }
    }

    public void a() {
        com.google.ads.util.b.a("AdLoader cancelled.");
        if (this.j != null) {
            this.j.stopLoading();
            this.j.destroy();
        }
        if (this.t != null) {
            this.t.interrupt();
            this.t = null;
        }
        if (this.g != null) {
            this.g.a();
        }
        this.o = true;
    }

    public synchronized void a(int i) {
        this.s = i;
    }

    public synchronized void a(AdRequest.ErrorCode errorCode) {
        this.q = errorCode;
        notify();
    }

    protected void a(AdRequest.ErrorCode errorCode, boolean z) {
        this.h.a(new r(this.h, this.j, this.g, errorCode, z));
    }

    public void a(AdRequest adRequest) {
        this.i = adRequest;
        this.o = false;
        this.t = new Thread(this);
        this.t.start();
    }

    public synchronized void a(AdSize adSize) {
        this.n = adSize;
    }

    public synchronized void a(d dVar) {
        this.v = dVar;
    }

    public synchronized void a(String str) {
        this.l.add(str);
    }

    public synchronized void a(String str, String str2) {
        this.b = str2;
        this.c = str;
        notify();
    }

    public synchronized void a(boolean z) {
        this.f = z;
    }

    protected void b() {
        try {
            if (TextUtils.isEmpty(this.e)) {
                com.google.ads.util.b.b("Got a mediation response with no content type. Aborting mediation.");
                a(AdRequest.ErrorCode.INTERNAL_ERROR, false);
            } else if (this.e.startsWith("application/json")) {
                com.google.ads.c a = com.google.ads.c.a(this.c);
                a(this.d, a, this.h.i());
                this.h.a(new p(this, a));
            } else {
                com.google.ads.util.b.b("Got a mediation response with a content type: '" + this.e + "'. Expected something starting with 'application/json'. Aborting mediation.");
                a(AdRequest.ErrorCode.INTERNAL_ERROR, false);
            }
        } catch (JSONException e) {
            com.google.ads.util.b.b("AdLoader can't parse gWhirl server configuration.", e);
            a(AdRequest.ErrorCode.INTERNAL_ERROR, false);
        }
    }

    public synchronized void b(String str) {
        this.e = str;
    }

    public synchronized void b(boolean z) {
        this.p = z;
    }

    public synchronized void c() {
        this.r = true;
        notify();
    }

    public synchronized void c(String str) {
        this.d = str;
    }

    public synchronized void c(boolean z) {
        this.u = z;
    }

    public synchronized void d(String str) {
        this.k = str;
        notify();
    }

    public synchronized void d(boolean z) {
        this.a = z;
    }

    public synchronized void e(String str) {
        this.m = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this) {
            if (this.j == null || this.g == null) {
                com.google.ads.util.b.e("adRequestWebView was null while trying to load an ad.");
                a(AdRequest.ErrorCode.INTERNAL_ERROR, false);
                return;
            }
            Activity activity = (Activity) this.h.h().e.a();
            if (activity == null) {
                com.google.ads.util.b.e("activity was null while forming an ad request.");
                a(AdRequest.ErrorCode.INTERNAL_ERROR, false);
                return;
            }
            long o = this.h.o();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Map a = this.i.a((Context) this.h.h().f.a());
            Object obj = a.get("extras");
            if (obj instanceof Map) {
                Map map = (Map) obj;
                Object obj2 = map.get("_adUrl");
                if (obj2 instanceof String) {
                    this.b = (String) obj2;
                }
                Object obj3 = map.get("_requestUrl");
                if (obj3 instanceof String) {
                    this.k = (String) obj3;
                }
                Object obj4 = map.get("_orientation");
                if (obj4 instanceof String) {
                    if (obj4.equals("p")) {
                        this.s = 1;
                    } else if (obj4.equals(Constants.ALIGN_LEFT)) {
                        this.s = 0;
                    }
                }
                Object obj5 = map.get("_norefresh");
                if ((obj5 instanceof String) && obj5.equals(Constants.ALIGN_TOP)) {
                    this.h.d();
                }
            }
            if (this.b == null) {
                if (this.k == null) {
                    try {
                        b(a(a, activity), f());
                        long elapsedRealtime2 = o - (SystemClock.elapsedRealtime() - elapsedRealtime);
                        if (elapsedRealtime2 > 0) {
                            try {
                                wait(elapsedRealtime2);
                            } catch (InterruptedException e) {
                                com.google.ads.util.b.a("AdLoader InterruptedException while getting the URL: " + e);
                                return;
                            }
                        }
                        if (this.o) {
                            return;
                        }
                        if (this.q != null) {
                            a(this.q, false);
                            return;
                        } else if (this.k == null) {
                            com.google.ads.util.b.c("AdLoader timed out after " + o + "ms while getting the URL.");
                            a(AdRequest.ErrorCode.NETWORK_ERROR, false);
                            return;
                        }
                    } catch (s e2) {
                        com.google.ads.util.b.c("Caught internal exception: " + e2);
                        a(AdRequest.ErrorCode.INTERNAL_ERROR, false);
                        return;
                    }
                }
                g m = this.h.m();
                switch (this.v) {
                    case ONLINE_SERVER_REQUEST:
                        m.r();
                        m.u();
                        m.x();
                        com.google.ads.util.b.c("Request scenario: Online server request.");
                        break;
                    case ONLINE_USING_BUFFERED_ADS:
                        m.t();
                        com.google.ads.util.b.c("Request scenario: Online using buffered ads.");
                        break;
                    case OFFLINE_USING_BUFFERED_ADS:
                        m.w();
                        m.q();
                        com.google.ads.util.b.c("Request scenario: Offline using buffered ads.");
                        break;
                    case OFFLINE_EMPTY:
                        m.q();
                        com.google.ads.util.b.c("Request scenario: Offline with no buffered ads.");
                        com.google.ads.util.b.c("Network is unavailable.  Aborting ad request.");
                        a(AdRequest.ErrorCode.NETWORK_ERROR, false);
                        return;
                }
                if (this.a) {
                    this.b = this.k;
                    com.google.ads.util.b.a("Using loadUrl.  adBaseUrl: " + this.b);
                } else {
                    com.google.ads.util.b.a("Not using loadUrl().");
                    this.g.a(this.u);
                    this.g.a(this.k);
                    long elapsedRealtime3 = o - (SystemClock.elapsedRealtime() - elapsedRealtime);
                    if (elapsedRealtime3 > 0) {
                        try {
                            wait(elapsedRealtime3);
                        } catch (InterruptedException e3) {
                            com.google.ads.util.b.a("AdLoader InterruptedException while getting the ad server's response: " + e3);
                            return;
                        }
                    }
                    if (this.o) {
                        return;
                    }
                    if (this.q != null) {
                        a(this.q, false);
                        return;
                    } else if (this.c == null) {
                        com.google.ads.util.b.c("AdLoader timed out after " + o + "ms while waiting for the ad server's response.");
                        a(AdRequest.ErrorCode.NETWORK_ERROR, false);
                        return;
                    }
                }
            }
            if (!this.a) {
                if (this.f) {
                    this.h.b(true);
                    b();
                    return;
                } else if (this.e != null && (this.e.startsWith("application/json") || this.e.startsWith("text/javascript"))) {
                    com.google.ads.util.b.b("Expected HTML but received " + this.e + ".");
                    a(AdRequest.ErrorCode.INTERNAL_ERROR, false);
                    return;
                } else if (this.h.h().l.a() != null) {
                    if (this.n == null) {
                        com.google.ads.util.b.b("Multiple supported ad sizes were specified, but the server did not respond with a size.");
                        a(AdRequest.ErrorCode.INTERNAL_ERROR, false);
                        return;
                    } else if (!Arrays.asList((Object[]) this.h.h().l.a()).contains(this.n)) {
                        com.google.ads.util.b.b("The ad server did not respond with a supported AdSize: " + this.n);
                        a(AdRequest.ErrorCode.INTERNAL_ERROR, false);
                        return;
                    }
                } else if (this.n != null) {
                    com.google.ads.util.b.e("adSize was expected to be null in AdLoader.");
                    this.n = null;
                }
            }
            this.h.b(false);
            i();
            long elapsedRealtime4 = o - (SystemClock.elapsedRealtime() - elapsedRealtime);
            if (elapsedRealtime4 > 0) {
                try {
                    wait(elapsedRealtime4);
                } catch (InterruptedException e4) {
                    com.google.ads.util.b.a("AdLoader InterruptedException while loading the HTML: " + e4);
                    return;
                }
            }
            if (this.r) {
                j();
            } else {
                com.google.ads.util.b.c("AdLoader timed out after " + o + "ms while loading the HTML.");
                a(AdRequest.ErrorCode.NETWORK_ERROR, true);
            }
        }
    }
}
