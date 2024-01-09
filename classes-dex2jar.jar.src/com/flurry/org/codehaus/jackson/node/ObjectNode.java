package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ObjectNode extends ContainerNode {
  protected LinkedHashMap _children = null;
  
  public ObjectNode(JsonNodeFactory paramJsonNodeFactory) {
    super(paramJsonNodeFactory);
  }
  
  private final JsonNode _put(String paramString, JsonNode paramJsonNode) {
    if (this._children == null)
      this._children = new LinkedHashMap<Object, Object>(); 
    return this._children.put(paramString, paramJsonNode);
  }
  
  public JsonToken asToken() {
    return JsonToken.START_OBJECT;
  }
  
  public boolean equals(Object paramObject) {
    // Byte code:
    //   0: aload_1
    //   1: aload_0
    //   2: if_acmpne -> 9
    //   5: iconst_1
    //   6: istore_2
    //   7: iload_2
    //   8: ireturn
    //   9: aload_1
    //   10: ifnonnull -> 18
    //   13: iconst_0
    //   14: istore_2
    //   15: goto -> 7
    //   18: aload_1
    //   19: invokevirtual getClass : ()Ljava/lang/Class;
    //   22: aload_0
    //   23: invokevirtual getClass : ()Ljava/lang/Class;
    //   26: if_acmpeq -> 34
    //   29: iconst_0
    //   30: istore_2
    //   31: goto -> 7
    //   34: aload_1
    //   35: checkcast com/flurry/org/codehaus/jackson/node/ObjectNode
    //   38: astore_1
    //   39: aload_1
    //   40: invokevirtual size : ()I
    //   43: aload_0
    //   44: invokevirtual size : ()I
    //   47: if_icmpeq -> 55
    //   50: iconst_0
    //   51: istore_2
    //   52: goto -> 7
    //   55: aload_0
    //   56: getfield _children : Ljava/util/LinkedHashMap;
    //   59: ifnull -> 147
    //   62: aload_0
    //   63: getfield _children : Ljava/util/LinkedHashMap;
    //   66: invokevirtual entrySet : ()Ljava/util/Set;
    //   69: invokeinterface iterator : ()Ljava/util/Iterator;
    //   74: astore_3
    //   75: aload_3
    //   76: invokeinterface hasNext : ()Z
    //   81: ifeq -> 147
    //   84: aload_3
    //   85: invokeinterface next : ()Ljava/lang/Object;
    //   90: checkcast java/util/Map$Entry
    //   93: astore #4
    //   95: aload #4
    //   97: invokeinterface getKey : ()Ljava/lang/Object;
    //   102: checkcast java/lang/String
    //   105: astore #5
    //   107: aload #4
    //   109: invokeinterface getValue : ()Ljava/lang/Object;
    //   114: checkcast com/flurry/org/codehaus/jackson/JsonNode
    //   117: astore #4
    //   119: aload_1
    //   120: aload #5
    //   122: invokevirtual get : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   125: astore #5
    //   127: aload #5
    //   129: ifnull -> 142
    //   132: aload #5
    //   134: aload #4
    //   136: invokevirtual equals : (Ljava/lang/Object;)Z
    //   139: ifne -> 75
    //   142: iconst_0
    //   143: istore_2
    //   144: goto -> 7
    //   147: iconst_1
    //   148: istore_2
    //   149: goto -> 7
  }
  
  public ObjectNode findParent(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: getfield _children : Ljava/util/LinkedHashMap;
    //   4: ifnull -> 82
    //   7: aload_0
    //   8: getfield _children : Ljava/util/LinkedHashMap;
    //   11: invokevirtual entrySet : ()Ljava/util/Set;
    //   14: invokeinterface iterator : ()Ljava/util/Iterator;
    //   19: astore_2
    //   20: aload_2
    //   21: invokeinterface hasNext : ()Z
    //   26: ifeq -> 82
    //   29: aload_2
    //   30: invokeinterface next : ()Ljava/lang/Object;
    //   35: checkcast java/util/Map$Entry
    //   38: astore_3
    //   39: aload_1
    //   40: aload_3
    //   41: invokeinterface getKey : ()Ljava/lang/Object;
    //   46: invokevirtual equals : (Ljava/lang/Object;)Z
    //   49: ifeq -> 56
    //   52: aload_0
    //   53: astore_1
    //   54: aload_1
    //   55: areturn
    //   56: aload_3
    //   57: invokeinterface getValue : ()Ljava/lang/Object;
    //   62: checkcast com/flurry/org/codehaus/jackson/JsonNode
    //   65: aload_1
    //   66: invokevirtual findParent : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   69: astore_3
    //   70: aload_3
    //   71: ifnull -> 20
    //   74: aload_3
    //   75: checkcast com/flurry/org/codehaus/jackson/node/ObjectNode
    //   78: astore_1
    //   79: goto -> 54
    //   82: aconst_null
    //   83: astore_1
    //   84: goto -> 54
  }
  
  public List findParents(String paramString, List<ObjectNode> paramList) {
    List<ObjectNode> list;
    if (this._children != null) {
      Iterator<Map.Entry> iterator = this._children.entrySet().iterator();
      while (true) {
        list = paramList;
        if (iterator.hasNext()) {
          Map.Entry entry = iterator.next();
          if (paramString.equals(entry.getKey())) {
            if (paramList == null)
              paramList = new ArrayList(); 
            paramList.add(this);
            continue;
          } 
          paramList = ((JsonNode)entry.getValue()).findParents(paramString, paramList);
          continue;
        } 
        break;
      } 
    } else {
      list = paramList;
    } 
    return list;
  }
  
  public JsonNode findValue(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: getfield _children : Ljava/util/LinkedHashMap;
    //   4: ifnull -> 87
    //   7: aload_0
    //   8: getfield _children : Ljava/util/LinkedHashMap;
    //   11: invokevirtual entrySet : ()Ljava/util/Set;
    //   14: invokeinterface iterator : ()Ljava/util/Iterator;
    //   19: astore_3
    //   20: aload_3
    //   21: invokeinterface hasNext : ()Z
    //   26: ifeq -> 87
    //   29: aload_3
    //   30: invokeinterface next : ()Ljava/lang/Object;
    //   35: checkcast java/util/Map$Entry
    //   38: astore_2
    //   39: aload_1
    //   40: aload_2
    //   41: invokeinterface getKey : ()Ljava/lang/Object;
    //   46: invokevirtual equals : (Ljava/lang/Object;)Z
    //   49: ifeq -> 64
    //   52: aload_2
    //   53: invokeinterface getValue : ()Ljava/lang/Object;
    //   58: checkcast com/flurry/org/codehaus/jackson/JsonNode
    //   61: astore_1
    //   62: aload_1
    //   63: areturn
    //   64: aload_2
    //   65: invokeinterface getValue : ()Ljava/lang/Object;
    //   70: checkcast com/flurry/org/codehaus/jackson/JsonNode
    //   73: aload_1
    //   74: invokevirtual findValue : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   77: astore_2
    //   78: aload_2
    //   79: ifnull -> 20
    //   82: aload_2
    //   83: astore_1
    //   84: goto -> 62
    //   87: aconst_null
    //   88: astore_1
    //   89: goto -> 62
  }
  
  public List findValues(String paramString, List paramList) {
    List list;
    if (this._children != null) {
      Iterator<Map.Entry> iterator = this._children.entrySet().iterator();
      while (true) {
        list = paramList;
        if (iterator.hasNext()) {
          Map.Entry entry = iterator.next();
          if (paramString.equals(entry.getKey())) {
            list = paramList;
            if (paramList == null)
              list = new ArrayList(); 
            list.add(entry.getValue());
            paramList = list;
            continue;
          } 
          paramList = ((JsonNode)entry.getValue()).findValues(paramString, paramList);
          continue;
        } 
        break;
      } 
    } else {
      list = paramList;
    } 
    return list;
  }
  
  public List findValuesAsText(String paramString, List<String> paramList) {
    List<String> list;
    if (this._children != null) {
      Iterator<Map.Entry> iterator = this._children.entrySet().iterator();
      while (true) {
        list = paramList;
        if (iterator.hasNext()) {
          Map.Entry entry = iterator.next();
          if (paramString.equals(entry.getKey())) {
            list = paramList;
            if (paramList == null)
              list = new ArrayList(); 
            list.add(((JsonNode)entry.getValue()).asText());
            paramList = list;
            continue;
          } 
          paramList = ((JsonNode)entry.getValue()).findValuesAsText(paramString, paramList);
          continue;
        } 
        break;
      } 
    } else {
      list = paramList;
    } 
    return list;
  }
  
  public JsonNode get(int paramInt) {
    return null;
  }
  
  public JsonNode get(String paramString) {
    return (this._children != null) ? (JsonNode)this._children.get(paramString) : null;
  }
  
  public Iterator getElements() {
    return (this._children == null) ? ContainerNode$NoNodesIterator.instance() : this._children.values().iterator();
  }
  
  public Iterator getFieldNames() {
    return (this._children == null) ? ContainerNode$NoStringsIterator.instance() : this._children.keySet().iterator();
  }
  
  public Iterator getFields() {
    return (this._children == null) ? ObjectNode$NoFieldsIterator.instance : this._children.entrySet().iterator();
  }
  
  public int hashCode() {
    return (this._children == null) ? -1 : this._children.hashCode();
  }
  
  public boolean isObject() {
    return true;
  }
  
  public JsonNode path(int paramInt) {
    return MissingNode.getInstance();
  }
  
  public JsonNode path(String paramString) {
    if (this._children != null) {
      JsonNode jsonNode = (JsonNode)this._children.get(paramString);
      if (jsonNode != null)
        return jsonNode; 
    } 
    return MissingNode.getInstance();
  }
  
  public JsonNode put(String paramString, JsonNode paramJsonNode) {
    JsonNode jsonNode = paramJsonNode;
    if (paramJsonNode == null)
      jsonNode = nullNode(); 
    return _put(paramString, jsonNode);
  }
  
  public void put(String paramString, double paramDouble) {
    _put(paramString, numberNode(paramDouble));
  }
  
  public void put(String paramString, float paramFloat) {
    _put(paramString, numberNode(paramFloat));
  }
  
  public void put(String paramString, int paramInt) {
    _put(paramString, numberNode(paramInt));
  }
  
  public void put(String paramString, long paramLong) {
    _put(paramString, numberNode(paramLong));
  }
  
  public void put(String paramString, Boolean paramBoolean) {
    if (paramBoolean == null) {
      _put(paramString, nullNode());
      return;
    } 
    _put(paramString, booleanNode(paramBoolean.booleanValue()));
  }
  
  public void put(String paramString, Double paramDouble) {
    if (paramDouble == null) {
      _put(paramString, nullNode());
      return;
    } 
    _put(paramString, numberNode(paramDouble.doubleValue()));
  }
  
  public void put(String paramString, Float paramFloat) {
    if (paramFloat == null) {
      _put(paramString, nullNode());
      return;
    } 
    _put(paramString, numberNode(paramFloat.floatValue()));
  }
  
  public void put(String paramString, Integer paramInteger) {
    if (paramInteger == null) {
      _put(paramString, nullNode());
      return;
    } 
    _put(paramString, numberNode(paramInteger.intValue()));
  }
  
  public void put(String paramString, Long paramLong) {
    if (paramLong == null) {
      _put(paramString, nullNode());
      return;
    } 
    _put(paramString, numberNode(paramLong.longValue()));
  }
  
  public void put(String paramString1, String paramString2) {
    if (paramString2 == null) {
      putNull(paramString1);
      return;
    } 
    _put(paramString1, textNode(paramString2));
  }
  
  public void put(String paramString, BigDecimal paramBigDecimal) {
    if (paramBigDecimal == null) {
      putNull(paramString);
      return;
    } 
    _put(paramString, numberNode(paramBigDecimal));
  }
  
  public void put(String paramString, boolean paramBoolean) {
    _put(paramString, booleanNode(paramBoolean));
  }
  
  public void put(String paramString, byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte == null) {
      _put(paramString, nullNode());
      return;
    } 
    _put(paramString, binaryNode(paramArrayOfbyte));
  }
  
  public JsonNode putAll(ObjectNode paramObjectNode) {
    int i = paramObjectNode.size();
    if (i > 0) {
      if (this._children == null)
        this._children = new LinkedHashMap<Object, Object>(i); 
      paramObjectNode.putContentsTo(this._children);
    } 
    return this;
  }
  
  public JsonNode putAll(Map<?, ?> paramMap) {
    if (this._children == null) {
      this._children = new LinkedHashMap<Object, Object>(paramMap);
      return this;
    } 
    Iterator<Map.Entry> iterator = paramMap.entrySet().iterator();
    while (true) {
      if (iterator.hasNext()) {
        Map.Entry entry = iterator.next();
        JsonNode jsonNode2 = (JsonNode)entry.getValue();
        JsonNode jsonNode1 = jsonNode2;
        if (jsonNode2 == null)
          jsonNode1 = nullNode(); 
        this._children.put(entry.getKey(), jsonNode1);
        continue;
      } 
      return this;
    } 
  }
  
  public ArrayNode putArray(String paramString) {
    ArrayNode arrayNode = arrayNode();
    _put(paramString, arrayNode);
    return arrayNode;
  }
  
  protected void putContentsTo(Map paramMap) {
    if (this._children != null)
      for (Map.Entry entry : this._children.entrySet())
        paramMap.put(entry.getKey(), entry.getValue());  
  }
  
  public void putNull(String paramString) {
    _put(paramString, nullNode());
  }
  
  public ObjectNode putObject(String paramString) {
    ObjectNode objectNode = objectNode();
    _put(paramString, objectNode);
    return objectNode;
  }
  
  public void putPOJO(String paramString, Object paramObject) {
    _put(paramString, POJONode(paramObject));
  }
  
  public JsonNode remove(String paramString) {
    return (this._children != null) ? (JsonNode)this._children.remove(paramString) : null;
  }
  
  public ObjectNode remove(Collection paramCollection) {
    if (this._children != null)
      for (String str : paramCollection)
        this._children.remove(str);  
    return this;
  }
  
  public ObjectNode removeAll() {
    this._children = null;
    return this;
  }
  
  public ObjectNode retain(Collection paramCollection) {
    if (this._children != null) {
      Iterator<Map.Entry> iterator = this._children.entrySet().iterator();
      while (iterator.hasNext()) {
        if (!paramCollection.contains(((Map.Entry)iterator.next()).getKey()))
          iterator.remove(); 
      } 
    } 
    return this;
  }
  
  public ObjectNode retain(String... paramVarArgs) {
    return retain(Arrays.asList(paramVarArgs));
  }
  
  public final void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeStartObject();
    if (this._children != null)
      for (Map.Entry entry : this._children.entrySet()) {
        paramJsonGenerator.writeFieldName((String)entry.getKey());
        ((BaseJsonNode)entry.getValue()).serialize(paramJsonGenerator, paramSerializerProvider);
      }  
    paramJsonGenerator.writeEndObject();
  }
  
  public void serializeWithType(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    paramTypeSerializer.writeTypePrefixForObject(this, paramJsonGenerator);
    if (this._children != null)
      for (Map.Entry entry : this._children.entrySet()) {
        paramJsonGenerator.writeFieldName((String)entry.getKey());
        ((BaseJsonNode)entry.getValue()).serialize(paramJsonGenerator, paramSerializerProvider);
      }  
    paramTypeSerializer.writeTypeSuffixForObject(this, paramJsonGenerator);
  }
  
  public int size() {
    return (this._children == null) ? 0 : this._children.size();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder((size() << 4) + 32);
    stringBuilder.append("{");
    if (this._children != null) {
      Iterator<Map.Entry> iterator = this._children.entrySet().iterator();
      for (byte b = 0; iterator.hasNext(); b++) {
        Map.Entry entry = iterator.next();
        if (b)
          stringBuilder.append(","); 
        TextNode.appendQuoted(stringBuilder, (String)entry.getKey());
        stringBuilder.append(':');
        stringBuilder.append(((JsonNode)entry.getValue()).toString());
      } 
    } 
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public ObjectNode with(String paramString) {
    if (this._children == null) {
      this._children = new LinkedHashMap<Object, Object>();
    } else {
      JsonNode jsonNode = (JsonNode)this._children.get(paramString);
      if (jsonNode != null) {
        if (jsonNode instanceof ObjectNode)
          return (ObjectNode)jsonNode; 
        throw new UnsupportedOperationException("Property '" + paramString + "' has value that is not of type ObjectNode (but " + jsonNode.getClass().getName() + ")");
      } 
    } 
    ObjectNode objectNode = objectNode();
    this._children.put(paramString, objectNode);
    return objectNode;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\ObjectNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */