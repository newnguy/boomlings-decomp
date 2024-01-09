package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;

public class AdViewContainer$Builder extends SpecificRecordBuilderBase implements RecordBuilder {
  private int a;
  
  private int b;
  
  private int c;
  
  private int d;
  
  private AdViewContainer$Builder(byte paramByte) {
    super(AdViewContainer.SCHEMA$);
  }
  
  public AdViewContainer build() {
    try {
      AdViewContainer adViewContainer = new AdViewContainer();
      this();
      if (fieldSetFlags()[0]) {
        i = this.a;
      } else {
        i = ((Integer)defaultValue(fields()[0])).intValue();
      } 
      adViewContainer.a = i;
      if (fieldSetFlags()[1]) {
        i = this.b;
      } else {
        i = ((Integer)defaultValue(fields()[1])).intValue();
      } 
      adViewContainer.b = i;
      if (fieldSetFlags()[2]) {
        i = this.c;
      } else {
        i = ((Integer)defaultValue(fields()[2])).intValue();
      } 
      adViewContainer.c = i;
      if (fieldSetFlags()[3]) {
        i = this.d;
        adViewContainer.d = i;
        return adViewContainer;
      } 
      int i = ((Integer)defaultValue(fields()[3])).intValue();
      adViewContainer.d = i;
      return adViewContainer;
    } catch (Exception exception) {
      throw new AvroRuntimeException(exception);
    } 
  }
  
  public AdViewContainer$Builder clearScreenHeight() {
    fieldSetFlags()[3] = false;
    return this;
  }
  
  public AdViewContainer$Builder clearScreenWidth() {
    fieldSetFlags()[2] = false;
    return this;
  }
  
  public AdViewContainer$Builder clearViewHeight() {
    fieldSetFlags()[1] = false;
    return this;
  }
  
  public AdViewContainer$Builder clearViewWidth() {
    fieldSetFlags()[0] = false;
    return this;
  }
  
  public Integer getScreenHeight() {
    return Integer.valueOf(this.d);
  }
  
  public Integer getScreenWidth() {
    return Integer.valueOf(this.c);
  }
  
  public Integer getViewHeight() {
    return Integer.valueOf(this.b);
  }
  
  public Integer getViewWidth() {
    return Integer.valueOf(this.a);
  }
  
  public boolean hasScreenHeight() {
    return fieldSetFlags()[3];
  }
  
  public boolean hasScreenWidth() {
    return fieldSetFlags()[2];
  }
  
  public boolean hasViewHeight() {
    return fieldSetFlags()[1];
  }
  
  public boolean hasViewWidth() {
    return fieldSetFlags()[0];
  }
  
  public AdViewContainer$Builder setScreenHeight(int paramInt) {
    validate(fields()[3], Integer.valueOf(paramInt));
    this.d = paramInt;
    fieldSetFlags()[3] = true;
    return this;
  }
  
  public AdViewContainer$Builder setScreenWidth(int paramInt) {
    validate(fields()[2], Integer.valueOf(paramInt));
    this.c = paramInt;
    fieldSetFlags()[2] = true;
    return this;
  }
  
  public AdViewContainer$Builder setViewHeight(int paramInt) {
    validate(fields()[1], Integer.valueOf(paramInt));
    this.b = paramInt;
    fieldSetFlags()[1] = true;
    return this;
  }
  
  public AdViewContainer$Builder setViewWidth(int paramInt) {
    validate(fields()[0], Integer.valueOf(paramInt));
    this.a = paramInt;
    fieldSetFlags()[0] = true;
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\AdViewContainer$Builder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */