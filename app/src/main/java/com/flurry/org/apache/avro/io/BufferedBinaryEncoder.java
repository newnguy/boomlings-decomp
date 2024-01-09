package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.AvroRuntimeException;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes.dex */
public class BufferedBinaryEncoder extends BinaryEncoder {
    private byte[] buf;
    private int bulkLimit;
    private int pos;
    private ByteSink sink;

    /* loaded from: classes.dex */
    public abstract class ByteSink {
        protected ByteSink() {
        }

        protected abstract void innerFlush();

        protected abstract void innerWrite(byte[] bArr, int i, int i2);
    }

    /* loaded from: classes.dex */
    public class OutputStreamSink extends ByteSink {
        private final OutputStream out;

        private OutputStreamSink(OutputStream outputStream) {
            this.out = outputStream;
        }

        @Override // com.flurry.org.apache.avro.io.BufferedBinaryEncoder.ByteSink
        protected void innerFlush() {
            this.out.flush();
        }

        @Override // com.flurry.org.apache.avro.io.BufferedBinaryEncoder.ByteSink
        protected void innerWrite(byte[] bArr, int i, int i2) {
            this.out.write(bArr, i, i2);
        }
    }

    public BufferedBinaryEncoder(OutputStream outputStream, int i) {
        configure(outputStream, i);
    }

    private void ensureBounds(int i) {
        if (this.buf.length - this.pos < i) {
            flushBuffer();
        }
    }

    private void flushBuffer() {
        if (this.pos > 0) {
            this.sink.innerWrite(this.buf, 0, this.pos);
            this.pos = 0;
        }
    }

    private void writeByte(int i) {
        if (this.pos == this.buf.length) {
            flushBuffer();
        }
        byte[] bArr = this.buf;
        int i2 = this.pos;
        this.pos = i2 + 1;
        bArr[i2] = (byte) (i & 255);
    }

    @Override // com.flurry.org.apache.avro.io.BinaryEncoder
    public int bytesBuffered() {
        return this.pos;
    }

    public BufferedBinaryEncoder configure(OutputStream outputStream, int i) {
        if (outputStream == null) {
            throw new NullPointerException("OutputStream cannot be null!");
        }
        if (this.sink != null && this.pos > 0) {
            try {
                flushBuffer();
            } catch (IOException e) {
                throw new AvroRuntimeException("Failure flushing old output", e);
            }
        }
        this.sink = new OutputStreamSink(outputStream);
        this.pos = 0;
        if (this.buf == null || this.buf.length != i) {
            this.buf = new byte[i];
        }
        this.bulkLimit = this.buf.length >>> 1;
        if (this.bulkLimit > 512) {
            this.bulkLimit = 512;
        }
        return this;
    }

    @Override // java.io.Flushable
    public void flush() {
        flushBuffer();
        this.sink.innerFlush();
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeBoolean(boolean z) {
        if (this.buf.length == this.pos) {
            flushBuffer();
        }
        this.pos += BinaryData.encodeBoolean(z, this.buf, this.pos);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeDouble(double d) {
        ensureBounds(8);
        this.pos += BinaryData.encodeDouble(d, this.buf, this.pos);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeFixed(byte[] bArr, int i, int i2) {
        if (i2 > this.bulkLimit) {
            flushBuffer();
            this.sink.innerWrite(bArr, i, i2);
            return;
        }
        ensureBounds(i2);
        System.arraycopy(bArr, i, this.buf, this.pos, i2);
        this.pos += i2;
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeFloat(float f) {
        ensureBounds(4);
        this.pos += BinaryData.encodeFloat(f, this.buf, this.pos);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeInt(int i) {
        ensureBounds(5);
        this.pos += BinaryData.encodeInt(i, this.buf, this.pos);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeLong(long j) {
        ensureBounds(10);
        this.pos += BinaryData.encodeLong(j, this.buf, this.pos);
    }

    @Override // com.flurry.org.apache.avro.io.BinaryEncoder
    protected void writeZero() {
        writeByte(0);
    }
}
