package com.flurry.org.apache.avro.file;

import java.io.FilterInputStream;
import java.io.InputStream;

/* loaded from: classes.dex */
class LengthLimitedInputStream extends FilterInputStream {
    private long remaining;

    protected LengthLimitedInputStream(InputStream inputStream, long j) {
        super(inputStream);
        this.remaining = j;
    }

    private int remainingInt() {
        return (int) Math.min(this.remaining, 2147483647L);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() {
        return Math.min(super.available(), remainingInt());
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        if (this.remaining > 0) {
            int read = super.read();
            if (read != -1) {
                this.remaining--;
                return read;
            }
            return read;
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (this.remaining == 0) {
            return -1;
        }
        if (i2 > this.remaining) {
            i2 = remainingInt();
        }
        int read = super.read(bArr, i, i2);
        if (read != -1) {
            this.remaining -= read;
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) {
        long skip = super.skip(Math.min(this.remaining, j));
        this.remaining -= skip;
        return skip;
    }
}
