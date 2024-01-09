package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.AvroTypeException;
import java.util.Arrays;

/* loaded from: classes.dex */
public abstract class ParsingEncoder extends Encoder {
    private long[] counts = new long[10];
    protected int pos = -1;

    /* JADX INFO: Access modifiers changed from: protected */
    public final int depth() {
        return this.pos;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void pop() {
        if (this.counts[this.pos] != 0) {
            throw new AvroTypeException("Incorrect number of items written. " + this.counts[this.pos] + " more required.");
        }
        this.pos--;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void push() {
        if (this.pos == this.counts.length) {
            this.counts = Arrays.copyOf(this.counts, this.pos + 10);
        }
        long[] jArr = this.counts;
        int i = this.pos + 1;
        this.pos = i;
        jArr[i] = 0;
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void setItemCount(long j) {
        if (this.counts[this.pos] != 0) {
            throw new AvroTypeException("Incorrect number of items written. " + this.counts[this.pos] + " more required.");
        }
        this.counts[this.pos] = j;
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void startItem() {
        long[] jArr = this.counts;
        int i = this.pos;
        jArr[i] = jArr[i] - 1;
    }
}
