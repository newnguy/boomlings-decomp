package com.flurry.org.codehaus.jackson.format;

import com.flurry.org.codehaus.jackson.JsonFactory;
import java.io.InputStream;
import java.util.Collection;

public class DataFormatDetector {
  public static final int DEFAULT_MAX_INPUT_LOOKAHEAD = 64;
  
  protected final JsonFactory[] _detectors;
  
  protected final int _maxInputLookahead;
  
  protected final MatchStrength _minimalMatch;
  
  protected final MatchStrength _optimalMatch;
  
  public DataFormatDetector(Collection paramCollection) {
    this((JsonFactory[])paramCollection.toArray((Object[])new JsonFactory[paramCollection.size()]));
  }
  
  public DataFormatDetector(JsonFactory... paramVarArgs) {
    this(paramVarArgs, MatchStrength.SOLID_MATCH, MatchStrength.WEAK_MATCH, 64);
  }
  
  private DataFormatDetector(JsonFactory[] paramArrayOfJsonFactory, MatchStrength paramMatchStrength1, MatchStrength paramMatchStrength2, int paramInt) {
    this._detectors = paramArrayOfJsonFactory;
    this._optimalMatch = paramMatchStrength1;
    this._minimalMatch = paramMatchStrength2;
    this._maxInputLookahead = paramInt;
  }
  
  private DataFormatMatcher _findFormat(InputAccessor$Std paramInputAccessor$Std) {
    JsonFactory[] arrayOfJsonFactory = this._detectors;
    int i = arrayOfJsonFactory.length;
    byte b = 0;
    JsonFactory jsonFactory = null;
    MatchStrength matchStrength = null;
    while (true) {
      if (b < i) {
        JsonFactory jsonFactory1 = arrayOfJsonFactory[b];
        paramInputAccessor$Std.reset();
        MatchStrength matchStrength2 = jsonFactory1.hasFormat(paramInputAccessor$Std);
        if (matchStrength2 != null && matchStrength2.ordinal() >= this._minimalMatch.ordinal() && (jsonFactory == null || matchStrength.ordinal() < matchStrength2.ordinal())) {
          if (matchStrength2.ordinal() >= this._optimalMatch.ordinal()) {
            jsonFactory = jsonFactory1;
            return paramInputAccessor$Std.createMatcher(jsonFactory, matchStrength2);
          } 
          matchStrength = matchStrength2;
          jsonFactory = jsonFactory1;
        } 
        b++;
        continue;
      } 
      MatchStrength matchStrength1 = matchStrength;
      return paramInputAccessor$Std.createMatcher(jsonFactory, matchStrength1);
    } 
  }
  
  public DataFormatMatcher findFormat(InputStream paramInputStream) {
    return _findFormat(new InputAccessor$Std(paramInputStream, new byte[this._maxInputLookahead]));
  }
  
  public DataFormatMatcher findFormat(byte[] paramArrayOfbyte) {
    return _findFormat(new InputAccessor$Std(paramArrayOfbyte));
  }
  
  public DataFormatDetector withMaxInputLookahead(int paramInt) {
    return (paramInt == this._maxInputLookahead) ? this : new DataFormatDetector(this._detectors, this._optimalMatch, this._minimalMatch, paramInt);
  }
  
  public DataFormatDetector withMinimalMatch(MatchStrength paramMatchStrength) {
    return (paramMatchStrength == this._minimalMatch) ? this : new DataFormatDetector(this._detectors, this._optimalMatch, paramMatchStrength, this._maxInputLookahead);
  }
  
  public DataFormatDetector withOptimalMatch(MatchStrength paramMatchStrength) {
    return (paramMatchStrength == this._optimalMatch) ? this : new DataFormatDetector(this._detectors, paramMatchStrength, this._minimalMatch, this._maxInputLookahead);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\format\DataFormatDetector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */