package com.ssm.estest;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * @author shaoshao
 * @Date 2022/11/25 21:05
 * @Description:
 */
public class ESTest_Doc_Delete_Bath {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest().index("user").id("1001"));
        request.add(new DeleteRequest().index("user").id("1002"));
        request.add(new DeleteRequest().index("user").id("1003"));

        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println("响应时间：" + response.getTook());
        System.out.println("创建的内容：" + response.getItems());
        client.close();
    }
}
