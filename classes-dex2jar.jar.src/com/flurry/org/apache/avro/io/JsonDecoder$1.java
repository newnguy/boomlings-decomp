package com.flurry.org.apache.avro.io;

import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.JsonLocation;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonStreamContext;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.ObjectCodec;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

class JsonDecoder$1 extends JsonParser {
  int pos = 0;
  
  final JsonDecoder this$0;
  
  final List val$elements;
  
  public void close() {
    throw new UnsupportedOperationException();
  }
  
  public BigInteger getBigIntegerValue() {
    throw new UnsupportedOperationException();
  }
  
  public byte[] getBinaryValue(Base64Variant paramBase64Variant) {
    throw new UnsupportedOperationException();
  }
  
  public ObjectCodec getCodec() {
    throw new UnsupportedOperationException();
  }
  
  public JsonLocation getCurrentLocation() {
    throw new UnsupportedOperationException();
  }
  
  public String getCurrentName() {
    throw new UnsupportedOperationException();
  }
  
  public JsonToken getCurrentToken() {
    return ((JsonDecoder$JsonElement)elements.get(this.pos)).token;
  }
  
  public BigDecimal getDecimalValue() {
    throw new UnsupportedOperationException();
  }
  
  public double getDoubleValue() {
    return Double.parseDouble(getText());
  }
  
  public float getFloatValue() {
    return Float.parseFloat(getText());
  }
  
  public int getIntValue() {
    return Integer.parseInt(getText());
  }
  
  public long getLongValue() {
    return Long.parseLong(getText());
  }
  
  public JsonParser.NumberType getNumberType() {
    throw new UnsupportedOperationException();
  }
  
  public Number getNumberValue() {
    throw new UnsupportedOperationException();
  }
  
  public JsonStreamContext getParsingContext() {
    throw new UnsupportedOperationException();
  }
  
  public String getText() {
    return ((JsonDecoder$JsonElement)elements.get(this.pos)).value;
  }
  
  public char[] getTextCharacters() {
    throw new UnsupportedOperationException();
  }
  
  public int getTextLength() {
    throw new UnsupportedOperationException();
  }
  
  public int getTextOffset() {
    throw new UnsupportedOperationException();
  }
  
  public JsonLocation getTokenLocation() {
    throw new UnsupportedOperationException();
  }
  
  public boolean isClosed() {
    throw new UnsupportedOperationException();
  }
  
  public JsonToken nextToken() {
    this.pos++;
    return ((JsonDecoder$JsonElement)elements.get(this.pos)).token;
  }
  
  public void setCodec(ObjectCodec paramObjectCodec) {
    throw new UnsupportedOperationException();
  }
  
  public JsonParser skipChildren() {
    byte b = 0;
    while (true) {
      int[] arrayOfInt = JsonDecoder$2.$SwitchMap$org$codehaus$jackson$JsonToken;
      List list = elements;
      int i = this.pos;
      this.pos = i + 1;
      switch (arrayOfInt[((JsonDecoder$JsonElement)list.get(i)).token.ordinal()]) {
        case 1:
        case 2:
          if (++b <= 0)
            return this; 
          break;
        case 3:
        case 4:
          if (--b <= 0)
            return this; 
          break;
      } 
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\JsonDecoder$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */