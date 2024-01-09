package com.chartboost.sdk.impl;

import java.io.OutputStream;

/* loaded from: classes.dex */
public class ao extends ap {
    private int a;
    private int b;
    private byte[] c = new byte[512];

    @Override // com.chartboost.sdk.impl.ap
    public int a() {
        return this.a;
    }

    @Override // com.chartboost.sdk.impl.ap
    public int a(OutputStream outputStream) {
        outputStream.write(this.c, 0, this.b);
        return this.b;
    }

    @Override // com.chartboost.sdk.impl.ap
    public void a(int i) {
        this.a = i;
    }

    @Override // com.chartboost.sdk.impl.ap
    public int b() {
        return this.b;
    }

    void b(int i) {
        int i2 = this.a + i;
        if (i2 < this.c.length) {
            return;
        }
        int length = this.c.length * 2;
        if (length <= i2) {
            length = i2 + 128;
        }
        byte[] bArr = new byte[length];
        System.arraycopy(this.c, 0, bArr, 0, this.b);
        this.c = bArr;
    }

    @Override // com.chartboost.sdk.impl.ap, java.io.OutputStream
    public void write(int i) {
        b(1);
        byte[] bArr = this.c;
        int i2 = this.a;
        this.a = i2 + 1;
        bArr[i2] = (byte) (i & 255);
        this.b = Math.max(this.a, this.b);
    }

    @Override // com.chartboost.sdk.impl.ap, java.io.OutputStream
    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        b(i2);
        System.arraycopy(bArr, i, this.c, this.a, i2);
        this.a += i2;
        this.b = Math.max(this.a, this.b);
    }
}
