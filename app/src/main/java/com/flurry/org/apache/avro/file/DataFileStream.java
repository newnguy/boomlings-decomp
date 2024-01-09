package com.flurry.org.apache.avro.file;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.BinaryDecoder;
import com.flurry.org.apache.avro.io.BinaryEncoder;
import com.flurry.org.apache.avro.io.DatumReader;
import com.flurry.org.apache.avro.io.DecoderFactory;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public class DataFileStream implements Closeable, Iterable, Iterator {
    private boolean availableBlock;
    private DataBlock block;
    ByteBuffer blockBuffer;
    long blockCount;
    long blockRemaining;
    private long blockSize;
    private Codec codec;
    BinaryDecoder datumIn;
    private Header header;
    private DatumReader reader;
    byte[] syncBuffer;
    BinaryDecoder vin;

    /* loaded from: classes.dex */
    public class DataBlock {
        private int blockSize;
        private byte[] data;
        private long numEntries;
        private int offset;

        private DataBlock(long j, int i) {
            this.offset = 0;
            this.data = new byte[i];
            this.numEntries = j;
            this.blockSize = i;
        }

        public DataBlock(ByteBuffer byteBuffer, long j) {
            this.offset = 0;
            this.data = byteBuffer.array();
            this.blockSize = byteBuffer.remaining();
            this.offset = byteBuffer.arrayOffset() + byteBuffer.position();
            this.numEntries = j;
        }

        public void compressUsing(Codec codec) {
            ByteBuffer compress = codec.compress(getAsByteBuffer());
            this.data = compress.array();
            this.blockSize = compress.remaining();
        }

        public void decompressUsing(Codec codec) {
            ByteBuffer decompress = codec.decompress(getAsByteBuffer());
            this.data = decompress.array();
            this.blockSize = decompress.remaining();
        }

        ByteBuffer getAsByteBuffer() {
            return ByteBuffer.wrap(this.data, this.offset, this.blockSize);
        }

        int getBlockSize() {
            return this.blockSize;
        }

        byte[] getData() {
            return this.data;
        }

        long getNumEntries() {
            return this.numEntries;
        }

        public void writeBlockTo(BinaryEncoder binaryEncoder, byte[] bArr) {
            binaryEncoder.writeLong(this.numEntries);
            binaryEncoder.writeLong(this.blockSize);
            binaryEncoder.writeFixed(this.data, this.offset, this.blockSize);
            binaryEncoder.writeFixed(bArr);
            binaryEncoder.flush();
        }
    }

    /* loaded from: classes.dex */
    public final class Header {
        Map meta;
        private transient List metaKeyList;
        Schema schema;
        byte[] sync;

        private Header() {
            this.meta = new HashMap();
            this.metaKeyList = new ArrayList();
            this.sync = new byte[16];
        }
    }

    public DataFileStream(DatumReader datumReader) {
        this.availableBlock = false;
        this.datumIn = null;
        this.syncBuffer = new byte[16];
        this.block = null;
        this.reader = datumReader;
    }

    public DataFileStream(InputStream inputStream, DatumReader datumReader) {
        this.availableBlock = false;
        this.datumIn = null;
        this.syncBuffer = new byte[16];
        this.block = null;
        this.reader = datumReader;
        initialize(inputStream);
    }

    protected void blockFinished() {
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.vin.inputStream().close();
    }

    public long getBlockCount() {
        return this.blockCount;
    }

    public Header getHeader() {
        return this.header;
    }

    public byte[] getMeta(String str) {
        return (byte[]) this.header.meta.get(str);
    }

    public List getMetaKeys() {
        return this.header.metaKeyList;
    }

    public long getMetaLong(String str) {
        return Long.parseLong(getMetaString(str));
    }

    public String getMetaString(String str) {
        byte[] meta = getMeta(str);
        if (meta == null) {
            return null;
        }
        try {
            return new String(meta, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public Schema getSchema() {
        return this.header.schema;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        try {
            if (this.blockRemaining == 0) {
                if (this.datumIn != null && !this.datumIn.isEnd()) {
                    throw new IOException("Block read partially, the data may be corrupt");
                }
                if (hasNextBlock()) {
                    this.block = nextRawBlock(this.block);
                    this.block.decompressUsing(this.codec);
                    this.blockBuffer = this.block.getAsByteBuffer();
                    this.datumIn = DecoderFactory.get().binaryDecoder(this.blockBuffer.array(), this.blockBuffer.arrayOffset() + this.blockBuffer.position(), this.blockBuffer.remaining(), this.datumIn);
                }
            }
            return this.blockRemaining != 0;
        } catch (EOFException e) {
            return false;
        } catch (IOException e2) {
            throw new AvroRuntimeException(e2);
        }
    }

    public boolean hasNextBlock() {
        try {
            if (this.availableBlock) {
                return true;
            }
            if (this.vin.isEnd()) {
                return false;
            }
            this.blockRemaining = this.vin.readLong();
            this.blockSize = this.vin.readLong();
            if (this.blockSize > 2147483647L || this.blockSize < 0) {
                throw new IOException("Block size invalid or too large for this implementation: " + this.blockSize);
            }
            this.blockCount = this.blockRemaining;
            this.availableBlock = true;
            return true;
        } catch (EOFException e) {
            return false;
        } catch (IOException e2) {
            throw new AvroRuntimeException(e2);
        }
    }

    public void initialize(InputStream inputStream) {
        this.header = new Header();
        this.vin = DecoderFactory.get().binaryDecoder(inputStream, this.vin);
        byte[] bArr = new byte[DataFileConstants.MAGIC.length];
        try {
            this.vin.readFixed(bArr);
            if (!Arrays.equals(DataFileConstants.MAGIC, bArr)) {
                throw new IOException("Not a data file.");
            }
            long readMapStart = this.vin.readMapStart();
            if (readMapStart <= 0) {
                this.vin.readFixed(this.header.sync);
                this.header.metaKeyList = Collections.unmodifiableList(this.header.metaKeyList);
                this.header.schema = Schema.parse(getMetaString(DataFileConstants.SCHEMA), false);
                this.codec = resolveCodec();
                this.reader.setSchema(this.header.schema);
            }
            do {
                for (long j = 0; j < readMapStart; j++) {
                    String utf8 = this.vin.readString(null).toString();
                    ByteBuffer readBytes = this.vin.readBytes(null);
                    byte[] bArr2 = new byte[readBytes.remaining()];
                    readBytes.get(bArr2);
                    this.header.meta.put(utf8, bArr2);
                    this.header.metaKeyList.add(utf8);
                }
                readMapStart = this.vin.mapNext();
            } while (readMapStart != 0);
            this.vin.readFixed(this.header.sync);
            this.header.metaKeyList = Collections.unmodifiableList(this.header.metaKeyList);
            this.header.schema = Schema.parse(getMetaString(DataFileConstants.SCHEMA), false);
            this.codec = resolveCodec();
            this.reader.setSchema(this.header.schema);
        } catch (IOException e) {
            throw new IOException("Not a data file.");
        }
    }

    public void initialize(InputStream inputStream, Header header) {
        this.header = header;
        this.codec = resolveCodec();
        this.reader.setSchema(header.schema);
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return this;
    }

    @Override // java.util.Iterator
    public Object next() {
        try {
            return next(null);
        } catch (IOException e) {
            throw new AvroRuntimeException(e);
        }
    }

    public Object next(Object obj) {
        if (hasNext()) {
            Object read = this.reader.read(obj, this.datumIn);
            long j = this.blockRemaining - 1;
            this.blockRemaining = j;
            if (0 == j) {
                blockFinished();
            }
            return read;
        }
        throw new NoSuchElementException();
    }

    public ByteBuffer nextBlock() {
        if (hasNext()) {
            if (this.blockRemaining != this.blockCount) {
                throw new IllegalStateException("Not at block start.");
            }
            this.blockRemaining = 0L;
            this.datumIn = null;
            return this.blockBuffer;
        }
        throw new NoSuchElementException();
    }

    public DataBlock nextRawBlock(DataBlock dataBlock) {
        if (hasNextBlock()) {
            if (dataBlock == null || dataBlock.data.length < ((int) this.blockSize)) {
                dataBlock = new DataBlock(this.blockRemaining, (int) this.blockSize);
            } else {
                dataBlock.numEntries = this.blockRemaining;
                dataBlock.blockSize = (int) this.blockSize;
            }
            this.vin.readFixed(dataBlock.data, 0, dataBlock.blockSize);
            this.vin.readFixed(this.syncBuffer);
            if (Arrays.equals(this.syncBuffer, this.header.sync)) {
                this.availableBlock = false;
                return dataBlock;
            }
            throw new IOException("Invalid sync!");
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public Codec resolveCodec() {
        String metaString = getMetaString(DataFileConstants.CODEC);
        return metaString != null ? CodecFactory.fromString(metaString).createInstance() : CodecFactory.nullCodec().createInstance();
    }
}
