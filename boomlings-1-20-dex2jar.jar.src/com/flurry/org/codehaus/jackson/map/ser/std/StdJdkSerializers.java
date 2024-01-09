package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.map.util.Provider;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class StdJdkSerializers implements Provider {
  public Collection provide() {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    ToStringSerializer toStringSerializer = ToStringSerializer.instance;
    hashMap.put(URL.class, toStringSerializer);
    hashMap.put(URI.class, toStringSerializer);
    hashMap.put(Currency.class, toStringSerializer);
    hashMap.put(UUID.class, toStringSerializer);
    hashMap.put(Pattern.class, toStringSerializer);
    hashMap.put(Locale.class, toStringSerializer);
    hashMap.put(Locale.class, toStringSerializer);
    hashMap.put(AtomicReference.class, StdJdkSerializers$AtomicReferenceSerializer.class);
    hashMap.put(AtomicBoolean.class, StdJdkSerializers$AtomicBooleanSerializer.class);
    hashMap.put(AtomicInteger.class, StdJdkSerializers$AtomicIntegerSerializer.class);
    hashMap.put(AtomicLong.class, StdJdkSerializers$AtomicLongSerializer.class);
    hashMap.put(File.class, StdJdkSerializers$FileSerializer.class);
    hashMap.put(Class.class, StdJdkSerializers$ClassSerializer.class);
    hashMap.put(void.class, NullSerializer.class);
    return hashMap.entrySet();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StdJdkSerializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */