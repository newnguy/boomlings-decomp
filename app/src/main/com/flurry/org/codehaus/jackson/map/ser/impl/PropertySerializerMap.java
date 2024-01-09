package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.type.JavaType;

/* loaded from: classes.dex */
public abstract class PropertySerializerMap {

    /* loaded from: classes.dex */
    final class Double extends PropertySerializerMap {
        private final JsonSerializer _serializer1;
        private final JsonSerializer _serializer2;
        private final Class _type1;
        private final Class _type2;

        public Double(Class cls, JsonSerializer jsonSerializer, Class cls2, JsonSerializer jsonSerializer2) {
            this._type1 = cls;
            this._serializer1 = jsonSerializer;
            this._type2 = cls2;
            this._serializer2 = jsonSerializer2;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.impl.PropertySerializerMap
        public PropertySerializerMap newWith(Class cls, JsonSerializer jsonSerializer) {
            return new Multi(new TypeAndSerializer[]{new TypeAndSerializer(this._type1, this._serializer1), new TypeAndSerializer(this._type2, this._serializer2)});
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.impl.PropertySerializerMap
        public JsonSerializer serializerFor(Class cls) {
            if (cls == this._type1) {
                return this._serializer1;
            }
            if (cls == this._type2) {
                return this._serializer2;
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class Empty extends PropertySerializerMap {
        protected static final Empty instance = new Empty();

        private Empty() {
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.impl.PropertySerializerMap
        public PropertySerializerMap newWith(Class cls, JsonSerializer jsonSerializer) {
            return new Single(cls, jsonSerializer);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.impl.PropertySerializerMap
        public JsonSerializer serializerFor(Class cls) {
            return null;
        }
    }

    /* loaded from: classes.dex */
    final class Multi extends PropertySerializerMap {
        private static final int MAX_ENTRIES = 8;
        private final TypeAndSerializer[] _entries;

        public Multi(TypeAndSerializer[] typeAndSerializerArr) {
            this._entries = typeAndSerializerArr;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.impl.PropertySerializerMap
        public PropertySerializerMap newWith(Class cls, JsonSerializer jsonSerializer) {
            int length = this._entries.length;
            if (length == MAX_ENTRIES) {
                return this;
            }
            TypeAndSerializer[] typeAndSerializerArr = new TypeAndSerializer[length + 1];
            System.arraycopy(this._entries, 0, typeAndSerializerArr, 0, length);
            typeAndSerializerArr[length] = new TypeAndSerializer(cls, jsonSerializer);
            return new Multi(typeAndSerializerArr);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.impl.PropertySerializerMap
        public JsonSerializer serializerFor(Class cls) {
            int length = this._entries.length;
            for (int i = 0; i < length; i++) {
                TypeAndSerializer typeAndSerializer = this._entries[i];
                if (typeAndSerializer.type == cls) {
                    return typeAndSerializer.serializer;
                }
            }
            return null;
        }
    }

    /* loaded from: classes.dex */
    public final class SerializerAndMapResult {
        public final PropertySerializerMap map;
        public final JsonSerializer serializer;

        public SerializerAndMapResult(JsonSerializer jsonSerializer, PropertySerializerMap propertySerializerMap) {
            this.serializer = jsonSerializer;
            this.map = propertySerializerMap;
        }
    }

    /* loaded from: classes.dex */
    final class Single extends PropertySerializerMap {
        private final JsonSerializer _serializer;
        private final Class _type;

        public Single(Class cls, JsonSerializer jsonSerializer) {
            this._type = cls;
            this._serializer = jsonSerializer;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.impl.PropertySerializerMap
        public PropertySerializerMap newWith(Class cls, JsonSerializer jsonSerializer) {
            return new Double(this._type, this._serializer, cls, jsonSerializer);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.impl.PropertySerializerMap
        public JsonSerializer serializerFor(Class cls) {
            if (cls == this._type) {
                return this._serializer;
            }
            return null;
        }
    }

    /* loaded from: classes.dex */
    final class TypeAndSerializer {
        public final JsonSerializer serializer;
        public final Class type;

        public TypeAndSerializer(Class cls, JsonSerializer jsonSerializer) {
            this.type = cls;
            this.serializer = jsonSerializer;
        }
    }

    public static PropertySerializerMap emptyMap() {
        return Empty.instance;
    }

    public final SerializerAndMapResult findAndAddSerializer(JavaType javaType, SerializerProvider serializerProvider, BeanProperty beanProperty) {
        JsonSerializer findValueSerializer = serializerProvider.findValueSerializer(javaType, beanProperty);
        return new SerializerAndMapResult(findValueSerializer, newWith(javaType.getRawClass(), findValueSerializer));
    }

    public final SerializerAndMapResult findAndAddSerializer(Class cls, SerializerProvider serializerProvider, BeanProperty beanProperty) {
        JsonSerializer findValueSerializer = serializerProvider.findValueSerializer(cls, beanProperty);
        return new SerializerAndMapResult(findValueSerializer, newWith(cls, findValueSerializer));
    }

    public abstract PropertySerializerMap newWith(Class cls, JsonSerializer jsonSerializer);

    public abstract JsonSerializer serializerFor(Class cls);
}
