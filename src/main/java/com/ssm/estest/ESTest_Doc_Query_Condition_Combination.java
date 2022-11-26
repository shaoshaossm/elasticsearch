package com.ssm.estest;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * @author shaoshao
 * @Date 2022/11/25 19:26
 * @Description: 条件组合查询数据
 */
public class ESTest_Doc_Query_Condition_Combination {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 过滤字段
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 年龄必须20 性别必须男
//        boolQueryBuilder.must(QueryBuilders.matchQuery("age", 20));
        boolQueryBuilder.must(QueryBuilders.matchQuery("sex", "男"));
        // 年龄不是22
        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("age", 22));
        // 年龄应该20
        boolQueryBuilder.should(QueryBuilders.matchQuery("age", 20));
//        boolQueryBuilder.should(QueryBuilders.matchQuery("age", 30));
        builder.query(boolQueryBuilder);
        SearchResponse response = client.search(request.source(builder), RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }

        client.close();
    }
}
