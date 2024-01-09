package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.io.SerializedString;
import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.flurry.org.codehaus.jackson.map.util.EnumValues;
import com.flurry.org.codehaus.jackson.node.ArrayNode;
import com.flurry.org.codehaus.jackson.node.ObjectNode;
import java.lang.reflect.Type;

@JacksonStdImpl
/* loaded from: classes.dex */
public class EnumSerializer extends ScalarSerializerBase {
    protected final EnumValues _values;

    public EnumSerializer(EnumValues enumValues) {
        super(Enum.class, false);
        this._values = enumValues;
    }

    public static EnumSerializer construct(Class cls, SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        return new EnumSerializer(serializationConfig.isEnabled(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING) ? EnumValues.constructFromToString(cls, annotationIntrospector) : EnumValues.constructFromName(cls, annotationIntrospector));
    }

    public EnumValues getEnumValues() {
        return this._values;
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        if (serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_ENUMS_USING_INDEX)) {
            return createSchemaNode("integer", true);
        }
        ObjectNode createSchemaNode = createSchemaNode("string", true);
        if (type != null && serializerProvider.constructType(type).isEnumType()) {
            ArrayNode putArray = createSchemaNode.putArray("enum");
            for (SerializedString serializedString : this._values.values()) {
                putArray.add(serializedString.getValue());
            }
        }
        return createSchemaNode;
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
    public final void serialize(Enum r2, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_ENUMS_USING_INDEX)) {
            jsonGenerator.writeNumber(r2.ordinal());
        } else {
            jsonGenerator.writeString(this._values.serializedValueFor(r2));
        }
    }
}
