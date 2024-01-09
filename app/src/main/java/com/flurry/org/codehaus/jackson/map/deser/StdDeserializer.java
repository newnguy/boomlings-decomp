package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.Base64Variants;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.type.JavaType;

@Deprecated
/* loaded from: classes.dex */
public abstract class StdDeserializer extends com.flurry.org.codehaus.jackson.map.deser.std.StdDeserializer {

    @JacksonStdImpl
    @Deprecated
    /* loaded from: classes.dex */
    public class CalendarDeserializer extends com.flurry.org.codehaus.jackson.map.deser.std.CalendarDeserializer {
        public CalendarDeserializer() {
            StdDeserializer.this = r1;
        }
    }

    @JacksonStdImpl
    @Deprecated
    /* loaded from: classes.dex */
    public class ClassDeserializer extends com.flurry.org.codehaus.jackson.map.deser.std.ClassDeserializer {
        public ClassDeserializer() {
            StdDeserializer.this = r1;
        }
    }

    @JacksonStdImpl
    @Deprecated
    /* loaded from: classes.dex */
    public final class StringDeserializer extends com.flurry.org.codehaus.jackson.map.deser.std.StdScalarDeserializer {
        public StringDeserializer() {
            super(String.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                return jsonParser.getText();
            }
            if (currentToken != JsonToken.VALUE_EMBEDDED_OBJECT) {
                if (currentToken.isScalarValue()) {
                    return jsonParser.getText();
                }
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
            Object embeddedObject = jsonParser.getEmbeddedObject();
            if (embeddedObject == null) {
                return null;
            }
            return embeddedObject instanceof byte[] ? Base64Variants.getDefaultVariant().encode((byte[]) embeddedObject, false) : embeddedObject.toString();
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdScalarDeserializer, com.flurry.org.codehaus.jackson.map.deser.std.StdDeserializer, com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public String deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            return deserialize(jsonParser, deserializationContext);
        }
    }

    protected StdDeserializer(JavaType javaType) {
        super(javaType);
    }

    protected StdDeserializer(Class cls) {
        super(cls);
    }
}
