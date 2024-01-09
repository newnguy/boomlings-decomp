package com.google.ads.util;

import android.os.Build;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class d {
    static final d d = new d();
    static final d e = new d("unknown", "generic", "generic");
    public final String a;
    public final String b;
    public final String c;

    d() {
        this.a = Build.BOARD;
        this.b = Build.DEVICE;
        this.c = Build.BRAND;
    }

    d(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    private static boolean a(String str, String str2) {
        return str != null ? str.equals(str2) : str == str2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof d) {
            d dVar = (d) obj;
            return a(this.a, dVar.a) && a(this.b, dVar.b) && a(this.c, dVar.c);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.a != null ? 0 + this.a.hashCode() : 0;
        if (this.b != null) {
            hashCode += this.b.hashCode();
        }
        return this.c != null ? hashCode + this.c.hashCode() : hashCode;
    }
}
