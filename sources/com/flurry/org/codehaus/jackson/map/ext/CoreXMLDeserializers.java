package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.StdScalarDeserializer;
import com.flurry.org.codehaus.jackson.map.util.Provider;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

/* loaded from: classes.dex */
public class CoreXMLDeserializers implements Provider {
    static final DatatypeFactory _dataTypeFactory;

    /* loaded from: classes.dex */
    public class DurationDeserializer extends FromStringDeserializer {
        public DurationDeserializer() {
            super(Duration.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public Duration _deserialize(String str, DeserializationContext deserializationContext) {
            return CoreXMLDeserializers._dataTypeFactory.newDuration(str);
        }
    }

    /* loaded from: classes.dex */
    public class GregorianCalendarDeserializer extends StdScalarDeserializer {
        public GregorianCalendarDeserializer() {
            super(XMLGregorianCalendar.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public XMLGregorianCalendar deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            Date _parseDate = _parseDate(jsonParser, deserializationContext);
            if (_parseDate == null) {
                return null;
            }
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(_parseDate);
            return CoreXMLDeserializers._dataTypeFactory.newXMLGregorianCalendar(gregorianCalendar);
        }
    }

    /* loaded from: classes.dex */
    public class QNameDeserializer extends FromStringDeserializer {
        public QNameDeserializer() {
            super(QName.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public QName _deserialize(String str, DeserializationContext deserializationContext) {
            return QName.valueOf(str);
        }
    }

    static {
        try {
            _dataTypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.util.Provider
    public Collection provide() {
        return Arrays.asList(new DurationDeserializer(), new GregorianCalendarDeserializer(), new QNameDeserializer());
    }
}
