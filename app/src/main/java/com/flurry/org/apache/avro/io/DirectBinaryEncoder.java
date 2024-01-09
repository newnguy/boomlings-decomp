package com.flurry.org.apache.avro.io;

import java.io.OutputStream;

/* loaded from: classes.dex */
public class DirectBinaryEncoder extends BinaryEncoder {
    private final byte[] buf = new byte[12];
    private OutputStream out;

    public DirectBinaryEncoder(OutputStream outputStream) {
        configure(outputStream);
    }

    @Override // com.flurry.org.apache.avro.io.BinaryEncoder
    public int bytesBuffered() {
        return 0;
    }

    public DirectBinaryEncoder configure(OutputStream outputStream) {
        if (outputStream == null) {
            throw new NullPointerException("OutputStream cannot be null!");
        }
        this.out = outputStream;
        return this;
    }

    @Override // java.io.Flushable
    public void flush() {
        this.out.flush();
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeBoolean(boolean z) {
        this.out.write(z ? 1 : 0);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeDouble(double d) {
        byte[] bArr = new byte[8];
        this.out.write(bArr, 0, BinaryData.encodeDouble(d, bArr, 0));
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeFixed(byte[] bArr, int i, int i2) {
        this.out.write(bArr, i, i2);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeFloat(float f) {
        this.out.write(this.buf, 0, BinaryData.encodeFloat(f, this.buf, 0));
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeInt(int i) {
        int i2 = (i << 1) ^ (i >> 31);
        if ((i2 & (-128)) == 0) {
            this.out.write(i2);
        } else if ((i2 & (-16384)) == 0) {
            this.out.write(i2 | 128);
            this.out.write(i2 >>> 7);
        } else {
            this.out.write(this.buf, 0, BinaryData.encodeInt(i, this.buf, 0));
        }
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeLong(long j) {
        long j2 = (j << 1) ^ (j >> 63);
        if (((-2147483648L) & j2) != 0) {
            this.out.write(this.buf, 0, BinaryData.encodeLong(j, this.buf, 0));
            return;
        }
        int i = (int) j2;
        while ((i & (-128)) != 0) {
            this.out.write((byte) ((i | 128) & 255));
            i >>>= 7;
        }
        this.out.write((byte) i);
    }

    @Override // com.flurry.org.apache.avro.io.BinaryEncoder
    protected void writeZero() {
        this.out.write(0);
    }
}
