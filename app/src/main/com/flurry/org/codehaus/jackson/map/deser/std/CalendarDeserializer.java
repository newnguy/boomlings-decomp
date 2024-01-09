package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.util.Calendar;
import java.util.Date;

@JacksonStdImpl
/* loaded from: classes.dex */
public class CalendarDeserializer extends StdScalarDeserializer {
    protected final Class _calendarClass;

    public CalendarDeserializer() {
        this(null);
    }

    public CalendarDeserializer(Class cls) {
        super(Calendar.class);
        this._calendarClass = cls;
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public Calendar deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        Date _parseDate = _parseDate(jsonParser, deserializationContext);
        if (_parseDate == null) {
            return null;
        }
        if (this._calendarClass == null) {
            return deserializationContext.constructCalendar(_parseDate);
        }
        try {
            Calendar calendar = (Calendar) this._calendarClass.newInstance();
            calendar.setTimeInMillis(_parseDate.getTime());
            return calendar;
        } catch (Exception e) {
            throw deserializationContext.instantiationException(this._calendarClass, e);
        }
    }
}
