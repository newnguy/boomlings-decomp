package com.flurry.org.codehaus.jackson.util;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.PrettyPrinter;

public class MinimalPrettyPrinter implements PrettyPrinter {
  public static final String DEFAULT_ROOT_VALUE_SEPARATOR = " ";
  
  protected String _rootValueSeparator = " ";
  
  public MinimalPrettyPrinter() {
    this(" ");
  }
  
  public MinimalPrettyPrinter(String paramString) {
    this._rootValueSeparator = paramString;
  }
  
  public void beforeArrayValues(JsonGenerator paramJsonGenerator) {}
  
  public void beforeObjectEntries(JsonGenerator paramJsonGenerator) {}
  
  public void setRootValueSeparator(String paramString) {
    this._rootValueSeparator = paramString;
  }
  
  public void writeArrayValueSeparator(JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeRaw(',');
  }
  
  public void writeEndArray(JsonGenerator paramJsonGenerator, int paramInt) {
    paramJsonGenerator.writeRaw(']');
  }
  
  public void writeEndObject(JsonGenerator paramJsonGenerator, int paramInt) {
    paramJsonGenerator.writeRaw('}');
  }
  
  public void writeObjectEntrySeparator(JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeRaw(',');
  }
  
  public void writeObjectFieldValueSeparator(JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeRaw(':');
  }
  
  public void writeRootValueSeparator(JsonGenerator paramJsonGenerator) {
    if (this._rootValueSeparator != null)
      paramJsonGenerator.writeRaw(this._rootValueSeparator); 
  }
  
  public void writeStartArray(JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeRaw('[');
  }
  
  public void writeStartObject(JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeRaw('{');
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackso\\util\MinimalPrettyPrinter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */