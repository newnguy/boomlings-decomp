package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.io.File;
import java.lang.reflect.Type;

public final class StdJdkSerializers$FileSerializer extends ScalarSerializerBase {
  public StdJdkSerializers$FileSerializer() {
    super(File.class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    return (JsonNode)createSchemaNode("string", true);
  }
  
  public void serialize(File paramFile, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeString(paramFile.getAbsolutePath());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StdJdkSerializers$FileSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */