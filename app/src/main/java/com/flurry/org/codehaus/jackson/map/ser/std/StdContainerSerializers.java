package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class StdContainerSerializers {

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public class IndexedListSerializer extends AsArraySerializerBase {
        public IndexedListSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer jsonSerializer) {
            super(List.class, javaType, z, typeSerializer, beanProperty, jsonSerializer);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new IndexedListSerializer(this._elementType, this._staticTyping, typeSerializer, this._property, this._elementSerializer);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.AsArraySerializerBase
        public void serializeContents(List list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            if (this._elementSerializer != null) {
                serializeContentsUsing(list, jsonGenerator, serializerProvider, this._elementSerializer);
            } else if (this._valueTypeSerializer != null) {
                serializeTypedContents(list, jsonGenerator, serializerProvider);
            } else {
                int size = list.size();
                if (size != 0) {
                    int i = 0;
                    try {
                        PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                        while (i < size) {
                            Object obj = list.get(i);
                            if (obj == null) {
                                serializerProvider.defaultSerializeNull(jsonGenerator);
                            } else {
                                Class<?> cls = obj.getClass();
                                JsonSerializer serializerFor = propertySerializerMap.serializerFor(cls);
                                if (serializerFor == null) {
                                    JsonSerializer _findAndAddDynamic = this._elementType.hasGenericTypes() ? _findAndAddDynamic(propertySerializerMap, serializerProvider.constructSpecializedType(this._elementType, cls), serializerProvider) : _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                                    propertySerializerMap = this._dynamicSerializers;
                                    serializerFor = _findAndAddDynamic;
                                }
                                serializerFor.serialize(obj, jsonGenerator, serializerProvider);
                            }
                            i++;
                        }
                    } catch (Exception e) {
                        wrapAndThrow(serializerProvider, e, list, i);
                    }
                }
            }
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x0021 -> B:36:0x0016). Please submit an issue!!! */
        public void serializeContentsUsing(List list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer jsonSerializer) {
            int size = list.size();
            if (size == 0) {
                return;
            }
            TypeSerializer typeSerializer = this._valueTypeSerializer;
            int i = 0;
            while (i < size) {
                Object obj = list.get(i);
                if (obj == null) {
                    try {
                        serializerProvider.defaultSerializeNull(jsonGenerator);
                    } catch (Exception e) {
                        wrapAndThrow(serializerProvider, e, list, i);
                    }
                } else if (typeSerializer == null) {
                    jsonSerializer.serialize(obj, jsonGenerator, serializerProvider);
                } else {
                    jsonSerializer.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
                }
                i++;
            }
        }

        public void serializeTypedContents(List list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            int size = list.size();
            if (size == 0) {
                return;
            }
            int i = 0;
            try {
                TypeSerializer typeSerializer = this._valueTypeSerializer;
                PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                while (i < size) {
                    Object obj = list.get(i);
                    if (obj == null) {
                        serializerProvider.defaultSerializeNull(jsonGenerator);
                    } else {
                        Class<?> cls = obj.getClass();
                        JsonSerializer serializerFor = propertySerializerMap.serializerFor(cls);
                        if (serializerFor == null) {
                            JsonSerializer _findAndAddDynamic = this._elementType.hasGenericTypes() ? _findAndAddDynamic(propertySerializerMap, serializerProvider.constructSpecializedType(this._elementType, cls), serializerProvider) : _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                            propertySerializerMap = this._dynamicSerializers;
                            serializerFor = _findAndAddDynamic;
                        }
                        serializerFor.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
                    }
                    i++;
                }
            } catch (Exception e) {
                wrapAndThrow(serializerProvider, e, list, i);
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public class IteratorSerializer extends AsArraySerializerBase {
        public IteratorSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
            super(Iterator.class, javaType, z, typeSerializer, beanProperty, null);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new IteratorSerializer(this._elementType, this._staticTyping, typeSerializer, this._property);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.AsArraySerializerBase
        public void serializeContents(Iterator it, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            JsonSerializer jsonSerializer;
            Class<?> cls = null;
            if (it.hasNext()) {
                TypeSerializer typeSerializer = this._valueTypeSerializer;
                JsonSerializer jsonSerializer2 = null;
                do {
                    Object next = it.next();
                    if (next == null) {
                        serializerProvider.defaultSerializeNull(jsonGenerator);
                    } else {
                        Class<?> cls2 = next.getClass();
                        if (cls2 == cls) {
                            jsonSerializer = jsonSerializer2;
                        } else {
                            jsonSerializer2 = serializerProvider.findValueSerializer(cls2, this._property);
                            cls = cls2;
                            jsonSerializer = jsonSerializer2;
                        }
                        if (typeSerializer == null) {
                            jsonSerializer.serialize(next, jsonGenerator, serializerProvider);
                        } else {
                            jsonSerializer.serializeWithType(next, jsonGenerator, serializerProvider, typeSerializer);
                        }
                    }
                } while (it.hasNext());
            }
        }
    }

    public static ContainerSerializerBase collectionSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer jsonSerializer) {
        return new CollectionSerializer(javaType, z, typeSerializer, beanProperty, jsonSerializer);
    }

    public static JsonSerializer enumSetSerializer(JavaType javaType, BeanProperty beanProperty) {
        return new EnumSetSerializer(javaType, beanProperty);
    }

    public static ContainerSerializerBase indexedListSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer jsonSerializer) {
        return new IndexedListSerializer(javaType, z, typeSerializer, beanProperty, jsonSerializer);
    }

    public static ContainerSerializerBase iterableSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        return new IterableSerializer(javaType, z, typeSerializer, beanProperty);
    }

    public static ContainerSerializerBase iteratorSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        return new IteratorSerializer(javaType, z, typeSerializer, beanProperty);
    }
}
