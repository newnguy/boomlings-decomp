package com.flurry.org.apache.avro.io.parsing;

import java.util.Map;

public class Symbol$ResolvingAction extends Symbol$ImplicitAction {
  public final Symbol reader;
  
  public final Symbol writer;
  
  private Symbol$ResolvingAction(Symbol paramSymbol1, Symbol paramSymbol2) {
    super((Symbol$1)null);
    this.writer = paramSymbol1;
    this.reader = paramSymbol2;
  }
  
  public Symbol$ResolvingAction flatten(Map paramMap1, Map paramMap2) {
    return new Symbol$ResolvingAction(this.writer.flatten(paramMap1, paramMap2), this.reader.flatten(paramMap1, paramMap2));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\parsing\Symbol$ResolvingAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */