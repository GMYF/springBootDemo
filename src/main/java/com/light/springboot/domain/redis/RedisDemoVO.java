/*
 * 〈描述〉
 *
 * @author lzz
 * @since 版本号
 */

package com.light.springboot.domain.redis;/**
 * @author 李振振
 * @date 2021/4/22 10:17
 * @version 1.0
 */

import java.io.Serializable;

/**
 * 〈一句话功能简述〉
 *
 * <p>〈功能详细描述〉
 *
 * @author lzz
 * @since 版本号
 */
public class RedisDemoVO implements Serializable {
    private String id;
    private String name;
    private String age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "RedisDemoVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
