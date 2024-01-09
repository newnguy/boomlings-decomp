package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.node.ContainerNode;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public class ObjectNode extends ContainerNode {
    protected LinkedHashMap _children;

    /* loaded from: classes.dex */
    public class NoFieldsIterator implements Iterator {
        static final NoFieldsIterator instance = new NoFieldsIterator();

        private NoFieldsIterator() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public Map.Entry next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new IllegalStateException();
        }
    }

    public ObjectNode(JsonNodeFactory jsonNodeFactory) {
        super(jsonNodeFactory);
        this._children = null;
    }

    private final JsonNode _put(String str, JsonNode jsonNode) {
        if (this._children == null) {
            this._children = new LinkedHashMap();
        }
        return (JsonNode) this._children.put(str, jsonNode);
    }

    @Override // com.flurry.org.codehaus.jackson.node.ContainerNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.START_OBJECT;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0038  */
    @Override // com.flurry.org.codehaus.jackson.JsonNode
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r2 = 1
            r3 = 0
            if (r6 != r5) goto L6
            r0 = r2
        L5:
            return r0
        L6:
            if (r6 != 0) goto La
            r0 = r3
            goto L5
        La:
            java.lang.Class r0 = r6.getClass()
            java.lang.Class r1 = r5.getClass()
            if (r0 == r1) goto L16
            r0 = r3
            goto L5
        L16:
            com.flurry.org.codehaus.jackson.node.ObjectNode r6 = (com.flurry.org.codehaus.jackson.node.ObjectNode) r6
            int r0 = r6.size()
            int r1 = r5.size()
            if (r0 == r1) goto L24
            r0 = r3
            goto L5
        L24:
            java.util.LinkedHashMap r0 = r5._children
            if (r0 == 0) goto L58
            java.util.LinkedHashMap r0 = r5._children
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r4 = r0.iterator()
        L32:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L58
            java.lang.Object r0 = r4.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r1 = r0.getKey()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r0 = r0.getValue()
            com.flurry.org.codehaus.jackson.JsonNode r0 = (com.flurry.org.codehaus.jackson.JsonNode) r0
            com.flurry.org.codehaus.jackson.JsonNode r1 = r6.get(r1)
            if (r1 == 0) goto L56
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L32
        L56:
            r0 = r3
            goto L5
        L58:
            r0 = r2
            goto L5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.node.ObjectNode.equals(java.lang.Object):boolean");
    }

    @Override // com.flurry.org.codehaus.jackson.node.ContainerNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public ObjectNode findParent(String str) {
        if (this._children != null) {
            for (Map.Entry entry : this._children.entrySet()) {
                if (str.equals(entry.getKey())) {
                    return this;
                }
                JsonNode findParent = ((JsonNode) entry.getValue()).findParent(str);
                if (findParent != null) {
                    return (ObjectNode) findParent;
                }
            }
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.node.ContainerNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public List findParents(String str, List list) {
        List findParents;
        if (this._children != null) {
            List list2 = list;
            for (Map.Entry entry : this._children.entrySet()) {
                if (str.equals(entry.getKey())) {
                    findParents = list2 == null ? new ArrayList() : list2;
                    findParents.add(this);
                } else {
                    findParents = ((JsonNode) entry.getValue()).findParents(str, list2);
                }
                list2 = findParents;
            }
            return list2;
        }
        return list;
    }

    @Override // com.flurry.org.codehaus.jackson.node.ContainerNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public JsonNode findValue(String str) {
        if (this._children != null) {
            for (Map.Entry entry : this._children.entrySet()) {
                if (str.equals(entry.getKey())) {
                    return (JsonNode) entry.getValue();
                }
                JsonNode findValue = ((JsonNode) entry.getValue()).findValue(str);
                if (findValue != null) {
                    return findValue;
                }
            }
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.node.ContainerNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public List findValues(String str, List list) {
        if (this._children != null) {
            List list2 = list;
            for (Map.Entry entry : this._children.entrySet()) {
                if (str.equals(entry.getKey())) {
                    if (list2 == null) {
                        list2 = new ArrayList();
                    }
                    list2.add(entry.getValue());
                } else {
                    list2 = ((JsonNode) entry.getValue()).findValues(str, list2);
                }
            }
            return list2;
        }
        return list;
    }

    @Override // com.flurry.org.codehaus.jackson.node.ContainerNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public List findValuesAsText(String str, List list) {
        if (this._children != null) {
            List list2 = list;
            for (Map.Entry entry : this._children.entrySet()) {
                if (str.equals(entry.getKey())) {
                    if (list2 == null) {
                        list2 = new ArrayList();
                    }
                    list2.add(((JsonNode) entry.getValue()).asText());
                } else {
                    list2 = ((JsonNode) entry.getValue()).findValuesAsText(str, list2);
                }
            }
            return list2;
        }
        return list;
    }

    @Override // com.flurry.org.codehaus.jackson.node.ContainerNode, com.flurry.org.codehaus.jackson.JsonNode
    public JsonNode get(int i) {
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.node.ContainerNode, com.flurry.org.codehaus.jackson.JsonNode
    public JsonNode get(String str) {
        if (this._children != null) {
            return (JsonNode) this._children.get(str);
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public Iterator getElements() {
        return this._children == null ? ContainerNode.NoNodesIterator.instance() : this._children.values().iterator();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public Iterator getFieldNames() {
        return this._children == null ? ContainerNode.NoStringsIterator.instance() : this._children.keySet().iterator();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public Iterator getFields() {
        return this._children == null ? NoFieldsIterator.instance : this._children.entrySet().iterator();
    }

    public int hashCode() {
        if (this._children == null) {
            return -1;
        }
        return this._children.hashCode();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean isObject() {
        return true;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public JsonNode path(int i) {
        return MissingNode.getInstance();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public JsonNode path(String str) {
        JsonNode jsonNode;
        return (this._children == null || (jsonNode = (JsonNode) this._children.get(str)) == null) ? MissingNode.getInstance() : jsonNode;
    }

    public JsonNode put(String str, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        return _put(str, jsonNode);
    }

    public void put(String str, double d) {
        _put(str, numberNode(d));
    }

    public void put(String str, float f) {
        _put(str, numberNode(f));
    }

    public void put(String str, int i) {
        _put(str, numberNode(i));
    }

    public void put(String str, long j) {
        _put(str, numberNode(j));
    }

    public void put(String str, Boolean bool) {
        if (bool == null) {
            _put(str, nullNode());
        } else {
            _put(str, booleanNode(bool.booleanValue()));
        }
    }

    public void put(String str, Double d) {
        if (d == null) {
            _put(str, nullNode());
        } else {
            _put(str, numberNode(d.doubleValue()));
        }
    }

    public void put(String str, Float f) {
        if (f == null) {
            _put(str, nullNode());
        } else {
            _put(str, numberNode(f.floatValue()));
        }
    }

    public void put(String str, Integer num) {
        if (num == null) {
            _put(str, nullNode());
        } else {
            _put(str, numberNode(num.intValue()));
        }
    }

    public void put(String str, Long l) {
        if (l == null) {
            _put(str, nullNode());
        } else {
            _put(str, numberNode(l.longValue()));
        }
    }

    public void put(String str, String str2) {
        if (str2 == null) {
            putNull(str);
        } else {
            _put(str, textNode(str2));
        }
    }

    public void put(String str, BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            putNull(str);
        } else {
            _put(str, numberNode(bigDecimal));
        }
    }

    public void put(String str, boolean z) {
        _put(str, booleanNode(z));
    }

    public void put(String str, byte[] bArr) {
        if (bArr == null) {
            _put(str, nullNode());
        } else {
            _put(str, binaryNode(bArr));
        }
    }

    public JsonNode putAll(ObjectNode objectNode) {
        int size = objectNode.size();
        if (size > 0) {
            if (this._children == null) {
                this._children = new LinkedHashMap(size);
            }
            objectNode.putContentsTo(this._children);
        }
        return this;
    }

    public JsonNode putAll(Map map) {
        if (this._children == null) {
            this._children = new LinkedHashMap(map);
        } else {
            for (Map.Entry entry : map.entrySet()) {
                Object obj = (JsonNode) entry.getValue();
                if (obj == null) {
                    obj = nullNode();
                }
                this._children.put(entry.getKey(), obj);
            }
        }
        return this;
    }

    public ArrayNode putArray(String str) {
        ArrayNode arrayNode = arrayNode();
        _put(str, arrayNode);
        return arrayNode;
    }

    protected void putContentsTo(Map map) {
        if (this._children != null) {
            for (Map.Entry entry : this._children.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public void putNull(String str) {
        _put(str, nullNode());
    }

    public ObjectNode putObject(String str) {
        ObjectNode objectNode = objectNode();
        _put(str, objectNode);
        return objectNode;
    }

    public void putPOJO(String str, Object obj) {
        _put(str, POJONode(obj));
    }

    public JsonNode remove(String str) {
        if (this._children != null) {
            return (JsonNode) this._children.remove(str);
        }
        return null;
    }

    public ObjectNode remove(Collection collection) {
        if (this._children != null) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                this._children.remove((String) it.next());
            }
        }
        return this;
    }

    @Override // com.flurry.org.codehaus.jackson.node.ContainerNode
    public ObjectNode removeAll() {
        this._children = null;
        return this;
    }

    public ObjectNode retain(Collection collection) {
        if (this._children != null) {
            Iterator it = this._children.entrySet().iterator();
            while (it.hasNext()) {
                if (!collection.contains(((Map.Entry) it.next()).getKey())) {
                    it.remove();
                }
            }
        }
        return this;
    }

    public ObjectNode retain(String... strArr) {
        return retain(Arrays.asList(strArr));
    }

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeStartObject();
        if (this._children != null) {
            for (Map.Entry entry : this._children.entrySet()) {
                jsonGenerator.writeFieldName((String) entry.getKey());
                ((BaseJsonNode) entry.getValue()).serialize(jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.map.JsonSerializableWithType
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForObject(this, jsonGenerator);
        if (this._children != null) {
            for (Map.Entry entry : this._children.entrySet()) {
                jsonGenerator.writeFieldName((String) entry.getKey());
                ((BaseJsonNode) entry.getValue()).serialize(jsonGenerator, serializerProvider);
            }
        }
        typeSerializer.writeTypeSuffixForObject(this, jsonGenerator);
    }

    @Override // com.flurry.org.codehaus.jackson.node.ContainerNode, com.flurry.org.codehaus.jackson.JsonNode
    public int size() {
        if (this._children == null) {
            return 0;
        }
        return this._children.size();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public String toString() {
        StringBuilder sb = new StringBuilder((size() << 4) + 32);
        sb.append("{");
        if (this._children != null) {
            int i = 0;
            for (Map.Entry entry : this._children.entrySet()) {
                if (i > 0) {
                    sb.append(",");
                }
                TextNode.appendQuoted(sb, (String) entry.getKey());
                sb.append(':');
                sb.append(((JsonNode) entry.getValue()).toString());
                i++;
            }
        }
        sb.append("}");
        return sb.toString();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public ObjectNode with(String str) {
        if (this._children == null) {
            this._children = new LinkedHashMap();
        } else {
            JsonNode jsonNode = (JsonNode) this._children.get(str);
            if (jsonNode != null) {
                if (jsonNode instanceof ObjectNode) {
                    return (ObjectNode) jsonNode;
                }
                throw new UnsupportedOperationException("Property '" + str + "' has value that is not of type ObjectNode (but " + jsonNode.getClass().getName() + ")");
            }
        }
        ObjectNode objectNode = objectNode();
        this._children.put(str, objectNode);
        return objectNode;
    }
}
