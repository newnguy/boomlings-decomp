package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.specific.SpecificRecord;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;

class AdViewContainer extends SpecificRecordBase implements SpecificRecord {
  public static final Schema SCHEMA$ = (new Schema.Parser()).parse("{\"type\":\"record\",\"name\":\"AdViewContainer\",\"namespace\":\"com.flurry.android\",\"fields\":[{\"name\":\"viewWidth\",\"type\":\"int\",\"default\":0},{\"name\":\"viewHeight\",\"type\":\"int\",\"default\":0},{\"name\":\"screenWidth\",\"type\":\"int\",\"default\":0},{\"name\":\"screenHeight\",\"type\":\"int\",\"default\":0}]}");
  
  public int a;
  
  public int b;
  
  public int c;
  
  public int d;
  
  public static AdViewContainer$Builder a() {
    return new AdViewContainer$Builder();
  }
  
  public Object get(int paramInt) {
    switch (paramInt) {
      default:
        throw new AvroRuntimeException("Bad index");
      case 0:
        return Integer.valueOf(this.a);
      case 1:
        return Integer.valueOf(this.b);
      case 2:
        return Integer.valueOf(this.c);
      case 3:
        break;
    } 
    return Integer.valueOf(this.d);
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
        this.b = ((Integer)paramObject).intValue();
        return;
      case 2:
        this.c = ((Integer)paramObject).intValue();
        return;
      case 3:
        break;
    } 
    this.d = ((Integer)paramObject).intValue();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\AdViewContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */