package com.flurry.org.apache.avro.specific;

import com.flurry.org.apache.avro.AvroRemoteException;
import com.flurry.org.apache.avro.Schema;

/* loaded from: classes.dex */
public abstract class SpecificExceptionBase extends AvroRemoteException implements SpecificRecord {
    public SpecificExceptionBase() {
    }

    public SpecificExceptionBase(Object obj) {
        super(obj);
    }

    public SpecificExceptionBase(Object obj, Throwable th) {
        super(obj, th);
    }

    public SpecificExceptionBase(Throwable th) {
        super(th);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SpecificExceptionBase) && getClass() == obj.getClass() && SpecificData.get().compare(this, obj, getSchema()) == 0;
    }

    @Override // com.flurry.org.apache.avro.generic.IndexedRecord
    public abstract Object get(int i);

    @Override // com.flurry.org.apache.avro.generic.GenericContainer
    public abstract Schema getSchema();

    public int hashCode() {
        return SpecificData.get().hashCode(this, getSchema());
    }

    @Override // com.flurry.org.apache.avro.generic.IndexedRecord
    public abstract void put(int i, Object obj);
}
