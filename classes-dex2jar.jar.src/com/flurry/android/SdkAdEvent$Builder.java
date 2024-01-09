package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;
import java.util.Map;

public class SdkAdEvent$Builder extends SpecificRecordBuilderBase implements RecordBuilder {
  private CharSequence a;
  
  private Map b;
  
  private long c;
  
  private SdkAdEvent$Builder() {
    super(SdkAdEvent.SCHEMA$);
  }
  
  public SdkAdEvent build() {
    try {
      CharSequence charSequence;
      Map map;
      SdkAdEvent sdkAdEvent = new SdkAdEvent();
      this();
      if (fieldSetFlags()[0]) {
        charSequence = this.a;
      } else {
        charSequence = (CharSequence)defaultValue(fields()[0]);
      } 
      sdkAdEvent.a = charSequence;
      if (fieldSetFlags()[1]) {
        map = this.b;
      } else {
        map = (Map)defaultValue(fields()[1]);
      } 
      sdkAdEvent.b = map;
      if (fieldSetFlags()[2]) {
        long l1 = this.c;
        sdkAdEvent.c = l1;
        return sdkAdEvent;
      } 
      long l = ((Long)defaultValue(fields()[2])).longValue();
      sdkAdEvent.c = l;
      return sdkAdEvent;
    } catch (Exception exception) {
      throw new AvroRuntimeException(exception);
    } 
  }
  
  public SdkAdEvent$Builder clearParams() {
    this.b = null;
    fieldSetFlags()[1] = false;
    return this;
  }
  
  public SdkAdEvent$Builder clearTimeOffset() {
    fieldSetFlags()[2] = false;
    return this;
  }
  
  public SdkAdEvent$Builder clearType() {
    this.a = null;
    fieldSetFlags()[0] = false;
    return this;
  }
  
  public Map getParams() {
    return this.b;
  }
  
  public Long getTimeOffset() {
    return Long.valueOf(this.c);
  }
  
  public CharSequence getType() {
    return this.a;
  }
  
  public boolean hasParams() {
    return fieldSetFlags()[1];
  }
  
  public boolean hasTimeOffset() {
    return fieldSetFlags()[2];
  }
  
  public boolean hasType() {
    return fieldSetFlags()[0];
  }
  
  public SdkAdEvent$Builder setParams(Map paramMap) {
    validate(fields()[1], paramMap);
    this.b = paramMap;
    fieldSetFlags()[1] = true;
    return this;
  }
  
  public SdkAdEvent$Builder setTimeOffset(long paramLong) {
    validate(fields()[2], Long.valueOf(paramLong));
    this.c = paramLong;
    fieldSetFlags()[2] = true;
    return this;
  }
  
  public SdkAdEvent$Builder setType(CharSequence paramCharSequence) {
    validate(fields()[0], paramCharSequence);
    this.a = paramCharSequence;
    fieldSetFlags()[0] = true;
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\SdkAdEvent$Builder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */