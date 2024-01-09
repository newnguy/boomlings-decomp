package com.flurry.org.apache.avro.file;

import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.BinaryDecoder;
import com.flurry.org.apache.avro.io.DatumReader;
import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataFileReader12 implements FileReader, Closeable {
  private static final String CODEC = "codec";
  
  private static final String COUNT = "count";
  
  private static final long FOOTER_BLOCK = -1L;
  
  static final byte[] MAGIC = new byte[] { 79, 98, 106, 0 };
  
  private static final String NULL_CODEC = "null";
  
  private static final String SCHEMA = "schema";
  
  private static final String SYNC = "sync";
  
  private static final int SYNC_INTERVAL = 16000;
  
  private static final int SYNC_SIZE = 16;
  
  private static final byte VERSION = 0;
  
  private long blockCount;
  
  private long blockStart;
  
  private long count;
  
  private DataFileReader$SeekableInputStream in;
  
  private Map meta = new HashMap<Object, Object>();
  
  private Object peek;
  
  private DatumReader reader;
  
  private Schema schema;
  
  private byte[] sync = new byte[16];
  
  private byte[] syncBuffer = new byte[16];
  
  private BinaryDecoder vin;
  
  public DataFileReader12(SeekableInput paramSeekableInput, DatumReader paramDatumReader) {
    this.in = new DataFileReader$SeekableInputStream(paramSeekableInput);
    byte[] arrayOfByte = new byte[4];
    this.in.read(arrayOfByte);
    if (!Arrays.equals(MAGIC, arrayOfByte))
      throw new IOException("Not a data file."); 
    long l = this.in.length();
    this.in.seek(l - 4L);
    seek(l - ((this.in.read() << 24) + (this.in.read() << 16) + (this.in.read() << 8) + this.in.read()));
    l = this.vin.readMapStart();
    if (l > 0L) {
      long l1;
      do {
        for (l1 = 0L; l1 < l; l1++) {
          String str1 = this.vin.readString(null).toString();
          ByteBuffer byteBuffer = this.vin.readBytes(null);
          byte[] arrayOfByte1 = new byte[byteBuffer.remaining()];
          byteBuffer.get(arrayOfByte1);
          this.meta.put(str1, arrayOfByte1);
        } 
        l1 = this.vin.mapNext();
        l = l1;
      } while (l1 != 0L);
    } 
    this.sync = getMeta("sync");
    this.count = getMetaLong("count");
    String str = getMetaString("codec");
    if (str != null && !str.equals("null"))
      throw new IOException("Unknown codec: " + str); 
    this.schema = Schema.parse(getMetaString("schema"));
    this.reader = paramDatumReader;
    paramDatumReader.setSchema(this.schema);
    seek(MAGIC.length);
  }
  
  private void skipSync() {
    this.vin.readFixed(this.syncBuffer);
    if (!Arrays.equals(this.syncBuffer, this.sync))
      throw new IOException("Invalid sync!"); 
  }
  
  public void close() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield in : Lcom/flurry/org/apache/avro/file/DataFileReader$SeekableInputStream;
    //   6: invokevirtual close : ()V
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: astore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: aload_1
    //   16: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	12	finally
  }
  
  public byte[] getMeta(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield meta : Ljava/util/Map;
    //   6: aload_1
    //   7: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   12: checkcast [B
    //   15: astore_1
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_1
    //   19: areturn
    //   20: astore_1
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	20	finally
  }
  
  public long getMetaLong(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual getMetaString : (Ljava/lang/String;)Ljava/lang/String;
    //   7: invokestatic parseLong : (Ljava/lang/String;)J
    //   10: lstore_2
    //   11: aload_0
    //   12: monitorexit
    //   13: lload_2
    //   14: lreturn
    //   15: astore_1
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_1
    //   19: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	15	finally
  }
  
  public String getMetaString(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual getMeta : (Ljava/lang/String;)[B
    //   7: astore_1
    //   8: aload_1
    //   9: ifnonnull -> 18
    //   12: aconst_null
    //   13: astore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: areturn
    //   18: new java/lang/String
    //   21: dup
    //   22: aload_1
    //   23: ldc 'UTF-8'
    //   25: invokespecial <init> : ([BLjava/lang/String;)V
    //   28: astore_1
    //   29: goto -> 14
    //   32: astore_1
    //   33: new java/lang/RuntimeException
    //   36: astore_2
    //   37: aload_2
    //   38: aload_1
    //   39: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   42: aload_2
    //   43: athrow
    //   44: astore_1
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_1
    //   48: athrow
    // Exception table:
    //   from	to	target	type
    //   2	8	44	finally
    //   18	29	32	java/io/UnsupportedEncodingException
    //   18	29	44	finally
    //   33	44	44	finally
  }
  
  public Schema getSchema() {
    return this.schema;
  }
  
  public boolean hasNext() {
    boolean bool2 = true;
    boolean bool1 = bool2;
    if (this.peek == null) {
      if (this.blockCount != 0L)
        return bool2; 
    } else {
      return bool1;
    } 
    this.peek = next();
    bool1 = bool2;
    if (this.peek == null)
      bool1 = false; 
    return bool1;
  }
  
  public Iterator iterator() {
    return this;
  }
  
  public Object next() {
    if (this.peek != null) {
      Object object = this.peek;
      this.peek = null;
      return object;
    } 
    try {
      return next(null);
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } 
  }
  
  public Object next(Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield blockCount : J
    //   6: lconst_0
    //   7: lcmp
    //   8: ifne -> 94
    //   11: aload_0
    //   12: getfield in : Lcom/flurry/org/apache/avro/file/DataFileReader$SeekableInputStream;
    //   15: invokevirtual tell : ()J
    //   18: lstore #4
    //   20: aload_0
    //   21: getfield in : Lcom/flurry/org/apache/avro/file/DataFileReader$SeekableInputStream;
    //   24: invokevirtual length : ()J
    //   27: lstore_2
    //   28: lload #4
    //   30: lload_2
    //   31: lcmp
    //   32: ifne -> 41
    //   35: aconst_null
    //   36: astore_1
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_1
    //   40: areturn
    //   41: aload_0
    //   42: invokespecial skipSync : ()V
    //   45: aload_0
    //   46: aload_0
    //   47: getfield vin : Lcom/flurry/org/apache/avro/io/BinaryDecoder;
    //   50: invokevirtual readLong : ()J
    //   53: putfield blockCount : J
    //   56: aload_0
    //   57: getfield blockCount : J
    //   60: ldc2_w -1
    //   63: lcmp
    //   64: ifne -> 2
    //   67: aload_0
    //   68: aload_0
    //   69: getfield vin : Lcom/flurry/org/apache/avro/io/BinaryDecoder;
    //   72: invokevirtual readLong : ()J
    //   75: aload_0
    //   76: getfield in : Lcom/flurry/org/apache/avro/file/DataFileReader$SeekableInputStream;
    //   79: invokevirtual tell : ()J
    //   82: ladd
    //   83: invokevirtual seek : (J)V
    //   86: goto -> 2
    //   89: astore_1
    //   90: aload_0
    //   91: monitorexit
    //   92: aload_1
    //   93: athrow
    //   94: aload_0
    //   95: aload_0
    //   96: getfield blockCount : J
    //   99: lconst_1
    //   100: lsub
    //   101: putfield blockCount : J
    //   104: aload_0
    //   105: getfield reader : Lcom/flurry/org/apache/avro/io/DatumReader;
    //   108: aload_1
    //   109: aload_0
    //   110: getfield vin : Lcom/flurry/org/apache/avro/io/BinaryDecoder;
    //   113: invokeinterface read : (Ljava/lang/Object;Lcom/flurry/org/apache/avro/io/Decoder;)Ljava/lang/Object;
    //   118: astore_1
    //   119: goto -> 37
    // Exception table:
    //   from	to	target	type
    //   2	28	89	finally
    //   41	86	89	finally
    //   94	119	89	finally
  }
  
  public boolean pastSync(long paramLong) {
    return (this.blockStart >= 16L + paramLong || this.blockStart >= this.in.length());
  }
  
  public void remove() {
    throw new UnsupportedOperationException();
  }
  
  public void seek(long paramLong) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield in : Lcom/flurry/org/apache/avro/file/DataFileReader$SeekableInputStream;
    //   6: lload_1
    //   7: invokevirtual seek : (J)V
    //   10: aload_0
    //   11: lconst_0
    //   12: putfield blockCount : J
    //   15: aload_0
    //   16: lload_1
    //   17: putfield blockStart : J
    //   20: aload_0
    //   21: invokestatic get : ()Lcom/flurry/org/apache/avro/io/DecoderFactory;
    //   24: aload_0
    //   25: getfield in : Lcom/flurry/org/apache/avro/file/DataFileReader$SeekableInputStream;
    //   28: aload_0
    //   29: getfield vin : Lcom/flurry/org/apache/avro/io/BinaryDecoder;
    //   32: invokevirtual binaryDecoder : (Ljava/io/InputStream;Lcom/flurry/org/apache/avro/io/BinaryDecoder;)Lcom/flurry/org/apache/avro/io/BinaryDecoder;
    //   35: putfield vin : Lcom/flurry/org/apache/avro/io/BinaryDecoder;
    //   38: aload_0
    //   39: monitorexit
    //   40: return
    //   41: astore_3
    //   42: aload_0
    //   43: monitorexit
    //   44: aload_3
    //   45: athrow
    // Exception table:
    //   from	to	target	type
    //   2	38	41	finally
  }
  
  public void sync(long paramLong) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield in : Lcom/flurry/org/apache/avro/file/DataFileReader$SeekableInputStream;
    //   6: invokevirtual tell : ()J
    //   9: ldc2_w 16
    //   12: ladd
    //   13: aload_0
    //   14: getfield in : Lcom/flurry/org/apache/avro/file/DataFileReader$SeekableInputStream;
    //   17: invokevirtual length : ()J
    //   20: lcmp
    //   21: iflt -> 38
    //   24: aload_0
    //   25: aload_0
    //   26: getfield in : Lcom/flurry/org/apache/avro/file/DataFileReader$SeekableInputStream;
    //   29: invokevirtual length : ()J
    //   32: invokevirtual seek : (J)V
    //   35: aload_0
    //   36: monitorexit
    //   37: return
    //   38: aload_0
    //   39: getfield in : Lcom/flurry/org/apache/avro/file/DataFileReader$SeekableInputStream;
    //   42: lload_1
    //   43: invokevirtual seek : (J)V
    //   46: aload_0
    //   47: getfield vin : Lcom/flurry/org/apache/avro/io/BinaryDecoder;
    //   50: aload_0
    //   51: getfield syncBuffer : [B
    //   54: invokevirtual readFixed : ([B)V
    //   57: iconst_0
    //   58: istore_3
    //   59: aload_0
    //   60: getfield in : Lcom/flurry/org/apache/avro/file/DataFileReader$SeekableInputStream;
    //   63: invokevirtual tell : ()J
    //   66: aload_0
    //   67: getfield in : Lcom/flurry/org/apache/avro/file/DataFileReader$SeekableInputStream;
    //   70: invokevirtual length : ()J
    //   73: lcmp
    //   74: ifge -> 182
    //   77: iconst_0
    //   78: istore #4
    //   80: iload #4
    //   82: aload_0
    //   83: getfield sync : [B
    //   86: arraylength
    //   87: if_icmpge -> 115
    //   90: aload_0
    //   91: getfield sync : [B
    //   94: iload #4
    //   96: baload
    //   97: aload_0
    //   98: getfield syncBuffer : [B
    //   101: iload_3
    //   102: iload #4
    //   104: iadd
    //   105: aload_0
    //   106: getfield sync : [B
    //   109: arraylength
    //   110: irem
    //   111: baload
    //   112: if_icmpeq -> 150
    //   115: iload #4
    //   117: aload_0
    //   118: getfield sync : [B
    //   121: arraylength
    //   122: if_icmpne -> 156
    //   125: aload_0
    //   126: aload_0
    //   127: getfield in : Lcom/flurry/org/apache/avro/file/DataFileReader$SeekableInputStream;
    //   130: invokevirtual tell : ()J
    //   133: ldc2_w 16
    //   136: lsub
    //   137: invokevirtual seek : (J)V
    //   140: goto -> 35
    //   143: astore #5
    //   145: aload_0
    //   146: monitorexit
    //   147: aload #5
    //   149: athrow
    //   150: iinc #4, 1
    //   153: goto -> 80
    //   156: aload_0
    //   157: getfield syncBuffer : [B
    //   160: iload_3
    //   161: aload_0
    //   162: getfield sync : [B
    //   165: arraylength
    //   166: irem
    //   167: aload_0
    //   168: getfield in : Lcom/flurry/org/apache/avro/file/DataFileReader$SeekableInputStream;
    //   171: invokevirtual read : ()I
    //   174: i2b
    //   175: bastore
    //   176: iinc #3, 1
    //   179: goto -> 59
    //   182: aload_0
    //   183: aload_0
    //   184: getfield in : Lcom/flurry/org/apache/avro/file/DataFileReader$SeekableInputStream;
    //   187: invokevirtual length : ()J
    //   190: invokevirtual seek : (J)V
    //   193: goto -> 35
    // Exception table:
    //   from	to	target	type
    //   2	35	143	finally
    //   38	57	143	finally
    //   59	77	143	finally
    //   80	115	143	finally
    //   115	140	143	finally
    //   156	176	143	finally
    //   182	193	143	finally
  }
  
  public long tell() {
    return this.in.tell();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\file\DataFileReader12.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */