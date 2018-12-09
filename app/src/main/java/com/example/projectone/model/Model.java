package com.example.projectone.model;


import android.widget.TextView;

import com.example.projectone.callback.CallBack;

public interface Model<T> {
    void sendMessage(String path, Class aClass, CallBack callBack);


//    void requestData(String phone, String pass, CallBack callBack);
//
//    void getdata(String path, T t,CallBack callBack);
//
//    void getbean(UserBean userBean,CallBack callBack);
}
