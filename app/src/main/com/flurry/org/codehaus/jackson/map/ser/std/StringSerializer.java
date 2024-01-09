package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.lang.reflect.Type;

@JacksonStdImpl
/* loaded from: classes.dex */
public final class StringSerializer extends NonTypedScalarSerializerBase {
    public StringSerializer() {
        super(String.class);
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode("string", true);
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
    public void serialize(String str, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeString(str);
    }
}
