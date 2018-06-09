package com.myself.service;

import com.myself.model.SysRole;
import com.myself.model.SysUser;
import com.myself.param.RoleParam;

import java.util.List;
import java.util.Map;

/**
 * 角色接口定义
 * @author zion
 */
public interface SysRoleService {
    /**
     * 根据权限获取角色列表
     * @param aclId
     * @return
     */
    List<SysRole> getRoleListByAclId(int aclId);

    /**
     * 根据角色获取用户列表
     * @param roleList
     * @return
     */
    Object getUserListByRoleList(List<SysRole> roleList);

    /**
     * 更具用户角色
     * @param userId
     * @return
     */
    Object getRoleListByUserId(int userId);

    /**
     * 获取角色树
     * @param roleId
     * @return
     */
    Object getRoleTreeByRoleId(int roleId);

    /**
     * 获取全部角色
     * @return
     */
    Object getAll();

    /**
     * 保存角色
     * @param param
     */
    void save(RoleParam param);

    /**
     * 更新角色
     * @param param
     */
    void update(RoleParam param);

    /**
     * 变更角色权限
     * @param roleId
     * @param aclIds
     */
    void changeRoleAcls(int roleId, String aclIds);

    /**
     * 变更用户角色
     * @param roleId
     * @param userIds
     */
    void changeRoleUsers(int roleId, String userIds);

    /**
     * 获取特定角色用户列表
     * @param roleId
     * @return
     */
    Map<String, List<SysUser>> getUserListByRoleId(int roleId);
}
