package org.zdd.bookstore.crawl;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicHttpResponse;

import java.io.IOException;

public class HttpUtil {

    public static HttpResponse getHtml(HttpClient httpclient, String url) throws IOException
    {
        HttpGet getMethod = new HttpGet(url);   //get方法
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK,"ok");   //response初始化
        response = httpclient.execute(getMethod);     //执行get方法
        return response;
    }
}
