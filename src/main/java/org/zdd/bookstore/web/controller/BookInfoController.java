package org.zdd.bookstore.web.controller;


import com.github.pagehelper.PageInfo;
import org.zdd.bookstore.exception.BSException;
import org.zdd.bookstore.model.dao.BookDescMapper;
import org.zdd.bookstore.model.entity.BookDesc;
import org.zdd.bookstore.model.entity.BookInfo;
import org.zdd.bookstore.model.service.IBookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookInfoController {



    @Autowired
    private IBookInfoService bookInfoService;

    @Autowired
    private BookDescMapper bookDescMapper;

    /**
     * 查询某一本书籍详情
     *
     * @param bookId
     * @param model
     * @return
     */
    @RequestMapping("/info/{bookId}")
    public String bookInfo(@PathVariable("bookId") Integer bookId, Model model) throws BSException {
        //查询书籍
        BookInfo bookInfo = bookInfoService.findById(bookId);
        //查询书籍推荐列表
        List<BookInfo> recommendBookList = bookInfoService.findBookListByCateId(bookInfo.getBookCategoryId(), 1, 5);
        //查询书籍详情
        BookDesc bookDesc = bookDescMapper.selectByPrimaryKey(bookId);
        //增加访问量
        bookInfoService.addLookMount(bookInfo);
        Collections.shuffle(recommendBookList);
        model.addAttribute("bookInfo", bookInfo);
        model.addAttribute("bookDesc", bookDesc);
        model.addAttribute("recommendBookList", recommendBookList);
        return "book_info";
    }


    /**
     * 通过关键字和书籍分类搜索书籍列表
     *
     * @param keywords
     * @return
     */
    @RequestMapping("/list")
    public String bookSearchList(@RequestParam(defaultValue = "", required = false) String keywords,
                                 @RequestParam(defaultValue = "0", required = false) int cateId,//分类Id，默认为0，即不按照分类Id查
                                 @RequestParam(defaultValue = "1", required = false) int page,
                                 @RequestParam(defaultValue = "6", required = false) int pageSize,
                                 Model model) {
        keywords = keywords.trim();
        PageInfo<BookInfo> bookPageInfo = bookInfoService.findBookListByCondition(keywords, cateId, page, pageSize,0);//storeId为0，不按照商店Id查询

        model.addAttribute("bookPageInfo", bookPageInfo);

        model.addAttribute("keywords", keywords);

        model.addAttribute("cateId", cateId);

        return "book_list";
    }

}
