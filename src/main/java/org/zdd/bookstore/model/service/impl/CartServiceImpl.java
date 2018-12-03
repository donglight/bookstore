package org.zdd.bookstore.model.service.impl;

import org.zdd.bookstore.common.pojo.BSResult;
import org.zdd.bookstore.common.utils.BSResultUtil;
import org.zdd.bookstore.model.entity.custom.Cart;
import org.zdd.bookstore.model.entity.custom.CartItem;
import org.zdd.bookstore.model.entity.BookInfo;
import org.zdd.bookstore.model.service.ICartService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class CartServiceImpl implements ICartService {

    @Override
    public BSResult addToCart(BookInfo bookInfo, Cart cart, int buyNum) {

        //购物车为空，新建一个
        if (cart == null) {
            cart = new Cart();
        }
        Map<Integer, CartItem> cartItems = cart.getCartItems();
        double total = 0;
        if (cartItems.containsKey(bookInfo.getBookId())) {
            CartItem cartItem = cartItems.get(bookInfo.getBookId());
            cartItem.setBuyNum(cartItem.getBuyNum() + buyNum);
            cartItem.setSubTotal(cartItem.getBuyNum() * bookInfo.getPrice().doubleValue());
            cartItem.setBookInfo(bookInfo);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setBuyNum(buyNum);
            cartItem.setBookInfo(bookInfo);
            cartItem.setSubTotal(bookInfo.getPrice().doubleValue() * buyNum);
            cartItems.put(bookInfo.getBookId(), cartItem);
        }
        for (CartItem cartItem : cartItems.values()) {
            total += cartItem.getSubTotal();
        }
        cart.setTotal(total);

        return BSResultUtil.success(cart);

    }

    @Override
    public BSResult clearCart(HttpServletRequest request, String sessionName) {
        request.getSession().removeAttribute(sessionName);
        return BSResultUtil.success();
    }

    @Override
    public BSResult deleteCartItem(int bookId, HttpServletRequest request) {

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        Map<Integer, CartItem> cartItems = cart.getCartItems();
        if (cartItems.containsKey(bookId)) {
            CartItem cartItem = cartItems.get(bookId);
            cart.setTotal(cart.getTotal() - cartItem.getSubTotal());
            cartItems.remove(bookId);
        }
        request.getSession().setAttribute("cart", cart);
        return BSResultUtil.success();
    }

    @Override
    public BSResult updateBuyNum(int bookId, int newNum, HttpServletRequest request) {

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        Map<Integer, CartItem> cartItems = cart.getCartItems();
        if (cartItems.containsKey(bookId)) {
            //取出订单项所对应的书籍，根据新的购买数量重新计算小计
            CartItem cartItem = cartItems.get(bookId);
            //不知道是加还是减去商品的数量，所以先减去原来的购物项小计，最后再加新的小计
            cart.setTotal(cart.getTotal() - cartItem.getSubTotal());
            BookInfo bookInfo = cartItem.getBookInfo();
            cartItem.setSubTotal(
                    bookInfo.getPrice().doubleValue() * newNum);

            cartItem.setBuyNum(newNum);
            cart.setTotal(cart.getTotal() + cartItem.getSubTotal());
        }

        request.getSession().setAttribute("cart", cart);
        return BSResultUtil.success();
    }

    @Override
    public BSResult checkedOrNot(Cart cart, int bookId) {
        Map<Integer, CartItem> cartItems = cart.getCartItems();

        if (cartItems.containsKey(bookId)) {
            CartItem cartItem = cartItems.get(bookId);
            if (cartItem.isChecked()) {
                //如果之前是true，那就设为false
                cartItem.setChecked(false);
                cart.setTotal(cart.getTotal() - cartItem.getSubTotal());
                cartItem.setSubTotal(0.00);
            } else {
                //如果之前是false，那就设为true
                cartItem.setChecked(true);
                cartItem.setSubTotal(cartItem.getBuyNum() * cartItem.getBookInfo().getPrice().doubleValue());
                cart.setTotal(cart.getTotal() + cartItem.getSubTotal());

            }
            return BSResultUtil.success();
        } else
            return BSResultUtil.build(400, "购物车没有这本书籍!");
    }

}
