package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.map.deser.std.StdDeserializer;
import com.flurry.org.codehaus.jackson.map.util.Provider;
import java.util.Arrays;
import java.util.Collection;
import org.joda.time.DateTime;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;

public class JodaDeserializers implements Provider {
  public Collection provide() {
    return Arrays.asList(new StdDeserializer[] { (StdDeserializer)new JodaDeserializers$DateTimeDeserializer(DateTime.class), (StdDeserializer)new JodaDeserializers$DateTimeDeserializer(ReadableDateTime.class), (StdDeserializer)new JodaDeserializers$DateTimeDeserializer(ReadableInstant.class), (StdDeserializer)new JodaDeserializers$LocalDateDeserializer(), (StdDeserializer)new JodaDeserializers$LocalDateTimeDeserializer(), (StdDeserializer)new JodaDeserializers$DateMidnightDeserializer(), (StdDeserializer)new JodaDeserializers$PeriodDeserializer() });
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\JodaDeserializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */