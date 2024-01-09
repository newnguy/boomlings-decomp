package com.flurry.org.apache.avro.generic;

import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.BinaryData;
import java.util.Arrays;

public class GenericData$Fixed implements GenericFixed, Comparable {
  private byte[] bytes;
  
  private Schema schema;
  
  protected GenericData$Fixed() {}
  
  public GenericData$Fixed(Schema paramSchema) {
    setSchema(paramSchema);
  }
  
  public GenericData$Fixed(Schema paramSchema, byte[] paramArrayOfbyte) {
    this.schema = paramSchema;
    this.bytes = paramArrayOfbyte;
  }
  
  public void bytes(byte[] paramArrayOfbyte) {
    this.bytes = paramArrayOfbyte;
  }
  
  public byte[] bytes() {
    return this.bytes;
  }
  
  public int compareTo(GenericData$Fixed paramGenericData$Fixed) {
    return BinaryData.compareBytes(this.bytes, 0, this.bytes.length, paramGenericData$Fixed.bytes, 0, paramGenericData$Fixed.bytes.length);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this && (!(paramObject instanceof GenericFixed) || !Arrays.equals(this.bytes, ((GenericFixed)paramObject).bytes())))
      bool = false; 
    return bool;
  }
  
  public Schema getSchema() {
    return this.schema;
  }
  
  public int hashCode() {
    return Arrays.hashCode(this.bytes);
  }
  
  protected void setSchema(Schema paramSchema) {
    this.schema = paramSchema;
    this.bytes = new byte[paramSchema.getFixedSize()];
  }
  
  public String toString() {
    return Arrays.toString(this.bytes);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\generic\GenericData$Fixed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */