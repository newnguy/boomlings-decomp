package com.chartboost.sdk.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class aq extends ap {
    private static bh g = new bh(640) { // from class: com.chartboost.sdk.impl.aq.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.chartboost.sdk.impl.bh
        /* renamed from: a */
        public byte[] b() {
            return new byte[16384];
        }
    };
    final byte[] a = new byte[16384];
    final char[] b = new char[16384];
    final List c = new ArrayList();
    final ar d = new ar();
    private final a e = new a();
    private final a f = new a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a {
        int a;
        int b;

        a() {
            a();
        }

        void a() {
            this.a = -1;
            this.b = 0;
        }

        void a(int i) {
            this.a = (i / 16384) - 1;
            this.b = i % 16384;
        }

        void a(a aVar) {
            this.a = aVar.a;
            this.b = aVar.b;
        }

        int b() {
            return ((this.a + 1) * 16384) + this.b;
        }

        void b(int i) {
            this.b += i;
            if (this.b > 16384) {
                throw new IllegalArgumentException("something is wrong");
            }
        }

        int c() {
            int i = this.b;
            this.b = i + 1;
            return i;
        }

        int c(int i) {
            if (i < this.a) {
                return 16384;
            }
            return this.b;
        }

        void d() {
            if (this.b != 16384) {
                throw new IllegalArgumentException("broken");
            }
            this.a++;
            this.b = 0;
        }

        public String toString() {
            return this.a + "," + this.b;
        }
    }

    public aq() {
        d();
    }

    @Override // com.chartboost.sdk.impl.ap
    public int a() {
        return this.e.b();
    }

    @Override // com.chartboost.sdk.impl.ap
    public int a(OutputStream outputStream) {
        if (outputStream == null) {
            throw new NullPointerException("out is null");
        }
        int i = 0;
        for (int i2 = -1; i2 < this.c.size(); i2++) {
            byte[] b = b(i2);
            int c = this.f.c(i2);
            outputStream.write(b, 0, c);
            i += c;
        }
        return i;
    }

    @Override // com.chartboost.sdk.impl.ap
    public void a(int i) {
        this.e.a(i);
    }

    @Override // com.chartboost.sdk.impl.ap
    public int b() {
        return this.f.b();
    }

    byte[] b(int i) {
        return i < 0 ? this.a : (byte[]) this.c.get(i);
    }

    public void d() {
        this.e.a();
        this.f.a();
        for (int i = 0; i < this.c.size(); i++) {
            g.b(this.c.get(i));
        }
        this.c.clear();
    }

    void e() {
        if (this.e.b() < this.f.b()) {
            if (this.e.b == 16384) {
                this.e.d();
                return;
            }
            return;
        }
        this.f.a(this.e);
        if (this.f.b >= 16384) {
            this.c.add(g.c());
            this.f.d();
            this.e.a(this.f);
        }
    }

    byte[] f() {
        return b(this.e.a);
    }

    @Override // com.chartboost.sdk.impl.ap, java.io.OutputStream
    public void write(int i) {
        f()[this.e.c()] = (byte) (i & 255);
        e();
    }

    @Override // com.chartboost.sdk.impl.ap, java.io.OutputStream
    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            byte[] f = f();
            int min = Math.min(f.length - this.e.b, i2);
            System.arraycopy(bArr, i, f, this.e.b, min);
            this.e.b(min);
            i2 -= min;
            i += min;
            e();
        }
    }
}
