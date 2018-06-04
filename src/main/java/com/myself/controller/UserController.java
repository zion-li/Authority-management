package com.myself.controller;

import com.myself.beans.PageQuery;
import com.myself.beans.PageResult;
import com.myself.common.JsonData;
import com.myself.model.SysUser;
import com.myself.param.UserParam;
import com.myself.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/sys/user")
public class UserController {

    @Resource
    private SysUserService sysUserService;
    /**
     * 用户登出跳转登录界面
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/logout.page")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        sysUserService.processLogout(request,response);
    }

    /**
     * 用户登录，跳转token界面，没有token跳转默认页面
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping("/login.page")
    public void login(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        sysUserService.processLogin(request,response);
    }

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
    public JsonData save(UserParam param){
        sysUserService.save(param);
        return  JsonData.success();
    }

    /**
     * 用户更新
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
     * 分页查询
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


}
