package com.flurry.org.codehaus.jackson.map.util;

public final class LinkedNode {
  final LinkedNode _next;
  
  final Object _value;
  
  public LinkedNode(Object paramObject, LinkedNode paramLinkedNode) {
    this._value = paramObject;
    this._next = paramLinkedNode;
  }
  
  public static boolean contains(LinkedNode paramLinkedNode, Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: ifnull -> 24
    //   4: aload_0
    //   5: invokevirtual value : ()Ljava/lang/Object;
    //   8: aload_1
    //   9: if_acmpne -> 16
    //   12: iconst_1
    //   13: istore_2
    //   14: iload_2
    //   15: ireturn
    //   16: aload_0
    //   17: invokevirtual next : ()Lcom/flurry/org/codehaus/jackson/map/util/LinkedNode;
    //   20: astore_0
    //   21: goto -> 0
    //   24: iconst_0
    //   25: istore_2
    //   26: goto -> 14
  }
  
  public LinkedNode next() {
    return this._next;
  }
  
  public Object value() {
    return this._value;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\LinkedNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */