package net.robotmedia.billing;

/* loaded from: classes.dex */
public enum m {
    CHECK_BILLING_SUPPORTED,
    CHECK_SUBSCRIPTION_SUPPORTED,
    CONFIRM_NOTIFICATIONS,
    GET_PURCHASE_INFORMATION,
    REQUEST_PURCHASE,
    REQUEST_SUBSCRIPTION,
    RESTORE_TRANSACTIONS;

    /* renamed from: values  reason: to resolve conflict with enum method */
    public static m[] valuesCustom() {
        m[] valuesCustom = values();
        int length = valuesCustom.length;
        m[] mVarArr = new m[length];
        System.arraycopy(valuesCustom, 0, mVarArr, 0, length);
        return mVarArr;
    }
}
