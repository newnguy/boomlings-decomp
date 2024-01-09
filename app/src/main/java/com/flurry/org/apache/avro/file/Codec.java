package com.flurry.org.apache.avro.file;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
abstract class Codec {
    public abstract ByteBuffer compress(ByteBuffer byteBuffer);

    public abstract ByteBuffer decompress(ByteBuffer byteBuffer);

    public abstract boolean equals(Object obj);

    public abstract String getName();

    public abstract int hashCode();

    public String toString() {
        return getName();
    }
}
