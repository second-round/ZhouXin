package com.example.projectone.model;


import android.widget.TextView;

import com.example.projectone.callback.CallBack;

public interface Model<T> {
    void sendMessage(String path, Class aClass, CallBack callBack);
}
