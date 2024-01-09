package com.chartboost.sdk.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes.dex */
public abstract class ap extends OutputStream {
    public abstract int a();

    public abstract int a(OutputStream outputStream);

    public void a(double d) {
        a(Double.doubleToRawLongBits(d));
    }

    public abstract void a(int i);

    public void a(int i, int i2) {
        int a = a();
        a(i);
        c(i2);
        a(a);
    }

    public void a(long j) {
        write((byte) ((j >> 0) & 255));
        write((byte) ((j >> 8) & 255));
        write((byte) ((j >> 16) & 255));
        write((byte) ((j >> 24) & 255));
        write((byte) ((j >> 32) & 255));
        write((byte) ((j >> 40) & 255));
        write((byte) ((j >> 48) & 255));
        write((byte) ((j >> 56) & 255));
    }

    public abstract int b();

    public void c(int i) {
        write(i >> 0);
        write(i >> 8);
        write(i >> 16);
        write(i >> 24);
    }

    public byte[] c() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(b());
            a(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("should be impossible", e);
        }
    }

    public void d(int i) {
        write(i >> 24);
        write(i >> 16);
        write(i >> 8);
        write(i);
    }

    public String toString() {
        return getClass().getName() + " size: " + b() + " pos: " + a();
    }

    @Override // java.io.OutputStream
    public abstract void write(int i);

    @Override // java.io.OutputStream
    public abstract void write(byte[] bArr);
}
