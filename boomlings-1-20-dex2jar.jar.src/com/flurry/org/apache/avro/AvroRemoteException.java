package com.flurry.org.apache.avro;

import java.io.IOException;

public class AvroRemoteException extends IOException {
  private Object value;
  
  protected AvroRemoteException() {}
  
  public AvroRemoteException(Object paramObject) {
    super(str);
    String str;
    this.value = paramObject;
  }
  
  public AvroRemoteException(Object paramObject, Throwable paramThrowable) {
    super(str, paramThrowable);
    String str;
    this.value = paramObject;
  }
  
  public AvroRemoteException(Throwable paramThrowable) {
    this(paramThrowable.toString());
    initCause(paramThrowable);
  }
  
  public Object getValue() {
    return this.value;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\AvroRemoteException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */