package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.ResolvableDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiator;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Collection;

@JacksonStdImpl
/* loaded from: classes.dex */
public final class StringCollectionDeserializer extends ContainerDeserializerBase implements ResolvableDeserializer {
    protected final JavaType _collectionType;
    protected JsonDeserializer _delegateDeserializer;
    protected final boolean _isDefaultDeserializer;
    protected final JsonDeserializer _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;

    protected StringCollectionDeserializer(StringCollectionDeserializer stringCollectionDeserializer) {
        super(stringCollectionDeserializer._valueClass);
        this._collectionType = stringCollectionDeserializer._collectionType;
        this._valueDeserializer = stringCollectionDeserializer._valueDeserializer;
        this._valueInstantiator = stringCollectionDeserializer._valueInstantiator;
        this._isDefaultDeserializer = stringCollectionDeserializer._isDefaultDeserializer;
    }

    public StringCollectionDeserializer(JavaType javaType, JsonDeserializer jsonDeserializer, ValueInstantiator valueInstantiator) {
        super(javaType.getRawClass());
        this._collectionType = javaType;
        this._valueDeserializer = jsonDeserializer;
        this._valueInstantiator = valueInstantiator;
        this._isDefaultDeserializer = isDefaultSerializer(jsonDeserializer);
    }

    private Collection deserializeUsingCustom(JsonParser jsonParser, DeserializationContext deserializationContext, Collection collection) {
        JsonDeserializer jsonDeserializer = this._valueDeserializer;
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == JsonToken.END_ARRAY) {
                return collection;
            }
            collection.add(nextToken == JsonToken.VALUE_NULL ? null : (String) jsonDeserializer.deserialize(jsonParser, deserializationContext));
        }
    }

    private final Collection handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext, Collection collection) {
        if (deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            JsonDeserializer jsonDeserializer = this._valueDeserializer;
            collection.add(jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? null : jsonDeserializer == null ? jsonParser.getText() : (String) jsonDeserializer.deserialize(jsonParser, deserializationContext));
            return collection;
        }
        throw deserializationContext.mappingException(this._collectionType.getRawClass());
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public Collection deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return this._delegateDeserializer != null ? (Collection) this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.deserialize(jsonParser, deserializationContext)) : deserialize(jsonParser, deserializationContext, (Collection) this._valueInstantiator.createUsingDefault());
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public Collection deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Collection collection) {
        if (!jsonParser.isExpectedStartArrayToken()) {
            return handleNonArray(jsonParser, deserializationContext, collection);
        }
        if (!this._isDefaultDeserializer) {
            return deserializeUsingCustom(jsonParser, deserializationContext, collection);
        }
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == JsonToken.END_ARRAY) {
                return collection;
            }
            collection.add(nextToken == JsonToken.VALUE_NULL ? null : jsonParser.getText());
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdDeserializer, com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.std.ContainerDeserializerBase
    public JsonDeserializer getContentDeserializer() {
        return this._valueDeserializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.std.ContainerDeserializerBase
    public JavaType getContentType() {
        return this._collectionType.getContentType();
    }

    @Override // com.flurry.org.codehaus.jackson.map.ResolvableDeserializer
    public void resolve(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider) {
        AnnotatedWithParams delegateCreator = this._valueInstantiator.getDelegateCreator();
        if (delegateCreator != null) {
            JavaType delegateType = this._valueInstantiator.getDelegateType();
            this._delegateDeserializer = findDeserializer(deserializationConfig, deserializerProvider, delegateType, new BeanProperty.Std(null, delegateType, null, delegateCreator));
        }
    }
}
