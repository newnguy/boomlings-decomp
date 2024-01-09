package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.map.deser.std.AtomicBooleanDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.CalendarDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.ClassDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.DateDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.JavaTypeDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.StdDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.StringDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.TimestampDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.TokenBufferDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.UntypedObjectDeserializer;
import com.flurry.org.codehaus.jackson.map.type.ClassKey;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

class StdDeserializers {
  final HashMap _deserializers = new HashMap<Object, Object>();
  
  private StdDeserializers() {
    add((StdDeserializer)new UntypedObjectDeserializer());
    StringDeserializer stringDeserializer = new StringDeserializer();
    add((StdDeserializer)stringDeserializer, String.class);
    add((StdDeserializer)stringDeserializer, CharSequence.class);
    add((StdDeserializer)new ClassDeserializer());
    add((StdDeserializer)new StdDeserializer.BooleanDeserializer(Boolean.class, null));
    add((StdDeserializer)new StdDeserializer.ByteDeserializer(Byte.class, null));
    add((StdDeserializer)new StdDeserializer.ShortDeserializer(Short.class, null));
    add((StdDeserializer)new StdDeserializer.CharacterDeserializer(Character.class, null));
    add((StdDeserializer)new StdDeserializer.IntegerDeserializer(Integer.class, null));
    add((StdDeserializer)new StdDeserializer.LongDeserializer(Long.class, null));
    add((StdDeserializer)new StdDeserializer.FloatDeserializer(Float.class, null));
    add((StdDeserializer)new StdDeserializer.DoubleDeserializer(Double.class, null));
    add((StdDeserializer)new StdDeserializer.BooleanDeserializer(boolean.class, Boolean.FALSE));
    add((StdDeserializer)new StdDeserializer.ByteDeserializer(byte.class, Byte.valueOf((byte)0)));
    add((StdDeserializer)new StdDeserializer.ShortDeserializer(short.class, Short.valueOf((short)0)));
    add((StdDeserializer)new StdDeserializer.CharacterDeserializer(char.class, Character.valueOf(false)));
    add((StdDeserializer)new StdDeserializer.IntegerDeserializer(int.class, Integer.valueOf(0)));
    add((StdDeserializer)new StdDeserializer.LongDeserializer(long.class, Long.valueOf(0L)));
    add((StdDeserializer)new StdDeserializer.FloatDeserializer(float.class, Float.valueOf(0.0F)));
    add((StdDeserializer)new StdDeserializer.DoubleDeserializer(double.class, Double.valueOf(0.0D)));
    add((StdDeserializer)new StdDeserializer.NumberDeserializer());
    add((StdDeserializer)new StdDeserializer.BigDecimalDeserializer());
    add((StdDeserializer)new StdDeserializer.BigIntegerDeserializer());
    add((StdDeserializer)new CalendarDeserializer());
    add((StdDeserializer)new DateDeserializer());
    add((StdDeserializer)new CalendarDeserializer(GregorianCalendar.class), GregorianCalendar.class);
    add((StdDeserializer)new StdDeserializer.SqlDateDeserializer());
    add((StdDeserializer)new TimestampDeserializer());
    Iterator<FromStringDeserializer> iterator = FromStringDeserializer.all().iterator();
    while (iterator.hasNext())
      add((StdDeserializer)iterator.next()); 
    add((StdDeserializer)new StdDeserializer.StackTraceElementDeserializer());
    add((StdDeserializer)new AtomicBooleanDeserializer());
    add((StdDeserializer)new TokenBufferDeserializer());
    add((StdDeserializer)new JavaTypeDeserializer());
  }
  
  private void add(StdDeserializer paramStdDeserializer) {
    add(paramStdDeserializer, paramStdDeserializer.getValueClass());
  }
  
  private void add(StdDeserializer paramStdDeserializer, Class paramClass) {
    this._deserializers.put(new ClassKey(paramClass), paramStdDeserializer);
  }
  
  public static HashMap constructAll() {
    return (new StdDeserializers())._deserializers;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\StdDeserializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */