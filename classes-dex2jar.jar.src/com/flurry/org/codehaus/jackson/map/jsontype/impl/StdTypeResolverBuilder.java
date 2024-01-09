package com.flurry.org.codehaus.jackson.map.jsontype.impl;

import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Collection;

public class StdTypeResolverBuilder implements TypeResolverBuilder {
  protected TypeIdResolver _customIdResolver;
  
  protected Class _defaultImpl;
  
  protected JsonTypeInfo.Id _idType;
  
  protected JsonTypeInfo.As _includeAs;
  
  protected String _typeProperty;
  
  public static StdTypeResolverBuilder noTypeInfoBuilder() {
    return (new StdTypeResolverBuilder()).init(JsonTypeInfo.Id.NONE, (TypeIdResolver)null);
  }
  
  public TypeDeserializer buildTypeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, Collection paramCollection, BeanProperty paramBeanProperty) {
    if (this._idType == JsonTypeInfo.Id.NONE)
      return null; 
    TypeIdResolver typeIdResolver = idResolver((MapperConfig)paramDeserializationConfig, paramJavaType, paramCollection, false, true);
    switch (StdTypeResolverBuilder$1.$SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$As[this._includeAs.ordinal()]) {
      default:
        throw new IllegalStateException("Do not know how to construct standard type serializer for inclusion type: " + this._includeAs);
      case 1:
        return new AsArrayTypeDeserializer(paramJavaType, typeIdResolver, paramBeanProperty, this._defaultImpl);
      case 2:
        return new AsPropertyTypeDeserializer(paramJavaType, typeIdResolver, paramBeanProperty, this._defaultImpl, this._typeProperty);
      case 3:
        return new AsWrapperTypeDeserializer(paramJavaType, typeIdResolver, paramBeanProperty, this._defaultImpl);
      case 4:
        break;
    } 
    return new AsExternalTypeDeserializer(paramJavaType, typeIdResolver, paramBeanProperty, this._defaultImpl, this._typeProperty);
  }
  
  public TypeSerializer buildTypeSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, Collection paramCollection, BeanProperty paramBeanProperty) {
    if (this._idType == JsonTypeInfo.Id.NONE)
      return null; 
    TypeIdResolver typeIdResolver = idResolver((MapperConfig)paramSerializationConfig, paramJavaType, paramCollection, true, false);
    switch (StdTypeResolverBuilder$1.$SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$As[this._includeAs.ordinal()]) {
      default:
        throw new IllegalStateException("Do not know how to construct standard type serializer for inclusion type: " + this._includeAs);
      case 1:
        return new AsArrayTypeSerializer(typeIdResolver, paramBeanProperty);
      case 2:
        return new AsPropertyTypeSerializer(typeIdResolver, paramBeanProperty, this._typeProperty);
      case 3:
        return new AsWrapperTypeSerializer(typeIdResolver, paramBeanProperty);
      case 4:
        break;
    } 
    return new AsExternalTypeSerializer(typeIdResolver, paramBeanProperty, this._typeProperty);
  }
  
  public StdTypeResolverBuilder defaultImpl(Class paramClass) {
    this._defaultImpl = paramClass;
    return this;
  }
  
  public Class getDefaultImpl() {
    return this._defaultImpl;
  }
  
  public String getTypeProperty() {
    return this._typeProperty;
  }
  
  protected TypeIdResolver idResolver(MapperConfig paramMapperConfig, JavaType paramJavaType, Collection paramCollection, boolean paramBoolean1, boolean paramBoolean2) {
    if (this._customIdResolver != null)
      return this._customIdResolver; 
    if (this._idType == null)
      throw new IllegalStateException("Can not build, 'init()' not yet called"); 
    switch (StdTypeResolverBuilder$1.$SwitchMap$org$codehaus$jackson$annotate$JsonTypeInfo$Id[this._idType.ordinal()]) {
      default:
        throw new IllegalStateException("Do not know how to construct standard type id resolver for idType: " + this._idType);
      case 1:
        return new ClassNameIdResolver(paramJavaType, paramMapperConfig.getTypeFactory());
      case 2:
        return new MinimalClassNameIdResolver(paramJavaType, paramMapperConfig.getTypeFactory());
      case 3:
        return TypeNameIdResolver.construct(paramMapperConfig, paramJavaType, paramCollection, paramBoolean1, paramBoolean2);
      case 4:
        break;
    } 
    return null;
  }
  
  public StdTypeResolverBuilder inclusion(JsonTypeInfo.As paramAs) {
    if (paramAs == null)
      throw new IllegalArgumentException("includeAs can not be null"); 
    this._includeAs = paramAs;
    return this;
  }
  
  public StdTypeResolverBuilder init(JsonTypeInfo.Id paramId, TypeIdResolver paramTypeIdResolver) {
    if (paramId == null)
      throw new IllegalArgumentException("idType can not be null"); 
    this._idType = paramId;
    this._customIdResolver = paramTypeIdResolver;
    this._typeProperty = paramId.getDefaultPropertyName();
    return this;
  }
  
  public StdTypeResolverBuilder typeProperty(String paramString) {
    if (paramString != null) {
      String str1 = paramString;
      if (paramString.length() == 0) {
        str1 = this._idType.getDefaultPropertyName();
        this._typeProperty = str1;
        return this;
      } 
      this._typeProperty = str1;
      return this;
    } 
    String str = this._idType.getDefaultPropertyName();
    this._typeProperty = str;
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\jsontype\impl\StdTypeResolverBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */