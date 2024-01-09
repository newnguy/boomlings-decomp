package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.SerializableString;
import com.flurry.org.codehaus.jackson.io.SerializedString;
import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.flurry.org.codehaus.jackson.map.util.EnumValues;
import com.flurry.org.codehaus.jackson.node.ArrayNode;
import com.flurry.org.codehaus.jackson.node.ObjectNode;
import java.lang.reflect.Type;
import java.util.Iterator;

@JacksonStdImpl
public class EnumSerializer extends ScalarSerializerBase {
  protected final EnumValues _values;
  
  public EnumSerializer(EnumValues paramEnumValues) {
    super(Enum.class, false);
    this._values = paramEnumValues;
  }
  
  public static EnumSerializer construct(Class paramClass, SerializationConfig paramSerializationConfig, BasicBeanDescription paramBasicBeanDescription) {
    AnnotationIntrospector annotationIntrospector = paramSerializationConfig.getAnnotationIntrospector();
    if (paramSerializationConfig.isEnabled(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING)) {
      enumValues = EnumValues.constructFromToString(paramClass, annotationIntrospector);
      return new EnumSerializer(enumValues);
    } 
    EnumValues enumValues = EnumValues.constructFromName((Class)enumValues, annotationIntrospector);
    return new EnumSerializer(enumValues);
  }
  
  public EnumValues getEnumValues() {
    return this._values;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    if (paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_ENUMS_USING_INDEX))
      return (JsonNode)createSchemaNode("integer", true); 
    ObjectNode objectNode = createSchemaNode("string", true);
    if (paramType != null && paramSerializerProvider.constructType(paramType).isEnumType()) {
      ArrayNode arrayNode = objectNode.putArray("enum");
      Iterator<SerializedString> iterator = this._values.values().iterator();
      while (iterator.hasNext())
        arrayNode.add(((SerializedString)iterator.next()).getValue()); 
    } 
    return (JsonNode)objectNode;
  }
  
  public final void serialize(Enum paramEnum, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    if (paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_ENUMS_USING_INDEX)) {
      paramJsonGenerator.writeNumber(paramEnum.ordinal());
      return;
    } 
    paramJsonGenerator.writeString((SerializableString)this._values.serializedValueFor(paramEnum));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\EnumSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */