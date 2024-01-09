package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.specific.SpecificRecord;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;
import java.util.List;

class AdFrame extends SpecificRecordBase implements SpecificRecord {
  public static final Schema SCHEMA$ = (new Schema.Parser()).parse("{\"type\":\"record\",\"name\":\"AdFrame\",\"namespace\":\"com.flurry.android\",\"fields\":[{\"name\":\"binding\",\"type\":\"int\"},{\"name\":\"display\",\"type\":\"string\"},{\"name\":\"content\",\"type\":\"string\"},{\"name\":\"adSpaceLayout\",\"type\":{\"type\":\"record\",\"name\":\"AdSpaceLayout\",\"fields\":[{\"name\":\"adWidth\",\"type\":\"int\"},{\"name\":\"adHeight\",\"type\":\"int\"},{\"name\":\"fix\",\"type\":\"string\"},{\"name\":\"format\",\"type\":\"string\"},{\"name\":\"alignment\",\"type\":\"string\"}]}},{\"name\":\"callbacks\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Callback\",\"fields\":[{\"name\":\"event\",\"type\":\"string\"},{\"name\":\"actions\",\"type\":{\"type\":\"array\",\"items\":\"string\"}}]}}},{\"name\":\"adGuid\",\"type\":\"string\"}]}");
  
  public int a;
  
  public CharSequence b;
  
  public CharSequence c;
  
  public AdSpaceLayout d;
  
  public List e;
  
  public CharSequence f;
  
  public final Integer a() {
    return Integer.valueOf(this.a);
  }
  
  public final CharSequence b() {
    return this.b;
  }
  
  public final CharSequence c() {
    return this.c;
  }
  
  public final AdSpaceLayout d() {
    return this.d;
  }
  
  public final List e() {
    return this.e;
  }
  
  public final CharSequence f() {
    return this.f;
  }
  
  public Object get(int paramInt) {
    switch (paramInt) {
      default:
        throw new AvroRuntimeException("Bad index");
      case 0:
        return Integer.valueOf(this.a);
      case 1:
        return this.b;
      case 2:
        return this.c;
      case 3:
        return this.d;
      case 4:
        return this.e;
      case 5:
        break;
    } 
    return this.f;
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
        this.b = (CharSequence)paramObject;
        return;
      case 2:
        this.c = (CharSequence)paramObject;
        return;
      case 3:
        this.d = (AdSpaceLayout)paramObject;
        return;
      case 4:
        this.e = (List)paramObject;
        return;
      case 5:
        break;
    } 
    this.f = (CharSequence)paramObject;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\AdFrame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */