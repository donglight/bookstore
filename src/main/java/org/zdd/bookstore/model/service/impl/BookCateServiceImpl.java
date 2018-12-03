package org.zdd.bookstore.model.service.impl;

import org.zdd.bookstore.model.dao.BookCategoryMapper;
import org.zdd.bookstore.model.entity.BookCategory;
import org.zdd.bookstore.model.service.IBookCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 书籍分类服务
 */
@Service
public class BookCateServiceImpl implements IBookCateService {


    @Autowired
    private BookCategoryMapper bookCategoryMapper;


    @Override
    public List<BookCategory> getCategoryList() {
        return bookCategoryMapper.selectAll();
    }
}
