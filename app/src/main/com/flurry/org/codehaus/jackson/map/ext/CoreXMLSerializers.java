package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.ser.std.CalendarSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase;
import com.flurry.org.codehaus.jackson.map.ser.std.ToStringSerializer;
import com.flurry.org.codehaus.jackson.map.util.Provider;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

/* loaded from: classes.dex */
public class CoreXMLSerializers implements Provider {
    static final HashMap _serializers = new HashMap();

    /* loaded from: classes.dex */
    public class XMLGregorianCalendarSerializer extends SerializerBase {
        public XMLGregorianCalendarSerializer() {
            super(XMLGregorianCalendar.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return CalendarSerializer.instance.getSchema(serializerProvider, type);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(XMLGregorianCalendar xMLGregorianCalendar, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            CalendarSerializer.instance.serialize((Calendar) xMLGregorianCalendar.toGregorianCalendar(), jsonGenerator, serializerProvider);
        }
    }

    static {
        ToStringSerializer toStringSerializer = ToStringSerializer.instance;
        _serializers.put(Duration.class, toStringSerializer);
        _serializers.put(XMLGregorianCalendar.class, new XMLGregorianCalendarSerializer());
        _serializers.put(QName.class, toStringSerializer);
    }

    @Override // com.flurry.org.codehaus.jackson.map.util.Provider
    public Collection provide() {
        return _serializers.entrySet();
    }
}
