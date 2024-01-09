package com.flurry.org.codehaus.jackson.map.type;

public final class ClassKey implements Comparable {
  private Class _class = null;
  
  private String _className = null;
  
  private int _hashCode = 0;
  
  public ClassKey() {}
  
  public ClassKey(Class paramClass) {}
  
  public int compareTo(ClassKey paramClassKey) {
    return this._className.compareTo(paramClassKey._className);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (paramObject == null)
        return false; 
      if (paramObject.getClass() != getClass())
        return false; 
      if (((ClassKey)paramObject)._class != this._class)
        bool = false; 
    } 
    return bool;
  }
  
  public int hashCode() {
    return this._hashCode;
  }
  
  public void reset(Class paramClass) {
    this._class = paramClass;
    this._className = paramClass.getName();
    this._hashCode = this._className.hashCode();
  }
  
  public String toString() {
    return this._className;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\type\ClassKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */