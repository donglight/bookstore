package org.zdd.bookstore.web.controller.admin;

import org.zdd.bookstore.model.service.IStoreService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminIndexController {

    @Autowired
    private IStoreService storeService;


    @RequestMapping({"", "/", "/index"})
    @RequiresPermissions("system")
    public String adminIndex() {
        return "admin/index";
    }
}
