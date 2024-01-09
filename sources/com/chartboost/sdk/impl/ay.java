package com.chartboost.sdk.impl;

import com.flurry.android.Constants;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class ay implements Serializable, Comparable {
    static final Logger a = Logger.getLogger("org.bson.ObjectId");
    private static AtomicInteger f = new AtomicInteger(new Random().nextInt());
    private static final int g;
    final int b;
    final int c;
    final int d;
    boolean e;

    static {
        int i;
        int i2;
        try {
            StringBuilder sb = new StringBuilder();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                sb.append(networkInterfaces.nextElement().toString());
            }
            int hashCode = sb.toString().hashCode() << 16;
            a.fine("machine piece post: " + Integer.toHexString(hashCode));
            new Random().nextInt();
            try {
                i2 = ManagementFactory.getRuntimeMXBean().getName().hashCode();
            } catch (Throwable th) {
                i2 = i;
            }
            ClassLoader classLoader = ay.class.getClassLoader();
            int identityHashCode = classLoader != null ? System.identityHashCode(classLoader) : 0;
            int hashCode2 = (Integer.toHexString(i2) + Integer.toHexString(identityHashCode)).hashCode() & 65535;
            a.fine(new StringBuilder().append("process piece: ").append(Integer.toHexString(hashCode2)).toString());
            g = hashCode2 | hashCode;
            a.fine(new StringBuilder().append("machine : ").append(Integer.toHexString(g)).toString());
        }
    }

    public ay() {
        this.b = (int) (System.currentTimeMillis() / 1000);
        this.c = g;
        this.d = f.getAndIncrement();
        this.e = true;
    }

    public ay(int i, int i2, int i3) {
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = false;
    }

    public ay(String str) {
        this(str, false);
    }

    public ay(String str, boolean z) {
        if (!a(str)) {
            throw new IllegalArgumentException("invalid ObjectId [" + str + "]");
        }
        str = z ? b(str) : str;
        byte[] bArr = new byte[12];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) Integer.parseInt(str.substring(i * 2, (i * 2) + 2), 16);
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        this.b = wrap.getInt();
        this.c = wrap.getInt();
        this.d = wrap.getInt();
        this.e = false;
    }

    public static ay a(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof ay) {
            return (ay) obj;
        }
        if (obj instanceof String) {
            String obj2 = obj.toString();
            if (a(obj2)) {
                return new ay(obj2);
            }
        }
        return null;
    }

    static String a(String str, int i) {
        return str.substring(i * 2, (i * 2) + 2);
    }

    public static boolean a(String str) {
        int length;
        if (str != null && (length = str.length()) == 24) {
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if ((charAt < '0' || charAt > '9') && ((charAt < 'a' || charAt > 'f') && (charAt < 'A' || charAt > 'F'))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static String b(String str) {
        if (a(str)) {
            StringBuilder sb = new StringBuilder(24);
            for (int i = 7; i >= 0; i--) {
                sb.append(a(str, i));
            }
            for (int i2 = 11; i2 >= 8; i2--) {
                sb.append(a(str, i2));
            }
            return sb.toString();
        }
        throw new IllegalArgumentException("invalid object id: " + str);
    }

    int a(int i, int i2) {
        long j = (i & 4294967295L) - (i2 & 4294967295L);
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(ay ayVar) {
        if (ayVar == null) {
            return -1;
        }
        int a2 = a(this.b, ayVar.b);
        if (a2 == 0) {
            int a3 = a(this.c, ayVar.c);
            return a3 == 0 ? a(this.d, ayVar.d) : a3;
        }
        return a2;
    }

    public String a() {
        byte[] b = b();
        StringBuilder sb = new StringBuilder(24);
        for (byte b2 : b) {
            String hexString = Integer.toHexString(b2 & Constants.UNKNOWN);
            if (hexString.length() == 1) {
                sb.append("0");
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public byte[] b() {
        byte[] bArr = new byte[12];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.putInt(this.b);
        wrap.putInt(this.c);
        wrap.putInt(this.d);
        return bArr;
    }

    public int c() {
        return this.b;
    }

    public int d() {
        return this.c;
    }

    public int e() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        ay a2 = a(obj);
        if (a2 == null) {
            return false;
        }
        return this.b == a2.b && this.c == a2.c && this.d == a2.d;
    }

    public int hashCode() {
        return this.b + (this.c * 111) + (this.d * 17);
    }

    public String toString() {
        return a();
    }
}
