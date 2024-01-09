package com.flurry.org.apache.avro.io;

import com.flurry.android.Constants;
import com.flurry.org.apache.avro.util.ByteBufferOutputStream;
import com.flurry.org.apache.avro.util.Utf8;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class BinaryDecoder extends Decoder {
    private ByteSource source = null;
    private byte[] buf = null;
    private int minPos = 0;
    private int pos = 0;
    private int limit = 0;
    private final Utf8 scratchUtf8 = new Utf8();

    /* loaded from: classes.dex */
    public class BufferAccessor {
        private byte[] buf;
        private final BinaryDecoder decoder;
        boolean detached;
        private int limit;
        private int pos;

        private BufferAccessor(BinaryDecoder binaryDecoder) {
            this.detached = false;
            this.decoder = binaryDecoder;
        }

        void detach() {
            this.buf = this.decoder.buf;
            this.pos = this.decoder.pos;
            this.limit = this.decoder.limit;
            this.detached = true;
        }

        public byte[] getBuf() {
            return this.detached ? this.buf : this.decoder.buf;
        }

        int getLim() {
            return this.detached ? this.limit : this.decoder.limit;
        }

        public int getPos() {
            return this.detached ? this.pos : this.decoder.pos;
        }

        void setBuf(byte[] bArr, int i, int i2) {
            if (this.detached) {
                this.buf = bArr;
                this.limit = i + i2;
                this.pos = i;
                return;
            }
            this.decoder.buf = bArr;
            this.decoder.limit = i + i2;
            this.decoder.pos = i;
            this.decoder.minPos = i;
        }

        void setLimit(int i) {
            if (this.detached) {
                this.limit = i;
            } else {
                this.decoder.limit = i;
            }
        }

        void setPos(int i) {
            if (this.detached) {
                this.pos = i;
            } else {
                this.decoder.pos = i;
            }
        }
    }

    /* loaded from: classes.dex */
    public class ByteArrayByteSource extends ByteSource {
        private boolean compacted;
        private byte[] data;
        private int max;
        private int position;

        private ByteArrayByteSource(byte[] bArr, int i, int i2) {
            this.compacted = false;
            if (bArr.length >= 16 && i2 >= 16) {
                this.data = bArr;
                this.position = i;
                this.max = i + i2;
                return;
            }
            this.data = new byte[16];
            System.arraycopy(bArr, i, this.data, 0, i2);
            this.position = 0;
            this.max = i2;
        }

        @Override // com.flurry.org.apache.avro.io.BinaryDecoder.ByteSource
        protected void attach(int i, BinaryDecoder binaryDecoder) {
            binaryDecoder.buf = this.data;
            binaryDecoder.pos = this.position;
            binaryDecoder.minPos = this.position;
            binaryDecoder.limit = this.max;
            this.ba = new BufferAccessor();
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.ba.setPos(this.ba.getLim());
        }

        @Override // com.flurry.org.apache.avro.io.BinaryDecoder.ByteSource
        protected void compactAndFill(byte[] bArr, int i, int i2, int i3) {
            if (this.compacted) {
                return;
            }
            byte[] bArr2 = new byte[i3 + 16];
            System.arraycopy(bArr, i, bArr2, 0, i3);
            this.ba.setBuf(bArr2, 0, i3);
            this.compacted = true;
        }

        @Override // com.flurry.org.apache.avro.io.BinaryDecoder.ByteSource
        public boolean isEof() {
            return this.ba.getLim() - this.ba.getPos() == 0;
        }

        @Override // java.io.InputStream
        public int read() {
            this.max = this.ba.getLim();
            this.position = this.ba.getPos();
            if (this.position >= this.max) {
                return -1;
            }
            byte[] buf = this.ba.getBuf();
            int i = this.position;
            this.position = i + 1;
            int i2 = buf[i] & Constants.UNKNOWN;
            this.ba.setPos(this.position);
            return i2;
        }

        @Override // com.flurry.org.apache.avro.io.BinaryDecoder.ByteSource
        protected void readRaw(byte[] bArr, int i, int i2) {
            if (tryReadRaw(bArr, i, i2) < i2) {
                throw new EOFException();
            }
        }

        @Override // com.flurry.org.apache.avro.io.BinaryDecoder.ByteSource
        protected void skipSourceBytes(long j) {
            if (trySkipBytes(j) < j) {
                throw new EOFException();
            }
        }

        @Override // com.flurry.org.apache.avro.io.BinaryDecoder.ByteSource
        protected int tryReadRaw(byte[] bArr, int i, int i2) {
            return 0;
        }

        @Override // com.flurry.org.apache.avro.io.BinaryDecoder.ByteSource
        protected long trySkipBytes(long j) {
            this.max = this.ba.getLim();
            this.position = this.ba.getPos();
            long j2 = this.max - this.position;
            if (j2 >= j) {
                this.position = (int) (this.position + j);
                this.ba.setPos(this.position);
                return j;
            }
            this.position = (int) (this.position + j2);
            this.ba.setPos(this.position);
            return j2;
        }
    }

    /* loaded from: classes.dex */
    public abstract class ByteSource extends InputStream {
        protected BufferAccessor ba;

        protected ByteSource() {
        }

        protected void attach(int i, BinaryDecoder binaryDecoder) {
            binaryDecoder.buf = new byte[i];
            binaryDecoder.pos = 0;
            binaryDecoder.minPos = 0;
            binaryDecoder.limit = 0;
            this.ba = new BufferAccessor();
        }

        @Override // java.io.InputStream
        public int available() {
            return this.ba.getLim() - this.ba.getPos();
        }

        protected void compactAndFill(byte[] bArr, int i, int i2, int i3) {
            System.arraycopy(bArr, i, bArr, i2, i3);
            this.ba.setPos(i2);
            this.ba.setLimit(tryReadRaw(bArr, i2 + i3, bArr.length - i3) + i3);
        }

        protected void detach() {
            this.ba.detach();
        }

        abstract boolean isEof();

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            int lim = this.ba.getLim();
            int pos = this.ba.getPos();
            byte[] buf = this.ba.getBuf();
            int i3 = lim - pos;
            if (i3 >= i2) {
                System.arraycopy(buf, pos, bArr, i, i2);
                this.ba.setPos(pos + i2);
                return i2;
            }
            System.arraycopy(buf, pos, bArr, i, i3);
            this.ba.setPos(pos + i3);
            int tryReadRaw = i3 + tryReadRaw(bArr, i + i3, i2 - i3);
            if (tryReadRaw == 0) {
                return -1;
            }
            return tryReadRaw;
        }

        protected abstract void readRaw(byte[] bArr, int i, int i2);

        @Override // java.io.InputStream
        public long skip(long j) {
            int lim = this.ba.getLim();
            int pos = this.ba.getPos();
            int i = lim - pos;
            if (i <= j) {
                this.ba.setPos(lim);
                return trySkipBytes(j - i) + i;
            }
            this.ba.setPos((int) (pos + j));
            return j;
        }

        protected abstract void skipSourceBytes(long j);

        protected abstract int tryReadRaw(byte[] bArr, int i, int i2);

        protected abstract long trySkipBytes(long j);
    }

    /* loaded from: classes.dex */
    public class InputStreamByteSource extends ByteSource {
        private InputStream in;
        protected boolean isEof;

        private InputStreamByteSource(InputStream inputStream) {
            this.isEof = false;
            this.in = inputStream;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.in.close();
        }

        @Override // com.flurry.org.apache.avro.io.BinaryDecoder.ByteSource
        public boolean isEof() {
            return this.isEof;
        }

        @Override // java.io.InputStream
        public int read() {
            if (this.ba.getLim() - this.ba.getPos() == 0) {
                return this.in.read();
            }
            int pos = this.ba.getPos();
            int i = this.ba.getBuf()[pos] & Constants.UNKNOWN;
            this.ba.setPos(pos + 1);
            return i;
        }

        @Override // com.flurry.org.apache.avro.io.BinaryDecoder.ByteSource
        protected void readRaw(byte[] bArr, int i, int i2) {
            while (i2 > 0) {
                int read = this.in.read(bArr, i, i2);
                if (read < 0) {
                    this.isEof = true;
                    throw new EOFException();
                } else {
                    i2 -= read;
                    i += read;
                }
            }
        }

        @Override // com.flurry.org.apache.avro.io.BinaryDecoder.ByteSource
        protected void skipSourceBytes(long j) {
            boolean z = false;
            while (j > 0) {
                long skip = this.in.skip(j);
                if (skip > 0) {
                    j -= skip;
                } else if (skip != 0) {
                    this.isEof = true;
                    throw new EOFException();
                } else if (z) {
                    this.isEof = true;
                    throw new EOFException();
                } else {
                    z = true;
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x000c, code lost:
            r3.isEof = true;
         */
        @Override // com.flurry.org.apache.avro.io.BinaryDecoder.ByteSource
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        protected int tryReadRaw(byte[] r4, int r5, int r6) {
            /*
                r3 = this;
                r2 = 1
                r0 = r6
            L2:
                if (r0 <= 0) goto Lf
                java.io.InputStream r1 = r3.in     // Catch: java.io.EOFException -> L15
                int r1 = r1.read(r4, r5, r0)     // Catch: java.io.EOFException -> L15
                if (r1 >= 0) goto L12
                r1 = 1
                r3.isEof = r1     // Catch: java.io.EOFException -> L15
            Lf:
                int r0 = r6 - r0
                return r0
            L12:
                int r0 = r0 - r1
                int r5 = r5 + r1
                goto L2
            L15:
                r1 = move-exception
                r3.isEof = r2
                goto Lf
            */
            throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.apache.avro.io.BinaryDecoder.InputStreamByteSource.tryReadRaw(byte[], int, int):int");
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x0028 -> B:41:0x001e). Please submit an issue!!! */
        @Override // com.flurry.org.apache.avro.io.BinaryDecoder.ByteSource
        protected long trySkipBytes(long j) {
            boolean z = false;
            long j2 = j;
            while (true) {
                if (j2 <= 0) {
                    break;
                }
                try {
                    long skip = this.in.skip(j);
                    if (skip <= 0) {
                        if (skip != 0) {
                            this.isEof = true;
                            break;
                        } else if (z) {
                            this.isEof = true;
                            break;
                        } else {
                            z = true;
                        }
                    } else {
                        j2 -= skip;
                    }
                } catch (EOFException e) {
                    this.isEof = true;
                }
            }
            return j - j2;
        }
    }

    public BinaryDecoder() {
    }

    public BinaryDecoder(InputStream inputStream, int i) {
        configure(inputStream, i);
    }

    public BinaryDecoder(byte[] bArr, int i, int i2) {
        configure(bArr, i, i2);
    }

    private void configureSource(int i, ByteSource byteSource) {
        if (this.source != null) {
            this.source.detach();
        }
        byteSource.attach(i, this);
        this.source = byteSource;
    }

    private long doSkipItems() {
        int readInt = readInt();
        while (true) {
            long j = readInt;
            if (j >= 0) {
                return j;
            }
            doSkipBytes(readLong());
            readInt = readInt();
        }
    }

    private void ensureBounds(int i) {
        int i2 = this.limit - this.pos;
        if (i2 < i) {
            this.source.compactAndFill(this.buf, this.pos, this.minPos, i2);
        }
    }

    private long innerLongDecode(long j) {
        int i;
        long j2;
        int i2 = this.buf[this.pos] & Constants.UNKNOWN;
        long j3 = ((i2 & 127) << 28) ^ j;
        if (i2 > 127) {
            i = 2;
            int i3 = this.buf[1 + this.pos] & Constants.UNKNOWN;
            j2 = j3 ^ ((i3 & 127) << 35);
            if (i3 > 127) {
                i = 3;
                int i4 = this.buf[this.pos + 2] & Constants.UNKNOWN;
                j2 ^= (i4 & 127) << 42;
                if (i4 > 127) {
                    i = 4;
                    int i5 = this.buf[this.pos + 3] & Constants.UNKNOWN;
                    j2 ^= (i5 & 127) << 49;
                    if (i5 > 127) {
                        i = 5;
                        int i6 = this.buf[this.pos + 4] & Constants.UNKNOWN;
                        j2 ^= (i6 & 127) << 56;
                        if (i6 > 127) {
                            i = 6;
                            int i7 = this.buf[this.pos + 5] & Constants.UNKNOWN;
                            j2 ^= (i7 & 127) << 63;
                            if (i7 > 127) {
                                throw new IOException("Invalid long encoding");
                            }
                        }
                    }
                }
            }
        } else {
            i = 1;
            j2 = j3;
        }
        this.pos = i + this.pos;
        return j2;
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long arrayNext() {
        return doReadItemCount();
    }

    public BinaryDecoder configure(InputStream inputStream, int i) {
        configureSource(i, new InputStreamByteSource(inputStream));
        return this;
    }

    public BinaryDecoder configure(byte[] bArr, int i, int i2) {
        configureSource(ByteBufferOutputStream.BUFFER_SIZE, new ByteArrayByteSource(bArr, i, i2));
        return this;
    }

    protected void doReadBytes(byte[] bArr, int i, int i2) {
        int i3 = this.limit - this.pos;
        if (i2 <= i3) {
            System.arraycopy(this.buf, this.pos, bArr, i, i2);
            this.pos += i2;
            return;
        }
        System.arraycopy(this.buf, this.pos, bArr, i, i3);
        this.pos = this.limit;
        this.source.readRaw(bArr, i + i3, i2 - i3);
    }

    protected long doReadItemCount() {
        long readLong = readLong();
        if (readLong < 0) {
            readLong();
            return -readLong;
        }
        return readLong;
    }

    protected void doSkipBytes(long j) {
        int i = this.limit - this.pos;
        if (j <= i) {
            this.pos = (int) (this.pos + j);
            return;
        }
        this.pos = 0;
        this.limit = 0;
        this.source.skipSourceBytes(j - i);
    }

    public BufferAccessor getBufferAccessor() {
        return new BufferAccessor();
    }

    public InputStream inputStream() {
        return this.source;
    }

    public boolean isEnd() {
        if (this.limit - this.pos > 0) {
            return false;
        }
        if (this.source.isEof()) {
            return true;
        }
        int tryReadRaw = this.source.tryReadRaw(this.buf, 0, this.buf.length);
        this.pos = 0;
        this.limit = tryReadRaw;
        return tryReadRaw == 0;
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long mapNext() {
        return doReadItemCount();
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long readArrayStart() {
        return doReadItemCount();
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public boolean readBoolean() {
        if (this.limit == this.pos) {
            this.limit = this.source.tryReadRaw(this.buf, 0, this.buf.length);
            this.pos = 0;
            if (this.limit == 0) {
                throw new EOFException();
            }
        }
        byte[] bArr = this.buf;
        int i = this.pos;
        this.pos = i + 1;
        return (bArr[i] & Constants.UNKNOWN) == 1;
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public ByteBuffer readBytes(ByteBuffer byteBuffer) {
        int readInt = readInt();
        if (byteBuffer == null || readInt > byteBuffer.capacity()) {
            byteBuffer = ByteBuffer.allocate(readInt);
        } else {
            byteBuffer.clear();
        }
        doReadBytes(byteBuffer.array(), byteBuffer.position(), readInt);
        byteBuffer.limit(readInt);
        return byteBuffer;
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public double readDouble() {
        ensureBounds(8);
        int i = ((this.buf[1 + this.pos] & Constants.UNKNOWN) << 8) | (this.buf[this.pos] & Constants.UNKNOWN) | ((this.buf[this.pos + 2] & Constants.UNKNOWN) << 16) | ((this.buf[this.pos + 3] & Constants.UNKNOWN) << 24);
        int i2 = (this.buf[this.pos + 4] & Constants.UNKNOWN) | ((this.buf[this.pos + 5] & Constants.UNKNOWN) << 8) | ((this.buf[this.pos + 6] & Constants.UNKNOWN) << 16) | ((this.buf[this.pos + 7] & Constants.UNKNOWN) << 24);
        if (this.pos + 8 > this.limit) {
            throw new EOFException();
        }
        this.pos += 8;
        return Double.longBitsToDouble((i2 << 32) | (i & 4294967295L));
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public int readEnum() {
        return readInt();
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public void readFixed(byte[] bArr, int i, int i2) {
        doReadBytes(bArr, i, i2);
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public float readFloat() {
        ensureBounds(4);
        int i = ((this.buf[1 + this.pos] & Constants.UNKNOWN) << 8) | (this.buf[this.pos] & Constants.UNKNOWN) | ((this.buf[this.pos + 2] & Constants.UNKNOWN) << 16) | ((this.buf[this.pos + 3] & Constants.UNKNOWN) << 24);
        if (this.pos + 4 > this.limit) {
            throw new EOFException();
        }
        this.pos += 4;
        return Float.intBitsToFloat(i);
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public int readIndex() {
        return readInt();
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public int readInt() {
        int i = 5;
        ensureBounds(5);
        int i2 = this.buf[this.pos] & Constants.UNKNOWN;
        int i3 = i2 & 127;
        if (i2 > 127) {
            int i4 = 2;
            int i5 = this.buf[1 + this.pos] & Constants.UNKNOWN;
            i3 ^= (i5 & 127) << 7;
            if (i5 > 127) {
                i4 = 3;
                int i6 = this.buf[this.pos + 2] & Constants.UNKNOWN;
                i3 ^= (i6 & 127) << 14;
                if (i6 > 127) {
                    i4 = 4;
                    int i7 = this.buf[this.pos + 3] & Constants.UNKNOWN;
                    i3 ^= (i7 & 127) << 21;
                    if (i7 > 127) {
                        int i8 = this.buf[this.pos + 4] & Constants.UNKNOWN;
                        i3 ^= (i8 & 127) << 28;
                        if (i8 > 127) {
                            throw new IOException("Invalid int encoding");
                        }
                    }
                }
            }
            i = i4;
        } else {
            i = 1;
        }
        this.pos = i + this.pos;
        if (this.pos > this.limit) {
            throw new EOFException();
        }
        return (-(i3 & 1)) ^ (i3 >>> 1);
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long readLong() {
        long j;
        ensureBounds(10);
        byte[] bArr = this.buf;
        int i = this.pos;
        this.pos = i + 1;
        int i2 = bArr[i] & Constants.UNKNOWN;
        int i3 = i2 & 127;
        if (i2 > 127) {
            byte[] bArr2 = this.buf;
            int i4 = this.pos;
            this.pos = i4 + 1;
            int i5 = bArr2[i4] & Constants.UNKNOWN;
            int i6 = i3 ^ ((i5 & 127) << 7);
            if (i5 > 127) {
                byte[] bArr3 = this.buf;
                int i7 = this.pos;
                this.pos = i7 + 1;
                int i8 = bArr3[i7] & Constants.UNKNOWN;
                int i9 = i6 ^ ((i8 & 127) << 14);
                if (i8 > 127) {
                    byte[] bArr4 = this.buf;
                    int i10 = this.pos;
                    this.pos = i10 + 1;
                    int i11 = bArr4[i10] & Constants.UNKNOWN;
                    int i12 = i9 ^ ((i11 & 127) << 21);
                    j = i11 > 127 ? innerLongDecode(i12) : i12;
                } else {
                    j = i9;
                }
            } else {
                j = i6;
            }
        } else {
            j = i3;
        }
        if (this.pos > this.limit) {
            throw new EOFException();
        }
        return (-(j & 1)) ^ (j >>> 1);
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long readMapStart() {
        return doReadItemCount();
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public void readNull() {
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public Utf8 readString(Utf8 utf8) {
        int readInt = readInt();
        if (utf8 == null) {
            utf8 = new Utf8();
        }
        utf8.setByteLength(readInt);
        if (readInt != 0) {
            doReadBytes(utf8.getBytes(), 0, readInt);
        }
        return utf8;
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public String readString() {
        return readString(this.scratchUtf8).toString();
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long skipArray() {
        return doSkipItems();
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public void skipBytes() {
        doSkipBytes(readInt());
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public void skipFixed(int i) {
        doSkipBytes(i);
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long skipMap() {
        return doSkipItems();
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public void skipString() {
        doSkipBytes(readInt());
    }
}
