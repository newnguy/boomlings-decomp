package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.type.JavaType;

/* loaded from: classes.dex */
public class JavaTypeDeserializer extends StdScalarDeserializer {
    public JavaTypeDeserializer() {
        super(JavaType.class);
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public JavaType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            return trim.length() == 0 ? (JavaType) getEmptyValue() : deserializationContext.getTypeFactory().constructFromCanonical(trim);
        } else if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
            return (JavaType) jsonParser.getEmbeddedObject();
        } else {
            throw deserializationContext.mappingException(this._valueClass);
        }
    }
}
