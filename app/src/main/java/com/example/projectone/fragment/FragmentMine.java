package com.example.projectone.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projectone.R;
import com.example.projectone.ScanActivity;

import java.lang.ref.WeakReference;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class FragmentMine extends Fragment {
    private ImageView imageView;
    private EditText name;
    private Button sao,sheng;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mine,container,false);
        imageView=view.findViewById(R.id.imageView);
        name=view.findViewById(R.id.editText);
        sao=view.findViewById(R.id.button5);
        sheng=view.findViewById(R.id.button6);
        sheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init(name.getText().toString());
            }
        });
        sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });

        return view;
    }

    private void checkPermission() {{
        //第一步，判断系统版本是否为6.0以上
        Log.i("TAG","1");
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
        //第二步：checkSelfPermission判断有没有此权限
        //第一个参数：上下文
        //第二个参数：我们想要判断的权限，此处为相机权限
        Log.i("TAG","2");
        //PackageManager.PERMISSION_GRANTED 条件，权限有没有被授予
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //如果没授予，则申请权限
            //第一个：上下文
            Log.i("TAG","3");

            //第二个：要申请的权限数组，此处为相机权限
            //第三个：请求码，startActivityForResult一样
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},100);
        }else{
            Log.i("TAG","4");

            startActivity(new Intent(getActivity(), ScanActivity.class));
        }
//        }
    }

    }

    private void init(String s) {
        QRTask qrTask = new QRTask(getActivity(), imageView);
        qrTask.execute(s);
    }
    static class QRTask extends AsyncTask<String, Void, Bitmap> {
        private WeakReference<Context> mContext;
        private WeakReference<ImageView> mImageView;
        public QRTask(Context context, ImageView image) {
            mContext = new WeakReference<>(context);
            mImageView = new WeakReference<>(image);
        }
        @Override
        protected Bitmap doInBackground(String... params) {
            String str = params[0];
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            int size = mContext.get().getResources().getDimensionPixelOffset(R.dimen.qr_code_size);
            return QRCodeEncoder.syncEncodeQRCode(str, size);
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                mImageView.get().setImageBitmap(bitmap);
            } else {
                Toast.makeText(mContext.get(), "生成失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}