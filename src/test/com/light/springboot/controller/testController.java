package com.light.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)   //调用Spring单元测试类
@SpringBootTest
public class testController {

    private MockMvc mvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp(){
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void selectAll() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/api/user/detail/{id}","1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                // .content(mapper.writeValueAsString(id))//将对象转化成JSON字符换
        )
//                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())////打印出请求和相应的内容
//                .andExpect(status().is2xxSuccessful())
                .andReturn();

    }

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
