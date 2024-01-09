package com.flurry.android;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
final class at {
    private static List a = Arrays.asList("requested", "filled", "unfilled", "rendered", "clicked", "videoStart", "videoCompleted", "videoProgressed", "sentToUrl", "adClosed", "adWillClose", "renderFailed", "requestAdComponents");
    private final String b;
    private final boolean c;
    private final long d;
    private final Map e;

    public at(DataInput dataInput) {
        this.b = dataInput.readUTF();
        this.c = dataInput.readBoolean();
        this.d = dataInput.readLong();
        this.e = new HashMap();
        short readShort = dataInput.readShort();
        for (short s = 0; s < readShort; s = (short) (s + 1)) {
            this.e.put(dataInput.readUTF(), dataInput.readUTF());
        }
    }

    public at(String str, boolean z, long j, Map map) {
        if (!a.contains(str)) {
            bm.a("FlurryAgent", "AdEvent initialized with unrecognized type: " + str);
        }
        this.b = str;
        this.c = z;
        this.d = j;
        if (map == null) {
            this.e = new HashMap();
        } else {
            this.e = map;
        }
    }

    public final String a() {
        return this.b;
    }

    public final void a(DataOutput dataOutput) {
        dataOutput.writeUTF(this.b);
        dataOutput.writeBoolean(this.c);
        dataOutput.writeLong(this.d);
        dataOutput.writeShort(this.e.size());
        for (Map.Entry entry : this.e.entrySet()) {
            dataOutput.writeUTF((String) entry.getKey());
            dataOutput.writeUTF((String) entry.getValue());
        }
    }

    public final boolean b() {
        return this.c;
    }

    public final long c() {
        return this.d;
    }

    public final Map d() {
        return this.e;
    }
}
