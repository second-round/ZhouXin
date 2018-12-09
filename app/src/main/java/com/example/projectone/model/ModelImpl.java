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
//    CallBack callBack;
//    @Override
//    public void requestData(final String phone, final String pass, final CallBack callBack) {
//        this.callBack=callBack;
//        Boolean aBoolean = Util.requestData(phone, pass);
//        callBack.setData(aBoolean);
//    }
//
//    @SuppressLint("StaticFieldLeak")
//    @Override
//    public void getdata(String path, Object o, final CallBack callBack) {
//        this.callBack=callBack;
//        new AsyncTask<String, Void, UserBean>() {
//            @Override
//            protected UserBean doInBackground(String... strings) {
//                try {
//                    URL url=new URL(strings[0]);
//                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                    urlConnection.setConnectTimeout(5000);
//                    urlConnection.setReadTimeout(5000);
//                    int responseCode = urlConnection.getResponseCode();
//                    if (responseCode==200){
//                        String s=stream2string(urlConnection.getInputStream());
//                        UserBean userBean = new Gson().fromJson(s, UserBean.class);
//                        return userBean;
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(UserBean userBean) {
//                super.onPostExecute(userBean);
//                callBack.setbean(userBean);
//            }
//        }.execute(path);
//    }
//
//    @Override
//    public void getbean(UserBean userBean, CallBack callBack) {
//        this.callBack=callBack;
//        callBack.setbean(userBean);
//    }
//
//    private String stream2string(InputStream inputStream) throws IOException {
//        InputStreamReader reader=new InputStreamReader(inputStream);
//        BufferedReader bf=new BufferedReader(reader);
//        StringBuilder builder=new StringBuilder();
//        for (String emp=bf.readLine();emp!=null;emp=bf.readLine()){
//            builder.append(emp);
//        }
//        return builder.toString();
//    }
}