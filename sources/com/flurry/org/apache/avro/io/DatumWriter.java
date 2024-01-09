package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.Schema;

/* loaded from: classes.dex */
public interface DatumWriter {
    void setSchema(Schema schema);

    void write(Object obj, Encoder encoder);
}
