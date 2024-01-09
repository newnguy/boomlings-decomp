package com.chartboost.sdk.impl;

/* loaded from: classes.dex */
public class bb {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends IllegalArgumentException {
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
