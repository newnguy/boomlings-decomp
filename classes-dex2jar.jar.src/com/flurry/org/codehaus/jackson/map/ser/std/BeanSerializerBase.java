package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.ResolvableSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.ser.AnyGetterWriter;
import com.flurry.org.codehaus.jackson.map.ser.BeanPropertyFilter;
import com.flurry.org.codehaus.jackson.map.ser.BeanPropertyWriter;
import com.flurry.org.codehaus.jackson.map.ser.FilterProvider;
import com.flurry.org.codehaus.jackson.node.ObjectNode;
import com.flurry.org.codehaus.jackson.schema.JsonSchema;
import com.flurry.org.codehaus.jackson.schema.SchemaAware;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Type;

public abstract class BeanSerializerBase extends SerializerBase implements ResolvableSerializer, SchemaAware {
  protected static final BeanPropertyWriter[] NO_PROPS = new BeanPropertyWriter[0];
  
  protected final AnyGetterWriter _anyGetterWriter;
  
  protected final BeanPropertyWriter[] _filteredProps;
  
  protected final Object _propertyFilterId;
  
  protected final BeanPropertyWriter[] _props;
  
  protected BeanSerializerBase(BeanSerializerBase paramBeanSerializerBase) {
    this(paramBeanSerializerBase._handledType, paramBeanSerializerBase._props, paramBeanSerializerBase._filteredProps, paramBeanSerializerBase._anyGetterWriter, paramBeanSerializerBase._propertyFilterId);
  }
  
  protected BeanSerializerBase(JavaType paramJavaType, BeanPropertyWriter[] paramArrayOfBeanPropertyWriter1, BeanPropertyWriter[] paramArrayOfBeanPropertyWriter2, AnyGetterWriter paramAnyGetterWriter, Object paramObject) {
    super(paramJavaType);
    this._props = paramArrayOfBeanPropertyWriter1;
    this._filteredProps = paramArrayOfBeanPropertyWriter2;
    this._anyGetterWriter = paramAnyGetterWriter;
    this._propertyFilterId = paramObject;
  }
  
  public BeanSerializerBase(Class paramClass, BeanPropertyWriter[] paramArrayOfBeanPropertyWriter1, BeanPropertyWriter[] paramArrayOfBeanPropertyWriter2, AnyGetterWriter paramAnyGetterWriter, Object paramObject) {
    super(paramClass);
    this._props = paramArrayOfBeanPropertyWriter1;
    this._filteredProps = paramArrayOfBeanPropertyWriter2;
    this._anyGetterWriter = paramAnyGetterWriter;
    this._propertyFilterId = paramObject;
  }
  
  protected BeanPropertyFilter findFilter(SerializerProvider paramSerializerProvider) {
    Object object = this._propertyFilterId;
    FilterProvider filterProvider = paramSerializerProvider.getFilterProvider();
    if (filterProvider == null)
      throw new JsonMappingException("Can not resolve BeanPropertyFilter with id '" + object + "'; no FilterProvider configured"); 
    return filterProvider.findFilter(object);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    ObjectNode objectNode1 = createSchemaNode("object", true);
    ObjectNode objectNode2 = objectNode1.objectNode();
    for (byte b = 0; b < this._props.length; b++) {
      Type type;
      JsonNode jsonNode;
      BeanPropertyWriter beanPropertyWriter = this._props[b];
      JavaType javaType = beanPropertyWriter.getSerializationType();
      if (javaType == null) {
        type = beanPropertyWriter.getGenericPropertyType();
      } else {
        type = type.getRawClass();
      } 
      JsonSerializer jsonSerializer2 = beanPropertyWriter.getSerializer();
      JsonSerializer jsonSerializer1 = jsonSerializer2;
      if (jsonSerializer2 == null) {
        Class clazz2 = beanPropertyWriter.getRawSerializationType();
        Class clazz1 = clazz2;
        if (clazz2 == null)
          clazz1 = beanPropertyWriter.getPropertyType(); 
        jsonSerializer1 = paramSerializerProvider.findValueSerializer(clazz1, (BeanProperty)beanPropertyWriter);
      } 
      if (jsonSerializer1 instanceof SchemaAware) {
        jsonNode = ((SchemaAware)jsonSerializer1).getSchema(paramSerializerProvider, type);
      } else {
        jsonNode = JsonSchema.getDefaultSchemaNode();
      } 
      objectNode2.put(beanPropertyWriter.getName(), jsonNode);
    } 
    objectNode1.put("properties", (JsonNode)objectNode2);
    return (JsonNode)objectNode1;
  }
  
  public void resolve(SerializerProvider paramSerializerProvider) {
    int i;
    if (this._filteredProps == null) {
      i = 0;
    } else {
      i = this._filteredProps.length;
    } 
    int j = this._props.length;
    for (byte b = 0; b < j; b++) {
      BeanPropertyWriter beanPropertyWriter2 = this._props[b];
      if (beanPropertyWriter2.hasSerializer())
        continue; 
      JavaType javaType1 = beanPropertyWriter2.getSerializationType();
      JavaType javaType2 = javaType1;
      if (javaType1 == null) {
        javaType1 = paramSerializerProvider.constructType(beanPropertyWriter2.getGenericPropertyType());
        javaType2 = javaType1;
        if (!javaType1.isFinal()) {
          if (javaType1.isContainerType() || javaType1.containedTypeCount() > 0)
            beanPropertyWriter2.setNonTrivialBaseType(javaType1); 
          continue;
        } 
      } 
      JsonSerializer jsonSerializer2 = paramSerializerProvider.findValueSerializer(javaType2, (BeanProperty)beanPropertyWriter2);
      JsonSerializer jsonSerializer1 = jsonSerializer2;
      if (javaType2.isContainerType()) {
        TypeSerializer typeSerializer = (TypeSerializer)javaType2.getContentType().getTypeHandler();
        jsonSerializer1 = jsonSerializer2;
        if (typeSerializer != null) {
          jsonSerializer1 = jsonSerializer2;
          if (jsonSerializer2 instanceof ContainerSerializerBase)
            jsonSerializer1 = ((ContainerSerializerBase)jsonSerializer2).withValueTypeSerializer(typeSerializer); 
        } 
      } 
      BeanPropertyWriter beanPropertyWriter1 = beanPropertyWriter2.withSerializer(jsonSerializer1);
      this._props[b] = beanPropertyWriter1;
      if (b < i) {
        beanPropertyWriter1 = this._filteredProps[b];
        if (beanPropertyWriter1 != null)
          this._filteredProps[b] = beanPropertyWriter1.withSerializer(jsonSerializer1); 
      } 
      continue;
    } 
    if (this._anyGetterWriter != null)
      this._anyGetterWriter.resolve(paramSerializerProvider); 
  }
  
  public abstract void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider);
  
  protected void serializeFields(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    String str;
    JsonMappingException jsonMappingException;
    BeanPropertyWriter[] arrayOfBeanPropertyWriter;
    if (this._filteredProps != null && paramSerializerProvider.getSerializationView() != null) {
      arrayOfBeanPropertyWriter = this._filteredProps;
    } else {
      arrayOfBeanPropertyWriter = this._props;
    } 
    byte b2 = 0;
    byte b3 = 0;
    byte b1 = 0;
    try {
      int i = arrayOfBeanPropertyWriter.length;
      while (b1 < i) {
        BeanPropertyWriter beanPropertyWriter = arrayOfBeanPropertyWriter[b1];
        if (beanPropertyWriter != null) {
          b2 = b1;
          b3 = b1;
          beanPropertyWriter.serializeAsField(paramObject, paramJsonGenerator, paramSerializerProvider);
        } 
        b1++;
      } 
      b2 = b1;
      b3 = b1;
      if (this._anyGetterWriter != null) {
        b2 = b1;
        b3 = b1;
        this._anyGetterWriter.getAndSerialize(paramObject, paramJsonGenerator, paramSerializerProvider);
      } 
      return;
    } catch (Exception exception) {
      if (b2 == arrayOfBeanPropertyWriter.length) {
        str = "[anySetter]";
      } else {
        str = arrayOfBeanPropertyWriter[b2].getName();
      } 
      wrapAndThrow(paramSerializerProvider, exception, paramObject, str);
      return;
    } catch (StackOverflowError stackOverflowError) {
      jsonMappingException = new JsonMappingException("Infinite recursion (StackOverflowError)");
      if (b3 == arrayOfBeanPropertyWriter.length) {
        str = "[anySetter]";
        jsonMappingException.prependPath(new JsonMappingException.Reference(paramObject, str));
        throw jsonMappingException;
      } 
    } 
    jsonMappingException.prependPath(new JsonMappingException.Reference(paramObject, str));
    throw jsonMappingException;
  }
  
  protected void serializeFieldsFiltered(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    String str;
    JsonMappingException jsonMappingException;
    BeanPropertyWriter[] arrayOfBeanPropertyWriter;
    if (this._filteredProps != null && paramSerializerProvider.getSerializationView() != null) {
      arrayOfBeanPropertyWriter = this._filteredProps;
    } else {
      arrayOfBeanPropertyWriter = this._props;
    } 
    BeanPropertyFilter beanPropertyFilter = findFilter(paramSerializerProvider);
    if (beanPropertyFilter == null) {
      serializeFields(paramObject, paramJsonGenerator, paramSerializerProvider);
      return;
    } 
    byte b2 = 0;
    byte b3 = 0;
    byte b1 = 0;
    try {
      int i = arrayOfBeanPropertyWriter.length;
      while (b1 < i) {
        BeanPropertyWriter beanPropertyWriter = arrayOfBeanPropertyWriter[b1];
        if (beanPropertyWriter != null) {
          b2 = b1;
          b3 = b1;
          beanPropertyFilter.serializeAsField(paramObject, paramJsonGenerator, paramSerializerProvider, beanPropertyWriter);
        } 
        b1++;
      } 
      b2 = b1;
      b3 = b1;
      if (this._anyGetterWriter != null) {
        b2 = b1;
        b3 = b1;
        this._anyGetterWriter.getAndSerialize(paramObject, paramJsonGenerator, paramSerializerProvider);
      } 
      return;
    } catch (Exception exception) {
      if (b2 == arrayOfBeanPropertyWriter.length) {
        str = "[anySetter]";
      } else {
        str = arrayOfBeanPropertyWriter[b2].getName();
      } 
      wrapAndThrow(paramSerializerProvider, exception, paramObject, str);
      return;
    } catch (StackOverflowError stackOverflowError) {
      jsonMappingException = new JsonMappingException("Infinite recursion (StackOverflowError)");
      if (b3 == arrayOfBeanPropertyWriter.length) {
        str = "[anySetter]";
        jsonMappingException.prependPath(new JsonMappingException.Reference(paramObject, str));
        throw jsonMappingException;
      } 
    } 
    jsonMappingException.prependPath(new JsonMappingException.Reference(paramObject, str));
    throw jsonMappingException;
  }
  
  public void serializeWithType(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    paramTypeSerializer.writeTypePrefixForObject(paramObject, paramJsonGenerator);
    if (this._propertyFilterId != null) {
      serializeFieldsFiltered(paramObject, paramJsonGenerator, paramSerializerProvider);
    } else {
      serializeFields(paramObject, paramJsonGenerator, paramSerializerProvider);
    } 
    paramTypeSerializer.writeTypeSuffixForObject(paramObject, paramJsonGenerator);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\BeanSerializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */