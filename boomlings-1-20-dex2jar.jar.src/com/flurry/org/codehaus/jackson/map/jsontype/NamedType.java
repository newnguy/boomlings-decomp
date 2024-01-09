package com.flurry.org.codehaus.jackson.map.jsontype;

public final class NamedType {
  protected final Class _class;
  
  protected final int _hashCode;
  
  protected String _name;
  
  public NamedType(Class paramClass) {
    this(paramClass, null);
  }
  
  public NamedType(Class paramClass, String paramString) {
    this._class = paramClass;
    this._hashCode = paramClass.getName().hashCode();
    setName(paramString);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (paramObject == null)
        return false; 
      if (paramObject.getClass() != getClass())
        return false; 
      if (this._class != ((NamedType)paramObject)._class)
        bool = false; 
    } 
    return bool;
  }
  
  public String getName() {
    return this._name;
  }
  
  public Class getType() {
    return this._class;
  }
  
  public boolean hasName() {
    return (this._name != null);
  }
  
  public int hashCode() {
    return this._hashCode;
  }
  
  public void setName(String paramString) {
    // Byte code:
    //   0: aload_1
    //   1: ifnull -> 13
    //   4: aload_1
    //   5: astore_2
    //   6: aload_1
    //   7: invokevirtual length : ()I
    //   10: ifne -> 15
    //   13: aconst_null
    //   14: astore_2
    //   15: aload_0
    //   16: aload_2
    //   17: putfield _name : Ljava/lang/String;
    //   20: return
  }
  
  public String toString() {
    StringBuilder stringBuilder = (new StringBuilder()).append("[NamedType, class ").append(this._class.getName()).append(", name: ");
    if (this._name == null) {
      String str1 = "null";
      return stringBuilder.append(str1).append("]").toString();
    } 
    String str = "'" + this._name + "'";
    return stringBuilder.append(str).append("]").toString();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\jsontype\NamedType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */