package com.flurry.android;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.zip.CRC32;

public class CrcMessageDigest extends MessageDigest {
  private CRC32 a = new CRC32();
  
  public CrcMessageDigest() {
    super("CRC");
  }
  
  protected byte[] engineDigest() {
    long l = this.a.getValue();
    return new byte[] { (byte)(int)((0xFFFFFFFFFF000000L & l) >> 24L), (byte)(int)((0xFF0000L & l) >> 16L), (byte)(int)((0xFF00L & l) >> 8L), (byte)(int)(l & 0xFFL) };
  }
  
  protected void engineReset() {
    this.a.reset();
  }
  
  protected void engineUpdate(byte paramByte) {
    this.a.update(paramByte);
  }
  
  protected void engineUpdate(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.a.update(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public int getChecksum() {
    return ByteBuffer.wrap(engineDigest()).getInt();
  }
  
  public byte[] getDigest() {
    return engineDigest();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\CrcMessageDigest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */