package com.myself.service;

import com.myself.beans.PageQuery;
import com.myself.param.AclParam;

import java.util.Map;

public interface SysAclService {
    void save(AclParam param);

    void update(AclParam param);

    Object getPageByAclModuleId(Integer aclModuleId, PageQuery pageQuery);

    Map getaclsByAclId(int aclId);
}
