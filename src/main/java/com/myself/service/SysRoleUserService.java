package com.myself.service;

import com.myself.model.SysUser;

import java.util.List;

/**
 * 用户角色映射接口
 * @author zion
 */
public interface SysRoleUserService {
    /**
     * 改变角色用户列表
     * @param roleId
     * @param userIdList
     */
    void changeRoleUsers(int roleId, List<Integer> userIdList);

    /**
     * 获取角色用具列表
     * @param roleId
     * @return
     */
    List<SysUser> getListByRoleId(int roleId);
}
