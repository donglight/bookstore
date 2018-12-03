package org.zdd.bookstore.model.service;

import com.github.pagehelper.PageInfo;
import org.zdd.bookstore.common.pojo.BSResult;
import org.zdd.bookstore.common.pojo.Bar;
import org.zdd.bookstore.common.pojo.Pie;
import org.zdd.bookstore.exception.BSException;
import org.zdd.bookstore.model.entity.BookInfo;

import java.util.List;

public interface IBookInfoService {

    List<BookInfo> findBookListByCateId(int cateId, int currentPage, int pageSize);

    BookInfo findById(Integer bookId) throws BSException;

    PageInfo<BookInfo> findBookListByCondition(String keywords, int cateId, int page, int pageSize,int storeId);

    BookInfo queryBookAvailable(int bookId);

    BSResult saveBook(BookInfo bookInfo,String bookDescStr);

    BSResult updateBook(BookInfo bookInfo, String bookDesc);

    BSResult changeShelfStatus(int bookId,int shelf);

    BookInfo adminFindById(int bookId) throws BSException;

    BSResult deleteBook(int bookId);

    int addLookMount(BookInfo bookInfo);

    List<Pie> getBookViewsPiesByStoreId(Integer storeId);

    Bar getBookSalesBarJson(Integer storeId);
}
