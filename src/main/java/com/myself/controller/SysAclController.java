package com.myself.controller;

import com.myself.beans.PageQuery;
import com.myself.common.JsonData;
import com.myself.param.AclParam;
import com.myself.service.SysAclService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
@Slf4j
@Controller
@RequestMapping("/sys/acl")
public class SysAclController {

    @Resource
    private SysAclService sysAclService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveAclModule(AclParam param) {
        sysAclService.save(param);
        return JsonData.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateAclModule(AclParam param) {
        sysAclService.update(param);
        return JsonData.success();
    }

    @RequestMapping("/page.json")
    @ResponseBody
    public JsonData list(@RequestParam("aclModuleId") Integer aclModuleId, PageQuery pageQuery) {
        return JsonData.success(
                sysAclService.getPageByAclModuleId(aclModuleId, pageQuery));
    }

    @RequestMapping("acls.json")
    @ResponseBody
    public JsonData acls(@RequestParam("aclId") int aclId) {
        return  JsonData.success(sysAclService.getAclByAclId(aclId));
    }
}
