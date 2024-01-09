package com.flurry.org.apache.avro;

import java.io.IOException;

/* loaded from: classes.dex */
public class AvroRemoteException extends IOException {
    private Object value;

    public AvroRemoteException() {
    }

    public AvroRemoteException(Object obj) {
        super(obj != null ? obj.toString() : null);
        this.value = obj;
    }

    public AvroRemoteException(Object obj, Throwable th) {
        super(obj != null ? obj.toString() : null, th);
        this.value = obj;
    }

    public AvroRemoteException(Throwable th) {
        this(th.toString());
        initCause(th);
    }

    public Object getValue() {
        return this.value;
    }
}
