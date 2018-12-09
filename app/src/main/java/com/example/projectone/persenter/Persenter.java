package com.example.projectone.persenter;

import com.example.projectone.model.RegBean;

public interface Persenter<T> {
    void sendMessage(String path, Class<RegBean> regBeanClass);
}
