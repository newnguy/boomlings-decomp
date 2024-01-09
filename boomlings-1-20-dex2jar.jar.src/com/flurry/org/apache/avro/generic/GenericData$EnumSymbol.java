package com.flurry.org.apache.avro.generic;

import com.flurry.org.apache.avro.Schema;

public class GenericData$EnumSymbol implements GenericEnumSymbol {
  private Schema schema;
  
  private String symbol;
  
  public GenericData$EnumSymbol(Schema paramSchema, String paramString) {
    this.schema = paramSchema;
    this.symbol = paramString;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this && (!(paramObject instanceof GenericEnumSymbol) || !this.symbol.equals(paramObject.toString())))
      bool = false; 
    return bool;
  }
  
  public Schema getSchema() {
    return this.schema;
  }
  
  public int hashCode() {
    return this.symbol.hashCode();
  }
  
  public String toString() {
    return this.symbol;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\generic\GenericData$EnumSymbol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */