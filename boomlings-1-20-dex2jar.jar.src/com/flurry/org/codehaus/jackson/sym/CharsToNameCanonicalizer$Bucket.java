package com.flurry.org.codehaus.jackson.sym;

final class CharsToNameCanonicalizer$Bucket {
  private final String _symbol;
  
  private final CharsToNameCanonicalizer$Bucket mNext;
  
  public CharsToNameCanonicalizer$Bucket(String paramString, CharsToNameCanonicalizer$Bucket paramCharsToNameCanonicalizer$Bucket) {
    this._symbol = paramString;
    this.mNext = paramCharsToNameCanonicalizer$Bucket;
  }
  
  public String find(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    String str = this._symbol;
    for (CharsToNameCanonicalizer$Bucket charsToNameCanonicalizer$Bucket = this.mNext;; charsToNameCanonicalizer$Bucket = charsToNameCanonicalizer$Bucket.getNext()) {
      if (str.length() == paramInt2) {
        int i = 0;
        while (true) {
          if (str.charAt(i) == paramArrayOfchar[paramInt1 + i]) {
            int j = i + 1;
            i = j;
            if (j >= paramInt2) {
              i = j;
            } else {
              continue;
            } 
          } 
          if (i == paramInt2)
            return str; 
          break;
        } 
      } 
      if (charsToNameCanonicalizer$Bucket == null) {
        str = null;
        continue;
      } 
      str = charsToNameCanonicalizer$Bucket.getSymbol();
    } 
  }
  
  public CharsToNameCanonicalizer$Bucket getNext() {
    return this.mNext;
  }
  
  public String getSymbol() {
    return this._symbol;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\sym\CharsToNameCanonicalizer$Bucket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */