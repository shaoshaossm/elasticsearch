package com.ssm.estest;

import org.apache.http.HttpHost;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * @author shaoshao
 * @Date 2022/11/25 19:26
 * @Description: 文档 修改数据
 */
public class ESTest_Doc_Update {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 修改数据
        UpdateRequest request = new UpdateRequest();
        request.index("user").id("1001");

        request.doc(XContentType.JSON, "sex", "女");
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult());

        client.close();
    }
}
