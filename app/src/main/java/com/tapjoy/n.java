package com.tapjoy;

import android.content.Context;
import android.content.Intent;

/* loaded from: classes.dex */
public class n {
    public static String a;
    private static p c;
    private static am d = null;
    private static String g;
    final String b = "Daily Reward";
    private Context e;
    private String f;

    public n(Context context) {
        this.e = context;
        d = new am();
    }

    public void a() {
        aj.a("Daily Reward", "Displaying Daily Reward ad...");
        if (g == null || g.length() == 0) {
            return;
        }
        Intent intent = new Intent(this.e, TapjoyDailyRewardAdWebView.class);
        intent.setFlags(268435456);
        intent.putExtra("RE_ENGAGEMENT_HTML_DATA", g);
        this.e.startActivity(intent);
    }

    public void a(p pVar) {
        aj.a("Daily Reward", "Getting Daily Reward Ad");
        a(null, pVar);
    }

    public void a(String str, p pVar) {
        this.f = str;
        aj.a("Daily Reward", "Getting Daily Reward ad userID: " + h.e() + ", currencyID: " + this.f);
        c = pVar;
        a = h.c();
        a += "&publisher_user_id=" + h.e();
        if (this.f != null) {
            a += "&currency_id=" + this.f;
        }
        new Thread(new o(this)).start();
    }
}
