package org.zdd.bookstore.model.service;

import org.zdd.bookstore.model.entity.BookInfo;

import java.util.List;

public interface IOrderDetailService {

    List<BookInfo> findBooksByOrderId(String orderId);
}
