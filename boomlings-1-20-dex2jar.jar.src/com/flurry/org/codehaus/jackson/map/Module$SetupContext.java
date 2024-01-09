package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.Version;
import com.flurry.org.codehaus.jackson.map.deser.BeanDeserializerModifier;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiators;
import com.flurry.org.codehaus.jackson.map.ser.BeanSerializerModifier;
import com.flurry.org.codehaus.jackson.map.type.TypeModifier;

public interface Module$SetupContext {
  void addAbstractTypeResolver(AbstractTypeResolver paramAbstractTypeResolver);
  
  void addBeanDeserializerModifier(BeanDeserializerModifier paramBeanDeserializerModifier);
  
  void addBeanSerializerModifier(BeanSerializerModifier paramBeanSerializerModifier);
  
  void addDeserializers(Deserializers paramDeserializers);
  
  void addKeyDeserializers(KeyDeserializers paramKeyDeserializers);
  
  void addKeySerializers(Serializers paramSerializers);
  
  void addSerializers(Serializers paramSerializers);
  
  void addTypeModifier(TypeModifier paramTypeModifier);
  
  void addValueInstantiators(ValueInstantiators paramValueInstantiators);
  
  void appendAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector);
  
  DeserializationConfig getDeserializationConfig();
  
  Version getMapperVersion();
  
  SerializationConfig getSerializationConfig();
  
  void insertAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector);
  
  boolean isEnabled(JsonGenerator.Feature paramFeature);
  
  boolean isEnabled(JsonParser.Feature paramFeature);
  
  boolean isEnabled(DeserializationConfig$Feature paramDeserializationConfig$Feature);
  
  boolean isEnabled(SerializationConfig$Feature paramSerializationConfig$Feature);
  
  void setMixInAnnotations(Class paramClass1, Class paramClass2);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\Module$SetupContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */