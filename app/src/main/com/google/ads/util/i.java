package com.google.ads.util;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes.dex */
public abstract class i {
    private static final Object a = new Object();
    private static int b = 0;
    private static HashMap c = new HashMap();
    private final ArrayList d = new ArrayList();
    public final int o;

    /* loaded from: classes.dex */
    public abstract class a {
        protected Object a;
        protected final String b;

        private a(i iVar, String str) {
            this(str, (Object) null);
        }

        private a(String str, Object obj) {
            this.b = str;
            i.this.a(this);
            this.a = obj;
        }

        public String toString() {
            return i.this.toString() + "." + this.b + " = " + this.a;
        }
    }

    /* loaded from: classes.dex */
    public final class b extends a {
        public b(String str, Object obj) {
            super(str, obj);
        }

        public Object a() {
            return this.a;
        }

        @Override // com.google.ads.util.i.a
        public String toString() {
            return super.toString() + " (!)";
        }
    }

    /* loaded from: classes.dex */
    public final class c extends a {
        private boolean e;

        public c(String str) {
            super(str);
            this.e = false;
            this.e = false;
        }

        public c(String str, Object obj) {
            super(str, obj);
            this.e = false;
            this.e = false;
        }

        public synchronized Object a() {
            return this.a;
        }

        public synchronized void a(Object obj) {
            com.google.ads.util.b.d("State changed - " + i.this.toString() + "." + this.b + ": '" + obj + "' <-- '" + this.a + "'.");
            this.a = obj;
            this.e = true;
        }

        @Override // com.google.ads.util.i.a
        public String toString() {
            return super.toString() + (this.e ? " (*)" : "");
        }
    }

    /* loaded from: classes.dex */
    public final class d extends a {
        public d(String str, Object obj) {
            super(str, new WeakReference(obj));
        }

        public Object a() {
            return ((WeakReference) this.a).get();
        }

        @Override // com.google.ads.util.i.a
        public String toString() {
            return i.this.toString() + "." + this.b + " = " + a() + " (?)";
        }
    }

    public i() {
        synchronized (a) {
            int i = b;
            b = i + 1;
            this.o = i;
            Integer num = (Integer) c.get(getClass());
            if (num == null) {
                c.put(getClass(), 1);
            } else {
                c.put(getClass(), Integer.valueOf(num.intValue() + 1));
            }
        }
        com.google.ads.util.b.d("State created: " + toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(a aVar) {
        this.d.add(aVar);
    }

    protected void finalize() {
        synchronized (a) {
            c.put(getClass(), Integer.valueOf(((Integer) c.get(getClass())).intValue() - 1));
        }
        super.finalize();
    }

    public String toString() {
        return getClass().getSimpleName() + "[" + this.o + "]";
    }
}
