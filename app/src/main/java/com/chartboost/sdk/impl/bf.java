package com.chartboost.sdk.impl;

import com.chartboost.sdk.impl.ba;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class bf extends ba {

    /* loaded from: classes.dex */
    public class a {
        private ba.h.a a = ba.h.a.STABLE;
        private final Map b = new HashMap();

        a() {
        }

        public bf a() {
            return new b(this.b, this.a);
        }
    }

    /* loaded from: classes.dex */
    public class b extends bf {
        b(Map map, ba.h.a aVar) {
            super(map, aVar);
        }

        @Override // com.chartboost.sdk.impl.ba
        public Map a(Map map) {
            return new HashMap(map);
        }
    }

    protected bf(Map map, ba.h.a aVar) {
        super(map, aVar);
    }

    public static a b() {
        return new a();
    }

    public static bf c() {
        return b().a();
    }
}
