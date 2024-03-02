package com.mayank.employwise_backend.entity;

public class Hierarchy {

        private String id;
        private Integer num;
        
        public Hierarchy() {
        }

        public Hierarchy(String id, Integer num) {
            this.id = id;
            this.num = num;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }
        
        
}
