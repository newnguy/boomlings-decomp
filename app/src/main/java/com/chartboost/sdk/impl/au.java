package com.chartboost.sdk.impl;

import java.io.Serializable;

/* loaded from: classes.dex */
public class au implements Serializable {
    final String a;

    public String a() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof au) {
            return this.a.equals(((au) obj).a);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return a();
    }
}
