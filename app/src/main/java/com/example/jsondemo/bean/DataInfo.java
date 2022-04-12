package com.example.jsondemo.bean;

import java.util.List;

/*{
        "data":{
        "count":5,
        "items":[
        {
        "id":45,
        "title":"坚果"
        },
        {
        "id":46,
        "title":"炒货"
        }
        ]
        },
        "rs_code":"1000",
        "rs_msg":"success"
        }*/

public class DataInfo {

    /**
     * data : {"count":5,"items":[{"id":45,"title":"坚果"},{"id":46,"title":"炒货"}]}
     * rs_code : 1000
     * rs_msg : success
     */

    private DataDTO data;
    private String rs_code;
    private String rs_msg;

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public String getRs_code() {
        return rs_code;
    }

    public void setRs_code(String rs_code) {
        this.rs_code = rs_code;
    }

    public String getRs_msg() {
        return rs_msg;
    }

    public void setRs_msg(String rs_msg) {
        this.rs_msg = rs_msg;
    }

    @Override
    public String toString() {
        return "DataInfo{" +
                "data=" + data +
                ", rs_code='" + rs_code + '\'' +
                ", rs_msg='" + rs_msg + '\'' +
                '}';
    }

    public static class DataDTO {
        /**
         * count : 5
         * items : [{"id":45,"title":"坚果"},{"id":46,"title":"炒货"}]
         */

        private int count;
        private List<ItemsDTO> items;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ItemsDTO> getItems() {
            return items;
        }

        public void setItems(List<ItemsDTO> items) {
            this.items = items;
        }

        @Override
        public String toString() {
            return "DataDTO{" +
                    "count=" + count +
                    ", items=" + items +
                    '}';
        }

        public static class ItemsDTO {
            /**
             * id : 45
             * title : 坚果
             */

            private int id;
            private String title;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            @Override
            public String toString() {
                return "ItemsDTO{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        '}';
            }
        }
    }
}
