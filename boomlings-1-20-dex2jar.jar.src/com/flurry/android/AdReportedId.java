package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.specific.SpecificRecord;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;
import java.nio.ByteBuffer;

class AdReportedId extends SpecificRecordBase implements SpecificRecord {
  public static final Schema SCHEMA$ = (new Schema.Parser()).parse("{\"type\":\"record\",\"name\":\"AdReportedId\",\"namespace\":\"com.flurry.android\",\"fields\":[{\"name\":\"type\",\"type\":\"int\"},{\"name\":\"id\",\"type\":\"bytes\"}]}");
  
  public int a;
  
  public ByteBuffer b;
  
  public static AdReportedId$Builder a() {
    return new AdReportedId$Builder();
  }
  
  public Object get(int paramInt) {
    switch (paramInt) {
      default:
        throw new AvroRuntimeException("Bad index");
      case 0:
        return Integer.valueOf(this.a);
      case 1:
        break;
    } 
    return this.b;
  }
  
  public Schema getSchema() {
    return SCHEMA$;
  }
  
  public void put(int paramInt, Object paramObject) {
    switch (paramInt) {
      default:
        throw new AvroRuntimeException("Bad index");
      case 0:
        this.a = ((Integer)paramObject).intValue();
        return;
      case 1:
        break;
    } 
    this.b = (ByteBuffer)paramObject;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\AdReportedId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */