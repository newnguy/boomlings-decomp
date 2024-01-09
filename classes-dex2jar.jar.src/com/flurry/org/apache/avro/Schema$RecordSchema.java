package com.flurry.org.apache.avro;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Schema$RecordSchema extends Schema$NamedSchema {
  private Map fieldMap;
  
  private List fields;
  
  private final boolean isError;
  
  public Schema$RecordSchema(Schema$Name paramSchema$Name, String paramString, boolean paramBoolean) {
    super(Schema$Type.RECORD, paramSchema$Name, paramString);
    this.isError = paramBoolean;
  }
  
  int computeHash() {
    int i;
    Map<Schema$RecordSchema, Schema$RecordSchema> map = Schema.access$1000().get();
    if (map.containsKey(this))
      return 0; 
    boolean bool = map.isEmpty();
    try {
      map.put(this, this);
      int j = super.computeHash();
      i = this.fields.hashCode();
      if (bool)
        map.clear(); 
      i = j + i;
    } finally {
      Exception exception;
    } 
    return i;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool1;
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof Schema$RecordSchema))
      return false; 
    Schema$RecordSchema schema$RecordSchema = (Schema$RecordSchema)paramObject;
    if (!equalCachedHash(schema$RecordSchema))
      return false; 
    if (!equalNames(schema$RecordSchema))
      return false; 
    if (!this.props.equals(schema$RecordSchema.props))
      return false; 
    Set<Schema$SeenPair> set = Schema.access$800().get();
    Schema$SeenPair schema$SeenPair = new Schema$SeenPair(this, paramObject, null);
    if (set.contains(schema$SeenPair))
      return true; 
    boolean bool2 = set.isEmpty();
    try {
      set.add(schema$SeenPair);
      bool1 = this.fields.equals(((Schema$RecordSchema)paramObject).fields);
      if (bool2)
        set.clear(); 
    } finally {}
    return bool1;
  }
  
  void fieldsToJson(Schema$Names paramSchema$Names, JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeStartArray();
    for (Schema$Field schema$Field : this.fields) {
      paramJsonGenerator.writeStartObject();
      paramJsonGenerator.writeStringField("name", schema$Field.name());
      paramJsonGenerator.writeFieldName("type");
      schema$Field.schema().toJson(paramSchema$Names, paramJsonGenerator);
      if (schema$Field.doc() != null)
        paramJsonGenerator.writeStringField("doc", schema$Field.doc()); 
      if (schema$Field.defaultValue() != null) {
        paramJsonGenerator.writeFieldName("default");
        paramJsonGenerator.writeTree(schema$Field.defaultValue());
      } 
      if (schema$Field.order() != Schema$Field$Order.ASCENDING)
        paramJsonGenerator.writeStringField("order", Schema$Field$Order.access$1200(schema$Field.order())); 
      if (Schema$Field.access$1300(schema$Field) != null && Schema$Field.access$1300(schema$Field).size() != 0) {
        paramJsonGenerator.writeFieldName("aliases");
        paramJsonGenerator.writeStartArray();
        Iterator<String> iterator = Schema$Field.access$1300(schema$Field).iterator();
        while (iterator.hasNext())
          paramJsonGenerator.writeString(iterator.next()); 
        paramJsonGenerator.writeEndArray();
      } 
      Schema$Field.access$1400(schema$Field).write(paramJsonGenerator);
      paramJsonGenerator.writeEndObject();
    } 
    paramJsonGenerator.writeEndArray();
  }
  
  public Schema$Field getField(String paramString) {
    if (this.fieldMap == null)
      throw new AvroRuntimeException("Schema fields not set yet"); 
    return (Schema$Field)this.fieldMap.get(paramString);
  }
  
  public List getFields() {
    if (this.fields == null)
      throw new AvroRuntimeException("Schema fields not set yet"); 
    return this.fields;
  }
  
  public boolean isError() {
    return this.isError;
  }
  
  public void setFields(List paramList) {
    if (this.fields != null)
      throw new AvroRuntimeException("Fields are already set"); 
    this.fieldMap = new HashMap<Object, Object>();
    Schema$LockableArrayList schema$LockableArrayList = new Schema$LockableArrayList();
    Iterator<Schema$Field> iterator = paramList.iterator();
    for (byte b = 0; iterator.hasNext(); b++) {
      Schema$Field schema$Field = iterator.next();
      if (Schema$Field.access$700(schema$Field) != -1)
        throw new AvroRuntimeException("Field already used: " + schema$Field); 
      Schema$Field.access$702(schema$Field, b);
      this.fieldMap.put(schema$Field.name(), schema$Field);
      schema$LockableArrayList.add(schema$Field);
    } 
    this.fields = schema$LockableArrayList.lock();
    this.hashCode = Integer.MIN_VALUE;
  }
  
  void toJson(Schema$Names paramSchema$Names, JsonGenerator paramJsonGenerator) {
    if (!writeNameRef(paramSchema$Names, paramJsonGenerator)) {
      String str1;
      String str2 = Schema$Names.access$1100(paramSchema$Names);
      paramJsonGenerator.writeStartObject();
      if (this.isError) {
        str1 = "error";
      } else {
        str1 = "record";
      } 
      paramJsonGenerator.writeStringField("type", str1);
      writeName(paramSchema$Names, paramJsonGenerator);
      Schema$Names.access$1102(paramSchema$Names, Schema$Name.access$600(this.name));
      if (getDoc() != null)
        paramJsonGenerator.writeStringField("doc", getDoc()); 
      paramJsonGenerator.writeFieldName("fields");
      fieldsToJson(paramSchema$Names, paramJsonGenerator);
      this.props.write(paramJsonGenerator);
      aliasesToJson(paramJsonGenerator);
      paramJsonGenerator.writeEndObject();
      Schema$Names.access$1102(paramSchema$Names, str2);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\Schema$RecordSchema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */