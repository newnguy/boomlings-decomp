package net.robotmedia.billing.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class a {
  private static final String[] b = new String[] { "_id", "productId", "state", "purchaseTime", "developerPayload" };
  
  SQLiteDatabase a;
  
  private b c;
  
  public a(Context paramContext) {
    this.c = new b(this, paramContext);
    this.a = this.c.getWritableDatabase();
  }
  
  protected static final c a(Cursor paramCursor) {
    c c = new c();
    c.c = paramCursor.getString(0);
    c.e = paramCursor.getString(1);
    c.f = d.a(paramCursor.getInt(2));
    c.g = paramCursor.getLong(3);
    c.a = paramCursor.getString(4);
    return c;
  }
  
  public void a() {
    this.c.close();
  }
  
  public void a(c paramc) {
    ContentValues contentValues = new ContentValues();
    contentValues.put("_id", paramc.c);
    contentValues.put("productId", paramc.e);
    contentValues.put("state", Integer.valueOf(paramc.f.ordinal()));
    contentValues.put("purchaseTime", Long.valueOf(paramc.g));
    contentValues.put("developerPayload", paramc.a);
    this.a.replace("purchases", null, contentValues);
  }
  
  public Cursor b() {
    return this.a.query("purchases", b, null, null, null, null, null);
  }
  
  public void b(c paramc) {
    this.a.delete("purchases", "_id=?", new String[] { paramc.c });
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\net\robotmedia\billing\a\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */