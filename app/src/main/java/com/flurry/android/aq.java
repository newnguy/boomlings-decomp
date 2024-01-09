package com.flurry.android;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class aq {
    private int a;
    private String b;
    private Map c;
    private long d;
    private boolean e;
    private long f;

    public aq(int i, String str, Map map, long j, boolean z) {
        this.a = i;
        this.b = str;
        this.c = map;
        this.d = j;
        this.e = z;
    }

    public final void a(long j) {
        this.f = j - this.d;
        bm.a("FlurryAgent", "Ended event '" + this.b + "' (" + this.d + ") after " + this.f + "ms");
    }

    public final boolean a(String str) {
        return this.e && this.f == 0 && this.b.equals(str);
    }

    public final byte[] a() {
        DataOutputStream dataOutputStream;
        Throwable th;
        DataOutputStream dataOutputStream2;
        byte[] bArr;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.writeShort(this.a);
                dataOutputStream.writeUTF(this.b);
                if (this.c == null) {
                    dataOutputStream.writeShort(0);
                } else {
                    dataOutputStream.writeShort(this.c.size());
                    for (Map.Entry entry : this.c.entrySet()) {
                        dataOutputStream.writeUTF(ac.a((String) entry.getKey()));
                        dataOutputStream.writeUTF(ac.a((String) entry.getValue()));
                    }
                }
                dataOutputStream.writeLong(this.d);
                dataOutputStream.writeLong(this.f);
                dataOutputStream.flush();
                bArr = byteArrayOutputStream.toByteArray();
                ac.a(dataOutputStream);
            } catch (IOException e) {
                dataOutputStream2 = dataOutputStream;
                try {
                    bArr = new byte[0];
                    ac.a(dataOutputStream2);
                    return bArr;
                } catch (Throwable th2) {
                    th = th2;
                    dataOutputStream = dataOutputStream2;
                    ac.a(dataOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                ac.a(dataOutputStream);
                throw th;
            }
        } catch (IOException e2) {
            dataOutputStream2 = null;
        } catch (Throwable th4) {
            dataOutputStream = null;
            th = th4;
        }
        return bArr;
    }
}
