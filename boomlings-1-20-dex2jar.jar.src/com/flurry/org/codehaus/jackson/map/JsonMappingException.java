package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.JsonLocation;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonProcessingException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class JsonMappingException extends JsonProcessingException {
  static final int MAX_REFS_TO_LIST = 1000;
  
  private static final long serialVersionUID = 1L;
  
  protected LinkedList _path;
  
  public JsonMappingException(String paramString) {
    super(paramString);
  }
  
  public JsonMappingException(String paramString, JsonLocation paramJsonLocation) {
    super(paramString, paramJsonLocation);
  }
  
  public JsonMappingException(String paramString, JsonLocation paramJsonLocation, Throwable paramThrowable) {
    super(paramString, paramJsonLocation, paramThrowable);
  }
  
  public JsonMappingException(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
  
  public static JsonMappingException from(JsonParser paramJsonParser, String paramString) {
    return new JsonMappingException(paramString, paramJsonParser.getTokenLocation());
  }
  
  public static JsonMappingException from(JsonParser paramJsonParser, String paramString, Throwable paramThrowable) {
    return new JsonMappingException(paramString, paramJsonParser.getTokenLocation(), paramThrowable);
  }
  
  public static JsonMappingException wrapWithPath(Throwable paramThrowable, JsonMappingException$Reference paramJsonMappingException$Reference) {
    // Byte code:
    //   0: aload_0
    //   1: instanceof com/flurry/org/codehaus/jackson/map/JsonMappingException
    //   4: ifeq -> 19
    //   7: aload_0
    //   8: checkcast com/flurry/org/codehaus/jackson/map/JsonMappingException
    //   11: astore_0
    //   12: aload_0
    //   13: aload_1
    //   14: invokevirtual prependPath : (Lcom/flurry/org/codehaus/jackson/map/JsonMappingException$Reference;)V
    //   17: aload_0
    //   18: areturn
    //   19: aload_0
    //   20: invokevirtual getMessage : ()Ljava/lang/String;
    //   23: astore_3
    //   24: aload_3
    //   25: ifnull -> 37
    //   28: aload_3
    //   29: astore_2
    //   30: aload_3
    //   31: invokevirtual length : ()I
    //   34: ifne -> 68
    //   37: new java/lang/StringBuilder
    //   40: dup
    //   41: invokespecial <init> : ()V
    //   44: ldc '(was '
    //   46: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: aload_0
    //   50: invokevirtual getClass : ()Ljava/lang/Class;
    //   53: invokevirtual getName : ()Ljava/lang/String;
    //   56: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: ldc ')'
    //   61: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: invokevirtual toString : ()Ljava/lang/String;
    //   67: astore_2
    //   68: new com/flurry/org/codehaus/jackson/map/JsonMappingException
    //   71: dup
    //   72: aload_2
    //   73: aconst_null
    //   74: aload_0
    //   75: invokespecial <init> : (Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/JsonLocation;Ljava/lang/Throwable;)V
    //   78: astore_0
    //   79: goto -> 12
  }
  
  public static JsonMappingException wrapWithPath(Throwable paramThrowable, Object paramObject, int paramInt) {
    return wrapWithPath(paramThrowable, new JsonMappingException$Reference(paramObject, paramInt));
  }
  
  public static JsonMappingException wrapWithPath(Throwable paramThrowable, Object paramObject, String paramString) {
    return wrapWithPath(paramThrowable, new JsonMappingException$Reference(paramObject, paramString));
  }
  
  protected void _appendPathDesc(StringBuilder paramStringBuilder) {
    Iterator<JsonMappingException$Reference> iterator = this._path.iterator();
    while (iterator.hasNext()) {
      paramStringBuilder.append(((JsonMappingException$Reference)iterator.next()).toString());
      if (iterator.hasNext())
        paramStringBuilder.append("->"); 
    } 
  }
  
  public String getMessage() {
    String str = super.getMessage();
    if (this._path != null) {
      StringBuilder stringBuilder;
      if (str == null) {
        stringBuilder = new StringBuilder();
      } else {
        stringBuilder = new StringBuilder((String)stringBuilder);
      } 
      stringBuilder.append(" (through reference chain: ");
      _appendPathDesc(stringBuilder);
      stringBuilder.append(')');
      str = stringBuilder.toString();
    } 
    return str;
  }
  
  public List getPath() {
    return (this._path == null) ? Collections.emptyList() : Collections.unmodifiableList(this._path);
  }
  
  public void prependPath(JsonMappingException$Reference paramJsonMappingException$Reference) {
    if (this._path == null)
      this._path = new LinkedList(); 
    if (this._path.size() < 1000)
      this._path.addFirst(paramJsonMappingException$Reference); 
  }
  
  public void prependPath(Object paramObject, int paramInt) {
    prependPath(new JsonMappingException$Reference(paramObject, paramInt));
  }
  
  public void prependPath(Object paramObject, String paramString) {
    prependPath(new JsonMappingException$Reference(paramObject, paramString));
  }
  
  public String toString() {
    return getClass().getName() + ": " + getMessage();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\JsonMappingException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */