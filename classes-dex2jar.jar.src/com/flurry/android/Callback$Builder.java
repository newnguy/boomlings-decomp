package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;
import java.util.List;

public class Callback$Builder extends SpecificRecordBuilderBase implements RecordBuilder {
  private CharSequence a;
  
  private List b;
  
  private Callback$Builder() {
    super(Callback.SCHEMA$);
  }
  
  public Callback build() {
    try {
      CharSequence charSequence;
      Callback callback = new Callback();
      this();
      if (fieldSetFlags()[0]) {
        charSequence = this.a;
      } else {
        charSequence = (CharSequence)defaultValue(fields()[0]);
      } 
      callback.a = charSequence;
      if (fieldSetFlags()[1]) {
        List list1 = this.b;
        callback.b = list1;
        return callback;
      } 
      List list = (List)defaultValue(fields()[1]);
      callback.b = list;
      return callback;
    } catch (Exception exception) {
      throw new AvroRuntimeException(exception);
    } 
  }
  
  public Callback$Builder clearActions() {
    this.b = null;
    fieldSetFlags()[1] = false;
    return this;
  }
  
  public Callback$Builder clearEvent() {
    this.a = null;
    fieldSetFlags()[0] = false;
    return this;
  }
  
  public List getActions() {
    return this.b;
  }
  
  public CharSequence getEvent() {
    return this.a;
  }
  
  public boolean hasActions() {
    return fieldSetFlags()[1];
  }
  
  public boolean hasEvent() {
    return fieldSetFlags()[0];
  }
  
  public Callback$Builder setActions(List paramList) {
    validate(fields()[1], paramList);
    this.b = paramList;
    fieldSetFlags()[1] = true;
    return this;
  }
  
  public Callback$Builder setEvent(CharSequence paramCharSequence) {
    validate(fields()[0], paramCharSequence);
    this.a = paramCharSequence;
    fieldSetFlags()[0] = true;
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\Callback$Builder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */