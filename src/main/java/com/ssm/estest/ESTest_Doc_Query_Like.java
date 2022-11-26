package com.ssm.estest;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * @author shaoshao
 * @Date 2022/11/25 19:26
 * @Description: 模糊查询数据
 */
public class ESTest_Doc_Query_Like {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 过滤字段
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name", "zhangsa").fuzziness(Fuzziness.TWO);
        builder.query(fuzzyQueryBuilder);
        request.source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }

        client.close();
    }
}
