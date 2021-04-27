package com.light.springboot.service.impl;

import com.light.springboot.constants.SearchConstants;
import com.light.springboot.domain.search.GoodInfo;
import com.light.springboot.service.search.GoodSearchService;
import com.light.springboot.util.json.JsonUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * @author 李振振
 * @version 1.0
 * @date 2021/3/26 9:41
 */
@Service("GoodSearchServiceImpl")
public class GoodSearchServiceImpl implements GoodSearchService {

    private RestHighLevelClient restHighLevelClient;


    public RestHighLevelClient getRestHighLevelClient() {
        return restHighLevelClient;
    }
    @Autowired
    @Qualifier("highLevelClient")
    public void setRestHighLevelClient(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @Override
    public GoodInfo getGoodById(Integer id) {
        // 准备搜索builder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 准备搜索请求
        SearchRequest request = new SearchRequest();
        // 设置索引
        request.indices(SearchConstants.INX_GOODS);
        // bool查询（组合查询）
        // 如果字段是用了分词器，则需要用match查询
        // must
        // 文档 必须 匹配这些条件才能被包含进来。
        // must_not
        // 文档 必须不 匹配这些条件才能被包含进来。
        // should
        // 如果满足这些语句中的任意语句，将增加 _score ，否则，无任何影响。它们主要用于修正每个文档的相关性得分。
        // filter
        // 必须 匹配，但它以不评分、过滤模式来进行。这些语句对评分没有贡献，只是根据过滤标准来排除或包含文档。
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 必须包含不锈钢的型材类型
        boolQueryBuilder.must(QueryBuilders.termQuery("good_type", "不锈钢"));
        // 必须不包含型材规格为48的
        boolQueryBuilder.mustNot(QueryBuilders.termQuery("good_scale", "48"));
        // 添加shoule查询，用于修正评分结果
        // 查找型材规格包含34、22的结果
        boolQueryBuilder.should(QueryBuilders.matchQuery("good_scale", "34"));
        boolQueryBuilder.should(QueryBuilders.matchQuery("good_scale", "22"));
        // filter 过滤条件
        boolQueryBuilder.filter(QueryBuilders.termQuery("good_scale_type", "方管"));
        searchSourceBuilder.query(boolQueryBuilder);
        // 分页
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(5);
        // 超时
        searchSourceBuilder.timeout(TimeValue.timeValueMillis(60000));
        // 组装组合条件
        request.source(searchSourceBuilder);
        // 查询
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchHit[] searchHits = null;
        if (searchResponse == null) {

        } else {
            searchHits = searchResponse.getHits().getHits();
            for (int i = 0; i < searchHits.length; i++) {
                SearchHit searchHit = searchHits[i];
                Map<String, Object> responseMap = searchHit.getSourceAsMap();
                System.out.println(JsonUtil.toString(responseMap));
            }
        }
        return new GoodInfo();
    }
}
