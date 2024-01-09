package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Schema;
import java.io.OutputStream;
import java.util.Arrays;

public class BlockingBinaryEncoder extends BufferedBinaryEncoder {
  static final boolean $assertionsDisabled;
  
  private static final int STACK_STEP = 10;
  
  private BlockingBinaryEncoder$BlockedValue[] blockStack;
  
  private byte[] buf;
  
  private byte[] headerBuffer = new byte[12];
  
  private int pos;
  
  private int stackTop = -1;
  
  static {
    boolean bool;
    if (!BlockingBinaryEncoder.class.desiredAssertionStatus()) {
      bool = true;
    } else {
      bool = false;
    } 
    $assertionsDisabled = bool;
  }
  
  BlockingBinaryEncoder(OutputStream paramOutputStream, int paramInt1, int paramInt2) {
    super(paramOutputStream, paramInt2);
    this.buf = new byte[paramInt1];
    this.pos = 0;
    this.blockStack = new BlockingBinaryEncoder$BlockedValue[0];
    expandStack();
    BlockingBinaryEncoder$BlockedValue[] arrayOfBlockingBinaryEncoder$BlockedValue = this.blockStack;
    paramInt1 = this.stackTop + 1;
    this.stackTop = paramInt1;
    BlockingBinaryEncoder$BlockedValue blockingBinaryEncoder$BlockedValue = arrayOfBlockingBinaryEncoder$BlockedValue[paramInt1];
    blockingBinaryEncoder$BlockedValue.type = null;
    blockingBinaryEncoder$BlockedValue.state = BlockingBinaryEncoder$BlockedValue$State.ROOT;
    blockingBinaryEncoder$BlockedValue.lastFullItem = 0;
    blockingBinaryEncoder$BlockedValue.start = 0;
    blockingBinaryEncoder$BlockedValue.items = 1;
    assert check();
  }
  
  private boolean check() {
    assert this.buf != null;
    assert this.pos >= 0;
    assert this.pos <= this.buf.length : this.pos + " " + this.buf.length;
    assert this.blockStack != null;
    BlockingBinaryEncoder$BlockedValue blockingBinaryEncoder$BlockedValue = null;
    byte b = 0;
    while (b <= this.stackTop) {
      BlockingBinaryEncoder$BlockedValue blockingBinaryEncoder$BlockedValue1 = this.blockStack[b];
      blockingBinaryEncoder$BlockedValue1.check(blockingBinaryEncoder$BlockedValue, this.pos);
      b++;
      blockingBinaryEncoder$BlockedValue = blockingBinaryEncoder$BlockedValue1;
    } 
    return true;
  }
  
  private void compact() {
    int i;
    BlockingBinaryEncoder$BlockedValue blockingBinaryEncoder$BlockedValue2 = null;
    assert check();
    byte b = 1;
    BlockingBinaryEncoder$BlockedValue blockingBinaryEncoder$BlockedValue1 = null;
    while (true) {
      if (b <= this.stackTop) {
        blockingBinaryEncoder$BlockedValue1 = this.blockStack[b];
        if (blockingBinaryEncoder$BlockedValue1.state != BlockingBinaryEncoder$BlockedValue$State.REGULAR) {
          b++;
          continue;
        } 
      } 
      assert blockingBinaryEncoder$BlockedValue1 != null;
      break;
    } 
    super.writeFixed(this.buf, 0, blockingBinaryEncoder$BlockedValue1.start);
    if (1 < blockingBinaryEncoder$BlockedValue1.items) {
      super.writeInt(-(blockingBinaryEncoder$BlockedValue1.items - 1));
      super.writeInt(blockingBinaryEncoder$BlockedValue1.lastFullItem - blockingBinaryEncoder$BlockedValue1.start);
      super.writeFixed(this.buf, blockingBinaryEncoder$BlockedValue1.start, blockingBinaryEncoder$BlockedValue1.lastFullItem - blockingBinaryEncoder$BlockedValue1.start);
      blockingBinaryEncoder$BlockedValue1.start = blockingBinaryEncoder$BlockedValue1.lastFullItem;
      blockingBinaryEncoder$BlockedValue1.items = 1;
    } 
    super.writeInt(1);
    if (b + 1 <= this.stackTop)
      blockingBinaryEncoder$BlockedValue2 = this.blockStack[b + 1]; 
    if (blockingBinaryEncoder$BlockedValue2 == null) {
      i = this.pos;
    } else {
      i = blockingBinaryEncoder$BlockedValue2.start;
    } 
    super.writeFixed(this.buf, blockingBinaryEncoder$BlockedValue1.lastFullItem, i - blockingBinaryEncoder$BlockedValue1.lastFullItem);
    System.arraycopy(this.buf, i, this.buf, 0, this.pos - i);
    while (++b <= this.stackTop) {
      blockingBinaryEncoder$BlockedValue2 = this.blockStack[b];
      blockingBinaryEncoder$BlockedValue2.start -= i;
      blockingBinaryEncoder$BlockedValue2.lastFullItem -= i;
      b++;
    } 
    this.pos -= i;
    assert blockingBinaryEncoder$BlockedValue1.items == 1;
    blockingBinaryEncoder$BlockedValue1.lastFullItem = 0;
    blockingBinaryEncoder$BlockedValue1.start = 0;
    blockingBinaryEncoder$BlockedValue1.state = BlockingBinaryEncoder$BlockedValue$State.OVERFLOW;
    assert check();
  }
  
  private void doWriteBytes(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (paramInt2 < this.buf.length) {
      ensureBounds(paramInt2);
      System.arraycopy(paramArrayOfbyte, paramInt1, this.buf, this.pos, paramInt2);
      this.pos += paramInt2;
      return;
    } 
    ensureBounds(this.buf.length);
    assert (this.blockStack[this.stackTop]).state == BlockingBinaryEncoder$BlockedValue$State.ROOT || (this.blockStack[this.stackTop]).state == BlockingBinaryEncoder$BlockedValue$State.OVERFLOW;
    write(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  private void endBlockedValue() {
    while (true) {
      assert check();
      BlockingBinaryEncoder$BlockedValue blockingBinaryEncoder$BlockedValue = this.blockStack[this.stackTop];
      assert blockingBinaryEncoder$BlockedValue.state != BlockingBinaryEncoder$BlockedValue$State.ROOT;
      if (blockingBinaryEncoder$BlockedValue.state == BlockingBinaryEncoder$BlockedValue$State.OVERFLOW)
        finishOverflow(); 
      assert blockingBinaryEncoder$BlockedValue.state == BlockingBinaryEncoder$BlockedValue$State.REGULAR;
      if (blockingBinaryEncoder$BlockedValue.items > 0) {
        int j = this.pos - blockingBinaryEncoder$BlockedValue.start;
        if (blockingBinaryEncoder$BlockedValue.start == 0 && (this.blockStack[this.stackTop - 1]).state != BlockingBinaryEncoder$BlockedValue$State.REGULAR) {
          super.writeInt(-blockingBinaryEncoder$BlockedValue.items);
          super.writeInt(j);
        } else {
          int k = BinaryData.encodeInt(-blockingBinaryEncoder$BlockedValue.items, this.headerBuffer, 0) + 0;
          int m = k + BinaryData.encodeInt(j, this.headerBuffer, k);
          if (this.buf.length >= this.pos + m) {
            this.pos += m;
            k = blockingBinaryEncoder$BlockedValue.start;
            System.arraycopy(this.buf, k, this.buf, k + m, j);
            System.arraycopy(this.headerBuffer, 0, this.buf, k, m);
          } else {
            compact();
            continue;
          } 
        } 
      } 
      this.stackTop--;
      ensureBounds(1);
      byte[] arrayOfByte = this.buf;
      int i = this.pos;
      this.pos = i + 1;
      arrayOfByte[i] = 0;
      assert check();
      break;
    } 
    if ((this.blockStack[this.stackTop]).state == BlockingBinaryEncoder$BlockedValue$State.ROOT)
      flush(); 
  }
  
  private void ensureBounds(int paramInt) {
    while (this.buf.length < this.pos + paramInt) {
      if ((this.blockStack[this.stackTop]).state == BlockingBinaryEncoder$BlockedValue$State.REGULAR) {
        compact();
        continue;
      } 
      super.writeFixed(this.buf, 0, this.pos);
      this.pos = 0;
    } 
  }
  
  private void expandStack() {
    int i = this.blockStack.length;
    this.blockStack = Arrays.<BlockingBinaryEncoder$BlockedValue>copyOf(this.blockStack, this.blockStack.length + 10);
    while (i < this.blockStack.length) {
      this.blockStack[i] = new BlockingBinaryEncoder$BlockedValue();
      i++;
    } 
  }
  
  private void finishOverflow() {
    BlockingBinaryEncoder$BlockedValue blockingBinaryEncoder$BlockedValue = this.blockStack[this.stackTop];
    if (blockingBinaryEncoder$BlockedValue.state != BlockingBinaryEncoder$BlockedValue$State.OVERFLOW)
      throw new IllegalStateException("Not an overflow block"); 
    assert check();
    super.writeFixed(this.buf, 0, this.pos);
    this.pos = 0;
    blockingBinaryEncoder$BlockedValue.state = BlockingBinaryEncoder$BlockedValue$State.REGULAR;
    blockingBinaryEncoder$BlockedValue.lastFullItem = 0;
    blockingBinaryEncoder$BlockedValue.start = 0;
    blockingBinaryEncoder$BlockedValue.items = 0;
    assert check();
  }
  
  private void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if ((this.blockStack[this.stackTop]).state == BlockingBinaryEncoder$BlockedValue$State.ROOT) {
      super.writeFixed(paramArrayOfbyte, paramInt1, paramInt2);
    } else {
      int i = paramInt2;
      assert false;
      while (this.buf.length < this.pos + i) {
        if ((this.blockStack[this.stackTop]).state == BlockingBinaryEncoder$BlockedValue$State.REGULAR) {
          compact();
          continue;
        } 
        super.writeFixed(this.buf, 0, this.pos);
        this.pos = 0;
        if (this.buf.length <= i) {
          super.writeFixed(paramArrayOfbyte, paramInt1, i);
          i = 0;
        } 
      } 
      System.arraycopy(paramArrayOfbyte, paramInt1, this.buf, this.pos, i);
      this.pos += i;
    } 
    assert check();
  }
  
  public int bytesBuffered() {
    return this.pos + super.bytesBuffered();
  }
  
  BlockingBinaryEncoder configure(OutputStream paramOutputStream, int paramInt1, int paramInt2) {
    configure(paramOutputStream, paramInt2);
    this.pos = 0;
    this.stackTop = 0;
    if (this.buf == null || this.buf.length != paramInt1)
      this.buf = new byte[paramInt1]; 
    assert check();
    return this;
  }
  
  public void flush() {
    // Byte code:
    //   0: aload_0
    //   1: getfield blockStack : [Lcom/flurry/org/apache/avro/io/BlockingBinaryEncoder$BlockedValue;
    //   4: aload_0
    //   5: getfield stackTop : I
    //   8: aaload
    //   9: astore_1
    //   10: aload_1
    //   11: getfield state : Lcom/flurry/org/apache/avro/io/BlockingBinaryEncoder$BlockedValue$State;
    //   14: getstatic com/flurry/org/apache/avro/io/BlockingBinaryEncoder$BlockedValue$State.ROOT : Lcom/flurry/org/apache/avro/io/BlockingBinaryEncoder$BlockedValue$State;
    //   17: if_acmpne -> 63
    //   20: aload_0
    //   21: aload_0
    //   22: getfield buf : [B
    //   25: iconst_0
    //   26: aload_0
    //   27: getfield pos : I
    //   30: invokespecial writeFixed : ([BII)V
    //   33: aload_0
    //   34: iconst_0
    //   35: putfield pos : I
    //   38: aload_0
    //   39: invokespecial flush : ()V
    //   42: getstatic com/flurry/org/apache/avro/io/BlockingBinaryEncoder.$assertionsDisabled : Z
    //   45: ifne -> 80
    //   48: aload_0
    //   49: invokespecial check : ()Z
    //   52: ifne -> 80
    //   55: new java/lang/AssertionError
    //   58: dup
    //   59: invokespecial <init> : ()V
    //   62: athrow
    //   63: aload_1
    //   64: getfield state : Lcom/flurry/org/apache/avro/io/BlockingBinaryEncoder$BlockedValue$State;
    //   67: getstatic com/flurry/org/apache/avro/io/BlockingBinaryEncoder$BlockedValue$State.OVERFLOW : Lcom/flurry/org/apache/avro/io/BlockingBinaryEncoder$BlockedValue$State;
    //   70: if_acmpeq -> 38
    //   73: aload_0
    //   74: invokespecial compact : ()V
    //   77: goto -> 63
    //   80: return
  }
  
  public void setItemCount(long paramLong) {
    BlockingBinaryEncoder$BlockedValue blockingBinaryEncoder$BlockedValue = this.blockStack[this.stackTop];
    assert blockingBinaryEncoder$BlockedValue.type == Schema.Type.ARRAY || blockingBinaryEncoder$BlockedValue.type == Schema.Type.MAP;
    assert blockingBinaryEncoder$BlockedValue.itemsLeftToWrite == 0L;
    blockingBinaryEncoder$BlockedValue.itemsLeftToWrite = paramLong;
    assert check();
  }
  
  public void startItem() {
    if ((this.blockStack[this.stackTop]).state == BlockingBinaryEncoder$BlockedValue$State.OVERFLOW)
      finishOverflow(); 
    BlockingBinaryEncoder$BlockedValue blockingBinaryEncoder$BlockedValue = this.blockStack[this.stackTop];
    blockingBinaryEncoder$BlockedValue.items++;
    blockingBinaryEncoder$BlockedValue.lastFullItem = this.pos;
    blockingBinaryEncoder$BlockedValue.itemsLeftToWrite--;
    assert check();
  }
  
  public void writeArrayEnd() {
    BlockingBinaryEncoder$BlockedValue blockingBinaryEncoder$BlockedValue = this.blockStack[this.stackTop];
    if (blockingBinaryEncoder$BlockedValue.type != Schema.Type.ARRAY)
      throw new AvroTypeException("Called writeArrayEnd outside of an array."); 
    if (blockingBinaryEncoder$BlockedValue.itemsLeftToWrite != 0L)
      throw new AvroTypeException("Failed to write expected number of array elements."); 
    endBlockedValue();
    assert check();
  }
  
  public void writeArrayStart() {
    if (this.stackTop + 1 == this.blockStack.length)
      expandStack(); 
    BlockingBinaryEncoder$BlockedValue[] arrayOfBlockingBinaryEncoder$BlockedValue = this.blockStack;
    int i = this.stackTop + 1;
    this.stackTop = i;
    BlockingBinaryEncoder$BlockedValue blockingBinaryEncoder$BlockedValue = arrayOfBlockingBinaryEncoder$BlockedValue[i];
    blockingBinaryEncoder$BlockedValue.type = Schema.Type.ARRAY;
    blockingBinaryEncoder$BlockedValue.state = BlockingBinaryEncoder$BlockedValue$State.REGULAR;
    i = this.pos;
    blockingBinaryEncoder$BlockedValue.lastFullItem = i;
    blockingBinaryEncoder$BlockedValue.start = i;
    blockingBinaryEncoder$BlockedValue.items = 0;
    assert check();
  }
  
  public void writeBoolean(boolean paramBoolean) {
    ensureBounds(1);
    this.pos += BinaryData.encodeBoolean(paramBoolean, this.buf, this.pos);
  }
  
  public void writeDouble(double paramDouble) {
    ensureBounds(8);
    this.pos += BinaryData.encodeDouble(paramDouble, this.buf, this.pos);
  }
  
  public void writeFixed(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    doWriteBytes(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public void writeFloat(float paramFloat) {
    ensureBounds(4);
    this.pos += BinaryData.encodeFloat(paramFloat, this.buf, this.pos);
  }
  
  public void writeIndex(int paramInt) {
    ensureBounds(5);
    this.pos += BinaryData.encodeInt(paramInt, this.buf, this.pos);
  }
  
  public void writeInt(int paramInt) {
    ensureBounds(5);
    this.pos += BinaryData.encodeInt(paramInt, this.buf, this.pos);
  }
  
  public void writeLong(long paramLong) {
    ensureBounds(10);
    this.pos += BinaryData.encodeLong(paramLong, this.buf, this.pos);
  }
  
  public void writeMapEnd() {
    BlockingBinaryEncoder$BlockedValue blockingBinaryEncoder$BlockedValue = this.blockStack[this.stackTop];
    if (blockingBinaryEncoder$BlockedValue.type != Schema.Type.MAP)
      throw new AvroTypeException("Called writeMapEnd outside of a map."); 
    if (blockingBinaryEncoder$BlockedValue.itemsLeftToWrite != 0L)
      throw new AvroTypeException("Failed to read write expected number of array elements."); 
    endBlockedValue();
    assert check();
  }
  
  public void writeMapStart() {
    if (this.stackTop + 1 == this.blockStack.length)
      expandStack(); 
    BlockingBinaryEncoder$BlockedValue[] arrayOfBlockingBinaryEncoder$BlockedValue = this.blockStack;
    int i = this.stackTop + 1;
    this.stackTop = i;
    BlockingBinaryEncoder$BlockedValue blockingBinaryEncoder$BlockedValue = arrayOfBlockingBinaryEncoder$BlockedValue[i];
    blockingBinaryEncoder$BlockedValue.type = Schema.Type.MAP;
    blockingBinaryEncoder$BlockedValue.state = BlockingBinaryEncoder$BlockedValue$State.REGULAR;
    i = this.pos;
    blockingBinaryEncoder$BlockedValue.lastFullItem = i;
    blockingBinaryEncoder$BlockedValue.start = i;
    blockingBinaryEncoder$BlockedValue.items = 0;
    assert check();
  }
  
  protected void writeZero() {
    ensureBounds(1);
    byte[] arrayOfByte = this.buf;
    int i = this.pos;
    this.pos = i + 1;
    arrayOfByte[i] = 0;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\BlockingBinaryEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */