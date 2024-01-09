package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.lang.reflect.Type;
import org.joda.time.DateMidnight;
import org.joda.time.ReadableInstant;

public final class JodaSerializers$DateMidnightSerializer extends JodaSerializers$JodaSerializer {
  public JodaSerializers$DateMidnightSerializer() {
    super(DateMidnight.class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    if (paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
      String str1 = "array";
      return (JsonNode)createSchemaNode(str1, true);
    } 
    String str = "string";
    return (JsonNode)createSchemaNode(str, true);
  }
  
  public void serialize(DateMidnight paramDateMidnight, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    if (paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
      paramJsonGenerator.writeStartArray();
      paramJsonGenerator.writeNumber(paramDateMidnight.year().get());
      paramJsonGenerator.writeNumber(paramDateMidnight.monthOfYear().get());
      paramJsonGenerator.writeNumber(paramDateMidnight.dayOfMonth().get());
      paramJsonGenerator.writeEndArray();
      return;
    } 
    paramJsonGenerator.writeString(printLocalDate((ReadableInstant)paramDateMidnight));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\JodaSerializers$DateMidnightSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */