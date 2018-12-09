package com.example.projectone.persenter;

import com.example.projectone.model.RegBean;
import com.example.projectone.model.UserBean;

public interface Persenter<T> {
    void sendMessage(String path, Class<RegBean> regBeanClass);
//    void opinion(String phone, String pass);
//
//    void option(String path, T t );
}
