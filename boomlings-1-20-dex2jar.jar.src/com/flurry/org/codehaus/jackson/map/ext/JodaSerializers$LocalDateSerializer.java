package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.lang.reflect.Type;
import org.joda.time.LocalDate;
import org.joda.time.ReadablePartial;

public final class JodaSerializers$LocalDateSerializer extends JodaSerializers$JodaSerializer {
  public JodaSerializers$LocalDateSerializer() {
    super(LocalDate.class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    if (paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
      String str1 = "array";
      return (JsonNode)createSchemaNode(str1, true);
    } 
    String str = "string";
    return (JsonNode)createSchemaNode(str, true);
  }
  
  public void serialize(LocalDate paramLocalDate, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    if (paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
      paramJsonGenerator.writeStartArray();
      paramJsonGenerator.writeNumber(paramLocalDate.year().get());
      paramJsonGenerator.writeNumber(paramLocalDate.monthOfYear().get());
      paramJsonGenerator.writeNumber(paramLocalDate.dayOfMonth().get());
      paramJsonGenerator.writeEndArray();
      return;
    } 
    paramJsonGenerator.writeString(printLocalDate((ReadablePartial)paramLocalDate));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\JodaSerializers$LocalDateSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */