package com.example.projectone.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.projectone.R;
import com.example.projectone.model.RegBean;
import com.example.projectone.model.UserBean;
import com.example.projectone.persenter.Persenter;
import com.example.projectone.persenter.PersenterImpl;
import com.example.projectone.persenter.UserAdapter;
import com.example.projectone.view.IView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;

public class FragmentHead extends Fragment implements IView {
    private Banner banner;
    private XListView xListView;
    private UserAdapter adapter;
    private PersenterImpl persenter;
    private int page;
    private List<UserBean.DataBean> data;
    private String path="http://www.xieast.com/api/news/news.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_head,container,false);
        banner=view.findViewById(R.id.banner);
        banner.setImageLoader(new ImageLoderBanner());
        xListView=view.findViewById(R.id.xlist);
        persenter=new PersenterImpl(this);
        ArrayList<String> list = new ArrayList<>();
        list.add("http://img02.store.sogou.com/app/a/10010016/872a3aea2cb3a3ec0f93168a8bfdb3b5");
        list.add("http://img03.store.sogou.com/app/a/10010016/9b34d9c0f1f507365fd89288a116fa57");
        list.add("http://img04.store.sogou.com/app/a/10010016/74786309c1ef489a38f92cbba70a4ad8");
        banner.setImages(list);
        banner.start();
        page=1;
        xListView.setPullRefreshEnable(true);
        xListView.setPullLoadEnable(true);
        adapter=new UserAdapter(getActivity());
        xListView.setAdapter(adapter);
        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                page=1;
                load();
            }
            @Override
            public void onLoadMore() {
               load();
            }
        });
        load();
        return view;
    }

    private void load() {
        persenter.sendMessage(path,UserBean.class);
    }

    @Override
    public void setClass(Object clazz) {
        UserBean userBean= (UserBean) clazz;
        data = userBean.getData();
        if (page==1){
            adapter.setList(data);
        }
        else {
            adapter.addList(data);
        }
        page++;
        xListView.stopRefresh();
        xListView.stopLoadMore();
    }


    private class ImageLoderBanner extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            com.nostra13.universalimageloader.core.ImageLoader instance = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
            instance.displayImage((String) path, imageView);
        }
    }
}