package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.map.type.ArrayType;
import com.flurry.org.codehaus.jackson.map.type.CollectionLikeType;
import com.flurry.org.codehaus.jackson.map.type.CollectionType;
import com.flurry.org.codehaus.jackson.map.type.MapLikeType;
import com.flurry.org.codehaus.jackson.map.type.MapType;
import com.flurry.org.codehaus.jackson.type.JavaType;

/* loaded from: classes.dex */
public interface Serializers {

    /* loaded from: classes.dex */
    public class Base implements Serializers {
        @Override // com.flurry.org.codehaus.jackson.map.Serializers
        public JsonSerializer findArraySerializer(SerializationConfig serializationConfig, ArrayType arrayType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer jsonSerializer) {
            return null;
        }

        @Override // com.flurry.org.codehaus.jackson.map.Serializers
        public JsonSerializer findCollectionLikeSerializer(SerializationConfig serializationConfig, CollectionLikeType collectionLikeType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer jsonSerializer) {
            return null;
        }

        @Override // com.flurry.org.codehaus.jackson.map.Serializers
        public JsonSerializer findCollectionSerializer(SerializationConfig serializationConfig, CollectionType collectionType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer jsonSerializer) {
            return null;
        }

        @Override // com.flurry.org.codehaus.jackson.map.Serializers
        public JsonSerializer findMapLikeSerializer(SerializationConfig serializationConfig, MapLikeType mapLikeType, BeanDescription beanDescription, BeanProperty beanProperty, JsonSerializer jsonSerializer, TypeSerializer typeSerializer, JsonSerializer jsonSerializer2) {
            return null;
        }

        @Override // com.flurry.org.codehaus.jackson.map.Serializers
        public JsonSerializer findMapSerializer(SerializationConfig serializationConfig, MapType mapType, BeanDescription beanDescription, BeanProperty beanProperty, JsonSerializer jsonSerializer, TypeSerializer typeSerializer, JsonSerializer jsonSerializer2) {
            return null;
        }

        @Override // com.flurry.org.codehaus.jackson.map.Serializers
        public JsonSerializer findSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, BeanProperty beanProperty) {
            return null;
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public class None extends Base {
    }

    JsonSerializer findArraySerializer(SerializationConfig serializationConfig, ArrayType arrayType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer jsonSerializer);

    JsonSerializer findCollectionLikeSerializer(SerializationConfig serializationConfig, CollectionLikeType collectionLikeType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer jsonSerializer);

    JsonSerializer findCollectionSerializer(SerializationConfig serializationConfig, CollectionType collectionType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer jsonSerializer);

    JsonSerializer findMapLikeSerializer(SerializationConfig serializationConfig, MapLikeType mapLikeType, BeanDescription beanDescription, BeanProperty beanProperty, JsonSerializer jsonSerializer, TypeSerializer typeSerializer, JsonSerializer jsonSerializer2);

    JsonSerializer findMapSerializer(SerializationConfig serializationConfig, MapType mapType, BeanDescription beanDescription, BeanProperty beanProperty, JsonSerializer jsonSerializer, TypeSerializer typeSerializer, JsonSerializer jsonSerializer2);

    JsonSerializer findSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, BeanProperty beanProperty);
}
