package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerFactory;
import com.flurry.org.codehaus.jackson.map.type.ClassKey;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Modifier;
import java.util.HashMap;

/* loaded from: classes.dex */
public class CustomSerializerFactory extends BeanSerializerFactory {
    protected HashMap _directClassMappings;
    protected JsonSerializer _enumSerializerOverride;
    protected HashMap _interfaceMappings;
    protected HashMap _transitiveClassMappings;

    public CustomSerializerFactory() {
        this(null);
    }

    public CustomSerializerFactory(SerializerFactory.Config config) {
        super(config);
        this._directClassMappings = null;
        this._transitiveClassMappings = null;
        this._interfaceMappings = null;
    }

    protected JsonSerializer _findInterfaceMapping(Class cls, ClassKey classKey) {
        Class<?>[] interfaces;
        for (Class<?> cls2 : cls.getInterfaces()) {
            classKey.reset(cls2);
            JsonSerializer jsonSerializer = (JsonSerializer) this._interfaceMappings.get(classKey);
            if (jsonSerializer != null) {
                return jsonSerializer;
            }
            JsonSerializer _findInterfaceMapping = _findInterfaceMapping(cls2, classKey);
            if (_findInterfaceMapping != null) {
                return _findInterfaceMapping;
            }
        }
        return null;
    }

    public void addGenericMapping(Class cls, JsonSerializer jsonSerializer) {
        ClassKey classKey = new ClassKey(cls);
        if (cls.isInterface()) {
            if (this._interfaceMappings == null) {
                this._interfaceMappings = new HashMap();
            }
            this._interfaceMappings.put(classKey, jsonSerializer);
            return;
        }
        if (this._transitiveClassMappings == null) {
            this._transitiveClassMappings = new HashMap();
        }
        this._transitiveClassMappings.put(classKey, jsonSerializer);
    }

    public void addSpecificMapping(Class cls, JsonSerializer jsonSerializer) {
        ClassKey classKey = new ClassKey(cls);
        if (cls.isInterface()) {
            throw new IllegalArgumentException("Can not add specific mapping for an interface (" + cls.getName() + ")");
        }
        if (Modifier.isAbstract(cls.getModifiers())) {
            throw new IllegalArgumentException("Can not add specific mapping for an abstract class (" + cls.getName() + ")");
        }
        if (this._directClassMappings == null) {
            this._directClassMappings = new HashMap();
        }
        this._directClassMappings.put(classKey, jsonSerializer);
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.BeanSerializerFactory, com.flurry.org.codehaus.jackson.map.ser.BasicSerializerFactory, com.flurry.org.codehaus.jackson.map.SerializerFactory
    public JsonSerializer createSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) {
        JsonSerializer findCustomSerializer = findCustomSerializer(javaType.getRawClass(), serializationConfig);
        return findCustomSerializer != null ? findCustomSerializer : super.createSerializer(serializationConfig, javaType, beanProperty);
    }

    protected JsonSerializer findCustomSerializer(Class cls, SerializationConfig serializationConfig) {
        JsonSerializer jsonSerializer;
        ClassKey classKey = new ClassKey(cls);
        if (this._directClassMappings == null || (jsonSerializer = (JsonSerializer) this._directClassMappings.get(classKey)) == null) {
            if (!cls.isEnum() || this._enumSerializerOverride == null) {
                if (this._transitiveClassMappings != null) {
                    for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                        classKey.reset(cls2);
                        JsonSerializer jsonSerializer2 = (JsonSerializer) this._transitiveClassMappings.get(classKey);
                        if (jsonSerializer2 != null) {
                            return jsonSerializer2;
                        }
                    }
                }
                if (this._interfaceMappings != null) {
                    classKey.reset(cls);
                    JsonSerializer jsonSerializer3 = (JsonSerializer) this._interfaceMappings.get(classKey);
                    if (jsonSerializer3 != null) {
                        return jsonSerializer3;
                    }
                    while (cls != null) {
                        JsonSerializer _findInterfaceMapping = _findInterfaceMapping(cls, classKey);
                        if (_findInterfaceMapping != null) {
                            return _findInterfaceMapping;
                        }
                        cls = cls.getSuperclass();
                    }
                }
                return null;
            }
            return this._enumSerializerOverride;
        }
        return jsonSerializer;
    }

    public void setEnumSerializer(JsonSerializer jsonSerializer) {
        this._enumSerializerOverride = jsonSerializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.BeanSerializerFactory, com.flurry.org.codehaus.jackson.map.SerializerFactory
    public SerializerFactory withConfig(SerializerFactory.Config config) {
        if (getClass() != CustomSerializerFactory.class) {
            throw new IllegalStateException("Subtype of CustomSerializerFactory (" + getClass().getName() + ") has not properly overridden method 'withAdditionalSerializers': can not instantiate subtype with additional serializer definitions");
        }
        return new CustomSerializerFactory(config);
    }
}
