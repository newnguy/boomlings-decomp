package net.robotmedia.billing.a;

/* loaded from: classes.dex */
public enum d {
    PURCHASED,
    CANCELLED,
    REFUNDED,
    EXPIRED;

    public static d a(int i) {
        d[] valuesCustom = valuesCustom();
        return (i < 0 || i >= valuesCustom.length) ? CANCELLED : valuesCustom[i];
    }

    /* renamed from: values  reason: to resolve conflict with enum method */
    public static d[] valuesCustom() {
        d[] valuesCustom = values();
        int length = valuesCustom.length;
        d[] dVarArr = new d[length];
        System.arraycopy(valuesCustom, 0, dVarArr, 0, length);
        return dVarArr;
    }
}
