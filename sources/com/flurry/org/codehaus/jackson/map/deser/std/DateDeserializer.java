package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.util.Date;

/* loaded from: classes.dex */
public class DateDeserializer extends StdScalarDeserializer {
    public DateDeserializer() {
        super(Date.class);
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return _parseDate(jsonParser, deserializationContext);
    }
}
