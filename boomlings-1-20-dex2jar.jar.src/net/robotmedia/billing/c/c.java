package net.robotmedia.billing.c;

public class c {
  static final boolean a;
  
  private static final byte[] b = new byte[] { 
      65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
      75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
      85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
      101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
      111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
      121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
      56, 57, 43, 47 };
  
  private static final byte[] c = new byte[] { 
      65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
      75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
      85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
      101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
      111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
      121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
      56, 57, 45, 95 };
  
  private static final byte[] d;
  
  private static final byte[] e;
  
  static {
    byte[] arrayOfByte = new byte[128];
    arrayOfByte[0] = -9;
    arrayOfByte[1] = -9;
    arrayOfByte[2] = -9;
    arrayOfByte[3] = -9;
    arrayOfByte[4] = -9;
    arrayOfByte[5] = -9;
    arrayOfByte[6] = -9;
    arrayOfByte[7] = -9;
    arrayOfByte[8] = -9;
    arrayOfByte[9] = -5;
    arrayOfByte[10] = -5;
    arrayOfByte[11] = -9;
    arrayOfByte[12] = -9;
    arrayOfByte[13] = -5;
    arrayOfByte[14] = -9;
    arrayOfByte[15] = -9;
    arrayOfByte[16] = -9;
    arrayOfByte[17] = -9;
    arrayOfByte[18] = -9;
    arrayOfByte[19] = -9;
    arrayOfByte[20] = -9;
    arrayOfByte[21] = -9;
    arrayOfByte[22] = -9;
    arrayOfByte[23] = -9;
    arrayOfByte[24] = -9;
    arrayOfByte[25] = -9;
    arrayOfByte[26] = -9;
    arrayOfByte[27] = -9;
    arrayOfByte[28] = -9;
    arrayOfByte[29] = -9;
    arrayOfByte[30] = -9;
    arrayOfByte[31] = -9;
    arrayOfByte[32] = -5;
    arrayOfByte[33] = -9;
    arrayOfByte[34] = -9;
    arrayOfByte[35] = -9;
    arrayOfByte[36] = -9;
    arrayOfByte[37] = -9;
    arrayOfByte[38] = -9;
    arrayOfByte[39] = -9;
    arrayOfByte[40] = -9;
    arrayOfByte[41] = -9;
    arrayOfByte[42] = -9;
    arrayOfByte[43] = 62;
    arrayOfByte[44] = -9;
    arrayOfByte[45] = -9;
    arrayOfByte[46] = -9;
    arrayOfByte[47] = 63;
    arrayOfByte[48] = 52;
    arrayOfByte[49] = 53;
    arrayOfByte[50] = 54;
    arrayOfByte[51] = 55;
    arrayOfByte[52] = 56;
    arrayOfByte[53] = 57;
    arrayOfByte[54] = 58;
    arrayOfByte[55] = 59;
    arrayOfByte[56] = 60;
    arrayOfByte[57] = 61;
    arrayOfByte[58] = -9;
    arrayOfByte[59] = -9;
    arrayOfByte[60] = -9;
    arrayOfByte[61] = -1;
    arrayOfByte[62] = -9;
    arrayOfByte[63] = -9;
    arrayOfByte[64] = -9;
    arrayOfByte[66] = 1;
    arrayOfByte[67] = 2;
    arrayOfByte[68] = 3;
    arrayOfByte[69] = 4;
    arrayOfByte[70] = 5;
    arrayOfByte[71] = 6;
    arrayOfByte[72] = 7;
    arrayOfByte[73] = 8;
    arrayOfByte[74] = 9;
    arrayOfByte[75] = 10;
    arrayOfByte[76] = 11;
    arrayOfByte[77] = 12;
    arrayOfByte[78] = 13;
    arrayOfByte[79] = 14;
    arrayOfByte[80] = 15;
    arrayOfByte[81] = 16;
    arrayOfByte[82] = 17;
    arrayOfByte[83] = 18;
    arrayOfByte[84] = 19;
    arrayOfByte[85] = 20;
    arrayOfByte[86] = 21;
    arrayOfByte[87] = 22;
    arrayOfByte[88] = 23;
    arrayOfByte[89] = 24;
    arrayOfByte[90] = 25;
    arrayOfByte[91] = -9;
    arrayOfByte[92] = -9;
    arrayOfByte[93] = -9;
    arrayOfByte[94] = -9;
    arrayOfByte[95] = -9;
    arrayOfByte[96] = -9;
    arrayOfByte[97] = 26;
    arrayOfByte[98] = 27;
    arrayOfByte[99] = 28;
    arrayOfByte[100] = 29;
    arrayOfByte[101] = 30;
    arrayOfByte[102] = 31;
    arrayOfByte[103] = 32;
    arrayOfByte[104] = 33;
    arrayOfByte[105] = 34;
    arrayOfByte[106] = 35;
    arrayOfByte[107] = 36;
    arrayOfByte[108] = 37;
    arrayOfByte[109] = 38;
    arrayOfByte[110] = 39;
    arrayOfByte[111] = 40;
    arrayOfByte[112] = 41;
    arrayOfByte[113] = 42;
    arrayOfByte[114] = 43;
    arrayOfByte[115] = 44;
    arrayOfByte[116] = 45;
    arrayOfByte[117] = 46;
    arrayOfByte[118] = 47;
    arrayOfByte[119] = 48;
    arrayOfByte[120] = 49;
    arrayOfByte[121] = 50;
    arrayOfByte[122] = 51;
    arrayOfByte[123] = -9;
    arrayOfByte[124] = -9;
    arrayOfByte[125] = -9;
    arrayOfByte[126] = -9;
    arrayOfByte[127] = -9;
    d = arrayOfByte;
    arrayOfByte = new byte[128];
    arrayOfByte[0] = -9;
    arrayOfByte[1] = -9;
    arrayOfByte[2] = -9;
    arrayOfByte[3] = -9;
    arrayOfByte[4] = -9;
    arrayOfByte[5] = -9;
    arrayOfByte[6] = -9;
    arrayOfByte[7] = -9;
    arrayOfByte[8] = -9;
    arrayOfByte[9] = -5;
    arrayOfByte[10] = -5;
    arrayOfByte[11] = -9;
    arrayOfByte[12] = -9;
    arrayOfByte[13] = -5;
    arrayOfByte[14] = -9;
    arrayOfByte[15] = -9;
    arrayOfByte[16] = -9;
    arrayOfByte[17] = -9;
    arrayOfByte[18] = -9;
    arrayOfByte[19] = -9;
    arrayOfByte[20] = -9;
    arrayOfByte[21] = -9;
    arrayOfByte[22] = -9;
    arrayOfByte[23] = -9;
    arrayOfByte[24] = -9;
    arrayOfByte[25] = -9;
    arrayOfByte[26] = -9;
    arrayOfByte[27] = -9;
    arrayOfByte[28] = -9;
    arrayOfByte[29] = -9;
    arrayOfByte[30] = -9;
    arrayOfByte[31] = -9;
    arrayOfByte[32] = -5;
    arrayOfByte[33] = -9;
    arrayOfByte[34] = -9;
    arrayOfByte[35] = -9;
    arrayOfByte[36] = -9;
    arrayOfByte[37] = -9;
    arrayOfByte[38] = -9;
    arrayOfByte[39] = -9;
    arrayOfByte[40] = -9;
    arrayOfByte[41] = -9;
    arrayOfByte[42] = -9;
    arrayOfByte[43] = -9;
    arrayOfByte[44] = -9;
    arrayOfByte[45] = 62;
    arrayOfByte[46] = -9;
    arrayOfByte[47] = -9;
    arrayOfByte[48] = 52;
    arrayOfByte[49] = 53;
    arrayOfByte[50] = 54;
    arrayOfByte[51] = 55;
    arrayOfByte[52] = 56;
    arrayOfByte[53] = 57;
    arrayOfByte[54] = 58;
    arrayOfByte[55] = 59;
    arrayOfByte[56] = 60;
    arrayOfByte[57] = 61;
    arrayOfByte[58] = -9;
    arrayOfByte[59] = -9;
    arrayOfByte[60] = -9;
    arrayOfByte[61] = -1;
    arrayOfByte[62] = -9;
    arrayOfByte[63] = -9;
    arrayOfByte[64] = -9;
    arrayOfByte[66] = 1;
    arrayOfByte[67] = 2;
    arrayOfByte[68] = 3;
    arrayOfByte[69] = 4;
    arrayOfByte[70] = 5;
    arrayOfByte[71] = 6;
    arrayOfByte[72] = 7;
    arrayOfByte[73] = 8;
    arrayOfByte[74] = 9;
    arrayOfByte[75] = 10;
    arrayOfByte[76] = 11;
    arrayOfByte[77] = 12;
    arrayOfByte[78] = 13;
    arrayOfByte[79] = 14;
    arrayOfByte[80] = 15;
    arrayOfByte[81] = 16;
    arrayOfByte[82] = 17;
    arrayOfByte[83] = 18;
    arrayOfByte[84] = 19;
    arrayOfByte[85] = 20;
    arrayOfByte[86] = 21;
    arrayOfByte[87] = 22;
    arrayOfByte[88] = 23;
    arrayOfByte[89] = 24;
    arrayOfByte[90] = 25;
    arrayOfByte[91] = -9;
    arrayOfByte[92] = -9;
    arrayOfByte[93] = -9;
    arrayOfByte[94] = -9;
    arrayOfByte[95] = 63;
    arrayOfByte[96] = -9;
    arrayOfByte[97] = 26;
    arrayOfByte[98] = 27;
    arrayOfByte[99] = 28;
    arrayOfByte[100] = 29;
    arrayOfByte[101] = 30;
    arrayOfByte[102] = 31;
    arrayOfByte[103] = 32;
    arrayOfByte[104] = 33;
    arrayOfByte[105] = 34;
    arrayOfByte[106] = 35;
    arrayOfByte[107] = 36;
    arrayOfByte[108] = 37;
    arrayOfByte[109] = 38;
    arrayOfByte[110] = 39;
    arrayOfByte[111] = 40;
    arrayOfByte[112] = 41;
    arrayOfByte[113] = 42;
    arrayOfByte[114] = 43;
    arrayOfByte[115] = 44;
    arrayOfByte[116] = 45;
    arrayOfByte[117] = 46;
    arrayOfByte[118] = 47;
    arrayOfByte[119] = 48;
    arrayOfByte[120] = 49;
    arrayOfByte[121] = 50;
    arrayOfByte[122] = 51;
    arrayOfByte[123] = -9;
    arrayOfByte[124] = -9;
    arrayOfByte[125] = -9;
    arrayOfByte[126] = -9;
    arrayOfByte[127] = -9;
    e = arrayOfByte;
  }
  
  private static int a(byte[] paramArrayOfbyte1, int paramInt1, byte[] paramArrayOfbyte2, int paramInt2, byte[] paramArrayOfbyte3) {
    if (paramArrayOfbyte1[paramInt1 + 2] == 61) {
      paramArrayOfbyte2[paramInt2] = (byte)((paramArrayOfbyte3[paramArrayOfbyte1[paramInt1]] << 24 >>> 6 | paramArrayOfbyte3[paramArrayOfbyte1[paramInt1 + 1]] << 24 >>> 12) >>> 16);
      return 1;
    } 
    if (paramArrayOfbyte1[paramInt1 + 3] == 61) {
      paramInt1 = paramArrayOfbyte3[paramArrayOfbyte1[paramInt1]] << 24 >>> 6 | paramArrayOfbyte3[paramArrayOfbyte1[paramInt1 + 1]] << 24 >>> 12 | paramArrayOfbyte3[paramArrayOfbyte1[paramInt1 + 2]] << 24 >>> 18;
      paramArrayOfbyte2[paramInt2] = (byte)(paramInt1 >>> 16);
      paramArrayOfbyte2[paramInt2 + 1] = (byte)(paramInt1 >>> 8);
      return 2;
    } 
    paramInt1 = paramArrayOfbyte3[paramArrayOfbyte1[paramInt1]] << 24 >>> 6 | paramArrayOfbyte3[paramArrayOfbyte1[paramInt1 + 1]] << 24 >>> 12 | paramArrayOfbyte3[paramArrayOfbyte1[paramInt1 + 2]] << 24 >>> 18 | paramArrayOfbyte3[paramArrayOfbyte1[paramInt1 + 3]] << 24 >>> 24;
    paramArrayOfbyte2[paramInt2] = (byte)(paramInt1 >> 16);
    paramArrayOfbyte2[paramInt2 + 1] = (byte)(paramInt1 >> 8);
    paramArrayOfbyte2[paramInt2 + 2] = (byte)paramInt1;
    return 3;
  }
  
  public static String a(byte[] paramArrayOfbyte) {
    return a(paramArrayOfbyte, 0, paramArrayOfbyte.length, b, true);
  }
  
  public static String a(byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2, boolean paramBoolean) {
    paramArrayOfbyte1 = a(paramArrayOfbyte1, paramInt1, paramInt2, paramArrayOfbyte2, 2147483647);
    paramInt1 = paramArrayOfbyte1.length;
    while (true) {
      if (!paramBoolean && paramInt1 > 0 && paramArrayOfbyte1[paramInt1 - 1] == 61) {
        paramInt1--;
        continue;
      } 
      return new String(paramArrayOfbyte1, 0, paramInt1);
    } 
  }
  
  public static byte[] a(String paramString) {
    byte[] arrayOfByte = paramString.getBytes();
    return a(arrayOfByte, 0, arrayOfByte.length);
  }
  
  public static byte[] a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return a(paramArrayOfbyte, paramInt1, paramInt2, d);
  }
  
  public static byte[] a(byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2) {
    byte[] arrayOfByte1 = new byte[paramInt2 * 3 / 4 + 2];
    byte[] arrayOfByte2 = new byte[4];
    int k = 0;
    int j = 0;
    int i = 0;
    while (true) {
      if (k < paramInt2) {
        byte b1 = (byte)(paramArrayOfbyte1[k + paramInt1] & Byte.MAX_VALUE);
        byte b2 = paramArrayOfbyte2[b1];
        if (b2 >= -5) {
          if (b2 >= -1) {
            if (b1 == 61) {
              int m = paramInt2 - k;
              paramInt1 = (byte)(paramArrayOfbyte1[paramInt2 - 1 + paramInt1] & Byte.MAX_VALUE);
              if (!j || j == 1)
                throw new d("invalid padding byte '=' at byte offset " + k); 
              if ((j == 3 && m > 2) || (j == 4 && m > 1))
                throw new d("padding byte '=' falsely signals end of encoded value at offset " + k); 
              if (paramInt1 != 61 && paramInt1 != 10)
                throw new d("encoded value has invalid trailing byte"); 
            } else {
              int m = j + 1;
              arrayOfByte2[j] = b1;
              if (m == 4) {
                j = a(arrayOfByte2, 0, arrayOfByte1, i, paramArrayOfbyte2) + i;
                i = 0;
              } else {
                j = i;
                i = m;
              } 
              m = k + 1;
              k = j;
              j = i;
              i = k;
              k = m;
            } 
          } else {
            int m = j;
            j = i;
            i = m;
            m = k + 1;
            k = j;
            j = i;
            i = k;
            k = m;
          } 
        } else {
          throw new d("Bad Base64 input character at " + k + ": " + paramArrayOfbyte1[k + paramInt1] + "(decimal)");
        } 
      } 
      paramInt1 = i;
      if (j != 0) {
        if (j == 1)
          throw new d("single trailing character at offset " + (paramInt2 - 1)); 
        arrayOfByte2[j] = 61;
        paramInt1 = i + a(arrayOfByte2, 0, arrayOfByte1, i, paramArrayOfbyte2);
      } 
      paramArrayOfbyte1 = new byte[paramInt1];
      System.arraycopy(arrayOfByte1, 0, paramArrayOfbyte1, 0, paramInt1);
      return paramArrayOfbyte1;
    } 
  }
  
  public static byte[] a(byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2, int paramInt3) {
    int i = (paramInt2 + 2) / 3 * 4;
    byte[] arrayOfByte = new byte[i + i / paramInt3];
    int j = 0;
    i = 0;
    byte b = 0;
    while (true) {
      if (b >= paramInt2 - 2) {
        int n = i;
        if (b < paramInt2) {
          a(paramArrayOfbyte1, b + paramInt1, paramInt2 - b, arrayOfByte, i, paramArrayOfbyte2);
          paramInt1 = i;
          if (j + 4 == paramInt3) {
            arrayOfByte[i + 4] = 10;
            paramInt1 = i + 1;
          } 
          n = paramInt1 + 4;
        } 
        if (!a && n != arrayOfByte.length)
          throw new AssertionError(); 
        break;
      } 
      int k = paramArrayOfbyte1[b + paramInt1] << 24 >>> 8 | paramArrayOfbyte1[b + 1 + paramInt1] << 24 >>> 16 | paramArrayOfbyte1[b + 2 + paramInt1] << 24 >>> 24;
      arrayOfByte[i] = paramArrayOfbyte2[k >>> 18];
      arrayOfByte[i + 1] = paramArrayOfbyte2[k >>> 12 & 0x3F];
      arrayOfByte[i + 2] = paramArrayOfbyte2[k >>> 6 & 0x3F];
      arrayOfByte[i + 3] = paramArrayOfbyte2[k & 0x3F];
      int m = j + 4;
      j = m;
      k = i;
      if (m == paramInt3) {
        arrayOfByte[i + 4] = 10;
        k = i + 1;
        j = 0;
      } 
      b += 3;
      i = k + 4;
    } 
    return arrayOfByte;
  }
  
  private static byte[] a(byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2, int paramInt3, byte[] paramArrayOfbyte3) {
    boolean bool1;
    boolean bool2;
    int i = 0;
    if (paramInt2 > 0) {
      bool1 = paramArrayOfbyte1[paramInt1] << 24 >>> 8;
    } else {
      bool1 = false;
    } 
    if (paramInt2 > 1) {
      bool2 = paramArrayOfbyte1[paramInt1 + 1] << 24 >>> 16;
    } else {
      bool2 = false;
    } 
    if (paramInt2 > 2)
      i = paramArrayOfbyte1[paramInt1 + 2] << 24 >>> 24; 
    paramInt1 = i | bool2 | bool1;
    switch (paramInt2) {
      default:
        return paramArrayOfbyte2;
      case 3:
        paramArrayOfbyte2[paramInt3] = paramArrayOfbyte3[paramInt1 >>> 18];
        paramArrayOfbyte2[paramInt3 + 1] = paramArrayOfbyte3[paramInt1 >>> 12 & 0x3F];
        paramArrayOfbyte2[paramInt3 + 2] = paramArrayOfbyte3[paramInt1 >>> 6 & 0x3F];
        paramArrayOfbyte2[paramInt3 + 3] = paramArrayOfbyte3[paramInt1 & 0x3F];
      case 2:
        paramArrayOfbyte2[paramInt3] = paramArrayOfbyte3[paramInt1 >>> 18];
        paramArrayOfbyte2[paramInt3 + 1] = paramArrayOfbyte3[paramInt1 >>> 12 & 0x3F];
        paramArrayOfbyte2[paramInt3 + 2] = paramArrayOfbyte3[paramInt1 >>> 6 & 0x3F];
        paramArrayOfbyte2[paramInt3 + 3] = 61;
      case 1:
        break;
    } 
    paramArrayOfbyte2[paramInt3] = paramArrayOfbyte3[paramInt1 >>> 18];
    paramArrayOfbyte2[paramInt3 + 1] = paramArrayOfbyte3[paramInt1 >>> 12 & 0x3F];
    paramArrayOfbyte2[paramInt3 + 2] = 61;
    paramArrayOfbyte2[paramInt3 + 3] = 61;
  }
  
  static {
    boolean bool;
    if (!c.class.desiredAssertionStatus()) {
      bool = true;
    } else {
      bool = false;
    } 
    a = bool;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\net\robotmedia\billing\c\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */