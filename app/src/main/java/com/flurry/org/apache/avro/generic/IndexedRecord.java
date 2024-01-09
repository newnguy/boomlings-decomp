package com.flurry.org.apache.avro.generic;

/* loaded from: classes.dex */
public interface IndexedRecord extends GenericContainer {
    Object get(int i);

    void put(int i, Object obj);
}
