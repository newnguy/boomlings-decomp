package com.flurry.org.apache.avro.file;

import com.flurry.android.Constants;
import com.flurry.org.apache.avro.file.DataFileStream;
import com.flurry.org.apache.avro.io.DatumReader;
import com.flurry.org.apache.avro.io.DecoderFactory;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* loaded from: classes.dex */
public class DataFileReader extends DataFileStream implements FileReader {
    private long blockStart;
    private SeekableInputStream sin;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class SeekableInputStream extends InputStream implements SeekableInput {
        private SeekableInput in;
        private final byte[] oneByte = new byte[1];

        /* JADX INFO: Access modifiers changed from: package-private */
        public SeekableInputStream(SeekableInput seekableInput) {
            this.in = seekableInput;
        }

        @Override // java.io.InputStream
        public int available() {
            long length = this.in.length() - this.in.tell();
            if (length > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            return (int) length;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.in.close();
            super.close();
        }

        @Override // com.flurry.org.apache.avro.file.SeekableInput
        public long length() {
            return this.in.length();
        }

        @Override // java.io.InputStream
        public int read() {
            int read = read(this.oneByte, 0, 1);
            return read == 1 ? this.oneByte[0] & Constants.UNKNOWN : read;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr) {
            return this.in.read(bArr, 0, bArr.length);
        }

        @Override // java.io.InputStream, com.flurry.org.apache.avro.file.SeekableInput
        public int read(byte[] bArr, int i, int i2) {
            return this.in.read(bArr, i, i2);
        }

        @Override // com.flurry.org.apache.avro.file.SeekableInput
        public void seek(long j) {
            if (j < 0) {
                throw new IOException("Illegal seek: " + j);
            }
            this.in.seek(j);
        }

        @Override // java.io.InputStream
        public long skip(long j) {
            long tell = this.in.tell();
            long length = this.in.length() - tell;
            if (length > j) {
                this.in.seek(j);
                return this.in.tell() - tell;
            }
            this.in.seek(length);
            return this.in.tell() - tell;
        }

        @Override // com.flurry.org.apache.avro.file.SeekableInput
        public long tell() {
            return this.in.tell();
        }
    }

    public DataFileReader(SeekableInput seekableInput, DatumReader datumReader) {
        super(datumReader);
        this.sin = new SeekableInputStream(seekableInput);
        initialize(this.sin);
        blockFinished();
    }

    protected DataFileReader(SeekableInput seekableInput, DatumReader datumReader, DataFileStream.Header header) {
        super(datumReader);
        this.sin = new SeekableInputStream(seekableInput);
        initialize(this.sin, header);
    }

    public DataFileReader(File file, DatumReader datumReader) {
        this(new SeekableFileInput(file), datumReader);
    }

    public static DataFileReader openReader(SeekableInput seekableInput, DatumReader datumReader, DataFileStream.Header header, boolean z) {
        DataFileReader dataFileReader = new DataFileReader(seekableInput, datumReader, header);
        if (z) {
            dataFileReader.sync(seekableInput.tell());
        } else {
            dataFileReader.seek(seekableInput.tell());
        }
        return dataFileReader;
    }

    public static FileReader openReader(SeekableInput seekableInput, DatumReader datumReader) {
        if (seekableInput.length() < DataFileConstants.MAGIC.length) {
            throw new IOException("Not an Avro data file");
        }
        byte[] bArr = new byte[DataFileConstants.MAGIC.length];
        seekableInput.seek(0L);
        int i = 0;
        while (i < bArr.length) {
            i = seekableInput.read(bArr, i, bArr.length - i);
        }
        seekableInput.seek(0L);
        if (Arrays.equals(DataFileConstants.MAGIC, bArr)) {
            return new DataFileReader(seekableInput, datumReader);
        }
        if (Arrays.equals(DataFileReader12.MAGIC, bArr)) {
            return new DataFileReader12(seekableInput, datumReader);
        }
        throw new IOException("Not an Avro data file");
    }

    public static FileReader openReader(File file, DatumReader datumReader) {
        return openReader(new SeekableFileInput(file), datumReader);
    }

    @Override // com.flurry.org.apache.avro.file.DataFileStream
    protected void blockFinished() {
        this.blockStart = this.sin.tell() - this.vin.inputStream().available();
    }

    @Override // com.flurry.org.apache.avro.file.FileReader
    public boolean pastSync(long j) {
        return this.blockStart >= 16 + j || this.blockStart >= this.sin.length();
    }

    public long previousSync() {
        return this.blockStart;
    }

    public void seek(long j) {
        this.sin.seek(j);
        this.vin = DecoderFactory.get().binaryDecoder(this.sin, this.vin);
        this.datumIn = null;
        this.blockRemaining = 0L;
        this.blockStart = j;
    }

    @Override // com.flurry.org.apache.avro.file.FileReader
    public void sync(long j) {
        seek(j);
        if (j == 0 && getMeta("avro.sync") != null) {
            initialize(this.sin);
            return;
        }
        try {
            InputStream inputStream = this.vin.inputStream();
            this.vin.readFixed(this.syncBuffer);
            int i = 0;
            while (true) {
                int i2 = 0;
                while (i2 < 16 && getHeader().sync[i2] == this.syncBuffer[(i + i2) % 16]) {
                    i2++;
                }
                if (i2 != 16) {
                    int read = inputStream.read();
                    int i3 = i + 1;
                    this.syncBuffer[i % 16] = (byte) read;
                    if (read == -1) {
                        break;
                    }
                    i = i3;
                } else {
                    this.blockStart = i + j + 16;
                    return;
                }
            }
        } catch (EOFException e) {
        }
        this.blockStart = this.sin.tell();
    }

    @Override // com.flurry.org.apache.avro.file.FileReader
    public long tell() {
        return this.sin.tell();
    }
}
