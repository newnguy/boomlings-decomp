package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class AtomicBooleanDeserializer extends StdScalarDeserializer {
    public AtomicBooleanDeserializer() {
        super(AtomicBoolean.class);
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public AtomicBoolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return new AtomicBoolean(_parseBooleanPrimitive(jsonParser, deserializationContext));
    }
}
