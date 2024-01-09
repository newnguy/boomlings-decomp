package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.lang.reflect.Type;
import java.util.Calendar;

@JacksonStdImpl
/* loaded from: classes.dex */
public class CalendarSerializer extends ScalarSerializerBase {
    public static CalendarSerializer instance = new CalendarSerializer();

    public CalendarSerializer() {
        super(Calendar.class);
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode(serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS) ? "number" : "string", true);
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
    public void serialize(Calendar calendar, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        serializerProvider.defaultSerializeDateValue(calendar.getTimeInMillis(), jsonGenerator);
    }
}
