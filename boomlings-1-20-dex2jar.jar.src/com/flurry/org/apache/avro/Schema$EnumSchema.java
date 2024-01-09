package com.flurry.org.apache.avro;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class Schema$EnumSchema extends Schema$NamedSchema {
  private final Map ordinals;
  
  private final List symbols;
  
  public Schema$EnumSchema(Schema$Name paramSchema$Name, String paramString, Schema$LockableArrayList paramSchema$LockableArrayList) {
    super(Schema$Type.ENUM, paramSchema$Name, paramString);
    this.symbols = paramSchema$LockableArrayList.lock();
    this.ordinals = new HashMap<Object, Object>();
    Iterator<E> iterator = paramSchema$LockableArrayList.iterator();
    for (byte b = 0; iterator.hasNext(); b++) {
      paramString = (String)iterator.next();
      if (this.ordinals.put(Schema.access$200(paramString), Integer.valueOf(b)) != null)
        throw new SchemaParseException("Duplicate enum symbol: " + paramString); 
    } 
  }
  
  int computeHash() {
    return super.computeHash() + this.symbols.hashCode();
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (!(paramObject instanceof Schema$EnumSchema))
        return false; 
      paramObject = paramObject;
      if (!equalCachedHash((Schema)paramObject) || !equalNames((Schema$NamedSchema)paramObject) || !this.symbols.equals(((Schema$EnumSchema)paramObject).symbols) || !this.props.equals(((Schema$EnumSchema)paramObject).props))
        bool = false; 
    } 
    return bool;
  }
  
  public int getEnumOrdinal(String paramString) {
    return ((Integer)this.ordinals.get(paramString)).intValue();
  }
  
  public List getEnumSymbols() {
    return this.symbols;
  }
  
  public boolean hasEnumSymbol(String paramString) {
    return this.ordinals.containsKey(paramString);
  }
  
  void toJson(Schema$Names paramSchema$Names, JsonGenerator paramJsonGenerator) {
    if (!writeNameRef(paramSchema$Names, paramJsonGenerator)) {
      paramJsonGenerator.writeStartObject();
      paramJsonGenerator.writeStringField("type", "enum");
      writeName(paramSchema$Names, paramJsonGenerator);
      if (getDoc() != null)
        paramJsonGenerator.writeStringField("doc", getDoc()); 
      paramJsonGenerator.writeArrayFieldStart("symbols");
      Iterator<String> iterator = this.symbols.iterator();
      while (iterator.hasNext())
        paramJsonGenerator.writeString(iterator.next()); 
      paramJsonGenerator.writeEndArray();
      this.props.write(paramJsonGenerator);
      aliasesToJson(paramJsonGenerator);
      paramJsonGenerator.writeEndObject();
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\Schema$EnumSchema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */