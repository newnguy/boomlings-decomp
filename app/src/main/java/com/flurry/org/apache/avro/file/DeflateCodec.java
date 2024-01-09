package com.flurry.org.apache.avro.file;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

/* loaded from: classes.dex */
class DeflateCodec extends Codec {
    private int compressionLevel;
    private Deflater deflater;
    private Inflater inflater;
    private boolean nowrap = true;
    private ByteArrayOutputStream outputBuffer;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Option extends CodecFactory {
        private int compressionLevel;

        public Option(int i) {
            this.compressionLevel = i;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.flurry.org.apache.avro.file.CodecFactory
        public Codec createInstance() {
            return new DeflateCodec(this.compressionLevel);
        }
    }

    public DeflateCodec(int i) {
        this.compressionLevel = i;
    }

    private Deflater getDeflater() {
        if (this.deflater == null) {
            this.deflater = new Deflater(this.compressionLevel, this.nowrap);
        }
        this.deflater.reset();
        return this.deflater;
    }

    private Inflater getInflater() {
        if (this.inflater == null) {
            this.inflater = new Inflater(this.nowrap);
        }
        this.inflater.reset();
        return this.inflater;
    }

    private ByteArrayOutputStream getOutputBuffer(int i) {
        if (this.outputBuffer == null) {
            this.outputBuffer = new ByteArrayOutputStream(i);
        }
        this.outputBuffer.reset();
        return this.outputBuffer;
    }

    private void writeAndClose(ByteBuffer byteBuffer, OutputStream outputStream) {
        try {
            outputStream.write(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
        } finally {
            outputStream.close();
        }
    }

    @Override // com.flurry.org.apache.avro.file.Codec
    public ByteBuffer compress(ByteBuffer byteBuffer) {
        ByteArrayOutputStream outputBuffer = getOutputBuffer(byteBuffer.remaining());
        writeAndClose(byteBuffer, new DeflaterOutputStream(outputBuffer, getDeflater()));
        return ByteBuffer.wrap(outputBuffer.toByteArray());
    }

    @Override // com.flurry.org.apache.avro.file.Codec
    public ByteBuffer decompress(ByteBuffer byteBuffer) {
        ByteArrayOutputStream outputBuffer = getOutputBuffer(byteBuffer.remaining());
        writeAndClose(byteBuffer, new InflaterOutputStream(outputBuffer, getInflater()));
        return ByteBuffer.wrap(outputBuffer.toByteArray());
    }

    @Override // com.flurry.org.apache.avro.file.Codec
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return getClass() == obj.getClass() && this.nowrap == ((DeflateCodec) obj).nowrap;
    }

    @Override // com.flurry.org.apache.avro.file.Codec
    public String getName() {
        return DataFileConstants.DEFLATE_CODEC;
    }

    @Override // com.flurry.org.apache.avro.file.Codec
    public int hashCode() {
        return this.nowrap ? 0 : 1;
    }

    @Override // com.flurry.org.apache.avro.file.Codec
    public String toString() {
        return getName() + "-" + this.compressionLevel;
    }
}
