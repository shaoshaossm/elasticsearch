package com.ssm.estest;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * @author shaoshao
 * @Date 2022/11/25 19:26
 * @Description: 文档 过滤查询数据
 */
public class ESTest_Doc_Query_Filter {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 过滤字段
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        // 过滤条件
        String[] excludes = {"age"};
        String[] includes = {"name","sex"};
        builder.fetchSource(includes, excludes);
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
