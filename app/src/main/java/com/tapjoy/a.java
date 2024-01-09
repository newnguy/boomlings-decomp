package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import org.w3c.dom.Document;

/* loaded from: classes.dex */
public class a {
    private static ak d;
    private static al e;
    private static v f;
    Context c;
    String a = null;
    int b = 0;
    private String g = "";
    private String h = "";

    public a(Context context) {
        this.c = context;
    }

    public synchronized boolean a(String str) {
        boolean z;
        Document c = an.c(str);
        if (c != null) {
            String a = an.a(c.getElementsByTagName("Success"));
            if (a == null || !a.equals("true")) {
                aj.b("TapjoyPoints", "Invalid XML: Missing <Success> tag.");
            } else {
                String a2 = an.a(c.getElementsByTagName("TapPoints"));
                String a3 = an.a(c.getElementsByTagName("CurrencyName"));
                if (a2 == null || a3 == null) {
                    aj.b("TapjoyPoints", "Invalid XML: Missing tags.");
                } else {
                    try {
                        int parseInt = Integer.parseInt(a2);
                        int i = h.i();
                        if (f != null && i != -9999 && parseInt > i) {
                            aj.a("TapjoyPoints", "earned: " + (parseInt - i));
                            f.earnedTapPoints(parseInt - i);
                        }
                        h.a(Integer.parseInt(a2));
                        d.getUpdatePoints(a3, Integer.parseInt(a2));
                        z = true;
                    } catch (Exception e2) {
                        aj.b("TapjoyPoints", "Error parsing XML.");
                    }
                }
            }
        }
        z = false;
        return z;
    }

    public boolean b(String str) {
        Document c = an.c(str);
        if (c != null) {
            String a = an.a(c.getElementsByTagName("Success"));
            if (a != null && a.equals("true")) {
                String a2 = an.a(c.getElementsByTagName("TapPoints"));
                String a3 = an.a(c.getElementsByTagName("CurrencyName"));
                if (a2 != null && a3 != null) {
                    h.a(Integer.parseInt(a2));
                    e.getSpendPointsResponse(a3, Integer.parseInt(a2));
                    return true;
                }
                aj.b("TapjoyPoints", "Invalid XML: Missing tags.");
            } else if (a != null && a.endsWith("false")) {
                String a4 = an.a(c.getElementsByTagName("Message"));
                aj.a("TapjoyPoints", a4);
                e.getSpendPointsResponseFailed(a4);
                return true;
            } else {
                aj.b("TapjoyPoints", "Invalid XML: Missing <Success> tag.");
            }
        }
        return false;
    }

    public void a() {
        aj.a("TapjoyOffers", "Showing offers with userID: " + h.e());
        Intent intent = new Intent(this.c, TJCOffersWebView.class);
        intent.setFlags(268435456);
        intent.putExtra("USER_ID", h.e());
        intent.putExtra("URL_PARAMS", h.c());
        this.c.startActivity(intent);
    }

    public void a(int i, al alVar) {
        if (i < 0) {
            aj.b("TapjoyPoints", "spendTapPoints error: amount must be a positive number");
            return;
        }
        this.a = "" + i;
        e = alVar;
        new Thread(new c(this)).start();
    }

    public void a(ak akVar) {
        d = akVar;
        new Thread(new b(this)).start();
    }

    public void a(v vVar) {
        f = vVar;
    }
}
