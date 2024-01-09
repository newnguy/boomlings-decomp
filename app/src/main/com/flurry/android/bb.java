package com.flurry.android;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
final class bb {
    private int a;
    private long b;
    private String c;
    private String d;
    private String e;

    public bb(int i, long j, String str, String str2, String str3) {
        this.a = i;
        this.b = j;
        this.c = str;
        this.d = str2;
        this.e = str3;
    }

    public final byte[] a() {
        DataOutputStream dataOutputStream;
        Throwable th;
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            } catch (IOException e) {
                dataOutputStream = null;
            } catch (Throwable th2) {
                dataOutputStream = null;
                th = th2;
                ac.a(dataOutputStream);
                throw th;
            }
            try {
                dataOutputStream.writeShort(this.a);
                dataOutputStream.writeLong(this.b);
                dataOutputStream.writeUTF(this.c);
                dataOutputStream.writeUTF(this.d);
                dataOutputStream.writeUTF(this.e);
                dataOutputStream.flush();
                bArr = byteArrayOutputStream.toByteArray();
                ac.a(dataOutputStream);
            } catch (IOException e2) {
                bArr = new byte[0];
                ac.a(dataOutputStream);
                return bArr;
            }
            return bArr;
        } catch (Throwable th3) {
            th = th3;
            ac.a(dataOutputStream);
            throw th;
        }
    }

    public final String b() {
        return this.c;
    }
}
