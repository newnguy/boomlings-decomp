package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.ResolvableDeserializer;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public class AtomicReferenceDeserializer extends StdScalarDeserializer implements ResolvableDeserializer {
    protected final BeanProperty _property;
    protected final JavaType _referencedType;
    protected JsonDeserializer _valueDeserializer;

    public AtomicReferenceDeserializer(JavaType javaType, BeanProperty beanProperty) {
        super(AtomicReference.class);
        this._referencedType = javaType;
        this._property = beanProperty;
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public AtomicReference deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return new AtomicReference(this._valueDeserializer.deserialize(jsonParser, deserializationContext));
    }

    @Override // com.flurry.org.codehaus.jackson.map.ResolvableDeserializer
    public void resolve(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider) {
        this._valueDeserializer = deserializerProvider.findValueDeserializer(deserializationConfig, this._referencedType, this._property);
    }
}
