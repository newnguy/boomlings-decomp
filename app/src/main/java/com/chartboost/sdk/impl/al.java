package com.chartboost.sdk.impl;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class al implements ai {
    protected ap a;

    private void a(String str, int i, byte[] bArr) {
        a((byte) 5, str);
        int length = bArr.length;
        if (i == 2) {
            length += 4;
        }
        this.a.c(length);
        this.a.write(i);
        if (i == 2) {
            this.a.c(length - 4);
        }
        int a = this.a.a();
        this.a.write(bArr);
        ae.a(this.a.a() - a, bArr.length);
    }

    private void a(String str, Iterable iterable) {
        a((byte) 4, str);
        int a = this.a.a();
        this.a.c(0);
        int i = 0;
        for (Object obj : iterable) {
            b(String.valueOf(i), obj);
            i++;
        }
        this.a.write(0);
        this.a.a(a, this.a.a() - a);
    }

    private void a(String str, String str2, byte b) {
        a(b, str);
        b(str2);
    }

    private void a(String str, Map map) {
        a((byte) 3, str);
        int a = this.a.a();
        this.a.c(0);
        for (Map.Entry entry : map.entrySet()) {
            b(entry.getKey().toString(), entry.getValue());
        }
        this.a.write(0);
        this.a.a(a, this.a.a() - a);
    }

    private void a(String str, Pattern pattern) {
        a((byte) 11, str);
        c(pattern.pattern());
        c(ag.a(pattern.flags()));
    }

    private void c(String str, Object obj) {
        a((byte) 4, str);
        int a = this.a.a();
        this.a.c(0);
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            b(String.valueOf(i), Array.get(obj, i));
        }
        this.a.write(0);
        this.a.a(a, this.a.a() - a);
    }

    private void d(String str) {
        a((byte) -1, str);
    }

    private void e(String str) {
        a(Byte.MAX_VALUE, str);
    }

    public void a() {
        this.a = null;
    }

    protected void a(byte b, String str) {
        this.a.write(b);
        c(str);
    }

    public void a(ap apVar) {
        if (this.a != null) {
            throw new IllegalStateException("in the middle of something");
        }
        this.a = apVar;
    }

    protected void a(String str) {
        a((byte) 10, str);
    }

    protected void a(String str, as asVar) {
        a((byte) 17, str);
        this.a.c(asVar.b());
        this.a.c(asVar.a());
    }

    protected void a(String str, at atVar) {
        a(str, atVar.a(), atVar.b());
    }

    protected void a(String str, au auVar) {
        a((byte) 13, str);
        this.a.a();
        b(auVar.a());
    }

    protected void a(String str, av avVar) {
        a((byte) 15, str);
        int a = this.a.a();
        this.a.c(0);
        b(avVar.a());
        b(avVar.b());
        this.a.a(a, this.a.a() - a);
    }

    protected void a(String str, ay ayVar) {
        a((byte) 7, str);
        this.a.d(ayVar.c());
        this.a.d(ayVar.d());
        this.a.d(ayVar.e());
    }

    protected void a(String str, az azVar) {
        a(str, azVar.a(), (byte) 14);
    }

    protected void a(String str, Boolean bool) {
        a((byte) 8, str);
        this.a.write(bool.booleanValue() ? 1 : 0);
    }

    protected void a(String str, Number number) {
        if ((number instanceof Integer) || (number instanceof Short) || (number instanceof Byte) || (number instanceof AtomicInteger)) {
            a((byte) 16, str);
            this.a.c(number.intValue());
        } else if ((number instanceof Long) || (number instanceof AtomicLong)) {
            a((byte) 18, str);
            this.a.a(number.longValue());
        } else if (!(number instanceof Float) && !(number instanceof Double)) {
            throw new IllegalArgumentException("can't serialize " + number.getClass());
        } else {
            a((byte) 1, str);
            this.a.a(number.doubleValue());
        }
    }

    protected void a(String str, String str2) {
        a(str, str2, (byte) 2);
    }

    protected void a(String str, Date date) {
        a((byte) 9, str);
        this.a.a(date.getTime());
    }

    protected void a(String str, UUID uuid) {
        a((byte) 5, str);
        this.a.c(16);
        this.a.write(3);
        this.a.a(uuid.getMostSignificantBits());
        this.a.a(uuid.getLeastSignificantBits());
    }

    protected void a(String str, byte[] bArr) {
        a(str, 0, bArr);
    }

    protected boolean a(String str, aj ajVar) {
        return false;
    }

    protected boolean a(String str, Object obj) {
        return false;
    }

    public byte[] a(aj ajVar) {
        ao aoVar = new ao();
        a(aoVar);
        b(ajVar);
        a();
        return aoVar.c();
    }

    public int b(aj ajVar) {
        return b((String) null, ajVar);
    }

    protected int b(String str, aj ajVar) {
        if (ajVar == null) {
            throw new NullPointerException("can't save a null object");
        }
        int a = this.a.a();
        byte b = ajVar instanceof List ? (byte) 4 : (byte) 3;
        if (a(str, ajVar)) {
            return this.a.a() - a;
        }
        if (str != null) {
            a(b, str);
        }
        int a2 = this.a.a();
        this.a.c(0);
        List list = null;
        boolean z = b == 3 && str == null;
        if (b == 3) {
            if (z && ajVar.b("_id")) {
                b("_id", ajVar.a("_id"));
            }
            Object a3 = ajVar.a("_transientFields");
            if (a3 instanceof List) {
                list = (List) a3;
            }
        }
        if (ajVar instanceof Map) {
            for (Map.Entry entry : ((Map) ajVar).entrySet()) {
                if (!z || !((String) entry.getKey()).equals("_id")) {
                    if (list == null || !list.contains(entry.getKey())) {
                        b((String) entry.getKey(), entry.getValue());
                    }
                }
            }
        } else {
            for (String str2 : ajVar.keySet()) {
                if (!z || !str2.equals("_id")) {
                    if (list == null || !list.contains(str2)) {
                        b(str2, ajVar.a(str2));
                    }
                }
            }
        }
        this.a.write(0);
        this.a.a(a2, this.a.a() - a2);
        return this.a.a() - a;
    }

    protected void b(String str) {
        int a = this.a.a();
        this.a.c(0);
        this.a.a(a, c(str));
    }

    protected void b(String str, Object obj) {
        if (str.equals("_transientFields")) {
            return;
        }
        if (str.equals("$where") && (obj instanceof String)) {
            a((byte) 13, str);
            b(obj.toString());
            return;
        }
        Object a = ag.a(obj);
        if (a == null) {
            a(str);
        } else if (a instanceof Date) {
            a(str, (Date) a);
        } else if (a instanceof Number) {
            a(str, (Number) a);
        } else if (a instanceof Character) {
            a(str, a.toString());
        } else if (a instanceof String) {
            a(str, a.toString());
        } else if (a instanceof ay) {
            a(str, (ay) a);
        } else if (a instanceof aj) {
            b(str, (aj) a);
        } else if (a instanceof Boolean) {
            a(str, (Boolean) a);
        } else if (a instanceof Pattern) {
            a(str, (Pattern) a);
        } else if (a instanceof Map) {
            a(str, (Map) a);
        } else if (a instanceof Iterable) {
            a(str, (Iterable) a);
        } else if (a instanceof byte[]) {
            a(str, (byte[]) a);
        } else if (a instanceof at) {
            a(str, (at) a);
        } else if (a instanceof UUID) {
            a(str, (UUID) a);
        } else if (a.getClass().isArray()) {
            c(str, a);
        } else if (a instanceof az) {
            a(str, (az) a);
        } else if (a instanceof as) {
            a(str, (as) a);
        } else if (a instanceof av) {
            a(str, (av) a);
        } else if (a instanceof au) {
            a(str, (au) a);
        } else if (a instanceof z) {
            am amVar = new am();
            amVar.a("$ref", ((z) a).b());
            amVar.a("$id", ((z) a).a());
            b(str, (aj) amVar);
        } else if (a instanceof ax) {
            d(str);
        } else if (a instanceof aw) {
            e(str);
        } else if (!a(str, a)) {
            throw new IllegalArgumentException("can't serialize " + a.getClass());
        }
    }

    protected int c(String str) {
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int codePointAt = Character.codePointAt(str, i);
            if (codePointAt < 128) {
                this.a.write((byte) codePointAt);
                i2++;
            } else if (codePointAt < 2048) {
                this.a.write((byte) ((codePointAt >> 6) + 192));
                this.a.write((byte) ((codePointAt & 63) + 128));
                i2 += 2;
            } else if (codePointAt < 65536) {
                this.a.write((byte) ((codePointAt >> 12) + 224));
                this.a.write((byte) (((codePointAt >> 6) & 63) + 128));
                this.a.write((byte) ((codePointAt & 63) + 128));
                i2 += 3;
            } else {
                this.a.write((byte) ((codePointAt >> 18) + 240));
                this.a.write((byte) (((codePointAt >> 12) & 63) + 128));
                this.a.write((byte) (((codePointAt >> 6) & 63) + 128));
                this.a.write((byte) ((codePointAt & 63) + 128));
                i2 += 4;
            }
            i += Character.charCount(codePointAt);
        }
        this.a.write(0);
        return i2 + 1;
    }
}
