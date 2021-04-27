package com.light.springboot.controller.search;


import com.light.springboot.service.search.GoodSearchService;
import com.light.springboot.util.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 李振振
 * @version 1.0
 * @date 2021/3/25 15:37
 */
@RestController
@RequestMapping(value ="/search")
public class GoodSearchController {
    @Autowired
    private GoodSearchService goodSearchService;
    @GetMapping("/good/{id}")
    public void getGoodById(@PathVariable("id") Integer id){
        ResponseResult.success(goodSearchService.getGoodById(id));
    }
}
