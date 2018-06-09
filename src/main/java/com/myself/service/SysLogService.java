package com.myself.service;

import com.myself.beans.PageQuery;
import com.myself.beans.PageResult;
import com.myself.model.*;
import com.myself.param.SearchLogParam;

import java.util.List;

/**
 * 日志接口
 */
public interface SysLogService {

    /**
     * 保存用户记录
     * @param before
     * @param after
     */
    void saveUserLog(SysUser before,SysUser after);

    /**
     * 保存部门日志
     * @param before
     * @param after
     */
    void saveDeptLog(SysDept before, SysDept after);

    /**
     * 权限记录
     * @param before
     * @param after
     */
    void saveAclLog(SysAcl before, SysAcl after);

    /**
     * 权限模块
     * @param before
     * @param after
     */
    void saveAclModuleLog(SysAclModule before, SysAclModule after);

    /**
     * 角色模块
     * @param before
     * @param after
     */
    void saveRoleLog(SysRole before, SysRole after);

    /**
     * 记录恢复
     * @param id
     */
    void recover(int id);

    /**
     * 查询记录列表
     * @param param
     * @param page
     * @return
     */
    PageResult<SysLogWithBLOBs> searchPageList(SearchLogParam param, PageQuery page);

    /**
     * 用户角色日志
     * @param roleId
     * @param before
     * @param after
     */
    void saveRoleUserLog(int roleId, List<Integer> before, List<Integer> after);

    /**
     * 角色权限日志
     * @param roleId
     * @param before
     * @param after
     */
    void saveRoleAclLog(int roleId,  List<Integer> before, List<Integer> after);
}
