package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.map.ser.BeanPropertyFilter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class SimpleBeanPropertyFilter implements BeanPropertyFilter {
  public static SimpleBeanPropertyFilter filterOutAllExcept(Set paramSet) {
    return new SimpleBeanPropertyFilter$FilterExceptFilter(paramSet);
  }
  
  public static SimpleBeanPropertyFilter filterOutAllExcept(String... paramVarArgs) {
    HashSet<? super String> hashSet = new HashSet(paramVarArgs.length);
    Collections.addAll(hashSet, paramVarArgs);
    return new SimpleBeanPropertyFilter$FilterExceptFilter(hashSet);
  }
  
  public static SimpleBeanPropertyFilter serializeAllExcept(Set paramSet) {
    return new SimpleBeanPropertyFilter$SerializeExceptFilter(paramSet);
  }
  
  public static SimpleBeanPropertyFilter serializeAllExcept(String... paramVarArgs) {
    HashSet<? super String> hashSet = new HashSet(paramVarArgs.length);
    Collections.addAll(hashSet, paramVarArgs);
    return new SimpleBeanPropertyFilter$SerializeExceptFilter(hashSet);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\impl\SimpleBeanPropertyFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */