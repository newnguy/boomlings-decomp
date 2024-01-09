package com.flurry.org.codehaus.jackson;

import com.flurry.org.codehaus.jackson.type.TypeReference;
import java.io.Closeable;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;

public abstract class JsonParser implements Versioned, Closeable {
  private static final int MAX_BYTE_I = 127;
  
  private static final int MAX_SHORT_I = 32767;
  
  private static final int MIN_BYTE_I = -128;
  
  private static final int MIN_SHORT_I = -32768;
  
  protected JsonToken _currToken;
  
  protected int _features;
  
  protected JsonToken _lastClearedToken;
  
  protected JsonParser() {}
  
  protected JsonParser(int paramInt) {
    this._features = paramInt;
  }
  
  protected JsonParseException _constructError(String paramString) {
    return new JsonParseException(paramString, getCurrentLocation());
  }
  
  public boolean canUseSchema(FormatSchema paramFormatSchema) {
    return false;
  }
  
  public void clearCurrentToken() {
    if (this._currToken != null) {
      this._lastClearedToken = this._currToken;
      this._currToken = null;
    } 
  }
  
  public abstract void close();
  
  public JsonParser configure(JsonParser$Feature paramJsonParser$Feature, boolean paramBoolean) {
    if (paramBoolean) {
      enableFeature(paramJsonParser$Feature);
      return this;
    } 
    disableFeature(paramJsonParser$Feature);
    return this;
  }
  
  public JsonParser disable(JsonParser$Feature paramJsonParser$Feature) {
    this._features &= paramJsonParser$Feature.getMask() ^ 0xFFFFFFFF;
    return this;
  }
  
  public void disableFeature(JsonParser$Feature paramJsonParser$Feature) {
    disable(paramJsonParser$Feature);
  }
  
  public JsonParser enable(JsonParser$Feature paramJsonParser$Feature) {
    this._features |= paramJsonParser$Feature.getMask();
    return this;
  }
  
  public void enableFeature(JsonParser$Feature paramJsonParser$Feature) {
    enable(paramJsonParser$Feature);
  }
  
  public abstract BigInteger getBigIntegerValue();
  
  public byte[] getBinaryValue() {
    return getBinaryValue(Base64Variants.getDefaultVariant());
  }
  
  public abstract byte[] getBinaryValue(Base64Variant paramBase64Variant);
  
  public boolean getBooleanValue() {
    if (getCurrentToken() == JsonToken.VALUE_TRUE)
      return true; 
    if (getCurrentToken() == JsonToken.VALUE_FALSE)
      return false; 
    throw new JsonParseException("Current token (" + this._currToken + ") not of boolean type", getCurrentLocation());
  }
  
  public byte getByteValue() {
    int i = getIntValue();
    if (i < -128 || i > 127)
      throw _constructError("Numeric value (" + getText() + ") out of range of Java byte"); 
    return (byte)i;
  }
  
  public abstract ObjectCodec getCodec();
  
  public abstract JsonLocation getCurrentLocation();
  
  public abstract String getCurrentName();
  
  public JsonToken getCurrentToken() {
    return this._currToken;
  }
  
  public abstract BigDecimal getDecimalValue();
  
  public abstract double getDoubleValue();
  
  public Object getEmbeddedObject() {
    return null;
  }
  
  public abstract float getFloatValue();
  
  public Object getInputSource() {
    return null;
  }
  
  public abstract int getIntValue();
  
  public JsonToken getLastClearedToken() {
    return this._lastClearedToken;
  }
  
  public abstract long getLongValue();
  
  public abstract JsonParser$NumberType getNumberType();
  
  public abstract Number getNumberValue();
  
  public abstract JsonStreamContext getParsingContext();
  
  public short getShortValue() {
    int i = getIntValue();
    if (i < -32768 || i > 32767)
      throw _constructError("Numeric value (" + getText() + ") out of range of Java short"); 
    return (short)i;
  }
  
  public abstract String getText();
  
  public abstract char[] getTextCharacters();
  
  public abstract int getTextLength();
  
  public abstract int getTextOffset();
  
  public abstract JsonLocation getTokenLocation();
  
  public boolean getValueAsBoolean() {
    return getValueAsBoolean(false);
  }
  
  public boolean getValueAsBoolean(boolean paramBoolean) {
    return paramBoolean;
  }
  
  public double getValueAsDouble() {
    return getValueAsDouble(0.0D);
  }
  
  public double getValueAsDouble(double paramDouble) {
    return paramDouble;
  }
  
  public int getValueAsInt() {
    return getValueAsInt(0);
  }
  
  public int getValueAsInt(int paramInt) {
    return paramInt;
  }
  
  public long getValueAsLong() {
    return getValueAsInt(0);
  }
  
  public long getValueAsLong(long paramLong) {
    return paramLong;
  }
  
  public boolean hasCurrentToken() {
    return (this._currToken != null);
  }
  
  public boolean hasTextCharacters() {
    return false;
  }
  
  public abstract boolean isClosed();
  
  public boolean isEnabled(JsonParser$Feature paramJsonParser$Feature) {
    return ((this._features & paramJsonParser$Feature.getMask()) != 0);
  }
  
  public boolean isExpectedStartArrayToken() {
    return (getCurrentToken() == JsonToken.START_ARRAY);
  }
  
  public final boolean isFeatureEnabled(JsonParser$Feature paramJsonParser$Feature) {
    return isEnabled(paramJsonParser$Feature);
  }
  
  public Boolean nextBooleanValue() {
    switch (JsonParser$1.$SwitchMap$org$codehaus$jackson$JsonToken[nextToken().ordinal()]) {
      default:
        return null;
      case 1:
        return Boolean.TRUE;
      case 2:
        break;
    } 
    return Boolean.FALSE;
  }
  
  public boolean nextFieldName(SerializableString paramSerializableString) {
    return (nextToken() == JsonToken.FIELD_NAME && paramSerializableString.getValue().equals(getCurrentName()));
  }
  
  public int nextIntValue(int paramInt) {
    if (nextToken() == JsonToken.VALUE_NUMBER_INT)
      paramInt = getIntValue(); 
    return paramInt;
  }
  
  public long nextLongValue(long paramLong) {
    if (nextToken() == JsonToken.VALUE_NUMBER_INT)
      paramLong = getLongValue(); 
    return paramLong;
  }
  
  public String nextTextValue() {
    return (nextToken() == JsonToken.VALUE_STRING) ? getText() : null;
  }
  
  public abstract JsonToken nextToken();
  
  public JsonToken nextValue() {
    JsonToken jsonToken2 = nextToken();
    JsonToken jsonToken1 = jsonToken2;
    if (jsonToken2 == JsonToken.FIELD_NAME)
      jsonToken1 = nextToken(); 
    return jsonToken1;
  }
  
  public Object readValueAs(TypeReference paramTypeReference) {
    ObjectCodec objectCodec = getCodec();
    if (objectCodec == null)
      throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects"); 
    return objectCodec.readValue(this, paramTypeReference);
  }
  
  public Object readValueAs(Class paramClass) {
    ObjectCodec objectCodec = getCodec();
    if (objectCodec == null)
      throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects"); 
    return objectCodec.readValue(this, paramClass);
  }
  
  public JsonNode readValueAsTree() {
    ObjectCodec objectCodec = getCodec();
    if (objectCodec == null)
      throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into JsonNode tree"); 
    return objectCodec.readTree(this);
  }
  
  public Iterator readValuesAs(TypeReference paramTypeReference) {
    ObjectCodec objectCodec = getCodec();
    if (objectCodec == null)
      throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects"); 
    return objectCodec.readValues(this, paramTypeReference);
  }
  
  public Iterator readValuesAs(Class paramClass) {
    ObjectCodec objectCodec = getCodec();
    if (objectCodec == null)
      throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects"); 
    return objectCodec.readValues(this, paramClass);
  }
  
  public int releaseBuffered(OutputStream paramOutputStream) {
    return -1;
  }
  
  public int releaseBuffered(Writer paramWriter) {
    return -1;
  }
  
  public abstract void setCodec(ObjectCodec paramObjectCodec);
  
  public void setFeature(JsonParser$Feature paramJsonParser$Feature, boolean paramBoolean) {
    configure(paramJsonParser$Feature, paramBoolean);
  }
  
  public void setSchema(FormatSchema paramFormatSchema) {
    throw new UnsupportedOperationException("Parser of type " + getClass().getName() + " does not support schema of type '" + paramFormatSchema.getSchemaType() + "'");
  }
  
  public abstract JsonParser skipChildren();
  
  public Version version() {
    return Version.unknownVersion();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\JsonParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */