package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.UUID;

public class FromStringDeserializer$UUIDDeserializer extends FromStringDeserializer {
  public FromStringDeserializer$UUIDDeserializer() {
    super(UUID.class);
  }
  
  protected UUID _deserialize(String paramString, DeserializationContext paramDeserializationContext) {
    return UUID.fromString(paramString);
  }
  
  protected UUID _deserializeEmbedded(Object paramObject, DeserializationContext paramDeserializationContext) {
    if (paramObject instanceof byte[]) {
      paramObject = paramObject;
      if (paramObject.length != 16)
        paramDeserializationContext.mappingException("Can only construct UUIDs from 16 byte arrays; got " + paramObject.length + " bytes"); 
      paramObject = new DataInputStream(new ByteArrayInputStream((byte[])paramObject));
      return new UUID(paramObject.readLong(), paramObject.readLong());
    } 
    super._deserializeEmbedded(paramObject, paramDeserializationContext);
    return null;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\FromStringDeserializer$UUIDDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */