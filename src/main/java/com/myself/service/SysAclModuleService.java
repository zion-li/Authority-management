package com.myself.service;

import com.myself.param.AclModuleParam;

public interface SysAclModuleService {
    void save(AclModuleParam param);

    void update(AclModuleParam param);

    Object aclModuleTree();

    void delete(int id);
}
