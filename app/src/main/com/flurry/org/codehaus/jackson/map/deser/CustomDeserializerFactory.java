package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializerFactory;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.type.ArrayType;
import com.flurry.org.codehaus.jackson.map.type.ClassKey;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.HashMap;

@Deprecated
/* loaded from: classes.dex */
public class CustomDeserializerFactory extends BeanDeserializerFactory {
    protected HashMap _directClassMappings;
    protected HashMap _mixInAnnotations;

    public CustomDeserializerFactory() {
        this(null);
    }

    protected CustomDeserializerFactory(DeserializerFactory.Config config) {
        super(config);
        this._directClassMappings = null;
    }

    public void addMixInAnnotationMapping(Class cls, Class cls2) {
        if (this._mixInAnnotations == null) {
            this._mixInAnnotations = new HashMap();
        }
        this._mixInAnnotations.put(new ClassKey(cls), cls2);
    }

    public void addSpecificMapping(Class cls, JsonDeserializer jsonDeserializer) {
        ClassKey classKey = new ClassKey(cls);
        if (this._directClassMappings == null) {
            this._directClassMappings = new HashMap();
        }
        this._directClassMappings.put(classKey, jsonDeserializer);
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.BasicDeserializerFactory, com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer createArrayDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, ArrayType arrayType, BeanProperty beanProperty) {
        JsonDeserializer jsonDeserializer;
        return (this._directClassMappings == null || (jsonDeserializer = (JsonDeserializer) this._directClassMappings.get(new ClassKey(arrayType.getRawClass()))) == null) ? super.createArrayDeserializer(deserializationConfig, deserializerProvider, arrayType, beanProperty) : jsonDeserializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.BeanDeserializerFactory, com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer createBeanDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) {
        JsonDeserializer jsonDeserializer;
        return (this._directClassMappings == null || (jsonDeserializer = (JsonDeserializer) this._directClassMappings.get(new ClassKey(javaType.getRawClass()))) == null) ? super.createBeanDeserializer(deserializationConfig, deserializerProvider, javaType, beanProperty) : jsonDeserializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.BasicDeserializerFactory, com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer createEnumDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) {
        if (this._directClassMappings != null) {
            JsonDeserializer jsonDeserializer = (JsonDeserializer) this._directClassMappings.get(new ClassKey(javaType.getRawClass()));
            if (jsonDeserializer != null) {
                return jsonDeserializer;
            }
        }
        return super.createEnumDeserializer(deserializationConfig, deserializerProvider, javaType, beanProperty);
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.BeanDeserializerFactory, com.flurry.org.codehaus.jackson.map.deser.BasicDeserializerFactory, com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public DeserializerFactory withConfig(DeserializerFactory.Config config) {
        if (getClass() != CustomDeserializerFactory.class) {
            throw new IllegalStateException("Subtype of CustomDeserializerFactory (" + getClass().getName() + ") has not properly overridden method 'withAdditionalDeserializers': can not instantiate subtype with additional deserializer definitions");
        }
        return new CustomDeserializerFactory(config);
    }
}
