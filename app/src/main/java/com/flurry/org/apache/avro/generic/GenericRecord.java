package com.flurry.org.apache.avro.generic;

/* loaded from: classes.dex */
public interface GenericRecord extends IndexedRecord {
    Object get(String str);

    void put(String str, Object obj);
}
