package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.util.ObjectBuffer;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@JacksonStdImpl
/* loaded from: classes.dex */
public class UntypedObjectDeserializer extends StdDeserializer {
    private static final Object[] NO_OBJECTS = new Object[0];

    public UntypedObjectDeserializer() {
        super(Object.class);
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        switch (jsonParser.getCurrentToken()) {
            case START_OBJECT:
                return mapObject(jsonParser, deserializationContext);
            case END_OBJECT:
            case END_ARRAY:
            default:
                throw deserializationContext.mappingException(Object.class);
            case START_ARRAY:
                return mapArray(jsonParser, deserializationContext);
            case FIELD_NAME:
                return mapObject(jsonParser, deserializationContext);
            case VALUE_EMBEDDED_OBJECT:
                return jsonParser.getEmbeddedObject();
            case VALUE_STRING:
                return jsonParser.getText();
            case VALUE_NUMBER_INT:
                return deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS) ? jsonParser.getBigIntegerValue() : jsonParser.getNumberValue();
            case VALUE_NUMBER_FLOAT:
                return deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS) ? jsonParser.getDecimalValue() : Double.valueOf(jsonParser.getDoubleValue());
            case VALUE_TRUE:
                return Boolean.TRUE;
            case VALUE_FALSE:
                return Boolean.FALSE;
            case VALUE_NULL:
                return null;
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdDeserializer, com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        switch (jsonParser.getCurrentToken()) {
            case START_OBJECT:
            case START_ARRAY:
            case FIELD_NAME:
                return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
            case END_OBJECT:
            case END_ARRAY:
            default:
                throw deserializationContext.mappingException(Object.class);
            case VALUE_EMBEDDED_OBJECT:
                return jsonParser.getEmbeddedObject();
            case VALUE_STRING:
                return jsonParser.getText();
            case VALUE_NUMBER_INT:
                return deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS) ? jsonParser.getBigIntegerValue() : Integer.valueOf(jsonParser.getIntValue());
            case VALUE_NUMBER_FLOAT:
                return deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS) ? jsonParser.getDecimalValue() : Double.valueOf(jsonParser.getDoubleValue());
            case VALUE_TRUE:
                return Boolean.TRUE;
            case VALUE_FALSE:
                return Boolean.FALSE;
            case VALUE_NULL:
                return null;
        }
    }

    protected Object mapArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
        int i;
        if (deserializationContext.isEnabled(DeserializationConfig.Feature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
            return mapArrayToArray(jsonParser, deserializationContext);
        }
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
            return new ArrayList(4);
        }
        ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        int i2 = 0;
        Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
        int i3 = 0;
        do {
            Object deserialize = deserialize(jsonParser, deserializationContext);
            i3++;
            if (i2 >= resetAndStart.length) {
                resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                i = 0;
            } else {
                i = i2;
            }
            i2 = i + 1;
            resetAndStart[i] = deserialize;
        } while (jsonParser.nextToken() != JsonToken.END_ARRAY);
        ArrayList arrayList = new ArrayList(i3 + (i3 >> 3) + 1);
        leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i2, arrayList);
        return arrayList;
    }

    protected Object[] mapArrayToArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
        int i;
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
            return NO_OBJECTS;
        }
        ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
        int i2 = 0;
        do {
            Object deserialize = deserialize(jsonParser, deserializationContext);
            if (i2 >= resetAndStart.length) {
                resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                i = 0;
            } else {
                i = i2;
            }
            i2 = i + 1;
            resetAndStart[i] = deserialize;
        } while (jsonParser.nextToken() != JsonToken.END_ARRAY);
        return leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i2);
    }

    protected Object mapObject(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        if (currentToken != JsonToken.FIELD_NAME) {
            return new LinkedHashMap(4);
        }
        String text = jsonParser.getText();
        jsonParser.nextToken();
        Object deserialize = deserialize(jsonParser, deserializationContext);
        if (jsonParser.nextToken() != JsonToken.FIELD_NAME) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(4);
            linkedHashMap.put(text, deserialize);
            return linkedHashMap;
        }
        String text2 = jsonParser.getText();
        jsonParser.nextToken();
        Object deserialize2 = deserialize(jsonParser, deserializationContext);
        if (jsonParser.nextToken() != JsonToken.FIELD_NAME) {
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(4);
            linkedHashMap2.put(text, deserialize);
            linkedHashMap2.put(text2, deserialize2);
            return linkedHashMap2;
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put(text, deserialize);
        linkedHashMap3.put(text2, deserialize2);
        do {
            String text3 = jsonParser.getText();
            jsonParser.nextToken();
            linkedHashMap3.put(text3, deserialize(jsonParser, deserializationContext));
        } while (jsonParser.nextToken() != JsonToken.END_OBJECT);
        return linkedHashMap3;
    }
}
