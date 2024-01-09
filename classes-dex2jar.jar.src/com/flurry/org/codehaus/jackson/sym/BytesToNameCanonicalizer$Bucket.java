package com.flurry.org.codehaus.jackson.sym;

final class BytesToNameCanonicalizer$Bucket {
  protected final Name _name;
  
  protected final BytesToNameCanonicalizer$Bucket _next;
  
  BytesToNameCanonicalizer$Bucket(Name paramName, BytesToNameCanonicalizer$Bucket paramBytesToNameCanonicalizer$Bucket) {
    this._name = paramName;
    this._next = paramBytesToNameCanonicalizer$Bucket;
  }
  
  public Name find(int paramInt1, int paramInt2, int paramInt3) {
    // Byte code:
    //   0: aload_0
    //   1: getfield _name : Lcom/flurry/org/codehaus/jackson/sym/Name;
    //   4: invokevirtual hashCode : ()I
    //   7: iload_1
    //   8: if_icmpne -> 32
    //   11: aload_0
    //   12: getfield _name : Lcom/flurry/org/codehaus/jackson/sym/Name;
    //   15: iload_2
    //   16: iload_3
    //   17: invokevirtual equals : (II)Z
    //   20: ifeq -> 32
    //   23: aload_0
    //   24: getfield _name : Lcom/flurry/org/codehaus/jackson/sym/Name;
    //   27: astore #4
    //   29: aload #4
    //   31: areturn
    //   32: aload_0
    //   33: getfield _next : Lcom/flurry/org/codehaus/jackson/sym/BytesToNameCanonicalizer$Bucket;
    //   36: astore #5
    //   38: aload #5
    //   40: ifnull -> 83
    //   43: aload #5
    //   45: getfield _name : Lcom/flurry/org/codehaus/jackson/sym/Name;
    //   48: astore #6
    //   50: aload #6
    //   52: invokevirtual hashCode : ()I
    //   55: iload_1
    //   56: if_icmpne -> 73
    //   59: aload #6
    //   61: astore #4
    //   63: aload #6
    //   65: iload_2
    //   66: iload_3
    //   67: invokevirtual equals : (II)Z
    //   70: ifne -> 29
    //   73: aload #5
    //   75: getfield _next : Lcom/flurry/org/codehaus/jackson/sym/BytesToNameCanonicalizer$Bucket;
    //   78: astore #5
    //   80: goto -> 38
    //   83: aconst_null
    //   84: astore #4
    //   86: goto -> 29
  }
  
  public Name find(int paramInt1, int[] paramArrayOfint, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: getfield _name : Lcom/flurry/org/codehaus/jackson/sym/Name;
    //   4: invokevirtual hashCode : ()I
    //   7: iload_1
    //   8: if_icmpne -> 32
    //   11: aload_0
    //   12: getfield _name : Lcom/flurry/org/codehaus/jackson/sym/Name;
    //   15: aload_2
    //   16: iload_3
    //   17: invokevirtual equals : ([II)Z
    //   20: ifeq -> 32
    //   23: aload_0
    //   24: getfield _name : Lcom/flurry/org/codehaus/jackson/sym/Name;
    //   27: astore #4
    //   29: aload #4
    //   31: areturn
    //   32: aload_0
    //   33: getfield _next : Lcom/flurry/org/codehaus/jackson/sym/BytesToNameCanonicalizer$Bucket;
    //   36: astore #5
    //   38: aload #5
    //   40: ifnull -> 83
    //   43: aload #5
    //   45: getfield _name : Lcom/flurry/org/codehaus/jackson/sym/Name;
    //   48: astore #6
    //   50: aload #6
    //   52: invokevirtual hashCode : ()I
    //   55: iload_1
    //   56: if_icmpne -> 73
    //   59: aload #6
    //   61: astore #4
    //   63: aload #6
    //   65: aload_2
    //   66: iload_3
    //   67: invokevirtual equals : ([II)Z
    //   70: ifne -> 29
    //   73: aload #5
    //   75: getfield _next : Lcom/flurry/org/codehaus/jackson/sym/BytesToNameCanonicalizer$Bucket;
    //   78: astore #5
    //   80: goto -> 38
    //   83: aconst_null
    //   84: astore #4
    //   86: goto -> 29
  }
  
  public int length() {
    byte b = 1;
    for (BytesToNameCanonicalizer$Bucket bytesToNameCanonicalizer$Bucket = this._next; bytesToNameCanonicalizer$Bucket != null; bytesToNameCanonicalizer$Bucket = bytesToNameCanonicalizer$Bucket._next)
      b++; 
    return b;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\sym\BytesToNameCanonicalizer$Bucket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */