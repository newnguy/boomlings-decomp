package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;
import java.nio.ByteBuffer;

public class AdReportedId$Builder extends SpecificRecordBuilderBase implements RecordBuilder {
  private int a;
  
  private ByteBuffer b;
  
  private AdReportedId$Builder(byte paramByte) {
    super(AdReportedId.SCHEMA$);
  }
  
  public AdReportedId build() {
    try {
      int i;
      AdReportedId adReportedId = new AdReportedId();
      this();
      if (fieldSetFlags()[0]) {
        i = this.a;
      } else {
        i = ((Integer)defaultValue(fields()[0])).intValue();
      } 
      adReportedId.a = i;
      if (fieldSetFlags()[1]) {
        ByteBuffer byteBuffer1 = this.b;
        adReportedId.b = byteBuffer1;
        return adReportedId;
      } 
      ByteBuffer byteBuffer = (ByteBuffer)defaultValue(fields()[1]);
      adReportedId.b = byteBuffer;
      return adReportedId;
    } catch (Exception exception) {
      throw new AvroRuntimeException(exception);
    } 
  }
  
  public AdReportedId$Builder clearId() {
    this.b = null;
    fieldSetFlags()[1] = false;
    return this;
  }
  
  public AdReportedId$Builder clearType() {
    fieldSetFlags()[0] = false;
    return this;
  }
  
  public ByteBuffer getId() {
    return this.b;
  }
  
  public Integer getType() {
    return Integer.valueOf(this.a);
  }
  
  public boolean hasId() {
    return fieldSetFlags()[1];
  }
  
  public boolean hasType() {
    return fieldSetFlags()[0];
  }
  
  public AdReportedId$Builder setId(ByteBuffer paramByteBuffer) {
    validate(fields()[1], paramByteBuffer);
    this.b = paramByteBuffer;
    fieldSetFlags()[1] = true;
    return this;
  }
  
  public AdReportedId$Builder setType(int paramInt) {
    validate(fields()[0], Integer.valueOf(paramInt));
    this.a = paramInt;
    fieldSetFlags()[0] = true;
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\AdReportedId$Builder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */