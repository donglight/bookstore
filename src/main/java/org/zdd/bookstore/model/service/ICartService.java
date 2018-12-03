package org.zdd.bookstore.model.service;

import org.zdd.bookstore.common.pojo.BSResult;
import org.zdd.bookstore.model.entity.custom.Cart;
import org.zdd.bookstore.model.entity.BookInfo;

import javax.servlet.http.HttpServletRequest;

public interface ICartService {

    BSResult addToCart(BookInfo bookInfo, Cart cart,int buyNum);

    BSResult clearCart(HttpServletRequest request,String sessionName);

    BSResult deleteCartItem(int bookId, HttpServletRequest request);

    BSResult updateBuyNum(int bookId, int newNum, HttpServletRequest request);

    BSResult checkedOrNot(Cart cart,int bookId);

}
