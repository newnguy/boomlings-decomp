package com.flurry.org.apache.avro;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

final class Schema$Props extends LinkedHashMap {
  private Set reserved;
  
  public Schema$Props(Set paramSet) {
    super(1);
    this.reserved = paramSet;
  }
  
  public void add(String paramString1, String paramString2) {
    if (this.reserved.contains(paramString1))
      throw new AvroRuntimeException("Can't set reserved property: " + paramString1); 
    if (paramString2 == null)
      throw new AvroRuntimeException("Can't set a property to null: " + paramString1); 
    String str = (String)get(paramString1);
    if (str == null) {
      put((K)paramString1, (V)paramString2);
      return;
    } 
    if (!str.equals(paramString2))
      throw new AvroRuntimeException("Can't overwrite property: " + paramString1); 
  }
  
  public void write(JsonGenerator paramJsonGenerator) {
    for (Map.Entry<K, V> entry : entrySet())
      paramJsonGenerator.writeStringField((String)entry.getKey(), (String)entry.getValue()); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\Schema$Props.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */