package com.flurry.org.codehaus.jackson.util;

import com.flurry.org.codehaus.jackson.Version;
import java.util.regex.Pattern;

public class VersionUtil {
  public static final String VERSION_FILE = "VERSION.txt";
  
  private static final Pattern VERSION_SEPARATOR = Pattern.compile("[-_./;:]");
  
  public static Version parseVersion(String paramString) {
    Version version;
    int i = 0;
    String str2 = null;
    String str1 = null;
    if (paramString == null)
      return (Version)str1; 
    String str3 = paramString.trim();
    paramString = str1;
    if (str3.length() != 0) {
      String[] arrayOfString = VERSION_SEPARATOR.split(str3);
      paramString = str1;
      if (arrayOfString.length >= 2) {
        int j = parseVersionPart(arrayOfString[0]);
        int k = parseVersionPart(arrayOfString[1]);
        if (arrayOfString.length > 2)
          i = parseVersionPart(arrayOfString[2]); 
        paramString = str2;
        if (arrayOfString.length > 3)
          paramString = arrayOfString[3]; 
        version = new Version(j, k, i, paramString);
      } 
    } 
    return version;
  }
  
  protected static int parseVersionPart(String paramString) {
    byte b = 0;
    paramString = paramString.toString();
    int j = paramString.length();
    int i = 0;
    while (true) {
      if (b < j) {
        char c = paramString.charAt(b);
        if (c <= '9' && c >= '0') {
          i = i * 10 + c - 48;
          b++;
          continue;
        } 
      } 
      return i;
    } 
  }
  
  public static Version versionFor(Class paramClass) {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_3
    //   4: aload_2
    //   5: astore_1
    //   6: aload_0
    //   7: ldc 'VERSION.txt'
    //   9: invokevirtual getResourceAsStream : (Ljava/lang/String;)Ljava/io/InputStream;
    //   12: astore #4
    //   14: aload_3
    //   15: astore_0
    //   16: aload #4
    //   18: ifnull -> 55
    //   21: new java/io/BufferedReader
    //   24: astore_1
    //   25: new java/io/InputStreamReader
    //   28: astore_0
    //   29: aload_0
    //   30: aload #4
    //   32: ldc 'UTF-8'
    //   34: invokespecial <init> : (Ljava/io/InputStream;Ljava/lang/String;)V
    //   37: aload_1
    //   38: aload_0
    //   39: invokespecial <init> : (Ljava/io/Reader;)V
    //   42: aload_1
    //   43: invokevirtual readLine : ()Ljava/lang/String;
    //   46: invokestatic parseVersion : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/Version;
    //   49: astore_0
    //   50: aload #4
    //   52: invokevirtual close : ()V
    //   55: aload_0
    //   56: astore_1
    //   57: aload_0
    //   58: ifnonnull -> 65
    //   61: invokestatic unknownVersion : ()Lcom/flurry/org/codehaus/jackson/Version;
    //   64: astore_1
    //   65: aload_1
    //   66: areturn
    //   67: astore_2
    //   68: aload_0
    //   69: astore_1
    //   70: new java/lang/RuntimeException
    //   73: astore_3
    //   74: aload_0
    //   75: astore_1
    //   76: aload_3
    //   77: aload_2
    //   78: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   81: aload_0
    //   82: astore_1
    //   83: aload_3
    //   84: athrow
    //   85: astore_0
    //   86: aload_1
    //   87: astore_0
    //   88: goto -> 55
    //   91: astore_0
    //   92: aload #4
    //   94: invokevirtual close : ()V
    //   97: aload_2
    //   98: astore_1
    //   99: aload_0
    //   100: athrow
    //   101: astore_3
    //   102: aload_2
    //   103: astore_1
    //   104: new java/lang/RuntimeException
    //   107: astore_0
    //   108: aload_2
    //   109: astore_1
    //   110: aload_0
    //   111: aload_3
    //   112: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   115: aload_2
    //   116: astore_1
    //   117: aload_0
    //   118: athrow
    // Exception table:
    //   from	to	target	type
    //   6	14	85	java/io/IOException
    //   21	50	91	finally
    //   50	55	67	java/io/IOException
    //   70	74	85	java/io/IOException
    //   76	81	85	java/io/IOException
    //   83	85	85	java/io/IOException
    //   92	97	101	java/io/IOException
    //   99	101	85	java/io/IOException
    //   104	108	85	java/io/IOException
    //   110	115	85	java/io/IOException
    //   117	119	85	java/io/IOException
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackso\\util\VersionUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */