package com.flurry.org.apache.avro.io.parsing;

import java.util.Map;

public class Symbol$UnionAdjustAction extends Symbol$ImplicitAction {
  public final int rindex;
  
  public final Symbol symToParse;
  
  public Symbol$UnionAdjustAction(int paramInt, Symbol paramSymbol) {
    super((Symbol$1)null);
    this.rindex = paramInt;
    this.symToParse = paramSymbol;
  }
  
  public Symbol$UnionAdjustAction flatten(Map paramMap1, Map paramMap2) {
    return new Symbol$UnionAdjustAction(this.rindex, this.symToParse.flatten(paramMap1, paramMap2));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\parsing\Symbol$UnionAdjustAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */