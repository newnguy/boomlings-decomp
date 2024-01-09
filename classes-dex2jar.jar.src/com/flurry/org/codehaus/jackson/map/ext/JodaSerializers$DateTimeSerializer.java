package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.lang.reflect.Type;
import org.joda.time.DateTime;

public final class JodaSerializers$DateTimeSerializer extends JodaSerializers$JodaSerializer {
  public JodaSerializers$DateTimeSerializer() {
    super(DateTime.class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    if (paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
      String str1 = "number";
      return (JsonNode)createSchemaNode(str1, true);
    } 
    String str = "string";
    return (JsonNode)createSchemaNode(str, true);
  }
  
  public void serialize(DateTime paramDateTime, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    if (paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
      paramJsonGenerator.writeNumber(paramDateTime.getMillis());
      return;
    } 
    paramJsonGenerator.writeString(paramDateTime.toString());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\JodaSerializers$DateTimeSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */