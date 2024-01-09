package com.flurry.org.apache.avro.specific;

import com.flurry.org.apache.avro.AvroRemoteException;
import com.flurry.org.apache.avro.Schema;

public abstract class SpecificExceptionBase extends AvroRemoteException implements SpecificRecord {
  public SpecificExceptionBase() {}
  
  public SpecificExceptionBase(Object paramObject) {
    super(paramObject);
  }
  
  public SpecificExceptionBase(Object paramObject, Throwable paramThrowable) {
    super(paramObject, paramThrowable);
  }
  
  public SpecificExceptionBase(Throwable paramThrowable) {
    super(paramThrowable);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (!(paramObject instanceof SpecificExceptionBase))
        return false; 
      if (getClass() != paramObject.getClass())
        return false; 
      if (SpecificData.get().compare(this, paramObject, getSchema()) != 0)
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
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\specific\SpecificExceptionBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */