package com.flurry.org.codehaus.jackson;

import com.flurry.org.codehaus.jackson.annotate.JsonCreator;
import com.flurry.org.codehaus.jackson.annotate.JsonProperty;
import java.io.Serializable;

public class JsonLocation implements Serializable {
  public static final JsonLocation NA = new JsonLocation("N/A", -1L, -1L, -1, -1);
  
  private static final long serialVersionUID = 1L;
  
  final int _columnNr;
  
  final int _lineNr;
  
  final Object _sourceRef;
  
  final long _totalBytes;
  
  final long _totalChars;
  
  public JsonLocation(Object paramObject, long paramLong, int paramInt1, int paramInt2) {
    this(paramObject, -1L, paramLong, paramInt1, paramInt2);
  }
  
  @JsonCreator
  public JsonLocation(@JsonProperty("sourceRef") Object paramObject, @JsonProperty("byteOffset") long paramLong1, @JsonProperty("charOffset") long paramLong2, @JsonProperty("lineNr") int paramInt1, @JsonProperty("columnNr") int paramInt2) {
    this._sourceRef = paramObject;
    this._totalBytes = paramLong1;
    this._totalChars = paramLong2;
    this._lineNr = paramInt1;
    this._columnNr = paramInt2;
  }
  
  public boolean equals(Object paramObject) {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: iconst_0
    //   3: istore #4
    //   5: aload_1
    //   6: aload_0
    //   7: if_acmpne -> 14
    //   10: iconst_1
    //   11: istore_2
    //   12: iload_2
    //   13: ireturn
    //   14: iload #4
    //   16: istore_2
    //   17: aload_1
    //   18: ifnull -> 12
    //   21: iload #4
    //   23: istore_2
    //   24: aload_1
    //   25: instanceof com/flurry/org/codehaus/jackson/JsonLocation
    //   28: ifeq -> 12
    //   31: aload_1
    //   32: checkcast com/flurry/org/codehaus/jackson/JsonLocation
    //   35: astore_1
    //   36: aload_0
    //   37: getfield _sourceRef : Ljava/lang/Object;
    //   40: ifnonnull -> 104
    //   43: iload #4
    //   45: istore_2
    //   46: aload_1
    //   47: getfield _sourceRef : Ljava/lang/Object;
    //   50: ifnonnull -> 12
    //   53: aload_0
    //   54: getfield _lineNr : I
    //   57: aload_1
    //   58: getfield _lineNr : I
    //   61: if_icmpne -> 124
    //   64: aload_0
    //   65: getfield _columnNr : I
    //   68: aload_1
    //   69: getfield _columnNr : I
    //   72: if_icmpne -> 124
    //   75: aload_0
    //   76: getfield _totalChars : J
    //   79: aload_1
    //   80: getfield _totalChars : J
    //   83: lcmp
    //   84: ifne -> 124
    //   87: aload_0
    //   88: invokevirtual getByteOffset : ()J
    //   91: aload_1
    //   92: invokevirtual getByteOffset : ()J
    //   95: lcmp
    //   96: ifne -> 124
    //   99: iload_3
    //   100: istore_2
    //   101: goto -> 12
    //   104: aload_0
    //   105: getfield _sourceRef : Ljava/lang/Object;
    //   108: aload_1
    //   109: getfield _sourceRef : Ljava/lang/Object;
    //   112: invokevirtual equals : (Ljava/lang/Object;)Z
    //   115: ifne -> 53
    //   118: iload #4
    //   120: istore_2
    //   121: goto -> 12
    //   124: iconst_0
    //   125: istore_2
    //   126: goto -> 101
  }
  
  public long getByteOffset() {
    return this._totalBytes;
  }
  
  public long getCharOffset() {
    return this._totalChars;
  }
  
  public int getColumnNr() {
    return this._columnNr;
  }
  
  public int getLineNr() {
    return this._lineNr;
  }
  
  public Object getSourceRef() {
    return this._sourceRef;
  }
  
  public int hashCode() {
    if (this._sourceRef == null) {
      boolean bool = true;
      return ((bool ^ this._lineNr) + this._columnNr ^ (int)this._totalChars) + (int)this._totalBytes;
    } 
    int i = this._sourceRef.hashCode();
    return ((i ^ this._lineNr) + this._columnNr ^ (int)this._totalChars) + (int)this._totalBytes;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(80);
    stringBuilder.append("[Source: ");
    if (this._sourceRef == null) {
      stringBuilder.append("UNKNOWN");
      stringBuilder.append("; line: ");
      stringBuilder.append(this._lineNr);
      stringBuilder.append(", column: ");
      stringBuilder.append(this._columnNr);
      stringBuilder.append(']');
      return stringBuilder.toString();
    } 
    stringBuilder.append(this._sourceRef.toString());
    stringBuilder.append("; line: ");
    stringBuilder.append(this._lineNr);
    stringBuilder.append(", column: ");
    stringBuilder.append(this._columnNr);
    stringBuilder.append(']');
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\JsonLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */