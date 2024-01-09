package com.flurry.org.codehaus.jackson.io;

public final class NumberOutput {
  private static int BILLION;
  
  static final char[] FULL_TRIPLETS;
  
  static final byte[] FULL_TRIPLETS_B;
  
  static final char[] LEADING_TRIPLETS;
  
  private static long MAX_INT_AS_LONG;
  
  private static int MILLION = 1000000;
  
  private static long MIN_INT_AS_LONG;
  
  private static final char NULL_CHAR = '\000';
  
  static final String SMALLEST_LONG;
  
  private static long TEN_BILLION_L;
  
  private static long THOUSAND_L;
  
  static final String[] sSmallIntStrs;
  
  static final String[] sSmallIntStrs2;
  
  static {
    BILLION = 1000000000;
    TEN_BILLION_L = 10000000000L;
    THOUSAND_L = 1000L;
    MIN_INT_AS_LONG = -2147483648L;
    MAX_INT_AS_LONG = 2147483647L;
    SMALLEST_LONG = String.valueOf(Long.MIN_VALUE);
    LEADING_TRIPLETS = new char[4000];
    FULL_TRIPLETS = new char[4000];
    byte b2 = 0;
    byte b1 = 0;
    while (b2 < 10) {
      char c;
      char c1 = (char)(b2 + 48);
      if (b2 == 0) {
        c = Character.MIN_VALUE;
      } else {
        c = c1;
      } 
      for (byte b = 0; b < 10; b++) {
        char c2;
        char c3 = (char)(b + 48);
        if (b2 == 0 && b == 0) {
          c2 = Character.MIN_VALUE;
        } else {
          c2 = c3;
        } 
        for (byte b3 = 0; b3 < 10; b3++) {
          char c4 = (char)(b3 + 48);
          LEADING_TRIPLETS[b1] = c;
          LEADING_TRIPLETS[b1 + 1] = c2;
          LEADING_TRIPLETS[b1 + 2] = c4;
          FULL_TRIPLETS[b1] = c1;
          FULL_TRIPLETS[b1 + 1] = c3;
          FULL_TRIPLETS[b1 + 2] = c4;
          b1 += 4;
        } 
      } 
      b2++;
    } 
    FULL_TRIPLETS_B = new byte[4000];
    for (b1 = 0; b1 < 'ྠ'; b1++)
      FULL_TRIPLETS_B[b1] = (byte)FULL_TRIPLETS[b1]; 
    sSmallIntStrs = new String[] { 
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
        "10" };
    sSmallIntStrs2 = new String[] { "-1", "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10" };
  }
  
  private static int calcLongStrLength(long paramLong) {
    byte b = 10;
    for (long l = TEN_BILLION_L;; l = (l << 1L) + (l << 3L)) {
      if (paramLong < l || b == 19)
        return b; 
      b++;
    } 
  }
  
  private static int outputFullTriplet(int paramInt1, byte[] paramArrayOfbyte, int paramInt2) {
    int j = paramInt1 << 2;
    paramInt1 = paramInt2 + 1;
    byte[] arrayOfByte = FULL_TRIPLETS_B;
    int i = j + 1;
    paramArrayOfbyte[paramInt2] = arrayOfByte[j];
    paramInt2 = paramInt1 + 1;
    paramArrayOfbyte[paramInt1] = FULL_TRIPLETS_B[i];
    paramArrayOfbyte[paramInt2] = FULL_TRIPLETS_B[i + 1];
    return paramInt2 + 1;
  }
  
  private static int outputFullTriplet(int paramInt1, char[] paramArrayOfchar, int paramInt2) {
    int j = paramInt1 << 2;
    int i = paramInt2 + 1;
    char[] arrayOfChar = FULL_TRIPLETS;
    paramInt1 = j + 1;
    paramArrayOfchar[paramInt2] = arrayOfChar[j];
    paramInt2 = i + 1;
    paramArrayOfchar[i] = FULL_TRIPLETS[paramInt1];
    paramArrayOfchar[paramInt2] = FULL_TRIPLETS[paramInt1 + 1];
    return paramInt2 + 1;
  }
  
  public static int outputInt(int paramInt1, byte[] paramArrayOfbyte, int paramInt2) {
    boolean bool;
    int j = paramInt1;
    int i = paramInt2;
    if (paramInt1 < 0) {
      if (paramInt1 == Integer.MIN_VALUE)
        return outputLong(paramInt1, paramArrayOfbyte, paramInt2); 
      paramArrayOfbyte[paramInt2] = 45;
      j = -paramInt1;
      i = paramInt2 + 1;
    } 
    if (j < MILLION) {
      if (j < 1000) {
        if (j < 10) {
          paramInt1 = i + 1;
          paramArrayOfbyte[i] = (byte)(j + 48);
          return paramInt1;
        } 
        return outputLeadingTriplet(j, paramArrayOfbyte, i);
      } 
      paramInt1 = j / 1000;
      return outputFullTriplet(j - paramInt1 * 1000, paramArrayOfbyte, outputLeadingTriplet(paramInt1, paramArrayOfbyte, i));
    } 
    if (j >= BILLION) {
      bool = true;
    } else {
      bool = false;
    } 
    paramInt1 = j;
    paramInt2 = i;
    if (bool) {
      paramInt1 = j - BILLION;
      if (paramInt1 >= BILLION) {
        paramInt1 -= BILLION;
        paramArrayOfbyte[i] = 50;
        paramInt2 = i + 1;
      } else {
        paramArrayOfbyte[i] = 49;
        paramInt2 = i + 1;
      } 
    } 
    i = paramInt1 / 1000;
    j = i / 1000;
    if (bool) {
      paramInt2 = outputFullTriplet(j, paramArrayOfbyte, paramInt2);
    } else {
      paramInt2 = outputLeadingTriplet(j, paramArrayOfbyte, paramInt2);
    } 
    return outputFullTriplet(paramInt1 - i * 1000, paramArrayOfbyte, outputFullTriplet(i - j * 1000, paramArrayOfbyte, paramInt2));
  }
  
  public static int outputInt(int paramInt1, char[] paramArrayOfchar, int paramInt2) {
    boolean bool;
    int j = paramInt1;
    int i = paramInt2;
    if (paramInt1 < 0) {
      if (paramInt1 == Integer.MIN_VALUE)
        return outputLong(paramInt1, paramArrayOfchar, paramInt2); 
      paramArrayOfchar[paramInt2] = '-';
      j = -paramInt1;
      i = paramInt2 + 1;
    } 
    if (j < MILLION) {
      if (j < 1000) {
        if (j < 10) {
          paramInt1 = i + 1;
          paramArrayOfchar[i] = (char)(j + 48);
          return paramInt1;
        } 
        return outputLeadingTriplet(j, paramArrayOfchar, i);
      } 
      paramInt1 = j / 1000;
      return outputFullTriplet(j - paramInt1 * 1000, paramArrayOfchar, outputLeadingTriplet(paramInt1, paramArrayOfchar, i));
    } 
    if (j >= BILLION) {
      bool = true;
    } else {
      bool = false;
    } 
    paramInt1 = j;
    paramInt2 = i;
    if (bool) {
      paramInt1 = j - BILLION;
      if (paramInt1 >= BILLION) {
        paramInt1 -= BILLION;
        paramArrayOfchar[i] = '2';
        paramInt2 = i + 1;
      } else {
        paramArrayOfchar[i] = '1';
        paramInt2 = i + 1;
      } 
    } 
    i = paramInt1 / 1000;
    j = i / 1000;
    if (bool) {
      paramInt2 = outputFullTriplet(j, paramArrayOfchar, paramInt2);
    } else {
      paramInt2 = outputLeadingTriplet(j, paramArrayOfchar, paramInt2);
    } 
    return outputFullTriplet(paramInt1 - i * 1000, paramArrayOfchar, outputFullTriplet(i - j * 1000, paramArrayOfchar, paramInt2));
  }
  
  private static int outputLeadingTriplet(int paramInt1, byte[] paramArrayOfbyte, int paramInt2) {
    paramInt1 <<= 2;
    char[] arrayOfChar = LEADING_TRIPLETS;
    int i = paramInt1 + 1;
    char c = arrayOfChar[paramInt1];
    paramInt1 = paramInt2;
    if (c != '\000') {
      paramArrayOfbyte[paramInt2] = (byte)c;
      paramInt1 = paramInt2 + 1;
    } 
    c = LEADING_TRIPLETS[i];
    paramInt2 = paramInt1;
    if (c != '\000') {
      paramArrayOfbyte[paramInt1] = (byte)c;
      paramInt2 = paramInt1 + 1;
    } 
    paramArrayOfbyte[paramInt2] = (byte)LEADING_TRIPLETS[i + 1];
    return paramInt2 + 1;
  }
  
  private static int outputLeadingTriplet(int paramInt1, char[] paramArrayOfchar, int paramInt2) {
    paramInt1 <<= 2;
    char[] arrayOfChar = LEADING_TRIPLETS;
    int i = paramInt1 + 1;
    char c = arrayOfChar[paramInt1];
    paramInt1 = paramInt2;
    if (c != '\000') {
      paramArrayOfchar[paramInt2] = c;
      paramInt1 = paramInt2 + 1;
    } 
    c = LEADING_TRIPLETS[i];
    paramInt2 = paramInt1;
    if (c != '\000') {
      paramArrayOfchar[paramInt1] = c;
      paramInt2 = paramInt1 + 1;
    } 
    paramArrayOfchar[paramInt2] = LEADING_TRIPLETS[i + 1];
    return paramInt2 + 1;
  }
  
  public static int outputLong(long paramLong, byte[] paramArrayOfbyte, int paramInt) {
    int i;
    long l;
    if (paramLong < 0L) {
      if (paramLong > MIN_INT_AS_LONG)
        return outputInt((int)paramLong, paramArrayOfbyte, paramInt); 
      if (paramLong == Long.MIN_VALUE) {
        int i1 = SMALLEST_LONG.length();
        byte b = 0;
        int n = paramInt;
        while (true) {
          paramInt = n;
          if (b < i1) {
            paramArrayOfbyte[n] = (byte)SMALLEST_LONG.charAt(b);
            b++;
            n++;
            continue;
          } 
          return paramInt;
        } 
      } 
      paramArrayOfbyte[paramInt] = 45;
      l = -paramLong;
      i = paramInt + 1;
    } else {
      l = paramLong;
      i = paramInt;
      if (paramLong <= MAX_INT_AS_LONG)
        return outputInt((int)paramLong, paramArrayOfbyte, paramInt); 
    } 
    int j = i + calcLongStrLength(l);
    paramInt = j;
    while (l > MAX_INT_AS_LONG) {
      paramInt -= 3;
      paramLong = l / THOUSAND_L;
      outputFullTriplet((int)(l - THOUSAND_L * paramLong), paramArrayOfbyte, paramInt);
      l = paramLong;
    } 
    int m = (int)l;
    int k = paramInt;
    for (paramInt = m; paramInt >= 1000; paramInt = m) {
      k -= 3;
      m = paramInt / 1000;
      outputFullTriplet(paramInt - m * 1000, paramArrayOfbyte, k);
    } 
    outputLeadingTriplet(paramInt, paramArrayOfbyte, i);
    return j;
  }
  
  public static int outputLong(long paramLong, char[] paramArrayOfchar, int paramInt) {
    int i;
    long l;
    if (paramLong < 0L) {
      if (paramLong > MIN_INT_AS_LONG)
        return outputInt((int)paramLong, paramArrayOfchar, paramInt); 
      if (paramLong == Long.MIN_VALUE) {
        int n = SMALLEST_LONG.length();
        SMALLEST_LONG.getChars(0, n, paramArrayOfchar, paramInt);
        paramInt += n;
        return paramInt;
      } 
      paramArrayOfchar[paramInt] = '-';
      l = -paramLong;
      i = paramInt + 1;
    } else {
      l = paramLong;
      i = paramInt;
      if (paramLong <= MAX_INT_AS_LONG)
        return outputInt((int)paramLong, paramArrayOfchar, paramInt); 
    } 
    int j = i + calcLongStrLength(l);
    paramInt = j;
    while (l > MAX_INT_AS_LONG) {
      paramInt -= 3;
      paramLong = l / THOUSAND_L;
      outputFullTriplet((int)(l - THOUSAND_L * paramLong), paramArrayOfchar, paramInt);
      l = paramLong;
    } 
    int k = (int)l;
    int m = paramInt;
    paramInt = k;
    while (paramInt >= 1000) {
      k = m - 3;
      m = paramInt / 1000;
      outputFullTriplet(paramInt - m * 1000, paramArrayOfchar, k);
      paramInt = m;
      m = k;
    } 
    outputLeadingTriplet(paramInt, paramArrayOfchar, i);
    return j;
  }
  
  public static String toString(double paramDouble) {
    return Double.toString(paramDouble);
  }
  
  public static String toString(int paramInt) {
    if (paramInt < sSmallIntStrs.length) {
      if (paramInt >= 0)
        return sSmallIntStrs[paramInt]; 
      int i = -paramInt - 1;
      if (i < sSmallIntStrs2.length)
        return sSmallIntStrs2[i]; 
    } 
    return Integer.toString(paramInt);
  }
  
  public static String toString(long paramLong) {
    return (paramLong <= 2147483647L && paramLong >= -2147483648L) ? toString((int)paramLong) : Long.toString(paramLong);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\io\NumberOutput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */