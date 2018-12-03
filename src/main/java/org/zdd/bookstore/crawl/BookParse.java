package org.zdd.bookstore.crawl;

import org.zdd.bookstore.model.entity.BookInfo;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookParse {
    public static List<BookInfo> getData(HttpClient httpclient, String html, String category) throws ParseException, IOException {
        List<BookInfo> datas = new ArrayList<BookInfo>();
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("div[id=search_nature_rg]").select("ul[class=bigimg]").select("li");
        int i = 1;
        for (Element element : elements) {

            String imgUrl = element.select("a[class=pic]").select("img").attr("data-original");
            if(StringUtils.isEmpty(imgUrl)){
                imgUrl = element.select("a[class=pic]").select("img").attr("src");
            }
            String bookName = element.select("a[class=pic]").select("img").attr("alt");
            String outline = element.select("p[class=name]").select("a").text();
            String detail = element.select("p[class=detail]").text();

            Elements authorList = element.select("p[class=search_book_author]").select("span");

            String author = authorList.get(0).select("a").attr("title");
            String publishDate = authorList.get(1).text().substring(authorList.get(1).text().indexOf("/") + 1).trim();
            String press = authorList.get(2).select("a").text();


            String bookRobPrice = element.select("p[class=price]").select("span[class=search_now_price]").text().substring(1);

            String bookMarketPrice = element.select("p[class=price]").select("span[class=search_pre_price]").text().substring(1);
            String discount = element.select("p[class=price]").select("span[class=search_discount]").text();

            String bookSize = "";
            String bookPackage = "";
            String ISBN = "";
            String catalog = "";
            /**
             * 爬取商品详情页的信息
             */

            String bookInfoUrl = element.select("a[class=pic]").attr("href");
            HttpResponse response = HttpUtil.getHtml(httpclient, bookInfoUrl);
            int statusCode = response.getStatusLine().getStatusCode();   //获取状态码
            if (statusCode == 200) {
                String entity = EntityUtils.toString(response.getEntity(), "utf-8");
                Document bookInfoDoc = Jsoup.parse(entity);
                Elements bookDetailDesc = bookInfoDoc.select("#detail_all");


                Elements keys = bookDetailDesc.select("ul[class=key clearfix]").select("li");

                bookSize = keys.get(0).text();
                bookSize = bookSize.substring(bookSize.indexOf("：") + 1);
                bookPackage = keys.get(2).text();
                bookPackage = bookPackage.substring(bookPackage.indexOf("：") + 1);
                ISBN = keys.get(4).text();
                ISBN = ISBN.substring(ISBN.indexOf("：") + 1);

                Elements select = bookDetailDesc.select("#detail").select("#catalog-show");

                catalog = select.text();
            }

            BookInfo book = new BookInfo();
            book.setName(bookName);
            book.setImageUrl(imgUrl);
            book.setOutline(outline);
            book.setAuthor(author);
            book.setBookCategoryId(Integer.parseInt(category));
            String substring = discount.substring(
                    (discount.indexOf("(") + 1) < 0 ? 0 : discount.indexOf("(") + 1, discount.indexOf(")") - 1 < 0 ? 0 : discount.indexOf(")") - 1);
            book.setDiscount(BigDecimal.valueOf(
                    Double.valueOf(
                            StringUtils.isEmpty(substring) ? "0" : substring
                    )
                    )
            );
            book.setPrice(BigDecimal.valueOf(Double.valueOf(bookRobPrice)));
            book.setMarketPrice(BigDecimal.valueOf(Double.valueOf(bookMarketPrice)));
            book.setPress(press);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(publishDate);
            book.setPublishDate(date);
            book.setSize(bookSize);
            book.setPackStyle(bookPackage);
            book.setIsbn(ISBN);
            book.setCatalog(catalog);
            book.setDetail(detail);
            book.setStoreId(1);
            book.setStoreTime(new Date());
            book.setDealMount(0);
            book.setLookMount(0);
            book.setStoreMount(200);

            datas.add(book);
            if (i++ == 30) {
                break;
            }
        }
        return datas;
    }
}
