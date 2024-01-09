package com.flurry.org.apache.avro.io;

import com.flurry.org.codehaus.jackson.JsonToken;

class JsonDecoder$JsonElement {
  public final JsonToken token;
  
  public final String value;
  
  public JsonDecoder$JsonElement(JsonToken paramJsonToken) {
    this(paramJsonToken, null);
  }
  
  public JsonDecoder$JsonElement(JsonToken paramJsonToken, String paramString) {
    this.token = paramJsonToken;
    this.value = paramString;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\JsonDecoder$JsonElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */