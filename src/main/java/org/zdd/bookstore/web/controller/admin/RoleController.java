package org.zdd.bookstore.web.controller.admin;

import org.zdd.bookstore.common.pojo.BSResult;
import org.zdd.bookstore.model.entity.Role;
import org.zdd.bookstore.model.service.IRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/role")
@RequiresPermissions("role-manage")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/list")
    @RequiresPermissions("role-list")
    public String roleList(Model model){
        List<Role> allRoles = roleService.findAllRoles();
        model.addAttribute("allRoles", allRoles);
        return "admin/role/list";
    }

    @RequestMapping("/echo/{roleId}")
    @RequiresPermissions("role-edit")
    public String toEdit(@PathVariable("roleId") int roleId, Model model){

        Role role = roleService.findById(roleId);

        model.addAttribute("role", role);

        return "admin/role/edit";
    }


    @RequestMapping("/deletion/{roleId}")
    @RequiresPermissions("role-delete")
    public String deleteRole(@PathVariable("roleId")int roleId){

        roleService.deleteById(roleId);

        return "forward:../list";
    }

    @RequestMapping("/toAddition")
    @RequiresPermissions("role-add")
    public String toAdd(){
        return "admin/role/add";
    }

    @RequestMapping("/addition")
    @RequiresPermissions("role-add")
    public String addRole(Role role){
        roleService.addRole(role);
        return "forward:list";
    }

    @RequestMapping("/edit")
    @RequiresPermissions("role-edit")
    public String updateRole(Role role,Model model){
        roleService.updateRole(role);
        model.addAttribute("saveMsg", "保存成功");
        return "forward:echo/"+role.getRoleId();
    }

    @RequestMapping("/updatePrivilege")
    @ResponseBody
    @RequiresPermissions("role-edit")
    public BSResult updateRolePrivilege(int[] ids,int roleId){
        BSResult bsResult = roleService.updateRolesPrivilege(ids,roleId);
        return bsResult;
    }

}
