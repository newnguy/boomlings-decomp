package com.flurry.org.apache.avro.file;

import com.flurry.android.Constants;
import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.file.DataFileStream;
import com.flurry.org.apache.avro.generic.GenericDatumReader;
import com.flurry.org.apache.avro.io.BinaryEncoder;
import com.flurry.org.apache.avro.io.DatumWriter;
import com.flurry.org.apache.avro.io.EncoderFactory;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.rmi.server.UID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class DataFileWriter implements Closeable, Flushable {
    private long blockCount;
    private BinaryEncoder bufOut;
    private NonCopyingByteArrayOutputStream buffer;
    private Codec codec;
    private DatumWriter dout;
    private boolean isOpen;
    private BufferedFileOutputStream out;
    private Schema schema;
    private byte[] sync;
    private BinaryEncoder vout;
    private final Map meta = new HashMap();
    private int syncInterval = DataFileConstants.DEFAULT_SYNC_INTERVAL;

    /* loaded from: classes.dex */
    public class AppendWriteException extends RuntimeException {
        public AppendWriteException(Exception exc) {
            super(exc);
        }
    }

    /* loaded from: classes.dex */
    public class BufferedFileOutputStream extends BufferedOutputStream {
        private long position;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class PositionFilter extends FilterOutputStream {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public PositionFilter(OutputStream outputStream) {
                super(outputStream);
                BufferedFileOutputStream.this = r1;
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) {
                this.out.write(bArr, i, i2);
                BufferedFileOutputStream.access$014(BufferedFileOutputStream.this, i2);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BufferedFileOutputStream(OutputStream outputStream) {
            super(null);
            DataFileWriter.this = r2;
            this.out = new PositionFilter(outputStream);
        }

        static /* synthetic */ long access$014(BufferedFileOutputStream bufferedFileOutputStream, long j) {
            long j2 = bufferedFileOutputStream.position + j;
            bufferedFileOutputStream.position = j2;
            return j2;
        }

        public long tell() {
            return this.position + this.count;
        }
    }

    /* loaded from: classes.dex */
    public class NonCopyingByteArrayOutputStream extends ByteArrayOutputStream {
        NonCopyingByteArrayOutputStream(int i) {
            super(i);
        }

        ByteBuffer getByteArrayAsByteBuffer() {
            return ByteBuffer.wrap(this.buf, 0, this.count);
        }
    }

    public DataFileWriter(DatumWriter datumWriter) {
        this.dout = datumWriter;
    }

    private void assertNotOpen() {
        if (this.isOpen) {
            throw new AvroRuntimeException("already open");
        }
    }

    private void assertOpen() {
        if (!this.isOpen) {
            throw new AvroRuntimeException("not open");
        }
    }

    private int bufferInUse() {
        return this.buffer.size() + this.bufOut.bytesBuffered();
    }

    private static byte[] generateSync() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update((new UID() + "@" + System.currentTimeMillis()).getBytes());
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void init(OutputStream outputStream) {
        this.out = new BufferedFileOutputStream(outputStream);
        EncoderFactory encoderFactory = new EncoderFactory();
        this.vout = encoderFactory.binaryEncoder(this.out, null);
        this.dout.setSchema(this.schema);
        this.buffer = new NonCopyingByteArrayOutputStream(Math.min((int) (this.syncInterval * 1.25d), 1073741822));
        this.bufOut = encoderFactory.binaryEncoder(this.buffer, null);
        if (this.codec == null) {
            this.codec = CodecFactory.nullCodec().createInstance();
        }
        this.isOpen = true;
    }

    public static boolean isReservedMeta(String str) {
        return str.startsWith("avro.");
    }

    private void resetBufferTo(int i) {
        this.bufOut.flush();
        byte[] byteArray = this.buffer.toByteArray();
        this.buffer.reset();
        this.buffer.write(byteArray, 0, i);
    }

    private DataFileWriter setMetaInternal(String str, String str2) {
        try {
            return setMetaInternal(str, str2.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private DataFileWriter setMetaInternal(String str, byte[] bArr) {
        assertNotOpen();
        this.meta.put(str, bArr);
        return this;
    }

    private void writeBlock() {
        if (this.blockCount > 0) {
            this.bufOut.flush();
            DataFileStream.DataBlock dataBlock = new DataFileStream.DataBlock(this.buffer.getByteArrayAsByteBuffer(), this.blockCount);
            dataBlock.compressUsing(this.codec);
            dataBlock.writeBlockTo(this.vout, this.sync);
            this.buffer.reset();
            this.blockCount = 0L;
        }
    }

    private void writeIfBlockFull() {
        if (bufferInUse() >= this.syncInterval) {
            writeBlock();
        }
    }

    public void append(Object obj) {
        assertOpen();
        int bufferInUse = bufferInUse();
        try {
            this.dout.write(obj, this.bufOut);
            this.blockCount++;
            writeIfBlockFull();
        } catch (IOException e) {
            resetBufferTo(bufferInUse);
            throw new AppendWriteException(e);
        } catch (RuntimeException e2) {
            resetBufferTo(bufferInUse);
            throw new AppendWriteException(e2);
        }
    }

    public void appendAllFrom(DataFileStream dataFileStream, boolean z) {
        assertOpen();
        if (!this.schema.equals(dataFileStream.getSchema())) {
            throw new IOException("Schema from file " + dataFileStream + " does not match");
        }
        writeBlock();
        Codec resolveCodec = dataFileStream.resolveCodec();
        DataFileStream.DataBlock dataBlock = null;
        if (this.codec.equals(resolveCodec) && !z) {
            while (dataFileStream.hasNextBlock()) {
                dataBlock = dataFileStream.nextRawBlock(dataBlock);
                dataBlock.writeBlockTo(this.vout, this.sync);
            }
            return;
        }
        while (dataFileStream.hasNextBlock()) {
            dataBlock = dataFileStream.nextRawBlock(dataBlock);
            dataBlock.decompressUsing(resolveCodec);
            dataBlock.compressUsing(this.codec);
            dataBlock.writeBlockTo(this.vout, this.sync);
        }
    }

    public void appendEncoded(ByteBuffer byteBuffer) {
        assertOpen();
        int position = byteBuffer.position();
        this.bufOut.writeFixed(byteBuffer.array(), position, byteBuffer.limit() - position);
        this.blockCount++;
        writeIfBlockFull();
    }

    public DataFileWriter appendTo(File file) {
        assertNotOpen();
        if (file.exists()) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, Constants.ALIGN_RIGHT);
            DataFileReader dataFileReader = new DataFileReader(new SeekableFileInput(randomAccessFile.getFD()), new GenericDatumReader());
            this.schema = dataFileReader.getSchema();
            this.sync = dataFileReader.getHeader().sync;
            this.meta.putAll(dataFileReader.getHeader().meta);
            byte[] bArr = (byte[]) this.meta.get(DataFileConstants.CODEC);
            if (bArr != null) {
                this.codec = CodecFactory.fromString(new String(bArr, "UTF-8")).createInstance();
            } else {
                this.codec = CodecFactory.nullCodec().createInstance();
            }
            randomAccessFile.close();
            init(new FileOutputStream(file, true));
            return this;
        }
        throw new FileNotFoundException("Not found: " + file);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        flush();
        this.out.close();
        this.isOpen = false;
    }

    public DataFileWriter create(Schema schema, File file) {
        return create(schema, new FileOutputStream(file));
    }

    public DataFileWriter create(Schema schema, OutputStream outputStream) {
        assertNotOpen();
        this.schema = schema;
        setMetaInternal(DataFileConstants.SCHEMA, schema.toString());
        this.sync = generateSync();
        init(outputStream);
        this.vout.writeFixed(DataFileConstants.MAGIC);
        this.vout.writeMapStart();
        this.vout.setItemCount(this.meta.size());
        for (Map.Entry entry : this.meta.entrySet()) {
            this.vout.startItem();
            this.vout.writeString((String) entry.getKey());
            this.vout.writeBytes((byte[]) entry.getValue());
        }
        this.vout.writeMapEnd();
        this.vout.writeFixed(this.sync);
        this.vout.flush();
        return this;
    }

    @Override // java.io.Flushable
    public void flush() {
        sync();
        this.vout.flush();
    }

    public DataFileWriter setCodec(CodecFactory codecFactory) {
        assertNotOpen();
        this.codec = codecFactory.createInstance();
        setMetaInternal(DataFileConstants.CODEC, this.codec.getName());
        return this;
    }

    public DataFileWriter setMeta(String str, long j) {
        return setMeta(str, Long.toString(j));
    }

    public DataFileWriter setMeta(String str, String str2) {
        try {
            return setMeta(str, str2.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public DataFileWriter setMeta(String str, byte[] bArr) {
        if (isReservedMeta(str)) {
            throw new AvroRuntimeException("Cannot set reserved meta key: " + str);
        }
        return setMetaInternal(str, bArr);
    }

    public DataFileWriter setSyncInterval(int i) {
        if (i < 32 || i > 1073741824) {
            throw new IllegalArgumentException("Invalid syncInterval value: " + i);
        }
        this.syncInterval = i;
        return this;
    }

    public long sync() {
        assertOpen();
        writeBlock();
        return this.out.tell();
    }
}
