package com.flurry.org.apache.avro.io.parsing;

import java.util.List;

public class Symbol$EnumLabelsAction extends Symbol$IntCheckAction {
  public final List symbols;
  
  public Symbol$EnumLabelsAction(List paramList) {
    super(paramList.size());
    this.symbols = paramList;
  }
  
  public int findLabel(String paramString) {
    if (paramString != null) {
      byte b1 = 0;
      while (b1 < this.symbols.size()) {
        if (!paramString.equals(this.symbols.get(b1))) {
          b1++;
          continue;
        } 
        return b1;
      } 
    } 
    byte b = -1;
    while (b < this.symbols.size()) {
      if (!paramString.equals(this.symbols.get(b))) {
        b++;
        continue;
      } 
      return b;
    } 
  }
  
  public String getLabel(int paramInt) {
    return this.symbols.get(paramInt);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\parsing\Symbol$EnumLabelsAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */