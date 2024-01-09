package com.flurry.org.codehaus.jackson.sym;

public final class NameN extends Name {
  final int mQuadLen;
  
  final int[] mQuads;
  
  NameN(String paramString, int paramInt1, int[] paramArrayOfint, int paramInt2) {
    super(paramString, paramInt1);
    if (paramInt2 < 3)
      throw new IllegalArgumentException("Qlen must >= 3"); 
    this.mQuads = paramArrayOfint;
    this.mQuadLen = paramInt2;
  }
  
  public boolean equals(int paramInt) {
    return false;
  }
  
  public boolean equals(int paramInt1, int paramInt2) {
    return false;
  }
  
  public boolean equals(int[] paramArrayOfint, int paramInt) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #5
    //   3: iload_2
    //   4: aload_0
    //   5: getfield mQuadLen : I
    //   8: if_icmpeq -> 18
    //   11: iload #5
    //   13: istore #4
    //   15: iload #4
    //   17: ireturn
    //   18: iconst_0
    //   19: istore_3
    //   20: iload_3
    //   21: iload_2
    //   22: if_icmpge -> 47
    //   25: iload #5
    //   27: istore #4
    //   29: aload_1
    //   30: iload_3
    //   31: iaload
    //   32: aload_0
    //   33: getfield mQuads : [I
    //   36: iload_3
    //   37: iaload
    //   38: if_icmpne -> 15
    //   41: iinc #3, 1
    //   44: goto -> 20
    //   47: iconst_1
    //   48: istore #4
    //   50: goto -> 15
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\sym\NameN.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */