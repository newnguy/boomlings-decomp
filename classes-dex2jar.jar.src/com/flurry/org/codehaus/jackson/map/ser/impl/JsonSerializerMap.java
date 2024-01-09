package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import java.util.Map;

public class JsonSerializerMap {
  private final JsonSerializerMap$Bucket[] _buckets;
  
  private final int _size;
  
  public JsonSerializerMap(Map paramMap) {
    int i = findSize(paramMap.size());
    this._size = i;
    JsonSerializerMap$Bucket[] arrayOfJsonSerializerMap$Bucket = new JsonSerializerMap$Bucket[i];
    for (Map.Entry entry : paramMap.entrySet()) {
      SerializerCache$TypeKey serializerCache$TypeKey = (SerializerCache$TypeKey)entry.getKey();
      int j = serializerCache$TypeKey.hashCode() & i - 1;
      arrayOfJsonSerializerMap$Bucket[j] = new JsonSerializerMap$Bucket(arrayOfJsonSerializerMap$Bucket[j], serializerCache$TypeKey, (JsonSerializer)entry.getValue());
    } 
    this._buckets = arrayOfJsonSerializerMap$Bucket;
  }
  
  private static final int findSize(int paramInt) {
    if (paramInt <= 64) {
      paramInt += paramInt;
    } else {
      paramInt = (paramInt >> 2) + paramInt;
    } 
    int i;
    for (i = 8; i < paramInt; i += i);
    return i;
  }
  
  public JsonSerializer find(SerializerCache$TypeKey paramSerializerCache$TypeKey) {
    int j = paramSerializerCache$TypeKey.hashCode();
    int i = this._buckets.length;
    JsonSerializerMap$Bucket jsonSerializerMap$Bucket2 = this._buckets[j & i - 1];
    if (jsonSerializerMap$Bucket2 == null)
      return null; 
    JsonSerializerMap$Bucket jsonSerializerMap$Bucket1 = jsonSerializerMap$Bucket2;
    if (paramSerializerCache$TypeKey.equals(jsonSerializerMap$Bucket2.key))
      return jsonSerializerMap$Bucket2.value; 
    while (true) {
      jsonSerializerMap$Bucket2 = jsonSerializerMap$Bucket1.next;
      if (jsonSerializerMap$Bucket2 != null) {
        jsonSerializerMap$Bucket1 = jsonSerializerMap$Bucket2;
        if (paramSerializerCache$TypeKey.equals(jsonSerializerMap$Bucket2.key))
          return jsonSerializerMap$Bucket2.value; 
        continue;
      } 
      return null;
    } 
  }
  
  public int size() {
    return this._size;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\impl\JsonSerializerMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */