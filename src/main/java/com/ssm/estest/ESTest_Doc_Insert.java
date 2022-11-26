package com.ssm.estest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * @author shaoshao
 * @Date 2022/11/25 19:26
 * @Description: 文档 插入数据
 */
public class ESTest_Doc_Insert {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 插入数据
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index("user").id("1001");
        User user = new User();
        user.setAge(20);
        user.setName("ssm");
        user.setSex("男");

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        indexRequest.source(userJson, XContentType.JSON);
        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);

        response.getResult();

        client.close();
    }
}
