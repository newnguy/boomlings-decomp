package com.flurry.org.apache.avro;

import com.flurry.org.codehaus.jackson.JsonNode;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Schema$Field {
  private Set aliases;
  
  private final JsonNode defaultValue;
  
  private final String doc;
  
  private final String name;
  
  private final Schema$Field$Order order;
  
  private transient int position = -1;
  
  private final Schema$Props props = new Schema$Props(Schema.access$100());
  
  private final Schema schema;
  
  public Schema$Field(String paramString1, Schema paramSchema, String paramString2, JsonNode paramJsonNode) {
    this(paramString1, paramSchema, paramString2, paramJsonNode, Schema$Field$Order.ASCENDING);
  }
  
  public Schema$Field(String paramString1, Schema paramSchema, String paramString2, JsonNode paramJsonNode, Schema$Field$Order paramSchema$Field$Order) {
    this.name = Schema.access$200(paramString1);
    this.schema = paramSchema;
    this.doc = paramString2;
    this.defaultValue = paramJsonNode;
    this.order = paramSchema$Field$Order;
  }
  
  private boolean defaultValueEquals(JsonNode paramJsonNode) {
    return (this.defaultValue == null) ? ((paramJsonNode == null)) : (Double.isNaN(this.defaultValue.getValueAsDouble()) ? Double.isNaN(paramJsonNode.getValueAsDouble()) : this.defaultValue.equals(paramJsonNode));
  }
  
  public void addAlias(String paramString) {
    if (this.aliases == null)
      this.aliases = new LinkedHashSet(); 
    this.aliases.add(paramString);
  }
  
  public void addProp(String paramString1, String paramString2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield props : Lcom/flurry/org/apache/avro/Schema$Props;
    //   6: aload_1
    //   7: aload_2
    //   8: invokevirtual add : (Ljava/lang/String;Ljava/lang/String;)V
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: astore_1
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_1
    //   18: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	14	finally
  }
  
  public Set aliases() {
    return (this.aliases == null) ? Collections.emptySet() : Collections.unmodifiableSet(this.aliases);
  }
  
  public JsonNode defaultValue() {
    return this.defaultValue;
  }
  
  public String doc() {
    return this.doc;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (!(paramObject instanceof Schema$Field))
        return false; 
      paramObject = paramObject;
      if (!this.name.equals(((Schema$Field)paramObject).name) || !this.schema.equals(((Schema$Field)paramObject).schema) || !defaultValueEquals(((Schema$Field)paramObject).defaultValue) || !this.props.equals(((Schema$Field)paramObject).props))
        bool = false; 
    } 
    return bool;
  }
  
  public String getProp(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield props : Lcom/flurry/org/apache/avro/Schema$Props;
    //   6: aload_1
    //   7: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   10: checkcast java/lang/String
    //   13: astore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: areturn
    //   18: astore_1
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	18	finally
  }
  
  public int hashCode() {
    return this.name.hashCode() + this.schema.computeHash();
  }
  
  public String name() {
    return this.name;
  }
  
  public Schema$Field$Order order() {
    return this.order;
  }
  
  public int pos() {
    return this.position;
  }
  
  public Map props() {
    return Collections.unmodifiableMap(this.props);
  }
  
  public Schema schema() {
    return this.schema;
  }
  
  public String toString() {
    return this.name + " type:" + Schema.access$300(this.schema) + " pos:" + this.position;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\Schema$Field.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */