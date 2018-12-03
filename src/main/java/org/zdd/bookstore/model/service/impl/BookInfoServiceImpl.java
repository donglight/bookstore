package org.zdd.bookstore.model.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.zdd.bookstore.common.pojo.BSResult;
import org.zdd.bookstore.common.pojo.Bar;
import org.zdd.bookstore.common.pojo.Pie;
import org.zdd.bookstore.common.utils.BSResultUtil;
import org.zdd.bookstore.common.utils.IDUtils;
import org.zdd.bookstore.exception.BSException;
import org.zdd.bookstore.model.dao.BookCategoryMapper;
import org.zdd.bookstore.model.dao.BookDescMapper;
import org.zdd.bookstore.model.dao.BookInfoMapper;
import org.zdd.bookstore.model.entity.BookDesc;
import org.zdd.bookstore.model.entity.BookInfo;
import org.zdd.bookstore.model.service.IBookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 书籍详情服务
 */

@Service
public class BookInfoServiceImpl implements IBookInfoService {

    @Autowired
    private BookInfoMapper bookInfoMapper;

    @Autowired
    private BookDescMapper bookDescMapper;

    @Autowired
    private BookCategoryMapper categoryMapper;


    @Override
    @Cacheable(cacheNames="book",key = "'bookInfo_'+#cateId+'_'+#currentPage+#pageSize")
    public List<BookInfo> findBookListByCateId(int cateId, int currentPage, int pageSize) {
        //设置分页信息，当前页，每页显示多少
        PageHelper.startPage(currentPage, pageSize);
        Example bookInfoExample = new Example(BookInfo.class);
        Example.Criteria criteria = bookInfoExample.createCriteria();
        criteria.andEqualTo("bookCategoryId", cateId);
        criteria.andEqualTo("isShelf", 1);
        bookInfoExample.setOrderByClause("deal_mount DESC,look_mount DESC");
        List<BookInfo> books = bookInfoMapper.selectByExample(bookInfoExample);
        PageInfo<BookInfo> pageInfo = new PageInfo<>(books);
        return pageInfo.getList();
    }

    @Override
    @Cacheable(cacheNames="book",key = "'bookInfo_'+#bookId")
    public BookInfo findById(Integer bookId) throws BSException {
        Example bookInfoExample = new Example(BookInfo.class);
        Example.Criteria criteriaOfIsShelf = bookInfoExample.createCriteria();
        criteriaOfIsShelf.andEqualTo("isShelf", 1);
        criteriaOfIsShelf.andEqualTo("bookId", bookId);
        List<BookInfo> bookInfos = bookInfoMapper.selectByExample(bookInfoExample);
        if (bookInfos == null || bookInfos.size() == 0) {
            throw new BSException("你搜索的书籍不存在或未上架！");
        }
        BookInfo bookInfo = bookInfos.get(0);
        bookInfo.setCategoryName(categoryMapper.selectByPrimaryKey(bookInfo.getBookCategoryId()).getName());
        return bookInfo;
    }

    public int addLookMount(BookInfo bookInfo){
        bookInfo.setLookMount(bookInfo.getLookMount() + 1);
        return bookInfoMapper.updateByPrimaryKeySelective(bookInfo);
    }

    /**
     * 按照一堆条件搜索书籍，查询关键字可以是书名、关键字或ISBN
     *
     * @param keywords
     * @param cateId
     * @param page
     * @param pageSize
     * @param storeId
     * @return
     */
    @Override
    public PageInfo<BookInfo> findBookListByCondition(String keywords, int cateId, int page, int pageSize, int storeId) {


        PageHelper.startPage(page, pageSize);
        Example bookInfoExample = new Example(BookInfo.class);

        if (!StringUtils.isEmpty(keywords)) {

            String s = "%" + keywords + "%";
            Example.Criteria criteriaOfKeywords = bookInfoExample.createCriteria();
            criteriaOfKeywords.orLike("name", s);
            criteriaOfKeywords.orLike("author", s);
            criteriaOfKeywords.orLike("isbn", s);
        }
        if (cateId != 0) {
            //加分类Id查询条件,where (name like ? or author like ? or isbn like ?) and cateId = ?
            Example.Criteria criteriaOfCateId = bookInfoExample.createCriteria();
            criteriaOfCateId.andEqualTo("bookCategoryId", cateId);
            bookInfoExample.and(criteriaOfCateId);
        }

        if (storeId == 0) {
            //前台展示，是否为上架书籍
            Example.Criteria criteriaOfIsShelf = bookInfoExample.createCriteria();
            criteriaOfIsShelf.andEqualTo("isShelf", 1);
            bookInfoExample.and(criteriaOfIsShelf);
        }else{
            //后台管理
            Example.Criteria criteriaOfStore = bookInfoExample.createCriteria();
            criteriaOfStore.andEqualTo("storeId", storeId);
            bookInfoExample.and(criteriaOfStore);
            bookInfoExample.setOrderByClause("store_time DESC");
        }
        List<BookInfo> books = bookInfoMapper.selectByExample(bookInfoExample);
        PageInfo<BookInfo> pageInfo = new PageInfo<>(books);

        return pageInfo;
    }

    @Override
    public BookInfo queryBookAvailable(int bookId) {

        Example bookInfoExample = new Example(BookInfo.class);
        Example.Criteria criteria = bookInfoExample.createCriteria();
        criteria.andEqualTo("bookId", bookId);
        criteria.andEqualTo("isShelf", 1);
        criteria.andGreaterThan("storeMount", 0);
        List<BookInfo> bookInfos = bookInfoMapper.selectByExample(bookInfoExample);
        if (bookInfos != null && !bookInfos.isEmpty()) {
            return bookInfos.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames="book",allEntries = true)
    public BSResult saveBook(BookInfo bookInfo, String bookDescStr) {

        bookInfo.setIsbn(String.valueOf(IDUtils.genItemId()));
        bookInfo.setVersion("1.0");
        bookInfo.setStoreTime(new Date());
        bookInfo.setDiscount(bookInfo.getPrice().divide(bookInfo.getMarketPrice(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(10.0)));

        bookInfo.setLookMount(100);
        bookInfo.setDealMount(100);
        bookInfo.setPackStyle("平装-胶订");
        bookInfo.setSize("16开");
        bookInfo.setIsShelf(1);

        bookInfoMapper.insert(bookInfo);

        BookDesc bookDesc = new BookDesc();
        bookDesc.setBookDesc(bookDescStr);
        bookDesc.setBookId(bookInfo.getBookId());
        bookDesc.setCreated(new Date());
        bookDesc.setUpdated(new Date());
        bookDescMapper.insert(bookDesc);

        return BSResultUtil.success();
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames="book",allEntries = true)
    public BSResult updateBook(BookInfo bookInfo, String bookDescStr) {

        bookInfo.setDiscount(bookInfo.getPrice().divide(bookInfo.getMarketPrice(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(10.0)));

        bookInfoMapper.updateByPrimaryKeySelective(bookInfo);

        BookDesc bookDesc = new BookDesc();
        bookDesc.setBookDesc(bookDescStr);
        bookDesc.setBookId(bookInfo.getBookId());
        bookDesc.setUpdated(new Date());
        if(bookDescMapper.selectByPrimaryKey(bookInfo.getBookId()) == null ){
            bookDesc.setCreated(new Date());
            bookDescMapper.insert(bookDesc);
            return BSResultUtil.success();
        }
        bookDescMapper.updateByPrimaryKeySelective(bookDesc);
        return BSResultUtil.success();
    }

    /**
     * 商品下架
     *
     * @param bookId
     * @return
     */
    @Override
    @Transactional
    @CacheEvict(cacheNames="book",allEntries = true)
    public BSResult changeShelfStatus(int bookId,int shelf) {

        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookId(bookId);
        bookInfo.setIsShelf(shelf);
        bookInfoMapper.updateByPrimaryKeySelective(bookInfo);
        return BSResultUtil.success();
    }

    @Override
    public BookInfo adminFindById(int bookId) throws BSException {
        Example bookInfoExample = new Example(BookInfo.class);
        Example.Criteria criteriaOfIsShelf = bookInfoExample.createCriteria();
        criteriaOfIsShelf.andEqualTo("bookId", bookId);
        BookInfo bookInfo = bookInfoMapper.selectByPrimaryKey(bookId);
        if(bookInfo == null){
            throw new BSException("您搜索的书籍不存在!");
        }
        return bookInfo;
    }

    @Override
    @Transactional
    public BSResult deleteBook(int bookId) {
        bookInfoMapper.deleteByPrimaryKey(bookId);
        bookDescMapper.deleteByPrimaryKey(bookId);
        return BSResultUtil.success();
    }


    @Override
    //@Cacheable(cacheNames="book",key = "'bookInfo_views'+#storeId")
    public List<Pie> getBookViewsPiesByStoreId(Integer storeId) {

        //top 8
        PageHelper.startPage(1, 8);
        Example example = new Example(BookInfo.class);
        example.createCriteria().andEqualTo("storeId", storeId);
        example.setOrderByClause("look_mount DESC");
        List<BookInfo> bookInfos = bookInfoMapper.selectByExample(example);
        if(bookInfos == null || bookInfos.size() == 0){
            return new ArrayList<>();
        }
        List<Pie> pies = new ArrayList<>();


        for (BookInfo bookInfo : bookInfos) {
            Pie pie = new Pie();
            pie.setName(bookInfo.getName());
            pie.setY(bookInfo.getLookMount());
            pies.add(pie);
        }
        pies.get(0).setSliced(true);
        pies.get(0).setSelected(true);
        return pies;
    }

    @Override
    //@Cacheable(cacheNames="book",key = "'bookInfo_sales'+#storeId")
    public Bar getBookSalesBarJson(Integer storeId) {
        //top 6
        PageHelper.startPage(1, 6);

        Example example = new Example(BookInfo.class);
        example.createCriteria().andEqualTo("storeId", storeId);
        example.setOrderByClause("deal_mount DESC");
        List<BookInfo> bookInfos = bookInfoMapper.selectByExample(example);
        if(bookInfos == null || bookInfos.size() == 0){
            return null;
        }
        Bar bar = new Bar();
        bar.setBookNames(bookInfos.stream().map(BookInfo::getName).collect(Collectors.toList()));
        bar.setSales(bookInfos.stream().map(BookInfo::getDealMount).collect(Collectors.toList()));

        return bar;
    }
}
