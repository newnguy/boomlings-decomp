package com.flurry.org.codehaus.jackson;

public interface PrettyPrinter {
  void beforeArrayValues(JsonGenerator paramJsonGenerator);
  
  void beforeObjectEntries(JsonGenerator paramJsonGenerator);
  
  void writeArrayValueSeparator(JsonGenerator paramJsonGenerator);
  
  void writeEndArray(JsonGenerator paramJsonGenerator, int paramInt);
  
  void writeEndObject(JsonGenerator paramJsonGenerator, int paramInt);
  
  void writeObjectEntrySeparator(JsonGenerator paramJsonGenerator);
  
  void writeObjectFieldValueSeparator(JsonGenerator paramJsonGenerator);
  
  void writeRootValueSeparator(JsonGenerator paramJsonGenerator);
  
  void writeStartArray(JsonGenerator paramJsonGenerator);
  
  void writeStartObject(JsonGenerator paramJsonGenerator);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\PrettyPrinter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */