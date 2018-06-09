package com.myself.service;

import com.myself.beans.PageQuery;
import com.myself.beans.PageResult;
import com.myself.common.JsonData;
import com.myself.model.SysUser;
import com.myself.param.UserParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 用户接口
 * @author zion
 */
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
     * 根据部门分页查询用户
     * @param deptId
     * @param pageQuery
     * @return
     */
    PageResult<SysUser> getPageByDeptId(int deptId, PageQuery pageQuery);

    /**
     * 获取用户角色以及权限
     * @param userId
     * @return
     */
    Map<String, Object> getAclsByUserId(int userId);

    /**
     * 获取所有用户
     * @return
     */
    List<SysUser> getAll();
}
