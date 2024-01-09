package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.Schema;
import java.io.InputStream;

/* loaded from: classes.dex */
public class DecoderFactory {
    static final int DEFAULT_BUFFER_SIZE = 8192;
    private static final DecoderFactory DEFAULT_FACTORY = new DefaultDecoderFactory();
    int binaryDecoderBufferSize = 8192;

    /* loaded from: classes.dex */
    class DefaultDecoderFactory extends DecoderFactory {
        private DefaultDecoderFactory() {
        }

        @Override // com.flurry.org.apache.avro.io.DecoderFactory
        public DecoderFactory configureDecoderBufferSize(int i) {
            throw new IllegalArgumentException("This Factory instance is Immutable");
        }
    }

    @Deprecated
    public static DecoderFactory defaultFactory() {
        return get();
    }

    public static DecoderFactory get() {
        return DEFAULT_FACTORY;
    }

    public BinaryDecoder binaryDecoder(InputStream inputStream, BinaryDecoder binaryDecoder) {
        return (binaryDecoder == null || !binaryDecoder.getClass().equals(BinaryDecoder.class)) ? new BinaryDecoder(inputStream, this.binaryDecoderBufferSize) : binaryDecoder.configure(inputStream, this.binaryDecoderBufferSize);
    }

    public BinaryDecoder binaryDecoder(byte[] bArr, int i, int i2, BinaryDecoder binaryDecoder) {
        return (binaryDecoder == null || !binaryDecoder.getClass().equals(BinaryDecoder.class)) ? new BinaryDecoder(bArr, i, i2) : binaryDecoder.configure(bArr, i, i2);
    }

    public BinaryDecoder binaryDecoder(byte[] bArr, BinaryDecoder binaryDecoder) {
        return binaryDecoder(bArr, 0, bArr.length, binaryDecoder);
    }

    public DecoderFactory configureDecoderBufferSize(int i) {
        int i2 = i >= 32 ? i : 32;
        this.binaryDecoderBufferSize = i2 <= 16777216 ? i2 : 16777216;
        return this;
    }

    @Deprecated
    public BinaryDecoder createBinaryDecoder(InputStream inputStream, BinaryDecoder binaryDecoder) {
        return binaryDecoder(inputStream, binaryDecoder);
    }

    @Deprecated
    public BinaryDecoder createBinaryDecoder(byte[] bArr, int i, int i2, BinaryDecoder binaryDecoder) {
        return (binaryDecoder == null || !binaryDecoder.getClass().equals(BinaryDecoder.class)) ? new BinaryDecoder(bArr, i, i2) : binaryDecoder.configure(bArr, i, i2);
    }

    @Deprecated
    public BinaryDecoder createBinaryDecoder(byte[] bArr, BinaryDecoder binaryDecoder) {
        return binaryDecoder(bArr, 0, bArr.length, binaryDecoder);
    }

    public BinaryDecoder directBinaryDecoder(InputStream inputStream, BinaryDecoder binaryDecoder) {
        return (binaryDecoder == null || !binaryDecoder.getClass().equals(DirectBinaryDecoder.class)) ? new DirectBinaryDecoder(inputStream) : ((DirectBinaryDecoder) binaryDecoder).configure(inputStream);
    }

    public int getConfiguredBufferSize() {
        return this.binaryDecoderBufferSize;
    }

    public JsonDecoder jsonDecoder(Schema schema, InputStream inputStream) {
        return new JsonDecoder(schema, inputStream);
    }

    public JsonDecoder jsonDecoder(Schema schema, String str) {
        return new JsonDecoder(schema, str);
    }

    public ResolvingDecoder resolvingDecoder(Schema schema, Schema schema2, Decoder decoder) {
        return new ResolvingDecoder(schema, schema2, decoder);
    }

    public ValidatingDecoder validatingDecoder(Schema schema, Decoder decoder) {
        return new ValidatingDecoder(schema, decoder);
    }
}
