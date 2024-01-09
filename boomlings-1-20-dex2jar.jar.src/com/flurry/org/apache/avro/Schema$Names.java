package com.flurry.org.apache.avro;

import java.util.LinkedHashMap;

class Schema$Names extends LinkedHashMap {
  private String space;
  
  public Schema$Names() {}
  
  public Schema$Names(String paramString) {
    this.space = paramString;
  }
  
  public void add(Schema paramSchema) {
    put(((Schema$NamedSchema)paramSchema).name, paramSchema);
  }
  
  public boolean contains(Schema paramSchema) {
    return (get(((Schema$NamedSchema)paramSchema).name) != null);
  }
  
  public Schema get(Object paramObject) {
    if (paramObject instanceof String) {
      Schema$Type schema$Type = (Schema$Type)Schema.PRIMITIVES.get(paramObject);
      if (schema$Type != null)
        return Schema.create(schema$Type); 
      paramObject = new Schema$Name((String)paramObject, this.space);
    } else {
      paramObject = paramObject;
    } 
    return super.get(paramObject);
  }
  
  public Schema put(Schema$Name paramSchema$Name, Schema paramSchema) {
    if (containsKey(paramSchema$Name))
      throw new SchemaParseException("Can't redefine: " + paramSchema$Name); 
    return super.put(paramSchema$Name, paramSchema);
  }
  
  public String space() {
    return this.space;
  }
  
  public void space(String paramString) {
    this.space = paramString;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\Schema$Names.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */