package com.flurry.org.apache.avro.file;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;

/* loaded from: classes.dex */
public class SeekableFileInput extends FileInputStream implements SeekableInput {
    public SeekableFileInput(File file) {
        super(file);
    }

    public SeekableFileInput(FileDescriptor fileDescriptor) {
        super(fileDescriptor);
    }

    @Override // com.flurry.org.apache.avro.file.SeekableInput
    public long length() {
        return getChannel().size();
    }

    @Override // com.flurry.org.apache.avro.file.SeekableInput
    public void seek(long j) {
        getChannel().position(j);
    }

    @Override // com.flurry.org.apache.avro.file.SeekableInput
    public long tell() {
        return getChannel().position();
    }
}
