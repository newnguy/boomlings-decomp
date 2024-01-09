package com.flurry.org.codehaus.jackson.map.util;

/* loaded from: classes.dex */
public final class LinkedNode {
    final LinkedNode _next;
    final Object _value;

    public LinkedNode(Object obj, LinkedNode linkedNode) {
        this._value = obj;
        this._next = linkedNode;
    }

    public static boolean contains(LinkedNode linkedNode, Object obj) {
        while (linkedNode != null) {
            if (linkedNode.value() == obj) {
                return true;
            }
            linkedNode = linkedNode.next();
        }
        return false;
    }

    public LinkedNode next() {
        return this._next;
    }

    public Object value() {
        return this._value;
    }
}
