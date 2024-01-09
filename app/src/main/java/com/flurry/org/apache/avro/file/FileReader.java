package com.flurry.org.apache.avro.file;

import com.flurry.org.apache.avro.Schema;
import java.io.Closeable;
import java.util.Iterator;

/* loaded from: classes.dex */
public interface FileReader extends Closeable, Iterable, Iterator {
    Schema getSchema();

    Object next(Object obj);

    boolean pastSync(long j);

    void sync(long j);

    long tell();
}
