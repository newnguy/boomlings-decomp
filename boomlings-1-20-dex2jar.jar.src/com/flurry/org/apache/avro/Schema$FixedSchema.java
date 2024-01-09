package com.flurry.org.apache.avro;

import com.flurry.org.codehaus.jackson.JsonGenerator;

class Schema$FixedSchema extends Schema$NamedSchema {
  private final int size;
  
  public Schema$FixedSchema(Schema$Name paramSchema$Name, String paramString, int paramInt) {
    super(Schema$Type.FIXED, paramSchema$Name, paramString);
    if (paramInt < 0)
      throw new IllegalArgumentException("Invalid fixed size: " + paramInt); 
    this.size = paramInt;
  }
  
  int computeHash() {
    return super.computeHash() + this.size;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (!(paramObject instanceof Schema$FixedSchema))
        return false; 
      paramObject = paramObject;
      if (!equalCachedHash((Schema)paramObject) || !equalNames((Schema$NamedSchema)paramObject) || this.size != ((Schema$FixedSchema)paramObject).size || !this.props.equals(((Schema$FixedSchema)paramObject).props))
        bool = false; 
    } 
    return bool;
  }
  
  public int getFixedSize() {
    return this.size;
  }
  
  void toJson(Schema$Names paramSchema$Names, JsonGenerator paramJsonGenerator) {
    if (!writeNameRef(paramSchema$Names, paramJsonGenerator)) {
      paramJsonGenerator.writeStartObject();
      paramJsonGenerator.writeStringField("type", "fixed");
      writeName(paramSchema$Names, paramJsonGenerator);
      if (getDoc() != null)
        paramJsonGenerator.writeStringField("doc", getDoc()); 
      paramJsonGenerator.writeNumberField("size", this.size);
      this.props.write(paramJsonGenerator);
      aliasesToJson(paramJsonGenerator);
      paramJsonGenerator.writeEndObject();
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\Schema$FixedSchema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */