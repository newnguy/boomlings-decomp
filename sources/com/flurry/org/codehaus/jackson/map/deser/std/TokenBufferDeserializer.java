package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.util.TokenBuffer;

@JacksonStdImpl
/* loaded from: classes.dex */
public class TokenBufferDeserializer extends StdScalarDeserializer {
    public TokenBufferDeserializer() {
        super(TokenBuffer.class);
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public TokenBuffer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        TokenBuffer tokenBuffer = new TokenBuffer(jsonParser.getCodec());
        tokenBuffer.copyCurrentStructure(jsonParser);
        return tokenBuffer;
    }
}
