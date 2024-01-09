package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.Schema;

class BlockingBinaryEncoder$BlockedValue {
  static final boolean $assertionsDisabled;
  
  public int items = 1;
  
  public long itemsLeftToWrite;
  
  public int lastFullItem = 0;
  
  public int start = 0;
  
  public BlockingBinaryEncoder$BlockedValue$State state = BlockingBinaryEncoder$BlockedValue$State.ROOT;
  
  public Schema.Type type = null;
  
  static {
    boolean bool;
    if (!BlockingBinaryEncoder.class.desiredAssertionStatus()) {
      bool = true;
    } else {
      bool = false;
    } 
    $assertionsDisabled = bool;
  }
  
  public boolean check(BlockingBinaryEncoder$BlockedValue paramBlockingBinaryEncoder$BlockedValue, int paramInt) {
    assert this.state != BlockingBinaryEncoder$BlockedValue$State.ROOT || this.type == null;
    assert this.state == BlockingBinaryEncoder$BlockedValue$State.ROOT || this.type == Schema.Type.ARRAY || this.type == Schema.Type.MAP;
    assert this.items >= 0;
    assert this.items != 0 || this.start == paramInt;
    assert 1 < this.items || this.start == this.lastFullItem;
    assert this.items <= 1 || this.start <= this.lastFullItem;
    assert this.lastFullItem <= paramInt;
    switch (BlockingBinaryEncoder$1.$SwitchMap$org$apache$avro$io$BlockingBinaryEncoder$BlockedValue$State[this.state.ordinal()]) {
      default:
        return false;
      case 1:
        assert this.start == 0;
        assert paramBlockingBinaryEncoder$BlockedValue == null;
      case 2:
        assert this.start >= 0;
        assert paramBlockingBinaryEncoder$BlockedValue.lastFullItem <= this.start;
        assert 1 <= paramBlockingBinaryEncoder$BlockedValue.items;
      case 3:
        break;
    } 
    assert this.start == 0;
    assert this.items == 1;
    assert paramBlockingBinaryEncoder$BlockedValue.state == BlockingBinaryEncoder$BlockedValue$State.ROOT || paramBlockingBinaryEncoder$BlockedValue.state == BlockingBinaryEncoder$BlockedValue$State.OVERFLOW;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\BlockingBinaryEncoder$BlockedValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */