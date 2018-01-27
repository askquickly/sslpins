package uk.askquickly;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Headers;
import java.io.IOException;
import okhttp3.Response;
import okhttp3.CertificatePinner;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.IOnActivityResult;
import anywheresoftware.b4a.BA.DependsOn;
import anywheresoftware.b4a.BA.Events;
import anywheresoftware.b4a.BA.Permissions;
import anywheresoftware.b4a.BA.ShortName;
import anywheresoftware.b4a.BA.Version;
import anywheresoftware.b4a.BA.Author;
import anywheresoftware.b4a.keywords.Common;

@Author("AskQuickly")
@Version(1.1f)
@Permissions(values = {"android.permission.INTERNET"})
@DependsOn(values = {"okhttp-3.9.1","okio-1.13.0"})
@ShortName("SSLPins")
@Events(values = {"ready (response As String)"})
public class SSLPins {
 private static String eventName;
 private static BA ba;
 private static String key;

 public void Initialize(final BA ba, String evname, String KEY) {
  this.ba = ba;
  this.eventName = evname.toLowerCase(BA.cul);
  this.key = KEY;
 }
 public String doCheckRequest(String url) throws IOException {
  try {
   CertificatePinner certificatePinner = new CertificatePinner.Builder()
    .add(url, key)
    .build();

   OkHttpClient client = new OkHttpClient.Builder()
    .certificatePinner(certificatePinner)
    .build();

   Request request = new Request.Builder()
    .url("https://" + url)
    .build();

   Response response = client.newCall(request).execute();
   return response.body().string();
  } catch (IOException e) {
   BA.Log(e.getMessage());
   return "";
  }
 }
 OkHttpClient client = new OkHttpClient();
 String doPostRequest(String url, String json) throws IOException {
 CertificatePinner certificatePinner = new CertificatePinner.Builder()
  .add(url, key)
  .build();

  OkHttpClient client = new OkHttpClient.Builder()
   .certificatePinner(certificatePinner)
   .build();

	MediaType type = MediaType.parse("application/json; charset=utf-8");
    RequestBody body = RequestBody.create(type, json);
    Request request = new Request.Builder()
    .url(url)
    .post(body)
    .build();
	
    Response response = client.newCall(request).execute();
    return response.body().string();
      }
 String doGetRequest(String url) throws IOException {

  CertificatePinner certificatePinner = new CertificatePinner.Builder()
   .add(url, key)
   .build();

  OkHttpClient client = new OkHttpClient.Builder()
   .certificatePinner(certificatePinner)
   .build();

  Request request = new Request.Builder()
   .url("https://" + url)
   .build();

  Response response = client.newCall(request).execute();
  return response.body().string();
 }
 public void getRequest(String Ia) throws IOException {
  try {
   SSLPins example = new SSLPins();
   String getResponse = example.doGetRequest(Ia);
   if (ba.subExists(SSLPins.eventName + "_ready")) {
    ba.raiseEventFromUI(this, SSLPins.eventName + "_ready", getResponse);
   } else {
    BA.LogError("event sub does not exist: " + SSLPins.eventName);
   }
  } catch (IOException e) {
   // BA.Log(e.getMessage());
   ba.raiseEventFromUI(this, SSLPins.eventName + "_ready", e.getMessage());
  }
 }
 public void PostJsonRequest(String Ia, String Json) throws IOException {
  try {
   SSLPins example = new SSLPins();
   String getResponse = example.doPostRequest(Ia, Json);
   if (ba.subExists(SSLPins.eventName + "_ready")) {
    ba.raiseEventFromUI(this, SSLPins.eventName + "_ready", getResponse);
   } else {
    BA.LogError("event sub does not exist: " + SSLPins.eventName);
   }
  } catch (IOException e) {
   ba.raiseEventFromUI(this, SSLPins.eventName + "_ready", e.getMessage());
  }
 }
}
