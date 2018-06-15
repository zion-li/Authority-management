package com.myself.controller;

import com.myself.beans.PageQuery;
import com.myself.beans.PageResult;
import com.myself.common.JsonData;
import com.myself.model.SysUser;
import com.myself.param.UserParam;
import com.myself.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 用户接口开发
 */
@Slf4j
@Controller
@RequestMapping("/sys/user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 跳转没有权限页面
     * @return
     */
    @RequestMapping("/noAuth.page")
    @ResponseBody
    public ModelAndView noAuth(){
        return new ModelAndView("noAuth");
    }

    /**
     * 添加用户
     * @param param 用户输入参数
     * @return
     */
    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveUser(UserParam param){
        sysUserService.save(param);
        return  JsonData.success();
    }

    /**
     * 用户更新，后台使用的
     * @param param
     * @return
     */
    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateUser(UserParam param) {
        sysUserService.update(param);
        return JsonData.success();
    }

    /**
     * 分页展示用户列表
     * @param deptId
     * @param pageQuery
     * @return
     */
    @RequestMapping("/page.json")
    @ResponseBody
    public JsonData page(@RequestParam("deptId") int deptId, PageQuery pageQuery) {
        PageResult<SysUser> result = sysUserService.getPageByDeptId(deptId, pageQuery);
        return JsonData.success(result);
    }

    /**
     *
     * @param userId
     * @return
     */
    @RequestMapping("/acls.json")
    @ResponseBody
    public JsonData acls(@RequestParam("userId") int userId){
        return JsonData.success(sysUserService.getAclsByUserId(userId));
    }

}
