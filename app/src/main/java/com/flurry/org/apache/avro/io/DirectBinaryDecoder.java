package com.flurry.org.apache.avro.io;

import com.flurry.android.Constants;
import com.flurry.org.apache.avro.util.ByteBufferInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class DirectBinaryDecoder extends BinaryDecoder {
    private final byte[] buf = new byte[8];
    private ByteReader byteReader;
    private InputStream in;

    /* loaded from: classes.dex */
    public class ByteReader {
        private ByteReader() {
            DirectBinaryDecoder.this = r1;
        }

        public ByteBuffer read(ByteBuffer byteBuffer, int i) {
            if (byteBuffer == null || i > byteBuffer.capacity()) {
                byteBuffer = ByteBuffer.allocate(i);
            } else {
                byteBuffer.clear();
            }
            DirectBinaryDecoder.this.doReadBytes(byteBuffer.array(), byteBuffer.position(), i);
            byteBuffer.limit(i);
            return byteBuffer;
        }
    }

    /* loaded from: classes.dex */
    public class ReuseByteReader extends ByteReader {
        private final ByteBufferInputStream bbi;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ReuseByteReader(ByteBufferInputStream byteBufferInputStream) {
            super();
            DirectBinaryDecoder.this = r2;
            this.bbi = byteBufferInputStream;
        }

        @Override // com.flurry.org.apache.avro.io.DirectBinaryDecoder.ByteReader
        public ByteBuffer read(ByteBuffer byteBuffer, int i) {
            return byteBuffer != null ? super.read(byteBuffer, i) : this.bbi.readBuffer(i);
        }
    }

    public DirectBinaryDecoder(InputStream inputStream) {
        configure(inputStream);
    }

    public DirectBinaryDecoder configure(InputStream inputStream) {
        this.in = inputStream;
        this.byteReader = inputStream instanceof ByteBufferInputStream ? new ReuseByteReader((ByteBufferInputStream) inputStream) : new ByteReader();
        return this;
    }

    @Override // com.flurry.org.apache.avro.io.BinaryDecoder
    protected void doReadBytes(byte[] bArr, int i, int i2) {
        while (true) {
            int read = this.in.read(bArr, i, i2);
            if (read == i2 || i2 == 0) {
                return;
            }
            if (read < 0) {
                throw new EOFException();
            }
            i += read;
            i2 -= read;
        }
    }

    @Override // com.flurry.org.apache.avro.io.BinaryDecoder
    protected void doSkipBytes(long j) {
        while (j > 0) {
            long skip = this.in.skip(j);
            if (skip <= 0) {
                throw new EOFException();
            }
            j -= skip;
        }
    }

    @Override // com.flurry.org.apache.avro.io.BinaryDecoder
    public InputStream inputStream() {
        return this.in;
    }

    @Override // com.flurry.org.apache.avro.io.BinaryDecoder
    public boolean isEnd() {
        throw new UnsupportedOperationException();
    }

    @Override // com.flurry.org.apache.avro.io.BinaryDecoder, com.flurry.org.apache.avro.io.Decoder
    public boolean readBoolean() {
        int read = this.in.read();
        if (read < 0) {
            throw new EOFException();
        }
        return read == 1;
    }

    @Override // com.flurry.org.apache.avro.io.BinaryDecoder, com.flurry.org.apache.avro.io.Decoder
    public ByteBuffer readBytes(ByteBuffer byteBuffer) {
        return this.byteReader.read(byteBuffer, readInt());
    }

    @Override // com.flurry.org.apache.avro.io.BinaryDecoder, com.flurry.org.apache.avro.io.Decoder
    public double readDouble() {
        doReadBytes(this.buf, 0, 8);
        return Double.longBitsToDouble((this.buf[0] & 255) | ((this.buf[1] & 255) << 8) | ((this.buf[2] & 255) << 16) | ((this.buf[3] & 255) << 24) | ((this.buf[4] & 255) << 32) | ((this.buf[5] & 255) << 40) | ((this.buf[6] & 255) << 48) | ((this.buf[7] & 255) << 56));
    }

    @Override // com.flurry.org.apache.avro.io.BinaryDecoder, com.flurry.org.apache.avro.io.Decoder
    public float readFloat() {
        doReadBytes(this.buf, 0, 4);
        return Float.intBitsToFloat((this.buf[0] & Constants.UNKNOWN) | ((this.buf[1] & Constants.UNKNOWN) << 8) | ((this.buf[2] & Constants.UNKNOWN) << 16) | ((this.buf[3] & Constants.UNKNOWN) << 24));
    }

    @Override // com.flurry.org.apache.avro.io.BinaryDecoder, com.flurry.org.apache.avro.io.Decoder
    public int readInt() {
        int i = 0;
        int i2 = 0;
        do {
            int read = this.in.read();
            if (read < 0) {
                throw new EOFException();
            }
            i2 |= (read & 127) << i;
            if ((read & 128) == 0) {
                return (i2 >>> 1) ^ (-(i2 & 1));
            }
            i += 7;
        } while (i < 32);
        throw new IOException("Invalid int encoding");
    }

    @Override // com.flurry.org.apache.avro.io.BinaryDecoder, com.flurry.org.apache.avro.io.Decoder
    public long readLong() {
        long j = 0;
        int i = 0;
        do {
            int read = this.in.read();
            if (read < 0) {
                throw new EOFException();
            }
            j |= (read & 127) << i;
            if ((read & 128) == 0) {
                return (-(j & 1)) ^ (j >>> 1);
            }
            i += 7;
        } while (i < 64);
        throw new IOException("Invalid long encoding");
    }
}
