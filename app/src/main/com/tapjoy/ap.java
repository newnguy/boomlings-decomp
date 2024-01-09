package com.tapjoy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.net.URL;
import java.net.URLConnection;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ap implements Runnable {
    final /* synthetic */ ao a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ap(ao aoVar) {
        this.a = aoVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z;
        boolean z2 = false;
        String b = new am().b("https://ws.tapjoyads.com/videos?", h.c() + "&publisher_user_id=" + h.e());
        if (b != null && b.length() > 0) {
            z2 = this.a.a(b);
        }
        if (!z2) {
            ao.b(2);
            return;
        }
        this.a.j();
        if ("https://s3.amazonaws.com/tapjoy/videos/assets/watermark.png" != 0 && "https://s3.amazonaws.com/tapjoy/videos/assets/watermark.png".length() > 0) {
            try {
                URL url = new URL("https://s3.amazonaws.com/tapjoy/videos/assets/watermark.png");
                URLConnection openConnection = url.openConnection();
                openConnection.setConnectTimeout(15000);
                openConnection.setReadTimeout(25000);
                openConnection.connect();
                Bitmap unused = ao.o = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (Exception e) {
                aj.b("TapjoyVideo", "e: " + e.toString());
            }
        }
        this.a.i();
        this.a.k = true;
        z = this.a.j;
        if (z) {
            aj.a("TapjoyVideo", "trying to cache because of cache_auto flag...");
            this.a.d();
        }
        aj.a("TapjoyVideo", "------------------------------");
        aj.a("TapjoyVideo", "------------------------------");
        aj.a("TapjoyVideo", "INIT DONE!");
        aj.a("TapjoyVideo", "------------------------------");
    }
}
