package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;
import java.util.List;

public class AdFrame$Builder extends SpecificRecordBuilderBase implements RecordBuilder {
  private int a;
  
  private CharSequence b;
  
  private CharSequence c;
  
  private AdSpaceLayout d;
  
  private List e;
  
  private CharSequence f;
  
  private AdFrame$Builder() {
    super(AdFrame.SCHEMA$);
  }
  
  public AdFrame build() {
    try {
      int i;
      CharSequence charSequence2;
      AdSpaceLayout adSpaceLayout;
      List list;
      AdFrame adFrame = new AdFrame();
      this();
      if (fieldSetFlags()[0]) {
        i = this.a;
      } else {
        i = ((Integer)defaultValue(fields()[0])).intValue();
      } 
      adFrame.a = i;
      if (fieldSetFlags()[1]) {
        charSequence2 = this.b;
      } else {
        charSequence2 = (CharSequence)defaultValue(fields()[1]);
      } 
      adFrame.b = charSequence2;
      if (fieldSetFlags()[2]) {
        charSequence2 = this.c;
      } else {
        charSequence2 = (CharSequence)defaultValue(fields()[2]);
      } 
      adFrame.c = charSequence2;
      if (fieldSetFlags()[3]) {
        adSpaceLayout = this.d;
      } else {
        adSpaceLayout = (AdSpaceLayout)defaultValue(fields()[3]);
      } 
      adFrame.d = adSpaceLayout;
      if (fieldSetFlags()[4]) {
        list = this.e;
      } else {
        list = (List)defaultValue(fields()[4]);
      } 
      adFrame.e = list;
      if (fieldSetFlags()[5]) {
        CharSequence charSequence = this.f;
        adFrame.f = charSequence;
        return adFrame;
      } 
      CharSequence charSequence1 = (CharSequence)defaultValue(fields()[5]);
      adFrame.f = charSequence1;
      return adFrame;
    } catch (Exception exception) {
      throw new AvroRuntimeException(exception);
    } 
  }
  
  public AdFrame$Builder clearAdGuid() {
    this.f = null;
    fieldSetFlags()[5] = false;
    return this;
  }
  
  public AdFrame$Builder clearAdSpaceLayout() {
    this.d = null;
    fieldSetFlags()[3] = false;
    return this;
  }
  
  public AdFrame$Builder clearBinding() {
    fieldSetFlags()[0] = false;
    return this;
  }
  
  public AdFrame$Builder clearCallbacks() {
    this.e = null;
    fieldSetFlags()[4] = false;
    return this;
  }
  
  public AdFrame$Builder clearContent() {
    this.c = null;
    fieldSetFlags()[2] = false;
    return this;
  }
  
  public AdFrame$Builder clearDisplay() {
    this.b = null;
    fieldSetFlags()[1] = false;
    return this;
  }
  
  public CharSequence getAdGuid() {
    return this.f;
  }
  
  public AdSpaceLayout getAdSpaceLayout() {
    return this.d;
  }
  
  public Integer getBinding() {
    return Integer.valueOf(this.a);
  }
  
  public List getCallbacks() {
    return this.e;
  }
  
  public CharSequence getContent() {
    return this.c;
  }
  
  public CharSequence getDisplay() {
    return this.b;
  }
  
  public boolean hasAdGuid() {
    return fieldSetFlags()[5];
  }
  
  public boolean hasAdSpaceLayout() {
    return fieldSetFlags()[3];
  }
  
  public boolean hasBinding() {
    return fieldSetFlags()[0];
  }
  
  public boolean hasCallbacks() {
    return fieldSetFlags()[4];
  }
  
  public boolean hasContent() {
    return fieldSetFlags()[2];
  }
  
  public boolean hasDisplay() {
    return fieldSetFlags()[1];
  }
  
  public AdFrame$Builder setAdGuid(CharSequence paramCharSequence) {
    validate(fields()[5], paramCharSequence);
    this.f = paramCharSequence;
    fieldSetFlags()[5] = true;
    return this;
  }
  
  public AdFrame$Builder setAdSpaceLayout(AdSpaceLayout paramAdSpaceLayout) {
    validate(fields()[3], paramAdSpaceLayout);
    this.d = paramAdSpaceLayout;
    fieldSetFlags()[3] = true;
    return this;
  }
  
  public AdFrame$Builder setBinding(int paramInt) {
    validate(fields()[0], Integer.valueOf(paramInt));
    this.a = paramInt;
    fieldSetFlags()[0] = true;
    return this;
  }
  
  public AdFrame$Builder setCallbacks(List paramList) {
    validate(fields()[4], paramList);
    this.e = paramList;
    fieldSetFlags()[4] = true;
    return this;
  }
  
  public AdFrame$Builder setContent(CharSequence paramCharSequence) {
    validate(fields()[2], paramCharSequence);
    this.c = paramCharSequence;
    fieldSetFlags()[2] = true;
    return this;
  }
  
  public AdFrame$Builder setDisplay(CharSequence paramCharSequence) {
    validate(fields()[1], paramCharSequence);
    this.b = paramCharSequence;
    fieldSetFlags()[1] = true;
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\AdFrame$Builder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */