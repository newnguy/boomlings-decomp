package org.cocos2dx.lib;

import java.util.Comparator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class au implements Comparator {
    final /* synthetic */ as a;

    public au(as asVar) {
        this.a = asVar;
    }

    @Override // java.util.Comparator
    /* renamed from: a */
    public int compare(String[] strArr, String[] strArr2) {
        String str = strArr[2];
        String str2 = strArr2[2];
        if (str == null) {
            str = "0";
        }
        if (str2 == null) {
            str2 = "0";
        }
        return (int) (Long.parseLong(str2) - Long.parseLong(str));
    }
}
