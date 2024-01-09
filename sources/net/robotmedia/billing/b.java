package net.robotmedia.billing;

/* loaded from: classes.dex */
public enum b {
    UNKNOWN,
    SUPPORTED,
    UNSUPPORTED;

    /* renamed from: values  reason: to resolve conflict with enum method */
    public static b[] valuesCustom() {
        b[] valuesCustom = values();
        int length = valuesCustom.length;
        b[] bVarArr = new b[length];
        System.arraycopy(valuesCustom, 0, bVarArr, 0, length);
        return bVarArr;
    }
}
