package com.flurry.org.apache.avro.file;

import java.nio.ByteBuffer;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class NullCodec extends Codec {
    private static final NullCodec INSTANCE = new NullCodec();
    public static final CodecFactory OPTION = new Option();

    /* loaded from: classes.dex */
    class Option extends CodecFactory {
        Option() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.flurry.org.apache.avro.file.CodecFactory
        public Codec createInstance() {
            return NullCodec.INSTANCE;
        }
    }

    NullCodec() {
    }

    @Override // com.flurry.org.apache.avro.file.Codec
    public ByteBuffer compress(ByteBuffer byteBuffer) {
        return byteBuffer;
    }

    @Override // com.flurry.org.apache.avro.file.Codec
    public ByteBuffer decompress(ByteBuffer byteBuffer) {
        return byteBuffer;
    }

    @Override // com.flurry.org.apache.avro.file.Codec
    public boolean equals(Object obj) {
        return this == obj || getClass() == obj.getClass();
    }

    @Override // com.flurry.org.apache.avro.file.Codec
    public String getName() {
        return DataFileConstants.NULL_CODEC;
    }

    @Override // com.flurry.org.apache.avro.file.Codec
    public int hashCode() {
        return 2;
    }
}
