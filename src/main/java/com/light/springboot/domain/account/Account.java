package com.light.springboot.domain.account;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;


public class Account {

    private int id;
    private String name;
    private String no;

    @Getter
    public int getId() {
        return id;
    }

    @Setter
    public void setId(int id) {
        this.id = id;
    }
    @Getter
    public String getName() {
        return name;
    }
    @Setter
    public void setName(String name) {
        this.name = name;
    }
    @Getter
    public String getNo() {
        return no;
    }
    @Setter
    public void setNo(String no) {
        this.no = no;
    }
}
