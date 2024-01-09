package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.util.Utf8;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public abstract class Decoder {
    public abstract long arrayNext();

    public abstract long mapNext();

    public abstract long readArrayStart();

    public abstract boolean readBoolean();

    public abstract ByteBuffer readBytes(ByteBuffer byteBuffer);

    public abstract double readDouble();

    public abstract int readEnum();

    public void readFixed(byte[] bArr) {
        readFixed(bArr, 0, bArr.length);
    }

    public abstract void readFixed(byte[] bArr, int i, int i2);

    public abstract float readFloat();

    public abstract int readIndex();

    public abstract int readInt();

    public abstract long readLong();

    public abstract long readMapStart();

    public abstract void readNull();

    public abstract Utf8 readString(Utf8 utf8);

    public abstract String readString();

    public abstract long skipArray();

    public abstract void skipBytes();

    public abstract void skipFixed(int i);

    public abstract long skipMap();

    public abstract void skipString();
}
