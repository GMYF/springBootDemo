package com.light.springboot.service.search;

import com.light.springboot.domain.search.GoodInfo;

/**
 * @author 李振振
 * @version 1.0
 * @date 2021/3/26 9:38
 */
public interface GoodSearchService {
    GoodInfo getGoodById(Integer id);
}
