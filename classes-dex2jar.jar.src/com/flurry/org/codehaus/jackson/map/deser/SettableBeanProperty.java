package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.util.Annotations;
import com.flurry.org.codehaus.jackson.type.JavaType;
import com.flurry.org.codehaus.jackson.util.InternCache;
import java.io.IOException;
import java.lang.annotation.Annotation;

public abstract class SettableBeanProperty implements BeanProperty {
  protected final Annotations _contextAnnotations;
  
  protected String _managedReferenceName;
  
  protected SettableBeanProperty$NullProvider _nullProvider;
  
  protected final String _propName;
  
  protected int _propertyIndex = -1;
  
  protected final JavaType _type;
  
  protected JsonDeserializer _valueDeserializer;
  
  protected TypeDeserializer _valueTypeDeserializer;
  
  protected SettableBeanProperty(SettableBeanProperty paramSettableBeanProperty) {
    this._propName = paramSettableBeanProperty._propName;
    this._type = paramSettableBeanProperty._type;
    this._contextAnnotations = paramSettableBeanProperty._contextAnnotations;
    this._valueDeserializer = paramSettableBeanProperty._valueDeserializer;
    this._valueTypeDeserializer = paramSettableBeanProperty._valueTypeDeserializer;
    this._nullProvider = paramSettableBeanProperty._nullProvider;
    this._managedReferenceName = paramSettableBeanProperty._managedReferenceName;
    this._propertyIndex = paramSettableBeanProperty._propertyIndex;
  }
  
  protected SettableBeanProperty(SettableBeanProperty paramSettableBeanProperty, JsonDeserializer paramJsonDeserializer) {
    this._propName = paramSettableBeanProperty._propName;
    this._type = paramSettableBeanProperty._type;
    this._contextAnnotations = paramSettableBeanProperty._contextAnnotations;
    this._valueTypeDeserializer = paramSettableBeanProperty._valueTypeDeserializer;
    this._managedReferenceName = paramSettableBeanProperty._managedReferenceName;
    this._propertyIndex = paramSettableBeanProperty._propertyIndex;
    this._valueDeserializer = paramJsonDeserializer;
    if (paramJsonDeserializer == null) {
      this._nullProvider = null;
      return;
    } 
    Object object = paramJsonDeserializer.getNullValue();
    if (object == null) {
      object = object1;
    } else {
      object = new SettableBeanProperty$NullProvider(this._type, object);
    } 
    this._nullProvider = (SettableBeanProperty$NullProvider)object;
  }
  
  protected SettableBeanProperty(String paramString, JavaType paramJavaType, TypeDeserializer paramTypeDeserializer, Annotations paramAnnotations) {
    if (paramString == null || paramString.length() == 0) {
      this._propName = "";
    } else {
      this._propName = InternCache.instance.intern(paramString);
    } 
    this._type = paramJavaType;
    this._contextAnnotations = paramAnnotations;
    this._valueTypeDeserializer = paramTypeDeserializer;
  }
  
  protected IOException _throwAsIOE(Exception paramException) {
    if (paramException instanceof IOException)
      throw (IOException)paramException; 
    Throwable throwable = paramException;
    if (paramException instanceof RuntimeException)
      throw (RuntimeException)paramException; 
    while (throwable.getCause() != null)
      throwable = throwable.getCause(); 
    throw new JsonMappingException(throwable.getMessage(), null, throwable);
  }
  
  protected void _throwAsIOE(Exception paramException, Object paramObject) {
    if (paramException instanceof IllegalArgumentException) {
      if (paramObject == null) {
        paramObject = "[NULL]";
      } else {
        paramObject = paramObject.getClass().getName();
      } 
      StringBuilder stringBuilder = (new StringBuilder("Problem deserializing property '")).append(getPropertyName());
      stringBuilder.append("' (expected type: ").append(getType());
      stringBuilder.append("; actual type: ").append((String)paramObject).append(")");
      paramObject = paramException.getMessage();
      if (paramObject != null) {
        stringBuilder.append(", problem: ").append((String)paramObject);
        throw new JsonMappingException(stringBuilder.toString(), null, paramException);
      } 
      stringBuilder.append(" (no error message provided)");
      throw new JsonMappingException(stringBuilder.toString(), null, paramException);
    } 
    _throwAsIOE(paramException);
  }
  
  public void assignIndex(int paramInt) {
    if (this._propertyIndex != -1)
      throw new IllegalStateException("Property '" + getName() + "' already had index (" + this._propertyIndex + "), trying to assign " + paramInt); 
    this._propertyIndex = paramInt;
  }
  
  public final Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    return (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL) ? ((this._nullProvider == null) ? null : this._nullProvider.nullValue(paramDeserializationContext)) : ((this._valueTypeDeserializer != null) ? this._valueDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, this._valueTypeDeserializer) : this._valueDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
  }
  
  public abstract void deserializeAndSet(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject);
  
  public abstract Annotation getAnnotation(Class paramClass);
  
  public Annotation getContextAnnotation(Class paramClass) {
    return this._contextAnnotations.get(paramClass);
  }
  
  protected final Class getDeclaringClass() {
    return getMember().getDeclaringClass();
  }
  
  public Object getInjectableValueId() {
    return null;
  }
  
  public String getManagedReferenceName() {
    return this._managedReferenceName;
  }
  
  public abstract AnnotatedMember getMember();
  
  public final String getName() {
    return this._propName;
  }
  
  public int getPropertyIndex() {
    return this._propertyIndex;
  }
  
  @Deprecated
  public String getPropertyName() {
    return this._propName;
  }
  
  @Deprecated
  public int getProperytIndex() {
    return getPropertyIndex();
  }
  
  public JavaType getType() {
    return this._type;
  }
  
  public JsonDeserializer getValueDeserializer() {
    return this._valueDeserializer;
  }
  
  public TypeDeserializer getValueTypeDeserializer() {
    return this._valueTypeDeserializer;
  }
  
  public boolean hasValueDeserializer() {
    return (this._valueDeserializer != null);
  }
  
  public boolean hasValueTypeDeserializer() {
    return (this._valueTypeDeserializer != null);
  }
  
  public abstract void set(Object paramObject1, Object paramObject2);
  
  public void setManagedReferenceName(String paramString) {
    this._managedReferenceName = paramString;
  }
  
  @Deprecated
  public void setValueDeserializer(JsonDeserializer paramJsonDeserializer) {
    if (this._valueDeserializer != null)
      throw new IllegalStateException("Already had assigned deserializer for property '" + getName() + "' (class " + getDeclaringClass().getName() + ")"); 
    this._valueDeserializer = paramJsonDeserializer;
    Object object = this._valueDeserializer.getNullValue();
    if (object == null) {
      object = null;
    } else {
      object = new SettableBeanProperty$NullProvider(this._type, object);
    } 
    this._nullProvider = (SettableBeanProperty$NullProvider)object;
  }
  
  public String toString() {
    return "[property '" + getName() + "']";
  }
  
  public abstract SettableBeanProperty withValueDeserializer(JsonDeserializer paramJsonDeserializer);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\SettableBeanProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */