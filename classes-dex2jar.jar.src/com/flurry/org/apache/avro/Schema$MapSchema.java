package com.flurry.org.apache.avro;

import com.flurry.org.codehaus.jackson.JsonGenerator;

class Schema$MapSchema extends Schema {
  private final Schema valueType;
  
  public Schema$MapSchema(Schema paramSchema) {
    super(Schema$Type.MAP);
    this.valueType = paramSchema;
  }
  
  int computeHash() {
    return super.computeHash() + this.valueType.computeHash();
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (!(paramObject instanceof Schema$MapSchema))
        return false; 
      paramObject = paramObject;
      if (!equalCachedHash((Schema)paramObject) || !this.valueType.equals(((Schema$MapSchema)paramObject).valueType) || !this.props.equals(((Schema$MapSchema)paramObject).props))
        bool = false; 
    } 
    return bool;
  }
  
  public Schema getValueType() {
    return this.valueType;
  }
  
  void toJson(Schema$Names paramSchema$Names, JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeStartObject();
    paramJsonGenerator.writeStringField("type", "map");
    paramJsonGenerator.writeFieldName("values");
    this.valueType.toJson(paramSchema$Names, paramJsonGenerator);
    this.props.write(paramJsonGenerator);
    paramJsonGenerator.writeEndObject();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\Schema$MapSchema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */