package com.flurry.org.codehaus.jackson.io;

import java.io.InputStream;
import java.io.Reader;

public abstract class InputDecorator {
  public abstract InputStream decorate(IOContext paramIOContext, InputStream paramInputStream);
  
  public abstract InputStream decorate(IOContext paramIOContext, byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  public abstract Reader decorate(IOContext paramIOContext, Reader paramReader);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\io\InputDecorator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */