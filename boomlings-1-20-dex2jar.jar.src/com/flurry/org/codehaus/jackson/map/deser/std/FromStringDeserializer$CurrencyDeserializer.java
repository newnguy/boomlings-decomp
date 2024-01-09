package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.util.Currency;

public class FromStringDeserializer$CurrencyDeserializer extends FromStringDeserializer {
  public FromStringDeserializer$CurrencyDeserializer() {
    super(Currency.class);
  }
  
  protected Currency _deserialize(String paramString, DeserializationContext paramDeserializationContext) {
    return Currency.getInstance(paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\FromStringDeserializer$CurrencyDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */