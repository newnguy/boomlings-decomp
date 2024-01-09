package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.JsonLocation;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonStreamContext;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.ObjectCodec;
import com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase;
import java.math.BigDecimal;
import java.math.BigInteger;

public class TreeTraversingParser extends JsonParserMinimalBase {
  protected boolean _closed;
  
  protected JsonToken _nextToken;
  
  protected NodeCursor _nodeCursor;
  
  protected ObjectCodec _objectCodec;
  
  protected boolean _startContainer;
  
  public TreeTraversingParser(JsonNode paramJsonNode) {
    this(paramJsonNode, (ObjectCodec)null);
  }
  
  public TreeTraversingParser(JsonNode paramJsonNode, ObjectCodec paramObjectCodec) {
    super(0);
    this._objectCodec = paramObjectCodec;
    if (paramJsonNode.isArray()) {
      this._nextToken = JsonToken.START_ARRAY;
      this._nodeCursor = new NodeCursor$Array(paramJsonNode, null);
      return;
    } 
    if (paramJsonNode.isObject()) {
      this._nextToken = JsonToken.START_OBJECT;
      this._nodeCursor = new NodeCursor$Object(paramJsonNode, null);
      return;
    } 
    this._nodeCursor = new NodeCursor$RootValue(paramJsonNode, null);
  }
  
  protected void _handleEOF() {
    _throwInternal();
  }
  
  public void close() {
    if (!this._closed) {
      this._closed = true;
      this._nodeCursor = null;
      this._currToken = null;
    } 
  }
  
  protected JsonNode currentNode() {
    return (this._closed || this._nodeCursor == null) ? null : this._nodeCursor.currentNode();
  }
  
  protected JsonNode currentNumericNode() {
    JsonToken jsonToken;
    JsonNode jsonNode = currentNode();
    if (jsonNode == null || !jsonNode.isNumber()) {
      if (jsonNode == null) {
        jsonNode = null;
        throw _constructError("Current token (" + jsonNode + ") not numeric, can not use numeric value accessors");
      } 
      jsonToken = jsonNode.asToken();
      throw _constructError("Current token (" + jsonToken + ") not numeric, can not use numeric value accessors");
    } 
    return (JsonNode)jsonToken;
  }
  
  public BigInteger getBigIntegerValue() {
    return currentNumericNode().getBigIntegerValue();
  }
  
  public byte[] getBinaryValue(Base64Variant paramBase64Variant) {
    JsonNode jsonNode = currentNode();
    if (jsonNode != null) {
      byte[] arrayOfByte = jsonNode.getBinaryValue();
      if (arrayOfByte != null)
        return arrayOfByte; 
      if (jsonNode.isPojo()) {
        Object object = ((POJONode)jsonNode).getPojo();
        if (object instanceof byte[])
          return (byte[])object; 
      } 
    } 
    return null;
  }
  
  public ObjectCodec getCodec() {
    return this._objectCodec;
  }
  
  public JsonLocation getCurrentLocation() {
    return JsonLocation.NA;
  }
  
  public String getCurrentName() {
    return (this._nodeCursor == null) ? null : this._nodeCursor.getCurrentName();
  }
  
  public BigDecimal getDecimalValue() {
    return currentNumericNode().getDecimalValue();
  }
  
  public double getDoubleValue() {
    return currentNumericNode().getDoubleValue();
  }
  
  public Object getEmbeddedObject() {
    if (!this._closed) {
      JsonNode jsonNode = currentNode();
      if (jsonNode != null) {
        if (jsonNode.isPojo())
          return ((POJONode)jsonNode).getPojo(); 
        if (jsonNode.isBinary())
          return ((BinaryNode)jsonNode).getBinaryValue(); 
      } 
    } 
    return null;
  }
  
  public float getFloatValue() {
    return (float)currentNumericNode().getDoubleValue();
  }
  
  public int getIntValue() {
    return currentNumericNode().getIntValue();
  }
  
  public long getLongValue() {
    return currentNumericNode().getLongValue();
  }
  
  public JsonParser.NumberType getNumberType() {
    JsonNode jsonNode = currentNumericNode();
    return (jsonNode == null) ? null : jsonNode.getNumberType();
  }
  
  public Number getNumberValue() {
    return currentNumericNode().getNumberValue();
  }
  
  public JsonStreamContext getParsingContext() {
    return this._nodeCursor;
  }
  
  public String getText() {
    String str = null;
    if (!this._closed) {
      switch (TreeTraversingParser$1.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
        default:
          if (this._currToken != null)
            str = this._currToken.asString(); 
          return str;
        case 1:
          return this._nodeCursor.getCurrentName();
        case 2:
          return currentNode().getTextValue();
        case 3:
        case 4:
          return String.valueOf(currentNode().getNumberValue());
        case 5:
          break;
      } 
      JsonNode jsonNode = currentNode();
      if (jsonNode != null && jsonNode.isBinary())
        return jsonNode.asText(); 
    } 
    return str;
  }
  
  public char[] getTextCharacters() {
    return getText().toCharArray();
  }
  
  public int getTextLength() {
    return getText().length();
  }
  
  public int getTextOffset() {
    return 0;
  }
  
  public JsonLocation getTokenLocation() {
    return JsonLocation.NA;
  }
  
  public boolean hasTextCharacters() {
    return false;
  }
  
  public boolean isClosed() {
    return this._closed;
  }
  
  public JsonToken nextToken() {
    null = null;
    if (this._nextToken != null) {
      this._currToken = this._nextToken;
      this._nextToken = null;
      return this._currToken;
    } 
    if (this._startContainer) {
      this._startContainer = false;
      if (!this._nodeCursor.currentHasChildren()) {
        if (this._currToken == JsonToken.START_OBJECT) {
          null = JsonToken.END_OBJECT;
        } else {
          null = JsonToken.END_ARRAY;
        } 
        this._currToken = null;
        return this._currToken;
      } 
      this._nodeCursor = this._nodeCursor.iterateChildren();
      this._currToken = this._nodeCursor.nextToken();
      if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY)
        this._startContainer = true; 
      return this._currToken;
    } 
    if (this._nodeCursor == null) {
      this._closed = true;
      return null;
    } 
    this._currToken = this._nodeCursor.nextToken();
    if (this._currToken != null) {
      if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY)
        this._startContainer = true; 
      return this._currToken;
    } 
    this._currToken = this._nodeCursor.endToken();
    this._nodeCursor = this._nodeCursor.getParent();
    return this._currToken;
  }
  
  public void setCodec(ObjectCodec paramObjectCodec) {
    this._objectCodec = paramObjectCodec;
  }
  
  public JsonParser skipChildren() {
    if (this._currToken == JsonToken.START_OBJECT) {
      this._startContainer = false;
      this._currToken = JsonToken.END_OBJECT;
      return (JsonParser)this;
    } 
    if (this._currToken == JsonToken.START_ARRAY) {
      this._startContainer = false;
      this._currToken = JsonToken.END_ARRAY;
    } 
    return (JsonParser)this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\TreeTraversingParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */