package net.robotmedia.billing;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.robotmedia.billing.a.c;
import net.robotmedia.billing.a.d;
import net.robotmedia.billing.a.e;
import net.robotmedia.billing.b.a;
import net.robotmedia.billing.b.b;
import net.robotmedia.billing.c.e;
import net.robotmedia.billing.c.g;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
  private static b a = b.a;
  
  private static b b = b.a;
  
  private static Set c = new HashSet();
  
  private static c d = null;
  
  private static boolean e = false;
  
  private static b f = null;
  
  private static HashMap g = new HashMap<Object, Object>();
  
  private static Set h = new HashSet();
  
  private static HashMap i = new HashMap<Object, Object>();
  
  private static List a(JSONObject paramJSONObject) {
    byte b1;
    byte b2 = 0;
    ArrayList<c> arrayList = new ArrayList();
    JSONArray jSONArray = paramJSONObject.optJSONArray("orders");
    if (jSONArray != null) {
      b1 = jSONArray.length();
    } else {
      b1 = 0;
    } 
    while (true) {
      if (b2 >= b1)
        return arrayList; 
      arrayList.add(c.a(jSONArray.getJSONObject(b2)));
      b2++;
    } 
  }
  
  public static b a(Context paramContext) {
    boolean bool;
    if (a == b.a) {
      BillingService.a(paramContext);
      return a;
    } 
    if (a == b.b) {
      bool = true;
    } else {
      bool = false;
    } 
    a(bool);
    return a;
  }
  
  protected static void a() {
    Iterator<n> iterator = h.iterator();
    while (true) {
      if (!iterator.hasNext())
        return; 
      ((n)iterator.next()).a();
    } 
  }
  
  protected static void a(long paramLong, d paramd) {
    a("Request " + paramLong + " of type " + paramd.c() + " sent");
    if (paramd.e()) {
      i.put(Long.valueOf(paramLong), paramd);
      return;
    } 
    if (paramd.d())
      g.b(paramd.b()); 
  }
  
  public static void a(Activity paramActivity, PendingIntent paramPendingIntent, Intent paramIntent) {
    if (e.a()) {
      e.a(paramActivity, paramPendingIntent.getIntentSender(), paramIntent);
      return;
    } 
    try {
      paramPendingIntent.send((Context)paramActivity, 0, paramIntent);
    } catch (android.app.PendingIntent.CanceledException canceledException) {
      Log.e("Billing", "Error starting purchase intent", (Throwable)canceledException);
    } 
  }
  
  protected static void a(Context paramContext, long paramLong, int paramInt) {
    k k = k.b(paramInt);
    a("Request " + paramLong + " received response " + k);
    d d = (d)i.get(Long.valueOf(paramLong));
    if (d != null) {
      i.remove(Long.valueOf(paramLong));
      d.a(k);
    } 
  }
  
  protected static void a(Context paramContext, String paramString) {
    a("Notification " + paramString + " available");
    d(paramContext, paramString);
  }
  
  protected static void a(Context paramContext, String paramString1, String paramString2) {
    JSONObject jSONObject;
    a("Purchase state changed");
    if (TextUtils.isEmpty(paramString1)) {
      Log.w("Billing", "Signed data is empty");
      return;
    } 
    a(paramString1);
    if (!e) {
      a a1;
      if (TextUtils.isEmpty(paramString2)) {
        Log.w("Billing", "Empty signature requires debug mode");
        return;
      } 
      if (f != null) {
        b b1 = f;
      } else {
        a1 = new a(d);
      } 
      if (!a1.a(paramString1, paramString2)) {
        Log.w("Billing", "Signature does not match data.");
        return;
      } 
    } 
    try {
      jSONObject = new JSONObject();
      this(paramString1);
      if (!b(jSONObject)) {
        Log.w("Billing", "Invalid nonce");
        return;
      } 
    } catch (JSONException jSONException) {
      Log.e("Billing", "JSON exception: ", (Throwable)jSONException);
      return;
    } 
    List list = a(jSONObject);
    ArrayList<String> arrayList = new ArrayList();
    Iterator<c> iterator = list.iterator();
    while (true) {
      if (!iterator.hasNext()) {
        if (!arrayList.isEmpty())
          a((Context)jSONException, (String[])arrayList.toArray((Object[])new String[arrayList.size()])); 
        return;
      } 
      c c1 = iterator.next();
      if (c1.b != null && c.contains(c1.e)) {
        arrayList.add(c1.b);
      } else {
        a(c1.e, c1.b);
      } 
      b((Context)jSONException, c1);
      a(c1.e, c1.f);
    } 
  }
  
  public static void a(Context paramContext, String paramString1, boolean paramBoolean, String paramString2) {
    if (paramBoolean)
      c.add(paramString1); 
    BillingService.a(paramContext, paramString1, paramString2);
  }
  
  public static void a(Context paramContext, List paramList) {
    Iterator<c> iterator = paramList.iterator();
    while (true) {
      if (!iterator.hasNext())
        return; 
      d(paramContext, iterator.next());
    } 
  }
  
  static void a(Context paramContext, c paramc) {
    byte[] arrayOfByte = b();
    if (arrayOfByte != null) {
      paramc.c = g.a(paramContext, arrayOfByte, paramc.c);
      paramc.e = g.a(paramContext, arrayOfByte, paramc.e);
      paramc.a = g.a(paramContext, arrayOfByte, paramc.a);
    } 
  }
  
  private static void a(Context paramContext, String[] paramArrayOfString) {
    BillingService.a(paramContext, paramArrayOfString);
  }
  
  protected static void a(String paramString) {
    if (e)
      Log.d("Billing", paramString); 
  }
  
  protected static void a(String paramString, PendingIntent paramPendingIntent) {
    Iterator<n> iterator = h.iterator();
    while (true) {
      if (!iterator.hasNext())
        return; 
      ((n)iterator.next()).a(paramString, paramPendingIntent);
    } 
  }
  
  private static final void a(String paramString1, String paramString2) {
    Set<String> set2 = (Set)g.get(paramString1);
    Set<String> set1 = set2;
    if (set2 == null) {
      set1 = new HashSet();
      g.put(paramString1, set1);
    } 
    set1.add(paramString2);
  }
  
  private static void a(String paramString, d paramd) {
    Iterator<n> iterator = h.iterator();
    while (true) {
      if (!iterator.hasNext())
        return; 
      ((n)iterator.next()).a(paramString, paramd);
    } 
  }
  
  protected static void a(String paramString, k paramk) {
    Iterator<n> iterator = h.iterator();
    while (true) {
      if (!iterator.hasNext())
        return; 
      ((n)iterator.next()).a(paramString, paramk);
    } 
  }
  
  public static void a(c paramc) {
    d = paramc;
  }
  
  protected static void a(boolean paramBoolean) {
    b b1;
    if (paramBoolean) {
      b1 = b.b;
    } else {
      b1 = b.c;
    } 
    a = b1;
    if (a == b.c)
      b = b.c; 
    Iterator<n> iterator = h.iterator();
    while (true) {
      if (!iterator.hasNext())
        return; 
      ((n)iterator.next()).a(paramBoolean);
    } 
  }
  
  public static boolean a(n paramn) {
    return h.add(paramn);
  }
  
  public static b b(Context paramContext) {
    boolean bool;
    if (b == b.a) {
      BillingService.b(paramContext);
      return b;
    } 
    if (b == b.b) {
      bool = true;
    } else {
      bool = false;
    } 
    b(bool);
    return b;
  }
  
  public static void b(Context paramContext, String paramString) {
    a(paramContext, paramString, false, null);
  }
  
  public static void b(Context paramContext, String paramString1, boolean paramBoolean, String paramString2) {
    if (paramBoolean)
      c.add(paramString1); 
    BillingService.b(paramContext, paramString1, paramString2);
  }
  
  public static void b(Context paramContext, c paramc) {
    paramc = paramc.a();
    a(paramContext, paramc);
    e.a(paramContext, paramc);
  }
  
  protected static void b(boolean paramBoolean) {
    b b1;
    if (paramBoolean) {
      b1 = b.b;
    } else {
      b1 = b.c;
    } 
    b = b1;
    if (b == b.b)
      a = b.b; 
    Iterator<n> iterator = h.iterator();
    while (true) {
      if (!iterator.hasNext())
        return; 
      ((n)iterator.next()).b(paramBoolean);
    } 
  }
  
  public static boolean b(n paramn) {
    return h.remove(paramn);
  }
  
  private static boolean b(JSONObject paramJSONObject) {
    long l = paramJSONObject.optLong("nonce");
    if (g.a(l)) {
      g.b(l);
      return true;
    } 
    return false;
  }
  
  private static byte[] b() {
    byte[] arrayOfByte = null;
    if (d != null) {
      arrayOfByte = d.getObfuscationSalt();
      byte[] arrayOfByte1 = arrayOfByte;
      if (arrayOfByte == null) {
        Log.w("Billing", "Can't (un)obfuscate purchases without salt");
        return arrayOfByte;
      } 
      return arrayOfByte1;
    } 
    Log.w("Billing", "Can't (un)obfuscate purchases without salt");
    return arrayOfByte;
  }
  
  public static List c(Context paramContext) {
    List list = e.a(paramContext);
    a(paramContext, list);
    return list;
  }
  
  public static void c(Context paramContext, String paramString) {
    b(paramContext, paramString, false, null);
  }
  
  public static void c(Context paramContext, c paramc) {
    paramc = paramc.a();
    a(paramContext, paramc);
    e.b(paramContext, paramc);
  }
  
  public static void d(Context paramContext) {
    BillingService.a(paramContext, g.a());
  }
  
  private static void d(Context paramContext, String paramString) {
    long l = g.a();
    BillingService.a(paramContext, new String[] { paramString }, l);
  }
  
  static void d(Context paramContext, c paramc) {
    byte[] arrayOfByte = b();
    if (arrayOfByte != null) {
      paramc.c = g.b(paramContext, arrayOfByte, paramc.c);
      paramc.e = g.b(paramContext, arrayOfByte, paramc.e);
      paramc.a = g.b(paramContext, arrayOfByte, paramc.a);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\net\robotmedia\billing\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */