package com.example.projectone;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectone.model.RegBean;
import com.example.projectone.persenter.PersenterImpl;
import com.example.projectone.view.IView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView {
    private EditText name,pass;
    private Button button,login,zhu;
    private SharedPreferences sharedPreferences;
    private CheckBox pwd,logins;
    private SharedPreferences.Editor edit;
    private PersenterImpl persenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiew();
//        checkLogin();
    }

    private void initiew() {
        name=findViewById(R.id.name);
        pass=findViewById(R.id.editText2);
        button=findViewById(R.id.button3);
        login=findViewById(R.id.button2);
        pwd=findViewById(R.id.checkBox);
        sharedPreferences=getSharedPreferences("user",MODE_PRIVATE);
        edit = sharedPreferences.edit();
        logins=findViewById(R.id.checkBox2);
        zhu=findViewById(R.id.button2);
        pwd.setOnClickListener(this);
        logins.setOnClickListener(this);
        button.setOnClickListener(this);
        persenter=new PersenterImpl(this);
        zhu.setOnClickListener(this);

    }
    private void checkLogin() {
        boolean pwd = sharedPreferences.getBoolean("pwd", false);
        if (pwd){
            name.setText(sharedPreferences.getString("phone",null));
            pass.setText(sharedPreferences.getString("pass",null));
            this.pwd.setChecked(true);
        }
        boolean logins = sharedPreferences.getBoolean("logins", false);
        if (logins){
            checkPermission();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                persenter.sendMessage("http://www.zhaoapi.cn/user/login?mobile="+name.getText().toString()+"&password="+pass.getText().toString(),RegBean.class);
                break;
            case R.id.button2:
                Intent intent=new Intent(MainActivity.this,ZhuActivity.class);
                startActivity(intent);
                break;
            case R.id.button3:
                UMShareAPI umShareAPI =  UMShareAPI.get(MainActivity.this);

                //开始登录
                //第一个参数：上下文
                //第二个参数，登录哪种平台
                //第三个参数，添加回调
                umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    /**
                     * 开始登录回调
                     * @param share_media
                     */
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Log.i("dj", "UMAuthListener onStart");
                    }

                    /**
                     * 登录完成
                     * @param share_media
                     * @param i
                     * @param map
                     */
                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        //头像，昵称，如果拿不到，自己debug看Key是啥，再问打死
                        Log.i("dj", map+"");

//                        //获取名字
//                        String name  = map.get("screen_name");
//                        //获取头像
//                        String img  = map.get("profile_image_url");
//
//                        Log.i("dj", "name is "+ name);
//                        Log.i("dj", "img is "+ img);
                    }

                    /**
                     * 登录失败
                     * @param share_media
                     * @param i
                     * @param throwable
                     */
                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        Log.i("dj", "UMAuthListener onError" + throwable.getLocalizedMessage());
                    }

                    /**
                     * 登录取消
                     * @param share_media
                     * @param i
                     */
                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                        Log.i("dj", "UMAuthListener onCancel");
                    }
                });
                break;
            case R.id.checkBox:
                if (!pwd.isChecked()){
                    logins.setChecked(false);
                }
                break;
            case R.id.checkBox2:
                if (logins.isChecked()){
                    pwd.setChecked(true);
                }
                break;
            default:
                break;
        }
    }

    private void check() {
        if (pwd.isChecked()){
            edit.putBoolean("pwd",true);
            edit.putString("phone",name.getText().toString());
            edit.putString("pass",pass.getText().toString());
        }else {
            edit.clear();
        }
        if (logins.isChecked()){
            edit.putBoolean("logins",true);
        }
        else {
            edit.putBoolean("logins",false);
        }
        edit.commit();
    }

    private void checkPermission() {
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void setClass(Object clazz) {
        RegBean regBean= (RegBean) clazz;
        Toast.makeText(MainActivity.this,regBean.getMsg(),Toast.LENGTH_LONG).show();
        if (regBean.getCode()==0){
            check();
            Intent intent1=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent1);
            finish();
        }
    }
}