package net.robotmedia.billing.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/* loaded from: classes.dex */
public class a {
    private static final String[] b = {"_id", "productId", "state", "purchaseTime", "developerPayload"};
    SQLiteDatabase a;
    private b c;

    public a(Context context) {
        this.c = new b(this, context);
        this.a = this.c.getWritableDatabase();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final c a(Cursor cursor) {
        c cVar = new c();
        cVar.c = cursor.getString(0);
        cVar.e = cursor.getString(1);
        cVar.f = d.a(cursor.getInt(2));
        cVar.g = cursor.getLong(3);
        cVar.a = cursor.getString(4);
        return cVar;
    }

    public void a() {
        this.c.close();
    }

    public void a(c cVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", cVar.c);
        contentValues.put("productId", cVar.e);
        contentValues.put("state", Integer.valueOf(cVar.f.ordinal()));
        contentValues.put("purchaseTime", Long.valueOf(cVar.g));
        contentValues.put("developerPayload", cVar.a);
        this.a.replace("purchases", null, contentValues);
    }

    public Cursor b() {
        return this.a.query("purchases", b, null, null, null, null, null);
    }

    public void b(c cVar) {
        this.a.delete("purchases", "_id=?", new String[]{cVar.c});
    }
}
