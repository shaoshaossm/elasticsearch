package com.ssm.estest;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author shaoshao
 * @Date 2022/11/25 19:26
 * @Description: 文档 查询数据
 */
public class ESTest_Doc_Get {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 查询数据
        GetRequest request = new GetRequest();
        request.index("user").id("1001");


        GetResponse response = client.get(request, RequestOptions.DEFAULT);

        System.out.println(response.getSourceAsString());

        client.close();
    }
}
