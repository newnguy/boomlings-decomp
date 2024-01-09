package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.ResolvableSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.util.List;

@JacksonStdImpl
/* loaded from: classes.dex */
public final class IndexedStringListSerializer extends StaticListSerializerBase implements ResolvableSerializer {
    protected JsonSerializer _serializer;

    public IndexedStringListSerializer(BeanProperty beanProperty) {
        super(List.class, beanProperty);
    }

    private final void serializeContents(List list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        int i = 0;
        try {
            int size = list.size();
            while (i < size) {
                String str = (String) list.get(i);
                if (str == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    jsonGenerator.writeString(str);
                }
                i++;
            }
        } catch (Exception e) {
            wrapAndThrow(serializerProvider, e, list, i);
        }
    }

    private final void serializeUsingCustom(List list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        int i = 0;
        try {
            int size = list.size();
            JsonSerializer jsonSerializer = this._serializer;
            while (i < size) {
                String str = (String) list.get(i);
                if (str == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    jsonSerializer.serialize(str, jsonGenerator, serializerProvider);
                }
                i++;
            }
        } catch (Exception e) {
            wrapAndThrow(serializerProvider, e, list, i);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.StaticListSerializerBase
    protected JsonNode contentSchema() {
        return createSchemaNode("string", true);
    }

    @Override // com.flurry.org.codehaus.jackson.map.ResolvableSerializer
    public void resolve(SerializerProvider serializerProvider) {
        JsonSerializer findValueSerializer = serializerProvider.findValueSerializer(String.class, this._property);
        if (isDefaultSerializer(findValueSerializer)) {
            return;
        }
        this._serializer = findValueSerializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
    public void serialize(List list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeStartArray();
        if (this._serializer == null) {
            serializeContents(list, jsonGenerator, serializerProvider);
        } else {
            serializeUsingCustom(list, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonSerializer
    public void serializeWithType(List list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForArray(list, jsonGenerator);
        if (this._serializer == null) {
            serializeContents(list, jsonGenerator, serializerProvider);
        } else {
            serializeUsingCustom(list, jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffixForArray(list, jsonGenerator);
    }
}
