package com.flurry.android;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class bl {
    private static int a = 1;
    private final int b;
    private final long c;
    private final String d;
    private List e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bl(long j, String str) {
        int i = a;
        a = i + 1;
        this.b = i;
        this.c = j;
        this.d = str;
        this.e = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public bl(DataInput dataInput) {
        this.b = dataInput.readInt();
        this.c = dataInput.readLong();
        this.d = dataInput.readUTF();
        this.e = new ArrayList();
        short readShort = dataInput.readShort();
        for (short s = 0; s < readShort; s = (short) (s + 1)) {
            this.e.add(new at(dataInput));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(at atVar) {
        this.e.add(atVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(DataOutput dataOutput) {
        dataOutput.writeInt(this.b);
        dataOutput.writeLong(this.c);
        dataOutput.writeUTF(this.d);
        dataOutput.writeShort(this.e.size());
        for (at atVar : this.e) {
            atVar.a(dataOutput);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String b() {
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long c() {
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List d() {
        return this.e;
    }
}
