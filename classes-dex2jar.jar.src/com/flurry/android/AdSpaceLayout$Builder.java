package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;

public class AdSpaceLayout$Builder extends SpecificRecordBuilderBase implements RecordBuilder {
  private int a;
  
  private int b;
  
  private CharSequence c;
  
  private CharSequence d;
  
  private CharSequence e;
  
  private AdSpaceLayout$Builder() {
    super(AdSpaceLayout.SCHEMA$);
  }
  
  public AdSpaceLayout build() {
    try {
      int i;
      AdSpaceLayout adSpaceLayout = new AdSpaceLayout();
      this();
      if (fieldSetFlags()[0]) {
        i = this.a;
      } else {
        i = ((Integer)defaultValue(fields()[0])).intValue();
      } 
      adSpaceLayout.a = i;
      if (fieldSetFlags()[1]) {
        i = this.b;
      } else {
        i = ((Integer)defaultValue(fields()[1])).intValue();
      } 
      adSpaceLayout.b = i;
      if (fieldSetFlags()[2]) {
        charSequence = this.c;
      } else {
        charSequence = (CharSequence)defaultValue(fields()[2]);
      } 
      adSpaceLayout.c = charSequence;
      if (fieldSetFlags()[3]) {
        charSequence = this.d;
      } else {
        charSequence = (CharSequence)defaultValue(fields()[3]);
      } 
      adSpaceLayout.d = charSequence;
      if (fieldSetFlags()[4]) {
        charSequence = this.e;
        adSpaceLayout.e = charSequence;
        return adSpaceLayout;
      } 
      CharSequence charSequence = (CharSequence)defaultValue(fields()[4]);
      adSpaceLayout.e = charSequence;
      return adSpaceLayout;
    } catch (Exception exception) {
      throw new AvroRuntimeException(exception);
    } 
  }
  
  public AdSpaceLayout$Builder clearAdHeight() {
    fieldSetFlags()[1] = false;
    return this;
  }
  
  public AdSpaceLayout$Builder clearAdWidth() {
    fieldSetFlags()[0] = false;
    return this;
  }
  
  public AdSpaceLayout$Builder clearAlignment() {
    this.e = null;
    fieldSetFlags()[4] = false;
    return this;
  }
  
  public AdSpaceLayout$Builder clearFix() {
    this.c = null;
    fieldSetFlags()[2] = false;
    return this;
  }
  
  public AdSpaceLayout$Builder clearFormat() {
    this.d = null;
    fieldSetFlags()[3] = false;
    return this;
  }
  
  public Integer getAdHeight() {
    return Integer.valueOf(this.b);
  }
  
  public Integer getAdWidth() {
    return Integer.valueOf(this.a);
  }
  
  public CharSequence getAlignment() {
    return this.e;
  }
  
  public CharSequence getFix() {
    return this.c;
  }
  
  public CharSequence getFormat() {
    return this.d;
  }
  
  public boolean hasAdHeight() {
    return fieldSetFlags()[1];
  }
  
  public boolean hasAdWidth() {
    return fieldSetFlags()[0];
  }
  
  public boolean hasAlignment() {
    return fieldSetFlags()[4];
  }
  
  public boolean hasFix() {
    return fieldSetFlags()[2];
  }
  
  public boolean hasFormat() {
    return fieldSetFlags()[3];
  }
  
  public AdSpaceLayout$Builder setAdHeight(int paramInt) {
    validate(fields()[1], Integer.valueOf(paramInt));
    this.b = paramInt;
    fieldSetFlags()[1] = true;
    return this;
  }
  
  public AdSpaceLayout$Builder setAdWidth(int paramInt) {
    validate(fields()[0], Integer.valueOf(paramInt));
    this.a = paramInt;
    fieldSetFlags()[0] = true;
    return this;
  }
  
  public AdSpaceLayout$Builder setAlignment(CharSequence paramCharSequence) {
    validate(fields()[4], paramCharSequence);
    this.e = paramCharSequence;
    fieldSetFlags()[4] = true;
    return this;
  }
  
  public AdSpaceLayout$Builder setFix(CharSequence paramCharSequence) {
    validate(fields()[2], paramCharSequence);
    this.c = paramCharSequence;
    fieldSetFlags()[2] = true;
    return this;
  }
  
  public AdSpaceLayout$Builder setFormat(CharSequence paramCharSequence) {
    validate(fields()[3], paramCharSequence);
    this.d = paramCharSequence;
    fieldSetFlags()[3] = true;
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\AdSpaceLayout$Builder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */