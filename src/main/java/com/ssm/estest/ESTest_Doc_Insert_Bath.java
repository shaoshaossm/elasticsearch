package com.ssm.estest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import javax.xml.transform.Source;

/**
 * @author shaoshao
 * @Date 2022/11/25 19:26
 * @Description: 文档 批量插入数据
 */
public class ESTest_Doc_Insert_Bath {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON, "name", "zhangsan", "age", 20, "sex", "男"));
        request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON, "name", "zhangsan2", "age", 22, "sex", "男"));
        request.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON, "name", "zhangsan2", "age", 23, "sex", "男"));

        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println("响应时间：" +response.getTook());
        System.out.println("创建的内容：" + response.getItems());
        client.close();
    }
}
