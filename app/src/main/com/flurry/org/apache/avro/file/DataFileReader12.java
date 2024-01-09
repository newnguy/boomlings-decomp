package com.flurry.org.apache.avro.file;

import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.file.DataFileReader;
import com.flurry.org.apache.avro.io.BinaryDecoder;
import com.flurry.org.apache.avro.io.DatumReader;
import com.flurry.org.apache.avro.io.DecoderFactory;
import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class DataFileReader12 implements FileReader, Closeable {
    private static final String CODEC = "codec";
    private static final String COUNT = "count";
    private static final long FOOTER_BLOCK = -1;
    static final byte[] MAGIC = {79, 98, 106, 0};
    private static final String NULL_CODEC = "null";
    private static final String SCHEMA = "schema";
    private static final String SYNC = "sync";
    private static final int SYNC_INTERVAL = 16000;
    private static final int SYNC_SIZE = 16;
    private static final byte VERSION = 0;
    private long blockCount;
    private long blockStart;
    private long count;
    private DataFileReader.SeekableInputStream in;
    private Object peek;
    private DatumReader reader;
    private Schema schema;
    private byte[] sync;
    private BinaryDecoder vin;
    private Map meta = new HashMap();
    private byte[] syncBuffer = new byte[16];

    public DataFileReader12(SeekableInput seekableInput, DatumReader datumReader) {
        String metaString;
        this.sync = new byte[16];
        this.in = new DataFileReader.SeekableInputStream(seekableInput);
        byte[] bArr = new byte[4];
        this.in.read(bArr);
        if (!Arrays.equals(MAGIC, bArr)) {
            throw new IOException("Not a data file.");
        }
        long length = this.in.length();
        this.in.seek(length - 4);
        seek(length - ((((this.in.read() << 24) + (this.in.read() << 16)) + (this.in.read() << 8)) + this.in.read()));
        long readMapStart = this.vin.readMapStart();
        if (readMapStart <= 0) {
            this.sync = getMeta(SYNC);
            this.count = getMetaLong(COUNT);
            metaString = getMetaString(CODEC);
            if (metaString == null) {
            }
            this.schema = Schema.parse(getMetaString(SCHEMA));
            this.reader = datumReader;
            datumReader.setSchema(this.schema);
            seek(MAGIC.length);
        }
        do {
            for (long j = 0; j < readMapStart; j++) {
                String utf8 = this.vin.readString(null).toString();
                ByteBuffer readBytes = this.vin.readBytes(null);
                byte[] bArr2 = new byte[readBytes.remaining()];
                readBytes.get(bArr2);
                this.meta.put(utf8, bArr2);
            }
            readMapStart = this.vin.mapNext();
        } while (readMapStart != 0);
        this.sync = getMeta(SYNC);
        this.count = getMetaLong(COUNT);
        metaString = getMetaString(CODEC);
        if (metaString == null && !metaString.equals("null")) {
            throw new IOException("Unknown codec: " + metaString);
        }
        this.schema = Schema.parse(getMetaString(SCHEMA));
        this.reader = datumReader;
        datumReader.setSchema(this.schema);
        seek(MAGIC.length);
    }

    private void skipSync() {
        this.vin.readFixed(this.syncBuffer);
        if (!Arrays.equals(this.syncBuffer, this.sync)) {
            throw new IOException("Invalid sync!");
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.in.close();
    }

    public synchronized byte[] getMeta(String str) {
        return (byte[]) this.meta.get(str);
    }

    public synchronized long getMetaLong(String str) {
        return Long.parseLong(getMetaString(str));
    }

    public synchronized String getMetaString(String str) {
        String str2;
        byte[] meta = getMeta(str);
        if (meta == null) {
            str2 = null;
        } else {
            try {
                str2 = new String(meta, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return str2;
    }

    @Override // com.flurry.org.apache.avro.file.FileReader
    public Schema getSchema() {
        return this.schema;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.peek == null && this.blockCount == 0) {
            this.peek = next();
            return this.peek != null;
        }
        return true;
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return this;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (this.peek != null) {
            Object obj = this.peek;
            this.peek = null;
            return obj;
        }
        try {
            return next(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0043, code lost:
        r4.blockCount--;
        r0 = r4.reader.read(r5, r4.vin);
     */
    @Override // com.flurry.org.apache.avro.file.FileReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized java.lang.Object next(java.lang.Object r5) {
        /*
            r4 = this;
            monitor-enter(r4)
        L1:
            long r0 = r4.blockCount     // Catch: java.lang.Throwable -> L40
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L43
            com.flurry.org.apache.avro.file.DataFileReader$SeekableInputStream r0 = r4.in     // Catch: java.lang.Throwable -> L40
            long r0 = r0.tell()     // Catch: java.lang.Throwable -> L40
            com.flurry.org.apache.avro.file.DataFileReader$SeekableInputStream r2 = r4.in     // Catch: java.lang.Throwable -> L40
            long r2 = r2.length()     // Catch: java.lang.Throwable -> L40
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L1c
            r0 = 0
        L1a:
            monitor-exit(r4)
            return r0
        L1c:
            r4.skipSync()     // Catch: java.lang.Throwable -> L40
            com.flurry.org.apache.avro.io.BinaryDecoder r0 = r4.vin     // Catch: java.lang.Throwable -> L40
            long r0 = r0.readLong()     // Catch: java.lang.Throwable -> L40
            r4.blockCount = r0     // Catch: java.lang.Throwable -> L40
            long r0 = r4.blockCount     // Catch: java.lang.Throwable -> L40
            r2 = -1
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L1
            com.flurry.org.apache.avro.io.BinaryDecoder r0 = r4.vin     // Catch: java.lang.Throwable -> L40
            long r0 = r0.readLong()     // Catch: java.lang.Throwable -> L40
            com.flurry.org.apache.avro.file.DataFileReader$SeekableInputStream r2 = r4.in     // Catch: java.lang.Throwable -> L40
            long r2 = r2.tell()     // Catch: java.lang.Throwable -> L40
            long r0 = r0 + r2
            r4.seek(r0)     // Catch: java.lang.Throwable -> L40
            goto L1
        L40:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        L43:
            long r0 = r4.blockCount     // Catch: java.lang.Throwable -> L40
            r2 = 1
            long r0 = r0 - r2
            r4.blockCount = r0     // Catch: java.lang.Throwable -> L40
            com.flurry.org.apache.avro.io.DatumReader r0 = r4.reader     // Catch: java.lang.Throwable -> L40
            com.flurry.org.apache.avro.io.BinaryDecoder r1 = r4.vin     // Catch: java.lang.Throwable -> L40
            java.lang.Object r0 = r0.read(r5, r1)     // Catch: java.lang.Throwable -> L40
            goto L1a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.apache.avro.file.DataFileReader12.next(java.lang.Object):java.lang.Object");
    }

    @Override // com.flurry.org.apache.avro.file.FileReader
    public boolean pastSync(long j) {
        return this.blockStart >= 16 + j || this.blockStart >= this.in.length();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public synchronized void seek(long j) {
        this.in.seek(j);
        this.blockCount = 0L;
        this.blockStart = j;
        this.vin = DecoderFactory.get().binaryDecoder(this.in, this.vin);
    }

    @Override // com.flurry.org.apache.avro.file.FileReader
    public synchronized void sync(long j) {
        if (this.in.tell() + 16 < this.in.length()) {
            this.in.seek(j);
            this.vin.readFixed(this.syncBuffer);
            int i = 0;
            while (true) {
                if (this.in.tell() >= this.in.length()) {
                    seek(this.in.length());
                    break;
                }
                int i2 = 0;
                while (i2 < this.sync.length && this.sync[i2] == this.syncBuffer[(i + i2) % this.sync.length]) {
                    i2++;
                }
                if (i2 == this.sync.length) {
                    seek(this.in.tell() - 16);
                    break;
                } else {
                    this.syncBuffer[i % this.sync.length] = (byte) this.in.read();
                    i++;
                }
            }
        } else {
            seek(this.in.length());
        }
    }

    @Override // com.flurry.org.apache.avro.file.FileReader
    public long tell() {
        return this.in.tell();
    }
}
