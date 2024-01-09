package net.robotmedia.billing.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* loaded from: classes.dex */
class b extends SQLiteOpenHelper {
    final /* synthetic */ a a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(a aVar, Context context) {
        super(context, "billing.db", (SQLiteDatabase.CursorFactory) null, 1);
        this.a = aVar;
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE purchases(_id TEXT PRIMARY KEY, productId INTEGER, state TEXT, purchaseTime TEXT, developerPayload INTEGER)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
