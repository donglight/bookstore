package org.zdd.bookstore.web.controller.admin;

import org.zdd.bookstore.common.pojo.BSResult;
import org.zdd.bookstore.common.utils.BSResultUtil;
import org.zdd.bookstore.model.entity.OrderShipping;
import org.zdd.bookstore.model.entity.Orders;
import org.zdd.bookstore.model.entity.Store;
import org.zdd.bookstore.model.entity.User;
import org.zdd.bookstore.model.entity.custom.OrderCustom;
import org.zdd.bookstore.model.service.IOrderService;
import org.zdd.bookstore.model.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("admin/order")
@RequiresPermissions("order-manage")
public class AdminOrderController {


    @Autowired
    private IOrderService orderService;

    @Autowired
    private IUserService userService;


    @RequestMapping("/list")
    @RequiresPermissions("order-list")
    public String orderList(HttpServletRequest request){

        Store loginStore = (Store) request.getSession().getAttribute("loginStore");

        List<OrderCustom> orderCustoms = orderService.findOrdersByStoreId(loginStore.getStoreId());

        request.setAttribute("orderCustoms", orderCustoms);

        return "admin/order/list";
    }

    /**
     * 更新订单
     * @param orderId
     * @return
     */
    @RequestMapping("/toUpdate/{orderId}")
    @RequiresPermissions("order-edit")
    public String updateOrder(@PathVariable("orderId") String orderId, Model model) {

        OrderCustom orderCustom = orderService.findOrderCustomById(orderId);

        User buyer = userService.findById(orderCustom.getOrder().getUserId());
        model.addAttribute("orderCustom", orderCustom);
        model.addAttribute("buyer", buyer);
        return "admin/order/edit";
    }

    @RequestMapping("/update")
    @RequiresPermissions("order-edit")
    public String updateOrder(Orders order, OrderShipping orderShipping, Model model) {

        OrderCustom orderCustom = new OrderCustom();
        orderCustom.setOrder(order);
        orderCustom.setOrderShipping(orderShipping);
        orderService.updateOrder(orderCustom);
        model.addAttribute("saveMsg", "保存成功");
        return "forward:toUpdate/"+order.getOrderId();
    }

    @RequestMapping("/deletion/{orderId}")
    @RequiresPermissions("order-delete")
    public String deletion(@PathVariable("orderId") String orderId) {

        BSResult bsResult = orderService.deleteOrder(orderId);

        if (bsResult.getCode() == 200) {
            return "redirect:/admin/order/list";
        }
        return "exception";
    }

    /**
     * 发货
     * @param orderId
     * @return
     */
    @RequiresPermissions("order-edit")
    @RequestMapping("/post/{orderId}")
    public String postOrder(@PathVariable("orderId") String orderId){
        BSResult bsResult =  orderService.postOrder(orderId);
        if (bsResult.getCode() == 200) {
            return "redirect:/admin/order/list";
        }
        return "exception";
    }

    /**
     * 查看买家信息
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("/buyer/{userId}")
    @ResponseBody
    @RequiresPermissions("order-list")
    public BSResult buyerInfo(@PathVariable("userId") Integer userId,Model model){
        User buyer = userService.findById(userId);
        return BSResultUtil.success(buyer);
    }
}
