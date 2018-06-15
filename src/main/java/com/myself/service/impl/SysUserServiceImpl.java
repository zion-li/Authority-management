package com.myself.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.myself.beans.PageQuery;
import com.myself.beans.PageResult;
import com.myself.common.RequestHolder;
import com.myself.dao.SysUserMapper;
import com.myself.exception.ParamException;
import com.myself.model.SysUser;
import com.myself.param.UserParam;
import com.myself.service.SysLogService;
import com.myself.service.SysRoleService;
import com.myself.service.SysTreeService;
import com.myself.service.SysUserService;
import com.myself.util.BeanValidator;
import com.myself.util.IpUtil;
import com.myself.util.MD5Util;
import com.myself.util.PasswordUtil;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户功能实现
 * @author zion
 */
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysLogService sysLogService;
    @Resource
    private SysTreeService sysTreeService;
    @Resource
    private SysRoleService sysRoleService;

    private static final String USERNAME = "userName";
    private static final String PASSWORD = "password";

    @Override
    public void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String errorMsg = "";
        String userName = request.getParameter(USERNAME);
        if (StringUtils.isBlank(userName)) {
            errorMsg = "用户名不可以为空";
        }
        String password = request.getParameter(PASSWORD);
        if (StringUtils.isBlank(password)) {
            errorMsg = "密码不可以为空";
        }

        //return 返回信息，登录成功跳转的token
        String ret = request.getParameter("ret");

        SysUser sysUser = sysUserMapper.findByKeyword(userName);
        if (sysUser == null) {
            errorMsg = "查询不到指定的用户";
        } else if (MD5Util.verify(password, sysUser.getPassword())) {
            errorMsg = "用户名或密码错误";
        } else if (sysUser.getStatus() != 1) {
            errorMsg = "用户已被冻结，请联系管理员";
        } else {
            // login success
            request.getSession().setAttribute("user", sysUser);
            //重定向到指定URL
            if (StringUtils.isNotBlank(ret)) {
                response.sendRedirect(ret);
            } else {
                response.sendRedirect("/admin/index.page");
            }
        }
        //以下是无法登陆的页面跳转
        //能执行到这一定是没有成功，跳转到登录页面，
        //还要把错误信息写到登录页面上去，错误信息和用户名
        request.setAttribute("error", errorMsg);
        request.setAttribute("username", userName);
        //ret不为空也返回给前端
        if (StringUtils.isNotBlank(ret)) {
            request.setAttribute("ret", ret);
        }
        String path = "signin.jsp";
        //进行页面跳转
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    public void processLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //销毁跟用户关联session,例如有的用户强制关闭浏览器,而跟踪用户的信息的session还存在,可是用户已经离开了。
        request.getSession().invalidate();
        //session移除了，无法保存登录状态，跳转到登录页面就完事了
        String path = "signin.jsp";
        response.sendRedirect(path);
    }

    @Override
    public void save(UserParam param) {
        BeanValidator.check(param);
        if (checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        if (checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException("邮箱已被占用");
        }
        //生成一个没有顾虑的随机密码
        String tempPassword = PasswordUtil.randomPassword();
        //加密
        String encryptedPassword = MD5Util.generate(tempPassword);
        SysUser sysUser = SysUser.builder().username(param.getUsername())
                .telephone(param.getTelephone()).mail(param.getMail())
                .password(encryptedPassword).deptId(param.getDeptId())
                .status(param.getStatus()).remark(param.getRemark()).build();
        sysUser.setOperateIp(RequestHolder.getCurrentUser().getOperateIp());
        sysUser.setOperateTime(new Date());
        sysUser.setOperator(RequestHolder.getCurrentUser().getUsername());
        //TODO 发送邮件

        sysUserMapper.insertSelective(sysUser);
        //记录日志
        sysLogService.saveUserLog(null, sysUser);
    }

    @Override
    public void update(UserParam param) {
        BeanValidator.check(param);
        if (checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        if (checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException("邮箱已被占用");
        }
        //只是记录日志使用
        SysUser before = sysUserMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的用户不存在");

        //不允许更新密码
        SysUser after = SysUser.builder().id(param.getId()).username(param.getUsername()).telephone(param.getTelephone()).mail(param.getMail())
                .deptId(param.getDeptId()).status(param.getStatus()).remark(param.getRemark()).build();
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperateTime(new Date());
        sysUserMapper.updateByPrimaryKeySelective(after);
        //记录日志
        sysLogService.saveUserLog(before, after);
    }

    @Override
    public PageResult<SysUser> getPageByDeptId(int deptId, PageQuery page) {
        BeanValidator.check(page);
        int count = sysUserMapper.countByDeptId(deptId);
        if (count > 0) {
            List<SysUser> list = sysUserMapper.getPageByDeptId(deptId, page);
            return PageResult.<SysUser>builder().total(count).data(list).build();
        }
        return PageResult.<SysUser>builder().build();
    }

    @Override
    public Map<String, Object> getAclsByUserId(int userId) {
        Map<String,Object> map= Maps.newHashMap();
        map.put("acls",sysTreeService.getUserAclTree(userId));
        map.put("roles",sysRoleService.getRoleListByUserId(userId));
        return map;
    }

    @Override
    public List<SysUser> getAll() {
        return sysUserMapper.getAll();
    }

    /**
     * 查询邮箱是否重复，不包含当前用户的邮箱
     *
     * @param mail   邮箱地址
     * @param userId 用户Id
     * @return
     */
    public boolean checkEmailExist(String mail, Integer userId) {
        return sysUserMapper.countByMail(mail, userId) > 0;
    }

    /**
     * 查询电话号是否重复胡，不包含当前用户的手机号
     *
     * @param telephone
     * @param userId
     * @return
     */
    public boolean checkTelephoneExist(String telephone, Integer userId) {
        return sysUserMapper.countByTelephone(telephone, userId) > 0;
    }

}
