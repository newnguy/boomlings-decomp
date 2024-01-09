package com.flurry.org.codehaus.jackson.io;

import java.io.OutputStream;
import java.io.Writer;

public abstract class OutputDecorator {
  public abstract OutputStream decorate(IOContext paramIOContext, OutputStream paramOutputStream);
  
  public abstract Writer decorate(IOContext paramIOContext, Writer paramWriter);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\io\OutputDecorator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */