package com.flurry.org.codehaus.jackson.map.module;

import com.flurry.org.codehaus.jackson.Version;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;
import com.flurry.org.codehaus.jackson.map.Module;
import com.flurry.org.codehaus.jackson.map.Serializers;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiator;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiators;
import java.util.HashMap;
import java.util.Map;

public class SimpleModule extends Module {
  protected SimpleAbstractTypeResolver _abstractTypes = null;
  
  protected SimpleDeserializers _deserializers = null;
  
  protected SimpleKeyDeserializers _keyDeserializers = null;
  
  protected SimpleSerializers _keySerializers = null;
  
  protected HashMap _mixins = null;
  
  protected final String _name;
  
  protected SimpleSerializers _serializers = null;
  
  protected SimpleValueInstantiators _valueInstantiators = null;
  
  protected final Version _version;
  
  public SimpleModule(String paramString, Version paramVersion) {
    this._name = paramString;
    this._version = paramVersion;
  }
  
  public SimpleModule addAbstractTypeMapping(Class paramClass1, Class paramClass2) {
    if (this._abstractTypes == null)
      this._abstractTypes = new SimpleAbstractTypeResolver(); 
    this._abstractTypes = this._abstractTypes.addMapping(paramClass1, paramClass2);
    return this;
  }
  
  public SimpleModule addDeserializer(Class paramClass, JsonDeserializer paramJsonDeserializer) {
    if (this._deserializers == null)
      this._deserializers = new SimpleDeserializers(); 
    this._deserializers.addDeserializer(paramClass, paramJsonDeserializer);
    return this;
  }
  
  public SimpleModule addKeyDeserializer(Class paramClass, KeyDeserializer paramKeyDeserializer) {
    if (this._keyDeserializers == null)
      this._keyDeserializers = new SimpleKeyDeserializers(); 
    this._keyDeserializers.addDeserializer(paramClass, paramKeyDeserializer);
    return this;
  }
  
  public SimpleModule addKeySerializer(Class paramClass, JsonSerializer paramJsonSerializer) {
    if (this._keySerializers == null)
      this._keySerializers = new SimpleSerializers(); 
    this._keySerializers.addSerializer(paramClass, paramJsonSerializer);
    return this;
  }
  
  public SimpleModule addSerializer(JsonSerializer paramJsonSerializer) {
    if (this._serializers == null)
      this._serializers = new SimpleSerializers(); 
    this._serializers.addSerializer(paramJsonSerializer);
    return this;
  }
  
  public SimpleModule addSerializer(Class paramClass, JsonSerializer paramJsonSerializer) {
    if (this._serializers == null)
      this._serializers = new SimpleSerializers(); 
    this._serializers.addSerializer(paramClass, paramJsonSerializer);
    return this;
  }
  
  public SimpleModule addValueInstantiator(Class paramClass, ValueInstantiator paramValueInstantiator) {
    if (this._valueInstantiators == null)
      this._valueInstantiators = new SimpleValueInstantiators(); 
    this._valueInstantiators = this._valueInstantiators.addValueInstantiator(paramClass, paramValueInstantiator);
    return this;
  }
  
  public String getModuleName() {
    return this._name;
  }
  
  public void setAbstractTypes(SimpleAbstractTypeResolver paramSimpleAbstractTypeResolver) {
    this._abstractTypes = paramSimpleAbstractTypeResolver;
  }
  
  public void setDeserializers(SimpleDeserializers paramSimpleDeserializers) {
    this._deserializers = paramSimpleDeserializers;
  }
  
  public void setKeyDeserializers(SimpleKeyDeserializers paramSimpleKeyDeserializers) {
    this._keyDeserializers = paramSimpleKeyDeserializers;
  }
  
  public void setKeySerializers(SimpleSerializers paramSimpleSerializers) {
    this._keySerializers = paramSimpleSerializers;
  }
  
  public SimpleModule setMixInAnnotation(Class<?> paramClass1, Class<?> paramClass2) {
    if (this._mixins == null)
      this._mixins = new HashMap<Object, Object>(); 
    this._mixins.put(paramClass1, paramClass2);
    return this;
  }
  
  public void setSerializers(SimpleSerializers paramSimpleSerializers) {
    this._serializers = paramSimpleSerializers;
  }
  
  public void setValueInstantiators(SimpleValueInstantiators paramSimpleValueInstantiators) {
    this._valueInstantiators = paramSimpleValueInstantiators;
  }
  
  public void setupModule(Module.SetupContext paramSetupContext) {
    if (this._serializers != null)
      paramSetupContext.addSerializers((Serializers)this._serializers); 
    if (this._deserializers != null)
      paramSetupContext.addDeserializers(this._deserializers); 
    if (this._keySerializers != null)
      paramSetupContext.addKeySerializers((Serializers)this._keySerializers); 
    if (this._keyDeserializers != null)
      paramSetupContext.addKeyDeserializers(this._keyDeserializers); 
    if (this._abstractTypes != null)
      paramSetupContext.addAbstractTypeResolver(this._abstractTypes); 
    if (this._valueInstantiators != null)
      paramSetupContext.addValueInstantiators((ValueInstantiators)this._valueInstantiators); 
    if (this._mixins != null)
      for (Map.Entry entry : this._mixins.entrySet())
        paramSetupContext.setMixInAnnotations((Class)entry.getKey(), (Class)entry.getValue());  
  }
  
  public Version version() {
    return this._version;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\module\SimpleModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */