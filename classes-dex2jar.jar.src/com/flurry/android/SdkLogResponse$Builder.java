package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;
import java.util.List;

public class SdkLogResponse$Builder extends SpecificRecordBuilderBase implements RecordBuilder {
  private CharSequence a;
  
  private List b;
  
  private SdkLogResponse$Builder() {
    super(SdkLogResponse.SCHEMA$);
  }
  
  public SdkLogResponse build() {
    try {
      CharSequence charSequence;
      SdkLogResponse sdkLogResponse = new SdkLogResponse();
      this();
      if (fieldSetFlags()[0]) {
        charSequence = this.a;
      } else {
        charSequence = (CharSequence)defaultValue(fields()[0]);
      } 
      sdkLogResponse.a = charSequence;
      if (fieldSetFlags()[1]) {
        List list1 = this.b;
        sdkLogResponse.b = list1;
        return sdkLogResponse;
      } 
      List list = (List)defaultValue(fields()[1]);
      sdkLogResponse.b = list;
      return sdkLogResponse;
    } catch (Exception exception) {
      throw new AvroRuntimeException(exception);
    } 
  }
  
  public SdkLogResponse$Builder clearErrors() {
    this.b = null;
    fieldSetFlags()[1] = false;
    return this;
  }
  
  public SdkLogResponse$Builder clearResult() {
    this.a = null;
    fieldSetFlags()[0] = false;
    return this;
  }
  
  public List getErrors() {
    return this.b;
  }
  
  public CharSequence getResult() {
    return this.a;
  }
  
  public boolean hasErrors() {
    return fieldSetFlags()[1];
  }
  
  public boolean hasResult() {
    return fieldSetFlags()[0];
  }
  
  public SdkLogResponse$Builder setErrors(List paramList) {
    validate(fields()[1], paramList);
    this.b = paramList;
    fieldSetFlags()[1] = true;
    return this;
  }
  
  public SdkLogResponse$Builder setResult(CharSequence paramCharSequence) {
    validate(fields()[0], paramCharSequence);
    this.a = paramCharSequence;
    fieldSetFlags()[0] = true;
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\SdkLogResponse$Builder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */