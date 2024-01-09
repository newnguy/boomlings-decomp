package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.util.Utf8;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public abstract class BinaryEncoder extends Encoder {
    public abstract int bytesBuffered();

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void setItemCount(long j) {
        if (j > 0) {
            writeLong(j);
        }
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void startItem() {
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeArrayEnd() {
        writeZero();
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeArrayStart() {
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeBytes(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        writeBytes(byteBuffer.array(), byteBuffer.arrayOffset() + position, byteBuffer.limit() - position);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeBytes(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            writeZero();
            return;
        }
        writeInt(i2);
        writeFixed(bArr, i, i2);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeEnum(int i) {
        writeInt(i);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeIndex(int i) {
        writeInt(i);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeMapEnd() {
        writeZero();
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeMapStart() {
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeNull() {
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeString(Utf8 utf8) {
        writeBytes(utf8.getBytes(), 0, utf8.getByteLength());
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeString(String str) {
        if (str.length() == 0) {
            writeZero();
            return;
        }
        byte[] bytes = str.getBytes("UTF-8");
        writeInt(bytes.length);
        writeFixed(bytes, 0, bytes.length);
    }

    protected abstract void writeZero();
}
