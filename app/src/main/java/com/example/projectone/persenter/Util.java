package com.example.projectone.persenter;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Util {
    private static Util util;

    public static Boolean requestData(String phone, String pass) {
        if (check(phone)&&checkPass(pass)){
            if (phone.equals("13800138000")&&pass.equals("123456")){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    private static boolean checkPass(String pass) {
        return (!TextUtils.isEmpty(pass)&&pass.length()>=6);
    }

    private static boolean check(String phone) {
        return (!TextUtils.isEmpty(phone)&&phone.length()==11);
    }


//    public static Class sengMessage(String string, String string1, final String path, final Class aClass) {
//        Class clazz =null;
//        new AsyncTask<String, Void, Class>() {
//            @Override
//            protected Class doInBackground(String... strings) {
//                try {
//                    URL url=new URL(path);
//                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                    urlConnection.setReadTimeout(5000);
//                    urlConnection.setConnectTimeout(5000);
//                    int responseCode = urlConnection.getResponseCode();
//                    if (responseCode==200){
//                        String s=stream2string(urlConnection.getInputStream());
//                        clazz = new Gson().fromJson(s, aClass);
//                        return (Class) o;
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Class aClass) {
//                super.onPostExecute(aClass);
//            }
//        }.execute("");
//    }
//
//    private static String stream2string(InputStream inputStream) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//        StringBuffer stringBuffer = new StringBuffer();
//        String str=null;
//        while ((str=reader.readLine())!=null){
//            stringBuffer.append(str);
//        }
//        return stringBuffer.toString();
//    }
}
