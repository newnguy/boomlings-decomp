package com.flurry.org.codehaus.jackson;

public class Version implements Comparable {
  private static final Version UNKNOWN_VERSION = new Version(0, 0, 0, null);
  
  protected final int _majorVersion;
  
  protected final int _minorVersion;
  
  protected final int _patchLevel;
  
  protected final String _snapshotInfo;
  
  public Version(int paramInt1, int paramInt2, int paramInt3, String paramString) {
    this._majorVersion = paramInt1;
    this._minorVersion = paramInt2;
    this._patchLevel = paramInt3;
    this._snapshotInfo = paramString;
  }
  
  public static Version unknownVersion() {
    return UNKNOWN_VERSION;
  }
  
  public int compareTo(Version paramVersion) {
    int j = this._majorVersion - paramVersion._majorVersion;
    int i = j;
    if (j == 0) {
      j = this._minorVersion - paramVersion._minorVersion;
      i = j;
      if (j == 0)
        i = this._patchLevel - paramVersion._patchLevel; 
    } 
    return i;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (paramObject == null)
        return false; 
      if (paramObject.getClass() != getClass())
        return false; 
      paramObject = paramObject;
      if (((Version)paramObject)._majorVersion != this._majorVersion || ((Version)paramObject)._minorVersion != this._minorVersion || ((Version)paramObject)._patchLevel != this._patchLevel)
        bool = false; 
    } 
    return bool;
  }
  
  public int getMajorVersion() {
    return this._majorVersion;
  }
  
  public int getMinorVersion() {
    return this._minorVersion;
  }
  
  public int getPatchLevel() {
    return this._patchLevel;
  }
  
  public int hashCode() {
    return this._majorVersion + this._minorVersion + this._patchLevel;
  }
  
  public boolean isSnapshot() {
    return (this._snapshotInfo != null && this._snapshotInfo.length() > 0);
  }
  
  public boolean isUknownVersion() {
    return (this == UNKNOWN_VERSION);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this._majorVersion).append('.');
    stringBuilder.append(this._minorVersion).append('.');
    stringBuilder.append(this._patchLevel);
    if (isSnapshot())
      stringBuilder.append('-').append(this._snapshotInfo); 
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\Version.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */