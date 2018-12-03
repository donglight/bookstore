package org.zdd.bookstore.model.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "orders")
public class Orders {
    /**
     * 订单id
     */
    @Id
    @Column(name = "order_id")
    private String orderId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    @Column(name = "payment")
    private String payment;

    /**
     * 支付类型，1、微信，2、支付宝
     */
    @Column(name = "payment_type")
    private Integer paymentType;

    /**
     * 支付状态 0未支付 1已支付
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    @Column(name = "post_fee")
    private String postFee;

    /**
     * 订单创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 订单更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "order_mount")
    private Integer orderMount;

    /**
     * 付款时间
     */
    @Column(name = "payment_time")
    private Date paymentTime;

    /**
     * 交易完成时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 交易关闭时间
     */
    @Column(name = "close_time")
    private Date closeTime;

    /**
     * 物流名称
     */
    @Column(name = "shipping_name")
    private String shippingName;

    /**
     * 物流单号
     */
    @Column(name = "shipping_code")
    private String shippingCode;

    /**
     * 买家留言
     */
    @Column(name = "buyer_message")
    private String buyerMessage;

    /**
     * 买家是否已经评价
     */
    @Column(name = "buyer_rate")
    private Integer buyerRate;

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
     *
     * @return payment - 实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    public String getPayment() {
        return payment;
    }

    /**
     * 设置实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
     *
     * @param payment 实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
    }

    /**
     * 获取支付类型，1、在线支付，2、货到付款
     *
     * @return payment_type - 支付类型，1、在线支付，2、货到付款
     */
    public Integer getPaymentType() {
        return paymentType;
    }

    /**
     * 设置支付类型，1、微信，2、支付宝
     *
     * @param paymentType 支付类型，1、微信，2、支付宝
     */
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * 获取订单状态：0、未付款，1、已付款，2、未发货，3、已发货，4、确认收货，5、交易关闭
     *
     * @return status - 状态：0、未付款，1、已付款，2、未发货，3、已发货，4、确认收货，5、交易关闭
     */
    public Integer getStatus() {
        return status;
    }

    public String getStatusString(){
        if(status == 0){
            return "未付款";
        }else if(status == 1){
            return "已付款";
        }else if(status == 2){
            return "未发货";
        }else if(status == 3){
            return "已发货";
        }else if(status == 4){
            return "确认收货";
        }else{
            return "交易关闭";
        }
    }

    /**
     * 设置支付状态 未付款，1、已付款，2、未发货，3、已发货，4、确认收货，5、交易关闭
     *
     * @param status 支付状态 1、已付款，2、未发货，3、已发货，4、确认收货，5、交易关闭
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
     *
     * @return post_fee - 邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    public String getPostFee() {
        return postFee;
    }

    /**
     * 设置邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
     *
     * @param postFee 邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    public void setPostFee(String postFee) {
        this.postFee = postFee == null ? null : postFee.trim();
    }

    /**
     * 获取订单创建时间
     *
     * @return create_time - 订单创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置订单创建时间
     *
     * @param createTime 订单创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取订单更新时间
     *
     * @return update_time - 订单更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置订单更新时间
     *
     * @param updateTime 订单更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return order_mount
     */
    public Integer getOrderMount() {
        return orderMount;
    }

    /**
     * @param orderMount
     */
    public void setOrderMount(Integer orderMount) {
        this.orderMount = orderMount;
    }

    /**
     * 获取付款时间
     *
     * @return payment_time - 付款时间
     */
    public Date getPaymentTime() {
        return paymentTime;
    }

    /**
     * 设置付款时间
     *
     * @param paymentTime 付款时间
     */
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    /**
     * 获取交易完成时间
     *
     * @return end_time - 交易完成时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置交易完成时间
     *
     * @param endTime 交易完成时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取交易关闭时间
     *
     * @return close_time - 交易关闭时间
     */
    public Date getCloseTime() {
        return closeTime;
    }

    /**
     * 设置交易关闭时间
     *
     * @param closeTime 交易关闭时间
     */
    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * 获取物流名称
     *
     * @return shipping_name - 物流名称
     */
    public String getShippingName() {
        return shippingName;
    }

    /**
     * 设置物流名称
     *
     * @param shippingName 物流名称
     */
    public void setShippingName(String shippingName) {
        this.shippingName = shippingName == null ? null : shippingName.trim();
    }

    /**
     * 获取物流单号
     *
     * @return shipping_code - 物流单号
     */
    public String getShippingCode() {
        return shippingCode;
    }

    /**
     * 设置物流单号
     *
     * @param shippingCode 物流单号
     */
    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode == null ? null : shippingCode.trim();
    }

    /**
     * 获取买家留言
     *
     * @return buyer_message - 买家留言
     */
    public String getBuyerMessage() {
        return buyerMessage;
    }

    /**
     * 设置买家留言
     *
     * @param buyerMessage 买家留言
     */
    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage == null ? null : buyerMessage.trim();
    }

    /**
     * 获取买家是否已经评价
     *
     * @return buyer_rate - 买家是否已经评价
     */
    public Integer getBuyerRate() {
        return buyerRate;
    }

    /**
     * 设置买家是否已经评价
     *
     * @param buyerRate 买家是否已经评价
     */
    public void setBuyerRate(Integer buyerRate) {
        this.buyerRate = buyerRate;
    }
}