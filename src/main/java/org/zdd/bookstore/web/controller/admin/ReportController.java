package org.zdd.bookstore.web.controller.admin;

import org.zdd.bookstore.common.pojo.Bar;
import org.zdd.bookstore.common.pojo.Pie;
import org.zdd.bookstore.model.entity.Store;
import org.zdd.bookstore.model.service.IBookInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/report")
@RequiresPermissions("store-manage")
public class ReportController {

    @Autowired
    private IBookInfoService bookInfoService;

    /**
     * 商店书籍访问量排行饼图
     * @return
     */
    @RequestMapping("/views/pie")
    public List<Pie> getBookViewsPieJson(HttpSession session){
        Store loginStore = (Store) session.getAttribute("loginStore");
        if(loginStore == null){
            return new ArrayList<>();
        }
        return bookInfoService.getBookViewsPiesByStoreId(loginStore.getStoreId());
    }

    @RequestMapping("/sales/bar")
    public Bar getBookSalesBarJson(HttpSession session){
        Store loginStore = (Store) session.getAttribute("loginStore");
        if(loginStore == null){
            return null;
        }
        return bookInfoService.getBookSalesBarJson(loginStore.getStoreId());
    }


}
