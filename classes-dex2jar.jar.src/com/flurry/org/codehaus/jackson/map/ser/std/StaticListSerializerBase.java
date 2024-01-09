package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.node.ObjectNode;
import java.lang.reflect.Type;

public abstract class StaticListSerializerBase extends SerializerBase {
  protected final BeanProperty _property;
  
  protected StaticListSerializerBase(Class paramClass, BeanProperty paramBeanProperty) {
    super(paramClass, false);
    this._property = paramBeanProperty;
  }
  
  protected abstract JsonNode contentSchema();
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    ObjectNode objectNode = createSchemaNode("array", true);
    objectNode.put("items", contentSchema());
    return (JsonNode)objectNode;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StaticListSerializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */