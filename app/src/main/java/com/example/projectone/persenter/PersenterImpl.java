package com.example.projectone.persenter;


import android.util.Log;

import com.example.projectone.callback.CallBack;
import com.example.projectone.model.ModelImpl;
import com.example.projectone.model.UserBean;
import com.example.projectone.view.IView;

public class PersenterImpl implements Persenter{
    private IView view;
    private ModelImpl model;
//
    public PersenterImpl(IView view) {
        this.view = view;
        model=new ModelImpl();
    }

    @Override
    public void sendMessage(String path, Class aClass) {

        Log.i("TAG","persenter____");
        model.sendMessage(path,aClass, new CallBack() {
            @Override
            public void setData(boolean result) {

            }

            @Override
            public void setbean(Object clazz) {
                view.setClass(clazz);

            }
        });
    }
}
