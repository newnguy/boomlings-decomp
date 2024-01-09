package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.util.ArrayList;

public abstract class FromStringDeserializer extends StdScalarDeserializer {
  protected FromStringDeserializer(Class paramClass) {
    super(paramClass);
  }
  
  public static Iterable all() {
    ArrayList<FromStringDeserializer$UUIDDeserializer> arrayList = new ArrayList();
    arrayList.add(new FromStringDeserializer$UUIDDeserializer());
    arrayList.add(new FromStringDeserializer$URLDeserializer());
    arrayList.add(new FromStringDeserializer$URIDeserializer());
    arrayList.add(new FromStringDeserializer$CurrencyDeserializer());
    arrayList.add(new FromStringDeserializer$PatternDeserializer());
    arrayList.add(new FromStringDeserializer$LocaleDeserializer());
    arrayList.add(new FromStringDeserializer$InetAddressDeserializer());
    arrayList.add(new FromStringDeserializer$TimeZoneDeserializer());
    arrayList.add(new FromStringDeserializer$CharsetDeserializer());
    return arrayList;
  }
  
  protected abstract Object _deserialize(String paramString, DeserializationContext paramDeserializationContext);
  
  protected Object _deserializeEmbedded(Object paramObject, DeserializationContext paramDeserializationContext) {
    throw paramDeserializationContext.mappingException("Don't know how to convert embedded Object of type " + paramObject.getClass().getName() + " into " + this._valueClass.getName());
  }
  
  public final Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    Object object2 = null;
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
      String str = paramJsonParser.getText().trim();
      if (str.length() == 0)
        return object2; 
      try {
        object2 = _deserialize(str, paramDeserializationContext);
        object1 = object2;
        if (object2 == null)
          throw paramDeserializationContext.weirdStringException(this._valueClass, "not a valid textual representation"); 
        return object1;
      } catch (IllegalArgumentException object1) {
        throw paramDeserializationContext.weirdStringException(this._valueClass, "not a valid textual representation");
      } 
    } 
    if (object1.getCurrentToken() == JsonToken.VALUE_EMBEDDED_OBJECT) {
      Object object = object1.getEmbeddedObject();
      object1 = object2;
      if (object != null) {
        if (this._valueClass.isAssignableFrom(object.getClass()))
          return object; 
        object1 = _deserializeEmbedded(object, paramDeserializationContext);
      } 
      return object1;
    } 
    throw paramDeserializationContext.mappingException(this._valueClass);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\FromStringDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */