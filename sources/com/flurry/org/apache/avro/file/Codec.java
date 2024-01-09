package com.flurry.org.apache.avro.file;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
abstract class Codec {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract ByteBuffer compress(ByteBuffer byteBuffer);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract ByteBuffer decompress(ByteBuffer byteBuffer);

    public abstract boolean equals(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getName();

    public abstract int hashCode();

    public String toString() {
        return getName();
    }
}
