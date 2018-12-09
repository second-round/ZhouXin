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

}
