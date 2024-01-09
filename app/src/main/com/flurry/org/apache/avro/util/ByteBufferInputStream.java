package com.flurry.org.apache.avro.util;

import com.flurry.android.Constants;
import java.io.EOFException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* loaded from: classes.dex */
public class ByteBufferInputStream extends InputStream {
    private List buffers;
    private int current;

    public ByteBufferInputStream(List list) {
        this.buffers = list;
    }

    private ByteBuffer getBuffer() {
        while (this.current < this.buffers.size()) {
            ByteBuffer byteBuffer = (ByteBuffer) this.buffers.get(this.current);
            if (byteBuffer.hasRemaining()) {
                return byteBuffer;
            }
            this.current++;
        }
        throw new EOFException();
    }

    @Override // java.io.InputStream
    public int read() {
        return getBuffer().get() & Constants.UNKNOWN;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        ByteBuffer buffer = getBuffer();
        int remaining = buffer.remaining();
        if (i2 > remaining) {
            buffer.get(bArr, i, remaining);
            return remaining;
        }
        buffer.get(bArr, i, i2);
        return i2;
    }

    public ByteBuffer readBuffer(int i) {
        int i2 = 0;
        if (i == 0) {
            return ByteBuffer.allocate(0);
        }
        ByteBuffer buffer = getBuffer();
        if (buffer.remaining() == i) {
            this.current++;
            return buffer;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        while (i2 < i) {
            i2 += read(allocate.array(), i2, i - i2);
        }
        return allocate;
    }
}
