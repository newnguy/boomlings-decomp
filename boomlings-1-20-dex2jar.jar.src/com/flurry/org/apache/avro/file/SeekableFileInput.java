package com.flurry.org.apache.avro.file;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;

public class SeekableFileInput extends FileInputStream implements SeekableInput {
  public SeekableFileInput(File paramFile) {
    super(paramFile);
  }
  
  public SeekableFileInput(FileDescriptor paramFileDescriptor) {
    super(paramFileDescriptor);
  }
  
  public long length() {
    return getChannel().size();
  }
  
  public void seek(long paramLong) {
    getChannel().position(paramLong);
  }
  
  public long tell() {
    return getChannel().position();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\file\SeekableFileInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */