package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.JsonFactory;
import com.flurry.org.codehaus.jackson.format.InputAccessor;
import com.flurry.org.codehaus.jackson.format.MatchStrength;

/* loaded from: classes.dex */
public class MappingJsonFactory extends JsonFactory {
    public MappingJsonFactory() {
        this(null);
    }

    public MappingJsonFactory(ObjectMapper objectMapper) {
        super(objectMapper);
        if (objectMapper == null) {
            setCodec(new ObjectMapper(this));
        }
    }

    @Override // com.flurry.org.codehaus.jackson.JsonFactory
    public final ObjectMapper getCodec() {
        return (ObjectMapper) this._objectCodec;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonFactory
    public String getFormatName() {
        return JsonFactory.FORMAT_NAME_JSON;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonFactory
    public MatchStrength hasFormat(InputAccessor inputAccessor) {
        return hasJSONFormat(inputAccessor);
    }
}
