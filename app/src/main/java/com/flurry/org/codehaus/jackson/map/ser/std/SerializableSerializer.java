package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.JsonSerializable;
import com.flurry.org.codehaus.jackson.map.JsonSerializableWithType;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;

@JacksonStdImpl
/* loaded from: classes.dex */
public class SerializableSerializer extends com.flurry.org.codehaus.jackson.map.ser.SerializerBase {
    public static final SerializableSerializer instance = new SerializableSerializer();

    public SerializableSerializer() {
        super(JsonSerializable.class);
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x004d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0061 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.flurry.org.codehaus.jackson.JsonNode getSchema(com.flurry.org.codehaus.jackson.map.SerializerProvider r8, java.lang.reflect.Type r9) {
        /*
            r7 = this;
            r2 = 0
            com.flurry.org.codehaus.jackson.node.ObjectNode r4 = r7.createObjectNode()
            java.lang.String r0 = "any"
            if (r9 == 0) goto L86
            com.flurry.org.codehaus.jackson.type.JavaType r1 = com.flurry.org.codehaus.jackson.map.type.TypeFactory.type(r9)
            java.lang.Class r1 = r1.getRawClass()
            java.lang.Class<com.flurry.org.codehaus.jackson.schema.JsonSerializableSchema> r3 = com.flurry.org.codehaus.jackson.schema.JsonSerializableSchema.class
            boolean r3 = r1.isAnnotationPresent(r3)
            if (r3 == 0) goto L86
            java.lang.Class<com.flurry.org.codehaus.jackson.schema.JsonSerializableSchema> r0 = com.flurry.org.codehaus.jackson.schema.JsonSerializableSchema.class
            java.lang.annotation.Annotation r0 = r1.getAnnotation(r0)
            com.flurry.org.codehaus.jackson.schema.JsonSerializableSchema r0 = (com.flurry.org.codehaus.jackson.schema.JsonSerializableSchema) r0
            java.lang.String r3 = r0.schemaType()
            java.lang.String r1 = "##irrelevant"
            java.lang.String r5 = r0.schemaObjectPropertiesDefinition()
            boolean r1 = r1.equals(r5)
            if (r1 != 0) goto L84
            java.lang.String r1 = r0.schemaObjectPropertiesDefinition()
        L35:
            java.lang.String r5 = "##irrelevant"
            java.lang.String r6 = r0.schemaItemDefinition()
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L82
            java.lang.String r2 = r0.schemaItemDefinition()
            r0 = r3
        L46:
            java.lang.String r3 = "type"
            r4.put(r3, r0)
            if (r1 == 0) goto L5f
            java.lang.String r3 = "properties"
            com.flurry.org.codehaus.jackson.map.ObjectMapper r0 = new com.flurry.org.codehaus.jackson.map.ObjectMapper     // Catch: java.io.IOException -> L74
            r0.<init>()     // Catch: java.io.IOException -> L74
            java.lang.Class<com.flurry.org.codehaus.jackson.JsonNode> r5 = com.flurry.org.codehaus.jackson.JsonNode.class
            java.lang.Object r0 = r0.readValue(r1, r5)     // Catch: java.io.IOException -> L74
            com.flurry.org.codehaus.jackson.JsonNode r0 = (com.flurry.org.codehaus.jackson.JsonNode) r0     // Catch: java.io.IOException -> L74
            r4.put(r3, r0)     // Catch: java.io.IOException -> L74
        L5f:
            if (r2 == 0) goto L73
            java.lang.String r1 = "items"
            com.flurry.org.codehaus.jackson.map.ObjectMapper r0 = new com.flurry.org.codehaus.jackson.map.ObjectMapper     // Catch: java.io.IOException -> L7b
            r0.<init>()     // Catch: java.io.IOException -> L7b
            java.lang.Class<com.flurry.org.codehaus.jackson.JsonNode> r3 = com.flurry.org.codehaus.jackson.JsonNode.class
            java.lang.Object r0 = r0.readValue(r2, r3)     // Catch: java.io.IOException -> L7b
            com.flurry.org.codehaus.jackson.JsonNode r0 = (com.flurry.org.codehaus.jackson.JsonNode) r0     // Catch: java.io.IOException -> L7b
            r4.put(r1, r0)     // Catch: java.io.IOException -> L7b
        L73:
            return r4
        L74:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>(r0)
            throw r1
        L7b:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>(r0)
            throw r1
        L82:
            r0 = r3
            goto L46
        L84:
            r1 = r2
            goto L35
        L86:
            r1 = r2
            goto L46
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.map.ser.std.SerializableSerializer.getSchema(com.flurry.org.codehaus.jackson.map.SerializerProvider, java.lang.reflect.Type):com.flurry.org.codehaus.jackson.JsonNode");
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
    public void serialize(JsonSerializable jsonSerializable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonSerializable.serialize(jsonGenerator, serializerProvider);
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonSerializer
    public final void serializeWithType(JsonSerializable jsonSerializable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        if (jsonSerializable instanceof JsonSerializableWithType) {
            ((JsonSerializableWithType) jsonSerializable).serializeWithType(jsonGenerator, serializerProvider, typeSerializer);
        } else {
            serialize(jsonSerializable, jsonGenerator, serializerProvider);
        }
    }
}
