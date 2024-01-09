package com.flurry.org.codehaus.jackson.map.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public final class ArrayBuilders {
  ArrayBuilders$BooleanBuilder _booleanBuilder = null;
  
  ArrayBuilders$ByteBuilder _byteBuilder = null;
  
  ArrayBuilders$DoubleBuilder _doubleBuilder = null;
  
  ArrayBuilders$FloatBuilder _floatBuilder = null;
  
  ArrayBuilders$IntBuilder _intBuilder = null;
  
  ArrayBuilders$LongBuilder _longBuilder = null;
  
  ArrayBuilders$ShortBuilder _shortBuilder = null;
  
  public static List addToList(List<Object> paramList, Object paramObject) {
    List<Object> list = paramList;
    if (paramList == null)
      list = new ArrayList(); 
    list.add(paramObject);
    return list;
  }
  
  public static Iterable arrayAsIterable(Object[] paramArrayOfObject) {
    return new ArrayBuilders$ArrayIterator(paramArrayOfObject);
  }
  
  public static Iterator arrayAsIterator(Object[] paramArrayOfObject) {
    return new ArrayBuilders$ArrayIterator(paramArrayOfObject);
  }
  
  public static HashSet arrayToSet(Object[] paramArrayOfObject) {
    HashSet<Object> hashSet = new HashSet();
    if (paramArrayOfObject != null) {
      int i = paramArrayOfObject.length;
      for (byte b = 0; b < i; b++)
        hashSet.add(paramArrayOfObject[b]); 
    } 
    return hashSet;
  }
  
  public static Object[] insertInList(Object[] paramArrayOfObject, Object paramObject) {
    int i = paramArrayOfObject.length;
    Object[] arrayOfObject = (Object[])Array.newInstance(paramArrayOfObject.getClass().getComponentType(), i + 1);
    if (i > 0)
      System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 1, i); 
    arrayOfObject[0] = paramObject;
    return arrayOfObject;
  }
  
  public static Object[] insertInListNoDup(Object[] paramArrayOfObject, Object paramObject) {
    int i = paramArrayOfObject.length;
    for (byte b = 0; b < i; b++) {
      if (paramArrayOfObject[b] == paramObject) {
        if (b != 0) {
          Object[] arrayOfObject1 = (Object[])Array.newInstance(paramArrayOfObject.getClass().getComponentType(), i);
          System.arraycopy(paramArrayOfObject, 0, arrayOfObject1, 1, b);
          paramArrayOfObject[0] = paramObject;
          paramArrayOfObject = arrayOfObject1;
        } 
        return paramArrayOfObject;
      } 
    } 
    Object[] arrayOfObject = (Object[])Array.newInstance(paramArrayOfObject.getClass().getComponentType(), i + 1);
    if (i > 0)
      System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 1, i); 
    arrayOfObject[0] = paramObject;
    return arrayOfObject;
  }
  
  public ArrayBuilders$BooleanBuilder getBooleanBuilder() {
    if (this._booleanBuilder == null)
      this._booleanBuilder = new ArrayBuilders$BooleanBuilder(); 
    return this._booleanBuilder;
  }
  
  public ArrayBuilders$ByteBuilder getByteBuilder() {
    if (this._byteBuilder == null)
      this._byteBuilder = new ArrayBuilders$ByteBuilder(); 
    return this._byteBuilder;
  }
  
  public ArrayBuilders$DoubleBuilder getDoubleBuilder() {
    if (this._doubleBuilder == null)
      this._doubleBuilder = new ArrayBuilders$DoubleBuilder(); 
    return this._doubleBuilder;
  }
  
  public ArrayBuilders$FloatBuilder getFloatBuilder() {
    if (this._floatBuilder == null)
      this._floatBuilder = new ArrayBuilders$FloatBuilder(); 
    return this._floatBuilder;
  }
  
  public ArrayBuilders$IntBuilder getIntBuilder() {
    if (this._intBuilder == null)
      this._intBuilder = new ArrayBuilders$IntBuilder(); 
    return this._intBuilder;
  }
  
  public ArrayBuilders$LongBuilder getLongBuilder() {
    if (this._longBuilder == null)
      this._longBuilder = new ArrayBuilders$LongBuilder(); 
    return this._longBuilder;
  }
  
  public ArrayBuilders$ShortBuilder getShortBuilder() {
    if (this._shortBuilder == null)
      this._shortBuilder = new ArrayBuilders$ShortBuilder(); 
    return this._shortBuilder;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\ArrayBuilders.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */