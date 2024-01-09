package com.chartboost.sdk.impl;

/* loaded from: classes.dex */
public class ab extends aa {
    private bd a = new bd();

    public void a(Class cls, af afVar) {
        this.a.a(cls, afVar);
    }

    @Override // com.chartboost.sdk.impl.af
    public void a(Object obj, StringBuilder sb) {
        Object a = x.a(obj);
        if (a == null) {
            sb.append(" null ");
            return;
        }
        af afVar = null;
        for (Class cls : bd.a((Class) a.getClass())) {
            afVar = (af) this.a.a((Object) cls);
            if (afVar != null) {
                break;
            }
        }
        if (afVar == null && a.getClass().isArray()) {
            afVar = (af) this.a.a((Object) Object[].class);
        }
        if (afVar == null) {
            throw new RuntimeException("json can't serialize type : " + a.getClass());
        }
        afVar.a(a, sb);
    }
}
