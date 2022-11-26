package com.ssm.estest;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author shaoshao
 * @Date 2022/11/25 19:26
 * @Description: 删除索引
 */
public class ESTestClient_Delete {
    public static void main(String[] args) throws Exception {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        //删除索引
        DeleteIndexRequest request = new DeleteIndexRequest("user");

        AcknowledgedResponse response = esClient.indices().delete(request, RequestOptions.DEFAULT);

        //响应状态
        System.out.println(response.isAcknowledged());

        esClient.close();
    }
}
