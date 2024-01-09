package com.flurry.org.apache.avro.file;

import com.flurry.org.apache.avro.Schema;
import java.io.Closeable;
import java.util.Iterator;

public interface FileReader extends Closeable, Iterable, Iterator {
  Schema getSchema();
  
  Object next(Object paramObject);
  
  boolean pastSync(long paramLong);
  
  void sync(long paramLong);
  
  long tell();
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\file\FileReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */