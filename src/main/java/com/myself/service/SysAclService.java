package com.myself.service;

import com.myself.beans.PageQuery;
import com.myself.param.AclParam;

import java.util.Map;

/**
 * 权限接口
 */
public interface SysAclService {
    /**
     * 保存权限
     * @param param
     */
    void save(AclParam param);

    /**
     * 更新
     * @param param
     */
    void update(AclParam param);

    /**
     * 获取权限列表
     * @param aclModuleId
     * @param pageQuery
     * @return
     */
    Object getPageByAclModuleId(Integer aclModuleId, PageQuery pageQuery);

    /**
     * 获取特定权限
     * @param aclId
     * @return
     */
    Map getAclByAclId(int aclId);
}
