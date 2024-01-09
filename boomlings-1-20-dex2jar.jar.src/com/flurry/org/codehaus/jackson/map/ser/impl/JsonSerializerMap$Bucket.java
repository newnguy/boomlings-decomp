package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.map.JsonSerializer;

final class JsonSerializerMap$Bucket {
  public final SerializerCache$TypeKey key;
  
  public final JsonSerializerMap$Bucket next;
  
  public final JsonSerializer value;
  
  public JsonSerializerMap$Bucket(JsonSerializerMap$Bucket paramJsonSerializerMap$Bucket, SerializerCache$TypeKey paramSerializerCache$TypeKey, JsonSerializer paramJsonSerializer) {
    this.next = paramJsonSerializerMap$Bucket;
    this.key = paramSerializerCache$TypeKey;
    this.value = paramJsonSerializer;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\impl\JsonSerializerMap$Bucket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */