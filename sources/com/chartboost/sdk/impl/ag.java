package com.chartboost.sdk.impl;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class ag {
    static final Logger f = Logger.getLogger("org.bson.BSON");
    private static boolean a = false;
    private static boolean b = false;
    static bd g = new bd();
    static bd h = new bd();
    protected static Charset i = Charset.forName("UTF-8");
    static ThreadLocal j = new ThreadLocal() { // from class: com.chartboost.sdk.impl.ag.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public ai initialValue() {
            return new al();
        }
    };
    static ThreadLocal k = new ThreadLocal() { // from class: com.chartboost.sdk.impl.ag.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public ah initialValue() {
            return new ak();
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum a {
        CANON_EQ(128, 'c', "Pattern.CANON_EQ"),
        UNIX_LINES(1, 'd', "Pattern.UNIX_LINES"),
        GLOBAL(256, 'g', null),
        CASE_INSENSITIVE(2, 'i', null),
        MULTILINE(8, 'm', null),
        DOTALL(32, 's', "Pattern.DOTALL"),
        LITERAL(16, 't', "Pattern.LITERAL"),
        UNICODE_CASE(64, 'u', "Pattern.UNICODE_CASE"),
        COMMENTS(4, 'x', null);
        
        private static final Map m = new HashMap();
        public final int j;
        public final char k;
        public final String l;

        static {
            a[] values;
            for (a aVar : values()) {
                m.put(Character.valueOf(aVar.k), aVar);
            }
        }

        a(int i, char c, String str) {
            this.j = i;
            this.k = c;
            this.l = str;
        }
    }

    public static Object a(Object obj) {
        List<an> list;
        if (a() && g.a() != 0 && obj != null && (list = (List) g.a((Object) obj.getClass())) != null) {
            for (an anVar : list) {
                obj = anVar.a(obj);
            }
        }
        return obj;
    }

    public static String a(int i2) {
        a[] values;
        StringBuilder sb = new StringBuilder();
        int i3 = i2;
        for (a aVar : a.values()) {
            if ((aVar.j & i3) > 0) {
                sb.append(aVar.k);
                i3 -= aVar.j;
            }
        }
        if (i3 > 0) {
            throw new IllegalArgumentException("some flags could not be recognized.");
        }
        return sb.toString();
    }

    private static boolean a() {
        return a || b;
    }
}
