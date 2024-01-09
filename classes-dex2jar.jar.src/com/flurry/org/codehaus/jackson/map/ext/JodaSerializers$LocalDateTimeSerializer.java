package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.lang.reflect.Type;
import org.joda.time.LocalDateTime;
import org.joda.time.ReadablePartial;

public final class JodaSerializers$LocalDateTimeSerializer extends JodaSerializers$JodaSerializer {
  public JodaSerializers$LocalDateTimeSerializer() {
    super(LocalDateTime.class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    if (paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
      String str1 = "array";
      return (JsonNode)createSchemaNode(str1, true);
    } 
    String str = "string";
    return (JsonNode)createSchemaNode(str, true);
  }
  
  public void serialize(LocalDateTime paramLocalDateTime, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    if (paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
      paramJsonGenerator.writeStartArray();
      paramJsonGenerator.writeNumber(paramLocalDateTime.year().get());
      paramJsonGenerator.writeNumber(paramLocalDateTime.monthOfYear().get());
      paramJsonGenerator.writeNumber(paramLocalDateTime.dayOfMonth().get());
      paramJsonGenerator.writeNumber(paramLocalDateTime.hourOfDay().get());
      paramJsonGenerator.writeNumber(paramLocalDateTime.minuteOfHour().get());
      paramJsonGenerator.writeNumber(paramLocalDateTime.secondOfMinute().get());
      paramJsonGenerator.writeNumber(paramLocalDateTime.millisOfSecond().get());
      paramJsonGenerator.writeEndArray();
      return;
    } 
    paramJsonGenerator.writeString(printLocalDateTime((ReadablePartial)paramLocalDateTime));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\JodaSerializers$LocalDateTimeSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */