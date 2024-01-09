package com.chartboost.sdk.impl;

import java.io.Serializable;
import java.util.Date;

/* loaded from: classes.dex */
public class as implements Serializable, Comparable {
    static final boolean a = Boolean.getBoolean("DEBUG.DBTIMESTAMP");
    final int b = 0;
    final Date c = null;

    public int a() {
        if (this.c == null) {
            return 0;
        }
        return (int) (this.c.getTime() / 1000);
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(as asVar) {
        return a() != asVar.a() ? a() - asVar.a() : b() - asVar.b();
    }

    public int b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof as) {
            as asVar = (as) obj;
            return a() == asVar.a() && b() == asVar.b();
        }
        return false;
    }

    public int hashCode() {
        return ((this.b + 31) * 31) + a();
    }

    public String toString() {
        return "TS time:" + this.c + " inc:" + this.b;
    }
}
