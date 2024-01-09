package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.Schema;

/* loaded from: classes.dex */
public interface DatumReader {
    Object read(Object obj, Decoder decoder);

    void setSchema(Schema schema);
}
