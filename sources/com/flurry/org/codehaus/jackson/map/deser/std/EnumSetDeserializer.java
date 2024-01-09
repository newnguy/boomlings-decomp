package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.util.EnumResolver;
import java.util.EnumSet;

/* loaded from: classes.dex */
public class EnumSetDeserializer extends StdDeserializer {
    protected final Class _enumClass;
    protected final JsonDeserializer _enumDeserializer;

    public EnumSetDeserializer(EnumResolver enumResolver) {
        this(enumResolver.getEnumClass(), new EnumDeserializer(enumResolver));
    }

    public EnumSetDeserializer(Class cls, JsonDeserializer jsonDeserializer) {
        super(EnumSet.class);
        this._enumClass = cls;
        this._enumDeserializer = jsonDeserializer;
    }

    private EnumSet constructSet() {
        return EnumSet.noneOf(this._enumClass);
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public EnumSet deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (!jsonParser.isExpectedStartArrayToken()) {
            throw deserializationContext.mappingException(EnumSet.class);
        }
        EnumSet constructSet = constructSet();
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == JsonToken.END_ARRAY) {
                return constructSet;
            }
            if (nextToken == JsonToken.VALUE_NULL) {
                throw deserializationContext.mappingException(this._enumClass);
            }
            constructSet.add((Enum) this._enumDeserializer.deserialize(jsonParser, deserializationContext));
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdDeserializer, com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }
}
