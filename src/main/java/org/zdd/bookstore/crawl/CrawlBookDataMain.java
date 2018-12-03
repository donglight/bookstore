package org.zdd.bookstore.crawl;

import org.zdd.bookstore.model.entity.BookInfo;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.List;

public class CrawlBookDataMain {

    public static void main(String[] args) throws Exception {

        HttpClient httpclient = new DefaultHttpClient(); //创建HttpClient
        //先去书籍列表页列表页
        String url = "http://category.dangdang.com/cp01.54.06.00.00.00.html"; //种子
        List<BookInfo> books = URLEntity.URLParse(httpclient, url,"7"); //通过URLEntity获取实体中的信息
        //mysql_control.executeInsert(books);  //数据库添加数据
        new WriteToMysql().executeInsert(books);
    }
}
