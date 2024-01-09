package com.flurry.org.apache.avro.file;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.file.DeflateCodec;
import com.flurry.org.apache.avro.file.SnappyCodec;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class CodecFactory {
    private static final int DEFAULT_DEFLATE_LEVEL = -1;
    private static final Map REGISTERED = new HashMap();

    static {
        addCodec(DataFileConstants.NULL_CODEC, nullCodec());
        addCodec(DataFileConstants.DEFLATE_CODEC, deflateCodec(-1));
        addCodec(DataFileConstants.SNAPPY_CODEC, snappyCodec());
    }

    public static CodecFactory addCodec(String str, CodecFactory codecFactory) {
        return (CodecFactory) REGISTERED.put(str, codecFactory);
    }

    public static CodecFactory deflateCodec(int i) {
        return new DeflateCodec.Option(i);
    }

    public static CodecFactory fromString(String str) {
        CodecFactory codecFactory = (CodecFactory) REGISTERED.get(str);
        if (codecFactory == null) {
            throw new AvroRuntimeException("Unrecognized codec: " + str);
        }
        return codecFactory;
    }

    public static CodecFactory nullCodec() {
        return NullCodec.OPTION;
    }

    public static CodecFactory snappyCodec() {
        return new SnappyCodec.Option();
    }

    public abstract Codec createInstance();

    public String toString() {
        return createInstance().toString();
    }
}
