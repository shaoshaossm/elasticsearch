package com.ssm.estest;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

/**
 * @author shaoshao
 * @Date 2022/11/25 19:26
 * @Description: 创建索引
 */
public class ESTestClient_Create {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 创建索引
        CreateIndexRequest user = new CreateIndexRequest("user");
        CreateIndexResponse createIndexResponse = client.indices().create(user, RequestOptions.DEFAULT);
        // 响应状态
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println("索引操作: " + acknowledged);
        client.close();
    }
}
