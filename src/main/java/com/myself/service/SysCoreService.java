package com.myself.service;

import com.myself.model.SysAcl;

import java.util.List;

public interface SysCoreService {
    List<SysAcl> getUserAclList(int userId);

    List<SysAcl> getCurrentUserAclList();

    List<SysAcl> getRoleAclList(int roleId);

    boolean hasUrlAcl(String url);
}
