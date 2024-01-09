package com.flurry.org.apache.avro.data;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.Decoder;
import com.flurry.org.apache.avro.io.Encoder;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.node.ArrayNode;
import com.flurry.org.codehaus.jackson.node.BooleanNode;
import com.flurry.org.codehaus.jackson.node.DoubleNode;
import com.flurry.org.codehaus.jackson.node.JsonNodeFactory;
import com.flurry.org.codehaus.jackson.node.LongNode;
import com.flurry.org.codehaus.jackson.node.NullNode;
import com.flurry.org.codehaus.jackson.node.ObjectNode;
import com.flurry.org.codehaus.jackson.node.TextNode;
import java.io.IOException;
import java.util.Iterator;

public class Json {
  public static final Schema SCHEMA;
  
  static {
    try {
      SCHEMA = Schema.parse(Json.class.getResourceAsStream("/com/flurry/org/apache/avro/data/Json.avsc"));
      return;
    } catch (IOException iOException) {
      throw new AvroRuntimeException(iOException);
    } 
  }
  
  public static JsonNode read(Decoder paramDecoder) {
    ArrayNode arrayNode;
    switch (Json$1.$SwitchMap$org$apache$avro$data$Json$JsonType[Json$JsonType.values()[paramDecoder.readIndex()].ordinal()]) {
      default:
        throw new AvroRuntimeException("Unexpected Json node type");
      case 1:
        return (JsonNode)new LongNode(paramDecoder.readLong());
      case 2:
        return (JsonNode)new DoubleNode(paramDecoder.readDouble());
      case 3:
        return (JsonNode)new TextNode(paramDecoder.readString());
      case 4:
        return (JsonNode)(paramDecoder.readBoolean() ? BooleanNode.TRUE : BooleanNode.FALSE);
      case 5:
        paramDecoder.readNull();
        return (JsonNode)NullNode.getInstance();
      case 6:
        arrayNode = JsonNodeFactory.instance.arrayNode();
        l = paramDecoder.readArrayStart();
        while (true) {
          ArrayNode arrayNode1 = arrayNode;
          if (l > 0L) {
            for (long l1 = 0L; l1 < l; l1++)
              arrayNode.add(read(paramDecoder)); 
            l = paramDecoder.arrayNext();
            continue;
          } 
          return (JsonNode)arrayNode1;
        } 
      case 7:
        break;
    } 
    ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
    long l = paramDecoder.readMapStart();
    while (true) {
      ObjectNode objectNode1 = objectNode;
      if (l > 0L) {
        for (long l1 = 0L; l1 < l; l1++)
          objectNode.put(paramDecoder.readString(), read(paramDecoder)); 
        l = paramDecoder.mapNext();
        continue;
      } 
      return (JsonNode)objectNode1;
    } 
  }
  
  public static void write(JsonNode paramJsonNode, Encoder paramEncoder) {
    switch (Json$1.$SwitchMap$org$codehaus$jackson$JsonToken[paramJsonNode.asToken().ordinal()]) {
      default:
        throw new AvroRuntimeException(paramJsonNode.asToken() + " unexpected: " + paramJsonNode);
      case 1:
        paramEncoder.writeIndex(Json$JsonType.LONG.ordinal());
        paramEncoder.writeLong(paramJsonNode.getLongValue());
        return;
      case 2:
        paramEncoder.writeIndex(Json$JsonType.DOUBLE.ordinal());
        paramEncoder.writeDouble(paramJsonNode.getDoubleValue());
        return;
      case 3:
        paramEncoder.writeIndex(Json$JsonType.STRING.ordinal());
        paramEncoder.writeString(paramJsonNode.getTextValue());
        return;
      case 4:
        paramEncoder.writeIndex(Json$JsonType.BOOLEAN.ordinal());
        paramEncoder.writeBoolean(true);
        return;
      case 5:
        paramEncoder.writeIndex(Json$JsonType.BOOLEAN.ordinal());
        paramEncoder.writeBoolean(false);
        return;
      case 6:
        paramEncoder.writeIndex(Json$JsonType.NULL.ordinal());
        paramEncoder.writeNull();
        return;
      case 7:
        paramEncoder.writeIndex(Json$JsonType.ARRAY.ordinal());
        paramEncoder.writeArrayStart();
        paramEncoder.setItemCount(paramJsonNode.size());
        for (JsonNode jsonNode : paramJsonNode) {
          paramEncoder.startItem();
          write(jsonNode, paramEncoder);
        } 
        paramEncoder.writeArrayEnd();
        return;
      case 8:
        break;
    } 
    paramEncoder.writeIndex(Json$JsonType.OBJECT.ordinal());
    paramEncoder.writeMapStart();
    paramEncoder.setItemCount(paramJsonNode.size());
    Iterator<String> iterator = paramJsonNode.getFieldNames();
    while (iterator.hasNext()) {
      paramEncoder.startItem();
      String str = iterator.next();
      paramEncoder.writeString(str);
      write(paramJsonNode.get(str), paramEncoder);
    } 
    paramEncoder.writeMapEnd();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\data\Json.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */