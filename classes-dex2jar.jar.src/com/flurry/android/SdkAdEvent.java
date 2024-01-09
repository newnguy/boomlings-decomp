package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.specific.SpecificRecord;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;
import java.util.Map;

class SdkAdEvent extends SpecificRecordBase implements SpecificRecord {
  public static final Schema SCHEMA$ = (new Schema.Parser()).parse("{\"type\":\"record\",\"name\":\"SdkAdEvent\",\"namespace\":\"com.flurry.android\",\"fields\":[{\"name\":\"type\",\"type\":\"string\"},{\"name\":\"params\",\"type\":{\"type\":\"map\",\"values\":\"string\"}},{\"name\":\"timeOffset\",\"type\":\"long\"}]}");
  
  public CharSequence a;
  
  public Map b;
  
  public long c;
  
  public final void a(CharSequence paramCharSequence) {
    this.a = paramCharSequence;
  }
  
  public final void a(Long paramLong) {
    this.c = paramLong.longValue();
  }
  
  public final void a(Map paramMap) {
    this.b = paramMap;
  }
  
  public Object get(int paramInt) {
    switch (paramInt) {
      default:
        throw new AvroRuntimeException("Bad index");
      case 0:
        return this.a;
      case 1:
        return this.b;
      case 2:
        break;
    } 
    return Long.valueOf(this.c);
  }
  
  public Schema getSchema() {
    return SCHEMA$;
  }
  
  public void put(int paramInt, Object paramObject) {
    switch (paramInt) {
      default:
        throw new AvroRuntimeException("Bad index");
      case 0:
        this.a = (CharSequence)paramObject;
        return;
      case 1:
        this.b = (Map)paramObject;
        return;
      case 2:
        break;
    } 
    this.c = ((Long)paramObject).longValue();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\SdkAdEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */