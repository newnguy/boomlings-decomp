package com.flurry.org.codehaus.jackson.util;

import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.JsonGenerationException;
import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonStreamContext;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.ObjectCodec;
import com.flurry.org.codehaus.jackson.SerializableString;
import com.flurry.org.codehaus.jackson.impl.JsonWriteContext;
import com.flurry.org.codehaus.jackson.io.SerializedString;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class TokenBuffer extends JsonGenerator {
  protected static final int DEFAULT_PARSER_FEATURES = JsonParser.Feature.collectDefaults();
  
  protected int _appendOffset;
  
  protected boolean _closed;
  
  protected TokenBuffer$Segment _first;
  
  protected int _generatorFeatures;
  
  protected TokenBuffer$Segment _last;
  
  protected ObjectCodec _objectCodec;
  
  protected JsonWriteContext _writeContext;
  
  public TokenBuffer(ObjectCodec paramObjectCodec) {
    this._objectCodec = paramObjectCodec;
    this._generatorFeatures = DEFAULT_PARSER_FEATURES;
    this._writeContext = JsonWriteContext.createRootContext();
    TokenBuffer$Segment tokenBuffer$Segment = new TokenBuffer$Segment();
    this._last = tokenBuffer$Segment;
    this._first = tokenBuffer$Segment;
    this._appendOffset = 0;
  }
  
  protected final void _append(JsonToken paramJsonToken) {
    TokenBuffer$Segment tokenBuffer$Segment = this._last.append(this._appendOffset, paramJsonToken);
    if (tokenBuffer$Segment == null) {
      this._appendOffset++;
      return;
    } 
    this._last = tokenBuffer$Segment;
    this._appendOffset = 1;
  }
  
  protected final void _append(JsonToken paramJsonToken, Object paramObject) {
    TokenBuffer$Segment tokenBuffer$Segment = this._last.append(this._appendOffset, paramJsonToken, paramObject);
    if (tokenBuffer$Segment == null) {
      this._appendOffset++;
      return;
    } 
    this._last = tokenBuffer$Segment;
    this._appendOffset = 1;
  }
  
  protected void _reportUnsupportedOperation() {
    throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
  }
  
  public JsonParser asParser() {
    return asParser(this._objectCodec);
  }
  
  public JsonParser asParser(JsonParser paramJsonParser) {
    TokenBuffer$Parser tokenBuffer$Parser = new TokenBuffer$Parser(this._first, paramJsonParser.getCodec());
    tokenBuffer$Parser.setLocation(paramJsonParser.getTokenLocation());
    return (JsonParser)tokenBuffer$Parser;
  }
  
  public JsonParser asParser(ObjectCodec paramObjectCodec) {
    return (JsonParser)new TokenBuffer$Parser(this._first, paramObjectCodec);
  }
  
  public void close() {
    this._closed = true;
  }
  
  public void copyCurrentEvent(JsonParser paramJsonParser) {
    switch (TokenBuffer$1.$SwitchMap$org$codehaus$jackson$JsonToken[paramJsonParser.getCurrentToken().ordinal()]) {
      default:
        throw new RuntimeException("Internal error: should never end up through this code path");
      case 1:
        writeStartObject();
        return;
      case 2:
        writeEndObject();
        return;
      case 3:
        writeStartArray();
        return;
      case 4:
        writeEndArray();
        return;
      case 5:
        writeFieldName(paramJsonParser.getCurrentName());
        return;
      case 6:
        if (paramJsonParser.hasTextCharacters()) {
          writeString(paramJsonParser.getTextCharacters(), paramJsonParser.getTextOffset(), paramJsonParser.getTextLength());
          return;
        } 
        writeString(paramJsonParser.getText());
        return;
      case 7:
        switch (TokenBuffer$1.$SwitchMap$org$codehaus$jackson$JsonParser$NumberType[paramJsonParser.getNumberType().ordinal()]) {
          default:
            writeNumber(paramJsonParser.getLongValue());
            return;
          case 1:
            writeNumber(paramJsonParser.getIntValue());
            return;
          case 2:
            break;
        } 
        writeNumber(paramJsonParser.getBigIntegerValue());
        return;
      case 8:
        switch (TokenBuffer$1.$SwitchMap$org$codehaus$jackson$JsonParser$NumberType[paramJsonParser.getNumberType().ordinal()]) {
          default:
            writeNumber(paramJsonParser.getDoubleValue());
            return;
          case 3:
            writeNumber(paramJsonParser.getDecimalValue());
            return;
          case 4:
            break;
        } 
        writeNumber(paramJsonParser.getFloatValue());
        return;
      case 9:
        writeBoolean(true);
        return;
      case 10:
        writeBoolean(false);
        return;
      case 11:
        writeNull();
        return;
      case 12:
        break;
    } 
    writeObject(paramJsonParser.getEmbeddedObject());
  }
  
  public void copyCurrentStructure(JsonParser paramJsonParser) {
    JsonToken jsonToken2 = paramJsonParser.getCurrentToken();
    JsonToken jsonToken1 = jsonToken2;
    if (jsonToken2 == JsonToken.FIELD_NAME) {
      writeFieldName(paramJsonParser.getCurrentName());
      jsonToken1 = paramJsonParser.nextToken();
    } 
    switch (TokenBuffer$1.$SwitchMap$org$codehaus$jackson$JsonToken[jsonToken1.ordinal()]) {
      default:
        copyCurrentEvent(paramJsonParser);
        return;
      case 3:
        writeStartArray();
        while (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
          copyCurrentStructure(paramJsonParser); 
        writeEndArray();
        return;
      case 1:
        break;
    } 
    writeStartObject();
    while (paramJsonParser.nextToken() != JsonToken.END_OBJECT)
      copyCurrentStructure(paramJsonParser); 
    writeEndObject();
  }
  
  public JsonGenerator disable(JsonGenerator.Feature paramFeature) {
    this._generatorFeatures &= paramFeature.getMask() ^ 0xFFFFFFFF;
    return this;
  }
  
  public JsonGenerator enable(JsonGenerator.Feature paramFeature) {
    this._generatorFeatures |= paramFeature.getMask();
    return this;
  }
  
  public void flush() {}
  
  public ObjectCodec getCodec() {
    return this._objectCodec;
  }
  
  public final JsonWriteContext getOutputContext() {
    return this._writeContext;
  }
  
  public boolean isClosed() {
    return this._closed;
  }
  
  public boolean isEnabled(JsonGenerator.Feature paramFeature) {
    return ((this._generatorFeatures & paramFeature.getMask()) != 0);
  }
  
  public void serialize(JsonGenerator paramJsonGenerator) {
    TokenBuffer$Segment tokenBuffer$Segment = this._first;
    byte b = -1;
    while (true) {
      if (++b >= 16) {
        tokenBuffer$Segment = tokenBuffer$Segment.next();
        if (tokenBuffer$Segment == null)
          return; 
        b = 0;
      } 
      JsonToken jsonToken = tokenBuffer$Segment.type(b);
      if (jsonToken != null) {
        Object object;
        switch (TokenBuffer$1.$SwitchMap$org$codehaus$jackson$JsonToken[jsonToken.ordinal()]) {
          case 1:
            paramJsonGenerator.writeStartObject();
            break;
          case 2:
            paramJsonGenerator.writeEndObject();
            break;
          case 3:
            paramJsonGenerator.writeStartArray();
            break;
          case 4:
            paramJsonGenerator.writeEndArray();
            break;
          case 5:
            object = tokenBuffer$Segment.get(b);
            if (object instanceof SerializableString) {
              paramJsonGenerator.writeFieldName((SerializableString)object);
              break;
            } 
            paramJsonGenerator.writeFieldName((String)object);
            break;
          case 6:
            object = tokenBuffer$Segment.get(b);
            if (object instanceof SerializableString) {
              paramJsonGenerator.writeString((SerializableString)object);
              break;
            } 
            paramJsonGenerator.writeString((String)object);
            break;
          case 7:
            object = tokenBuffer$Segment.get(b);
            if (object instanceof BigInteger) {
              paramJsonGenerator.writeNumber((BigInteger)object);
              break;
            } 
            if (object instanceof Long) {
              paramJsonGenerator.writeNumber(object.longValue());
              break;
            } 
            paramJsonGenerator.writeNumber(object.intValue());
            break;
          case 8:
            object = tokenBuffer$Segment.get(b);
            if (object instanceof BigDecimal) {
              paramJsonGenerator.writeNumber((BigDecimal)object);
              break;
            } 
            if (object instanceof Float) {
              paramJsonGenerator.writeNumber(((Float)object).floatValue());
              break;
            } 
            if (object instanceof Double) {
              paramJsonGenerator.writeNumber(((Double)object).doubleValue());
              break;
            } 
            if (object == null) {
              paramJsonGenerator.writeNull();
              break;
            } 
            if (object instanceof String) {
              paramJsonGenerator.writeNumber((String)object);
              break;
            } 
            throw new JsonGenerationException("Unrecognized value type for VALUE_NUMBER_FLOAT: " + object.getClass().getName() + ", can not serialize");
          case 9:
            paramJsonGenerator.writeBoolean(true);
            break;
          case 10:
            paramJsonGenerator.writeBoolean(false);
            break;
          case 11:
            paramJsonGenerator.writeNull();
            break;
          case 12:
            paramJsonGenerator.writeObject(tokenBuffer$Segment.get(b));
            break;
        } 
        continue;
      } 
      return;
    } 
  }
  
  public JsonGenerator setCodec(ObjectCodec paramObjectCodec) {
    this._objectCodec = paramObjectCodec;
    return this;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[TokenBuffer: ");
    JsonParser jsonParser = asParser();
    for (byte b = 0;; b++) {
      try {
        JsonToken jsonToken = jsonParser.nextToken();
        if (jsonToken == null) {
          if (b >= 100)
            stringBuilder.append(" ... (truncated ").append(b - 100).append(" entries)"); 
          stringBuilder.append(']');
          return stringBuilder.toString();
        } 
      } catch (IOException iOException) {
        throw new IllegalStateException(iOException);
      } 
      if (b < 100) {
        if (b > 0)
          stringBuilder.append(", "); 
        stringBuilder.append(iOException.toString());
      } 
    } 
  }
  
  public JsonGenerator useDefaultPrettyPrinter() {
    return this;
  }
  
  public void writeBinary(Base64Variant paramBase64Variant, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    byte[] arrayOfByte = new byte[paramInt2];
    System.arraycopy(paramArrayOfbyte, paramInt1, arrayOfByte, 0, paramInt2);
    writeObject(arrayOfByte);
  }
  
  public void writeBoolean(boolean paramBoolean) {
    JsonToken jsonToken;
    if (paramBoolean) {
      jsonToken = JsonToken.VALUE_TRUE;
    } else {
      jsonToken = JsonToken.VALUE_FALSE;
    } 
    _append(jsonToken);
  }
  
  public final void writeEndArray() {
    _append(JsonToken.END_ARRAY);
    JsonWriteContext jsonWriteContext = this._writeContext.getParent();
    if (jsonWriteContext != null)
      this._writeContext = jsonWriteContext; 
  }
  
  public final void writeEndObject() {
    _append(JsonToken.END_OBJECT);
    JsonWriteContext jsonWriteContext = this._writeContext.getParent();
    if (jsonWriteContext != null)
      this._writeContext = jsonWriteContext; 
  }
  
  public void writeFieldName(SerializableString paramSerializableString) {
    _append(JsonToken.FIELD_NAME, paramSerializableString);
    this._writeContext.writeFieldName(paramSerializableString.getValue());
  }
  
  public void writeFieldName(SerializedString paramSerializedString) {
    _append(JsonToken.FIELD_NAME, paramSerializedString);
    this._writeContext.writeFieldName(paramSerializedString.getValue());
  }
  
  public final void writeFieldName(String paramString) {
    _append(JsonToken.FIELD_NAME, paramString);
    this._writeContext.writeFieldName(paramString);
  }
  
  public void writeNull() {
    _append(JsonToken.VALUE_NULL);
  }
  
  public void writeNumber(double paramDouble) {
    _append(JsonToken.VALUE_NUMBER_FLOAT, Double.valueOf(paramDouble));
  }
  
  public void writeNumber(float paramFloat) {
    _append(JsonToken.VALUE_NUMBER_FLOAT, Float.valueOf(paramFloat));
  }
  
  public void writeNumber(int paramInt) {
    _append(JsonToken.VALUE_NUMBER_INT, Integer.valueOf(paramInt));
  }
  
  public void writeNumber(long paramLong) {
    _append(JsonToken.VALUE_NUMBER_INT, Long.valueOf(paramLong));
  }
  
  public void writeNumber(String paramString) {
    _append(JsonToken.VALUE_NUMBER_FLOAT, paramString);
  }
  
  public void writeNumber(BigDecimal paramBigDecimal) {
    if (paramBigDecimal == null) {
      writeNull();
      return;
    } 
    _append(JsonToken.VALUE_NUMBER_FLOAT, paramBigDecimal);
  }
  
  public void writeNumber(BigInteger paramBigInteger) {
    if (paramBigInteger == null) {
      writeNull();
      return;
    } 
    _append(JsonToken.VALUE_NUMBER_INT, paramBigInteger);
  }
  
  public void writeObject(Object paramObject) {
    _append(JsonToken.VALUE_EMBEDDED_OBJECT, paramObject);
  }
  
  public void writeRaw(char paramChar) {
    _reportUnsupportedOperation();
  }
  
  public void writeRaw(String paramString) {
    _reportUnsupportedOperation();
  }
  
  public void writeRaw(String paramString, int paramInt1, int paramInt2) {
    _reportUnsupportedOperation();
  }
  
  public void writeRaw(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    _reportUnsupportedOperation();
  }
  
  public void writeRawUTF8String(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    _reportUnsupportedOperation();
  }
  
  public void writeRawValue(String paramString) {
    _reportUnsupportedOperation();
  }
  
  public void writeRawValue(String paramString, int paramInt1, int paramInt2) {
    _reportUnsupportedOperation();
  }
  
  public void writeRawValue(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    _reportUnsupportedOperation();
  }
  
  public final void writeStartArray() {
    _append(JsonToken.START_ARRAY);
    this._writeContext = this._writeContext.createChildArrayContext();
  }
  
  public final void writeStartObject() {
    _append(JsonToken.START_OBJECT);
    this._writeContext = this._writeContext.createChildObjectContext();
  }
  
  public void writeString(SerializableString paramSerializableString) {
    if (paramSerializableString == null) {
      writeNull();
      return;
    } 
    _append(JsonToken.VALUE_STRING, paramSerializableString);
  }
  
  public void writeString(String paramString) {
    if (paramString == null) {
      writeNull();
      return;
    } 
    _append(JsonToken.VALUE_STRING, paramString);
  }
  
  public void writeString(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    writeString(new String(paramArrayOfchar, paramInt1, paramInt2));
  }
  
  public void writeTree(JsonNode paramJsonNode) {
    _append(JsonToken.VALUE_EMBEDDED_OBJECT, paramJsonNode);
  }
  
  public void writeUTF8String(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    _reportUnsupportedOperation();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackso\\util\TokenBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */