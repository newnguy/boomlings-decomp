package com.flurry.org.apache.avro.file;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.generic.GenericDatumReader;
import com.flurry.org.apache.avro.io.BinaryEncoder;
import com.flurry.org.apache.avro.io.DatumReader;
import com.flurry.org.apache.avro.io.DatumWriter;
import com.flurry.org.apache.avro.io.Encoder;
import com.flurry.org.apache.avro.io.EncoderFactory;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.rmi.server.UID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class DataFileWriter implements Closeable, Flushable {
  private long blockCount;
  
  private BinaryEncoder bufOut;
  
  private DataFileWriter$NonCopyingByteArrayOutputStream buffer;
  
  private Codec codec;
  
  private DatumWriter dout;
  
  private boolean isOpen;
  
  private final Map meta = new HashMap<Object, Object>();
  
  private DataFileWriter$BufferedFileOutputStream out;
  
  private Schema schema;
  
  private byte[] sync;
  
  private int syncInterval = 16000;
  
  private BinaryEncoder vout;
  
  public DataFileWriter(DatumWriter paramDatumWriter) {
    this.dout = paramDatumWriter;
  }
  
  private void assertNotOpen() {
    if (this.isOpen)
      throw new AvroRuntimeException("already open"); 
  }
  
  private void assertOpen() {
    if (!this.isOpen)
      throw new AvroRuntimeException("not open"); 
  }
  
  private int bufferInUse() {
    return this.buffer.size() + this.bufOut.bytesBuffered();
  }
  
  private static byte[] generateSync() {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      long l = System.currentTimeMillis();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      UID uID = new UID();
      this();
      messageDigest.update(stringBuilder.append(uID).append("@").append(l).toString().getBytes());
      return messageDigest.digest();
    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
      throw new RuntimeException(noSuchAlgorithmException);
    } 
  }
  
  private void init(OutputStream paramOutputStream) {
    this.out = new DataFileWriter$BufferedFileOutputStream(this, paramOutputStream);
    EncoderFactory encoderFactory = new EncoderFactory();
    this.vout = encoderFactory.binaryEncoder(this.out, null);
    this.dout.setSchema(this.schema);
    this.buffer = new DataFileWriter$NonCopyingByteArrayOutputStream(Math.min((int)(this.syncInterval * 1.25D), 1073741822));
    this.bufOut = encoderFactory.binaryEncoder(this.buffer, null);
    if (this.codec == null)
      this.codec = CodecFactory.nullCodec().createInstance(); 
    this.isOpen = true;
  }
  
  public static boolean isReservedMeta(String paramString) {
    return paramString.startsWith("avro.");
  }
  
  private void resetBufferTo(int paramInt) {
    this.bufOut.flush();
    byte[] arrayOfByte = this.buffer.toByteArray();
    this.buffer.reset();
    this.buffer.write(arrayOfByte, 0, paramInt);
  }
  
  private DataFileWriter setMetaInternal(String paramString1, String paramString2) {
    try {
      return setMetaInternal(paramString1, paramString2.getBytes("UTF-8"));
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      throw new RuntimeException(unsupportedEncodingException);
    } 
  }
  
  private DataFileWriter setMetaInternal(String paramString, byte[] paramArrayOfbyte) {
    assertNotOpen();
    this.meta.put(paramString, paramArrayOfbyte);
    return this;
  }
  
  private void writeBlock() {
    if (this.blockCount > 0L) {
      this.bufOut.flush();
      DataFileStream$DataBlock dataFileStream$DataBlock = new DataFileStream$DataBlock(this.buffer.getByteArrayAsByteBuffer(), this.blockCount);
      dataFileStream$DataBlock.compressUsing(this.codec);
      dataFileStream$DataBlock.writeBlockTo(this.vout, this.sync);
      this.buffer.reset();
      this.blockCount = 0L;
    } 
  }
  
  private void writeIfBlockFull() {
    if (bufferInUse() >= this.syncInterval)
      writeBlock(); 
  }
  
  public void append(Object paramObject) {
    assertOpen();
    int i = bufferInUse();
    try {
      this.dout.write(paramObject, (Encoder)this.bufOut);
      this.blockCount++;
      writeIfBlockFull();
      return;
    } catch (IOException iOException) {
      resetBufferTo(i);
      throw new DataFileWriter$AppendWriteException(iOException);
    } catch (RuntimeException runtimeException) {
      resetBufferTo(i);
      throw new DataFileWriter$AppendWriteException(runtimeException);
    } 
  }
  
  public void appendAllFrom(DataFileStream paramDataFileStream, boolean paramBoolean) {
    DataFileStream$DataBlock dataFileStream$DataBlock;
    assertOpen();
    Schema schema1 = paramDataFileStream.getSchema();
    if (!this.schema.equals(schema1))
      throw new IOException("Schema from file " + paramDataFileStream + " does not match"); 
    writeBlock();
    Codec codec = paramDataFileStream.resolveCodec();
    Schema schema3 = null;
    Schema schema2 = null;
    schema1 = schema3;
    if (this.codec.equals(codec)) {
      schema1 = schema3;
      if (!paramBoolean) {
        schema1 = schema2;
        while (paramDataFileStream.hasNextBlock()) {
          dataFileStream$DataBlock = paramDataFileStream.nextRawBlock((DataFileStream$DataBlock)schema1);
          dataFileStream$DataBlock.writeBlockTo(this.vout, this.sync);
        } 
        return;
      } 
    } 
    while (paramDataFileStream.hasNextBlock()) {
      dataFileStream$DataBlock = paramDataFileStream.nextRawBlock(dataFileStream$DataBlock);
      dataFileStream$DataBlock.decompressUsing(codec);
      dataFileStream$DataBlock.compressUsing(this.codec);
      dataFileStream$DataBlock.writeBlockTo(this.vout, this.sync);
    } 
  }
  
  public void appendEncoded(ByteBuffer paramByteBuffer) {
    assertOpen();
    int i = paramByteBuffer.position();
    this.bufOut.writeFixed(paramByteBuffer.array(), i, paramByteBuffer.limit() - i);
    this.blockCount++;
    writeIfBlockFull();
  }
  
  public DataFileWriter appendTo(File paramFile) {
    assertNotOpen();
    if (!paramFile.exists())
      throw new FileNotFoundException("Not found: " + paramFile); 
    RandomAccessFile randomAccessFile = new RandomAccessFile(paramFile, "r");
    DataFileReader dataFileReader = new DataFileReader(new SeekableFileInput(randomAccessFile.getFD()), (DatumReader)new GenericDatumReader());
    this.schema = dataFileReader.getSchema();
    this.sync = (dataFileReader.getHeader()).sync;
    this.meta.putAll((dataFileReader.getHeader()).meta);
    byte[] arrayOfByte = (byte[])this.meta.get("avro.codec");
    if (arrayOfByte != null) {
      this.codec = CodecFactory.fromString(new String(arrayOfByte, "UTF-8")).createInstance();
      randomAccessFile.close();
      init(new FileOutputStream(paramFile, true));
      return this;
    } 
    this.codec = CodecFactory.nullCodec().createInstance();
    randomAccessFile.close();
    init(new FileOutputStream(paramFile, true));
    return this;
  }
  
  public void close() {
    flush();
    this.out.close();
    this.isOpen = false;
  }
  
  public DataFileWriter create(Schema paramSchema, File paramFile) {
    return create(paramSchema, new FileOutputStream(paramFile));
  }
  
  public DataFileWriter create(Schema paramSchema, OutputStream paramOutputStream) {
    assertNotOpen();
    this.schema = paramSchema;
    setMetaInternal("avro.schema", paramSchema.toString());
    this.sync = generateSync();
    init(paramOutputStream);
    this.vout.writeFixed(DataFileConstants.MAGIC);
    this.vout.writeMapStart();
    this.vout.setItemCount(this.meta.size());
    for (Map.Entry entry : this.meta.entrySet()) {
      this.vout.startItem();
      this.vout.writeString((String)entry.getKey());
      this.vout.writeBytes((byte[])entry.getValue());
    } 
    this.vout.writeMapEnd();
    this.vout.writeFixed(this.sync);
    this.vout.flush();
    return this;
  }
  
  public void flush() {
    sync();
    this.vout.flush();
  }
  
  public DataFileWriter setCodec(CodecFactory paramCodecFactory) {
    assertNotOpen();
    this.codec = paramCodecFactory.createInstance();
    setMetaInternal("avro.codec", this.codec.getName());
    return this;
  }
  
  public DataFileWriter setMeta(String paramString, long paramLong) {
    return setMeta(paramString, Long.toString(paramLong));
  }
  
  public DataFileWriter setMeta(String paramString1, String paramString2) {
    try {
      return setMeta(paramString1, paramString2.getBytes("UTF-8"));
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      throw new RuntimeException(unsupportedEncodingException);
    } 
  }
  
  public DataFileWriter setMeta(String paramString, byte[] paramArrayOfbyte) {
    if (isReservedMeta(paramString))
      throw new AvroRuntimeException("Cannot set reserved meta key: " + paramString); 
    return setMetaInternal(paramString, paramArrayOfbyte);
  }
  
  public DataFileWriter setSyncInterval(int paramInt) {
    if (paramInt < 32 || paramInt > 1073741824)
      throw new IllegalArgumentException("Invalid syncInterval value: " + paramInt); 
    this.syncInterval = paramInt;
    return this;
  }
  
  public long sync() {
    assertOpen();
    writeBlock();
    return this.out.tell();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\file\DataFileWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */