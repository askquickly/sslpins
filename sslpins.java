package sslpins;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.ShortName;
import anywheresoftware.b4a.BA.Version;
import anywheresoftware.b4a.BA.Author;
import anywheresoftware.b4a.BA.Hide;
import anywheresoftware.b4a.BA.Events;

import okhttp3.CertificatePinner;
import okhttp3.*;
// Working In Progress :)
@Author("SSLPins")
@Version(0.01f)
@ShortName("SSLPins")

public class SSLPins{
  
  public String doCheck(String url, String key){
       String hostname = "publicobject.com";
      CertificatePinner certificatePinner = new CertificatePinner.Builder()
        //  sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=
        .add(url, key)
          .build();
      OkHttpClient client = new OkHttpClient.Builder()
         .certificatePinner(certificatePinner)
         .build();
 
      Request request = new Request.Builder()
          .url("https://" + url)
          .build();
      client.newCall(request).execute();
  }
}
