package com.flurry.org.apache.avro.io.parsing;

import java.util.HashMap;

public class Symbol$Root extends Symbol {
  private Symbol$Root(Symbol... paramVarArgs) {
    super(Symbol$Kind.ROOT, makeProduction(paramVarArgs));
    this.production[0] = this;
  }
  
  private static Symbol[] makeProduction(Symbol[] paramArrayOfSymbol) {
    Symbol[] arrayOfSymbol = new Symbol[flattenedSize(paramArrayOfSymbol, 0) + 1];
    flatten(paramArrayOfSymbol, 0, arrayOfSymbol, 1, new HashMap<Object, Object>(), new HashMap<Object, Object>());
    return arrayOfSymbol;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\parsing\Symbol$Root.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */