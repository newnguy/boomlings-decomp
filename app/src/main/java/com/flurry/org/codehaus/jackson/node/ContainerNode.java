package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonToken;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public abstract class ContainerNode extends BaseJsonNode {
    JsonNodeFactory _nodeFactory;

    /* loaded from: classes.dex */
    public class NoNodesIterator implements Iterator {
        static final NoNodesIterator instance = new NoNodesIterator();

        private NoNodesIterator() {
        }

        public static NoNodesIterator instance() {
            return instance;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public JsonNode next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new IllegalStateException();
        }
    }

    /* loaded from: classes.dex */
    public class NoStringsIterator implements Iterator {
        static final NoStringsIterator instance = new NoStringsIterator();

        private NoStringsIterator() {
        }

        public static NoStringsIterator instance() {
            return instance;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public String next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new IllegalStateException();
        }
    }

    public ContainerNode(JsonNodeFactory jsonNodeFactory) {
        this._nodeFactory = jsonNodeFactory;
    }

    public final POJONode POJONode(Object obj) {
        return this._nodeFactory.POJONode(obj);
    }

    public final ArrayNode arrayNode() {
        return this._nodeFactory.arrayNode();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public String asText() {
        return "";
    }

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public abstract JsonToken asToken();

    public final BinaryNode binaryNode(byte[] bArr) {
        return this._nodeFactory.binaryNode(bArr);
    }

    public final BinaryNode binaryNode(byte[] bArr, int i, int i2) {
        return this._nodeFactory.binaryNode(bArr, i, i2);
    }

    public final BooleanNode booleanNode(boolean z) {
        return this._nodeFactory.booleanNode(z);
    }

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public abstract ObjectNode findParent(String str);

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public abstract List findParents(String str, List list);

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public abstract JsonNode findValue(String str);

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public abstract List findValues(String str, List list);

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public abstract List findValuesAsText(String str, List list);

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public abstract JsonNode get(int i);

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public abstract JsonNode get(String str);

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public String getValueAsText() {
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean isContainerNode() {
        return true;
    }

    public final NullNode nullNode() {
        return this._nodeFactory.nullNode();
    }

    public final NumericNode numberNode(byte b) {
        return this._nodeFactory.numberNode(b);
    }

    public final NumericNode numberNode(double d) {
        return this._nodeFactory.numberNode(d);
    }

    public final NumericNode numberNode(float f) {
        return this._nodeFactory.numberNode(f);
    }

    public final NumericNode numberNode(int i) {
        return this._nodeFactory.numberNode(i);
    }

    public final NumericNode numberNode(long j) {
        return this._nodeFactory.numberNode(j);
    }

    public final NumericNode numberNode(BigDecimal bigDecimal) {
        return this._nodeFactory.numberNode(bigDecimal);
    }

    public final NumericNode numberNode(short s) {
        return this._nodeFactory.numberNode(s);
    }

    public final ObjectNode objectNode() {
        return this._nodeFactory.objectNode();
    }

    public abstract ContainerNode removeAll();

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public abstract int size();

    public final TextNode textNode(String str) {
        return this._nodeFactory.textNode(str);
    }
}
