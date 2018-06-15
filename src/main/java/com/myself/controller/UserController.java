package com.myself.controller;

import com.myself.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
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
}
