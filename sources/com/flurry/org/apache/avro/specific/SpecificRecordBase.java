package com.flurry.org.apache.avro.specific;

import com.flurry.org.apache.avro.Schema;

/* loaded from: classes.dex */
public abstract class SpecificRecordBase implements SpecificRecord, Comparable {
    @Override // java.lang.Comparable
    public int compareTo(SpecificRecord specificRecord) {
        return SpecificData.get().compare(this, specificRecord, getSchema());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SpecificRecord) && getClass() == obj.getClass() && compareTo((SpecificRecord) obj) == 0;
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

    public String toString() {
        return SpecificData.get().toString(this);
    }
}
