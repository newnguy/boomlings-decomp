package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.node.ContainerNode;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class ArrayNode extends ContainerNode {
    protected ArrayList _children;

    public ArrayNode(JsonNodeFactory jsonNodeFactory) {
        super(jsonNodeFactory);
    }

    private void _add(JsonNode jsonNode) {
        if (this._children == null) {
            this._children = new ArrayList();
        }
        this._children.add(jsonNode);
    }

    private void _insert(int i, JsonNode jsonNode) {
        if (this._children == null) {
            this._children = new ArrayList();
            this._children.add(jsonNode);
        } else if (i < 0) {
            this._children.add(0, jsonNode);
        } else if (i >= this._children.size()) {
            this._children.add(jsonNode);
        } else {
            this._children.add(i, jsonNode);
        }
    }

    private boolean _sameChildren(ArrayList arrayList) {
        int size = arrayList.size();
        if (size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!((JsonNode) this._children.get(i)).equals(arrayList.get(i))) {
                return false;
            }
        }
        return true;
    }

    public JsonNode _set(int i, JsonNode jsonNode) {
        if (this._children == null || i < 0 || i >= this._children.size()) {
            throw new IndexOutOfBoundsException("Illegal index " + i + ", array size " + size());
        }
        return (JsonNode) this._children.set(i, jsonNode);
    }

    public void add(double d) {
        _add(numberNode(d));
    }

    public void add(float f) {
        _add(numberNode(f));
    }

    public void add(int i) {
        _add(numberNode(i));
    }

    public void add(long j) {
        _add(numberNode(j));
    }

    public void add(JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        _add(jsonNode);
    }

    public void add(Boolean bool) {
        if (bool == null) {
            addNull();
        } else {
            _add(booleanNode(bool.booleanValue()));
        }
    }

    public void add(Double d) {
        if (d == null) {
            addNull();
        } else {
            _add(numberNode(d.doubleValue()));
        }
    }

    public void add(Float f) {
        if (f == null) {
            addNull();
        } else {
            _add(numberNode(f.floatValue()));
        }
    }

    public void add(Integer num) {
        if (num == null) {
            addNull();
        } else {
            _add(numberNode(num.intValue()));
        }
    }

    public void add(Long l) {
        if (l == null) {
            addNull();
        } else {
            _add(numberNode(l.longValue()));
        }
    }

    public void add(String str) {
        if (str == null) {
            addNull();
        } else {
            _add(textNode(str));
        }
    }

    public void add(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            addNull();
        } else {
            _add(numberNode(bigDecimal));
        }
    }

    public void add(boolean z) {
        _add(booleanNode(z));
    }

    public void add(byte[] bArr) {
        if (bArr == null) {
            addNull();
        } else {
            _add(binaryNode(bArr));
        }
    }

    public JsonNode addAll(ArrayNode arrayNode) {
        int size = arrayNode.size();
        if (size > 0) {
            if (this._children == null) {
                this._children = new ArrayList(size + 2);
            }
            arrayNode.addContentsTo(this._children);
        }
        return this;
    }

    public JsonNode addAll(Collection collection) {
        if (collection.size() > 0) {
            if (this._children == null) {
                this._children = new ArrayList(collection);
            } else {
                this._children.addAll(collection);
            }
        }
        return this;
    }

    public ArrayNode addArray() {
        ArrayNode arrayNode = arrayNode();
        _add(arrayNode);
        return arrayNode;
    }

    protected void addContentsTo(List list) {
        if (this._children != null) {
            Iterator it = this._children.iterator();
            while (it.hasNext()) {
                list.add((JsonNode) it.next());
            }
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

    public void addPOJO(Object obj) {
        if (obj == null) {
            addNull();
        } else {
            _add(POJONode(obj));
        }
    }

    @Override // com.flurry.org.codehaus.jackson.node.ContainerNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.START_ARRAY;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            ArrayNode arrayNode = (ArrayNode) obj;
            return (this._children == null || this._children.size() == 0) ? arrayNode.size() == 0 : arrayNode._sameChildren(this._children);
        }
        return false;
    }

    @Override // com.flurry.org.codehaus.jackson.node.ContainerNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public ObjectNode findParent(String str) {
        if (this._children != null) {
            Iterator it = this._children.iterator();
            while (it.hasNext()) {
                JsonNode findParent = ((JsonNode) it.next()).findParent(str);
                if (findParent != null) {
                    return (ObjectNode) findParent;
                }
            }
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.node.ContainerNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public List findParents(String str, List list) {
        if (this._children != null) {
            Iterator it = this._children.iterator();
            while (it.hasNext()) {
                list = ((JsonNode) it.next()).findParents(str, list);
            }
        }
        return list;
    }

    @Override // com.flurry.org.codehaus.jackson.node.ContainerNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public JsonNode findValue(String str) {
        if (this._children != null) {
            Iterator it = this._children.iterator();
            while (it.hasNext()) {
                JsonNode findValue = ((JsonNode) it.next()).findValue(str);
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
            Iterator it = this._children.iterator();
            while (it.hasNext()) {
                list = ((JsonNode) it.next()).findValues(str, list);
            }
        }
        return list;
    }

    @Override // com.flurry.org.codehaus.jackson.node.ContainerNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public List findValuesAsText(String str, List list) {
        if (this._children != null) {
            Iterator it = this._children.iterator();
            while (it.hasNext()) {
                list = ((JsonNode) it.next()).findValuesAsText(str, list);
            }
        }
        return list;
    }

    @Override // com.flurry.org.codehaus.jackson.node.ContainerNode, com.flurry.org.codehaus.jackson.JsonNode
    public JsonNode get(int i) {
        if (i < 0 || this._children == null || i >= this._children.size()) {
            return null;
        }
        return (JsonNode) this._children.get(i);
    }

    @Override // com.flurry.org.codehaus.jackson.node.ContainerNode, com.flurry.org.codehaus.jackson.JsonNode
    public JsonNode get(String str) {
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public Iterator getElements() {
        return this._children == null ? ContainerNode.NoNodesIterator.instance() : this._children.iterator();
    }

    public int hashCode() {
        if (this._children == null) {
            return 1;
        }
        int size = this._children.size();
        Iterator it = this._children.iterator();
        while (true) {
            int i = size;
            if (!it.hasNext()) {
                return i;
            }
            JsonNode jsonNode = (JsonNode) it.next();
            size = jsonNode != null ? jsonNode.hashCode() ^ i : i;
        }
    }

    public void insert(int i, double d) {
        _insert(i, numberNode(d));
    }

    public void insert(int i, float f) {
        _insert(i, numberNode(f));
    }

    public void insert(int i, int i2) {
        _insert(i, numberNode(i2));
    }

    public void insert(int i, long j) {
        _insert(i, numberNode(j));
    }

    public void insert(int i, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        _insert(i, jsonNode);
    }

    public void insert(int i, Boolean bool) {
        if (bool == null) {
            insertNull(i);
        } else {
            _insert(i, booleanNode(bool.booleanValue()));
        }
    }

    public void insert(int i, Double d) {
        if (d == null) {
            insertNull(i);
        } else {
            _insert(i, numberNode(d.doubleValue()));
        }
    }

    public void insert(int i, Float f) {
        if (f == null) {
            insertNull(i);
        } else {
            _insert(i, numberNode(f.floatValue()));
        }
    }

    public void insert(int i, Integer num) {
        if (num == null) {
            insertNull(i);
        } else {
            _insert(i, numberNode(num.intValue()));
        }
    }

    public void insert(int i, Long l) {
        if (l == null) {
            insertNull(i);
        } else {
            _insert(i, numberNode(l.longValue()));
        }
    }

    public void insert(int i, String str) {
        if (str == null) {
            insertNull(i);
        } else {
            _insert(i, textNode(str));
        }
    }

    public void insert(int i, BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            insertNull(i);
        } else {
            _insert(i, numberNode(bigDecimal));
        }
    }

    public void insert(int i, boolean z) {
        _insert(i, booleanNode(z));
    }

    public void insert(int i, byte[] bArr) {
        if (bArr == null) {
            insertNull(i);
        } else {
            _insert(i, binaryNode(bArr));
        }
    }

    public ArrayNode insertArray(int i) {
        ArrayNode arrayNode = arrayNode();
        _insert(i, arrayNode);
        return arrayNode;
    }

    public void insertNull(int i) {
        _insert(i, nullNode());
    }

    public ObjectNode insertObject(int i) {
        ObjectNode objectNode = objectNode();
        _insert(i, objectNode);
        return objectNode;
    }

    public void insertPOJO(int i, Object obj) {
        if (obj == null) {
            insertNull(i);
        } else {
            _insert(i, POJONode(obj));
        }
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean isArray() {
        return true;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public JsonNode path(int i) {
        return (i < 0 || this._children == null || i >= this._children.size()) ? MissingNode.getInstance() : (JsonNode) this._children.get(i);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public JsonNode path(String str) {
        return MissingNode.getInstance();
    }

    public JsonNode remove(int i) {
        if (i < 0 || this._children == null || i >= this._children.size()) {
            return null;
        }
        return (JsonNode) this._children.remove(i);
    }

    @Override // com.flurry.org.codehaus.jackson.node.ContainerNode
    public ArrayNode removeAll() {
        this._children = null;
        return this;
    }

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeStartArray();
        if (this._children != null) {
            Iterator it = this._children.iterator();
            while (it.hasNext()) {
                ((BaseJsonNode) ((JsonNode) it.next())).serialize(jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndArray();
    }

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.map.JsonSerializableWithType
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForArray(this, jsonGenerator);
        if (this._children != null) {
            Iterator it = this._children.iterator();
            while (it.hasNext()) {
                ((BaseJsonNode) ((JsonNode) it.next())).serialize(jsonGenerator, serializerProvider);
            }
        }
        typeSerializer.writeTypeSuffixForArray(this, jsonGenerator);
    }

    public JsonNode set(int i, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        return _set(i, jsonNode);
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
        StringBuilder sb = new StringBuilder((size() << 4) + 16);
        sb.append('[');
        if (this._children != null) {
            int size = this._children.size();
            for (int i = 0; i < size; i++) {
                if (i > 0) {
                    sb.append(',');
                }
                sb.append(((JsonNode) this._children.get(i)).toString());
            }
        }
        sb.append(']');
        return sb.toString();
    }
}
