package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.specific.SpecificRecord;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;
import java.util.List;
import java.util.Map;

class AdRequest extends SpecificRecordBase implements SpecificRecord {
  public static final Schema SCHEMA$ = (new Schema.Parser()).parse("{\"type\":\"record\",\"name\":\"AdRequest\",\"namespace\":\"com.flurry.android\",\"fields\":[{\"name\":\"apiKey\",\"type\":\"string\"},{\"name\":\"agentVersion\",\"type\":\"string\",\"default\":\"null\"},{\"name\":\"adSpaceName\",\"type\":\"string\"},{\"name\":\"sessionId\",\"type\":\"long\"},{\"name\":\"adReportedIds\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"AdReportedId\",\"fields\":[{\"name\":\"type\",\"type\":\"int\"},{\"name\":\"id\",\"type\":\"bytes\"}]}}},{\"name\":\"location\",\"type\":{\"type\":\"record\",\"name\":\"Location\",\"fields\":[{\"name\":\"lat\",\"type\":\"float\",\"default\":0.0},{\"name\":\"lon\",\"type\":\"float\",\"default\":0.0}]},\"default\":\"null\"},{\"name\":\"testDevice\",\"type\":\"boolean\",\"default\":false},{\"name\":\"bindings\",\"type\":{\"type\":\"array\",\"items\":\"int\"}},{\"name\":\"adViewContainer\",\"type\":{\"type\":\"record\",\"name\":\"AdViewContainer\",\"fields\":[{\"name\":\"viewWidth\",\"type\":\"int\",\"default\":0},{\"name\":\"viewHeight\",\"type\":\"int\",\"default\":0},{\"name\":\"screenWidth\",\"type\":\"int\",\"default\":0},{\"name\":\"screenHeight\",\"type\":\"int\",\"default\":0}]},\"default\":\"null\"},{\"name\":\"locale\",\"type\":\"string\",\"default\":\"null\"},{\"name\":\"timezone\",\"type\":\"string\",\"default\":\"null\"},{\"name\":\"osVersion\",\"type\":\"string\",\"default\":\"null\"},{\"name\":\"devicePlatform\",\"type\":\"string\",\"default\":\"null\"},{\"name\":\"testAds\",\"type\":{\"type\":\"record\",\"name\":\"TestAds\",\"fields\":[{\"name\":\"adspacePlacement\",\"type\":\"int\",\"default\":0}]},\"default\":\"null\"},{\"name\":\"keywords\",\"type\":{\"type\":\"map\",\"values\":\"string\"},\"default\":[]},{\"name\":\"refresh\",\"type\":\"boolean\",\"default\":false}]}");
  
  public CharSequence a;
  
  public CharSequence b;
  
  public CharSequence c;
  
  public long d;
  
  public List e;
  
  public Location f;
  
  public boolean g;
  
  public List h;
  
  public AdViewContainer i;
  
  public CharSequence j;
  
  public CharSequence k;
  
  public CharSequence l;
  
  public CharSequence m;
  
  public TestAds n;
  
  public Map o;
  
  public boolean p;
  
  public static AdRequest$Builder a() {
    return new AdRequest$Builder();
  }
  
  public final void a(TestAds paramTestAds) {
    this.n = paramTestAds;
  }
  
  public final void a(Boolean paramBoolean) {
    this.p = paramBoolean.booleanValue();
  }
  
  public final void a(CharSequence paramCharSequence) {
    this.c = paramCharSequence;
  }
  
  public final void a(Map paramMap) {
    this.o = paramMap;
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
        return this.c;
      case 3:
        return Long.valueOf(this.d);
      case 4:
        return this.e;
      case 5:
        return this.f;
      case 6:
        return Boolean.valueOf(this.g);
      case 7:
        return this.h;
      case 8:
        return this.i;
      case 9:
        return this.j;
      case 10:
        return this.k;
      case 11:
        return this.l;
      case 12:
        return this.m;
      case 13:
        return this.n;
      case 14:
        return this.o;
      case 15:
        break;
    } 
    return Boolean.valueOf(this.p);
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
        this.b = (CharSequence)paramObject;
        return;
      case 2:
        this.c = (CharSequence)paramObject;
        return;
      case 3:
        this.d = ((Long)paramObject).longValue();
        return;
      case 4:
        this.e = (List)paramObject;
        return;
      case 5:
        this.f = (Location)paramObject;
        return;
      case 6:
        this.g = ((Boolean)paramObject).booleanValue();
        return;
      case 7:
        this.h = (List)paramObject;
        return;
      case 8:
        this.i = (AdViewContainer)paramObject;
        return;
      case 9:
        this.j = (CharSequence)paramObject;
        return;
      case 10:
        this.k = (CharSequence)paramObject;
        return;
      case 11:
        this.l = (CharSequence)paramObject;
        return;
      case 12:
        this.m = (CharSequence)paramObject;
        return;
      case 13:
        this.n = (TestAds)paramObject;
        return;
      case 14:
        this.o = (Map)paramObject;
        return;
      case 15:
        break;
    } 
    this.p = ((Boolean)paramObject).booleanValue();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\AdRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */