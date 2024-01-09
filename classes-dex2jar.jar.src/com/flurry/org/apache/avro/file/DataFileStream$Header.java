package com.flurry.org.apache.avro.file;

import com.flurry.org.apache.avro.Schema;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DataFileStream$Header {
  Map meta = new HashMap<Object, Object>();
  
  private transient List metaKeyList = new ArrayList();
  
  Schema schema;
  
  byte[] sync = new byte[16];
  
  private DataFileStream$Header() {}
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\file\DataFileStream$Header.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */