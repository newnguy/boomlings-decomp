package com.flurry.org.apache.avro;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class Schema$UnionSchema extends Schema {
  private final Map indexByName = new HashMap<Object, Object>();
  
  private final List types;
  
  public Schema$UnionSchema(Schema$LockableArrayList paramSchema$LockableArrayList) {
    super(Schema$Type.UNION);
    this.types = paramSchema$LockableArrayList.lock();
    Iterator<E> iterator = paramSchema$LockableArrayList.iterator();
    for (byte b = 0; iterator.hasNext(); b++) {
      Schema schema = (Schema)iterator.next();
      if (schema.getType() == Schema$Type.UNION)
        throw new AvroRuntimeException("Nested union: " + this); 
      String str = schema.getFullName();
      if (str == null)
        throw new AvroRuntimeException("Nameless in union:" + this); 
      if (this.indexByName.put(str, Integer.valueOf(b)) != null)
        throw new AvroRuntimeException("Duplicate in union:" + str); 
    } 
  }
  
  public void addProp(String paramString1, String paramString2) {
    throw new AvroRuntimeException("Can't set properties on a union: " + this);
  }
  
  int computeHash() {
    int i = super.computeHash();
    Iterator<Schema> iterator = this.types.iterator();
    while (iterator.hasNext())
      i = ((Schema)iterator.next()).computeHash() + i; 
    return i;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (!(paramObject instanceof Schema$UnionSchema))
        return false; 
      paramObject = paramObject;
      if (!equalCachedHash((Schema)paramObject) || !this.types.equals(((Schema$UnionSchema)paramObject).types) || !this.props.equals(((Schema$UnionSchema)paramObject).props))
        bool = false; 
    } 
    return bool;
  }
  
  public Integer getIndexNamed(String paramString) {
    return (Integer)this.indexByName.get(paramString);
  }
  
  public List getTypes() {
    return this.types;
  }
  
  void toJson(Schema$Names paramSchema$Names, JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeStartArray();
    Iterator<Schema> iterator = this.types.iterator();
    while (iterator.hasNext())
      ((Schema)iterator.next()).toJson(paramSchema$Names, paramJsonGenerator); 
    paramJsonGenerator.writeEndArray();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\Schema$UnionSchema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */