package com.chartboost.sdk.impl;

/* loaded from: classes.dex */
public class z {
    final Object a;
    final String b;

    public Object a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        z zVar = (z) obj;
        if (this.a == null ? zVar.a != null : !this.a.equals(zVar.a)) {
            return false;
        }
        if (this.b != null) {
            if (this.b.equals(zVar.b)) {
                return true;
            }
        } else if (zVar.b == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((this.a != null ? this.a.hashCode() : 0) * 31) + (this.b != null ? this.b.hashCode() : 0);
    }

    public String toString() {
        return "{ \"$ref\" : \"" + this.b + "\", \"$id\" : \"" + this.a + "\" }";
    }
}
