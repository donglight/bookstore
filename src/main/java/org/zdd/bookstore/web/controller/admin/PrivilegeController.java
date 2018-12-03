package org.zdd.bookstore.web.controller.admin;

import org.zdd.bookstore.common.pojo.BSResult;
import org.zdd.bookstore.common.pojo.ZTreeNode;
import org.zdd.bookstore.model.entity.Privilege;
import org.zdd.bookstore.model.service.IPrivilegeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("admin/privilege")
@RequiresPermissions("privilege-manage")
public class PrivilegeController {


    @Autowired
    private IPrivilegeService privilegeService;

    @ResponseBody
    @RequestMapping("/treeNodes")
    public List<ZTreeNode> treeNodesJsonData(){
        return privilegeService.getZTreeNodes();
    }

    @ResponseBody
    @RequestMapping("/rolePrivileges/{roleId}")
    public List<ZTreeNode> treeRolePrivileges(@PathVariable("roleId") int roleId){
        return privilegeService.getRolePrivileges(roleId);
    }


    @ResponseBody
    @RequestMapping("/{privId}")
    public BSResult getPrivilege(@PathVariable("privId") int privId){
        return privilegeService.findById(privId);
    }


    @RequestMapping("/toEdit/{roleId}")
    @RequiresPermissions("privilege-edit")
    public String toEditPrivilege(@PathVariable("roleId") int roleId,Model model){
        model.addAttribute("roleId", roleId);
        return "admin/privilege/edit";
    }


    @ResponseBody
    @RequestMapping("/edit")
    @RequiresPermissions("privilege-edit")
    public BSResult editPrivilege(Privilege privilege){

        BSResult bsResult = privilegeService.updatePrivilege(privilege);

        return bsResult;
    }

    @RequestMapping("/list")
    @RequiresPermissions("privilege-list")
    public String privilegeList(){
        return "admin/privilege/list";
    }

    @RequestMapping("/addition")
    @ResponseBody
    @RequiresPermissions("privilege-add")
    public BSResult addPrivilege(Privilege privilege){
        //添加权限，仅有权限名字，之后再修改
        BSResult bsResult = privilegeService.addPrivilege(privilege);
        return bsResult;
    }

    @RequestMapping("/deletion/{privId}")
    @ResponseBody
    @RequiresPermissions("privilege-delete")
    public BSResult deletePrivilege(@PathVariable("privId") int privId){
        BSResult bsResult = privilegeService.deleteById(privId);
        return bsResult;
    }

}
