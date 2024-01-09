package com.chartboost.sdk.impl;

/* loaded from: classes.dex */
public class bb {

    /* loaded from: classes.dex */
    class a extends IllegalArgumentException {
        a(String str) {
            super(str + " should not be null!");
        }
    }

    public static Object a(String str, Object obj) {
        if (obj == null) {
            throw new a(str);
        }
        return obj;
    }
}
