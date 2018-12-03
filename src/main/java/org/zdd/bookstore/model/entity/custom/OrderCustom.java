package org.zdd.bookstore.model.entity.custom;

import org.zdd.bookstore.model.entity.Orders;
import org.zdd.bookstore.model.entity.OrderDetail;
import org.zdd.bookstore.model.entity.OrderShipping;

import java.util.List;

/**
 * 自定义Order实体，包括订单物流信息，订单详情，非mybaits逆向工程生成
 */
public class OrderCustom {


    private String orderId;

    private Orders order;

    private OrderShipping orderShipping;
    /**
     * 一个订单包括多个订单信息
     */
    private List<OrderDetail> orderDetails;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(OrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
