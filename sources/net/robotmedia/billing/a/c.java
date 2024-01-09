package net.robotmedia.billing.a;

import org.json.JSONObject;

/* loaded from: classes.dex */
public class c {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public d f;
    public long g;

    public c() {
    }

    public c(String str, String str2, String str3, d dVar, String str4, long j, String str5) {
        this.c = str;
        this.e = str2;
        this.d = str3;
        this.f = dVar;
        this.b = str4;
        this.g = j;
        this.a = str5;
    }

    public static c a(JSONObject jSONObject) {
        c cVar = new c();
        cVar.f = d.a(jSONObject.getInt("purchaseState"));
        cVar.e = jSONObject.getString("productId");
        cVar.d = jSONObject.getString("packageName");
        cVar.g = jSONObject.getLong("purchaseTime");
        cVar.c = jSONObject.optString("orderId", null);
        cVar.b = jSONObject.optString("notificationId", null);
        cVar.a = jSONObject.optString("developerPayload", null);
        return cVar;
    }

    /* renamed from: a */
    public c clone() {
        return new c(this.c, this.e, this.d, this.f, this.b, this.g, this.a);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            c cVar = (c) obj;
            if (this.a == null) {
                if (cVar.a != null) {
                    return false;
                }
            } else if (!this.a.equals(cVar.a)) {
                return false;
            }
            if (this.b == null) {
                if (cVar.b != null) {
                    return false;
                }
            } else if (!this.b.equals(cVar.b)) {
                return false;
            }
            if (this.c == null) {
                if (cVar.c != null) {
                    return false;
                }
            } else if (!this.c.equals(cVar.c)) {
                return false;
            }
            if (this.d == null) {
                if (cVar.d != null) {
                    return false;
                }
            } else if (!this.d.equals(cVar.d)) {
                return false;
            }
            if (this.e == null) {
                if (cVar.e != null) {
                    return false;
                }
            } else if (!this.e.equals(cVar.e)) {
                return false;
            }
            return this.f == cVar.f && this.g == cVar.g;
        }
        return false;
    }

    public String toString() {
        return String.valueOf(this.c);
    }
}
