package com.myself.service;


import com.myself.dto.AclModuleLevelDto;
import com.myself.dto.DeptLevelDto;

import java.util.List;

/**
 * 树形结构遍历接口
 * @author zion
 */
public interface SysTreeService {

    /**
     * 部门
     * @return
     */
    List<DeptLevelDto> deptTree();

    /**
     * 权限模块
     * @return
     */
    List<AclModuleLevelDto> aclModuleTree();

    /**
     * 用户权限
     * @param userId
     * @return
     */
    Object getUserAclTree(int userId);

    /**
     * 角色
     * @param roleId
     * @return
     */
    List<AclModuleLevelDto> roleTree(int roleId);
}
