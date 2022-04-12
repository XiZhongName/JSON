package com.example.jsondemo.bean;

import java.util.List;

/*{
        "code":0,
        "list":{
        "0":{
        "aid":"8900789",
        "author":"春江水暖鸭先知",
        "coins":188,
        "copyright":"Copy",
        "create":"2022-04-11 16:30"
        },
        "1":{
        "aid":"8900788",
        "author":"清明时节雨纷纷",
        "coins":189,
        "copyright":"Copy",
        "create":"2022-04-11 16:40"
        }
        }
        }*/

public class FilmInfo {
    private int code;
    private List<FilmBean> list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<FilmBean> getList() {
        return list;
    }

    public void setList(List<FilmBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "FilmInfo{" +
                "code=" + code +
                ", list=" + list +
                '}';
    }

    public static class FilmBean{
        private String aid;
        private String author;
        private int coins;
        private String copyright;
        private String create;

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getCoins() {
            return coins;
        }

        public void setCoins(int coins) {
            this.coins = coins;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public String getCreate() {
            return create;
        }

        public void setCreate(String create) {
            this.create = create;
        }

        @Override
        public String toString() {
            return "FilmBean{" +
                    "aid='" + aid + '\'' +
                    ", author='" + author + '\'' +
                    ", coins=" + coins +
                    ", copyright='" + copyright + '\'' +
                    ", create='" + create + '\'' +
                    '}';
        }
    }
}
