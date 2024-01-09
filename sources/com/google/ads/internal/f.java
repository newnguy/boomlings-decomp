package com.google.ads.internal;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.util.AdUtil;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes.dex */
public final class f implements Runnable {
    private final c a;
    private final d b;
    private final a c;
    private volatile boolean d;
    private boolean e;
    private String f;
    private Thread g;

    /* loaded from: classes.dex */
    public interface a {
        HttpURLConnection a(URL url);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(c cVar, d dVar) {
        this(cVar, dVar, new v());
    }

    f(c cVar, d dVar, a aVar) {
        this.g = null;
        this.a = cVar;
        this.b = dVar;
        this.c = aVar;
    }

    private void a(Context context, HttpURLConnection httpURLConnection) {
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString("drt", "");
        if (!this.e || TextUtils.isEmpty(string)) {
            return;
        }
        if (AdUtil.a == 8) {
            httpURLConnection.addRequestProperty("X-Afma-drt-Cookie", string);
        } else {
            httpURLConnection.addRequestProperty("Cookie", string);
        }
    }

    private void a(HttpURLConnection httpURLConnection) {
        b(httpURLConnection);
        f(httpURLConnection);
        g(httpURLConnection);
        h(httpURLConnection);
        e(httpURLConnection);
        i(httpURLConnection);
        j(httpURLConnection);
        k(httpURLConnection);
        d(httpURLConnection);
        c(httpURLConnection);
        l(httpURLConnection);
    }

    private void a(HttpURLConnection httpURLConnection, int i) {
        if (300 <= i && i < 400) {
            String headerField = httpURLConnection.getHeaderField("Location");
            if (headerField != null) {
                a(httpURLConnection);
                this.f = headerField;
                return;
            }
            com.google.ads.util.b.c("Could not get redirect location from a " + i + " redirect.");
            this.a.a(AdRequest.ErrorCode.INTERNAL_ERROR);
            a();
        } else if (i != 200) {
            if (i == 400) {
                com.google.ads.util.b.c("Bad request");
                this.a.a(AdRequest.ErrorCode.INVALID_REQUEST);
                a();
                return;
            }
            com.google.ads.util.b.c("Invalid response code: " + i);
            this.a.a(AdRequest.ErrorCode.INTERNAL_ERROR);
            a();
        } else {
            a(httpURLConnection);
            String trim = AdUtil.a(new InputStreamReader(httpURLConnection.getInputStream())).trim();
            com.google.ads.util.b.a("Response content is: " + trim);
            if (!TextUtils.isEmpty(trim)) {
                this.a.a(trim, this.f);
                a();
                return;
            }
            com.google.ads.util.b.a("Response message is null or zero length: " + trim);
            this.a.a(AdRequest.ErrorCode.NO_FILL);
            a();
        }
    }

    private void b() {
        while (!this.d) {
            HttpURLConnection a2 = this.c.a(new URL(this.f));
            try {
                a((Context) this.b.h().f.a(), a2);
                AdUtil.a(a2, (Context) this.b.h().f.a());
                a2.setInstanceFollowRedirects(false);
                a2.connect();
                a(a2, a2.getResponseCode());
            } finally {
                a2.disconnect();
            }
        }
    }

    private void b(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("X-Afma-Debug-Dialog");
        if (TextUtils.isEmpty(headerField)) {
            return;
        }
        this.a.e(headerField);
    }

    private void c(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("Content-Type");
        if (TextUtils.isEmpty(headerField)) {
            return;
        }
        this.a.b(headerField);
    }

    private void d(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("X-Afma-Mediation");
        if (TextUtils.isEmpty(headerField)) {
            return;
        }
        this.a.a(Boolean.valueOf(headerField).booleanValue());
    }

    private void e(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("X-Afma-Interstitial-Timeout");
        if (TextUtils.isEmpty(headerField)) {
            return;
        }
        try {
            this.b.a(Float.parseFloat(headerField) * 1000.0f);
        } catch (NumberFormatException e) {
            com.google.ads.util.b.d("Could not get timeout value: " + headerField, e);
        }
    }

    private void f(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("X-Afma-Tracking-Urls");
        if (TextUtils.isEmpty(headerField)) {
            return;
        }
        String[] split = headerField.trim().split("\\s+");
        for (String str : split) {
            this.b.b(str);
        }
    }

    private void g(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("X-Afma-Click-Tracking-Urls");
        if (TextUtils.isEmpty(headerField)) {
            return;
        }
        String[] split = headerField.trim().split("\\s+");
        for (String str : split) {
            this.a.a(str);
        }
    }

    private void h(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("X-Afma-Refresh-Rate");
        if (TextUtils.isEmpty(headerField)) {
            return;
        }
        try {
            float parseFloat = Float.parseFloat(headerField);
            if (parseFloat <= 0.0f) {
                if (this.b.r()) {
                    this.b.e();
                    return;
                }
                return;
            }
            this.b.a(parseFloat);
            if (this.b.r()) {
                return;
            }
            this.b.f();
        } catch (NumberFormatException e) {
            com.google.ads.util.b.d("Could not get refresh value: " + headerField, e);
        }
    }

    private void i(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("X-Afma-Orientation");
        if (TextUtils.isEmpty(headerField)) {
            return;
        }
        if (headerField.equals("portrait")) {
            this.a.a(AdUtil.b());
        } else if (headerField.equals("landscape")) {
            this.a.a(AdUtil.a());
        }
    }

    private void j(HttpURLConnection httpURLConnection) {
        if (TextUtils.isEmpty(httpURLConnection.getHeaderField("X-Afma-Doritos-Cache-Life"))) {
            return;
        }
        try {
            this.b.b(Long.parseLong(httpURLConnection.getHeaderField("X-Afma-Doritos-Cache-Life")));
        } catch (NumberFormatException e) {
            com.google.ads.util.b.e("Got bad value of Doritos cookie cache life from header: " + httpURLConnection.getHeaderField("X-Afma-Doritos-Cache-Life") + ". Using default value instead.");
        }
    }

    private void k(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("Cache-Control");
        if (TextUtils.isEmpty(headerField)) {
            return;
        }
        this.a.c(headerField);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void l(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("X-Afma-Ad-Size");
        if (TextUtils.isEmpty(headerField)) {
            return;
        }
        try {
            String[] split = headerField.split("x", 2);
            if (split.length != 2) {
                com.google.ads.util.b.e("Could not parse size header: " + headerField);
                headerField = headerField;
            } else {
                int parseInt = Integer.parseInt(split[0]);
                int parseInt2 = Integer.parseInt(split[1]);
                this.a.a(new AdSize(parseInt, parseInt2));
                headerField = parseInt2;
            }
        } catch (NumberFormatException e) {
            com.google.ads.util.b.e("Could not parse size header: " + headerField);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        this.d = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void a(String str) {
        if (this.g == null) {
            this.f = str;
            this.d = false;
            this.g = new Thread(this);
            this.g.start();
        }
    }

    public void a(boolean z) {
        this.e = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            b();
        } catch (MalformedURLException e) {
            com.google.ads.util.b.b("Received malformed ad url from javascript.", e);
            this.a.a(AdRequest.ErrorCode.INTERNAL_ERROR);
        } catch (IOException e2) {
            com.google.ads.util.b.d("IOException connecting to ad url.", e2);
            this.a.a(AdRequest.ErrorCode.NETWORK_ERROR);
        } catch (Throwable th) {
            com.google.ads.util.b.b("An unknown error occurred in AdResponseLoader.", th);
            this.a.a(AdRequest.ErrorCode.INTERNAL_ERROR);
        }
    }
}
