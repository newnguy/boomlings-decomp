package com.flurry.org.apache.avro.file;

import com.flurry.org.apache.avro.io.DatumReader;
import com.flurry.org.apache.avro.io.DecoderFactory;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class DataFileReader extends DataFileStream implements FileReader {
  private long blockStart;
  
  private DataFileReader$SeekableInputStream sin;
  
  public DataFileReader(SeekableInput paramSeekableInput, DatumReader paramDatumReader) {
    super(paramDatumReader);
    this.sin = new DataFileReader$SeekableInputStream(paramSeekableInput);
    initialize(this.sin);
    blockFinished();
  }
  
  protected DataFileReader(SeekableInput paramSeekableInput, DatumReader paramDatumReader, DataFileStream$Header paramDataFileStream$Header) {
    super(paramDatumReader);
    this.sin = new DataFileReader$SeekableInputStream(paramSeekableInput);
    initialize(this.sin, paramDataFileStream$Header);
  }
  
  public DataFileReader(File paramFile, DatumReader paramDatumReader) {
    this(new SeekableFileInput(paramFile), paramDatumReader);
  }
  
  public static DataFileReader openReader(SeekableInput paramSeekableInput, DatumReader paramDatumReader, DataFileStream$Header paramDataFileStream$Header, boolean paramBoolean) {
    DataFileReader dataFileReader = new DataFileReader(paramSeekableInput, paramDatumReader, paramDataFileStream$Header);
    if (paramBoolean) {
      dataFileReader.sync(paramSeekableInput.tell());
      return dataFileReader;
    } 
    dataFileReader.seek(paramSeekableInput.tell());
    return dataFileReader;
  }
  
  public static FileReader openReader(SeekableInput paramSeekableInput, DatumReader paramDatumReader) {
    if (paramSeekableInput.length() < DataFileConstants.MAGIC.length)
      throw new IOException("Not an Avro data file"); 
    byte[] arrayOfByte = new byte[DataFileConstants.MAGIC.length];
    paramSeekableInput.seek(0L);
    for (int i = 0; i < arrayOfByte.length; i = paramSeekableInput.read(arrayOfByte, i, arrayOfByte.length - i));
    paramSeekableInput.seek(0L);
    if (Arrays.equals(DataFileConstants.MAGIC, arrayOfByte))
      return new DataFileReader(paramSeekableInput, paramDatumReader); 
    if (Arrays.equals(DataFileReader12.MAGIC, arrayOfByte))
      return new DataFileReader12(paramSeekableInput, paramDatumReader); 
    throw new IOException("Not an Avro data file");
  }
  
  public static FileReader openReader(File paramFile, DatumReader paramDatumReader) {
    return openReader(new SeekableFileInput(paramFile), paramDatumReader);
  }
  
  protected void blockFinished() {
    this.blockStart = this.sin.tell() - this.vin.inputStream().available();
  }
  
  public boolean pastSync(long paramLong) {
    return (this.blockStart >= 16L + paramLong || this.blockStart >= this.sin.length());
  }
  
  public long previousSync() {
    return this.blockStart;
  }
  
  public void seek(long paramLong) {
    this.sin.seek(paramLong);
    this.vin = DecoderFactory.get().binaryDecoder(this.sin, this.vin);
    this.datumIn = null;
    this.blockRemaining = 0L;
    this.blockStart = paramLong;
  }
  
  public void sync(long paramLong) {
    seek(paramLong);
    if (paramLong == 0L && getMeta("avro.sync") != null) {
      initialize(this.sin);
      return;
    } 
    try {
      InputStream inputStream = this.vin.inputStream();
      this.vin.readFixed(this.syncBuffer);
      for (byte b = 0;; b++) {
        int i = 0;
        while (true) {
          if (i >= 16 || (getHeader()).sync[i] != this.syncBuffer[(b + i) % 16]) {
            if (i == 16) {
              this.blockStart = b + paramLong + 16L;
              return;
            } 
          } else {
            i++;
            continue;
          } 
          i = inputStream.read();
          this.syncBuffer[b % 16] = (byte)i;
          if (i != -1)
            break; 
          this.blockStart = this.sin.tell();
          return;
        } 
      } 
    } catch (EOFException eOFException) {}
    this.blockStart = this.sin.tell();
  }
  
  public long tell() {
    return this.sin.tell();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\file\DataFileReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */