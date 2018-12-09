package com.example.projectone.model;

import java.util.List;

public class UserBean {
    private List<DataBean> data;
    public List<DataBean> getData() {
        return data;
    }
    public void setData(List<DataBean> data) {
        this.data = data;
    }
    public static class DataBean {
        private String title;
        private String author_name;
        private String thumbnail_pic_s;
        private String thumbnail_pic_s02;
        private String thumbnail_pic_s03;

        public DataBean(String title, String author_name, String thumbnail_pic_s, String thumbnail_pic_s02, String thumbnail_pic_s03) {
            this.title = title;
            this.author_name = author_name;
            this.thumbnail_pic_s = thumbnail_pic_s;
            this.thumbnail_pic_s02 = thumbnail_pic_s02;
            this.thumbnail_pic_s03 = thumbnail_pic_s03;
        }

        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getAuthor_name() {
            return author_name;
        }
        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }
        public String getThumbnail_pic_s() {
            return thumbnail_pic_s;
        }
        public void setThumbnail_pic_s(String thumbnail_pic_s) {
            this.thumbnail_pic_s = thumbnail_pic_s;
        }
        public String getThumbnail_pic_s02() {
            return thumbnail_pic_s02;
        }
        public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
            this.thumbnail_pic_s02 = thumbnail_pic_s02;
        }
        public String getThumbnail_pic_s03() {
            return thumbnail_pic_s03;
        }
        public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
            this.thumbnail_pic_s03 = thumbnail_pic_s03;
        }
    }
}