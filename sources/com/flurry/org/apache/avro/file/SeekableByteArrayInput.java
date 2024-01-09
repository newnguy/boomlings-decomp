package com.flurry.org.apache.avro.file;

import java.io.ByteArrayInputStream;

/* loaded from: classes.dex */
public class SeekableByteArrayInput extends ByteArrayInputStream implements SeekableInput {
    public SeekableByteArrayInput(byte[] bArr) {
        super(bArr);
    }

    @Override // com.flurry.org.apache.avro.file.SeekableInput
    public long length() {
        return this.count;
    }

    @Override // com.flurry.org.apache.avro.file.SeekableInput
    public void seek(long j) {
        reset();
        skip(j);
    }

    @Override // com.flurry.org.apache.avro.file.SeekableInput
    public long tell() {
        return this.pos;
    }
}
