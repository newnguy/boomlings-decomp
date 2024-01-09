package com.flurry.org.apache.avro.util;

import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes.dex */
public class ByteBufferOutputStream extends OutputStream {
    public static final int BUFFER_SIZE = 8192;
    private List buffers;

    public ByteBufferOutputStream() {
        reset();
    }

    public void append(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ByteBuffer byteBuffer = (ByteBuffer) it.next();
            byteBuffer.position(byteBuffer.limit());
        }
        this.buffers.addAll(list);
    }

    public List getBufferList() {
        List<ByteBuffer> list = this.buffers;
        reset();
        for (ByteBuffer byteBuffer : list) {
            byteBuffer.flip();
        }
        return list;
    }

    public void prepend(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ByteBuffer byteBuffer = (ByteBuffer) it.next();
            byteBuffer.position(byteBuffer.limit());
        }
        this.buffers.addAll(0, list);
    }

    public void reset() {
        this.buffers = new LinkedList();
        this.buffers.add(ByteBuffer.allocate(BUFFER_SIZE));
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        ByteBuffer byteBuffer = (ByteBuffer) this.buffers.get(this.buffers.size() - 1);
        if (byteBuffer.remaining() < 1) {
            byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
            this.buffers.add(byteBuffer);
        }
        byteBuffer.put((byte) i);
    }

    public void write(ByteBuffer byteBuffer) {
        this.buffers.add(byteBuffer);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        ByteBuffer byteBuffer = (ByteBuffer) this.buffers.get(this.buffers.size() - 1);
        ByteBuffer byteBuffer2 = byteBuffer;
        int remaining = byteBuffer.remaining();
        while (i2 > remaining) {
            byteBuffer2.put(bArr, i, remaining);
            i2 -= remaining;
            i += remaining;
            byteBuffer2 = ByteBuffer.allocate(BUFFER_SIZE);
            this.buffers.add(byteBuffer2);
            remaining = byteBuffer2.remaining();
        }
        byteBuffer2.put(bArr, i, i2);
    }

    public void writeBuffer(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() < 8192) {
            write(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining());
            return;
        }
        ByteBuffer duplicate = byteBuffer.duplicate();
        duplicate.position(byteBuffer.limit());
        this.buffers.add(duplicate);
    }
}
