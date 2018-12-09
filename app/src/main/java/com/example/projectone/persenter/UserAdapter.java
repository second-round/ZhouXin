package com.example.projectone.persenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectone.R;
import com.example.projectone.model.UserBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends BaseAdapter {
    private List<UserBean.DataBean> list;
    private Context context;
    private final int NUM=2;

    public UserAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<UserBean.DataBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public void addList(List<UserBean.DataBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        return list.get(position).getThumbnail_pic_s02()==null&&list.get(position).getThumbnail_pic_s03()==null?0:1;
    }

    @Override
    public int getViewTypeCount() {
        return NUM;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public UserBean.DataBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView=LayoutInflater.from(context).inflate(
                    getItemViewType(position)==0?R.layout.item_text:R.layout.item_image,parent,false
            );
            holder=new ViewHolder(convertView);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        if (getItemViewType(position)==0){
            holder.setText(getItem(position));
        }else {
            holder.setImage(getItem(position));
        }
        return convertView;
    }
    class ViewHolder{
        TextView textView1,textView2;
        ImageView imageView1,imageView2,imageView3;

        public ViewHolder(View convertView) {
            textView1=convertView.findViewById(R.id.text1);
            textView2=convertView.findViewById(R.id.text2);
            imageView1=convertView.findViewById(R.id.image1);
            imageView2=convertView.findViewById(R.id.image2);
            imageView3=convertView.findViewById(R.id.image3);
            convertView.setTag(this);
        }
        public void setText(UserBean.DataBean dataBean){
            textView1.setText(dataBean.getTitle());
            textView2.setText(dataBean.getAuthor_name());
            ImageLoader.getInstance().displayImage(dataBean.getThumbnail_pic_s(),imageView1);
        }
        public void setImage(UserBean.DataBean dataBean){
            textView1.setText(dataBean.getTitle());
            ImageLoader.getInstance().displayImage(dataBean.getThumbnail_pic_s(),imageView1);
            ImageLoader.getInstance().displayImage(dataBean.getThumbnail_pic_s02(),imageView2);
            ImageLoader.getInstance().displayImage(dataBean.getThumbnail_pic_s03(),imageView3);
        }
    }
}
