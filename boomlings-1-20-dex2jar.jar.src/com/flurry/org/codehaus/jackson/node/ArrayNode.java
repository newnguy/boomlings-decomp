package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class ArrayNode extends ContainerNode {
  protected ArrayList _children;
  
  public ArrayNode(JsonNodeFactory paramJsonNodeFactory) {
    super(paramJsonNodeFactory);
  }
  
  private void _add(JsonNode paramJsonNode) {
    if (this._children == null)
      this._children = new ArrayList(); 
    this._children.add(paramJsonNode);
  }
  
  private void _insert(int paramInt, JsonNode paramJsonNode) {
    if (this._children == null) {
      this._children = new ArrayList();
      this._children.add(paramJsonNode);
      return;
    } 
    if (paramInt < 0) {
      this._children.add(0, paramJsonNode);
      return;
    } 
    if (paramInt >= this._children.size()) {
      this._children.add(paramJsonNode);
      return;
    } 
    this._children.add(paramInt, paramJsonNode);
  }
  
  private boolean _sameChildren(ArrayList paramArrayList) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual size : ()I
    //   4: istore_3
    //   5: aload_0
    //   6: invokevirtual size : ()I
    //   9: iload_3
    //   10: if_icmpeq -> 19
    //   13: iconst_0
    //   14: istore #4
    //   16: iload #4
    //   18: ireturn
    //   19: iconst_0
    //   20: istore_2
    //   21: iload_2
    //   22: iload_3
    //   23: if_icmpge -> 60
    //   26: aload_0
    //   27: getfield _children : Ljava/util/ArrayList;
    //   30: iload_2
    //   31: invokevirtual get : (I)Ljava/lang/Object;
    //   34: checkcast com/flurry/org/codehaus/jackson/JsonNode
    //   37: aload_1
    //   38: iload_2
    //   39: invokevirtual get : (I)Ljava/lang/Object;
    //   42: invokevirtual equals : (Ljava/lang/Object;)Z
    //   45: ifne -> 54
    //   48: iconst_0
    //   49: istore #4
    //   51: goto -> 16
    //   54: iinc #2, 1
    //   57: goto -> 21
    //   60: iconst_1
    //   61: istore #4
    //   63: goto -> 16
  }
  
  public JsonNode _set(int paramInt, JsonNode paramJsonNode) {
    if (this._children == null || paramInt < 0 || paramInt >= this._children.size())
      throw new IndexOutOfBoundsException("Illegal index " + paramInt + ", array size " + size()); 
    return this._children.set(paramInt, paramJsonNode);
  }
  
  public void add(double paramDouble) {
    _add(numberNode(paramDouble));
  }
  
  public void add(float paramFloat) {
    _add(numberNode(paramFloat));
  }
  
  public void add(int paramInt) {
    _add(numberNode(paramInt));
  }
  
  public void add(long paramLong) {
    _add(numberNode(paramLong));
  }
  
  public void add(JsonNode paramJsonNode) {
    JsonNode jsonNode = paramJsonNode;
    if (paramJsonNode == null)
      jsonNode = nullNode(); 
    _add(jsonNode);
  }
  
  public void add(Boolean paramBoolean) {
    if (paramBoolean == null) {
      addNull();
      return;
    } 
    _add(booleanNode(paramBoolean.booleanValue()));
  }
  
  public void add(Double paramDouble) {
    if (paramDouble == null) {
      addNull();
      return;
    } 
    _add(numberNode(paramDouble.doubleValue()));
  }
  
  public void add(Float paramFloat) {
    if (paramFloat == null) {
      addNull();
      return;
    } 
    _add(numberNode(paramFloat.floatValue()));
  }
  
  public void add(Integer paramInteger) {
    if (paramInteger == null) {
      addNull();
      return;
    } 
    _add(numberNode(paramInteger.intValue()));
  }
  
  public void add(Long paramLong) {
    if (paramLong == null) {
      addNull();
      return;
    } 
    _add(numberNode(paramLong.longValue()));
  }
  
  public void add(String paramString) {
    if (paramString == null) {
      addNull();
      return;
    } 
    _add(textNode(paramString));
  }
  
  public void add(BigDecimal paramBigDecimal) {
    if (paramBigDecimal == null) {
      addNull();
      return;
    } 
    _add(numberNode(paramBigDecimal));
  }
  
  public void add(boolean paramBoolean) {
    _add(booleanNode(paramBoolean));
  }
  
  public void add(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte == null) {
      addNull();
      return;
    } 
    _add(binaryNode(paramArrayOfbyte));
  }
  
  public JsonNode addAll(ArrayNode paramArrayNode) {
    int i = paramArrayNode.size();
    if (i > 0) {
      if (this._children == null)
        this._children = new ArrayList(i + 2); 
      paramArrayNode.addContentsTo(this._children);
    } 
    return this;
  }
  
  public JsonNode addAll(Collection<?> paramCollection) {
    if (paramCollection.size() > 0) {
      if (this._children == null) {
        this._children = new ArrayList(paramCollection);
        return this;
      } 
    } else {
      return this;
    } 
    this._children.addAll(paramCollection);
    return this;
  }
  
  public ArrayNode addArray() {
    ArrayNode arrayNode = arrayNode();
    _add(arrayNode);
    return arrayNode;
  }
  
  protected void addContentsTo(List<JsonNode> paramList) {
    if (this._children != null) {
      Iterator<JsonNode> iterator = this._children.iterator();
      while (iterator.hasNext())
        paramList.add(iterator.next()); 
    } 
  }
  
  public void addNull() {
    _add(nullNode());
  }
  
  public ObjectNode addObject() {
    ObjectNode objectNode = objectNode();
    _add(objectNode);
    return objectNode;
  }
  
  public void addPOJO(Object paramObject) {
    if (paramObject == null) {
      addNull();
      return;
    } 
    _add(POJONode(paramObject));
  }
  
  public JsonToken asToken() {
    return JsonToken.START_ARRAY;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (paramObject == null)
        return false; 
      if (paramObject.getClass() != getClass())
        return false; 
      paramObject = paramObject;
      if (this._children == null || this._children.size() == 0) {
        if (paramObject.size() != 0)
          bool = false; 
        return bool;
      } 
      bool = paramObject._sameChildren(this._children);
    } 
    return bool;
  }
  
  public ObjectNode findParent(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: getfield _children : Ljava/util/ArrayList;
    //   4: ifnull -> 49
    //   7: aload_0
    //   8: getfield _children : Ljava/util/ArrayList;
    //   11: invokevirtual iterator : ()Ljava/util/Iterator;
    //   14: astore_3
    //   15: aload_3
    //   16: invokeinterface hasNext : ()Z
    //   21: ifeq -> 49
    //   24: aload_3
    //   25: invokeinterface next : ()Ljava/lang/Object;
    //   30: checkcast com/flurry/org/codehaus/jackson/JsonNode
    //   33: aload_1
    //   34: invokevirtual findParent : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   37: astore_2
    //   38: aload_2
    //   39: ifnull -> 15
    //   42: aload_2
    //   43: checkcast com/flurry/org/codehaus/jackson/node/ObjectNode
    //   46: astore_1
    //   47: aload_1
    //   48: areturn
    //   49: aconst_null
    //   50: astore_1
    //   51: goto -> 47
  }
  
  public List findParents(String paramString, List paramList) {
    List list = paramList;
    if (this._children != null) {
      Iterator<JsonNode> iterator = this._children.iterator();
      while (true) {
        list = paramList;
        if (iterator.hasNext()) {
          paramList = ((JsonNode)iterator.next()).findParents(paramString, paramList);
          continue;
        } 
        break;
      } 
    } 
    return list;
  }
  
  public JsonNode findValue(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: getfield _children : Ljava/util/ArrayList;
    //   4: ifnull -> 46
    //   7: aload_0
    //   8: getfield _children : Ljava/util/ArrayList;
    //   11: invokevirtual iterator : ()Ljava/util/Iterator;
    //   14: astore_3
    //   15: aload_3
    //   16: invokeinterface hasNext : ()Z
    //   21: ifeq -> 46
    //   24: aload_3
    //   25: invokeinterface next : ()Ljava/lang/Object;
    //   30: checkcast com/flurry/org/codehaus/jackson/JsonNode
    //   33: aload_1
    //   34: invokevirtual findValue : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   37: astore_2
    //   38: aload_2
    //   39: ifnull -> 15
    //   42: aload_2
    //   43: astore_1
    //   44: aload_1
    //   45: areturn
    //   46: aconst_null
    //   47: astore_1
    //   48: goto -> 44
  }
  
  public List findValues(String paramString, List paramList) {
    List list = paramList;
    if (this._children != null) {
      Iterator<JsonNode> iterator = this._children.iterator();
      while (true) {
        list = paramList;
        if (iterator.hasNext()) {
          paramList = ((JsonNode)iterator.next()).findValues(paramString, paramList);
          continue;
        } 
        break;
      } 
    } 
    return list;
  }
  
  public List findValuesAsText(String paramString, List paramList) {
    List list = paramList;
    if (this._children != null) {
      Iterator<JsonNode> iterator = this._children.iterator();
      while (true) {
        list = paramList;
        if (iterator.hasNext()) {
          paramList = ((JsonNode)iterator.next()).findValuesAsText(paramString, paramList);
          continue;
        } 
        break;
      } 
    } 
    return list;
  }
  
  public JsonNode get(int paramInt) {
    return (paramInt >= 0 && this._children != null && paramInt < this._children.size()) ? this._children.get(paramInt) : null;
  }
  
  public JsonNode get(String paramString) {
    return null;
  }
  
  public Iterator getElements() {
    return (this._children == null) ? ContainerNode$NoNodesIterator.instance() : this._children.iterator();
  }
  
  public int hashCode() {
    if (this._children == null)
      return 1; 
    int i = this._children.size();
    Iterator<JsonNode> iterator = this._children.iterator();
    while (true) {
      int j = i;
      if (iterator.hasNext()) {
        JsonNode jsonNode = iterator.next();
        if (jsonNode != null)
          i = jsonNode.hashCode() ^ i; 
        continue;
      } 
      return j;
    } 
  }
  
  public void insert(int paramInt, double paramDouble) {
    _insert(paramInt, numberNode(paramDouble));
  }
  
  public void insert(int paramInt, float paramFloat) {
    _insert(paramInt, numberNode(paramFloat));
  }
  
  public void insert(int paramInt1, int paramInt2) {
    _insert(paramInt1, numberNode(paramInt2));
  }
  
  public void insert(int paramInt, long paramLong) {
    _insert(paramInt, numberNode(paramLong));
  }
  
  public void insert(int paramInt, JsonNode paramJsonNode) {
    JsonNode jsonNode = paramJsonNode;
    if (paramJsonNode == null)
      jsonNode = nullNode(); 
    _insert(paramInt, jsonNode);
  }
  
  public void insert(int paramInt, Boolean paramBoolean) {
    if (paramBoolean == null) {
      insertNull(paramInt);
      return;
    } 
    _insert(paramInt, booleanNode(paramBoolean.booleanValue()));
  }
  
  public void insert(int paramInt, Double paramDouble) {
    if (paramDouble == null) {
      insertNull(paramInt);
      return;
    } 
    _insert(paramInt, numberNode(paramDouble.doubleValue()));
  }
  
  public void insert(int paramInt, Float paramFloat) {
    if (paramFloat == null) {
      insertNull(paramInt);
      return;
    } 
    _insert(paramInt, numberNode(paramFloat.floatValue()));
  }
  
  public void insert(int paramInt, Integer paramInteger) {
    if (paramInteger == null) {
      insertNull(paramInt);
      return;
    } 
    _insert(paramInt, numberNode(paramInteger.intValue()));
  }
  
  public void insert(int paramInt, Long paramLong) {
    if (paramLong == null) {
      insertNull(paramInt);
      return;
    } 
    _insert(paramInt, numberNode(paramLong.longValue()));
  }
  
  public void insert(int paramInt, String paramString) {
    if (paramString == null) {
      insertNull(paramInt);
      return;
    } 
    _insert(paramInt, textNode(paramString));
  }
  
  public void insert(int paramInt, BigDecimal paramBigDecimal) {
    if (paramBigDecimal == null) {
      insertNull(paramInt);
      return;
    } 
    _insert(paramInt, numberNode(paramBigDecimal));
  }
  
  public void insert(int paramInt, boolean paramBoolean) {
    _insert(paramInt, booleanNode(paramBoolean));
  }
  
  public void insert(int paramInt, byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte == null) {
      insertNull(paramInt);
      return;
    } 
    _insert(paramInt, binaryNode(paramArrayOfbyte));
  }
  
  public ArrayNode insertArray(int paramInt) {
    ArrayNode arrayNode = arrayNode();
    _insert(paramInt, arrayNode);
    return arrayNode;
  }
  
  public void insertNull(int paramInt) {
    _insert(paramInt, nullNode());
  }
  
  public ObjectNode insertObject(int paramInt) {
    ObjectNode objectNode = objectNode();
    _insert(paramInt, objectNode);
    return objectNode;
  }
  
  public void insertPOJO(int paramInt, Object paramObject) {
    if (paramObject == null) {
      insertNull(paramInt);
      return;
    } 
    _insert(paramInt, POJONode(paramObject));
  }
  
  public boolean isArray() {
    return true;
  }
  
  public JsonNode path(int paramInt) {
    return (paramInt >= 0 && this._children != null && paramInt < this._children.size()) ? this._children.get(paramInt) : MissingNode.getInstance();
  }
  
  public JsonNode path(String paramString) {
    return MissingNode.getInstance();
  }
  
  public JsonNode remove(int paramInt) {
    return (paramInt >= 0 && this._children != null && paramInt < this._children.size()) ? this._children.remove(paramInt) : null;
  }
  
  public ArrayNode removeAll() {
    this._children = null;
    return this;
  }
  
  public final void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeStartArray();
    if (this._children != null) {
      Iterator<BaseJsonNode> iterator = this._children.iterator();
      while (iterator.hasNext())
        ((BaseJsonNode)iterator.next()).serialize(paramJsonGenerator, paramSerializerProvider); 
    } 
    paramJsonGenerator.writeEndArray();
  }
  
  public void serializeWithType(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    paramTypeSerializer.writeTypePrefixForArray(this, paramJsonGenerator);
    if (this._children != null) {
      Iterator<BaseJsonNode> iterator = this._children.iterator();
      while (iterator.hasNext())
        ((BaseJsonNode)iterator.next()).serialize(paramJsonGenerator, paramSerializerProvider); 
    } 
    paramTypeSerializer.writeTypeSuffixForArray(this, paramJsonGenerator);
  }
  
  public JsonNode set(int paramInt, JsonNode paramJsonNode) {
    JsonNode jsonNode = paramJsonNode;
    if (paramJsonNode == null)
      jsonNode = nullNode(); 
    return _set(paramInt, jsonNode);
  }
  
  public int size() {
    return (this._children == null) ? 0 : this._children.size();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder((size() << 4) + 16);
    stringBuilder.append('[');
    if (this._children != null) {
      int i = this._children.size();
      for (byte b = 0; b < i; b++) {
        if (b > 0)
          stringBuilder.append(','); 
        stringBuilder.append(((JsonNode)this._children.get(b)).toString());
      } 
    } 
    stringBuilder.append(']');
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\ArrayNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */