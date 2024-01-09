package net.robotmedia.billing;

/* loaded from: classes.dex */
public enum k {
    RESULT_OK,
    RESULT_USER_CANCELED,
    RESULT_SERVICE_UNAVAILABLE,
    RESULT_BILLING_UNAVAILABLE,
    RESULT_ITEM_UNAVAILABLE,
    RESULT_DEVELOPER_ERROR,
    RESULT_ERROR;

    public static boolean a(int i) {
        return RESULT_OK.ordinal() == i;
    }

    public static k b(int i) {
        k[] valuesCustom = valuesCustom();
        return (i < 0 || i >= valuesCustom.length) ? RESULT_ERROR : valuesCustom[i];
    }

    /* renamed from: values  reason: to resolve conflict with enum method */
    public static k[] valuesCustom() {
        k[] valuesCustom = values();
        int length = valuesCustom.length;
        k[] kVarArr = new k[length];
        System.arraycopy(valuesCustom, 0, kVarArr, 0, length);
        return kVarArr;
    }
}
