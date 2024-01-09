package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.sql.Timestamp;

/* loaded from: classes.dex */
public class TimestampDeserializer extends StdScalarDeserializer {
    public TimestampDeserializer() {
        super(Timestamp.class);
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return new Timestamp(_parseDate(jsonParser, deserializationContext).getTime());
    }
}
