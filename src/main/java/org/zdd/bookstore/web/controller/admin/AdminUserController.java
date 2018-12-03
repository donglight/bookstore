package org.zdd.bookstore.web.controller.admin;

import org.zdd.bookstore.common.pojo.BSResult;
import org.zdd.bookstore.model.dao.custom.CustomMapper;
import org.zdd.bookstore.model.entity.Role;
import org.zdd.bookstore.model.entity.User;
import org.zdd.bookstore.model.service.IRoleService;
import org.zdd.bookstore.model.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private CustomMapper customMapper;

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/echo/{userId}")
    @RequiresPermissions("user-edit")
    public String echo(@PathVariable("userId") int userId,Model model){
        User user = userService.findById(userId);
        List<Role> userRoles = customMapper.findRolesByUserId(userId);
        List<Role> allRoles = roleService.findAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("userRoles", userRoles);
        model.addAttribute("allRoles", allRoles);
        return "admin/user/edit";
    }

    @RequestMapping("/update")
    @RequiresPermissions("user-edit")
    public String updateUser(User user, int[] roleIds, Model model){
        BSResult bsResult = userService.updateUser(user);
        if(bsResult.getCode() == 200){
            model.addAttribute("saveMsg","保存成功");
        }
        //更新用户角色的对应关系
        roleService.updateUserRoleRelationship(user.getUserId(),roleIds);
        return "forward:/admin/user/echo/"+user.getUserId();
    }

    @RequestMapping("/addition")
    @RequiresPermissions("user-add")
    public String addUser(User user, int[] roleIds){
        User userFromDB = userService.addUser(user);
        //更新用户角色的对应关系
        roleService.updateUserRoleRelationship(userFromDB.getUserId(),roleIds);
        return "forward:list";
    }

    @RequestMapping("/deletion/{userId}")
    @RequiresPermissions("user-delete")
    public String delUser(@PathVariable("userId") int userId){
        userService.delUser(userId);
        return "forward:../list";
    }

    @RequestMapping("/list")
    @RequiresPermissions("user-list")
    public String userList(Model model){
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "admin/user/list";
    }

    @RequestMapping("/toAddition")
    @RequiresPermissions("user-add")
    public String toAddition(Model model){
        model.addAttribute("allRoles", roleService.findAllRoles());
        return "admin/user/add";
    }

    @RequestMapping("/logout")
    @CacheEvict(cacheNames="authenticationCache",allEntries = true)
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/admin/index";
    }
}
