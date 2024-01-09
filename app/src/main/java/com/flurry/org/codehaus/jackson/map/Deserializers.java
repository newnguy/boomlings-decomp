package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.map.type.ArrayType;
import com.flurry.org.codehaus.jackson.map.type.CollectionLikeType;
import com.flurry.org.codehaus.jackson.map.type.CollectionType;
import com.flurry.org.codehaus.jackson.map.type.MapLikeType;
import com.flurry.org.codehaus.jackson.map.type.MapType;
import com.flurry.org.codehaus.jackson.type.JavaType;

/* loaded from: classes.dex */
public interface Deserializers {

    /* loaded from: classes.dex */
    public class Base implements Deserializers {
        @Override // com.flurry.org.codehaus.jackson.map.Deserializers
        public JsonDeserializer findArrayDeserializer(ArrayType arrayType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer) {
            return null;
        }

        @Override // com.flurry.org.codehaus.jackson.map.Deserializers
        public JsonDeserializer findBeanDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanDescription beanDescription, BeanProperty beanProperty) {
            return null;
        }

        @Override // com.flurry.org.codehaus.jackson.map.Deserializers
        public JsonDeserializer findCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanDescription beanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer) {
            return null;
        }

        @Override // com.flurry.org.codehaus.jackson.map.Deserializers
        public JsonDeserializer findCollectionLikeDeserializer(CollectionLikeType collectionLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanDescription beanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer) {
            return null;
        }

        @Override // com.flurry.org.codehaus.jackson.map.Deserializers
        public JsonDeserializer findEnumDeserializer(Class cls, DeserializationConfig deserializationConfig, BeanDescription beanDescription, BeanProperty beanProperty) {
            return null;
        }

        @Override // com.flurry.org.codehaus.jackson.map.Deserializers
        public JsonDeserializer findMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanDescription beanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer) {
            return null;
        }

        @Override // com.flurry.org.codehaus.jackson.map.Deserializers
        public JsonDeserializer findMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanDescription beanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer) {
            return null;
        }

        @Override // com.flurry.org.codehaus.jackson.map.Deserializers
        public JsonDeserializer findTreeNodeDeserializer(Class cls, DeserializationConfig deserializationConfig, BeanProperty beanProperty) {
            return null;
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public class None extends Base {
    }

    JsonDeserializer findArrayDeserializer(ArrayType arrayType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer);

    JsonDeserializer findBeanDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanDescription beanDescription, BeanProperty beanProperty);

    JsonDeserializer findCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanDescription beanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer);

    JsonDeserializer findCollectionLikeDeserializer(CollectionLikeType collectionLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanDescription beanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer);

    JsonDeserializer findEnumDeserializer(Class cls, DeserializationConfig deserializationConfig, BeanDescription beanDescription, BeanProperty beanProperty);

    JsonDeserializer findMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanDescription beanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer);

    JsonDeserializer findMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanDescription beanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer);

    JsonDeserializer findTreeNodeDeserializer(Class cls, DeserializationConfig deserializationConfig, BeanProperty beanProperty);
}
