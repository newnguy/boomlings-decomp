package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;

@JacksonStdImpl
/* loaded from: classes.dex */
public class ClassDeserializer extends StdScalarDeserializer {
    public ClassDeserializer() {
        super(Class.class);
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public Class deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING) {
            String text = jsonParser.getText();
            if (text.indexOf(46) < 0) {
                if ("int".equals(text)) {
                    return Integer.TYPE;
                }
                if ("long".equals(text)) {
                    return Long.TYPE;
                }
                if ("float".equals(text)) {
                    return Float.TYPE;
                }
                if ("double".equals(text)) {
                    return Double.TYPE;
                }
                if ("boolean".equals(text)) {
                    return Boolean.TYPE;
                }
                if ("byte".equals(text)) {
                    return Byte.TYPE;
                }
                if ("char".equals(text)) {
                    return Character.TYPE;
                }
                if ("short".equals(text)) {
                    return Short.TYPE;
                }
                if ("void".equals(text)) {
                    return Void.TYPE;
                }
            }
            try {
                return Class.forName(jsonParser.getText());
            } catch (ClassNotFoundException e) {
                throw deserializationContext.instantiationException(this._valueClass, e);
            }
        }
        throw deserializationContext.mappingException(this._valueClass, currentToken);
    }
}
