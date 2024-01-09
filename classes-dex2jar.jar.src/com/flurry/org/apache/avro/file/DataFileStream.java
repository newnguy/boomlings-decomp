package com.flurry.org.apache.avro.file;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.BinaryDecoder;
import com.flurry.org.apache.avro.io.DatumReader;
import com.flurry.org.apache.avro.io.Decoder;
import com.flurry.org.apache.avro.io.DecoderFactory;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class DataFileStream implements Closeable, Iterable, Iterator {
  private boolean availableBlock = false;
  
  private DataFileStream$DataBlock block = null;
  
  ByteBuffer blockBuffer;
  
  long blockCount;
  
  long blockRemaining;
  
  private long blockSize;
  
  private Codec codec;
  
  BinaryDecoder datumIn = null;
  
  private DataFileStream$Header header;
  
  private DatumReader reader;
  
  byte[] syncBuffer = new byte[16];
  
  BinaryDecoder vin;
  
  protected DataFileStream(DatumReader paramDatumReader) {
    this.reader = paramDatumReader;
  }
  
  public DataFileStream(InputStream paramInputStream, DatumReader paramDatumReader) {
    this.reader = paramDatumReader;
    initialize(paramInputStream);
  }
  
  protected void blockFinished() {}
  
  public void close() {
    this.vin.inputStream().close();
  }
  
  public long getBlockCount() {
    return this.blockCount;
  }
  
  public DataFileStream$Header getHeader() {
    return this.header;
  }
  
  public byte[] getMeta(String paramString) {
    return (byte[])this.header.meta.get(paramString);
  }
  
  public List getMetaKeys() {
    return DataFileStream$Header.access$100(this.header);
  }
  
  public long getMetaLong(String paramString) {
    return Long.parseLong(getMetaString(paramString));
  }
  
  public String getMetaString(String paramString) {
    byte[] arrayOfByte = getMeta(paramString);
    if (arrayOfByte == null)
      return null; 
    try {
      return new String(arrayOfByte, "UTF-8");
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      throw new RuntimeException(unsupportedEncodingException);
    } 
  }
  
  public Schema getSchema() {
    return this.header.schema;
  }
  
  public boolean hasNext() {
    boolean bool = false;
    try {
      if (this.blockRemaining == 0L) {
        if (this.datumIn != null && !this.datumIn.isEnd()) {
          IOException iOException = new IOException();
          this("Block read partially, the data may be corrupt");
          throw iOException;
        } 
        if (hasNextBlock()) {
          this.block = nextRawBlock(this.block);
          this.block.decompressUsing(this.codec);
          this.blockBuffer = this.block.getAsByteBuffer();
          this.datumIn = DecoderFactory.get().binaryDecoder(this.blockBuffer.array(), this.blockBuffer.arrayOffset() + this.blockBuffer.position(), this.blockBuffer.remaining(), this.datumIn);
        } 
      } 
      long l = this.blockRemaining;
      if (l != 0L)
        bool = true; 
      return bool;
    } catch (EOFException eOFException) {
      return bool;
    } catch (IOException iOException) {
      throw new AvroRuntimeException(iOException);
    } 
  }
  
  boolean hasNextBlock() {
    boolean bool = true;
    try {
      if (!this.availableBlock) {
        if (this.vin.isEnd())
          return false; 
        this.blockRemaining = this.vin.readLong();
        this.blockSize = this.vin.readLong();
        if (this.blockSize > 2147483647L || this.blockSize < 0L) {
          IOException iOException = new IOException();
          StringBuilder stringBuilder = new StringBuilder();
          this();
          this(stringBuilder.append("Block size invalid or too large for this implementation: ").append(this.blockSize).toString());
          throw iOException;
        } 
        this.blockCount = this.blockRemaining;
        this.availableBlock = true;
      } 
      return bool;
    } catch (EOFException eOFException) {
      return false;
    } catch (IOException iOException) {
      throw new AvroRuntimeException(iOException);
    } 
  }
  
  void initialize(InputStream paramInputStream) {
    this.header = new DataFileStream$Header(null);
    this.vin = DecoderFactory.get().binaryDecoder(paramInputStream, this.vin);
    byte[] arrayOfByte = new byte[DataFileConstants.MAGIC.length];
    try {
      this.vin.readFixed(arrayOfByte);
      if (!Arrays.equals(DataFileConstants.MAGIC, arrayOfByte))
        throw new IOException("Not a data file."); 
    } catch (IOException iOException) {
      throw new IOException("Not a data file.");
    } 
    long l = this.vin.readMapStart();
    if (l > 0L) {
      long l1;
      do {
        for (l1 = 0L; l1 < l; l1++) {
          String str = this.vin.readString(null).toString();
          ByteBuffer byteBuffer = this.vin.readBytes(null);
          arrayOfByte = new byte[byteBuffer.remaining()];
          byteBuffer.get(arrayOfByte);
          this.header.meta.put(str, arrayOfByte);
          DataFileStream$Header.access$100(this.header).add(str);
        } 
        l1 = this.vin.mapNext();
        l = l1;
      } while (l1 != 0L);
    } 
    this.vin.readFixed(this.header.sync);
    DataFileStream$Header.access$102(this.header, Collections.unmodifiableList(DataFileStream$Header.access$100(this.header)));
    this.header.schema = Schema.parse(getMetaString("avro.schema"), false);
    this.codec = resolveCodec();
    this.reader.setSchema(this.header.schema);
  }
  
  void initialize(InputStream paramInputStream, DataFileStream$Header paramDataFileStream$Header) {
    this.header = paramDataFileStream$Header;
    this.codec = resolveCodec();
    this.reader.setSchema(paramDataFileStream$Header.schema);
  }
  
  public Iterator iterator() {
    return this;
  }
  
  public Object next() {
    try {
      return next(null);
    } catch (IOException iOException) {
      throw new AvroRuntimeException(iOException);
    } 
  }
  
  public Object next(Object paramObject) {
    if (!hasNext())
      throw new NoSuchElementException(); 
    paramObject = this.reader.read(paramObject, (Decoder)this.datumIn);
    long l = this.blockRemaining - 1L;
    this.blockRemaining = l;
    if (0L == l)
      blockFinished(); 
    return paramObject;
  }
  
  public ByteBuffer nextBlock() {
    if (!hasNext())
      throw new NoSuchElementException(); 
    if (this.blockRemaining != this.blockCount)
      throw new IllegalStateException("Not at block start."); 
    this.blockRemaining = 0L;
    this.datumIn = null;
    return this.blockBuffer;
  }
  
  DataFileStream$DataBlock nextRawBlock(DataFileStream$DataBlock paramDataFileStream$DataBlock) {
    if (!hasNextBlock())
      throw new NoSuchElementException(); 
    if (paramDataFileStream$DataBlock == null || (DataFileStream$DataBlock.access$200(paramDataFileStream$DataBlock)).length < (int)this.blockSize) {
      paramDataFileStream$DataBlock = new DataFileStream$DataBlock(this.blockRemaining, (int)this.blockSize, null);
    } else {
      DataFileStream$DataBlock.access$402(paramDataFileStream$DataBlock, this.blockRemaining);
      DataFileStream$DataBlock.access$502(paramDataFileStream$DataBlock, (int)this.blockSize);
    } 
    this.vin.readFixed(DataFileStream$DataBlock.access$200(paramDataFileStream$DataBlock), 0, DataFileStream$DataBlock.access$500(paramDataFileStream$DataBlock));
    this.vin.readFixed(this.syncBuffer);
    if (!Arrays.equals(this.syncBuffer, this.header.sync))
      throw new IOException("Invalid sync!"); 
    this.availableBlock = false;
    return paramDataFileStream$DataBlock;
  }
  
  public void remove() {
    throw new UnsupportedOperationException();
  }
  
  Codec resolveCodec() {
    String str = getMetaString("avro.codec");
    return (str != null) ? CodecFactory.fromString(str).createInstance() : CodecFactory.nullCodec().createInstance();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\file\DataFileStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */