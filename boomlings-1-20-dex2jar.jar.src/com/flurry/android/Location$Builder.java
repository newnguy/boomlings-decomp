package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;

public class Location$Builder extends SpecificRecordBuilderBase implements RecordBuilder {
  private float a;
  
  private float b;
  
  private Location$Builder(byte paramByte) {
    super(Location.SCHEMA$);
  }
  
  public Location build() {
    try {
      Location location = new Location();
      this();
      if (fieldSetFlags()[0]) {
        f = this.a;
      } else {
        f = ((Float)defaultValue(fields()[0])).floatValue();
      } 
      location.a = f;
      if (fieldSetFlags()[1]) {
        f = this.b;
        location.b = f;
        return location;
      } 
      float f = ((Float)defaultValue(fields()[1])).floatValue();
      location.b = f;
      return location;
    } catch (Exception exception) {
      throw new AvroRuntimeException(exception);
    } 
  }
  
  public Location$Builder clearLat() {
    fieldSetFlags()[0] = false;
    return this;
  }
  
  public Location$Builder clearLon() {
    fieldSetFlags()[1] = false;
    return this;
  }
  
  public Float getLat() {
    return Float.valueOf(this.a);
  }
  
  public Float getLon() {
    return Float.valueOf(this.b);
  }
  
  public boolean hasLat() {
    return fieldSetFlags()[0];
  }
  
  public boolean hasLon() {
    return fieldSetFlags()[1];
  }
  
  public Location$Builder setLat(float paramFloat) {
    validate(fields()[0], Float.valueOf(paramFloat));
    this.a = paramFloat;
    fieldSetFlags()[0] = true;
    return this;
  }
  
  public Location$Builder setLon(float paramFloat) {
    validate(fields()[1], Float.valueOf(paramFloat));
    this.b = paramFloat;
    fieldSetFlags()[1] = true;
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\Location$Builder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */