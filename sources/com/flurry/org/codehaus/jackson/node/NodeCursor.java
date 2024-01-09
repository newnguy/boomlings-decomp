package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonStreamContext;
import com.flurry.org.codehaus.jackson.JsonToken;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
abstract class NodeCursor extends JsonStreamContext {
    final NodeCursor _parent;

    /* loaded from: classes.dex */
    public final class Array extends NodeCursor {
        Iterator _contents;
        JsonNode _currentNode;

        public Array(JsonNode jsonNode, NodeCursor nodeCursor) {
            super(1, nodeCursor);
            this._contents = jsonNode.getElements();
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor
        public boolean currentHasChildren() {
            return ((ContainerNode) currentNode()).size() > 0;
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor
        public JsonNode currentNode() {
            return this._currentNode;
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor
        public JsonToken endToken() {
            return JsonToken.END_ARRAY;
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor, com.flurry.org.codehaus.jackson.JsonStreamContext
        public String getCurrentName() {
            return null;
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor, com.flurry.org.codehaus.jackson.JsonStreamContext
        public /* bridge */ /* synthetic */ JsonStreamContext getParent() {
            return super.getParent();
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor
        public JsonToken nextToken() {
            if (this._contents.hasNext()) {
                this._currentNode = (JsonNode) this._contents.next();
                return this._currentNode.asToken();
            }
            this._currentNode = null;
            return null;
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor
        public JsonToken nextValue() {
            return nextToken();
        }
    }

    /* loaded from: classes.dex */
    public final class Object extends NodeCursor {
        Iterator _contents;
        Map.Entry _current;
        boolean _needEntry;

        public Object(JsonNode jsonNode, NodeCursor nodeCursor) {
            super(2, nodeCursor);
            this._contents = ((ObjectNode) jsonNode).getFields();
            this._needEntry = true;
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor
        public boolean currentHasChildren() {
            return ((ContainerNode) currentNode()).size() > 0;
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor
        public JsonNode currentNode() {
            if (this._current == null) {
                return null;
            }
            return (JsonNode) this._current.getValue();
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor
        public JsonToken endToken() {
            return JsonToken.END_OBJECT;
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor, com.flurry.org.codehaus.jackson.JsonStreamContext
        public String getCurrentName() {
            if (this._current == null) {
                return null;
            }
            return (String) this._current.getKey();
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor, com.flurry.org.codehaus.jackson.JsonStreamContext
        public /* bridge */ /* synthetic */ JsonStreamContext getParent() {
            return super.getParent();
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor
        public JsonToken nextToken() {
            if (!this._needEntry) {
                this._needEntry = true;
                return ((JsonNode) this._current.getValue()).asToken();
            } else if (!this._contents.hasNext()) {
                this._current = null;
                return null;
            } else {
                this._needEntry = false;
                this._current = (Map.Entry) this._contents.next();
                return JsonToken.FIELD_NAME;
            }
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor
        public JsonToken nextValue() {
            JsonToken nextToken = nextToken();
            return nextToken == JsonToken.FIELD_NAME ? nextToken() : nextToken;
        }
    }

    /* loaded from: classes.dex */
    public final class RootValue extends NodeCursor {
        protected boolean _done;
        JsonNode _node;

        public RootValue(JsonNode jsonNode, NodeCursor nodeCursor) {
            super(0, nodeCursor);
            this._done = false;
            this._node = jsonNode;
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor
        public boolean currentHasChildren() {
            return false;
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor
        public JsonNode currentNode() {
            return this._node;
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor
        public JsonToken endToken() {
            return null;
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor, com.flurry.org.codehaus.jackson.JsonStreamContext
        public String getCurrentName() {
            return null;
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor, com.flurry.org.codehaus.jackson.JsonStreamContext
        public /* bridge */ /* synthetic */ JsonStreamContext getParent() {
            return super.getParent();
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor
        public JsonToken nextToken() {
            if (this._done) {
                this._node = null;
                return null;
            }
            this._done = true;
            return this._node.asToken();
        }

        @Override // com.flurry.org.codehaus.jackson.node.NodeCursor
        public JsonToken nextValue() {
            return nextToken();
        }
    }

    public NodeCursor(int i, NodeCursor nodeCursor) {
        this._type = i;
        this._index = -1;
        this._parent = nodeCursor;
    }

    public abstract boolean currentHasChildren();

    public abstract JsonNode currentNode();

    public abstract JsonToken endToken();

    @Override // com.flurry.org.codehaus.jackson.JsonStreamContext
    public abstract String getCurrentName();

    @Override // com.flurry.org.codehaus.jackson.JsonStreamContext
    public final NodeCursor getParent() {
        return this._parent;
    }

    public final NodeCursor iterateChildren() {
        JsonNode currentNode = currentNode();
        if (currentNode == null) {
            throw new IllegalStateException("No current node");
        }
        if (currentNode.isArray()) {
            return new Array(currentNode, this);
        }
        if (currentNode.isObject()) {
            return new Object(currentNode, this);
        }
        throw new IllegalStateException("Current node of type " + currentNode.getClass().getName());
    }

    public abstract JsonToken nextToken();

    public abstract JsonToken nextValue();
}
