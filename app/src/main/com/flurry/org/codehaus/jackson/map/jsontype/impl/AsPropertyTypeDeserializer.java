package com.flurry.org.codehaus.jackson.map.jsontype.impl;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.flurry.org.codehaus.jackson.type.JavaType;
import com.flurry.org.codehaus.jackson.util.JsonParserSequence;
import com.flurry.org.codehaus.jackson.util.TokenBuffer;

/* loaded from: classes.dex */
public class AsPropertyTypeDeserializer extends AsArrayTypeDeserializer {
    protected final String _typePropertyName;

    public AsPropertyTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty, Class cls, String str) {
        super(javaType, typeIdResolver, beanProperty, cls);
        this._typePropertyName = str;
    }

    @Deprecated
    public AsPropertyTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty, String str) {
        this(javaType, typeIdResolver, beanProperty, null, str);
    }

    protected Object _deserializeIfNatural(JsonParser jsonParser, DeserializationContext deserializationContext) {
        switch (jsonParser.getCurrentToken()) {
            case VALUE_STRING:
                if (this._baseType.getRawClass().isAssignableFrom(String.class)) {
                    return jsonParser.getText();
                }
                break;
            case VALUE_NUMBER_INT:
                if (this._baseType.getRawClass().isAssignableFrom(Integer.class)) {
                    return Integer.valueOf(jsonParser.getIntValue());
                }
                break;
            case VALUE_NUMBER_FLOAT:
                if (this._baseType.getRawClass().isAssignableFrom(Double.class)) {
                    return Double.valueOf(jsonParser.getDoubleValue());
                }
                break;
            case VALUE_TRUE:
                if (this._baseType.getRawClass().isAssignableFrom(Boolean.class)) {
                    return Boolean.TRUE;
                }
                break;
            case VALUE_FALSE:
                if (this._baseType.getRawClass().isAssignableFrom(Boolean.class)) {
                    return Boolean.FALSE;
                }
                break;
        }
        return null;
    }

    protected Object _deserializeTypedUsingDefaultImpl(JsonParser jsonParser, DeserializationContext deserializationContext, TokenBuffer tokenBuffer) {
        if (this._defaultImpl != null) {
            JsonDeserializer _findDefaultImplDeserializer = _findDefaultImplDeserializer(deserializationContext);
            if (tokenBuffer != null) {
                tokenBuffer.writeEndObject();
                jsonParser = tokenBuffer.asParser(jsonParser);
                jsonParser.nextToken();
            }
            return _findDefaultImplDeserializer.deserialize(jsonParser, deserializationContext);
        }
        Object _deserializeIfNatural = _deserializeIfNatural(jsonParser, deserializationContext);
        if (_deserializeIfNatural == null) {
            if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
                return super.deserializeTypedFromAny(jsonParser, deserializationContext);
            }
            throw deserializationContext.wrongTokenException(jsonParser, JsonToken.FIELD_NAME, "missing property '" + this._typePropertyName + "' that is to contain type id  (for class " + baseTypeName() + ")");
        }
        return _deserializeIfNatural;
    }

    @Override // com.flurry.org.codehaus.jackson.map.jsontype.impl.AsArrayTypeDeserializer, com.flurry.org.codehaus.jackson.map.TypeDeserializer
    public Object deserializeTypedFromAny(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return jsonParser.getCurrentToken() == JsonToken.START_ARRAY ? super.deserializeTypedFromArray(jsonParser, deserializationContext) : deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    @Override // com.flurry.org.codehaus.jackson.map.jsontype.impl.AsArrayTypeDeserializer, com.flurry.org.codehaus.jackson.map.TypeDeserializer
    public Object deserializeTypedFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        } else if (currentToken == JsonToken.START_ARRAY) {
            return _deserializeTypedUsingDefaultImpl(jsonParser, deserializationContext, null);
        } else {
            if (currentToken != JsonToken.FIELD_NAME) {
                return _deserializeTypedUsingDefaultImpl(jsonParser, deserializationContext, null);
            }
        }
        JsonToken jsonToken = currentToken;
        TokenBuffer tokenBuffer = null;
        while (jsonToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (this._typePropertyName.equals(currentName)) {
                JsonDeserializer _findDeserializer = _findDeserializer(deserializationContext, jsonParser.getText());
                if (tokenBuffer != null) {
                    jsonParser = JsonParserSequence.createFlattened(tokenBuffer.asParser(jsonParser), jsonParser);
                }
                jsonParser.nextToken();
                return _findDeserializer.deserialize(jsonParser, deserializationContext);
            }
            if (tokenBuffer == null) {
                tokenBuffer = new TokenBuffer(null);
            }
            tokenBuffer.writeFieldName(currentName);
            tokenBuffer.copyCurrentStructure(jsonParser);
            jsonToken = jsonParser.nextToken();
        }
        return _deserializeTypedUsingDefaultImpl(jsonParser, deserializationContext, tokenBuffer);
    }

    @Override // com.flurry.org.codehaus.jackson.map.jsontype.impl.TypeDeserializerBase, com.flurry.org.codehaus.jackson.map.TypeDeserializer
    public String getPropertyName() {
        return this._typePropertyName;
    }

    @Override // com.flurry.org.codehaus.jackson.map.jsontype.impl.AsArrayTypeDeserializer, com.flurry.org.codehaus.jackson.map.jsontype.impl.TypeDeserializerBase, com.flurry.org.codehaus.jackson.map.TypeDeserializer
    public JsonTypeInfo.As getTypeInclusion() {
        return JsonTypeInfo.As.PROPERTY;
    }
}
