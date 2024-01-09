package com.flurry.org.apache.avro.data;

import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.DatumWriter;
import com.flurry.org.apache.avro.io.Encoder;
import com.flurry.org.codehaus.jackson.JsonNode;

public class Json$Writer implements DatumWriter {
  public void setSchema(Schema paramSchema) {
    if (!Json.SCHEMA.equals(paramSchema))
      throw new RuntimeException("Not the Json schema: " + paramSchema); 
  }
  
  public void write(JsonNode paramJsonNode, Encoder paramEncoder) {
    Json.write(paramJsonNode, paramEncoder);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\data\Json$Writer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */