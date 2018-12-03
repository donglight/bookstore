package org.zdd.bookstore.model.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "book_info")
public class BookInfo {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(name = "book_category_id")
    private Integer bookCategoryId;

    @Column(name = "store_id")
    private Integer storeId;

    @Column(name = "name")
    private String name;

    /**
     * 简介
     */
    @Column(name = "outline")
    private String outline;

    /**
     * 商品详情
     */
    @Column(name = "detail")
    private String detail;

    /**
     * 出版社
     */
    @Column(name = "press")
    private String press;

    @Column(name = "publish_date")
    private Date publishDate;

    @Column(name = "size")
    private String size;

    @Column(name = "version")
    private String version;

    @Column(name = "author")
    private String author;

    /**
     * 翻译者
     */
    @Column(name = "translator")
    private String translator;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "price")
    private BigDecimal price;

    /**
     * 总页数
     */
    @Column(name = "pages")
    private Integer pages;

    /**
     * 目录
     */
    @Column(name = "catalog")
    private String catalog;

    /**
     * 市场价\定价
     */
    @Column(name = "market_price")
    private BigDecimal marketPrice;

    /**
     * 会员价格
     */
    @Column(name = "member_price")
    private BigDecimal memberPrice;

    /**
     * 成交量
     */
    @Column(name = "deal_mount")
    private Integer dealMount;

    /**
     * 浏览量
     */
    @Column(name = "look_mount")
    private Integer lookMount;

    @Column(name = "discount")
    private BigDecimal discount;

    /**
     * 版面图片
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * 库存数量
     */
    @Column(name = "store_mount")
    private Integer storeMount;

    /**
     * 入库时间
     */
    @Column(name = "store_time")
    private Date storeTime;

    /**
     * 封装方式
     */
    @Column(name = "pack_style")
    private String packStyle;

    /**
     * 是否上架
     */
    @Column(name = "is_shelf")
    private Integer isShelf;

    @Transient
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return book_id
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * @param bookId
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * @return book_category_id
     */
    public Integer getBookCategoryId() {
        return bookCategoryId;
    }

    /**
     * @param bookCategoryId
     */
    public void setBookCategoryId(Integer bookCategoryId) {
        this.bookCategoryId = bookCategoryId;
    }

    /**
     * @return store_id
     */
    public Integer getStoreId() {
        return storeId;
    }

    /**
     * @param storeId
     */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取简介
     *
     * @return outline - 简介
     */
    public String getOutline() {
        return outline;
    }

    /**
     * 设置简介
     *
     * @param outline 简介
     */
    public void setOutline(String outline) {
        this.outline = outline == null ? null : outline.trim();
    }

    /**
     * 获取商品详情
     *
     * @return detail - 商品详情
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置商品详情
     *
     * @param detail 商品详情
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    /**
     * 获取出版社
     *
     * @return press - 出版社
     */
    public String getPress() {
        return press;
    }

    /**
     * 设置出版社
     *
     * @param press 出版社
     */
    public void setPress(String press) {
        this.press = press == null ? null : press.trim();
    }

    /**
     * @return publish_date
     */
    public Date getPublishDate() {
        return publishDate;
    }

    /**
     * @param publishDate
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * @return size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size
     */
    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    /**
     * @return version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    /**
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    /**
     * 获取翻译者
     *
     * @return translator - 翻译者
     */
    public String getTranslator() {
        return translator;
    }

    /**
     * 设置翻译者
     *
     * @param translator 翻译者
     */
    public void setTranslator(String translator) {
        this.translator = translator == null ? null : translator.trim();
    }

    /**
     * @return isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    /**
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取总页数
     *
     * @return pages - 总页数
     */
    public Integer getPages() {
        return pages;
    }

    /**
     * 设置总页数
     *
     * @param pages 总页数
     */
    public void setPages(Integer pages) {
        this.pages = pages;
    }

    /**
     * 获取目录
     *
     * @return catalog - 目录
     */
    public String getCatalog() {
        return catalog;
    }

    /**
     * 设置目录
     *
     * @param catalog 目录
     */
    public void setCatalog(String catalog) {
        this.catalog = catalog == null ? null : catalog.trim();
    }

    /**
     * 获取市场价\定价
     *
     * @return market_price - 市场价\定价
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    /**
     * 设置市场价\定价
     *
     * @param marketPrice 市场价\定价
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * 获取会员价格
     *
     * @return member_price - 会员价格
     */
    public BigDecimal getMemberPrice() {
        return memberPrice;
    }

    /**
     * 设置会员价格
     *
     * @param memberPrice 会员价格
     */
    public void setMemberPrice(BigDecimal memberPrice) {
        this.memberPrice = memberPrice;
    }

    /**
     * 获取成交量
     *
     * @return deal_mount - 成交量
     */
    public Integer getDealMount() {
        return dealMount;
    }

    /**
     * 设置成交量
     *
     * @param dealMount 成交量
     */
    public void setDealMount(Integer dealMount) {
        this.dealMount = dealMount;
    }

    /**
     * 获取浏览量
     *
     * @return look_mount - 浏览量
     */
    public Integer getLookMount() {
        return lookMount;
    }

    /**
     * 设置浏览量
     *
     * @param lookMount 浏览量
     */
    public void setLookMount(Integer lookMount) {
        this.lookMount = lookMount;
    }

    /**
     * @return discount
     */
    public BigDecimal getDiscount() {
        return discount;
    }

    /**
     * @param discount
     */
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    /**
     * 获取版面图片
     *
     * @return image_url - 版面图片
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置版面图片
     *
     * @param imageUrl 版面图片
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    /**
     * 获取库存数量
     *
     * @return store_mount - 库存数量
     */
    public Integer getStoreMount() {
        return storeMount;
    }

    /**
     * 设置库存数量
     *
     * @param storeMount 库存数量
     */
    public void setStoreMount(Integer storeMount) {
        this.storeMount = storeMount;
    }

    /**
     * 获取入库时间
     *
     * @return store_time - 入库时间
     */
    public Date getStoreTime() {
        return storeTime;
    }

    /**
     * 设置入库时间
     *
     * @param storeTime 入库时间
     */
    public void setStoreTime(Date storeTime) {
        this.storeTime = storeTime;
    }

    /**
     * 获取封装方式
     *
     * @return pack_style - 封装方式
     */
    public String getPackStyle() {
        return packStyle;
    }

    /**
     * 设置封装方式
     *
     * @param packStyle 封装方式
     */
    public void setPackStyle(String packStyle) {
        this.packStyle = packStyle == null ? null : packStyle.trim();
    }

    /**
     * 获取是否上架
     *
     * @return is_shelf - 是否上架
     */
    public Integer getIsShelf() {
        return isShelf;
    }

    /**
     * 设置是否上架
     *
     * @param isShelf 是否上架
     */
    public void setIsShelf(Integer isShelf) {
        this.isShelf = isShelf;
    }
}