package com.flurry.org.codehaus.jackson.map.module;

import com.flurry.org.codehaus.jackson.map.BeanDescription;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.Serializers;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.type.ArrayType;
import com.flurry.org.codehaus.jackson.map.type.ClassKey;
import com.flurry.org.codehaus.jackson.map.type.CollectionLikeType;
import com.flurry.org.codehaus.jackson.map.type.CollectionType;
import com.flurry.org.codehaus.jackson.map.type.MapLikeType;
import com.flurry.org.codehaus.jackson.map.type.MapType;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.HashMap;

/* loaded from: classes.dex */
public class SimpleSerializers extends Serializers.Base {
    protected HashMap _classMappings = null;
    protected HashMap _interfaceMappings = null;

    private void _addSerializer(Class cls, JsonSerializer jsonSerializer) {
        ClassKey classKey = new ClassKey(cls);
        if (cls.isInterface()) {
            if (this._interfaceMappings == null) {
                this._interfaceMappings = new HashMap();
            }
            this._interfaceMappings.put(classKey, jsonSerializer);
            return;
        }
        if (this._classMappings == null) {
            this._classMappings = new HashMap();
        }
        this._classMappings.put(classKey, jsonSerializer);
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

    public void addSerializer(JsonSerializer jsonSerializer) {
        Class handledType = jsonSerializer.handledType();
        if (handledType == null || handledType == Object.class) {
            throw new IllegalArgumentException("JsonSerializer of type " + jsonSerializer.getClass().getName() + " does not define valid handledType() -- must either register with method that takes type argument  or make serializer extend 'org.codehaus.jackson.map.ser.std.SerializerBase'");
        }
        _addSerializer(handledType, jsonSerializer);
    }

    public void addSerializer(Class cls, JsonSerializer jsonSerializer) {
        _addSerializer(cls, jsonSerializer);
    }

    @Override // com.flurry.org.codehaus.jackson.map.Serializers.Base, com.flurry.org.codehaus.jackson.map.Serializers
    public JsonSerializer findArraySerializer(SerializationConfig serializationConfig, ArrayType arrayType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer jsonSerializer) {
        return findSerializer(serializationConfig, arrayType, beanDescription, beanProperty);
    }

    @Override // com.flurry.org.codehaus.jackson.map.Serializers.Base, com.flurry.org.codehaus.jackson.map.Serializers
    public JsonSerializer findCollectionLikeSerializer(SerializationConfig serializationConfig, CollectionLikeType collectionLikeType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer jsonSerializer) {
        return findSerializer(serializationConfig, collectionLikeType, beanDescription, beanProperty);
    }

    @Override // com.flurry.org.codehaus.jackson.map.Serializers.Base, com.flurry.org.codehaus.jackson.map.Serializers
    public JsonSerializer findCollectionSerializer(SerializationConfig serializationConfig, CollectionType collectionType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer jsonSerializer) {
        return findSerializer(serializationConfig, collectionType, beanDescription, beanProperty);
    }

    @Override // com.flurry.org.codehaus.jackson.map.Serializers.Base, com.flurry.org.codehaus.jackson.map.Serializers
    public JsonSerializer findMapLikeSerializer(SerializationConfig serializationConfig, MapLikeType mapLikeType, BeanDescription beanDescription, BeanProperty beanProperty, JsonSerializer jsonSerializer, TypeSerializer typeSerializer, JsonSerializer jsonSerializer2) {
        return findSerializer(serializationConfig, mapLikeType, beanDescription, beanProperty);
    }

    @Override // com.flurry.org.codehaus.jackson.map.Serializers.Base, com.flurry.org.codehaus.jackson.map.Serializers
    public JsonSerializer findMapSerializer(SerializationConfig serializationConfig, MapType mapType, BeanDescription beanDescription, BeanProperty beanProperty, JsonSerializer jsonSerializer, TypeSerializer typeSerializer, JsonSerializer jsonSerializer2) {
        return findSerializer(serializationConfig, mapType, beanDescription, beanProperty);
    }

    @Override // com.flurry.org.codehaus.jackson.map.Serializers.Base, com.flurry.org.codehaus.jackson.map.Serializers
    public JsonSerializer findSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, BeanProperty beanProperty) {
        JsonSerializer _findInterfaceMapping;
        JsonSerializer jsonSerializer;
        Class rawClass = javaType.getRawClass();
        ClassKey classKey = new ClassKey(rawClass);
        if (rawClass.isInterface()) {
            if (this._interfaceMappings != null && (jsonSerializer = (JsonSerializer) this._interfaceMappings.get(classKey)) != null) {
                return jsonSerializer;
            }
        } else if (this._classMappings != null) {
            JsonSerializer jsonSerializer2 = (JsonSerializer) this._classMappings.get(classKey);
            if (jsonSerializer2 != null) {
                return jsonSerializer2;
            }
            for (Class cls = rawClass; cls != null; cls = cls.getSuperclass()) {
                classKey.reset(cls);
                JsonSerializer jsonSerializer3 = (JsonSerializer) this._classMappings.get(classKey);
                if (jsonSerializer3 != null) {
                    return jsonSerializer3;
                }
            }
        }
        if (this._interfaceMappings != null) {
            JsonSerializer _findInterfaceMapping2 = _findInterfaceMapping(rawClass, classKey);
            if (_findInterfaceMapping2 != null) {
                return _findInterfaceMapping2;
            }
            if (!rawClass.isInterface()) {
                Class cls2 = rawClass;
                do {
                    cls2 = cls2.getSuperclass();
                    if (cls2 != null) {
                        _findInterfaceMapping = _findInterfaceMapping(cls2, classKey);
                    }
                } while (_findInterfaceMapping == null);
                return _findInterfaceMapping;
            }
        }
        return null;
    }
}
