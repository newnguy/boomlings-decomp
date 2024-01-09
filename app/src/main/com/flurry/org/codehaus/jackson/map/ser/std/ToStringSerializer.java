package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.lang.reflect.Type;

@JacksonStdImpl
/* loaded from: classes.dex */
public class ToStringSerializer extends SerializerBase {
    public static final ToStringSerializer instance = new ToStringSerializer();

    public ToStringSerializer() {
        super(Object.class);
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode("string", true);
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
    public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeString(obj.toString());
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonSerializer
    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForScalar(obj, jsonGenerator);
        serialize(obj, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForScalar(obj, jsonGenerator);
    }
}
