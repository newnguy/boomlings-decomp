package com.flurry.org.codehaus.jackson.map.jsontype.impl;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.flurry.org.codehaus.jackson.type.JavaType;

/* loaded from: classes.dex */
public class AsArrayTypeDeserializer extends TypeDeserializerBase {
    @Deprecated
    public AsArrayTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        this(javaType, typeIdResolver, beanProperty, null);
    }

    public AsArrayTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty, Class cls) {
        super(javaType, typeIdResolver, beanProperty, cls);
    }

    private final Object _deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        boolean isExpectedStartArrayToken = jsonParser.isExpectedStartArrayToken();
        Object deserialize = _findDeserializer(deserializationContext, _locateTypeId(jsonParser, deserializationContext)).deserialize(jsonParser, deserializationContext);
        if (!isExpectedStartArrayToken || jsonParser.nextToken() == JsonToken.END_ARRAY) {
            return deserialize;
        }
        throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "expected closing END_ARRAY after type information and deserialized value");
    }

    protected final String _locateTypeId(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (!jsonParser.isExpectedStartArrayToken()) {
            if (!(this._idResolver instanceof TypeIdResolverBase) || this._defaultImpl == null) {
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_ARRAY, "need JSON Array to contain As.WRAPPER_ARRAY type information for class " + baseTypeName());
            }
            return ((TypeIdResolverBase) this._idResolver).idFromBaseType();
        } else if (jsonParser.nextToken() == JsonToken.VALUE_STRING) {
            String text = jsonParser.getText();
            jsonParser.nextToken();
            return text;
        } else if (!(this._idResolver instanceof TypeIdResolverBase) || this._defaultImpl == null) {
            throw deserializationContext.wrongTokenException(jsonParser, JsonToken.VALUE_STRING, "need JSON String that contains type id (for subtype of " + baseTypeName() + ")");
        } else {
            return ((TypeIdResolverBase) this._idResolver).idFromBaseType();
        }
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
        return JsonTypeInfo.As.WRAPPER_ARRAY;
    }
}
