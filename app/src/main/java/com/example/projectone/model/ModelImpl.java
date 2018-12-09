package com.example.projectone.model;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.example.projectone.callback.CallBack;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class ModelImpl implements Model{
    @SuppressLint("StaticFieldLeak")
    @Override
    public void sendMessage(final String path, final Class aClass, final CallBack callBack) {
        new AsyncTask<String, Void, Object>() {
            @Override
            protected Object doInBackground(String... strings) {
                try {
                    URL url=new URL(path);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setReadTimeout(5000);
                    urlConnection.setConnectTimeout(5000);
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode==200){
                        String s=stream2string(urlConnection.getInputStream());
                        Log.i("TAG",s+"");
                        Object clazz =  new Gson().fromJson(s, aClass);
                        return  clazz;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Object aClass) {
                super.onPostExecute(aClass);
                callBack.setbean(aClass);
            }
        }.execute("");
    }
    private static String stream2string(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer stringBuffer = new StringBuffer();
        String str=null;
        while ((str=reader.readLine())!=null){
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }
}