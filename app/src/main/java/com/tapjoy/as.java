package com.tapjoy;

import java.lang.reflect.Array;

/* loaded from: classes.dex */
public class as {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String[][] j = (String[][]) Array.newInstance(String.class, 10, 2);
    public int k;

    public void a(String str, String str2) {
        this.j[this.k][0] = str;
        this.j[this.k][1] = str2;
        this.k++;
    }
}
