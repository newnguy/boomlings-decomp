package com.flurry.android;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class bl {
    private static int a = 1;
    private final int b;
    private final long c;
    private final String d;
    private List e;

    public bl(long j, String str) {
        int i = a;
        a = i + 1;
        this.b = i;
        this.c = j;
        this.d = str;
        this.e = new ArrayList();
    }

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

    public final int a() {
        return this.b;
    }

    public final void a(at atVar) {
        this.e.add(atVar);
    }

    public final void a(DataOutput dataOutput) {
        dataOutput.writeInt(this.b);
        dataOutput.writeLong(this.c);
        dataOutput.writeUTF(this.d);
        dataOutput.writeShort(this.e.size());
        for (at atVar : this.e) {
            atVar.a(dataOutput);
        }
    }

    public final String b() {
        return this.d;
    }

    public final long c() {
        return this.c;
    }

    public final List d() {
        return this.e;
    }
}
