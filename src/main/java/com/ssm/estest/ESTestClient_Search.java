package com.ssm.estest;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

/**
 * @author shaoshao
 * @Date 2022/11/25 19:26
 * @Description: 查询索引
 */
public class ESTestClient_Search {
    public static void main(String[] args) throws Exception {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        //查询索引
        GetIndexRequest request = new GetIndexRequest("user");
        GetIndexResponse getIndexResponse =
                esClient.indices().get(request, RequestOptions.DEFAULT);

        //响应状态
        System.out.println(getIndexResponse.getAliases());
        System.out.println(getIndexResponse.getMappings());
        System.out.println(getIndexResponse.getSettings());
        esClient.close();
    }
}
