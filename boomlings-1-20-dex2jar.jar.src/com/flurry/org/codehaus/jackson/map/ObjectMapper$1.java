package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.Version;
import com.flurry.org.codehaus.jackson.map.deser.BeanDeserializerModifier;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiators;
import com.flurry.org.codehaus.jackson.map.ser.BeanSerializerModifier;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.map.type.TypeModifier;

class ObjectMapper$1 implements Module$SetupContext {
  final ObjectMapper this$0;
  
  final ObjectMapper val$mapper;
  
  public void addAbstractTypeResolver(AbstractTypeResolver paramAbstractTypeResolver) {
    mapper._deserializerProvider = mapper._deserializerProvider.withAbstractTypeResolver(paramAbstractTypeResolver);
  }
  
  public void addBeanDeserializerModifier(BeanDeserializerModifier paramBeanDeserializerModifier) {
    mapper._deserializerProvider = mapper._deserializerProvider.withDeserializerModifier(paramBeanDeserializerModifier);
  }
  
  public void addBeanSerializerModifier(BeanSerializerModifier paramBeanSerializerModifier) {
    mapper._serializerFactory = mapper._serializerFactory.withSerializerModifier(paramBeanSerializerModifier);
  }
  
  public void addDeserializers(Deserializers paramDeserializers) {
    mapper._deserializerProvider = mapper._deserializerProvider.withAdditionalDeserializers(paramDeserializers);
  }
  
  public void addKeyDeserializers(KeyDeserializers paramKeyDeserializers) {
    mapper._deserializerProvider = mapper._deserializerProvider.withAdditionalKeyDeserializers(paramKeyDeserializers);
  }
  
  public void addKeySerializers(Serializers paramSerializers) {
    mapper._serializerFactory = mapper._serializerFactory.withAdditionalKeySerializers(paramSerializers);
  }
  
  public void addSerializers(Serializers paramSerializers) {
    mapper._serializerFactory = mapper._serializerFactory.withAdditionalSerializers(paramSerializers);
  }
  
  public void addTypeModifier(TypeModifier paramTypeModifier) {
    TypeFactory typeFactory = mapper._typeFactory.withModifier(paramTypeModifier);
    mapper.setTypeFactory(typeFactory);
  }
  
  public void addValueInstantiators(ValueInstantiators paramValueInstantiators) {
    mapper._deserializerProvider = mapper._deserializerProvider.withValueInstantiators(paramValueInstantiators);
  }
  
  public void appendAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector) {
    mapper._deserializationConfig = mapper._deserializationConfig.withAppendedAnnotationIntrospector(paramAnnotationIntrospector);
    mapper._serializationConfig = mapper._serializationConfig.withAppendedAnnotationIntrospector(paramAnnotationIntrospector);
  }
  
  public DeserializationConfig getDeserializationConfig() {
    return mapper.getDeserializationConfig();
  }
  
  public Version getMapperVersion() {
    return ObjectMapper.this.version();
  }
  
  public SerializationConfig getSerializationConfig() {
    return mapper.getSerializationConfig();
  }
  
  public void insertAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector) {
    mapper._deserializationConfig = mapper._deserializationConfig.withInsertedAnnotationIntrospector(paramAnnotationIntrospector);
    mapper._serializationConfig = mapper._serializationConfig.withInsertedAnnotationIntrospector(paramAnnotationIntrospector);
  }
  
  public boolean isEnabled(JsonGenerator.Feature paramFeature) {
    return mapper.isEnabled(paramFeature);
  }
  
  public boolean isEnabled(JsonParser.Feature paramFeature) {
    return mapper.isEnabled(paramFeature);
  }
  
  public boolean isEnabled(DeserializationConfig$Feature paramDeserializationConfig$Feature) {
    return mapper.isEnabled(paramDeserializationConfig$Feature);
  }
  
  public boolean isEnabled(SerializationConfig$Feature paramSerializationConfig$Feature) {
    return mapper.isEnabled(paramSerializationConfig$Feature);
  }
  
  public void setMixInAnnotations(Class paramClass1, Class paramClass2) {
    mapper._deserializationConfig.addMixInAnnotations(paramClass1, paramClass2);
    mapper._serializationConfig.addMixInAnnotations(paramClass1, paramClass2);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ObjectMapper$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */