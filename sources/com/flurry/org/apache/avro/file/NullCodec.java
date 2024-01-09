package com.flurry.org.apache.avro.file;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
final class NullCodec extends Codec {
    private static final NullCodec INSTANCE = new NullCodec();
    public static final CodecFactory OPTION = new Option();

    /* loaded from: classes.dex */
    class Option extends CodecFactory {
        Option() {
        }

        @Override // com.flurry.org.apache.avro.file.CodecFactory
        protected Codec createInstance() {
            return NullCodec.INSTANCE;
        }
    }

    NullCodec() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.flurry.org.apache.avro.file.Codec
    public ByteBuffer compress(ByteBuffer byteBuffer) {
        return byteBuffer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.flurry.org.apache.avro.file.Codec
    public ByteBuffer decompress(ByteBuffer byteBuffer) {
        return byteBuffer;
    }

    @Override // com.flurry.org.apache.avro.file.Codec
    public boolean equals(Object obj) {
        return this == obj || getClass() == obj.getClass();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.flurry.org.apache.avro.file.Codec
    public String getName() {
        return DataFileConstants.NULL_CODEC;
    }

    @Override // com.flurry.org.apache.avro.file.Codec
    public int hashCode() {
        return 2;
    }
}
