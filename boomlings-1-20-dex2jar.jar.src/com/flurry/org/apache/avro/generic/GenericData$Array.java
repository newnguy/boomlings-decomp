package com.flurry.org.apache.avro.generic;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;

public class GenericData$Array extends AbstractList implements GenericArray, Comparable {
  private static final Object[] EMPTY = new Object[0];
  
  private Object[] elements = EMPTY;
  
  private final Schema schema;
  
  private int size;
  
  public GenericData$Array(int paramInt, Schema paramSchema) {
    if (paramSchema == null || !Schema.Type.ARRAY.equals(paramSchema.getType()))
      throw new AvroRuntimeException("Not an array schema: " + paramSchema); 
    this.schema = paramSchema;
    if (paramInt != 0)
      this.elements = new Object[paramInt]; 
  }
  
  public GenericData$Array(Schema paramSchema, Collection<? extends E> paramCollection) {
    if (paramSchema == null || !Schema.Type.ARRAY.equals(paramSchema.getType()))
      throw new AvroRuntimeException("Not an array schema: " + paramSchema); 
    this.schema = paramSchema;
    if (paramCollection != null) {
      this.elements = new Object[paramCollection.size()];
      addAll(paramCollection);
    } 
  }
  
  public void add(int paramInt, Object paramObject) {
    if (paramInt > this.size || paramInt < 0)
      throw new IndexOutOfBoundsException("Index " + paramInt + " out of bounds."); 
    if (this.size == this.elements.length) {
      Object[] arrayOfObject = new Object[this.size * 3 / 2 + 1];
      System.arraycopy(this.elements, 0, arrayOfObject, 0, this.size);
      this.elements = arrayOfObject;
    } 
    System.arraycopy(this.elements, paramInt, this.elements, paramInt + 1, this.size - paramInt);
    this.elements[paramInt] = paramObject;
    this.size++;
  }
  
  public boolean add(Object paramObject) {
    if (this.size == this.elements.length) {
      Object[] arrayOfObject1 = new Object[this.size * 3 / 2 + 1];
      System.arraycopy(this.elements, 0, arrayOfObject1, 0, this.size);
      this.elements = arrayOfObject1;
    } 
    Object[] arrayOfObject = this.elements;
    int i = this.size;
    this.size = i + 1;
    arrayOfObject[i] = paramObject;
    return true;
  }
  
  public void clear() {
    this.size = 0;
  }
  
  public int compareTo(GenericArray paramGenericArray) {
    return GenericData.get().compare(this, paramGenericArray, getSchema());
  }
  
  public Object get(int paramInt) {
    if (paramInt >= this.size)
      throw new IndexOutOfBoundsException("Index " + paramInt + " out of bounds."); 
    return this.elements[paramInt];
  }
  
  public Schema getSchema() {
    return this.schema;
  }
  
  public Iterator iterator() {
    return new GenericData$Array$1(this);
  }
  
  public Object peek() {
    return (this.size < this.elements.length) ? this.elements[this.size] : null;
  }
  
  public Object remove(int paramInt) {
    if (paramInt >= this.size)
      throw new IndexOutOfBoundsException("Index " + paramInt + " out of bounds."); 
    Object object = this.elements[paramInt];
    this.size--;
    System.arraycopy(this.elements, paramInt + 1, this.elements, paramInt, this.size - paramInt);
    this.elements[this.size] = null;
    return object;
  }
  
  public void reverse() {
    byte b = 0;
    for (int i = this.elements.length - 1; b < i; i--) {
      Object object = this.elements[b];
      this.elements[b] = this.elements[i];
      this.elements[i] = object;
      b++;
    } 
  }
  
  public Object set(int paramInt, Object paramObject) {
    if (paramInt >= this.size)
      throw new IndexOutOfBoundsException("Index " + paramInt + " out of bounds."); 
    Object object = this.elements[paramInt];
    this.elements[paramInt] = paramObject;
    return object;
  }
  
  public int size() {
    return this.size;
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("[");
    int i = 0;
    for (String str : this) {
      if (str == null) {
        str = "null";
      } else {
        str = str.toString();
      } 
      stringBuffer.append(str);
      int j = i + 1;
      i = j;
      if (j < size()) {
        stringBuffer.append(", ");
        i = j;
      } 
    } 
    stringBuffer.append("]");
    return stringBuffer.toString();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\generic\GenericData$Array.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */