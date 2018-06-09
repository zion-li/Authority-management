package com.myself.controller;

import com.myself.common.JsonData;
import com.myself.param.RoleParam;
import com.myself.service.SysRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sys/role")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    @RequestMapping("role.page")
    public ModelAndView page() {
        return new ModelAndView("role");
    }

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveRole(RoleParam param) {
        sysRoleService.save(param);
        return JsonData.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateRole(RoleParam param) {
        sysRoleService.update(param);
        return JsonData.success();
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public JsonData list() {
        return JsonData.success(sysRoleService.getAll());
    }

    @RequestMapping("/roleTree.json")
    @ResponseBody
    public JsonData roleTree(@RequestParam("roleId") int roleId) {
        return JsonData.success(sysRoleService.getRoleTreeByRoleId(roleId));
    }

    @RequestMapping("/changeAcls.json")
    @ResponseBody
    public JsonData changeAcls(@RequestParam("roleId") int roleId, @RequestParam(value = "aclIds", required = false, defaultValue = "") String aclIds) {
        sysRoleService.changeRoleAcls(roleId,aclIds);
        return JsonData.success();
    }

    @RequestMapping("/changeUsers.json")
    @ResponseBody
    public JsonData changeUsers(@RequestParam("roleId") int roleId, @RequestParam(value = "userIds", required = false, defaultValue = "") String userIds) {
        sysRoleService.changeRoleUsers(roleId, userIds);
        return JsonData.success();
    }

    @RequestMapping("/users.json")
    @ResponseBody
    public JsonData users(@RequestParam("roleId") int roleId) {

        return JsonData.success(sysRoleService.getUserListByRoleId(roleId));
    }
}
