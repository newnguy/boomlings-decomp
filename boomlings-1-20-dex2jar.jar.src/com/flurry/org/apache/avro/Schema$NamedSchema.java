package com.flurry.org.apache.avro;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

abstract class Schema$NamedSchema extends Schema {
  Set aliases;
  
  final String doc;
  
  final Schema$Name name;
  
  public Schema$NamedSchema(Schema$Type paramSchema$Type, Schema$Name paramSchema$Name, String paramString) {
    super(paramSchema$Type);
    this.name = paramSchema$Name;
    this.doc = paramString;
    if (PRIMITIVES.containsKey(Schema$Name.access$400(paramSchema$Name)))
      throw new AvroTypeException("Schemas may not be named after primitives: " + Schema$Name.access$400(paramSchema$Name)); 
  }
  
  public void addAlias(String paramString) {
    if (this.aliases == null)
      this.aliases = new LinkedHashSet(); 
    this.aliases.add(new Schema$Name(paramString, Schema$Name.access$600(this.name)));
  }
  
  public void aliasesToJson(JsonGenerator paramJsonGenerator) {
    if (this.aliases != null && this.aliases.size() != 0) {
      paramJsonGenerator.writeFieldName("aliases");
      paramJsonGenerator.writeStartArray();
      Iterator<Schema$Name> iterator = this.aliases.iterator();
      while (iterator.hasNext())
        paramJsonGenerator.writeString(((Schema$Name)iterator.next()).getQualified(Schema$Name.access$600(this.name))); 
      paramJsonGenerator.writeEndArray();
    } 
  }
  
  int computeHash() {
    return super.computeHash() + this.name.hashCode();
  }
  
  public boolean equalNames(Schema$NamedSchema paramSchema$NamedSchema) {
    return this.name.equals(paramSchema$NamedSchema.name);
  }
  
  public Set getAliases() {
    LinkedHashSet<String> linkedHashSet = new LinkedHashSet();
    if (this.aliases != null) {
      Iterator<Schema$Name> iterator = this.aliases.iterator();
      while (iterator.hasNext())
        linkedHashSet.add(Schema$Name.access$400(iterator.next())); 
    } 
    return linkedHashSet;
  }
  
  public String getDoc() {
    return this.doc;
  }
  
  public String getFullName() {
    return Schema$Name.access$400(this.name);
  }
  
  public String getName() {
    return Schema$Name.access$500(this.name);
  }
  
  public String getNamespace() {
    return Schema$Name.access$600(this.name);
  }
  
  public void writeName(Schema$Names paramSchema$Names, JsonGenerator paramJsonGenerator) {
    this.name.writeName(paramSchema$Names, paramJsonGenerator);
  }
  
  public boolean writeNameRef(Schema$Names paramSchema$Names, JsonGenerator paramJsonGenerator) {
    if (equals(paramSchema$Names.get(this.name))) {
      paramJsonGenerator.writeString(this.name.getQualified(paramSchema$Names.space()));
      return true;
    } 
    if (Schema$Name.access$500(this.name) != null)
      paramSchema$Names.put(this.name, this); 
    return false;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\Schema$NamedSchema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */