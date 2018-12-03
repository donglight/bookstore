package org.zdd.bookstore.model.service;

import org.zdd.bookstore.model.entity.BookCategory;

import java.util.List;

public interface IBookCateService {
    List<BookCategory> getCategoryList();
}
