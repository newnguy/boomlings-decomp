package com.chartboost.sdk.impl;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class ad {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends c {
        a(af afVar) {
            super(afVar);
        }

        @Override // com.chartboost.sdk.impl.af
        public void a(Object obj, StringBuilder sb) {
            w wVar = new w();
            wVar.put("$code", ((au) obj).a());
            this.a.a(wVar, sb);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends c {
        b(af afVar) {
            super(afVar);
        }

        @Override // com.chartboost.sdk.impl.af
        public void a(Object obj, StringBuilder sb) {
            av avVar = (av) obj;
            w wVar = new w();
            wVar.put("$code", avVar.a());
            wVar.put("$scope", avVar.b());
            this.a.a(wVar, sb);
        }
    }

    /* loaded from: classes.dex */
    abstract class c extends aa {
        protected final af a;

        c(af afVar) {
            this.a = afVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d extends c {
        d(af afVar) {
            super(afVar);
        }

        @Override // com.chartboost.sdk.impl.af
        public void a(Object obj, StringBuilder sb) {
            sb.append("{ ");
            y yVar = (y) obj;
            boolean z = true;
            for (String str : yVar.keySet()) {
                if (z) {
                    z = false;
                } else {
                    sb.append(" , ");
                }
                ac.a(sb, str);
                sb.append(" : ");
                this.a.a(yVar.a(str), sb);
            }
            sb.append("}");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e extends c {
        e(af afVar) {
            super(afVar);
        }

        @Override // com.chartboost.sdk.impl.af
        public void a(Object obj, StringBuilder sb) {
            z zVar = (z) obj;
            w wVar = new w();
            wVar.put("$ref", zVar.b());
            wVar.put("$id", zVar.a());
            this.a.a(wVar, sb);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f extends c {
        f(af afVar) {
            super(afVar);
        }

        @Override // com.chartboost.sdk.impl.af
        public void a(Object obj, StringBuilder sb) {
            boolean z = true;
            sb.append("[ ");
            for (Object obj2 : (Iterable) obj) {
                if (z) {
                    z = false;
                } else {
                    sb.append(" , ");
                }
                this.a.a(obj2, sb);
            }
            sb.append("]");
        }
    }

    /* loaded from: classes.dex */
    class g extends c {
        g(af afVar) {
            super(afVar);
        }

        @Override // com.chartboost.sdk.impl.af
        public void a(Object obj, StringBuilder sb) {
            as asVar = (as) obj;
            w wVar = new w();
            wVar.put("$ts", Integer.valueOf(asVar.a()));
            wVar.put("$inc", Integer.valueOf(asVar.b()));
            this.a.a(wVar, sb);
        }
    }

    /* loaded from: classes.dex */
    class h extends aa {
        private h() {
        }

        @Override // com.chartboost.sdk.impl.af
        public void a(Object obj, StringBuilder sb) {
            sb.append("<Binary Data>");
        }
    }

    /* loaded from: classes.dex */
    class i extends c {
        i(af afVar) {
            super(afVar);
        }

        @Override // com.chartboost.sdk.impl.af
        public void a(Object obj, StringBuilder sb) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            simpleDateFormat.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
            this.a.a(new w("$date", simpleDateFormat.format((Date) obj)), sb);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class j extends c {
        j(af afVar) {
            super(afVar);
        }

        @Override // com.chartboost.sdk.impl.af
        public void a(Object obj, StringBuilder sb) {
            sb.append("{ ");
            boolean z = true;
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                if (z) {
                    z = false;
                } else {
                    sb.append(" , ");
                }
                ac.a(sb, entry.getKey().toString());
                sb.append(" : ");
                this.a.a(entry.getValue(), sb);
            }
            sb.append("}");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class k extends c {
        k(af afVar) {
            super(afVar);
        }

        @Override // com.chartboost.sdk.impl.af
        public void a(Object obj, StringBuilder sb) {
            this.a.a(new w("$maxKey", 1), sb);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class l extends c {
        l(af afVar) {
            super(afVar);
        }

        @Override // com.chartboost.sdk.impl.af
        public void a(Object obj, StringBuilder sb) {
            this.a.a(new w("$minKey", 1), sb);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class m extends c {
        m(af afVar) {
            super(afVar);
        }

        @Override // com.chartboost.sdk.impl.af
        public void a(Object obj, StringBuilder sb) {
            sb.append("[ ");
            for (int i = 0; i < Array.getLength(obj); i++) {
                if (i > 0) {
                    sb.append(" , ");
                }
                this.a.a(Array.get(obj, i), sb);
            }
            sb.append("]");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class n extends c {
        n(af afVar) {
            super(afVar);
        }

        @Override // com.chartboost.sdk.impl.af
        public void a(Object obj, StringBuilder sb) {
            this.a.a(new w("$oid", obj.toString()), sb);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class o extends c {
        o(af afVar) {
            super(afVar);
        }

        @Override // com.chartboost.sdk.impl.af
        public void a(Object obj, StringBuilder sb) {
            w wVar = new w();
            wVar.a("$regex", obj.toString());
            if (((Pattern) obj).flags() != 0) {
                wVar.a("$options", x.a(((Pattern) obj).flags()));
            }
            this.a.a(wVar, sb);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class p extends aa {
        private p() {
        }

        @Override // com.chartboost.sdk.impl.af
        public void a(Object obj, StringBuilder sb) {
            ac.a(sb, (String) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class q extends aa {
        private q() {
        }

        @Override // com.chartboost.sdk.impl.af
        public void a(Object obj, StringBuilder sb) {
            sb.append(obj.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class r extends c {
        r(af afVar) {
            super(afVar);
        }

        @Override // com.chartboost.sdk.impl.af
        public void a(Object obj, StringBuilder sb) {
            w wVar = new w();
            wVar.put("$uuid", ((UUID) obj).toString());
            this.a.a(wVar, sb);
        }
    }

    public static af a() {
        ab b2 = b();
        b2.a(Date.class, new i(b2));
        b2.a(as.class, new g(b2));
        b2.a(at.class, new h());
        b2.a(byte[].class, new h());
        return b2;
    }

    static ab b() {
        ab abVar = new ab();
        abVar.a(Object[].class, new m(abVar));
        abVar.a(Boolean.class, new q());
        abVar.a(au.class, new a(abVar));
        abVar.a(av.class, new b(abVar));
        abVar.a(y.class, new d(abVar));
        abVar.a(z.class, new e(abVar));
        abVar.a(Iterable.class, new f(abVar));
        abVar.a(Map.class, new j(abVar));
        abVar.a(aw.class, new k(abVar));
        abVar.a(ax.class, new l(abVar));
        abVar.a(Number.class, new q());
        abVar.a(ay.class, new n(abVar));
        abVar.a(Pattern.class, new o(abVar));
        abVar.a(String.class, new p());
        abVar.a(UUID.class, new r(abVar));
        return abVar;
    }
}
