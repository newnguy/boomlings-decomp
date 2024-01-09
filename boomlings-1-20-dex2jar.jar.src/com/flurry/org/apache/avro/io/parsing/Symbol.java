package com.flurry.org.apache.avro.io.parsing;

import java.util.List;
import java.util.Map;

public abstract class Symbol {
  public static final Symbol ARRAY_END;
  
  public static final Symbol ARRAY_START;
  
  public static final Symbol BOOLEAN;
  
  public static final Symbol BYTES;
  
  public static final Symbol DEFAULT_END_ACTION;
  
  public static final Symbol DOUBLE;
  
  public static final Symbol ENUM;
  
  public static final Symbol FIELD_ACTION;
  
  public static final Symbol FIELD_END;
  
  public static final Symbol FIXED;
  
  public static final Symbol FLOAT;
  
  public static final Symbol INT;
  
  public static final Symbol ITEM_END;
  
  public static final Symbol LONG;
  
  public static final Symbol MAP_END;
  
  public static final Symbol MAP_KEY_MARKER;
  
  public static final Symbol MAP_START;
  
  public static final Symbol NULL = new Symbol$Terminal("null");
  
  public static final Symbol RECORD_END;
  
  public static final Symbol RECORD_START;
  
  public static final Symbol STRING;
  
  public static final Symbol UNION;
  
  public static final Symbol UNION_END;
  
  public final Symbol$Kind kind;
  
  public final Symbol[] production;
  
  static {
    BOOLEAN = new Symbol$Terminal("boolean");
    INT = new Symbol$Terminal("int");
    LONG = new Symbol$Terminal("long");
    FLOAT = new Symbol$Terminal("float");
    DOUBLE = new Symbol$Terminal("double");
    STRING = new Symbol$Terminal("string");
    BYTES = new Symbol$Terminal("bytes");
    FIXED = new Symbol$Terminal("fixed");
    ENUM = new Symbol$Terminal("enum");
    UNION = new Symbol$Terminal("union");
    ARRAY_START = new Symbol$Terminal("array-start");
    ARRAY_END = new Symbol$Terminal("array-end");
    MAP_START = new Symbol$Terminal("map-start");
    MAP_END = new Symbol$Terminal("map-end");
    ITEM_END = new Symbol$Terminal("item-end");
    FIELD_ACTION = new Symbol$Terminal("field-action");
    RECORD_START = new Symbol$ImplicitAction(false, null);
    RECORD_END = new Symbol$ImplicitAction(true, null);
    UNION_END = new Symbol$ImplicitAction(true, null);
    FIELD_END = new Symbol$ImplicitAction(true, null);
    DEFAULT_END_ACTION = new Symbol$ImplicitAction(true, null);
    MAP_KEY_MARKER = new Symbol$Terminal("map-key-marker");
  }
  
  protected Symbol(Symbol$Kind paramSymbol$Kind) {
    this(paramSymbol$Kind, null);
  }
  
  protected Symbol(Symbol$Kind paramSymbol$Kind, Symbol[] paramArrayOfSymbol) {
    this.production = paramArrayOfSymbol;
    this.kind = paramSymbol$Kind;
  }
  
  static Symbol alt(Symbol[] paramArrayOfSymbol, String[] paramArrayOfString) {
    return new Symbol$Alternative(paramArrayOfSymbol, paramArrayOfString, null);
  }
  
  static Symbol error(String paramString) {
    return new Symbol$ErrorAction(paramString, null);
  }
  
  static void flatten(Symbol[] paramArrayOfSymbol1, int paramInt1, Symbol[] paramArrayOfSymbol2, int paramInt2, Map paramMap1, Map paramMap2) {
    int i = paramInt2;
    paramInt2 = paramInt1;
    while (paramInt2 < paramArrayOfSymbol1.length) {
      List<Symbol$Fixup> list;
      Symbol symbol = paramArrayOfSymbol1[paramInt2].flatten(paramMap1, paramMap2);
      if (symbol instanceof Symbol$Sequence) {
        Symbol[] arrayOfSymbol = symbol.production;
        list = (List)paramMap2.get(symbol);
        if (list == null) {
          System.arraycopy(arrayOfSymbol, 0, paramArrayOfSymbol2, i, arrayOfSymbol.length);
        } else {
          list.add(new Symbol$Fixup(paramArrayOfSymbol2, i));
        } 
        paramInt1 = i + arrayOfSymbol.length;
      } else {
        paramArrayOfSymbol2[i] = (Symbol)list;
        paramInt1 = i + 1;
      } 
      paramInt2++;
      i = paramInt1;
    } 
  }
  
  protected static int flattenedSize(Symbol[] paramArrayOfSymbol, int paramInt) {
    boolean bool = false;
    int i = paramInt;
    paramInt = bool;
    while (i < paramArrayOfSymbol.length) {
      if (paramArrayOfSymbol[i] instanceof Symbol$Sequence) {
        paramInt = ((Symbol$Sequence)paramArrayOfSymbol[i]).flattenedSize() + paramInt;
      } else {
        paramInt++;
      } 
      i++;
    } 
    return paramInt;
  }
  
  static Symbol repeat(Symbol paramSymbol, Symbol... paramVarArgs) {
    return new Symbol$Repeater(paramSymbol, paramVarArgs, null);
  }
  
  static Symbol resolve(Symbol paramSymbol1, Symbol paramSymbol2) {
    return new Symbol$ResolvingAction(paramSymbol1, paramSymbol2, null);
  }
  
  static Symbol root(Symbol... paramVarArgs) {
    return new Symbol$Root(paramVarArgs, null);
  }
  
  static Symbol seq(Symbol... paramVarArgs) {
    return new Symbol$Sequence(paramVarArgs, null);
  }
  
  public Symbol flatten(Map paramMap1, Map paramMap2) {
    return this;
  }
  
  public int flattenedSize() {
    return 1;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\parsing\Symbol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */