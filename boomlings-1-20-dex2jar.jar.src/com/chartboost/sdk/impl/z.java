package com.chartboost.sdk.impl;

public class z {
  final Object a;
  
  final String b;
  
  public Object a() {
    return this.a;
  }
  
  public String b() {
    return this.b;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this != paramObject) {
      if (paramObject == null || getClass() != paramObject.getClass())
        return false; 
      paramObject = paramObject;
      if ((this.a != null) ? !this.a.equals(((z)paramObject).a) : (((z)paramObject).a != null))
        return false; 
      if ((this.b != null) ? !this.b.equals(((z)paramObject).b) : (((z)paramObject).b != null))
        bool = false; 
    } 
    return bool;
  }
  
  public int hashCode() {
    byte b;
    int i = 0;
    if (this.a != null) {
      b = this.a.hashCode();
    } else {
      b = 0;
    } 
    if (this.b != null)
      i = this.b.hashCode(); 
    return b * 31 + i;
  }
  
  public String toString() {
    return "{ \"$ref\" : \"" + this.b + "\", \"$id\" : \"" + this.a + "\" }";
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\z.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */