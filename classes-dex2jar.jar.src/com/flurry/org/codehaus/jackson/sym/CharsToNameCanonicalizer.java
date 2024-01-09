package com.flurry.org.codehaus.jackson.sym;

import com.flurry.org.codehaus.jackson.util.InternCache;
import java.util.Arrays;

public final class CharsToNameCanonicalizer {
  protected static final int DEFAULT_TABLE_SIZE = 64;
  
  static final int MAX_ENTRIES_FOR_REUSE = 12000;
  
  protected static final int MAX_TABLE_SIZE = 65536;
  
  static final CharsToNameCanonicalizer sBootstrapSymbolTable = new CharsToNameCanonicalizer();
  
  protected CharsToNameCanonicalizer$Bucket[] _buckets;
  
  protected final boolean _canonicalize;
  
  protected boolean _dirty;
  
  protected int _indexMask;
  
  protected final boolean _intern;
  
  protected CharsToNameCanonicalizer _parent;
  
  protected int _size;
  
  protected int _sizeThreshold;
  
  protected String[] _symbols;
  
  private CharsToNameCanonicalizer() {
    this._canonicalize = true;
    this._intern = true;
    this._dirty = true;
    initTables(64);
  }
  
  private CharsToNameCanonicalizer(CharsToNameCanonicalizer paramCharsToNameCanonicalizer, boolean paramBoolean1, boolean paramBoolean2, String[] paramArrayOfString, CharsToNameCanonicalizer$Bucket[] paramArrayOfCharsToNameCanonicalizer$Bucket, int paramInt) {
    this._parent = paramCharsToNameCanonicalizer;
    this._canonicalize = paramBoolean1;
    this._intern = paramBoolean2;
    this._symbols = paramArrayOfString;
    this._buckets = paramArrayOfCharsToNameCanonicalizer$Bucket;
    this._size = paramInt;
    paramInt = paramArrayOfString.length;
    this._sizeThreshold = paramInt - (paramInt >> 2);
    this._indexMask = paramInt - 1;
    this._dirty = false;
  }
  
  public static int calcHash(String paramString) {
    int i = paramString.charAt(0);
    byte b = 1;
    int j = paramString.length();
    while (b < j) {
      i = i * 31 + paramString.charAt(b);
      b++;
    } 
    return i;
  }
  
  public static int calcHash(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    paramInt1 = paramArrayOfchar[0];
    for (byte b = 1; b < paramInt2; b++)
      paramInt1 = paramInt1 * 31 + paramArrayOfchar[b]; 
    return paramInt1;
  }
  
  private void copyArrays() {
    String[] arrayOfString = this._symbols;
    int i = arrayOfString.length;
    this._symbols = new String[i];
    System.arraycopy(arrayOfString, 0, this._symbols, 0, i);
    CharsToNameCanonicalizer$Bucket[] arrayOfCharsToNameCanonicalizer$Bucket = this._buckets;
    i = arrayOfCharsToNameCanonicalizer$Bucket.length;
    this._buckets = new CharsToNameCanonicalizer$Bucket[i];
    System.arraycopy(arrayOfCharsToNameCanonicalizer$Bucket, 0, this._buckets, 0, i);
  }
  
  public static CharsToNameCanonicalizer createRoot() {
    return sBootstrapSymbolTable.makeOrphan();
  }
  
  private void initTables(int paramInt) {
    this._symbols = new String[paramInt];
    this._buckets = new CharsToNameCanonicalizer$Bucket[paramInt >> 1];
    this._indexMask = paramInt - 1;
    this._size = 0;
    this._sizeThreshold = paramInt - (paramInt >> 2);
  }
  
  private CharsToNameCanonicalizer makeOrphan() {
    return new CharsToNameCanonicalizer(null, true, true, this._symbols, this._buckets, this._size);
  }
  
  private void mergeChild(CharsToNameCanonicalizer paramCharsToNameCanonicalizer) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual size : ()I
    //   6: sipush #12000
    //   9: if_icmple -> 26
    //   12: aload_0
    //   13: bipush #64
    //   15: invokespecial initTables : (I)V
    //   18: aload_0
    //   19: iconst_0
    //   20: putfield _dirty : Z
    //   23: aload_0
    //   24: monitorexit
    //   25: return
    //   26: aload_1
    //   27: invokevirtual size : ()I
    //   30: aload_0
    //   31: invokevirtual size : ()I
    //   34: if_icmple -> 23
    //   37: aload_0
    //   38: aload_1
    //   39: getfield _symbols : [Ljava/lang/String;
    //   42: putfield _symbols : [Ljava/lang/String;
    //   45: aload_0
    //   46: aload_1
    //   47: getfield _buckets : [Lcom/flurry/org/codehaus/jackson/sym/CharsToNameCanonicalizer$Bucket;
    //   50: putfield _buckets : [Lcom/flurry/org/codehaus/jackson/sym/CharsToNameCanonicalizer$Bucket;
    //   53: aload_0
    //   54: aload_1
    //   55: getfield _size : I
    //   58: putfield _size : I
    //   61: aload_0
    //   62: aload_1
    //   63: getfield _sizeThreshold : I
    //   66: putfield _sizeThreshold : I
    //   69: aload_0
    //   70: aload_1
    //   71: getfield _indexMask : I
    //   74: putfield _indexMask : I
    //   77: goto -> 18
    //   80: astore_1
    //   81: aload_0
    //   82: monitorexit
    //   83: aload_1
    //   84: athrow
    // Exception table:
    //   from	to	target	type
    //   2	18	80	finally
    //   18	23	80	finally
    //   26	77	80	finally
  }
  
  private void rehash() {
    int m = this._symbols.length;
    int i = m + m;
    if (i > 65536) {
      this._size = 0;
      Arrays.fill((Object[])this._symbols, (Object)null);
      Arrays.fill((Object[])this._buckets, (Object)null);
      this._dirty = true;
      return;
    } 
    String[] arrayOfString = this._symbols;
    CharsToNameCanonicalizer$Bucket[] arrayOfCharsToNameCanonicalizer$Bucket = this._buckets;
    this._symbols = new String[i];
    this._buckets = new CharsToNameCanonicalizer$Bucket[i >> 1];
    this._indexMask = i - 1;
    this._sizeThreshold += this._sizeThreshold;
    int j = 0;
    for (i = 0; j < m; i = n) {
      String str = arrayOfString[j];
      int n = i;
      if (str != null) {
        n = i + 1;
        i = calcHash(str) & this._indexMask;
        if (this._symbols[i] == null) {
          this._symbols[i] = str;
        } else {
          i >>= 1;
          this._buckets[i] = new CharsToNameCanonicalizer$Bucket(str, this._buckets[i]);
        } 
      } 
      j++;
    } 
    int k = 0;
    j = i;
    for (i = k; i < m >> 1; i++) {
      for (CharsToNameCanonicalizer$Bucket charsToNameCanonicalizer$Bucket = arrayOfCharsToNameCanonicalizer$Bucket[i]; charsToNameCanonicalizer$Bucket != null; charsToNameCanonicalizer$Bucket = charsToNameCanonicalizer$Bucket.getNext()) {
        j++;
        String str = charsToNameCanonicalizer$Bucket.getSymbol();
        k = calcHash(str) & this._indexMask;
        if (this._symbols[k] == null) {
          this._symbols[k] = str;
        } else {
          k >>= 1;
          this._buckets[k] = new CharsToNameCanonicalizer$Bucket(str, this._buckets[k]);
        } 
      } 
    } 
    if (j != this._size)
      throw new Error("Internal error on SymbolTable.rehash(): had " + this._size + " entries; now have " + j + "."); 
  }
  
  public String findSymbol(char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt2 < 1)
      return ""; 
    if (!this._canonicalize)
      return new String(paramArrayOfchar, paramInt1, paramInt2); 
    int i = paramInt3 & this._indexMask;
    String str2 = this._symbols[i];
    if (str2 != null) {
      if (str2.length() == paramInt2) {
        paramInt3 = 0;
        while (true) {
          if (str2.charAt(paramInt3) == paramArrayOfchar[paramInt1 + paramInt3]) {
            int j = paramInt3 + 1;
            paramInt3 = j;
            if (j >= paramInt2) {
              paramInt3 = j;
            } else {
              continue;
            } 
          } 
          if (paramInt3 == paramInt2)
            return str2; 
          break;
        } 
      } 
      CharsToNameCanonicalizer$Bucket charsToNameCanonicalizer$Bucket = this._buckets[i >> 1];
      if (charsToNameCanonicalizer$Bucket != null) {
        String str = charsToNameCanonicalizer$Bucket.find(paramArrayOfchar, paramInt1, paramInt2);
        if (str != null)
          return str; 
      } 
    } 
    if (!this._dirty) {
      copyArrays();
      this._dirty = true;
      paramInt3 = i;
    } else if (this._size >= this._sizeThreshold) {
      rehash();
      paramInt3 = calcHash(paramArrayOfchar, paramInt1, paramInt2) & this._indexMask;
    } else {
      paramInt3 = i;
    } 
    this._size++;
    str2 = new String(paramArrayOfchar, paramInt1, paramInt2);
    String str1 = str2;
    if (this._intern)
      str1 = InternCache.instance.intern(str2); 
    if (this._symbols[paramInt3] == null) {
      this._symbols[paramInt3] = str1;
      return str1;
    } 
    paramInt1 = paramInt3 >> 1;
    this._buckets[paramInt1] = new CharsToNameCanonicalizer$Bucket(str1, this._buckets[paramInt1]);
    return str1;
  }
  
  public CharsToNameCanonicalizer makeChild(boolean paramBoolean1, boolean paramBoolean2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/flurry/org/codehaus/jackson/sym/CharsToNameCanonicalizer
    //   5: dup
    //   6: aload_0
    //   7: iload_1
    //   8: iload_2
    //   9: aload_0
    //   10: getfield _symbols : [Ljava/lang/String;
    //   13: aload_0
    //   14: getfield _buckets : [Lcom/flurry/org/codehaus/jackson/sym/CharsToNameCanonicalizer$Bucket;
    //   17: aload_0
    //   18: getfield _size : I
    //   21: invokespecial <init> : (Lcom/flurry/org/codehaus/jackson/sym/CharsToNameCanonicalizer;ZZ[Ljava/lang/String;[Lcom/flurry/org/codehaus/jackson/sym/CharsToNameCanonicalizer$Bucket;I)V
    //   24: astore_3
    //   25: aload_0
    //   26: monitorexit
    //   27: aload_3
    //   28: areturn
    //   29: astore_3
    //   30: aload_0
    //   31: monitorexit
    //   32: aload_3
    //   33: athrow
    // Exception table:
    //   from	to	target	type
    //   2	25	29	finally
  }
  
  public boolean maybeDirty() {
    return this._dirty;
  }
  
  public void release() {
    if (maybeDirty() && this._parent != null) {
      this._parent.mergeChild(this);
      this._dirty = false;
    } 
  }
  
  public int size() {
    return this._size;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\sym\CharsToNameCanonicalizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */