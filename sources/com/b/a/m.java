package com.b.a;

/* loaded from: classes.dex */
public class m extends RuntimeException {
    private int a;
    private String b;

    public m(String str) {
        super(str);
        this.a = 0;
    }

    public m(String str, String str2, int i) {
        super(str);
        this.a = 0;
        this.b = str2;
        this.a = i;
    }
}
