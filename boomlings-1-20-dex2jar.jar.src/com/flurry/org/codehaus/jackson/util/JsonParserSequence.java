package com.flurry.org.codehaus.jackson.util;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import java.util.ArrayList;
import java.util.List;

public class JsonParserSequence extends JsonParserDelegate {
  protected int _nextParser;
  
  protected final JsonParser[] _parsers;
  
  protected JsonParserSequence(JsonParser[] paramArrayOfJsonParser) {
    super(paramArrayOfJsonParser[0]);
    this._parsers = paramArrayOfJsonParser;
    this._nextParser = 1;
  }
  
  public static JsonParserSequence createFlattened(JsonParser paramJsonParser1, JsonParser paramJsonParser2) {
    if (!(paramJsonParser1 instanceof JsonParserSequence) && !(paramJsonParser2 instanceof JsonParserSequence))
      return new JsonParserSequence(new JsonParser[] { paramJsonParser1, paramJsonParser2 }); 
    ArrayList<JsonParser> arrayList = new ArrayList();
    if (paramJsonParser1 instanceof JsonParserSequence) {
      ((JsonParserSequence)paramJsonParser1).addFlattenedActiveParsers(arrayList);
    } else {
      arrayList.add(paramJsonParser1);
    } 
    if (paramJsonParser2 instanceof JsonParserSequence) {
      ((JsonParserSequence)paramJsonParser2).addFlattenedActiveParsers(arrayList);
    } else {
      arrayList.add(paramJsonParser2);
    } 
    return new JsonParserSequence(arrayList.<JsonParser>toArray(new JsonParser[arrayList.size()]));
  }
  
  protected void addFlattenedActiveParsers(List<JsonParser> paramList) {
    int i = this._nextParser;
    int j = this._parsers.length;
    while (--i < j) {
      JsonParser jsonParser = this._parsers[i];
      if (jsonParser instanceof JsonParserSequence) {
        ((JsonParserSequence)jsonParser).addFlattenedActiveParsers(paramList);
      } else {
        paramList.add(jsonParser);
      } 
      i++;
    } 
  }
  
  public void close() {
    do {
      this.delegate.close();
    } while (switchToNext());
  }
  
  public int containedParsersCount() {
    return this._parsers.length;
  }
  
  public JsonToken nextToken() {
    // Byte code:
    //   0: aload_0
    //   1: getfield delegate : Lcom/flurry/org/codehaus/jackson/JsonParser;
    //   4: invokevirtual nextToken : ()Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   7: astore_1
    //   8: aload_1
    //   9: ifnull -> 14
    //   12: aload_1
    //   13: areturn
    //   14: aload_0
    //   15: invokevirtual switchToNext : ()Z
    //   18: ifeq -> 36
    //   21: aload_0
    //   22: getfield delegate : Lcom/flurry/org/codehaus/jackson/JsonParser;
    //   25: invokevirtual nextToken : ()Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   28: astore_1
    //   29: aload_1
    //   30: ifnull -> 14
    //   33: goto -> 12
    //   36: aconst_null
    //   37: astore_1
    //   38: goto -> 12
  }
  
  protected boolean switchToNext() {
    if (this._nextParser >= this._parsers.length)
      return false; 
    JsonParser[] arrayOfJsonParser = this._parsers;
    int i = this._nextParser;
    this._nextParser = i + 1;
    this.delegate = arrayOfJsonParser[i];
    return true;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackso\\util\JsonParserSequence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */