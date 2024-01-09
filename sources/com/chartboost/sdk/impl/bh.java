package com.chartboost.sdk.impl;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes.dex */
public abstract class bh {
    final int a;
    private Queue b = new ConcurrentLinkedQueue();

    public bh(int i) {
        this.a = i;
    }

    protected boolean a(Object obj) {
        return true;
    }

    protected abstract Object b();

    public void b(Object obj) {
        if (a(obj) && this.b.size() <= this.a) {
            this.b.add(obj);
        }
    }

    public Object c() {
        Object poll = this.b.poll();
        return poll != null ? poll : b();
    }
}
