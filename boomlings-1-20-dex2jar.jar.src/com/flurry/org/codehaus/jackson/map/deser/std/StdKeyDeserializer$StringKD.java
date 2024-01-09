package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;

final class StdKeyDeserializer$StringKD extends StdKeyDeserializer {
  private static final StdKeyDeserializer$StringKD sObject;
  
  private static final StdKeyDeserializer$StringKD sString = new StdKeyDeserializer$StringKD(String.class);
  
  static {
    sObject = new StdKeyDeserializer$StringKD(Object.class);
  }
  
  private StdKeyDeserializer$StringKD(Class paramClass) {
    super(paramClass);
  }
  
  public static StdKeyDeserializer$StringKD forType(Class<String> paramClass) {
    return (paramClass == String.class) ? sString : ((paramClass == Object.class) ? sObject : new StdKeyDeserializer$StringKD(paramClass));
  }
  
  public String _parse(String paramString, DeserializationContext paramDeserializationContext) {
    return paramString;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdKeyDeserializer$StringKD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */