package com.myself.service.impl;

import com.google.common.base.Preconditions;
import com.myself.beans.PageQuery;
import com.myself.beans.PageResult;
import com.myself.common.RequestHolder;
import com.myself.dao.SysUserMapper;
import com.myself.exception.ParamException;
import com.myself.model.SysUser;
import com.myself.param.UserParam;
import com.myself.service.SysLogService;
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

/**
 * 用户功能实现
 */
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysLogService sysLogService;

    private static final String USERNAME = "userName";
    private static final String PASSWORD = "password";

    @Override
    public void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String errorMsg = "";
        String userName = request.getParameter(USERNAME);
        if (StringUtils.isBlank(userName)) {
            throw new ParamException("用户名不可以为空");
        }
        String password = request.getParameter(PASSWORD);
        if (StringUtils.isBlank(password)) {
            throw new ParamException("密码不可以为空");
        }
        SysUser sysUser = sysUserMapper.findByKeyword(userName);
        if (sysUser == null) {
            throw new ParamException("查询不到指定的用户");
        } else if (MD5Util.verify(password, sysUser.getPassword())) {
            throw new ParamException("用户名或密码错误");
        } else if (sysUser.getStatus() != 1) {
            throw new ParamException("用户已被冻结，请联系管理员");
        } else {
            // login success
            String ret = request.getParameter("ret");
            if (StringUtils.isNotBlank(ret)) {
                response.sendRedirect(ret);
            } else {
                response.sendRedirect("/admin/index.page");
            }

            request.setAttribute("error", errorMsg);
            request.setAttribute("username", userName);
            if (StringUtils.isNotBlank(ret)) {
                request.setAttribute("ret", ret);
            }
            String path = "signin.jsp";
            request.getRequestDispatcher(path).forward(request, response);
        }
    }

    @Override
    public void processLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //销毁跟用户关联session,例如有的用户强制关闭浏览器,而跟踪用户的信息的session还存在,可是用户已经离开了。
        request.getSession().invalidate();
        String path="signin.jsp";
        response.sendRedirect(path);
    }

    @Override
    public void save(UserParam param) {
        BeanValidator.check(param);
        if(checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        if(checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException("邮箱已被占用");
        }
        //生成一个没有顾虑的随机密码
       String tempPassword= PasswordUtil.randomPassword();
        //加密
        String encryptedPassword= MD5Util.generate(tempPassword);
        SysUser sysUser=SysUser.builder().username(param.getUsername())
                .telephone(param.getTelephone()).mail(param.getMail())
                .password(encryptedPassword).deptId(param.getDeptId())
                .status(param.getStatus()).remark(param.getRemark()).build();
        sysUser.setOperateIp(RequestHolder.getCurrentUser().getOperateIp());
        sysUser.setOperateTime(new Date());
        sysUser.setOperator(RequestHolder.getCurrentUser().getUsername());

        //TODO 发送邮件

        sysUserMapper.insertSelective(sysUser);
        //记录日志
        sysLogService.saveUserLog(null,sysUser);
    }

    @Override
    public void update(UserParam param) {
        BeanValidator.check(param);
        if(checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        if(checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException("邮箱已被占用");
        }

        SysUser before = sysUserMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的用户不存在");

        SysUser after = SysUser.builder().id(param.getId()).username(param.getUsername()).telephone(param.getTelephone()).mail(param.getMail())
                .deptId(param.getDeptId()).status(param.getStatus()).remark(param.getRemark()).build();
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperateTime(new Date());
        sysUserMapper.updateByPrimaryKeySelective(after);
        //记录日志
        sysLogService.saveUserLog(before,after);
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

    /**
     * 查询邮箱是否重复，不包含当前用户的邮箱
     * @param mail 邮箱地址
     * @param userId 用户Id
     * @return
     */
    public boolean checkEmailExist(String mail, Integer userId) {
        return sysUserMapper.countByMail(mail, userId) > 0;
    }

    /**
     * 查询电话号是否重复胡，不包含当前用户的手机号
     * @param telephone
     * @param userId
     * @return
     */
    public boolean checkTelephoneExist(String telephone, Integer userId) {
        return sysUserMapper.countByTelephone(telephone, userId) > 0;
    }

}
