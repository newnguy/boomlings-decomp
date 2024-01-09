package org.cocos2dx.lib;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class as implements com.b.a.d, com.b.a.i {
    public static as me = null;
    public Cocos2dxActivity cAct_;
    private SharedPreferences mPrefs;
    final String TAG = "Facebook";
    final String AppID = "390270340997053";
    final String FBUseCount = "fb_app_usage_count";
    final String FBUseCountEnabled = "fb_app_usage_check_enable";
    private String userId_ = null;
    public String requestID_ = null;
    public boolean queueLogin_ = false;
    public boolean queueHandleRef_ = false;
    public boolean requestQueued_ = false;
    ArrayList fbImageList = new ArrayList();
    com.b.a.f facebook = new com.b.a.f("390270340997053");
    com.b.a.a mAsyncRunner = new com.b.a.a(this.facebook);

    public void appRequestFriends(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("message", str);
        this.facebook.a(this.cAct_, "apprequests", bundle, new ax(this));
    }

    public void appRequestFriendsRefID(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("message", str);
        bundle.putString("data", "{\"refID\":\"" + str2 + "\"}");
        this.facebook.a(this.cAct_, "apprequests", bundle, new ax(this));
    }

    public void checkForIncoming() {
        Uri data;
        String queryParameter;
        if (this.requestID_ != null || (data = this.cAct_.getIntent().getData()) == null || (queryParameter = data.getQueryParameter("request_ids")) == null) {
            return;
        }
        this.requestID_ = queryParameter.split(",")[0];
    }

    public void checkHandleIncoming() {
        if (this.requestID_ == null || this.requestQueued_) {
            return;
        }
        this.requestQueued_ = true;
        this.mAsyncRunner.a(this.requestID_, new az(this));
    }

    public void downloadImage(String str, String str2) {
        try {
            File file = new File("/data/data/com.robtopx.boomlings/" + str2);
            if (file.exists() && !shouldRedownloadImage(str2)) {
                return;
            }
            if (file.exists()) {
                file.delete();
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setInstanceFollowRedirects(false);
            URL url = new URL(httpURLConnection.getHeaderField("Location"));
            String file2 = url.getFile();
            if (file2.length() < 4) {
                return;
            }
            String substring = file2.substring(file2.length() - 4, file2.length());
            Log.d("ImageManager", substring);
            if (!substring.equals(".jpg")) {
                this.fbImageList.add(str2);
                return;
            }
            file.createNewFile();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openConnection().getInputStream());
            ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(50);
            while (true) {
                int read = bufferedInputStream.read();
                if (read == -1) {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(byteArrayBuffer.toByteArray());
                    fileOutputStream.close();
                    this.fbImageList.add(str2);
                    return;
                }
                byteArrayBuffer.append((byte) read);
            }
        } catch (IOException e) {
            Log.d("ImageManager", "Error: " + e);
        }
    }

    public void getFacebookScores() {
        Bundle bundle = new Bundle();
        bundle.putString("access_token", this.facebook.c());
        try {
            String str = "";
            try {
                JSONArray jSONArray = new JSONObject(this.facebook.a("390270340997053/scores", bundle, "GET")).getJSONArray("data");
                String[][] strArr = (String[][]) Array.newInstance(String.class, jSONArray.length(), 3);
                String[] strArr2 = new String[3];
                int i = 0;
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    JSONObject jSONObject2 = jSONObject.getJSONObject("user");
                    JSONObject jSONObject3 = jSONObject.getJSONObject("application");
                    String string = jSONObject2.getString("name");
                    String string2 = jSONObject2.getString("id");
                    String string3 = jSONObject.getString("score");
                    if (jSONObject3.getString("id").equals("390270340997053")) {
                        if (string2.equals(this.userId_)) {
                            strArr2[0] = string;
                            strArr2[1] = string2;
                            strArr2[2] = string3;
                        } else {
                            strArr[i][0] = string;
                            strArr[i][1] = string2;
                            strArr[i][2] = string3;
                            i++;
                        }
                    }
                }
                Arrays.sort(strArr, new au(this));
                boolean z = true;
                for (int i3 = 0; i3 < strArr.length && i3 < 25; i3++) {
                    if (strArr[i3] != null && strArr[i3][0] != null) {
                        if (!z) {
                            str = String.valueOf(str) + "|";
                        }
                        String str2 = String.valueOf(str) + strArr[i3][0] + "," + strArr[i3][1] + "," + strArr[i3][2];
                        try {
                            getImageForUserId(strArr[i3][1]);
                            str = str2;
                            z = false;
                        } catch (JSONException e) {
                            str = str2;
                        }
                    }
                }
                if (strArr2[0] != null) {
                    if (!z) {
                        str = String.valueOf(str) + "|";
                    }
                    str = String.valueOf(str) + strArr2[0] + "," + strArr2[1] + "," + strArr2[2];
                    getImageForUserId(strArr2[1]);
                }
            } catch (JSONException e2) {
            }
            JniToCpp.onFacebookScoresRecieved(str);
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
        } catch (MalformedURLException e4) {
            e4.printStackTrace();
        } catch (IOException e5) {
            e5.printStackTrace();
        }
    }

    public String getFacebookUserId() {
        return this.userId_;
    }

    public void getImageForUserId(String str) {
        downloadImage("http://graph.facebook.com/" + str + "/picture", String.valueOf(str) + ".jpg");
    }

    public String getTopScore() {
        Bundle bundle = new Bundle();
        bundle.putString("access_token", this.facebook.c());
        try {
            try {
                JSONArray jSONArray = new JSONObject(this.facebook.a("me/scores", bundle, "GET")).getJSONArray("data");
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= jSONArray.length()) {
                        return "0";
                    }
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    JSONObject jSONObject2 = jSONObject.getJSONObject("application");
                    String string = jSONObject.getString("score");
                    if (jSONObject2.getString("id").equals("390270340997053")) {
                        return string;
                    }
                    i = i2 + 1;
                }
            } catch (JSONException e) {
                return null;
            }
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            return null;
        } catch (MalformedURLException e3) {
            e3.printStackTrace();
            return null;
        } catch (IOException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    public void getUserId() {
        if (this.userId_ != null) {
            return;
        }
        new Thread(new at(this)).start();
    }

    public boolean isLoggedIn() {
        return this.facebook.b();
    }

    public void login() {
        Log.d("Facebook", "Trying to login!");
        this.queueLogin_ = false;
        String[] strArr = {"publish_actions", "email"};
        if (this.facebook.b()) {
            return;
        }
        this.facebook.a(this.cAct_, strArr, this);
    }

    public void logout() {
        this.mAsyncRunner.a(this.cAct_, this);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (intent == null) {
            return;
        }
        this.facebook.a(i, i2, intent);
    }

    @Override // com.b.a.i
    public void onCancel() {
        Log.d("Facebook", "onCancel 1");
        JniToCpp.onFacebookError();
    }

    @Override // com.b.a.i
    public void onComplete(Bundle bundle) {
        SharedPreferences.Editor edit = this.mPrefs.edit();
        edit.putString("access_token", this.facebook.c());
        edit.putLong("access_expires", this.facebook.d());
        edit.commit();
        Log.d("Facebook", "onComplete 1");
        getUserId();
        JniToCpp.onFacebookLogin();
        checkForIncoming();
        checkHandleIncoming();
        this.cAct_.runOnUiThread(new av(this));
    }

    @Override // com.b.a.d
    public void onComplete(String str, Object obj) {
        Log.d("Facebook", "onComplete 2");
        this.userId_ = null;
        JniToCpp.onFacebookLogout();
        this.cAct_.runOnUiThread(new aw(this));
    }

    @Override // com.b.a.i
    public void onError(com.b.a.e eVar) {
        Log.d("Facebook", "onError 1");
        JniToCpp.onFacebookError();
    }

    @Override // com.b.a.i
    public void onFacebookError(com.b.a.m mVar) {
        Log.d("Facebook", "onFacebookError 1");
        JniToCpp.onFacebookError();
    }

    @Override // com.b.a.d
    public void onFacebookError(com.b.a.m mVar, Object obj) {
        Log.d("Facebook", "onFacebookError 2");
        JniToCpp.onFacebookError();
    }

    @Override // com.b.a.d
    public void onFileNotFoundException(FileNotFoundException fileNotFoundException, Object obj) {
        Log.d("Facebook", "onFileNotFoundException 2");
        JniToCpp.onFacebookError();
    }

    @Override // com.b.a.d
    public void onIOException(IOException iOException, Object obj) {
        Log.d("Facebook", "onIOException 2");
        JniToCpp.onFacebookError();
    }

    @Override // com.b.a.d
    public void onMalformedURLException(MalformedURLException malformedURLException, Object obj) {
        Log.d("Facebook", "onMalformedURLException 2");
        JniToCpp.onFacebookError();
    }

    public void onResume() {
        checkForIncoming();
        if (this.facebook != null) {
            if (!this.facebook.b()) {
                if (this.requestID_ == null) {
                    Log.d("Facebook", "No FB Queue Login!");
                    return;
                }
                Log.d("Facebook", "FB Queue Login!");
                this.queueLogin_ = true;
                return;
            }
            this.facebook.b(this.cAct_, (com.b.a.j) null);
            if (this.requestID_ == null) {
                Log.d("Facebook", "No FB Queue handle ref!");
                return;
            }
            Log.d("Facebook", "FB Queue handle ref!");
            this.queueHandleRef_ = true;
        }
    }

    public void performQueuedFBAction() {
        Log.d("Facebook", "Perform Queued FB Action");
        if (this.queueLogin_) {
            Log.d("Facebook", "Queued Login!");
            this.queueLogin_ = false;
            login();
        } else if (this.queueHandleRef_) {
            Log.d("Facebook", "Queued Handle Ref!");
            this.queueHandleRef_ = false;
            checkForIncoming();
            checkHandleIncoming();
        }
    }

    public void postScore(String str) {
        String topScore = getTopScore();
        if (topScore == null) {
            return;
        }
        if (Long.parseLong(topScore) >= Long.parseLong(str)) {
            JniToCpp.onFacebookScoreSubmitted();
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("score", str);
        bundle.putString("access_token", this.facebook.c());
        try {
            System.out.println(this.facebook.a("me/scores", bundle, "POST"));
            JniToCpp.onFacebookScoreSubmitted();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public void postToWall(String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        bundle.putString("caption", str);
        bundle.putString("description", str2);
        bundle.putString("picture", str3);
        bundle.putString("name", str4);
        this.facebook.a(this.cAct_, "feed", bundle, new ba(this));
        System.out.println(this.facebook.c());
    }

    public void setup(Cocos2dxActivity cocos2dxActivity) {
        this.cAct_ = cocos2dxActivity;
        me = this;
        this.mPrefs = this.cAct_.getPreferences(0);
        String string = this.mPrefs.getString("access_token", null);
        long j = this.mPrefs.getLong("access_expires", 0L);
        if (string != null) {
            this.facebook.a(string);
        }
        if (j != 0) {
            this.facebook.a(j);
        }
        if (this.facebook.b()) {
            getUserId();
        }
    }

    public boolean shouldRedownloadImage(String str) {
        for (int i = 0; i < this.fbImageList.size(); i++) {
            if (((String) this.fbImageList.get(i)).equals(str)) {
                Log.d("Facebook", "Already downloaded " + str + ", skipping");
                return false;
            }
        }
        return true;
    }
}
