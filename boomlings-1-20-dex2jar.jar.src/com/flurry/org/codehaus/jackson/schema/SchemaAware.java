package com.flurry.org.codehaus.jackson.schema;

import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.lang.reflect.Type;

public interface SchemaAware {
  JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\schema\SchemaAware.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */