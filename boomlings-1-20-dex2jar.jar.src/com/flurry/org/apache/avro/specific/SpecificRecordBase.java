package com.flurry.org.apache.avro.specific;

import com.flurry.org.apache.avro.Schema;

public abstract class SpecificRecordBase implements SpecificRecord, Comparable {
  public int compareTo(SpecificRecord paramSpecificRecord) {
    return SpecificData.get().compare(this, paramSpecificRecord, getSchema());
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (!(paramObject instanceof SpecificRecord))
        return false; 
      if (getClass() != paramObject.getClass())
        return false; 
      if (compareTo((SpecificRecord)paramObject) != 0)
        bool = false; 
    } 
    return bool;
  }
  
  public abstract Object get(int paramInt);
  
  public abstract Schema getSchema();
  
  public int hashCode() {
    return SpecificData.get().hashCode(this, getSchema());
  }
  
  public abstract void put(int paramInt, Object paramObject);
  
  public String toString() {
    return SpecificData.get().toString(this);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\specific\SpecificRecordBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */