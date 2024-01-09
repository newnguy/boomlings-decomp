package com.flurry.org.codehaus.jackson.map.util;

import com.flurry.org.codehaus.jackson.io.NumberInput;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class StdDateFormat extends DateFormat {
  protected static final String[] ALL_FORMATS = new String[] { "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy-MM-dd" };
  
  protected static final DateFormat DATE_FORMAT_ISO8601;
  
  protected static final DateFormat DATE_FORMAT_ISO8601_Z;
  
  protected static final DateFormat DATE_FORMAT_PLAIN;
  
  protected static final DateFormat DATE_FORMAT_RFC1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
  
  protected static final String DATE_FORMAT_STR_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
  
  protected static final String DATE_FORMAT_STR_ISO8601_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
  
  protected static final String DATE_FORMAT_STR_PLAIN = "yyyy-MM-dd";
  
  protected static final String DATE_FORMAT_STR_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
  
  public static final StdDateFormat instance;
  
  protected transient DateFormat _formatISO8601;
  
  protected transient DateFormat _formatISO8601_z;
  
  protected transient DateFormat _formatPlain;
  
  protected transient DateFormat _formatRFC1123;
  
  static {
    DATE_FORMAT_RFC1123.setTimeZone(timeZone);
    DATE_FORMAT_ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    DATE_FORMAT_ISO8601.setTimeZone(timeZone);
    DATE_FORMAT_ISO8601_Z = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    DATE_FORMAT_ISO8601_Z.setTimeZone(timeZone);
    DATE_FORMAT_PLAIN = new SimpleDateFormat("yyyy-MM-dd");
    DATE_FORMAT_PLAIN.setTimeZone(timeZone);
    instance = new StdDateFormat();
  }
  
  public static DateFormat getBlueprintISO8601Format() {
    return DATE_FORMAT_ISO8601;
  }
  
  public static DateFormat getBlueprintRFC1123Format() {
    return DATE_FORMAT_RFC1123;
  }
  
  public static DateFormat getISO8601Format(TimeZone paramTimeZone) {
    DateFormat dateFormat = (DateFormat)DATE_FORMAT_ISO8601.clone();
    dateFormat.setTimeZone(paramTimeZone);
    return dateFormat;
  }
  
  public static DateFormat getRFC1123Format(TimeZone paramTimeZone) {
    DateFormat dateFormat = (DateFormat)DATE_FORMAT_RFC1123.clone();
    dateFormat.setTimeZone(paramTimeZone);
    return dateFormat;
  }
  
  private static final boolean hasTimeZone(String paramString) {
    boolean bool = true;
    int i = paramString.length();
    if (i >= 6) {
      char c = paramString.charAt(i - 6);
      boolean bool1 = bool;
      if (c != '+') {
        if (c == '-')
          return bool; 
      } else {
        return bool1;
      } 
      c = paramString.charAt(i - 5);
      bool1 = bool;
      if (c != '+') {
        bool1 = bool;
        if (c != '-') {
          i = paramString.charAt(i - 3);
          bool1 = bool;
          if (i != 43) {
            bool1 = bool;
            if (i != 45)
              return false; 
          } 
        } 
      } 
      return bool1;
    } 
    return false;
  }
  
  public StdDateFormat clone() {
    return new StdDateFormat();
  }
  
  public StringBuffer format(Date paramDate, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition) {
    if (this._formatISO8601 == null)
      this._formatISO8601 = (DateFormat)DATE_FORMAT_ISO8601.clone(); 
    return this._formatISO8601.format(paramDate, paramStringBuffer, paramFieldPosition);
  }
  
  protected boolean looksLikeISO8601(String paramString) {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramString.length() >= 5) {
      bool1 = bool2;
      if (Character.isDigit(paramString.charAt(0))) {
        bool1 = bool2;
        if (Character.isDigit(paramString.charAt(3))) {
          bool1 = bool2;
          if (paramString.charAt(4) == '-')
            bool1 = true; 
        } 
      } 
    } 
    return bool1;
  }
  
  public Date parse(String paramString) {
    String str = paramString.trim();
    ParsePosition parsePosition = new ParsePosition(0);
    Date date = parse(str, parsePosition);
    if (date != null)
      return date; 
    StringBuilder stringBuilder = new StringBuilder();
    for (String str1 : ALL_FORMATS) {
      if (stringBuilder.length() > 0) {
        stringBuilder.append("\", \"");
      } else {
        stringBuilder.append('"');
      } 
      stringBuilder.append(str1);
    } 
    stringBuilder.append('"');
    throw new ParseException(String.format("Can not parse date \"%s\": not compatible with any of standard forms (%s)", new Object[] { str, stringBuilder.toString() }), parsePosition.getErrorIndex());
  }
  
  public Date parse(String paramString, ParsePosition paramParsePosition) {
    int j;
    if (looksLikeISO8601(paramString))
      return parseAsISO8601(paramString, paramParsePosition); 
    int i = paramString.length();
    while (true) {
      j = i - 1;
      if (j >= 0) {
        char c = paramString.charAt(j);
        if (c >= '0') {
          i = j;
          if (c > '9')
            break; 
          continue;
        } 
      } 
      break;
    } 
    return (j < 0 && NumberInput.inLongRange(paramString, false)) ? new Date(Long.parseLong(paramString)) : parseAsRFC1123(paramString, paramParsePosition);
  }
  
  protected Date parseAsISO8601(String paramString, ParsePosition paramParsePosition) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual length : ()I
    //   4: istore #5
    //   6: aload_1
    //   7: iload #5
    //   9: iconst_1
    //   10: isub
    //   11: invokevirtual charAt : (I)C
    //   14: istore_3
    //   15: iload #5
    //   17: bipush #10
    //   19: if_icmpgt -> 76
    //   22: iload_3
    //   23: invokestatic isDigit : (C)Z
    //   26: ifeq -> 76
    //   29: aload_0
    //   30: getfield _formatPlain : Ljava/text/DateFormat;
    //   33: astore #8
    //   35: aload #8
    //   37: astore #6
    //   39: aload_1
    //   40: astore #7
    //   42: aload #8
    //   44: ifnonnull -> 67
    //   47: getstatic com/flurry/org/codehaus/jackson/map/util/StdDateFormat.DATE_FORMAT_PLAIN : Ljava/text/DateFormat;
    //   50: invokevirtual clone : ()Ljava/lang/Object;
    //   53: checkcast java/text/DateFormat
    //   56: astore #6
    //   58: aload_0
    //   59: aload #6
    //   61: putfield _formatPlain : Ljava/text/DateFormat;
    //   64: aload_1
    //   65: astore #7
    //   67: aload #6
    //   69: aload #7
    //   71: aload_2
    //   72: invokevirtual parse : (Ljava/lang/String;Ljava/text/ParsePosition;)Ljava/util/Date;
    //   75: areturn
    //   76: iload_3
    //   77: bipush #90
    //   79: if_icmpne -> 167
    //   82: aload_0
    //   83: getfield _formatISO8601_z : Ljava/text/DateFormat;
    //   86: astore #6
    //   88: aload #6
    //   90: astore #8
    //   92: aload #6
    //   94: ifnonnull -> 114
    //   97: getstatic com/flurry/org/codehaus/jackson/map/util/StdDateFormat.DATE_FORMAT_ISO8601_Z : Ljava/text/DateFormat;
    //   100: invokevirtual clone : ()Ljava/lang/Object;
    //   103: checkcast java/text/DateFormat
    //   106: astore #8
    //   108: aload_0
    //   109: aload #8
    //   111: putfield _formatISO8601_z : Ljava/text/DateFormat;
    //   114: aload #8
    //   116: astore #6
    //   118: aload_1
    //   119: astore #7
    //   121: aload_1
    //   122: iload #5
    //   124: iconst_4
    //   125: isub
    //   126: invokevirtual charAt : (I)C
    //   129: bipush #58
    //   131: if_icmpne -> 67
    //   134: new java/lang/StringBuilder
    //   137: dup
    //   138: aload_1
    //   139: invokespecial <init> : (Ljava/lang/String;)V
    //   142: astore_1
    //   143: aload_1
    //   144: iload #5
    //   146: iconst_1
    //   147: isub
    //   148: ldc '.000'
    //   150: invokevirtual insert : (ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   153: pop
    //   154: aload_1
    //   155: invokevirtual toString : ()Ljava/lang/String;
    //   158: astore #7
    //   160: aload #8
    //   162: astore #6
    //   164: goto -> 67
    //   167: aload_1
    //   168: invokestatic hasTimeZone : (Ljava/lang/String;)Z
    //   171: ifeq -> 351
    //   174: aload_1
    //   175: iload #5
    //   177: iconst_3
    //   178: isub
    //   179: invokevirtual charAt : (I)C
    //   182: istore #4
    //   184: iload #4
    //   186: bipush #58
    //   188: if_icmpne -> 310
    //   191: new java/lang/StringBuilder
    //   194: dup
    //   195: aload_1
    //   196: invokespecial <init> : (Ljava/lang/String;)V
    //   199: astore_1
    //   200: aload_1
    //   201: iload #5
    //   203: iconst_3
    //   204: isub
    //   205: iload #5
    //   207: iconst_2
    //   208: isub
    //   209: invokevirtual delete : (II)Ljava/lang/StringBuilder;
    //   212: pop
    //   213: aload_1
    //   214: invokevirtual toString : ()Ljava/lang/String;
    //   217: astore #6
    //   219: aload #6
    //   221: invokevirtual length : ()I
    //   224: istore #4
    //   226: aload #6
    //   228: astore_1
    //   229: aload #6
    //   231: iload #4
    //   233: bipush #9
    //   235: isub
    //   236: invokevirtual charAt : (I)C
    //   239: invokestatic isDigit : (C)Z
    //   242: ifeq -> 271
    //   245: new java/lang/StringBuilder
    //   248: dup
    //   249: aload #6
    //   251: invokespecial <init> : (Ljava/lang/String;)V
    //   254: astore_1
    //   255: aload_1
    //   256: iload #4
    //   258: iconst_5
    //   259: isub
    //   260: ldc '.000'
    //   262: invokevirtual insert : (ILjava/lang/String;)Ljava/lang/StringBuilder;
    //   265: pop
    //   266: aload_1
    //   267: invokevirtual toString : ()Ljava/lang/String;
    //   270: astore_1
    //   271: aload_0
    //   272: getfield _formatISO8601 : Ljava/text/DateFormat;
    //   275: astore #6
    //   277: aload_1
    //   278: astore #7
    //   280: aload_0
    //   281: getfield _formatISO8601 : Ljava/text/DateFormat;
    //   284: ifnonnull -> 67
    //   287: getstatic com/flurry/org/codehaus/jackson/map/util/StdDateFormat.DATE_FORMAT_ISO8601 : Ljava/text/DateFormat;
    //   290: invokevirtual clone : ()Ljava/lang/Object;
    //   293: checkcast java/text/DateFormat
    //   296: astore #6
    //   298: aload_0
    //   299: aload #6
    //   301: putfield _formatISO8601 : Ljava/text/DateFormat;
    //   304: aload_1
    //   305: astore #7
    //   307: goto -> 67
    //   310: iload #4
    //   312: bipush #43
    //   314: if_icmpeq -> 327
    //   317: aload_1
    //   318: astore #6
    //   320: iload #4
    //   322: bipush #45
    //   324: if_icmpne -> 219
    //   327: new java/lang/StringBuilder
    //   330: dup
    //   331: invokespecial <init> : ()V
    //   334: aload_1
    //   335: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   338: ldc '00'
    //   340: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: invokevirtual toString : ()Ljava/lang/String;
    //   346: astore #6
    //   348: goto -> 219
    //   351: new java/lang/StringBuilder
    //   354: dup
    //   355: aload_1
    //   356: invokespecial <init> : (Ljava/lang/String;)V
    //   359: astore #6
    //   361: iload #5
    //   363: aload_1
    //   364: bipush #84
    //   366: invokevirtual lastIndexOf : (I)I
    //   369: isub
    //   370: iconst_1
    //   371: isub
    //   372: bipush #8
    //   374: if_icmpgt -> 385
    //   377: aload #6
    //   379: ldc '.000'
    //   381: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   384: pop
    //   385: aload #6
    //   387: bipush #90
    //   389: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   392: pop
    //   393: aload #6
    //   395: invokevirtual toString : ()Ljava/lang/String;
    //   398: astore_1
    //   399: aload_0
    //   400: getfield _formatISO8601_z : Ljava/text/DateFormat;
    //   403: astore #8
    //   405: aload #8
    //   407: astore #6
    //   409: aload_1
    //   410: astore #7
    //   412: aload #8
    //   414: ifnonnull -> 67
    //   417: getstatic com/flurry/org/codehaus/jackson/map/util/StdDateFormat.DATE_FORMAT_ISO8601_Z : Ljava/text/DateFormat;
    //   420: invokevirtual clone : ()Ljava/lang/Object;
    //   423: checkcast java/text/DateFormat
    //   426: astore #6
    //   428: aload_0
    //   429: aload #6
    //   431: putfield _formatISO8601_z : Ljava/text/DateFormat;
    //   434: aload_1
    //   435: astore #7
    //   437: goto -> 67
  }
  
  protected Date parseAsRFC1123(String paramString, ParsePosition paramParsePosition) {
    if (this._formatRFC1123 == null)
      this._formatRFC1123 = (DateFormat)DATE_FORMAT_RFC1123.clone(); 
    return this._formatRFC1123.parse(paramString, paramParsePosition);
  }
  
  static {
    TimeZone timeZone = TimeZone.getTimeZone("GMT");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\StdDateFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */