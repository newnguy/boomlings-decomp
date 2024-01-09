package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;
import java.util.List;

public class SdkLogRequest$Builder extends SpecificRecordBuilderBase implements RecordBuilder {
  private CharSequence a;
  
  private List b;
  
  private List c;
  
  private long d;
  
  private boolean e;
  
  private SdkLogRequest$Builder(byte paramByte) {
    super(SdkLogRequest.SCHEMA$);
  }
  
  public SdkLogRequest build() {
    try {
      long l;
      CharSequence charSequence;
      List list;
      SdkLogRequest sdkLogRequest = new SdkLogRequest();
      this();
      if (fieldSetFlags()[0]) {
        charSequence = this.a;
      } else {
        charSequence = (CharSequence)defaultValue(fields()[0]);
      } 
      sdkLogRequest.a = charSequence;
      if (fieldSetFlags()[1]) {
        list = this.b;
      } else {
        list = (List)defaultValue(fields()[1]);
      } 
      sdkLogRequest.b = list;
      if (fieldSetFlags()[2]) {
        list = this.c;
      } else {
        list = (List)defaultValue(fields()[2]);
      } 
      sdkLogRequest.c = list;
      if (fieldSetFlags()[3]) {
        l = this.d;
      } else {
        l = ((Long)defaultValue(fields()[3])).longValue();
      } 
      sdkLogRequest.d = l;
      if (fieldSetFlags()[4]) {
        boolean bool1 = this.e;
        sdkLogRequest.e = bool1;
        return sdkLogRequest;
      } 
      boolean bool = ((Boolean)defaultValue(fields()[4])).booleanValue();
      sdkLogRequest.e = bool;
      return sdkLogRequest;
    } catch (Exception exception) {
      throw new AvroRuntimeException(exception);
    } 
  }
  
  public SdkLogRequest$Builder clearAdReportedIds() {
    this.b = null;
    fieldSetFlags()[1] = false;
    return this;
  }
  
  public SdkLogRequest$Builder clearAgentTimestamp() {
    fieldSetFlags()[3] = false;
    return this;
  }
  
  public SdkLogRequest$Builder clearApiKey() {
    this.a = null;
    fieldSetFlags()[0] = false;
    return this;
  }
  
  public SdkLogRequest$Builder clearSdkAdLogs() {
    this.c = null;
    fieldSetFlags()[2] = false;
    return this;
  }
  
  public SdkLogRequest$Builder clearTestDevice() {
    fieldSetFlags()[4] = false;
    return this;
  }
  
  public List getAdReportedIds() {
    return this.b;
  }
  
  public Long getAgentTimestamp() {
    return Long.valueOf(this.d);
  }
  
  public CharSequence getApiKey() {
    return this.a;
  }
  
  public List getSdkAdLogs() {
    return this.c;
  }
  
  public Boolean getTestDevice() {
    return Boolean.valueOf(this.e);
  }
  
  public boolean hasAdReportedIds() {
    return fieldSetFlags()[1];
  }
  
  public boolean hasAgentTimestamp() {
    return fieldSetFlags()[3];
  }
  
  public boolean hasApiKey() {
    return fieldSetFlags()[0];
  }
  
  public boolean hasSdkAdLogs() {
    return fieldSetFlags()[2];
  }
  
  public boolean hasTestDevice() {
    return fieldSetFlags()[4];
  }
  
  public SdkLogRequest$Builder setAdReportedIds(List paramList) {
    validate(fields()[1], paramList);
    this.b = paramList;
    fieldSetFlags()[1] = true;
    return this;
  }
  
  public SdkLogRequest$Builder setAgentTimestamp(long paramLong) {
    validate(fields()[3], Long.valueOf(paramLong));
    this.d = paramLong;
    fieldSetFlags()[3] = true;
    return this;
  }
  
  public SdkLogRequest$Builder setApiKey(CharSequence paramCharSequence) {
    validate(fields()[0], paramCharSequence);
    this.a = paramCharSequence;
    fieldSetFlags()[0] = true;
    return this;
  }
  
  public SdkLogRequest$Builder setSdkAdLogs(List paramList) {
    validate(fields()[2], paramList);
    this.c = paramList;
    fieldSetFlags()[2] = true;
    return this;
  }
  
  public SdkLogRequest$Builder setTestDevice(boolean paramBoolean) {
    validate(fields()[4], Boolean.valueOf(paramBoolean));
    this.e = paramBoolean;
    fieldSetFlags()[4] = true;
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\SdkLogRequest$Builder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */