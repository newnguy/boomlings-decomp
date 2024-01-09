package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.Base64Variants;
import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.util.Arrays;

public final class BinaryNode extends ValueNode {
  static final BinaryNode EMPTY_BINARY_NODE = new BinaryNode(new byte[0]);
  
  final byte[] _data;
  
  public BinaryNode(byte[] paramArrayOfbyte) {
    this._data = paramArrayOfbyte;
  }
  
  public BinaryNode(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (paramInt1 == 0 && paramInt2 == paramArrayOfbyte.length) {
      this._data = paramArrayOfbyte;
      return;
    } 
    this._data = new byte[paramInt2];
    System.arraycopy(paramArrayOfbyte, paramInt1, this._data, 0, paramInt2);
  }
  
  public static BinaryNode valueOf(byte[] paramArrayOfbyte) {
    return (paramArrayOfbyte == null) ? null : ((paramArrayOfbyte.length == 0) ? EMPTY_BINARY_NODE : new BinaryNode(paramArrayOfbyte));
  }
  
  public static BinaryNode valueOf(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return (paramArrayOfbyte == null) ? null : ((paramInt2 == 0) ? EMPTY_BINARY_NODE : new BinaryNode(paramArrayOfbyte, paramInt1, paramInt2));
  }
  
  public String asText() {
    return Base64Variants.getDefaultVariant().encode(this._data, false);
  }
  
  public JsonToken asToken() {
    return JsonToken.VALUE_EMBEDDED_OBJECT;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool2 = false;
    if (paramObject == this)
      return true; 
    boolean bool1 = bool2;
    if (paramObject != null) {
      bool1 = bool2;
      if (paramObject.getClass() == getClass())
        bool1 = Arrays.equals(((BinaryNode)paramObject)._data, this._data); 
    } 
    return bool1;
  }
  
  public byte[] getBinaryValue() {
    return this._data;
  }
  
  public int hashCode() {
    return (this._data == null) ? -1 : this._data.length;
  }
  
  public boolean isBinary() {
    return true;
  }
  
  public final void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeBinary(this._data);
  }
  
  public String toString() {
    return Base64Variants.getDefaultVariant().encode(this._data, true);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\BinaryNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */