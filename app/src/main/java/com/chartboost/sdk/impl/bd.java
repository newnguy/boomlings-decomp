package com.chartboost.sdk.impl;

import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class bd {
    private final Map a = bf.c();
    private final Map b = be.a((bg) new a());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class a implements bg {
        private a() {
            bd.this = r1;
        }

        @Override // com.chartboost.sdk.impl.bg
        public Object a(Class cls) {
            for (Class cls2 : bd.a(cls)) {
                Object obj = bd.this.a.get(cls2);
                if (obj != null) {
                    return obj;
                }
            }
            return null;
        }
    }

    public static List a(Class cls) {
        return bc.a(cls);
    }

    public int a() {
        return this.a.size();
    }

    public Object a(Class cls, Object obj) {
        try {
            return this.a.put(cls, obj);
        } finally {
            this.b.clear();
        }
    }

    public Object a(Object obj) {
        return this.b.get(obj);
    }
}
