package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.specific.SpecificRecord;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;

class AdSpaceLayout extends SpecificRecordBase implements SpecificRecord {
  public static final Schema SCHEMA$ = (new Schema.Parser()).parse("{\"type\":\"record\",\"name\":\"AdSpaceLayout\",\"namespace\":\"com.flurry.android\",\"fields\":[{\"name\":\"adWidth\",\"type\":\"int\"},{\"name\":\"adHeight\",\"type\":\"int\"},{\"name\":\"fix\",\"type\":\"string\"},{\"name\":\"format\",\"type\":\"string\"},{\"name\":\"alignment\",\"type\":\"string\"}]}");
  
  public int a;
  
  public int b;
  
  public CharSequence c;
  
  public CharSequence d;
  
  public CharSequence e;
  
  public final Integer a() {
    return Integer.valueOf(this.a);
  }
  
  public final Integer b() {
    return Integer.valueOf(this.b);
  }
  
  public final CharSequence c() {
    return this.c;
  }
  
  public final CharSequence d() {
    return this.d;
  }
  
  public final CharSequence e() {
    return this.e;
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
        return this.c;
      case 3:
        return this.d;
      case 4:
        break;
    } 
    return this.e;
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
        this.c = (CharSequence)paramObject;
        return;
      case 3:
        this.d = (CharSequence)paramObject;
        return;
      case 4:
        break;
    } 
    this.e = (CharSequence)paramObject;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\AdSpaceLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */