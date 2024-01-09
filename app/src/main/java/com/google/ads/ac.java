package com.google.ads;

import android.content.Context;
import com.google.ads.util.AdUtil;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes.dex */
public class ac implements Runnable {
    private final Context a;
    private final String b;

    public ac(String str, Context context) {
        this.b = str;
        this.a = context;
    }

    protected HttpURLConnection a(URL url) {
        return (HttpURLConnection) url.openConnection();
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            com.google.ads.util.b.a("Pinging URL: " + this.b);
            HttpURLConnection a = a(new URL(this.b));
            AdUtil.a(a, this.a);
            a.setInstanceFollowRedirects(true);
            a.connect();
            int responseCode = a.getResponseCode();
            if (responseCode < 200 || responseCode >= 300) {
                com.google.ads.util.b.e("Did not receive 2XX (got " + responseCode + ") from pinging URL: " + this.b);
            }
            a.disconnect();
        } catch (Throwable th) {
            com.google.ads.util.b.d("Unable to ping the URL: " + this.b, th);
        }
    }
}
