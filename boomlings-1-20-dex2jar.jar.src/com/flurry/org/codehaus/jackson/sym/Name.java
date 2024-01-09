package com.flurry.org.codehaus.jackson.sym;

public abstract class Name {
  protected final int _hashCode;
  
  protected final String _name;
  
  protected Name(String paramString, int paramInt) {
    this._name = paramString;
    this._hashCode = paramInt;
  }
  
  public abstract boolean equals(int paramInt);
  
  public abstract boolean equals(int paramInt1, int paramInt2);
  
  public boolean equals(Object paramObject) {
    return (paramObject == this);
  }
  
  public abstract boolean equals(int[] paramArrayOfint, int paramInt);
  
  public String getName() {
    return this._name;
  }
  
  public final int hashCode() {
    return this._hashCode;
  }
  
  public String toString() {
    return this._name;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\sym\Name.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */