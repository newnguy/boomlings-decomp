package com.flurry.org.apache.avro;

import com.flurry.org.codehaus.jackson.JsonFactory;
import com.flurry.org.codehaus.jackson.JsonParseException;
import com.flurry.org.codehaus.jackson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Schema$Parser {
  private Schema$Names names = new Schema$Names();
  
  private boolean validate = true;
  
  private Schema parse(JsonParser paramJsonParser) {
    boolean bool = ((Boolean)Schema.access$1500().get()).booleanValue();
    try {
      Schema.access$1500().set(Boolean.valueOf(this.validate));
      return Schema.parse(Schema.MAPPER.readTree(paramJsonParser), this.names);
    } catch (JsonParseException jsonParseException) {
      SchemaParseException schemaParseException = new SchemaParseException();
      this((Throwable)jsonParseException);
      throw schemaParseException;
    } finally {
      Schema.access$1500().set(Boolean.valueOf(bool));
    } 
  }
  
  public Schema$Parser addTypes(Map paramMap) {
    for (Schema schema : paramMap.values())
      this.names.add(schema); 
    return this;
  }
  
  public Map getTypes() {
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    for (Schema schema : this.names.values())
      linkedHashMap.put(schema.getFullName(), schema); 
    return linkedHashMap;
  }
  
  public boolean getValidate() {
    return this.validate;
  }
  
  public Schema parse(File paramFile) {
    return parse(Schema.FACTORY.createJsonParser(paramFile));
  }
  
  public Schema parse(InputStream paramInputStream) {
    return parse(Schema.FACTORY.createJsonParser(paramInputStream));
  }
  
  public Schema parse(String paramString) {
    try {
      JsonFactory jsonFactory = Schema.FACTORY;
      StringReader stringReader = new StringReader();
      this(paramString);
      return parse(jsonFactory.createJsonParser(stringReader));
    } catch (IOException iOException) {
      throw new SchemaParseException(iOException);
    } 
  }
  
  public Schema$Parser setValidate(boolean paramBoolean) {
    this.validate = paramBoolean;
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\Schema$Parser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */