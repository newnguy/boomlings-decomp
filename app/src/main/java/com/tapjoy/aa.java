package com.tapjoy;

import android.content.Context;
import android.content.Intent;

/* loaded from: classes.dex */
public class aa {
    public static String a;
    private static y c;
    private static ac d;
    private static am e = null;
    private static String h;
    final String b = "Full Screen Ad";
    private Context f;
    private String g;

    public aa(Context context) {
        this.f = context;
        e = new am();
    }

    public void a() {
        if (h == null || h.length() <= 0) {
            return;
        }
        Intent intent = new Intent(this.f, TapjoyFullScreenAdWebView.class);
        intent.setFlags(268435456);
        intent.putExtra("FULLSCREEN_HTML_DATA", h);
        this.f.startActivity(intent);
    }

    public void a(ac acVar) {
        a(null, acVar);
    }

    public void a(String str) {
        aj.a("Full Screen Ad", "Getting Full Screen Ad");
        this.g = str;
        aj.a("Full Screen Ad", "Getting Full Screen Ad userID: " + h.e() + ", currencyID: " + this.g);
        a = h.c();
        a += "&publisher_user_id=" + h.e();
        if (this.g != null) {
            a += "&currency_id=" + this.g;
        }
        if (h.f().length() > 0) {
            a += "&" + h.f();
        }
        new Thread(new ab(this)).start();
    }

    public void a(String str, ac acVar) {
        d = acVar;
        a(str);
    }
}
