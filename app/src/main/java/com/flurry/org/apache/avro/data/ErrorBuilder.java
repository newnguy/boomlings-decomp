package com.flurry.org.apache.avro.data;

/* loaded from: classes.dex */
public interface ErrorBuilder extends RecordBuilder {
    ErrorBuilder clearCause();

    ErrorBuilder clearValue();

    Throwable getCause();

    Object getValue();

    boolean hasCause();

    boolean hasValue();

    ErrorBuilder setCause(Throwable th);

    ErrorBuilder setValue(Object obj);
}
