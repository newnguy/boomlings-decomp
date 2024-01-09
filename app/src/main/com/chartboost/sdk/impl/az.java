package com.chartboost.sdk.impl;

import java.io.Serializable;

/* loaded from: classes.dex */
public class az implements Serializable {
    private final String a;

    public String a() {
        return this.a;
    }

    public boolean equals(Object obj) {
        String str;
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof az) {
            str = ((az) obj).a;
        } else if (!(obj instanceof String)) {
            return false;
        } else {
            str = (String) obj;
        }
        if (this.a != null) {
            if (this.a.equals(str)) {
                return true;
            }
        } else if (str == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.a != null) {
            return this.a.hashCode();
        }
        return 0;
    }

    public String toString() {
        return this.a;
    }
}
