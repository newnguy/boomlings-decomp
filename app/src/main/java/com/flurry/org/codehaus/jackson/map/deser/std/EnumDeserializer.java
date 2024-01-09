package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.annotate.JsonCachable;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.map.util.EnumResolver;
import java.lang.reflect.Method;

@JsonCachable
/* loaded from: classes.dex */
public class EnumDeserializer extends StdScalarDeserializer {
    protected final EnumResolver _resolver;

    /* loaded from: classes.dex */
    public class FactoryBasedDeserializer extends StdScalarDeserializer {
        protected final Class _enumClass;
        protected final Method _factory;

        public FactoryBasedDeserializer(Class cls, AnnotatedMethod annotatedMethod) {
            super(Enum.class);
            this._enumClass = cls;
            this._factory = annotatedMethod.getAnnotated();
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING || currentToken == JsonToken.FIELD_NAME) {
                try {
                    return this._factory.invoke(this._enumClass, jsonParser.getText());
                } catch (Exception e) {
                    ClassUtil.unwrapAndThrowAsIAE(e);
                    return null;
                }
            }
            throw deserializationContext.mappingException(this._enumClass);
        }
    }

    public EnumDeserializer(EnumResolver enumResolver) {
        super(Enum.class);
        this._resolver = enumResolver;
    }

    public static JsonDeserializer deserializerForCreator(DeserializationConfig deserializationConfig, Class cls, AnnotatedMethod annotatedMethod) {
        if (annotatedMethod.getParameterType(0) != String.class) {
            throw new IllegalArgumentException("Parameter #0 type for factory method (" + annotatedMethod + ") not suitable, must be java.lang.String");
        }
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            ClassUtil.checkAndFixAccess(annotatedMethod.getMember());
        }
        return new FactoryBasedDeserializer(cls, annotatedMethod);
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public Enum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        Enum findEnum;
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING || currentToken == JsonToken.FIELD_NAME) {
            findEnum = this._resolver.findEnum(jsonParser.getText());
            if (findEnum == null) {
                throw deserializationContext.weirdStringException(this._resolver.getEnumClass(), "value not one of declared Enum instance names");
            }
        } else if (currentToken != JsonToken.VALUE_NUMBER_INT) {
            throw deserializationContext.mappingException(this._resolver.getEnumClass());
        } else {
            if (deserializationContext.isEnabled(DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS)) {
                throw deserializationContext.mappingException("Not allowed to deserialize Enum value out of JSON number (disable DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS to allow)");
            }
            findEnum = this._resolver.getEnum(jsonParser.getIntValue());
            if (findEnum == null) {
                throw deserializationContext.weirdNumberException(this._resolver.getEnumClass(), "index value outside legal index range [0.." + this._resolver.lastValidIndex() + "]");
            }
        }
        return findEnum;
    }
}
