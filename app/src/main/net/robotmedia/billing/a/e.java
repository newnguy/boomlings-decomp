package net.robotmedia.billing.a;

import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class e {
    public static synchronized List a(Context context) {
        List a;
        synchronized (e.class) {
            a aVar = new a(context);
            a = a(aVar.b());
            aVar.a();
        }
        return a;
    }

    private static List a(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                arrayList.add(a.a(cursor));
            }
            cursor.close();
        }
        return arrayList;
    }

    public static synchronized void a(Context context, c cVar) {
        synchronized (e.class) {
            a aVar = new a(context);
            aVar.a(cVar);
            aVar.a();
        }
    }

    public static synchronized void b(Context context, c cVar) {
        synchronized (e.class) {
            a aVar = new a(context);
            aVar.b(cVar);
            aVar.a();
        }
    }
}
