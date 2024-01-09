package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;
import java.util.List;

public class AdResponse$Builder extends SpecificRecordBuilderBase implements RecordBuilder {
  private List a;
  
  private List b;
  
  private AdResponse$Builder() {
    super(AdResponse.SCHEMA$);
  }
  
  public AdResponse build() {
    try {
      AdResponse adResponse = new AdResponse();
      this();
      if (fieldSetFlags()[0]) {
        list = this.a;
      } else {
        list = (List)defaultValue(fields()[0]);
      } 
      adResponse.a = list;
      if (fieldSetFlags()[1]) {
        list = this.b;
        adResponse.b = list;
        return adResponse;
      } 
      List list = (List)defaultValue(fields()[1]);
      adResponse.b = list;
      return adResponse;
    } catch (Exception exception) {
      throw new AvroRuntimeException(exception);
    } 
  }
  
  public AdResponse$Builder clearAdUnits() {
    this.a = null;
    fieldSetFlags()[0] = false;
    return this;
  }
  
  public AdResponse$Builder clearErrors() {
    this.b = null;
    fieldSetFlags()[1] = false;
    return this;
  }
  
  public List getAdUnits() {
    return this.a;
  }
  
  public List getErrors() {
    return this.b;
  }
  
  public boolean hasAdUnits() {
    return fieldSetFlags()[0];
  }
  
  public boolean hasErrors() {
    return fieldSetFlags()[1];
  }
  
  public AdResponse$Builder setAdUnits(List paramList) {
    validate(fields()[0], paramList);
    this.a = paramList;
    fieldSetFlags()[0] = true;
    return this;
  }
  
  public AdResponse$Builder setErrors(List paramList) {
    validate(fields()[1], paramList);
    this.b = paramList;
    fieldSetFlags()[1] = true;
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\AdResponse$Builder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */