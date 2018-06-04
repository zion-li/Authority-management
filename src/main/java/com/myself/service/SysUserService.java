package com.myself.service;

import com.myself.beans.PageQuery;
import com.myself.beans.PageResult;
import com.myself.model.SysUser;
import com.myself.param.UserParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface SysUserService {

    /**
     * 登录请求认证
     * @param request
     * @param response
     */
    void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    /**
     * 登出请求
     * @param request
     * @param response
     */
    void processLogout(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * 添加用户
     * @param param
     */
    void save(UserParam param);

    /**
     * 更新用户
     * @param param
     */
    void update(UserParam param);

    /**
     * 分页查询用户
     * @param deptId
     * @param pageQuery
     * @return
     */
    PageResult<SysUser> getPageByDeptId(int deptId, PageQuery pageQuery);
}
