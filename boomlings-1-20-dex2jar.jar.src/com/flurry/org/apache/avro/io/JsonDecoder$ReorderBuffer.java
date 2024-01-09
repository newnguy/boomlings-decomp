package com.flurry.org.apache.avro.io;

import com.flurry.org.codehaus.jackson.JsonParser;
import java.util.HashMap;
import java.util.Map;

class JsonDecoder$ReorderBuffer {
  public JsonParser origParser = null;
  
  public Map savedFields = new HashMap<Object, Object>();
  
  private JsonDecoder$ReorderBuffer() {}
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\JsonDecoder$ReorderBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */