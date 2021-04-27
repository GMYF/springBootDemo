/*
 * 〈描述〉
 *
 * @author lzz
 * @since 版本号
 */

package com.light.springboot.controller.redis;/**
 * @author 李振振
 * @date 2021/4/22 10:05
 * @version 1.0
 */

import com.light.springboot.domain.redis.RedisDemoVO;
import com.light.springboot.util.json.JsonUtil;
import com.light.springboot.util.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉
 *
 * <p>〈功能详细描述〉
 *
 * @author lzz
 * @since 版本号
 */
@RestController
@RequestMapping(value = "/redis")
public class RedisController {
    /**
     * redis template操作类
     */
    @Autowired
    @Qualifier(value = "redisTemplate")
    private RedisTemplate<String,Object> redisTemplate;
    @GetMapping(value = "/test")
    public ResponseResult<String> testRedis(){
        // String 类型
        // 设置失效时间
        ValueOperations<String,Object> opsValues =  redisTemplate.opsForValue();
        opsValues.set("redis_2021_04_22_01","redisKey",100, TimeUnit.SECONDS);
        System.out.println("设置失效时间" + opsValues.get("redis_2021_04_22_01"));

        // 重写给定key的值，指定偏移量
        opsValues.set("redis_2021_04_22_01","Value",5);
        System.out.println("指定偏移量" + opsValues.get("redis_2021_04_26_01"));

        // 设置键的字符串值并返回旧值
        opsValues.set("key","hello world");
        opsValues.getAndSet("key","test");
        System.out.println("设置键的字符串值并返回旧值==>" + opsValues.get("key"));
        // 如果key已经存在并且是一个字符串，则该命令将该值追加到字符串的末尾。如果键不存在，则它被创建并设置为空字符串，因此APPEND在这种特殊情况下将类似于SET。
        opsValues.set("test","Hello");
        opsValues.append("test","World lzz");
        // setIfAbsent 有值则不设置
        System.out.println(opsValues.setIfAbsent("multi1","multi1"));//false  multi1之前已经存在
        System.out.println(opsValues.setIfAbsent("multi111","multi111"));//true  multi111之前不存在
        // 多键值保存
        Map<String,String> multiMap = new HashMap<>(4);
        multiMap.put("key1","test1");
        multiMap.put("key2","test2");
        multiMap.put("key3","test3");
        multiMap.put("key4","test4");
        opsValues.multiSet(multiMap);
        // 多键值保存，如果key，存在则不保存
        System.out.println(opsValues.multiSetIfAbsent(multiMap));

        // 为多个键分别取出它们的值
        Map<String,String> multiEKeyMap = new HashMap<>(4);
        multiEKeyMap.put("multiEKey1","multiValue1");
        multiEKeyMap.put("multiEKey2","multiValue2");
        multiEKeyMap.put("multiEKey3","multiValue3");
        multiEKeyMap.put("multiEKey4","multiValue4");
        opsValues.multiSet(multiEKeyMap);
        List<String> mapList = new ArrayList<>();
        mapList.add("multiEKey1");
        mapList.add("multiEKey2");
        mapList.add("multiEKey3");
        mapList.add("multiEKey4");
        System.out.println(opsValues.multiGet(mapList));


        // 集合使用
        ListOperations<String,Object> opsList =  redisTemplate.opsForList();

        List<String> stringList = new ArrayList<>();
        stringList.add("array 1");
        stringList.add("array 2");
        stringList.add("array 3");
        stringList.add("array 4");
        stringList.add("array 5");
        opsList.leftPushAll("stringList",stringList);
        // 获取集合数据
        // 从0开始，
        List<String> stringListRes = (List)opsList.range("stringList",0,4);
        System.out.println(JsonUtil.toString(stringListRes));
        // 实体集合2
        List<RedisDemoVO> redisDemoVOList = new ArrayList<>();
        RedisDemoVO redisDemoVO = new RedisDemoVO();
        redisDemoVO.setAge("12");
        redisDemoVO.setId("1");
        redisDemoVO.setName("李四");
        // pojo 保存
        opsValues.set("pojo-key",redisDemoVO);
        // pojo 获取
        System.out.println("pojo获取数据" + opsValues.get("pojo-key"));
        redisDemoVOList.add(redisDemoVO);
        redisDemoVO = new RedisDemoVO();
        redisDemoVO.setAge("34");
        redisDemoVO.setId("2");
        redisDemoVO.setName("王五");
        redisDemoVOList.add(redisDemoVO);
        opsList.rightPushAll("pojoKey",redisDemoVOList);
        opsValues.set("pojo-json-key",JsonUtil.toString(redisDemoVOList));
        // json-list获取数据
        System.out.println("获取-集合-json" + opsValues.get("pojo-json-key"));
        // 获取实体数据
        System.out.println(JsonUtil.toString(opsList.range("pojoKey", 0, -1)));
        // 获取hash
        Map<String,String> hashRedisMap = new HashMap<>();
        hashRedisMap.put("hash1","sdfdf");
        hashRedisMap.put("hash2","xxxxx");
        hashRedisMap.put("hash3","1222232");
        HashOperations<String,String,Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll("hash",hashRedisMap);
        // 以json格式保存
        opsValues.set("hash-json",JsonUtil.toString(hashRedisMap));
        System.out.println("获取json-map数据:" + opsValues.get("hash-json"));
        // 获取hashMap的值
        // 单个获取
        System.out.println(hashOperations.get("hash", "hash1"));
        // 单个删除
        hashOperations.delete("hash","hash2");
        // 批量获取
        Map<String, Object> hashMapRes = hashOperations.entries("hash");
        System.out.println(JsonUtil.toString(hashMapRes));

        // set类型
        // 单个值插入
        SetOperations<String,Object> setOperations = redisTemplate.opsForSet();
        setOperations.add("set-key",redisDemoVO);
        setOperations.add("set-key1",redisDemoVOList);
        setOperations.add("set-key2",JsonUtil.toString(redisDemoVO));
        setOperations.add("set-key3",JsonUtil.toString(redisDemoVOList));
        setOperations.add("set-key4",JsonUtil.toString(hashRedisMap));
        setOperations.add("set-key5",hashRedisMap);
        setOperations.add("set-key6","fdfdffdfd");
        return ResponseResult.success("测试成功");
    }
}
