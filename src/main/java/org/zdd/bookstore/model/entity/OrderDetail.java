package org.zdd.bookstore.model.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @Column(name = "order_detail_id")
    private Integer orderDetailId;

    /**
     * 订单号
     */
    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "store_id")
    private Integer storeId;

    /**
     * 卖出数量
     */
    @Column(name = "mount")
    private Integer mount;

    /**
     * 单价
     */
    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    /**
     * 支付、发货状态
     */
    @Column(name = "post_status")
    private String postStatus;

    /**
     * 发货时间
     */
    @Column(name = "delivery_time")
    private Date deliveryTime;

    /**
     * 收货状态
     */
    @Column(name = "receive_status")
    private String receiveStatus;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "book_name")
    private String bookName;

    /**
     * @return order_detail_id
     */
    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    /**
     * @param orderDetailId
     */
    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    /**
     * 获取订单号
     *
     * @return order_number - 订单号
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * 设置订单号
     *
     * @param orderNumber 订单号
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    /**
     * @return order_id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
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
     * 获取卖出数量
     *
     * @return mount - 卖出数量
     */
    public Integer getMount() {
        return mount;
    }

    /**
     * 设置卖出数量
     *
     * @param mount 卖出数量
     */
    public void setMount(Integer mount) {
        this.mount = mount;
    }

    /**
     * 获取单价
     *
     * @return unit_price - 单价
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * 设置单价
     *
     * @param unitPrice 单价
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return total_price
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 获取支付、发货状态
     *
     * @return post_status - 支付、发货状态
     */
    public String getPostStatus() {
        return postStatus;
    }

    /**
     * 设置支付、发货状态
     *
     * @param postStatus 支付、发货状态
     */
    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus == null ? null : postStatus.trim();
    }

    /**
     * 获取发货时间
     *
     * @return delivery_time - 发货时间
     */
    public Date getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * 设置发货时间
     *
     * @param deliveryTime 发货时间
     */
    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * 获取收货状态
     *
     * @return receive_status - 收货状态
     */
    public String getReceiveStatus() {
        return receiveStatus;
    }

    /**
     * 设置收货状态
     *
     * @param receiveStatus 收货状态
     */
    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus == null ? null : receiveStatus.trim();
    }

    /**
     * @return image_url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}