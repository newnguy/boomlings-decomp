package com.flurry.org.codehaus.jackson.sym;

public final class Name3 extends Name {
  final int mQuad1;
  
  final int mQuad2;
  
  final int mQuad3;
  
  Name3(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super(paramString, paramInt1);
    this.mQuad1 = paramInt2;
    this.mQuad2 = paramInt3;
    this.mQuad3 = paramInt4;
  }
  
  public boolean equals(int paramInt) {
    return false;
  }
  
  public boolean equals(int paramInt1, int paramInt2) {
    return false;
  }
  
  public boolean equals(int[] paramArrayOfint, int paramInt) {
    boolean bool = true;
    if (paramInt != 3 || paramArrayOfint[0] != this.mQuad1 || paramArrayOfint[1] != this.mQuad2 || paramArrayOfint[2] != this.mQuad3)
      bool = false; 
    return bool;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\sym\Name3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */