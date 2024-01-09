package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes.dex */
public class StdKeySerializers {
    protected static final JsonSerializer DEFAULT_KEY_SERIALIZER = new StdKeySerializer();
    protected static final JsonSerializer DEFAULT_STRING_SERIALIZER = new StringKeySerializer();

    /* loaded from: classes.dex */
    public class CalendarKeySerializer extends SerializerBase {
        protected static final JsonSerializer instance = new CalendarKeySerializer();

        public CalendarKeySerializer() {
            super(Calendar.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Calendar calendar, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            serializerProvider.defaultSerializeDateKey(calendar.getTimeInMillis(), jsonGenerator);
        }
    }

    /* loaded from: classes.dex */
    public class DateKeySerializer extends SerializerBase {
        protected static final JsonSerializer instance = new DateKeySerializer();

        public DateKeySerializer() {
            super(Date.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            serializerProvider.defaultSerializeDateKey(date, jsonGenerator);
        }
    }

    /* loaded from: classes.dex */
    public class StringKeySerializer extends SerializerBase {
        public StringKeySerializer() {
            super(String.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(String str, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeFieldName(str);
        }
    }

    private StdKeySerializers() {
    }

    public static JsonSerializer getStdKeySerializer(JavaType javaType) {
        if (javaType == null) {
            return DEFAULT_KEY_SERIALIZER;
        }
        Class rawClass = javaType.getRawClass();
        return rawClass == String.class ? DEFAULT_STRING_SERIALIZER : rawClass == Object.class ? DEFAULT_KEY_SERIALIZER : Date.class.isAssignableFrom(rawClass) ? DateKeySerializer.instance : Calendar.class.isAssignableFrom(rawClass) ? CalendarKeySerializer.instance : DEFAULT_KEY_SERIALIZER;
    }
}
