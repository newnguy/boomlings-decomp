package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.JsonLocation;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonStreamContext;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.ObjectCodec;
import com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase;
import com.flurry.org.codehaus.jackson.node.NodeCursor;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes.dex */
public class TreeTraversingParser extends JsonParserMinimalBase {
    protected boolean _closed;
    protected JsonToken _nextToken;
    protected NodeCursor _nodeCursor;
    protected ObjectCodec _objectCodec;
    protected boolean _startContainer;

    public TreeTraversingParser(JsonNode jsonNode) {
        this(jsonNode, null);
    }

    public TreeTraversingParser(JsonNode jsonNode, ObjectCodec objectCodec) {
        super(0);
        this._objectCodec = objectCodec;
        if (jsonNode.isArray()) {
            this._nextToken = JsonToken.START_ARRAY;
            this._nodeCursor = new NodeCursor.Array(jsonNode, null);
        } else if (!jsonNode.isObject()) {
            this._nodeCursor = new NodeCursor.RootValue(jsonNode, null);
        } else {
            this._nextToken = JsonToken.START_OBJECT;
            this._nodeCursor = new NodeCursor.Object(jsonNode, null);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase
    protected void _handleEOF() {
        _throwInternal();
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this._closed) {
            return;
        }
        this._closed = true;
        this._nodeCursor = null;
        this._currToken = null;
    }

    protected JsonNode currentNode() {
        if (this._closed || this._nodeCursor == null) {
            return null;
        }
        return this._nodeCursor.currentNode();
    }

    protected JsonNode currentNumericNode() {
        JsonNode currentNode = currentNode();
        if (currentNode == null || !currentNode.isNumber()) {
            throw _constructError("Current token (" + (currentNode == null ? null : currentNode.asToken()) + ") not numeric, can not use numeric value accessors");
        }
        return currentNode;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public BigInteger getBigIntegerValue() {
        return currentNumericNode().getBigIntegerValue();
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) {
        JsonNode currentNode = currentNode();
        if (currentNode != null) {
            byte[] binaryValue = currentNode.getBinaryValue();
            if (binaryValue != null) {
                return binaryValue;
            }
            if (currentNode.isPojo()) {
                Object pojo = ((POJONode) currentNode).getPojo();
                if (pojo instanceof byte[]) {
                    return (byte[]) pojo;
                }
            }
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public JsonLocation getCurrentLocation() {
        return JsonLocation.NA;
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public String getCurrentName() {
        if (this._nodeCursor == null) {
            return null;
        }
        return this._nodeCursor.getCurrentName();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public BigDecimal getDecimalValue() {
        return currentNumericNode().getDecimalValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public double getDoubleValue() {
        return currentNumericNode().getDoubleValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public Object getEmbeddedObject() {
        JsonNode currentNode;
        if (!this._closed && (currentNode = currentNode()) != null) {
            if (currentNode.isPojo()) {
                return ((POJONode) currentNode).getPojo();
            }
            if (currentNode.isBinary()) {
                return ((BinaryNode) currentNode).getBinaryValue();
            }
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public float getFloatValue() {
        return (float) currentNumericNode().getDoubleValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public int getIntValue() {
        return currentNumericNode().getIntValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public long getLongValue() {
        return currentNumericNode().getLongValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public JsonParser.NumberType getNumberType() {
        JsonNode currentNumericNode = currentNumericNode();
        if (currentNumericNode == null) {
            return null;
        }
        return currentNumericNode.getNumberType();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public Number getNumberValue() {
        return currentNumericNode().getNumberValue();
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public JsonStreamContext getParsingContext() {
        return this._nodeCursor;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public String getText() {
        if (this._closed) {
            return null;
        }
        switch (this._currToken) {
            case FIELD_NAME:
                return this._nodeCursor.getCurrentName();
            case VALUE_STRING:
                return currentNode().getTextValue();
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                return String.valueOf(currentNode().getNumberValue());
            case VALUE_EMBEDDED_OBJECT:
                JsonNode currentNode = currentNode();
                if (currentNode != null && currentNode.isBinary()) {
                    return currentNode.asText();
                }
                break;
        }
        if (this._currToken != null) {
            return this._currToken.asString();
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public char[] getTextCharacters() {
        return getText().toCharArray();
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public int getTextLength() {
        return getText().length();
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public int getTextOffset() {
        return 0;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public JsonLocation getTokenLocation() {
        return JsonLocation.NA;
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public boolean hasTextCharacters() {
        return false;
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public boolean isClosed() {
        return this._closed;
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public JsonToken nextToken() {
        if (this._nextToken != null) {
            this._currToken = this._nextToken;
            this._nextToken = null;
            return this._currToken;
        } else if (this._startContainer) {
            this._startContainer = false;
            if (!this._nodeCursor.currentHasChildren()) {
                this._currToken = this._currToken == JsonToken.START_OBJECT ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
                return this._currToken;
            }
            this._nodeCursor = this._nodeCursor.iterateChildren();
            this._currToken = this._nodeCursor.nextToken();
            if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
                this._startContainer = true;
            }
            return this._currToken;
        } else if (this._nodeCursor == null) {
            this._closed = true;
            return null;
        } else {
            this._currToken = this._nodeCursor.nextToken();
            if (this._currToken == null) {
                this._currToken = this._nodeCursor.endToken();
                this._nodeCursor = this._nodeCursor.getParent();
                return this._currToken;
            }
            if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
                this._startContainer = true;
            }
            return this._currToken;
        }
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public JsonParser skipChildren() {
        if (this._currToken == JsonToken.START_OBJECT) {
            this._startContainer = false;
            this._currToken = JsonToken.END_OBJECT;
        } else if (this._currToken == JsonToken.START_ARRAY) {
            this._startContainer = false;
            this._currToken = JsonToken.END_ARRAY;
        }
        return this;
    }
}
