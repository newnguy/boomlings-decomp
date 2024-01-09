package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.specific.SpecificRecord;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;
import java.util.List;

class SdkAdLog extends SpecificRecordBase implements SpecificRecord {
  public static final Schema SCHEMA$ = (new Schema.Parser()).parse("{\"type\":\"record\",\"name\":\"SdkAdLog\",\"namespace\":\"com.flurry.android\",\"fields\":[{\"name\":\"sessionId\",\"type\":\"long\"},{\"name\":\"adLogGUID\",\"type\":\"string\"},{\"name\":\"sdkAdEvents\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"SdkAdEvent\",\"fields\":[{\"name\":\"type\",\"type\":\"string\"},{\"name\":\"params\",\"type\":{\"type\":\"map\",\"values\":\"string\"}},{\"name\":\"timeOffset\",\"type\":\"long\"}]}}}]}");
  
  public long a;
  
  public CharSequence b;
  
  public List c;
  
  public final void a(CharSequence paramCharSequence) {
    this.b = paramCharSequence;
  }
  
  public final void a(Long paramLong) {
    this.a = paramLong.longValue();
  }
  
  public final void a(List paramList) {
    this.c = paramList;
  }
  
  public Object get(int paramInt) {
    switch (paramInt) {
      default:
        throw new AvroRuntimeException("Bad index");
      case 0:
        return Long.valueOf(this.a);
      case 1:
        return this.b;
      case 2:
        break;
    } 
    return this.c;
  }
  
  public Schema getSchema() {
    return SCHEMA$;
  }
  
  public void put(int paramInt, Object paramObject) {
    switch (paramInt) {
      default:
        throw new AvroRuntimeException("Bad index");
      case 0:
        this.a = ((Long)paramObject).longValue();
        return;
      case 1:
        this.b = (CharSequence)paramObject;
        return;
      case 2:
        break;
    } 
    this.c = (List)paramObject;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\SdkAdLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */