package com.flurry.org.codehaus.jackson.map.jsontype.impl;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.flurry.org.codehaus.jackson.type.JavaType;

/* loaded from: classes.dex */
public class AsWrapperTypeDeserializer extends TypeDeserializerBase {
    @Deprecated
    public AsWrapperTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        this(javaType, typeIdResolver, beanProperty, null);
    }

    public AsWrapperTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty, Class cls) {
        super(javaType, typeIdResolver, beanProperty, null);
    }

    private final Object _deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_OBJECT, "need JSON Object to contain As.WRAPPER_OBJECT type information for class " + baseTypeName());
        }
        if (jsonParser.nextToken() != JsonToken.FIELD_NAME) {
            throw deserializationContext.wrongTokenException(jsonParser, JsonToken.FIELD_NAME, "need JSON String that contains type id (for subtype of " + baseTypeName() + ")");
        }
        JsonDeserializer _findDeserializer = _findDeserializer(deserializationContext, jsonParser.getText());
        jsonParser.nextToken();
        Object deserialize = _findDeserializer.deserialize(jsonParser, deserializationContext);
        if (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_OBJECT, "expected closing END_OBJECT after type information and deserialized value");
        }
        return deserialize;
    }

    @Override // com.flurry.org.codehaus.jackson.map.TypeDeserializer
    public Object deserializeTypedFromAny(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return _deserialize(jsonParser, deserializationContext);
    }

    @Override // com.flurry.org.codehaus.jackson.map.TypeDeserializer
    public Object deserializeTypedFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return _deserialize(jsonParser, deserializationContext);
    }

    @Override // com.flurry.org.codehaus.jackson.map.TypeDeserializer
    public Object deserializeTypedFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return _deserialize(jsonParser, deserializationContext);
    }

    @Override // com.flurry.org.codehaus.jackson.map.TypeDeserializer
    public Object deserializeTypedFromScalar(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return _deserialize(jsonParser, deserializationContext);
    }

    @Override // com.flurry.org.codehaus.jackson.map.jsontype.impl.TypeDeserializerBase, com.flurry.org.codehaus.jackson.map.TypeDeserializer
    public JsonTypeInfo.As getTypeInclusion() {
        return JsonTypeInfo.As.WRAPPER_OBJECT;
    }
}
