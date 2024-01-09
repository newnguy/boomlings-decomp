package com.flurry.org.apache.avro.io.parsing;

import java.util.Map;

public class Symbol$SkipAction extends Symbol$ImplicitAction {
  public final Symbol symToSkip;
  
  public Symbol$SkipAction(Symbol paramSymbol) {
    super(true, null);
    this.symToSkip = paramSymbol;
  }
  
  public Symbol$SkipAction flatten(Map paramMap1, Map paramMap2) {
    return new Symbol$SkipAction(this.symToSkip.flatten(paramMap1, paramMap2));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\parsing\Symbol$SkipAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */