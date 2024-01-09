package com.flurry.org.codehaus.jackson.map.module;

import com.flurry.org.codehaus.jackson.map.BeanDescription;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.Deserializers;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.type.ArrayType;
import com.flurry.org.codehaus.jackson.map.type.ClassKey;
import com.flurry.org.codehaus.jackson.map.type.CollectionLikeType;
import com.flurry.org.codehaus.jackson.map.type.CollectionType;
import com.flurry.org.codehaus.jackson.map.type.MapLikeType;
import com.flurry.org.codehaus.jackson.map.type.MapType;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.HashMap;

/* loaded from: classes.dex */
public class SimpleDeserializers implements Deserializers {
    protected HashMap _classMappings = null;

    public void addDeserializer(Class cls, JsonDeserializer jsonDeserializer) {
        ClassKey classKey = new ClassKey(cls);
        if (this._classMappings == null) {
            this._classMappings = new HashMap();
        }
        this._classMappings.put(classKey, jsonDeserializer);
    }

    @Override // com.flurry.org.codehaus.jackson.map.Deserializers
    public JsonDeserializer findArrayDeserializer(ArrayType arrayType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer) {
        if (this._classMappings == null) {
            return null;
        }
        return (JsonDeserializer) this._classMappings.get(new ClassKey(arrayType.getRawClass()));
    }

    @Override // com.flurry.org.codehaus.jackson.map.Deserializers
    public JsonDeserializer findBeanDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanDescription beanDescription, BeanProperty beanProperty) {
        if (this._classMappings == null) {
            return null;
        }
        return (JsonDeserializer) this._classMappings.get(new ClassKey(javaType.getRawClass()));
    }

    @Override // com.flurry.org.codehaus.jackson.map.Deserializers
    public JsonDeserializer findCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanDescription beanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer) {
        if (this._classMappings == null) {
            return null;
        }
        return (JsonDeserializer) this._classMappings.get(new ClassKey(collectionType.getRawClass()));
    }

    @Override // com.flurry.org.codehaus.jackson.map.Deserializers
    public JsonDeserializer findCollectionLikeDeserializer(CollectionLikeType collectionLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanDescription beanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer) {
        if (this._classMappings == null) {
            return null;
        }
        return (JsonDeserializer) this._classMappings.get(new ClassKey(collectionLikeType.getRawClass()));
    }

    @Override // com.flurry.org.codehaus.jackson.map.Deserializers
    public JsonDeserializer findEnumDeserializer(Class cls, DeserializationConfig deserializationConfig, BeanDescription beanDescription, BeanProperty beanProperty) {
        if (this._classMappings == null) {
            return null;
        }
        return (JsonDeserializer) this._classMappings.get(new ClassKey(cls));
    }

    @Override // com.flurry.org.codehaus.jackson.map.Deserializers
    public JsonDeserializer findMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanDescription beanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer) {
        if (this._classMappings == null) {
            return null;
        }
        return (JsonDeserializer) this._classMappings.get(new ClassKey(mapType.getRawClass()));
    }

    @Override // com.flurry.org.codehaus.jackson.map.Deserializers
    public JsonDeserializer findMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanDescription beanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer) {
        if (this._classMappings == null) {
            return null;
        }
        return (JsonDeserializer) this._classMappings.get(new ClassKey(mapLikeType.getRawClass()));
    }

    @Override // com.flurry.org.codehaus.jackson.map.Deserializers
    public JsonDeserializer findTreeNodeDeserializer(Class cls, DeserializationConfig deserializationConfig, BeanProperty beanProperty) {
        if (this._classMappings == null) {
            return null;
        }
        return (JsonDeserializer) this._classMappings.get(new ClassKey(cls));
    }
}
