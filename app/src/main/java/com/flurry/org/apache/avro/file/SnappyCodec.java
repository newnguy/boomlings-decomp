package com.flurry.org.apache.avro.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.zip.CRC32;
import org.xerial.snappy.Snappy;

/* loaded from: classes.dex */
class SnappyCodec extends Codec {
    private CRC32 crc32;

    /* loaded from: classes.dex */
    public class Option extends CodecFactory {
        @Override // com.flurry.org.apache.avro.file.CodecFactory
        public Codec createInstance() {
            return new SnappyCodec();
        }
    }

    private SnappyCodec() {
        this.crc32 = new CRC32();
    }

    @Override // com.flurry.org.apache.avro.file.Codec
    public ByteBuffer compress(ByteBuffer byteBuffer) {
        ByteBuffer allocate = ByteBuffer.allocate(Snappy.maxCompressedLength(byteBuffer.remaining()) + 4);
        int compress = Snappy.compress(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining(), allocate.array(), 0);
        this.crc32.reset();
        this.crc32.update(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining());
        allocate.putInt(compress, (int) this.crc32.getValue());
        allocate.limit(compress + 4);
        return allocate;
    }

    @Override // com.flurry.org.apache.avro.file.Codec
    public ByteBuffer decompress(ByteBuffer byteBuffer) {
        ByteBuffer allocate = ByteBuffer.allocate(Snappy.uncompressedLength(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining() - 4));
        int uncompress = Snappy.uncompress(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining() - 4, allocate.array(), 0);
        allocate.limit(uncompress);
        this.crc32.reset();
        this.crc32.update(allocate.array(), 0, uncompress);
        if (byteBuffer.getInt(byteBuffer.limit() - 4) != ((int) this.crc32.getValue())) {
            throw new IOException("Checksum failure");
        }
        return allocate;
    }

    @Override // com.flurry.org.apache.avro.file.Codec
    public boolean equals(Object obj) {
        return this == obj || getClass() == obj.getClass();
    }

    @Override // com.flurry.org.apache.avro.file.Codec
    public String getName() {
        return DataFileConstants.SNAPPY_CODEC;
    }

    @Override // com.flurry.org.apache.avro.file.Codec
    public int hashCode() {
        return getName().hashCode();
    }
}
