package com.flurry.org.apache.avro;

/* loaded from: classes.dex */
public class AvroRuntimeException extends RuntimeException {
    public AvroRuntimeException(String str) {
        super(str);
    }

    public AvroRuntimeException(String str, Throwable th) {
        super(str, th);
    }

    public AvroRuntimeException(Throwable th) {
        super(th);
    }
}
