package net.robotmedia.billing.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class b extends SQLiteOpenHelper {
  final a a;
  
  public b(a parama, Context paramContext) {
    super(paramContext, "billing.db", null, 1);
  }
  
  private void a(SQLiteDatabase paramSQLiteDatabase) {
    paramSQLiteDatabase.execSQL("CREATE TABLE purchases(_id TEXT PRIMARY KEY, productId INTEGER, state TEXT, purchaseTime TEXT, developerPayload INTEGER)");
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase) {
    a(paramSQLiteDatabase);
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\net\robotmedia\billing\a\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */