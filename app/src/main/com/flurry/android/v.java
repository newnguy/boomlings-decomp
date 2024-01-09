package com.flurry.android;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class v {
    private static String a = "FlurryAgent";
    private bo b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public v(bo boVar) {
        this.b = boVar;
    }

    private static boolean a(String str, String str2) {
        return str2.equals("%{" + str + "}");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String a(bl blVar, AdUnit adUnit, String str, String str2) {
        String str3;
        if (a("fids", str2)) {
            String str4 = "0:" + this.b.e();
            bm.a(a, "Replacing param fids with: " + str4);
            return str.replace(str2, ac.b(str4));
        } else if (a("sid", str2)) {
            String valueOf = String.valueOf(this.b.b());
            bm.a(a, "Replacing param sid with: " + valueOf);
            return str.replace(str2, ac.b(valueOf));
        } else if (a("lid", str2)) {
            String valueOf2 = String.valueOf(blVar.a());
            bm.a(a, "Replacing param lid with: " + valueOf2);
            return str.replace(str2, ac.b(valueOf2));
        } else if (a("guid", str2)) {
            String b = blVar.b();
            bm.a(a, "Replacing param guid with: " + b);
            return str.replace(str2, ac.b(b));
        } else if (a("ats", str2)) {
            String valueOf3 = String.valueOf(System.currentTimeMillis());
            bm.a(a, "Replacing param ats with: " + valueOf3);
            return str.replace(str2, ac.b(valueOf3));
        } else if (a("apik", str2)) {
            String c = this.b.c();
            bm.a(a, "Replacing param apik with: " + c);
            return str.replace(str2, ac.b(c));
        } else if (a("hid", str2)) {
            String obj = adUnit.a().toString();
            bm.a(a, "Replacing param hid with: " + obj);
            return str.replace(str2, ac.b(obj));
        } else if (a("eso", str2)) {
            String l = Long.toString(System.currentTimeMillis() - this.b.b());
            bm.a(a, "Replacing param eso with: " + l);
            return str.replace(str2, ac.b(l));
        } else if (!a("uc", str2)) {
            bm.a(a, "Unknown param: " + str2);
            return str.replace(str2, "");
        } else {
            String str5 = "";
            Iterator it = this.b.i().entrySet().iterator();
            while (true) {
                str3 = str5;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry entry = (Map.Entry) it.next();
                str5 = str3 + "c_" + ac.b((String) entry.getKey()) + "=" + ac.b((String) entry.getValue()) + "&";
            }
            bm.a(a, "Replacing param uc with: " + str3);
            String replace = str.replace(str2, str3);
            return (!str3.equals("") || replace.length() <= 0) ? replace : replace.substring(0, replace.length() - 1);
        }
    }
}
