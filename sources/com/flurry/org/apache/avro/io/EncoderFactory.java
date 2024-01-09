package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.codehaus.jackson.JsonGenerator;
import java.io.OutputStream;

/* loaded from: classes.dex */
public class EncoderFactory {
    private static final int DEFAULT_BLOCK_BUFFER_SIZE = 65536;
    private static final int DEFAULT_BUFFER_SIZE = 2048;
    private static final EncoderFactory DEFAULT_FACTORY = new DefaultEncoderFactory();
    private static final int MAX_BLOCK_BUFFER_SIZE = 1073741824;
    private static final int MIN_BLOCK_BUFFER_SIZE = 64;
    protected int binaryBufferSize = DEFAULT_BUFFER_SIZE;
    protected int binaryBlockSize = DEFAULT_BLOCK_BUFFER_SIZE;

    /* loaded from: classes.dex */
    class DefaultEncoderFactory extends EncoderFactory {
        private DefaultEncoderFactory() {
        }

        @Override // com.flurry.org.apache.avro.io.EncoderFactory
        public EncoderFactory configureBlockSize(int i) {
            throw new AvroRuntimeException("Default EncoderFactory cannot be configured");
        }

        @Override // com.flurry.org.apache.avro.io.EncoderFactory
        public EncoderFactory configureBufferSize(int i) {
            throw new AvroRuntimeException("Default EncoderFactory cannot be configured");
        }
    }

    public static EncoderFactory get() {
        return DEFAULT_FACTORY;
    }

    public BinaryEncoder binaryEncoder(OutputStream outputStream, BinaryEncoder binaryEncoder) {
        return (binaryEncoder == null || !binaryEncoder.getClass().equals(BufferedBinaryEncoder.class)) ? new BufferedBinaryEncoder(outputStream, this.binaryBufferSize) : ((BufferedBinaryEncoder) binaryEncoder).configure(outputStream, this.binaryBufferSize);
    }

    public BinaryEncoder blockingBinaryEncoder(OutputStream outputStream, BinaryEncoder binaryEncoder) {
        return (binaryEncoder == null || !binaryEncoder.getClass().equals(BlockingBinaryEncoder.class)) ? new BlockingBinaryEncoder(outputStream, this.binaryBlockSize, 32) : ((BlockingBinaryEncoder) binaryEncoder).configure(outputStream, this.binaryBlockSize, 32);
    }

    public EncoderFactory configureBlockSize(int i) {
        int i2 = MAX_BLOCK_BUFFER_SIZE;
        int i3 = i >= 64 ? i : 64;
        if (i3 <= MAX_BLOCK_BUFFER_SIZE) {
            i2 = i3;
        }
        this.binaryBufferSize = i2;
        return this;
    }

    public EncoderFactory configureBufferSize(int i) {
        int i2 = i >= 32 ? i : 32;
        this.binaryBufferSize = i2 <= 16777216 ? i2 : 16777216;
        return this;
    }

    public BinaryEncoder directBinaryEncoder(OutputStream outputStream, BinaryEncoder binaryEncoder) {
        return (binaryEncoder == null || !binaryEncoder.getClass().equals(DirectBinaryEncoder.class)) ? new DirectBinaryEncoder(outputStream) : ((DirectBinaryEncoder) binaryEncoder).configure(outputStream);
    }

    public int getBlockSize() {
        return this.binaryBlockSize;
    }

    public int getBufferSize() {
        return this.binaryBufferSize;
    }

    public JsonEncoder jsonEncoder(Schema schema, JsonGenerator jsonGenerator) {
        return new JsonEncoder(schema, jsonGenerator);
    }

    public JsonEncoder jsonEncoder(Schema schema, OutputStream outputStream) {
        return new JsonEncoder(schema, outputStream);
    }

    public ValidatingEncoder validatingEncoder(Schema schema, Encoder encoder) {
        return new ValidatingEncoder(schema, encoder);
    }
}
