package com.ssm.estest;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author shaoshao
 * @Date 2022/11/25 19:26
 * @Description: 测试es连接客户端
 */
public class ESTestClient {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        client.close();
    }
}
