package com.flurry.org.apache.avro.data;

public interface ErrorBuilder extends RecordBuilder {
  ErrorBuilder clearCause();
  
  ErrorBuilder clearValue();
  
  Throwable getCause();
  
  Object getValue();
  
  boolean hasCause();
  
  boolean hasValue();
  
  ErrorBuilder setCause(Throwable paramThrowable);
  
  ErrorBuilder setValue(Object paramObject);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\data\ErrorBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */