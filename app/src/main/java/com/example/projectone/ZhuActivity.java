package com.example.projectone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectone.callback.CallBack;
import com.example.projectone.model.RegBean;
import com.example.projectone.persenter.PersenterImpl;
import com.example.projectone.view.IView;

public class ZhuActivity extends AppCompatActivity implements View.OnClickListener,IView {
    private EditText phone,pass;
    private Button button;
    private PersenterImpl persenter;
    private String path="http://120.27.23.105/user/reg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu);
        phone=findViewById(R.id.phone);
        persenter=new PersenterImpl(this);
        pass=findViewById(R.id.pass);
        button=findViewById(R.id.zhuce);
        button.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhuce:
                String string = phone.getText().toString();
                String string1 = pass.getText().toString();
                persenter.sendMessage(path+"?mobile="+string+"&password="+string1,RegBean.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void setClass(Object clazz) {
        Log.i("TAG","ettt");
        RegBean regBean= (RegBean) clazz;
        Log.i("TAG","eeee");
        int code = regBean.getCode();
        Log.i("TAG",regBean.getMsg());
        Toast.makeText(ZhuActivity.this,regBean.getMsg(),Toast.LENGTH_LONG).show();
        finish();
    }
}
