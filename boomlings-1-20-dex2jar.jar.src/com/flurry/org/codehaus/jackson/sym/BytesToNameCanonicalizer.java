package com.flurry.org.codehaus.jackson.sym;

import com.flurry.org.codehaus.jackson.util.InternCache;
import java.util.Arrays;

public final class BytesToNameCanonicalizer {
  protected static final int DEFAULT_TABLE_SIZE = 64;
  
  static final int INITIAL_COLLISION_LEN = 32;
  
  static final int LAST_VALID_BUCKET = 254;
  
  static final int MAX_ENTRIES_FOR_REUSE = 6000;
  
  protected static final int MAX_TABLE_SIZE = 65536;
  
  static final int MIN_HASH_SIZE = 16;
  
  private int _collCount;
  
  private int _collEnd;
  
  private BytesToNameCanonicalizer$Bucket[] _collList;
  
  private boolean _collListShared;
  
  private int _count;
  
  final boolean _intern;
  
  private int[] _mainHash;
  
  private int _mainHashMask;
  
  private boolean _mainHashShared;
  
  private Name[] _mainNames;
  
  private boolean _mainNamesShared;
  
  private transient boolean _needRehash;
  
  final BytesToNameCanonicalizer _parent;
  
  private BytesToNameCanonicalizer(int paramInt, boolean paramBoolean) {
    int i;
    this._parent = null;
    this._intern = paramBoolean;
    if (paramInt < 16) {
      i = 16;
    } else {
      i = paramInt;
      if ((paramInt - 1 & paramInt) != 0) {
        i = b;
        while (true) {
          if (i < paramInt) {
            i += i;
            continue;
          } 
          initTables(i);
          return;
        } 
      } 
    } 
    initTables(i);
  }
  
  private BytesToNameCanonicalizer(BytesToNameCanonicalizer paramBytesToNameCanonicalizer, boolean paramBoolean) {
    this._parent = paramBytesToNameCanonicalizer;
    this._intern = paramBoolean;
    this._count = paramBytesToNameCanonicalizer._count;
    this._mainHashMask = paramBytesToNameCanonicalizer._mainHashMask;
    this._mainHash = paramBytesToNameCanonicalizer._mainHash;
    this._mainNames = paramBytesToNameCanonicalizer._mainNames;
    this._collList = paramBytesToNameCanonicalizer._collList;
    this._collCount = paramBytesToNameCanonicalizer._collCount;
    this._collEnd = paramBytesToNameCanonicalizer._collEnd;
    this._needRehash = false;
    this._mainHashShared = true;
    this._mainNamesShared = true;
    this._collListShared = true;
  }
  
  private void _addSymbol(int paramInt, Name paramName) {
    if (this._mainHashShared)
      unshareMain(); 
    if (this._needRehash)
      rehash(); 
    this._count++;
    int j = paramInt & this._mainHashMask;
    if (this._mainNames[j] == null) {
      this._mainHash[j] = paramInt << 8;
      if (this._mainNamesShared)
        unshareNames(); 
      this._mainNames[j] = paramName;
    } else {
      if (this._collListShared)
        unshareCollision(); 
      this._collCount++;
      int k = this._mainHash[j];
      paramInt = k & 0xFF;
      if (paramInt == 0) {
        if (this._collEnd <= 254) {
          int m = this._collEnd;
          this._collEnd++;
          paramInt = m;
          if (m >= this._collList.length) {
            expandCollision();
            paramInt = m;
          } 
        } else {
          paramInt = findBestBucket();
        } 
        this._mainHash[j] = k & 0xFFFFFF00 | paramInt + 1;
      } else {
        paramInt--;
      } 
      this._collList[paramInt] = new BytesToNameCanonicalizer$Bucket(paramName, this._collList[paramInt]);
    } 
    int i = this._mainHash.length;
    if (this._count > i >> 1) {
      paramInt = i >> 2;
      if (this._count > i - paramInt) {
        this._needRehash = true;
        return;
      } 
    } else {
      return;
    } 
    if (this._collCount >= paramInt)
      this._needRehash = true; 
  }
  
  public static final int calcHash(int paramInt) {
    paramInt = paramInt >>> 16 ^ paramInt;
    return paramInt ^ paramInt >>> 8;
  }
  
  public static final int calcHash(int paramInt1, int paramInt2) {
    paramInt1 = paramInt1 * 31 + paramInt2;
    paramInt1 ^= paramInt1 >>> 16;
    return paramInt1 ^ paramInt1 >>> 8;
  }
  
  public static final int calcHash(int[] paramArrayOfint, int paramInt) {
    int i = paramArrayOfint[0];
    for (byte b = 1; b < paramInt; b++)
      i = i * 31 + paramArrayOfint[b]; 
    paramInt = i >>> 16 ^ i;
    return paramInt ^ paramInt >>> 8;
  }
  
  private static Name constructName(int paramInt1, String paramString, int paramInt2, int paramInt3) {
    return (Name)((paramInt3 == 0) ? new Name1(paramString, paramInt1, paramInt2) : new Name2(paramString, paramInt1, paramInt2, paramInt3));
  }
  
  private static Name constructName(int paramInt1, String paramString, int[] paramArrayOfint, int paramInt2) {
    if (paramInt2 < 4) {
      byte b;
      int[] arrayOfInt;
      switch (paramInt2) {
        default:
          arrayOfInt = new int[paramInt2];
          for (b = 0; b < paramInt2; b++)
            arrayOfInt[b] = paramArrayOfint[b]; 
          break;
        case 1:
          return new Name1(paramString, paramInt1, paramArrayOfint[0]);
        case 2:
          return new Name2(paramString, paramInt1, paramArrayOfint[0], paramArrayOfint[1]);
        case 3:
          return new Name3(paramString, paramInt1, paramArrayOfint[0], paramArrayOfint[1], paramArrayOfint[2]);
      } 
      return new NameN(paramString, paramInt1, arrayOfInt, paramInt2);
    } 
  }
  
  public static BytesToNameCanonicalizer createRoot() {
    return new BytesToNameCanonicalizer(64, true);
  }
  
  private void expandCollision() {
    BytesToNameCanonicalizer$Bucket[] arrayOfBytesToNameCanonicalizer$Bucket = this._collList;
    int i = arrayOfBytesToNameCanonicalizer$Bucket.length;
    this._collList = new BytesToNameCanonicalizer$Bucket[i + i];
    System.arraycopy(arrayOfBytesToNameCanonicalizer$Bucket, 0, this._collList, 0, i);
  }
  
  private int findBestBucket() {
    BytesToNameCanonicalizer$Bucket[] arrayOfBytesToNameCanonicalizer$Bucket = this._collList;
    int k = Integer.MAX_VALUE;
    int j = -1;
    int i = 0;
    int m = this._collEnd;
    while (true) {
      if (i < m) {
        int n = arrayOfBytesToNameCanonicalizer$Bucket[i].length();
        if (n < k) {
          if (n == 1)
            return i; 
          k = i;
          j = n;
        } else {
          n = k;
          k = j;
          j = n;
        } 
        i++;
        n = j;
        j = k;
        k = n;
        continue;
      } 
      return j;
    } 
  }
  
  public static Name getEmptyName() {
    return Name1.getEmptyName();
  }
  
  private void initTables(int paramInt) {
    this._count = 0;
    this._mainHash = new int[paramInt];
    this._mainNames = new Name[paramInt];
    this._mainHashShared = false;
    this._mainNamesShared = false;
    this._mainHashMask = paramInt - 1;
    this._collListShared = true;
    this._collList = null;
    this._collEnd = 0;
    this._needRehash = false;
  }
  
  private void markAsShared() {
    this._mainHashShared = true;
    this._mainNamesShared = true;
    this._collListShared = true;
  }
  
  private void mergeChild(BytesToNameCanonicalizer paramBytesToNameCanonicalizer) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: getfield _count : I
    //   6: istore_2
    //   7: aload_0
    //   8: getfield _count : I
    //   11: istore_3
    //   12: iload_2
    //   13: iload_3
    //   14: if_icmpgt -> 20
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: aload_1
    //   21: invokevirtual size : ()I
    //   24: sipush #6000
    //   27: if_icmple -> 44
    //   30: aload_0
    //   31: bipush #64
    //   33: invokespecial initTables : (I)V
    //   36: goto -> 17
    //   39: astore_1
    //   40: aload_0
    //   41: monitorexit
    //   42: aload_1
    //   43: athrow
    //   44: aload_0
    //   45: aload_1
    //   46: getfield _count : I
    //   49: putfield _count : I
    //   52: aload_0
    //   53: aload_1
    //   54: getfield _mainHash : [I
    //   57: putfield _mainHash : [I
    //   60: aload_0
    //   61: aload_1
    //   62: getfield _mainNames : [Lcom/flurry/org/codehaus/jackson/sym/Name;
    //   65: putfield _mainNames : [Lcom/flurry/org/codehaus/jackson/sym/Name;
    //   68: aload_0
    //   69: iconst_1
    //   70: putfield _mainHashShared : Z
    //   73: aload_0
    //   74: iconst_1
    //   75: putfield _mainNamesShared : Z
    //   78: aload_0
    //   79: aload_1
    //   80: getfield _mainHashMask : I
    //   83: putfield _mainHashMask : I
    //   86: aload_0
    //   87: aload_1
    //   88: getfield _collList : [Lcom/flurry/org/codehaus/jackson/sym/BytesToNameCanonicalizer$Bucket;
    //   91: putfield _collList : [Lcom/flurry/org/codehaus/jackson/sym/BytesToNameCanonicalizer$Bucket;
    //   94: aload_0
    //   95: aload_1
    //   96: getfield _collCount : I
    //   99: putfield _collCount : I
    //   102: aload_0
    //   103: aload_1
    //   104: getfield _collEnd : I
    //   107: putfield _collEnd : I
    //   110: goto -> 17
    // Exception table:
    //   from	to	target	type
    //   2	12	39	finally
    //   20	36	39	finally
    //   44	110	39	finally
  }
  
  private void nukeSymbols() {
    this._count = 0;
    Arrays.fill(this._mainHash, 0);
    Arrays.fill((Object[])this._mainNames, (Object)null);
    Arrays.fill((Object[])this._collList, (Object)null);
    this._collCount = 0;
    this._collEnd = 0;
  }
  
  private void rehash() {
    this._needRehash = false;
    this._mainNamesShared = false;
    int k = this._mainHash.length;
    int i = k + k;
    if (i > 65536) {
      nukeSymbols();
      return;
    } 
    this._mainHash = new int[i];
    this._mainHashMask = i - 1;
    Name[] arrayOfName = this._mainNames;
    this._mainNames = new Name[i];
    int j = 0;
    for (i = 0; j < k; i = n) {
      Name name = arrayOfName[j];
      int n = i;
      if (name != null) {
        n = i + 1;
        i = name.hashCode();
        int i1 = this._mainHashMask & i;
        this._mainNames[i1] = name;
        this._mainHash[i1] = i << 8;
      } 
      j++;
    } 
    int m = this._collEnd;
    if (m != 0) {
      this._collCount = 0;
      this._collEnd = 0;
      this._collListShared = false;
      BytesToNameCanonicalizer$Bucket[] arrayOfBytesToNameCanonicalizer$Bucket = this._collList;
      this._collList = new BytesToNameCanonicalizer$Bucket[arrayOfBytesToNameCanonicalizer$Bucket.length];
      for (byte b = 0; b < m; b++) {
        BytesToNameCanonicalizer$Bucket bytesToNameCanonicalizer$Bucket = arrayOfBytesToNameCanonicalizer$Bucket[b];
        while (bytesToNameCanonicalizer$Bucket != null) {
          Name name = bytesToNameCanonicalizer$Bucket._name;
          j = name.hashCode();
          int n = this._mainHashMask & j;
          int i1 = this._mainHash[n];
          if (this._mainNames[n] == null) {
            this._mainHash[n] = j << 8;
            this._mainNames[n] = name;
          } else {
            this._collCount++;
            j = i1 & 0xFF;
            if (j == 0) {
              if (this._collEnd <= 254) {
                k = this._collEnd;
                this._collEnd++;
                j = k;
                if (k >= this._collList.length) {
                  expandCollision();
                  j = k;
                } 
              } else {
                j = findBestBucket();
              } 
              this._mainHash[n] = i1 & 0xFFFFFF00 | j + 1;
            } else {
              j--;
            } 
            this._collList[j] = new BytesToNameCanonicalizer$Bucket(name, this._collList[j]);
          } 
          bytesToNameCanonicalizer$Bucket = bytesToNameCanonicalizer$Bucket._next;
          i++;
        } 
      } 
      if (i != this._count)
        throw new RuntimeException("Internal error: count after rehash " + i + "; should be " + this._count); 
    } 
  }
  
  private void unshareCollision() {
    BytesToNameCanonicalizer$Bucket[] arrayOfBytesToNameCanonicalizer$Bucket = this._collList;
    if (arrayOfBytesToNameCanonicalizer$Bucket == null) {
      this._collList = new BytesToNameCanonicalizer$Bucket[32];
    } else {
      int i = arrayOfBytesToNameCanonicalizer$Bucket.length;
      this._collList = new BytesToNameCanonicalizer$Bucket[i];
      System.arraycopy(arrayOfBytesToNameCanonicalizer$Bucket, 0, this._collList, 0, i);
    } 
    this._collListShared = false;
  }
  
  private void unshareMain() {
    int[] arrayOfInt = this._mainHash;
    int i = this._mainHash.length;
    this._mainHash = new int[i];
    System.arraycopy(arrayOfInt, 0, this._mainHash, 0, i);
    this._mainHashShared = false;
  }
  
  private void unshareNames() {
    Name[] arrayOfName = this._mainNames;
    int i = arrayOfName.length;
    this._mainNames = new Name[i];
    System.arraycopy(arrayOfName, 0, this._mainNames, 0, i);
    this._mainNamesShared = false;
  }
  
  public Name addName(String paramString, int paramInt1, int paramInt2) {
    String str = paramString;
    if (this._intern)
      str = InternCache.instance.intern(paramString); 
    if (paramInt2 == 0) {
      int j = calcHash(paramInt1);
      Name name1 = constructName(j, str, paramInt1, paramInt2);
      _addSymbol(j, name1);
      return name1;
    } 
    int i = calcHash(paramInt1, paramInt2);
    Name name = constructName(i, str, paramInt1, paramInt2);
    _addSymbol(i, name);
    return name;
  }
  
  public Name addName(String paramString, int[] paramArrayOfint, int paramInt) {
    String str = paramString;
    if (this._intern)
      str = InternCache.instance.intern(paramString); 
    int i = calcHash(paramArrayOfint, paramInt);
    Name name = constructName(i, str, paramArrayOfint, paramInt);
    _addSymbol(i, name);
    return name;
  }
  
  public Name findName(int paramInt) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #6
    //   3: iload_1
    //   4: invokestatic calcHash : (I)I
    //   7: istore_2
    //   8: aload_0
    //   9: getfield _mainHashMask : I
    //   12: iload_2
    //   13: iand
    //   14: istore_3
    //   15: aload_0
    //   16: getfield _mainHash : [I
    //   19: iload_3
    //   20: iaload
    //   21: istore #4
    //   23: iload #4
    //   25: bipush #8
    //   27: ishr
    //   28: iload_2
    //   29: ixor
    //   30: bipush #8
    //   32: ishl
    //   33: ifne -> 68
    //   36: aload_0
    //   37: getfield _mainNames : [Lcom/flurry/org/codehaus/jackson/sym/Name;
    //   40: iload_3
    //   41: aaload
    //   42: astore #5
    //   44: aload #5
    //   46: ifnonnull -> 56
    //   49: aload #6
    //   51: astore #5
    //   53: aload #5
    //   55: areturn
    //   56: aload #5
    //   58: iload_1
    //   59: invokevirtual equals : (I)Z
    //   62: ifeq -> 77
    //   65: goto -> 53
    //   68: aload #6
    //   70: astore #5
    //   72: iload #4
    //   74: ifeq -> 53
    //   77: iload #4
    //   79: sipush #255
    //   82: iand
    //   83: istore_3
    //   84: aload #6
    //   86: astore #5
    //   88: iload_3
    //   89: ifle -> 53
    //   92: aload_0
    //   93: getfield _collList : [Lcom/flurry/org/codehaus/jackson/sym/BytesToNameCanonicalizer$Bucket;
    //   96: iload_3
    //   97: iconst_1
    //   98: isub
    //   99: aaload
    //   100: astore #7
    //   102: aload #6
    //   104: astore #5
    //   106: aload #7
    //   108: ifnull -> 53
    //   111: aload #7
    //   113: iload_2
    //   114: iload_1
    //   115: iconst_0
    //   116: invokevirtual find : (III)Lcom/flurry/org/codehaus/jackson/sym/Name;
    //   119: astore #5
    //   121: goto -> 53
  }
  
  public Name findName(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #7
    //   3: iload_1
    //   4: iload_2
    //   5: invokestatic calcHash : (II)I
    //   8: istore_3
    //   9: aload_0
    //   10: getfield _mainHashMask : I
    //   13: iload_3
    //   14: iand
    //   15: istore #5
    //   17: aload_0
    //   18: getfield _mainHash : [I
    //   21: iload #5
    //   23: iaload
    //   24: istore #4
    //   26: iload #4
    //   28: bipush #8
    //   30: ishr
    //   31: iload_3
    //   32: ixor
    //   33: bipush #8
    //   35: ishl
    //   36: ifne -> 73
    //   39: aload_0
    //   40: getfield _mainNames : [Lcom/flurry/org/codehaus/jackson/sym/Name;
    //   43: iload #5
    //   45: aaload
    //   46: astore #6
    //   48: aload #6
    //   50: ifnonnull -> 60
    //   53: aload #7
    //   55: astore #6
    //   57: aload #6
    //   59: areturn
    //   60: aload #6
    //   62: iload_1
    //   63: iload_2
    //   64: invokevirtual equals : (II)Z
    //   67: ifeq -> 82
    //   70: goto -> 57
    //   73: aload #7
    //   75: astore #6
    //   77: iload #4
    //   79: ifeq -> 57
    //   82: iload #4
    //   84: sipush #255
    //   87: iand
    //   88: istore #4
    //   90: aload #7
    //   92: astore #6
    //   94: iload #4
    //   96: ifle -> 57
    //   99: aload_0
    //   100: getfield _collList : [Lcom/flurry/org/codehaus/jackson/sym/BytesToNameCanonicalizer$Bucket;
    //   103: iload #4
    //   105: iconst_1
    //   106: isub
    //   107: aaload
    //   108: astore #8
    //   110: aload #7
    //   112: astore #6
    //   114: aload #8
    //   116: ifnull -> 57
    //   119: aload #8
    //   121: iload_3
    //   122: iload_1
    //   123: iload_2
    //   124: invokevirtual find : (III)Lcom/flurry/org/codehaus/jackson/sym/Name;
    //   127: astore #6
    //   129: goto -> 57
  }
  
  public Name findName(int[] paramArrayOfint, int paramInt) {
    int i = calcHash(paramArrayOfint, paramInt);
    int k = this._mainHashMask & i;
    int j = this._mainHash[k];
    if ((j >> 8 ^ i) << 8 == 0) {
      Name name2 = this._mainNames[k];
      Name name1 = name2;
      if (name2 != null) {
        if (name2.equals(paramArrayOfint, paramInt))
          return name2; 
      } else {
        return name1;
      } 
    } else if (j == 0) {
      return null;
    } 
    j &= 0xFF;
    if (j > 0) {
      BytesToNameCanonicalizer$Bucket bytesToNameCanonicalizer$Bucket = this._collList[j - 1];
      if (bytesToNameCanonicalizer$Bucket != null)
        return bytesToNameCanonicalizer$Bucket.find(i, paramArrayOfint, paramInt); 
    } 
    return null;
  }
  
  public BytesToNameCanonicalizer makeChild(boolean paramBoolean1, boolean paramBoolean2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/flurry/org/codehaus/jackson/sym/BytesToNameCanonicalizer
    //   5: dup
    //   6: aload_0
    //   7: iload_2
    //   8: invokespecial <init> : (Lcom/flurry/org/codehaus/jackson/sym/BytesToNameCanonicalizer;Z)V
    //   11: astore_3
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_3
    //   15: areturn
    //   16: astore_3
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_3
    //   20: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	16	finally
  }
  
  public boolean maybeDirty() {
    return !this._mainHashShared;
  }
  
  public void release() {
    if (maybeDirty() && this._parent != null) {
      this._parent.mergeChild(this);
      markAsShared();
    } 
  }
  
  public int size() {
    return this._count;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\sym\BytesToNameCanonicalizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */