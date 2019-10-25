package com.light.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class testController {
    @GetMapping("helloWorld")
    public String helloWorld(){
        return "helloworld";
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(2);

        //通过subList生成一个与list1一样的列表 list3
        List<Integer> list3 = new ArrayList<>(list1.subList(0, list1.size()));
        /**
         修改list1  ,也可能是多线程情况下其他线程调用了list1这个实例的add,sort，remove等方法*/
        list1.add(3);

        System.out.println("list1'size：" + list1.size());
        System.out.println("list3'size：" + list3.toString());

    }
}
