package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;
import java.util.List;

public class SdkAdLog$Builder extends SpecificRecordBuilderBase implements RecordBuilder {
  private long a;
  
  private CharSequence b;
  
  private List c;
  
  private SdkAdLog$Builder() {
    super(SdkAdLog.SCHEMA$);
  }
  
  public SdkAdLog build() {
    try {
      long l;
      CharSequence charSequence;
      SdkAdLog sdkAdLog = new SdkAdLog();
      this();
      if (fieldSetFlags()[0]) {
        l = this.a;
      } else {
        l = ((Long)defaultValue(fields()[0])).longValue();
      } 
      sdkAdLog.a = l;
      if (fieldSetFlags()[1]) {
        charSequence = this.b;
      } else {
        charSequence = (CharSequence)defaultValue(fields()[1]);
      } 
      sdkAdLog.b = charSequence;
      if (fieldSetFlags()[2]) {
        List list1 = this.c;
        sdkAdLog.c = list1;
        return sdkAdLog;
      } 
      List list = (List)defaultValue(fields()[2]);
      sdkAdLog.c = list;
      return sdkAdLog;
    } catch (Exception exception) {
      throw new AvroRuntimeException(exception);
    } 
  }
  
  public SdkAdLog$Builder clearAdLogGUID() {
    this.b = null;
    fieldSetFlags()[1] = false;
    return this;
  }
  
  public SdkAdLog$Builder clearSdkAdEvents() {
    this.c = null;
    fieldSetFlags()[2] = false;
    return this;
  }
  
  public SdkAdLog$Builder clearSessionId() {
    fieldSetFlags()[0] = false;
    return this;
  }
  
  public CharSequence getAdLogGUID() {
    return this.b;
  }
  
  public List getSdkAdEvents() {
    return this.c;
  }
  
  public Long getSessionId() {
    return Long.valueOf(this.a);
  }
  
  public boolean hasAdLogGUID() {
    return fieldSetFlags()[1];
  }
  
  public boolean hasSdkAdEvents() {
    return fieldSetFlags()[2];
  }
  
  public boolean hasSessionId() {
    return fieldSetFlags()[0];
  }
  
  public SdkAdLog$Builder setAdLogGUID(CharSequence paramCharSequence) {
    validate(fields()[1], paramCharSequence);
    this.b = paramCharSequence;
    fieldSetFlags()[1] = true;
    return this;
  }
  
  public SdkAdLog$Builder setSdkAdEvents(List paramList) {
    validate(fields()[2], paramList);
    this.c = paramList;
    fieldSetFlags()[2] = true;
    return this;
  }
  
  public SdkAdLog$Builder setSessionId(long paramLong) {
    validate(fields()[0], Long.valueOf(paramLong));
    this.a = paramLong;
    fieldSetFlags()[0] = true;
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\SdkAdLog$Builder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */