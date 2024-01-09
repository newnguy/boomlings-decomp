package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.util.Utf8;
import java.io.Flushable;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public abstract class Encoder implements Flushable {
    public abstract void setItemCount(long j);

    public abstract void startItem();

    public abstract void writeArrayEnd();

    public abstract void writeArrayStart();

    public abstract void writeBoolean(boolean z);

    public abstract void writeBytes(ByteBuffer byteBuffer);

    public void writeBytes(byte[] bArr) {
        writeBytes(bArr, 0, bArr.length);
    }

    public abstract void writeBytes(byte[] bArr, int i, int i2);

    public abstract void writeDouble(double d);

    public abstract void writeEnum(int i);

    public void writeFixed(byte[] bArr) {
        writeFixed(bArr, 0, bArr.length);
    }

    public abstract void writeFixed(byte[] bArr, int i, int i2);

    public abstract void writeFloat(float f);

    public abstract void writeIndex(int i);

    public abstract void writeInt(int i);

    public abstract void writeLong(long j);

    public abstract void writeMapEnd();

    public abstract void writeMapStart();

    public abstract void writeNull();

    public abstract void writeString(Utf8 utf8);

    public void writeString(CharSequence charSequence) {
        if (charSequence instanceof Utf8) {
            writeString((Utf8) charSequence);
        } else {
            writeString(charSequence.toString());
        }
    }

    public void writeString(String str) {
        writeString(new Utf8(str));
    }
}
