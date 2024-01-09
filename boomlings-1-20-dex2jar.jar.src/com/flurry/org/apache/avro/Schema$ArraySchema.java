package com.flurry.org.apache.avro;

import com.flurry.org.codehaus.jackson.JsonGenerator;

class Schema$ArraySchema extends Schema {
  private final Schema elementType;
  
  public Schema$ArraySchema(Schema paramSchema) {
    super(Schema$Type.ARRAY);
    this.elementType = paramSchema;
  }
  
  int computeHash() {
    return super.computeHash() + this.elementType.computeHash();
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (!(paramObject instanceof Schema$ArraySchema))
        return false; 
      paramObject = paramObject;
      if (!equalCachedHash((Schema)paramObject) || !this.elementType.equals(((Schema$ArraySchema)paramObject).elementType) || !this.props.equals(((Schema$ArraySchema)paramObject).props))
        bool = false; 
    } 
    return bool;
  }
  
  public Schema getElementType() {
    return this.elementType;
  }
  
  void toJson(Schema$Names paramSchema$Names, JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeStartObject();
    paramJsonGenerator.writeStringField("type", "array");
    paramJsonGenerator.writeFieldName("items");
    this.elementType.toJson(paramSchema$Names, paramJsonGenerator);
    this.props.write(paramJsonGenerator);
    paramJsonGenerator.writeEndObject();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\Schema$ArraySchema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */