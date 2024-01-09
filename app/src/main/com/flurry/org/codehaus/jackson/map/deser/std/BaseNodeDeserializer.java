package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.node.ArrayNode;
import com.flurry.org.codehaus.jackson.node.JsonNodeFactory;
import com.flurry.org.codehaus.jackson.node.ObjectNode;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class BaseNodeDeserializer extends StdDeserializer {
    public BaseNodeDeserializer(Class cls) {
        super(cls);
    }

    protected void _handleDuplicateField(String str, ObjectNode objectNode, JsonNode jsonNode, JsonNode jsonNode2) {
    }

    protected void _reportProblem(JsonParser jsonParser, String str) {
        throw new JsonMappingException(str, jsonParser.getTokenLocation());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonNode deserializeAny(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) {
        switch (jsonParser.getCurrentToken()) {
            case START_OBJECT:
                return deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
            case START_ARRAY:
                return deserializeArray(jsonParser, deserializationContext, jsonNodeFactory);
            case VALUE_STRING:
                return jsonNodeFactory.textNode(jsonParser.getText());
            case END_ARRAY:
            default:
                throw deserializationContext.mappingException(getValueClass());
            case FIELD_NAME:
                return deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
            case VALUE_EMBEDDED_OBJECT:
                Object embeddedObject = jsonParser.getEmbeddedObject();
                return embeddedObject == null ? jsonNodeFactory.nullNode() : embeddedObject.getClass() == byte[].class ? jsonNodeFactory.binaryNode((byte[]) embeddedObject) : jsonNodeFactory.POJONode(embeddedObject);
            case VALUE_NUMBER_INT:
                JsonParser.NumberType numberType = jsonParser.getNumberType();
                return (numberType == JsonParser.NumberType.BIG_INTEGER || deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS)) ? jsonNodeFactory.numberNode(jsonParser.getBigIntegerValue()) : numberType == JsonParser.NumberType.INT ? jsonNodeFactory.numberNode(jsonParser.getIntValue()) : jsonNodeFactory.numberNode(jsonParser.getLongValue());
            case VALUE_NUMBER_FLOAT:
                return (jsonParser.getNumberType() == JsonParser.NumberType.BIG_DECIMAL || deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS)) ? jsonNodeFactory.numberNode(jsonParser.getDecimalValue()) : jsonNodeFactory.numberNode(jsonParser.getDoubleValue());
            case VALUE_TRUE:
                return jsonNodeFactory.booleanNode(true);
            case VALUE_FALSE:
                return jsonNodeFactory.booleanNode(false);
            case VALUE_NULL:
                return jsonNodeFactory.nullNode();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ArrayNode deserializeArray(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) {
        ArrayNode arrayNode = jsonNodeFactory.arrayNode();
        while (true) {
            switch (jsonParser.nextToken()) {
                case START_OBJECT:
                    arrayNode.add(deserializeObject(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
                case START_ARRAY:
                    arrayNode.add(deserializeArray(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
                case VALUE_STRING:
                    arrayNode.add(jsonNodeFactory.textNode(jsonParser.getText()));
                    break;
                case END_ARRAY:
                    return arrayNode;
                default:
                    arrayNode.add(deserializeAny(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ObjectNode deserializeObject(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) {
        JsonNode textNode;
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            switch (jsonParser.nextToken()) {
                case START_OBJECT:
                    textNode = deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
                    break;
                case START_ARRAY:
                    textNode = deserializeArray(jsonParser, deserializationContext, jsonNodeFactory);
                    break;
                case VALUE_STRING:
                    textNode = jsonNodeFactory.textNode(jsonParser.getText());
                    break;
                default:
                    textNode = deserializeAny(jsonParser, deserializationContext, jsonNodeFactory);
                    break;
            }
            JsonNode put = objectNode.put(currentName, textNode);
            if (put != null) {
                _handleDuplicateField(currentName, objectNode, put, textNode);
            }
            currentToken = jsonParser.nextToken();
        }
        return objectNode;
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdDeserializer, com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }
}
