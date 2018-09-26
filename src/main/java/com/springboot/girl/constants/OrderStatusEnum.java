package com.springboot.girl.constants;

public enum OrderStatusEnum {
    CREATE(0,"新建"),;
    private int key;
    private String name;
    private OrderStatusEnum(int key,String name) {
        this.key=key;
        this.name=name;
    }
    public int getKey() {
        return this.key;
    }
}
