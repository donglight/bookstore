package org.zdd.bookstore.crawl;

import org.zdd.bookstore.model.entity.BookInfo;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class URLEntity {

    public static List<BookInfo> URLParse(HttpClient httpclient, String url, String category) throws IOException, ParseException {
        List<BookInfo> getbooks = new ArrayList<BookInfo>();
        HttpResponse response = HttpUtil.getHtml(httpclient, url);
        int statusCode = response.getStatusLine().getStatusCode();   //获取状态码
        if(statusCode == 200)  //200为正常
        {
            String entity = EntityUtils.toString(response.getEntity(),"utf-8");
            getbooks = BookParse.getData(httpclient,entity,category);
            EntityUtils.consume(response.getEntity());   //消耗实体类，实体类最后需要消耗
        }
        else
            EntityUtils.consume(response.getEntity());

        return getbooks;
    }
}
