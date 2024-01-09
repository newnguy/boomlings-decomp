package com.flurry.org.apache.avro.file;

import java.io.Closeable;

/* loaded from: classes.dex */
public interface SeekableInput extends Closeable {
    long length();

    int read(byte[] bArr, int i, int i2);

    void seek(long j);

    long tell();
}
