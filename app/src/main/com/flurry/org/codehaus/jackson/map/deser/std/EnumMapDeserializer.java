package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.util.EnumResolver;
import java.util.EnumMap;

/* loaded from: classes.dex */
public class EnumMapDeserializer extends StdDeserializer {
    protected final Class _enumClass;
    protected final JsonDeserializer _keyDeserializer;
    protected final JsonDeserializer _valueDeserializer;

    @Deprecated
    public EnumMapDeserializer(EnumResolver enumResolver, JsonDeserializer jsonDeserializer) {
        this(enumResolver.getEnumClass(), new EnumDeserializer(enumResolver), jsonDeserializer);
    }

    public EnumMapDeserializer(Class cls, JsonDeserializer jsonDeserializer, JsonDeserializer jsonDeserializer2) {
        super(EnumMap.class);
        this._enumClass = cls;
        this._keyDeserializer = jsonDeserializer;
        this._valueDeserializer = jsonDeserializer2;
    }

    private EnumMap constructMap() {
        return new EnumMap(this._enumClass);
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public EnumMap deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            throw deserializationContext.mappingException(EnumMap.class);
        }
        EnumMap constructMap = constructMap();
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            Enum r0 = (Enum) this._keyDeserializer.deserialize(jsonParser, deserializationContext);
            if (r0 == null) {
                throw deserializationContext.weirdStringException(this._enumClass, "value not one of declared Enum instance names");
            }
            constructMap.put((EnumMap) r0, (Enum) (jsonParser.nextToken() == JsonToken.VALUE_NULL ? null : this._valueDeserializer.deserialize(jsonParser, deserializationContext)));
        }
        return constructMap;
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdDeserializer, com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }
}
