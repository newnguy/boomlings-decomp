package com.flurry.org.apache.avro.generic;

import com.flurry.org.apache.avro.util.WeakIdentityHashMap;
import java.util.Map;

final class GenericDatumReader$1 extends ThreadLocal {
  protected Map initialValue() {
    return (Map)new WeakIdentityHashMap();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\generic\GenericDatumReader$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */