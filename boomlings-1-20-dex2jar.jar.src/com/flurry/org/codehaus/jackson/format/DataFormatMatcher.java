package com.flurry.org.codehaus.jackson.format;

import com.flurry.org.codehaus.jackson.JsonFactory;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.io.MergedStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class DataFormatMatcher {
  protected final byte[] _bufferedData;
  
  protected final int _bufferedLength;
  
  protected final JsonFactory _match;
  
  protected final MatchStrength _matchStrength;
  
  protected final InputStream _originalStream;
  
  protected DataFormatMatcher(InputStream paramInputStream, byte[] paramArrayOfbyte, int paramInt, JsonFactory paramJsonFactory, MatchStrength paramMatchStrength) {
    this._originalStream = paramInputStream;
    this._bufferedData = paramArrayOfbyte;
    this._bufferedLength = paramInt;
    this._match = paramJsonFactory;
    this._matchStrength = paramMatchStrength;
  }
  
  public JsonParser createParserWithMatch() {
    return (this._match == null) ? null : ((this._originalStream == null) ? this._match.createJsonParser(this._bufferedData, 0, this._bufferedLength) : this._match.createJsonParser(getDataStream()));
  }
  
  public InputStream getDataStream() {
    return (InputStream)((this._originalStream == null) ? new ByteArrayInputStream(this._bufferedData, 0, this._bufferedLength) : new MergedStream(null, this._originalStream, this._bufferedData, 0, this._bufferedLength));
  }
  
  public JsonFactory getMatch() {
    return this._match;
  }
  
  public MatchStrength getMatchStrength() {
    return (this._matchStrength == null) ? MatchStrength.INCONCLUSIVE : this._matchStrength;
  }
  
  public String getMatchedFormatName() {
    return this._match.getFormatName();
  }
  
  public boolean hasMatch() {
    return (this._match != null);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\format\DataFormatMatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */