package com.light.springboot.domain.user;

import com.light.springboot.domain.account.Account;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

/**
 * @author zz.li
 * @Date 2019-07-31
 */
public class User {
    private Integer id;
    private String name;
    private String phone;

    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Setter
    public Integer getId() {
        return id;
    }
    @Setter
    public void setId(Integer id) {
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
    public String getPhone() {
        return phone;
    }
    @Setter
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        String s = "\\%/g";
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
