package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;
import java.util.List;

public class AdUnit$Builder extends SpecificRecordBuilderBase implements RecordBuilder {
  private CharSequence a;
  
  private long b;
  
  private List c;
  
  private int d;
  
  private AdUnit$Builder() {
    super(AdUnit.SCHEMA$);
  }
  
  public AdUnit build() {
    try {
      long l;
      CharSequence charSequence;
      List list;
      AdUnit adUnit = new AdUnit();
      this();
      if (fieldSetFlags()[0]) {
        charSequence = this.a;
      } else {
        charSequence = (CharSequence)defaultValue(fields()[0]);
      } 
      adUnit.a = charSequence;
      if (fieldSetFlags()[1]) {
        l = this.b;
      } else {
        l = ((Long)defaultValue(fields()[1])).longValue();
      } 
      adUnit.b = l;
      if (fieldSetFlags()[2]) {
        list = this.c;
      } else {
        list = (List)defaultValue(fields()[2]);
      } 
      adUnit.c = list;
      if (fieldSetFlags()[3]) {
        int j = this.d;
        adUnit.d = j;
        return adUnit;
      } 
      int i = ((Integer)defaultValue(fields()[3])).intValue();
      adUnit.d = i;
      return adUnit;
    } catch (Exception exception) {
      throw new AvroRuntimeException(exception);
    } 
  }
  
  public AdUnit$Builder clearAdFrames() {
    this.c = null;
    fieldSetFlags()[2] = false;
    return this;
  }
  
  public AdUnit$Builder clearAdSpace() {
    this.a = null;
    fieldSetFlags()[0] = false;
    return this;
  }
  
  public AdUnit$Builder clearCombinable() {
    fieldSetFlags()[3] = false;
    return this;
  }
  
  public AdUnit$Builder clearExpiration() {
    fieldSetFlags()[1] = false;
    return this;
  }
  
  public List getAdFrames() {
    return this.c;
  }
  
  public CharSequence getAdSpace() {
    return this.a;
  }
  
  public Integer getCombinable() {
    return Integer.valueOf(this.d);
  }
  
  public Long getExpiration() {
    return Long.valueOf(this.b);
  }
  
  public boolean hasAdFrames() {
    return fieldSetFlags()[2];
  }
  
  public boolean hasAdSpace() {
    return fieldSetFlags()[0];
  }
  
  public boolean hasCombinable() {
    return fieldSetFlags()[3];
  }
  
  public boolean hasExpiration() {
    return fieldSetFlags()[1];
  }
  
  public AdUnit$Builder setAdFrames(List paramList) {
    validate(fields()[2], paramList);
    this.c = paramList;
    fieldSetFlags()[2] = true;
    return this;
  }
  
  public AdUnit$Builder setAdSpace(CharSequence paramCharSequence) {
    validate(fields()[0], paramCharSequence);
    this.a = paramCharSequence;
    fieldSetFlags()[0] = true;
    return this;
  }
  
  public AdUnit$Builder setCombinable(int paramInt) {
    validate(fields()[3], Integer.valueOf(paramInt));
    this.d = paramInt;
    fieldSetFlags()[3] = true;
    return this;
  }
  
  public AdUnit$Builder setExpiration(long paramLong) {
    validate(fields()[1], Long.valueOf(paramLong));
    this.b = paramLong;
    fieldSetFlags()[1] = true;
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\AdUnit$Builder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */