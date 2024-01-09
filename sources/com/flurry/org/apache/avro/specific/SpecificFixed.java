package com.flurry.org.apache.avro.specific;

import com.flurry.org.apache.avro.generic.GenericData;

/* loaded from: classes.dex */
public abstract class SpecificFixed extends GenericData.Fixed {
    public SpecificFixed() {
        setSchema(SpecificData.get().getSchema(getClass()));
    }

    public SpecificFixed(byte[] bArr) {
        this();
        bytes(bArr);
    }
}
