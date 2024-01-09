package org.cocos2dx.lib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.b.a.a;
import com.b.a.d;
import com.b.a.e;
import com.b.a.f;
import com.b.a.i;
import com.b.a.m;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class as implements d, i {
  public static as me = null;
  
  final String AppID = "390270340997053";
  
  final String FBUseCount = "fb_app_usage_count";
  
  final String FBUseCountEnabled = "fb_app_usage_check_enable";
  
  final String TAG = "Facebook";
  
  public Cocos2dxActivity cAct_;
  
  f facebook = new f("390270340997053");
  
  ArrayList fbImageList = new ArrayList();
  
  a mAsyncRunner = new a(this.facebook);
  
  private SharedPreferences mPrefs;
  
  public boolean queueHandleRef_ = false;
  
  public boolean queueLogin_ = false;
  
  public String requestID_ = null;
  
  public boolean requestQueued_ = false;
  
  private String userId_ = null;
  
  public void appRequestFriends(String paramString) {
    Bundle bundle = new Bundle();
    bundle.putString("message", paramString);
    this.facebook.a((Context)this.cAct_, "apprequests", bundle, new ax(this));
  }
  
  public void appRequestFriendsRefID(String paramString1, String paramString2) {
    Bundle bundle = new Bundle();
    bundle.putString("message", paramString1);
    bundle.putString("data", "{\"refID\":\"" + paramString2 + "\"}");
    this.facebook.a((Context)this.cAct_, "apprequests", bundle, new ax(this));
  }
  
  public void checkForIncoming() {
    if (this.requestID_ == null) {
      Uri uri = this.cAct_.getIntent().getData();
      if (uri != null) {
        String str = uri.getQueryParameter("request_ids");
        if (str != null)
          this.requestID_ = str.split(",")[0]; 
      } 
    } 
  }
  
  public void checkHandleIncoming() {
    if (this.requestID_ != null && !this.requestQueued_) {
      this.requestQueued_ = true;
      this.mAsyncRunner.a(this.requestID_, new az(this));
    } 
  }
  
  public void downloadImage(String paramString1, String paramString2) {
    try {
      File file = new File();
      StringBuilder stringBuilder = new StringBuilder();
      this("/data/data/com.robtopx.boomlings/");
      this(stringBuilder.append(paramString2).toString());
      if (!file.exists() || shouldRedownloadImage(paramString2)) {
        if (file.exists())
          file.delete(); 
        URL uRL2 = new URL();
        this(paramString1);
        HttpURLConnection httpURLConnection = (HttpURLConnection)uRL2.openConnection();
        httpURLConnection.setInstanceFollowRedirects(false);
        URL uRL1 = new URL();
        this(httpURLConnection.getHeaderField("Location"));
        String str = uRL1.getFile();
        if (str.length() >= 4) {
          str = str.substring(str.length() - 4, str.length());
          Log.d("ImageManager", str);
          if (!str.equals(".jpg")) {
            this.fbImageList.add(paramString2);
            return;
          } 
          file.createNewFile();
          InputStream inputStream = uRL1.openConnection().getInputStream();
          BufferedInputStream bufferedInputStream = new BufferedInputStream();
          this(inputStream);
          ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer();
          this(50);
          while (true) {
            int j = bufferedInputStream.read();
            if (j == -1) {
              FileOutputStream fileOutputStream = new FileOutputStream();
              this(file);
              fileOutputStream.write(byteArrayBuffer.toByteArray());
              fileOutputStream.close();
              this.fbImageList.add(paramString2);
              return;
            } 
            byteArrayBuffer.append((byte)j);
          } 
        } 
      } 
    } catch (IOException iOException) {
      Log.d("ImageManager", "Error: " + iOException);
    } 
  }
  
  public void getFacebookScores() {
    Bundle bundle = new Bundle();
    bundle.putString("access_token", this.facebook.c());
    try {
      String str3 = this.facebook.a("390270340997053/scores", bundle, "GET");
      String str2 = "";
      String str1 = str2;
      try {
        JSONObject jSONObject = new JSONObject();
        str1 = str2;
        this(str3);
        str1 = str2;
        JSONArray jSONArray = jSONObject.getJSONArray("data");
        str1 = str2;
        String[][] arrayOfString1 = new String[jSONArray.length()][3];
        str1 = str2;
        String[] arrayOfString = new String[3];
        byte b = 0;
        for (int j = 0;; j = k) {
          String str4;
          String str5;
          str1 = str2;
          if (b >= jSONArray.length()) {
            str1 = str2;
            au au = new au();
            str1 = str2;
            this(this);
            str1 = str2;
            Arrays.sort(arrayOfString1, au);
            j = 1;
            b = 0;
            while (true) {
              str1 = str2;
              if (b >= arrayOfString1.length || b >= 25) {
                str1 = str2;
                if (arrayOfString[0] != null) {
                  str5 = str2;
                  if (!j) {
                    str1 = str2;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    str1 = str2;
                    this(String.valueOf(str2));
                    str1 = str2;
                    str5 = stringBuilder1.append("|").toString();
                  } 
                  str1 = str5;
                  StringBuilder stringBuilder = new StringBuilder();
                  str1 = str5;
                  this(String.valueOf(str5));
                  str1 = str5;
                  str4 = stringBuilder.append(arrayOfString[0]).append(",").append(arrayOfString[1]).append(",").append(arrayOfString[2]).toString();
                  str1 = str4;
                  getImageForUserId(arrayOfString[1]);
                  str1 = str4;
                } 
              } else {
                int m = j;
                str1 = str4;
                if (arrayOfString1[b] != null) {
                  m = j;
                  str1 = str4;
                  if (arrayOfString1[b][0] != null) {
                    str5 = str4;
                    if (!j) {
                      str1 = str4;
                      StringBuilder stringBuilder1 = new StringBuilder();
                      str1 = str4;
                      this(String.valueOf(str4));
                      str1 = str4;
                      str5 = stringBuilder1.append("|").toString();
                    } 
                    str1 = str5;
                    StringBuilder stringBuilder = new StringBuilder();
                    str1 = str5;
                    this(String.valueOf(str5));
                    str1 = str5;
                    String str = stringBuilder.append(arrayOfString1[b][0]).append(",").append(arrayOfString1[b][1]).append(",").append(arrayOfString1[b][2]).toString();
                    getImageForUserId(arrayOfString1[b][1]);
                    str1 = str;
                    m = 0;
                  } 
                } 
                b++;
                j = m;
                str4 = str1;
                continue;
              } 
              JniToCpp.onFacebookScoresRecieved(str1);
            } 
          } 
          str1 = str4;
          JSONObject jSONObject2 = str5.getJSONObject(b);
          str1 = str4;
          JSONObject jSONObject3 = jSONObject2.getJSONObject("user");
          str1 = str4;
          JSONObject jSONObject1 = jSONObject2.getJSONObject("application");
          str1 = str4;
          String str6 = jSONObject3.getString("name");
          str1 = str4;
          String str8 = jSONObject3.getString("id");
          str1 = str4;
          String str7 = jSONObject2.getString("score");
          int k = j;
          str1 = str4;
          if (jSONObject1.getString("id").equals("390270340997053")) {
            str1 = str4;
            if (str8.equals(this.userId_)) {
              arrayOfString[0] = str6;
              arrayOfString[1] = str8;
              arrayOfString[2] = str7;
              k = j;
            } else {
              arrayOfString1[j][0] = str6;
              arrayOfString1[j][1] = str8;
              arrayOfString1[j][2] = str7;
              k = j + 1;
            } 
          } 
          b++;
        } 
      } catch (JSONException jSONException) {
        JniToCpp.onFacebookScoresRecieved(str1);
      } 
    } catch (FileNotFoundException fileNotFoundException) {
      fileNotFoundException.printStackTrace();
    } catch (MalformedURLException malformedURLException) {
      malformedURLException.printStackTrace();
    } catch (IOException iOException) {
      iOException.printStackTrace();
    } 
  }
  
  public String getFacebookUserId() {
    return this.userId_;
  }
  
  public void getImageForUserId(String paramString) {
    downloadImage("http://graph.facebook.com/" + paramString + "/picture", String.valueOf(paramString) + ".jpg");
  }
  
  public String getTopScore() {
    null = new Bundle();
    null.putString("access_token", this.facebook.c());
    try {
      String str = this.facebook.a("me/scores", null, "GET");
      try {
        JSONObject jSONObject = new JSONObject();
        this(str);
        JSONArray jSONArray = jSONObject.getJSONArray("data");
        byte b = 0;
        while (true) {
          int j = jSONArray.length();
          if (b >= j)
            return "0"; 
          JSONObject jSONObject1 = jSONArray.getJSONObject(b);
          JSONObject jSONObject2 = jSONObject1.getJSONObject("application");
          String str1 = jSONObject1.getString("score");
          boolean bool = jSONObject2.getString("id").equals("390270340997053");
          if (!bool) {
            b++;
            continue;
          } 
          return str1;
        } 
      } catch (JSONException jSONException) {
        jSONException = null;
      } 
      return (String)jSONException;
    } catch (FileNotFoundException fileNotFoundException) {
      fileNotFoundException.printStackTrace();
    } catch (MalformedURLException malformedURLException) {
      malformedURLException.printStackTrace();
    } catch (IOException iOException) {
      iOException.printStackTrace();
    } 
    return null;
  }
  
  public void getUserId() {
    if (this.userId_ == null)
      (new Thread(new at(this))).start(); 
  }
  
  public boolean isLoggedIn() {
    return this.facebook.b();
  }
  
  public void login() {
    Log.d("Facebook", "Trying to login!");
    this.queueLogin_ = false;
    if (!this.facebook.b())
      this.facebook.a((Activity)this.cAct_, new String[] { "publish_actions", "email" }, this); 
  }
  
  public void logout() {
    this.mAsyncRunner.a((Context)this.cAct_, this);
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    if (paramIntent != null)
      this.facebook.a(paramInt1, paramInt2, paramIntent); 
  }
  
  public void onCancel() {
    Log.d("Facebook", "onCancel 1");
    JniToCpp.onFacebookError();
  }
  
  public void onComplete(Bundle paramBundle) {
    SharedPreferences.Editor editor = this.mPrefs.edit();
    editor.putString("access_token", this.facebook.c());
    editor.putLong("access_expires", this.facebook.d());
    editor.commit();
    Log.d("Facebook", "onComplete 1");
    getUserId();
    JniToCpp.onFacebookLogin();
    checkForIncoming();
    checkHandleIncoming();
    this.cAct_.runOnUiThread(new av(this));
  }
  
  public void onComplete(String paramString, Object paramObject) {
    Log.d("Facebook", "onComplete 2");
    this.userId_ = null;
    JniToCpp.onFacebookLogout();
    this.cAct_.runOnUiThread(new aw(this));
  }
  
  public void onError(e parame) {
    Log.d("Facebook", "onError 1");
    JniToCpp.onFacebookError();
  }
  
  public void onFacebookError(m paramm) {
    Log.d("Facebook", "onFacebookError 1");
    JniToCpp.onFacebookError();
  }
  
  public void onFacebookError(m paramm, Object paramObject) {
    Log.d("Facebook", "onFacebookError 2");
    JniToCpp.onFacebookError();
  }
  
  public void onFileNotFoundException(FileNotFoundException paramFileNotFoundException, Object paramObject) {
    Log.d("Facebook", "onFileNotFoundException 2");
    JniToCpp.onFacebookError();
  }
  
  public void onIOException(IOException paramIOException, Object paramObject) {
    Log.d("Facebook", "onIOException 2");
    JniToCpp.onFacebookError();
  }
  
  public void onMalformedURLException(MalformedURLException paramMalformedURLException, Object paramObject) {
    Log.d("Facebook", "onMalformedURLException 2");
    JniToCpp.onFacebookError();
  }
  
  public void onResume() {
    checkForIncoming();
    if (this.facebook != null) {
      if (!this.facebook.b()) {
        if (this.requestID_ != null) {
          Log.d("Facebook", "FB Queue Login!");
          this.queueLogin_ = true;
          return;
        } 
        Log.d("Facebook", "No FB Queue Login!");
        return;
      } 
    } else {
      return;
    } 
    this.facebook.b((Context)this.cAct_, null);
    if (this.requestID_ != null) {
      Log.d("Facebook", "FB Queue handle ref!");
      this.queueHandleRef_ = true;
      return;
    } 
    Log.d("Facebook", "No FB Queue handle ref!");
  }
  
  public void performQueuedFBAction() {
    Log.d("Facebook", "Perform Queued FB Action");
    if (this.queueLogin_) {
      Log.d("Facebook", "Queued Login!");
      this.queueLogin_ = false;
      login();
      return;
    } 
    if (this.queueHandleRef_) {
      Log.d("Facebook", "Queued Handle Ref!");
      this.queueHandleRef_ = false;
      checkForIncoming();
      checkHandleIncoming();
    } 
  }
  
  public void postScore(String paramString) {
    String str = getTopScore();
    if (str != null) {
      long l = Long.parseLong(paramString);
      if (Long.parseLong(str) >= l) {
        JniToCpp.onFacebookScoreSubmitted();
        return;
      } 
      Bundle bundle = new Bundle();
      bundle.putString("score", paramString);
      bundle.putString("access_token", this.facebook.c());
      try {
        paramString = this.facebook.a("me/scores", bundle, "POST");
        System.out.println(paramString);
        JniToCpp.onFacebookScoreSubmitted();
      } catch (FileNotFoundException fileNotFoundException) {
        fileNotFoundException.printStackTrace();
      } catch (MalformedURLException malformedURLException) {
        malformedURLException.printStackTrace();
      } catch (IOException iOException) {
        iOException.printStackTrace();
      } 
    } 
  }
  
  public void postToWall(String paramString1, String paramString2, String paramString3, String paramString4) {
    Bundle bundle = new Bundle();
    bundle.putString("caption", paramString1);
    bundle.putString("description", paramString2);
    bundle.putString("picture", paramString3);
    bundle.putString("name", paramString4);
    this.facebook.a((Context)this.cAct_, "feed", bundle, new ba(this));
    paramString1 = this.facebook.c();
    System.out.println(paramString1);
  }
  
  public void setup(Cocos2dxActivity paramCocos2dxActivity) {
    this.cAct_ = paramCocos2dxActivity;
    me = this;
    this.mPrefs = this.cAct_.getPreferences(0);
    String str = this.mPrefs.getString("access_token", null);
    long l = this.mPrefs.getLong("access_expires", 0L);
    if (str != null)
      this.facebook.a(str); 
    if (l != 0L)
      this.facebook.a(l); 
    if (this.facebook.b())
      getUserId(); 
  }
  
  public boolean shouldRedownloadImage(String paramString) {
    boolean bool = false;
    for (byte b = 0;; b++) {
      if (b >= this.fbImageList.size())
        return true; 
      if (((String)this.fbImageList.get(b)).equals(paramString)) {
        Log.d("Facebook", "Already downloaded " + paramString + ", skipping");
        return bool;
      } 
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\as.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */