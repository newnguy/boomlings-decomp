package com.flurry.org.codehaus.jackson.map.util;

/* loaded from: classes.dex */
public abstract class PrimitiveArrayBuilder {
    static final int INITIAL_CHUNK_SIZE = 12;
    static final int MAX_CHUNK_SIZE = 262144;
    static final int SMALL_CHUNK_SIZE = 16384;
    Node _bufferHead;
    Node _bufferTail;
    int _bufferedEntryCount;
    Object _freeBuffer;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class Node {
        final Object _data;
        final int _dataLength;
        Node _next;

        public Node(Object obj, int i) {
            this._data = obj;
            this._dataLength = i;
        }

        public int copyData(Object obj, int i) {
            System.arraycopy(this._data, 0, obj, i, this._dataLength);
            return this._dataLength + i;
        }

        public Object getData() {
            return this._data;
        }

        public void linkNext(Node node) {
            if (this._next != null) {
                throw new IllegalStateException();
            }
            this._next = node;
        }

        public Node next() {
            return this._next;
        }
    }

    protected abstract Object _constructArray(int i);

    protected void _reset() {
        if (this._bufferTail != null) {
            this._freeBuffer = this._bufferTail.getData();
        }
        this._bufferTail = null;
        this._bufferHead = null;
        this._bufferedEntryCount = 0;
    }

    public final Object appendCompletedChunk(Object obj, int i) {
        Node node = new Node(obj, i);
        if (this._bufferHead == null) {
            this._bufferTail = node;
            this._bufferHead = node;
        } else {
            this._bufferTail.linkNext(node);
            this._bufferTail = node;
        }
        this._bufferedEntryCount += i;
        return _constructArray(i < SMALL_CHUNK_SIZE ? i + i : (i >> 2) + i);
    }

    public Object completeAndClearBuffer(Object obj, int i) {
        int i2 = i + this._bufferedEntryCount;
        Object _constructArray = _constructArray(i2);
        int i3 = 0;
        for (Node node = this._bufferHead; node != null; node = node.next()) {
            i3 = node.copyData(_constructArray, i3);
        }
        System.arraycopy(obj, 0, _constructArray, i3, i);
        int i4 = i3 + i;
        if (i4 != i2) {
            throw new IllegalStateException("Should have gotten " + i2 + " entries, got " + i4);
        }
        return _constructArray;
    }

    public Object resetAndStart() {
        _reset();
        return this._freeBuffer == null ? _constructArray(INITIAL_CHUNK_SIZE) : this._freeBuffer;
    }
}
